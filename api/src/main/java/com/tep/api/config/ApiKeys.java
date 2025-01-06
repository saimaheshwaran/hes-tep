package com.tep.api.config;

/**
 * A utility class containing constants representing configuration keys used in the API framework.
 *
 * <p>This class is designed as a static constants container and cannot be instantiated.
 * It provides a centralized location for all API-related keys to ensure consistency and maintainability.
 */
public final class ApiKeys {

    // Base configuration keys
    public static final String BASE_URI = "baseUri";                 // The base URI for API requests
    public static final String BASE_PATH = "basePath";               // The base path for API endpoints
    public static final String ENDPOINT = "endpoint";                // Specific endpoint for the API

    // Request configuration keys
    public static final String BODY = "body";                        // Request body
    public static final String QUERY_PARAMS = "queryParams";         // Query parameters
    public static final String HEADERS = "headers";                  // Request headers
    public static final String COOKIES = "cookies";                  // Cookies for the request
    public static final String PATH_PARAMS = "pathParams";           // Path parameters
    public static final String FORM_PARAMS = "formParams";           // Form parameters for requests

    // Retry configuration keys
    private static final String RETRY_ON_ERROR = "retryOnError";                                 // Retry-on-error configuration namespace
    public static final String RETRY_ON_ERROR_ENABLED = RETRY_ON_ERROR + ".enabled";             // Enable retries
    public static final String RETRY_ON_ERROR_MAX_COUNT = RETRY_ON_ERROR + ".maxCount";          // Maximum retry count
    public static final String RETRY_ON_ERROR_MAX_BACKOFF_MS = RETRY_ON_ERROR + ".maxBackoffMs"; // Maximum backoff time in ms

    // Proxy configuration keys
    private static final String PROXY = "proxy";                     // Proxy configuration namespace
    public static final String PROXY_URL = PROXY + ".url";           // Proxy URL
    public static final String PROXY_USERNAME = PROXY + ".username"; // Proxy username
    public static final String PROXY_PASSWORD = PROXY + ".password"; // Proxy password
    public static final String PROXY_ENABLED = PROXY + ".enabled";   // Enable proxy usage

    /**
     * Private constructor to prevent instantiation.
     *
     * <p>This class is intended to serve as a static constants container.
     * Instantiating this class will result in an {@link UnsupportedOperationException}.
     */
    private ApiKeys() {
        throw new UnsupportedOperationException("ApiKeys is a static constants container and cannot be instantiated.");
    }

}
