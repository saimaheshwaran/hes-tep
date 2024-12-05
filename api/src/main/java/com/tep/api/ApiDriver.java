package com.tep.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tep.api.config.ApiConfig;
import com.tep.api.config.ApiConstants;
import com.tep.api.config.ApiEnums;
import com.tep.api.config.ApiKeys;
import com.tep.api.response.ApiResponse;
import com.tep.api.response.ApiSchema;
import com.tep.utilities.JsonUtils;
import com.tep.utilities.MethodUtils;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tep.utilities.StringUtils.secret;

/**
 * ApiDriver class for configuring and executing API requests.
 * It supports setting various request parameters like headers, cookies, query parameters, etc.,
 * handling proxy settings, and executing the request with retry capabilities.
 */
@Data
public class ApiDriver {

    private static final Logger logger = LoggerFactory.getLogger(ApiDriver.class);

    // Request configuration fields
    private String apiName;
    private String baseUri;
    private String basePath;
    private String endPoint;
    private Map<String, String> pathParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Object body;
    private String proxyHost;
    private Integer proxyPort;
    private String proxySchema;
    private String proxyUrl;
    private String proxyUsername;
    private String proxyPassword;
    private Response response;
    private Map<String, String> formParams = new HashMap<>();

    // ObjectMapper for JSON processing
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor to set up default RestAssured configuration for logging and HTTP timeouts.
     */
    public ApiDriver() {
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enablePrettyPrinting(true)
                        .enableLoggingOfRequestAndResponseIfValidationFails())
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000));
    }

    /**
     * Returns the ApiResponse object wrapping the last executed response.
     */
    public ApiResponse Response() {
        return (response == null) ? null : new ApiResponse(response);
    }

    /**
     * Returns the ApiSchema object wrapping the last executed response schema.
     */
    public ApiSchema Schema() {
        return (response == null) ? null : new ApiSchema(response);
    }

    /**
     * Sets API-specific configuration from the ApiConfig.
     * This includes base URI, path parameters, headers, cookies, etc.
     */
    public void setConfigFromYaml(String apiName) {
        this.apiName = apiName;
        this.baseUri = ApiConfig.get(apiName, ApiKeys.BASE_URI);
        this.basePath = ApiConfig.get(apiName, ApiKeys.BASE_PATH);
        this.endPoint = ApiConfig.get(apiName, ApiKeys.ENDPOINT);
        this.pathParams = ApiConfig.get(apiName, ApiKeys.PATH_PARAMS);
        this.headers = ApiConfig.get(apiName, ApiKeys.HEADERS);
        this.cookies = ApiConfig.get(apiName, ApiKeys.COOKIES);
        this.queryParams = ApiConfig.get(apiName, ApiKeys.QUERY_PARAMS);
        this.body = ApiConfig.get(apiName, ApiKeys.BODY);
        this.formParams = ApiConfig.get(apiName, ApiKeys.FORM_PARAMS);

        configureProxySettings(apiName);
    }

    /**
     * Configures the proxy settings if enabled in the ApiConfig.
     * Throws IllegalArgumentException if proxy URL is malformed.
     */
    private void configureProxySettings(String apiName) {
        Boolean proxyEnabled = Objects.requireNonNullElse(ApiConfig.get(apiName, ApiKeys.PROXY_ENABLED), Boolean.FALSE);

        if (proxyEnabled) {
            proxyUrl = ApiConfig.get(apiName, ApiKeys.PROXY_URL);
            proxyUsername = ApiConfig.get(apiName, ApiKeys.PROXY_USERNAME);
            proxyPassword = ApiConfig.get(apiName, ApiKeys.PROXY_PASSWORD);

            validateProxyConfig();

            try {
                URL parsedUrl = new URL(proxyUrl);
                proxySchema = parsedUrl.getProtocol();
                proxyHost = parsedUrl.getHost();
                proxyPort = parsedUrl.getPort();

                // Set default proxy scheme and port if not specified
                if (proxySchema == null) {
                    logger.info("No protocol specified for proxy URL '{}'. Using default - http.", proxyUrl);
                    proxySchema = "http";
                }
                if (proxyHost == null) {
                    throw new IllegalArgumentException("Proxy URL does not contain a valid host. URL: " + proxyUrl);
                }
                if (proxyPort == -1) {
                    logger.info("No port specified for proxy URL: '{}'", proxyUrl);
                }
            } catch (MalformedURLException e) {
                String message = String.format("Proxy URL is not correctly formatted: '%s'. Error: %s", proxyUrl, e.getMessage());
                logger.error(message);
                throw new IllegalArgumentException(message, e);
            }
        } else {
            resetProxySettings();
        }
    }

    /**
     * Validates the proxy configuration for missing credentials or URL.
     */
    private void validateProxyConfig() {
        if (proxyUrl == null) logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_URL);
        if (proxyUsername == null) logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_USERNAME);
        if (proxyPassword == null) logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_PASSWORD);
    }

    /**
     * Resets proxy settings to null when proxy is not enabled.
     */
    private void resetProxySettings() {
        proxyUrl = proxyUsername = proxyPassword = proxySchema = proxyHost = null;
        proxyPort = null;
    }

    /**
     * Executes the HTTP request using RestAssured, with retry functionality if enabled.
     */
    public ApiResponse executeRequest(ApiEnums.Http_Method httpMethod) {
        response = null;
        String endpoint = Objects.requireNonNullElse(this.endPoint, "");
        Boolean retryOnErrorEnabled = Objects.requireNonNullElse(ApiConfig.get(apiName, ApiKeys.RETRY_ON_ERROR_ENABLED), Boolean.FALSE);

        if (retryOnErrorEnabled) {
            response = executeRequestWithRetryMechanism(httpMethod, endpoint);

        } else {
            try {
                switch (httpMethod) {
                    case GET -> response = buildRequestSpec().request("GET", endpoint);
                    case POST -> response = buildRequestSpec().request("POST", endpoint);
                }
            } catch (Exception e) {
                logger.error("Error executing request: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return (response == null) ? null : new ApiResponse(response);
    }

    /**
     * Executes the request with retry on failure.
     */
    public Response executeRequestWithRetryMechanism(ApiEnums.Http_Method httpMethod, String endpoint) {
        logger.info("Retry configuration found.");
        Integer maxRetries = Objects.requireNonNullElse(ApiConfig.get(apiName, ApiKeys.RETRY_ON_ERROR_MAX_COUNT), ApiConstants.DEFAULT_MAX_RETRIES);
        Integer maxBackoffMs = Objects.requireNonNullElse(ApiConfig.get(apiName, ApiKeys.RETRY_ON_ERROR_MAX_BACKOFF_MS), ApiConstants.DEFAULT_MAX_BACKOFF_MS);

        logger.info("Executing request with retry on error: Max Retries = {}, Max Backoff ms = {}", maxRetries, maxBackoffMs);

        switch (httpMethod) {
            case GET -> {
                return MethodUtils.executeWithRetry(() -> buildRequestSpec().request("GET", endpoint), maxRetries, maxBackoffMs);
            }
            case POST -> {
                return MethodUtils.executeWithRetry(() -> buildRequestSpec().request("POST", endpoint), maxRetries, maxBackoffMs);
            }
            default -> {
                logger.error("Please select get or post http method");
                return null;
            }
        }
    }

    /**
     * Builds the RestAssured request specification.
     * Sets base URI, path parameters, headers, cookies, body, etc.
     */
    private RequestSpecification buildRequestSpec() {
        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.log().all();

        if (isNullOrEmpty(baseUri)) throw new IllegalStateException("Base URI is not set.");
        reqSpec.baseUri(baseUri);
        if (!isNullOrEmpty(basePath)) reqSpec.basePath(basePath);
        if (!isNullOrEmpty(pathParams)) reqSpec.pathParams(pathParams);
        if (body != null) reqSpec.body(body);
        if (!isNullOrEmpty(headers)) reqSpec.headers(headers);
        if (!isNullOrEmpty(cookies)) reqSpec.cookies(cookies);
        if (!isNullOrEmpty(queryParams)) reqSpec.queryParams(queryParams);
        if (!isNullOrEmpty(formParams)) reqSpec.formParams(formParams);

        configureProxy(reqSpec);
        reqSpec.relaxedHTTPSValidation();

        return reqSpec;
    }

    /**
     * Configures proxy settings for the request if specified.
     */
    private void configureProxy(RequestSpecification reqSpec) {
        if (!isNullOrEmpty(proxyHost)) {
            ProxySpecification proxySpec = ProxySpecification.host(proxyHost).withPort(proxyPort).withScheme(proxySchema);
            if (!isNullOrEmpty(proxyUsername) && !isNullOrEmpty(proxyPassword)) {
                proxySpec.withAuth(proxyUsername, proxyPassword);
            }
            reqSpec.proxy(proxySpec);
        }
    }

    /**
     * Resets all request data to its default state.
     */
    public void resetRequest() {
        logger.debug("Resetting Request Data: Start.");
        RestAssured.reset();
        apiName = null;
        baseUri = null;
        basePath = null;
        endPoint = null;
        body = null;
        pathParams = new HashMap<>();
        headers = new HashMap<>();
        cookies = new HashMap<>();
        queryParams = new HashMap<>();
        formParams = new HashMap<>();
        proxyHost = null;
        proxyPort = null;
        proxySchema = null;
        proxyUsername = null;
        proxyPassword = null;
        response = null;
        logger.debug("Resetting Request Data: Complete.");
    }

    /**
     * Converts the current ApiDriver instance to a string representation for debugging.
     */
    @Override
    public String toString() {
        return "{\n" +
                "\t\"apiName\": " + (apiName == null ? "null" : "\"" + apiName + "\"") + ",\n" +
                "\t\"baseUri\": " + (baseUri == null ? "null" : "\"" + baseUri + "\"") + ",\n" +
                "\t\"basePath\": " + (basePath == null ? "null" : "\"" + basePath + "\"") + ",\n" +
                "\t\"endpoint\": " + (endPoint == null ? "null" : "\"" + endPoint + "\"") + ",\n" +
                "\t\"pathParams\": " + JsonUtils.toJson(pathParams) + ",\n" +
                "\t\"headers\": " + JsonUtils.toJson(headers) + ",\n" +
                "\t\"cookies\": " + JsonUtils.toJson(cookies) + ",\n" +
                "\t\"queryParams\": " + JsonUtils.toJson(queryParams) + ",\n" +
                "\t\"formParams\": " + JsonUtils.toJson(formParams) + ",\n" +
                "\t\"proxyHost\": " + (proxyHost == null ? "null" : "\"" + proxyHost + "\"") + ",\n" +
                "\t\"proxyPort\": " + (proxyPort == null ? "null" : "\"" + proxyPort + "\"") + ",\n" +
                "\t\"proxyScheme\": " + (proxySchema == null ? "null" : "\"" + proxySchema + "\"") + ",\n" +
                "\t\"proxyUsername\": " + (proxyUsername == null ? "null" : "\"" + proxyUsername + "\"") + ",\n" +
                "\t\"proxyPassword\": " + (proxyPassword == null ? "null" : "\"" + secret(proxyPassword) + "\"") + ",\n" +
                "\t\"body\": " + body + ",\n" +
                "\t\"response\": " + (response == null ? "null" : ("\"" + response.asPrettyString() + "\"")) + "\n" +
                "}";
    }

    /**
     * Utility method to check if a string is null or empty.
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Utility method to check if a map is null or empty.
     */
    private boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Utility method to update parameters and log the operation.
     */
    protected void updateAndLogParams(String paramType, Map<String, String> params, String mode) {
        logger.debug(String.format("Using %s mode to modify %s with %s", mode.toUpperCase(), paramType, params));
        logger.info("Current " + paramType + ": {}", params);
    }

}
