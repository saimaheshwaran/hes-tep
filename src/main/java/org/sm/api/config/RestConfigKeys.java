package org.sm.api.config;

/**
 * A utility class that contains constant keys used for configuring REST API requests.
 * <p>
 * This class provides standardized keys for various aspects of REST configurations,
 * such as base URIs, endpoints, headers, query parameters, and retry mechanisms.
 * <p>
 * This class is not meant to be instantiated, as it serves as a container for static constants.
 */
public final class RestConfigKeys {

    /**
     * Key for specifying the base URI of the REST API.
     */
    public static final String BASE_URI = "baseUri";

    /**
     * Key for specifying the endpoint of the REST API.
     */
    public static final String ENDPOINT = "endpoint";

    /**
     * Key for specifying the body of the request payload.
     */
    public static final String BODY = "body";

    /**
     * Key for specifying query parameters for the request.
     */
    public static final String QUERY_PARAMS = "queryParams";

    /**
     * Key for specifying headers to include in the request.
     */
    public static final String HEADERS = "headers";

    /**
     * Key for specifying cookies to include in the request.
     */
    public static final String COOKIES = "cookies";

    /**
     * Key for specifying path parameters for the request.
     */
    public static final String PATH_PARAMS = "pathParams";

    /**
     * Key for specifying the base path of the REST API.
     */
    public static final String BASE_PATH = "basePath";

    /**
     * Key for enabling retries on request errors.
     */
    private static final String RETRY_ON_ERROR = "retryOnError";

    /**
     * Key for specifying the maximum number of retry attempts on request errors.
     */
    public static final String RETRY_ON_ERROR_MAX_COUNT = RETRY_ON_ERROR + ".maxCount";

    /**
     * Key for specifying the maximum backoff duration (in milliseconds) for retries.
     */
    public static final String RETRY_ON_ERROR_MAX_BACKOFF_MS = RETRY_ON_ERROR + ".maxBackoffMs";

    /**
     * Key for specifying proxy configurations.
     */
    private static final String PROXY = "proxy";

    /**
     * Key for specifying the URL of the proxy server.
     */
    public static final String PROXY_URL = PROXY + ".url";

    /**
     * Key for specifying the username for proxy authentication.
     */
    public static final String PROXY_USERNAME = PROXY + ".username";

    /**
     * Key for specifying the password for proxy authentication.
     */
    public static final String PROXY_PASSWORD = PROXY + ".password";

    /**
     * Key for enabling or disabling the proxy.
     */
    private static final String PROXY_ENABLED = PROXY + ".enabled";

    /**
     * Key for enabling or disabling retries on request errors.
     */
    public static final String RETRY_ON_ERROR_ENABLED = RETRY_ON_ERROR + ".enabled";

    /**
     * Key for specifying form parameters for requests.
     */
    public static final String FORM_PARAMS = "formParams";

    /**
     * Private constructor to prevent instantiation.
     * This class is a static constants container.
     */
    private RestConfigKeys() {
        throw new UnsupportedOperationException("RestConfigKeys is a static constants container and non-instantiable.");
    }
}
