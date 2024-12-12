package com.tep.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tep.api.config.ApiConfig;
import com.tep.api.config.ApiConstants;
import com.tep.api.config.ApiEnums;
import com.tep.api.config.ApiKeys;
import com.tep.api.response.ApiResponse;
import com.tep.api.response.ApiSchema;
import com.tep.utilities.Enums;
import com.tep.utilities.JsonUtils;
import com.tep.utilities.MethodUtils;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.tep.utilities.MapUtils.updateMap;
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
        if (proxyUrl == null)
            logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_URL);
        if (proxyUsername == null)
            logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_USERNAME);
        if (proxyPassword == null)
            logger.error("'{}' is enabled but '{}' was not found in the config.", ApiKeys.PROXY_ENABLED, ApiKeys.PROXY_PASSWORD);
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

    /**
     * Updates the query parameters of the API request.
     *
     * @param queryParams A map containing the query parameters to be updated.
     * @param mode        The update mode as defined in {@link Enums.Manipulation_Mode}.
     */
    public void setQueryParams(Map<String, String> queryParams, Enums.Manipulation_Mode mode) {
        updateAndLogParams("QUERY PARAMETERS", queryParams, String.valueOf(mode));
        setQueryParams(updateMap(mode, queryParams, getQueryParams()));
    }

    /**
     * Updates the form parameters of the API request.
     *
     * @param formParams A map containing the form parameters to be updated.
     * @param mode       The update mode as defined in {@link Enums.Manipulation_Mode}.
     */
    public void setFormParams(Map<String, String> formParams, Enums.Manipulation_Mode mode) {
        updateAndLogParams("FORM PARAMETERS", formParams, String.valueOf(mode));
        setFormParams(updateMap(mode, formParams, getFormParams()));
    }

    /**
     * Updates the cookies of the API request.
     *
     * @param cookies A map containing the cookies to be updated.
     * @param mode    The update mode as defined in {@link Enums.Manipulation_Mode}.
     */
    public void setCookies(Map<String, String> cookies, Enums.Manipulation_Mode mode) {
        updateAndLogParams("COOKIES", cookies, String.valueOf(mode));
        setCookies(updateMap(mode, cookies, getCookies()));
    }

    /**
     * Updates the headers of the API request.
     *
     * @param headers A map containing the headers to be updated.
     * @param mode    The update mode as defined in {@link Enums.Manipulation_Mode}.
     */
    public void setHeaders(Map<String, String> headers, Enums.Manipulation_Mode mode) {
        updateAndLogParams("HEADERS", headers, String.valueOf(mode));
        setHeaders(updateMap(mode, headers, getHeaders()));
    }

    /**
     * Updates the request body based on the specified mode of manipulation.
     * This method allows updating or deleting a value associated with a given key in a JSON body.
     *
     * @param jsonBody The JSON body as a String that needs to be manipulated.
     * @param key      The key in the JSON body whose value is to be updated or deleted.
     * @param newValue The new value to update in the JSON body. This parameter is ignored if mode is DELETE.
     * @param dataType The data type to which the new value should be converted. This parameter is ignored if mode is DELETE.
     * @param mode     The mode of manipulation, either UPDATE or DELETE, as defined in Enums.Manipulating_Mode..
     */
    public void updateRequestBody(String jsonBody, String key, String newValue, String dataType, Enums.Manipulation_Mode mode) {
        try {
            switch (mode) {
                case UPDATE:
                    Object updatedValue = convertValueBasedOnType(newValue, dataType);
                    this.body = updateJSONBody(jsonBody, key, updatedValue);
                    break;
                case DELETE:
                    this.body = deleteFieldFromJSONBody(jsonBody, key);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown mode: " + mode);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the JSON body with the new value for the given key.
     * This method supports updating both top-level and nested keys within the JSON object.
     * Nested keys are specified using dot notation (e.g., "parent.child").
     *
     * @param jsonBody     The original JSON body as a String.
     * @param key          The key whose value is to be updated. Can be a nested key represented in dot notation.
     * @param updatedValue The new value to be associated with the key.
     * @return The updated JSON body as a String.
     * @throws JSONException if there is an issue with JSON processing.
     */
    public String updateJSONBody(String jsonBody, String key, Object updatedValue) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonBody);
        if (key.contains(".")) {
            //nested json
            String[] newKeys = key.split("\\.");

            switch (newKeys.length) {
                case 2:
                    if (newKeys[1].contains("[")) {
                        jsonObj.put(newKeys[0], arrayUpdate(jsonObj.getJSONObject(newKeys[0]), newKeys[1], updatedValue));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).put(newKeys[1], updatedValue);
                    break;
                case 3:
                    if (newKeys[1].contains("[")) {
                        jsonObj.put(newKeys[0], arrayUpdate(jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]), newKeys[2], updatedValue));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).put(newKeys[2], updatedValue);
                    break;
                case 4:
                    if (newKeys[1].contains("[")) {
                        jsonObj.put(newKeys[0], arrayUpdate(jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).getJSONObject(newKeys[2]), newKeys[3], updatedValue));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).getJSONObject(newKeys[2]).put(newKeys[3], updatedValue);
                    break;
            }
        } else {
            if (key.contains("[")) {
                jsonObj = arrayUpdate(jsonObj, key, updatedValue);
            } else
                jsonObj.put(key, updatedValue);
        }
        return jsonObj.toString();
    }

    public String deleteFieldFromJSONBody(String jsonBody, String key) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonBody);
        if (key.contains(".")) {
            String[] newKeys = key.split("\\.");
            switch (newKeys.length) {
                case 2:
                    if (newKeys[1].contains("[")) {
                        jsonObj.put(newKeys[0], arrayDelete(jsonObj.getJSONObject(newKeys[0]), newKeys[1]));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).remove(newKeys[1]);
                    break;
                case 3:
                    if (newKeys[2].contains("[")) {
                        jsonObj.put(newKeys[0], arrayDelete(jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]), newKeys[2]));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).remove(newKeys[2]);
                    break;
                case 4:
                    if (newKeys[3].contains("[")) {
                        jsonObj.put(newKeys[0], arrayDelete(jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).getJSONObject(newKeys[2]), newKeys[3]));
                    } else
                        jsonObj.getJSONObject(newKeys[0]).getJSONObject(newKeys[1]).getJSONObject(newKeys[2]).remove(newKeys[3]);
                    break;
            }
        } else {
            if (key.contains("[")) {
                jsonObj = arrayDelete(jsonObj, key);
            } else
                jsonObj.remove(key);
        }
        return jsonObj.toString();
    }

    /**
     * Deletes an element at a specific index within a JSONArray that is part of a JSONObject.
     * * The method locates the JSONArray associated with the provided key and removes the element at the specified index.
     * * * @param jsonObj The JSONObject containing the JSONArray from which an element will be deleted.
     * * @param key     The key corresponding to the JSONArray in the JSONObject.
     * *                The key should contain the index of the element to delete in square brackets.
     * * @return        The updated JSONObject with the JSONArray element removed.
     * * @throws JSONException If the key is invalid or if an error occurs during the deletion process.
     */
    public JSONObject arrayDelete(JSONObject jsonObj, String key) throws JSONException {
        JSONObject nestObject = jsonObj;
        JSONArray cArray = nestObject.getJSONArray((key.split("\\["))[0]);
        int index = Integer.parseInt(key.substring(key.indexOf('[') + 1, key.indexOf(']')));
        cArray.remove(index);
        return jsonObj;
    }

    /**
     * Updates a specific index within a JSONArray that is part of a JSONObject.
     * * The method locates the JSONArray associated with the provided key and updates * the value at the specified index with the new value.
     * * * @param jsonObj   The JSONObject containing the JSONArray to be updated.
     * * @param key The key corresponding to the JSONArray in the JSONObject.
     * The key should contain the index of the element to update in square brackets.
     * * @param newValue  The new value to replace the existing value at the specified index in the JSONArray.
     * * @return The updated JSONObject with the modified JSONArray.
     * * @throws JSONException If the key is invalid or if an error occurs during the update process.
     */
    public JSONObject arrayUpdate(JSONObject jsonObj, String key, Object newValue) throws JSONException {
        JSONObject nestObject = jsonObj;
        JSONArray cArray = nestObject.getJSONArray((key.split("\\["))[0]);
        int index = Integer.parseInt(key.substring(key.indexOf('[') + 1, key.indexOf(']')));
        cArray.put(index, newValue);
        return jsonObj;
    }

    /**
     * Converts the given string value to the specified data type.
     * This method supports conversion to Integer, Boolean, and Double data types.
     * If the specified data type is not one of the supported types, the original string value is returned.
     * @param newValue The string value to be converted.
     * @param dataType The name of the data type to which the value should be converted. Supported types are "Integer", "Boolean", and "Double".
     * @return The converted value as an Object. If the data type is not supported, the original string value is returned.
     */
    private Object convertValueBasedOnType(String newValue, String dataType) throws JsonProcessingException {

        switch (dataType) {
            case "Integer":
                return Integer.parseInt(newValue);
            case "Boolean":
                return Boolean.parseBoolean(newValue);
            case "Double":
                return Double.parseDouble(newValue);
            default:
                return newValue;
        }
    }


}
