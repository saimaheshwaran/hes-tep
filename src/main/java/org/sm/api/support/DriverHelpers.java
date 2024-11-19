package org.sm.api.support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.DriverApi;
import org.sm.api.config.RestConfig;
import org.sm.api.config.RestConfigKeys;
import org.sm.utilities.ExceptionUtils;
import org.sm.utilities.MethodUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.sm.api.support.RestConfigHelpers.getMaxBackoffMs;
import static org.sm.api.support.RestConfigHelpers.getMaxRetries;
import static org.sm.utilities.CommonUtils.isNullOrEmpty;
import static org.sm.utilities.StringUtils.secret;

public class DriverHelpers {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHelpers.class);
    private final static RestConfig restConfig = RestConfig.getInstance();

    private DriverHelpers() {
    }

    /**
     * Sets the API details based on configuration retrieved from a YAML file.
     * This method retrieves configuration values for the specified API name from a YAML file
     * and sets them in the NGTPAPI instance, allowing subsequent HTTP requests to be made
     * with the configured API details.
     *
     * @param apiName The name of the API for which configuration details are retrieved.
     * @throws IllegalArgumentException If the provided 'apiName' is null or empty.
     */
    public static void setApiDetailsFromYaml(String apiName) {
        ExceptionUtils.logErrorAndThrowIfNull(LOGGER, "API Name", apiName);

        DriverApi driverApi = DriverApi.getInstance();
        LOGGER.info("Setting API details from yaml rest config file.");
        // Set Api Name in NGTPAPI
        LOGGER.debug("Setting API name to: {}", apiName);
        driverApi.setApiName(apiName);

        // Get data from config
        String configBaseUri = restConfig.get(apiName, RestConfigKeys.BASE_URI);
        LOGGER.debug("Base URI from config: {}", configBaseUri);
        String configBasePath = restConfig.get(apiName, RestConfigKeys.BASE_PATH);
        LOGGER.debug("Base Path from config: {}", configBasePath);
        String configEndpoint = restConfig.get(apiName, RestConfigKeys.ENDPOINT);
        LOGGER.debug("Endpoint from config: {}", configEndpoint);
        HashMap<String, String> configPathParams = restConfig.get(apiName, RestConfigKeys.PATH_PARAMS);
        LOGGER.debug("Path Parameters from config: {}", configPathParams);
        HashMap<String, String> configQueryParams = restConfig.get(apiName, RestConfigKeys.QUERY_PARAMS);
        LOGGER.debug("Query Parameters from config: {}", configQueryParams);
        HashMap<String, String> configCookies = restConfig.get(apiName, RestConfigKeys.COOKIES);
        LOGGER.debug("Cookies from config: {}", configCookies);
        HashMap<String, String> configHeaders = restConfig.get(apiName, RestConfigKeys.HEADERS);
        LOGGER.debug("Headers from config: {}", configHeaders);
        String configBody = restConfig.get(apiName, RestConfigKeys.BODY);
        LOGGER.debug("Body from config: {}", configBody);
        String configProxyUrl = RestConfigHelpers.getProxyUrlFromConfigIfEnabled(apiName);
        LOGGER.debug("Proxy URL from config: {}", configProxyUrl);
        String configProxyUsername = RestConfigHelpers.getProxyDataFromConfigIfEnabled(apiName, RestConfigKeys.PROXY_USERNAME);
        LOGGER.debug("Proxy username from config: {}", configProxyUsername);
        String configProxyPassword = RestConfigHelpers.getProxyDataFromConfigIfEnabled(apiName, RestConfigKeys.PROXY_PASSWORD);
        LOGGER.debug("Proxy password from config: {}", secret(configProxyPassword));
        HashMap<String, String> configFormParams = restConfig.get(apiName, RestConfigKeys.FORM_PARAMS);
        LOGGER.debug("Form Parameters from config: {}", configFormParams);


        // Set details for DriverAPI
        if (configBaseUri != null) driverApi.setBaseUri(configBaseUri);
        if (configBasePath != null) driverApi.setBasePath(configBasePath);
        if (configEndpoint != null) driverApi.setEndpoint(configEndpoint);
        if (configPathParams != null) driverApi.setPathParams(configPathParams);
        if (configQueryParams != null) driverApi.setQueryParams(configQueryParams);
        if (configCookies != null) driverApi.setCookies(configCookies);
        if (configHeaders != null) driverApi.setHeaders(configHeaders);
        if (configBody != null) driverApi.setBody(configBody);
        if (configProxyUrl != null) driverApi.setProxyUrl(configProxyUrl);
        if (configProxyUsername != null) driverApi.setProxyUsername(configProxyUsername);
        if (configProxyPassword != null) driverApi.setProxyPassword(configProxyPassword);
        if (configFormParams != null) driverApi.setFormParams(configFormParams);
    }

    /**
     * Executes an HTTP request with a retry mechanism, if configured.
     * If retry configuration is found for the current API, this method performs
     * the HTTP request with the specified HTTP method and endpoint, and retries
     * the request according to the configured retry settings in case of failures.
     *
     * @param httpMethod The HTTP method to be used for the request (e.g., GET, POST).
     * @param endpoint   The endpoint URL for the HTTP request.
     * @return The Response object resulting from the request, or null if no retries are performed.
     * @throws IllegalArgumentException If 'httpMethod' or 'endpoint' is null or empty.
     */
    public static Response executeRequestWithRetryMechanism(String httpMethod, String endpoint) {
        DriverApi driverApi = DriverApi.getInstance();
        LOGGER.info("Retry configuration found.");
        Integer maxRetries = getMaxRetries(driverApi.getApiName());
        Integer maxBackoffMs = getMaxBackoffMs(driverApi.getApiName());
        LOGGER.info(String.format(
                "Executing request with retry on error: Max Retries = %d; Max Backoff ms = %d;",
                maxRetries, maxBackoffMs
        ));
        return MethodUtils.executeWithRetry(() -> buildRequestSpec().request(httpMethod, endpoint), maxRetries, maxBackoffMs);
    }

    /**
     * Constructs and returns a {@code RequestSpecification} object based on the current thread's stored data.
     * The request specification is constructed using variables stored in thread-local NGTPAPI instance variables
     * such as base URI, path, headers, cookies, query parameters, proxy, etc.
     *
     * <p>If certain essential details like the base URI are missing, an exception is thrown, indicating
     * that the required data hasn't been provided. However, optional details like headers, cookies, proxy, etc.,
     * are added to the request specification only if they're present.</p>
     *
     * <p>This method is particularly useful for ensuring that API requests are built in a consistent manner
     * with the data previously set in the thread-scoped instance.</p>
     *
     * @return A complete {@code RequestSpecification} containing all the necessary request details.
     * @throws IllegalStateException If any essential request detail like base URI is missing.
     */
    public static RequestSpecification buildRequestSpec() {
        DriverApi driverApi = DriverApi.getInstance();
        RequestSpecification reqSpec = RestAssured.given();

        // Enable logging of all request details
        reqSpec.log().all();

        LOGGER.info("Building request specification with available API details.");

        if (!isNullOrEmpty(driverApi.getBaseUri())) {
            reqSpec.baseUri(driverApi.getBaseUri());
            LOGGER.debug(String.format("Added base URI to request specification: '%s'", driverApi.getBaseUri()));
        } else {
            LOGGER.error("Base URI is not set (value is null or empty). Setting base URI is required.");
            throw new IllegalStateException("Base URI is not set.");
        }

        if (!isNullOrEmpty(driverApi.getBasePath())) {
            reqSpec.basePath(driverApi.getBasePath());
            LOGGER.debug(String.format("Added base path to request specification: '%s'", driverApi.getBasePath()));
        }
        if (!isNullOrEmpty(driverApi.getPathParams())) {
            reqSpec.pathParams(driverApi.getPathParams());
            LOGGER.debug(String.format("Added path parameters to request specification: %s", driverApi.getPathParams()));
        }
        if (!isNullOrEmpty(driverApi.getHeaders())) {
            reqSpec.headers(driverApi.getHeaders());
            LOGGER.debug(String.format("Added headers to request specification: %s", driverApi.getHeaders()));
        }
        if (!isNullOrEmpty(driverApi.getCookies())) {
            reqSpec.cookies(driverApi.getCookies());
            LOGGER.debug(String.format("Added cookies to request specification: %s", driverApi.getCookies()));
        }
        if (!isNullOrEmpty(driverApi.getQueryParams())) {
            reqSpec.queryParams(driverApi.getQueryParams());
            LOGGER.debug(String.format("Added query parameters to request specification: %s", driverApi.getQueryParams()));
        }

        if (!isNullOrEmpty(driverApi.getFormParams())) {
            reqSpec.formParams(driverApi.getFormParams());
            LOGGER.debug(String.format("Added form parameters to request specification: %s", driverApi.getFormParams()));
        }
        if (driverApi.getBody() != null) {
            reqSpec.body(driverApi.getBody());
            LOGGER.debug(String.format("Added body to request specification: '%s'", driverApi.getBody()));
        }

        if (!isNullOrEmpty(driverApi.getProxyHost())) {
            if (driverApi.getProxyPort() != null) {
                // Check credentials
                if (!isNullOrEmpty(driverApi.getProxyUsername()) && !isNullOrEmpty(driverApi.getProxyPassword())) {
                    // Host with port and credentials is available
                    reqSpec.proxy(ProxySpecification
                            .host(driverApi.getProxyHost())
                            .withPort(driverApi.getProxyPort())
                            .withAuth(driverApi.getProxyUsername(), driverApi.getProxyPassword())
                            .withScheme(driverApi.getProxyScheme()));
                    LOGGER.debug("Host with port and credentials is available. Added proxy scheme '{}', host '{}', port {}, username '{}', password: '{}'",
                            driverApi.getProxyScheme(), driverApi.getProxyHost(), driverApi.getProxyPort(), driverApi.getProxyUsername(), secret(driverApi.getProxyPassword()));
                } else {
                    // Host with port is available
                    reqSpec.proxy(ProxySpecification
                            .host(driverApi.getProxyHost())
                            .withPort(driverApi.getProxyPort())
                            .withScheme(driverApi.getProxyScheme()));
                    LOGGER.debug("Host with port is available. Added proxy scheme '{}', host '{}' and port {} to request specification.", driverApi.getProxyScheme(), driverApi.getProxyHost(), driverApi.getProxyPort());
                }
            } else {
                // Check credentials
                if (!isNullOrEmpty(driverApi.getProxyUsername()) && !isNullOrEmpty(driverApi.getProxyPassword())) {
                    // Host with credentials is available
                    reqSpec.proxy(ProxySpecification
                            .host(driverApi.getProxyHost())
                            .withAuth(driverApi.getProxyUsername(), driverApi.getProxyPassword())
                            .withScheme(driverApi.getProxyScheme()));
                    LOGGER.debug("Host with credentials is available. Added proxy scheme '{}', host '{}', username '{}', password: '{}'",
                            driverApi.getProxyScheme(), driverApi.getProxyHost(), driverApi.getProxyUsername(), secret(driverApi.getProxyPassword()));

                } else {
                    // Only host is available
                    reqSpec.proxy(ProxySpecification
                            .host(driverApi.getProxyHost())
                            .withScheme(driverApi.getProxyScheme()));
                    LOGGER.debug("Only host is available. Added proxy scheme '{}' and host '{}' to request specification.", driverApi.getProxyScheme(), driverApi.getProxyHost());
                }
            }
        }

        LOGGER.debug("Using relaxed HTTPS validation by default.");
        reqSpec.relaxedHTTPSValidation();   // Use Relaxed HTTPS Validation by default

        return reqSpec;
    }

    /**
     * Parses the specified URL and sets the proxy host, scheme, and port in the NGTPAPI instance.
     * This method extracts the host and port from a given URL and applies them to configure
     * proxy settings in the NGTPAPI. If the port is not specified in the URL, it logs
     * the information and only sets the host. If the URL is not correctly formatted or
     * does not contain a valid host, this method will log an error and throw an IllegalArgumentException.
     *
     * @param url the URL string to parse and set as proxy settings. The URL must be a valid,
     *            well-formed URL.
     * @throws IllegalArgumentException if the URL is malformed, does not contain a valid host,
     *                                  or any other issue occurs indicating an invalid proxy URL setup.
     */
    public static void parseAndSetProxyUrl(String url) {
        DriverApi driverApi = DriverApi.getInstance();

        try {
            URL parsedUrl = new URL(url);
            String protocol = parsedUrl.getProtocol();
            String host = parsedUrl.getHost();
            int port = parsedUrl.getPort(); // Returns -1 if no port is explicitly specified

            // Set Host
            if (isNullOrEmpty(host)) {
                throw new IllegalArgumentException("Proxy URL does not contain a valid host. URL: " + url);
            }
            driverApi.setProxyHost(host);

            // Set Scheme/Protocol
            if (isNullOrEmpty(protocol)) {
                LOGGER.info("No protocol specified for proxy URL '{}'. Using default - http.", url);
                protocol = "http";
            }
            driverApi.setProxyScheme(protocol);

            // Set Port
            if (port != -1) { // Check if a port is specified
                driverApi.setProxyPort(port);
            } else {
                LOGGER.info("No port specified for proxy URL: '{}'", url);
            }

        } catch (MalformedURLException e) {
            String message = String.format("Proxy URL is not correctly formatted: '%s'. Error: %s", url, e.getMessage());
            LOGGER.error(message);
            throw new IllegalArgumentException(message, e);
        }
    }

}
