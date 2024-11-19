package com.hes.api.config;

/**
 * The {@code RestConfigKeys} class holds constant keys used for REST configuration.
 * These keys are used to access various configuration properties such as base URI, endpoints, headers, etc.
 */
public final class RestConfigKeys {

    /**
     * Key for the base URI.
     */
    public static final String BASE_URI = "baseUri";

    /**
     * Key for the endpoint.
     */
    public static final String ENDPOINT = "endpoint";

    /**
     * Key for the request body.
     */
    public static final String BODY = "body";

    /**
     * Key for the query parameters.
     */
    public static final String QUERY_PARAMS = "queryParams";

    /**
     * Key for the headers.
     */
    public static final String HEADERS = "headers";

    /**
     * Key for the cookies.
     */
    public static final String COOKIES = "cookies";

    /**
     * Key for the path parameters.
     */
    public static final String PATH_PARAMS = "pathParams";

    /**
     * Key for the base path.
     */
    public static final String BASE_PATH = "basePath";

    /**
     * Base key for retry on error settings.
     */
    private static final String RETRY_ON_ERROR = "retryOnError";

    /**
     * Key for the maximum retry count on error.
     */
    public static final String RETRY_ON_ERROR_MAX_COUNT = RETRY_ON_ERROR + ".maxCount";

    /**
     * Key for the maximum backoff time in milliseconds for retries on error.
     */
    public static final String RETRY_ON_ERROR_MAX_BACKOFF_MS = RETRY_ON_ERROR + ".maxBackoffMs";

    /**
     * Base key for proxy settings.
     */
    private static final String PROXY = "proxy";

    /**
     * Key for the proxy URL.
     */
    public static final String PROXY_URL = PROXY + ".url";

    /**
     * Key for the proxy username.
     */
    public static final String PROXY_USERNAME = PROXY + ".username";

    /**
     * Key for the proxy password.
     */
    public static final String PROXY_PASSWORD = PROXY + ".password";

    /**
     * Key for enabling the proxy.
     */
    public static final String PROXY_ENABLED = PROXY + ".enabled";

    /**
     * Key for enabling retry on error.
     */
    public static final String RETRY_ON_ERROR_ENABLED = RETRY_ON_ERROR + ".enabled";

    /**
     * Key for the form parameters.
     */
    public static final String FORM_PARAMS = "formParams";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private RestConfigKeys() {
        throw new UnsupportedOperationException("RestConfigKeys is a static constants container and non-instantiable.");
    }
}
