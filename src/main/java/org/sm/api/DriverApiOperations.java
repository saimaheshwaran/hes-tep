package org.sm.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.sm.utilities.CommonUtils.isNullOrEmpty;
import static org.sm.utilities.StringUtils.secret;

public class DriverApiOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverApiOperations.class);
    private String API_NAME;
    private String BASE_URI;
    private String BASE_PATH;
    private String ENDPOINT;
    private Map<String, String> PATH_PARAMS;
    private Map<String, String> HEADERS;
    private Map<String, String> COOKIES;
    private Map<String, String> QUERY_PARAMS;
    private Object BODY;
    private String PROXY_HOST;
    private Integer PROXY_PORT;
    private String PROXY_SCHEME;
    private String PROXY_URL;
    private String PROXY_USERNAME;
    private String PROXY_PASSWORD;
    private Response RESPONSE;
    private Map<String, String> FORM_PARAMS;

    /**
     * @return Latest available {@code Response} object
     */
    public Response getResponse() {
        if (RESPONSE == null) {
            LOGGER.warn("Retrieved the response but it was null. Did you forget to execute the request?");
        }
        return RESPONSE;
    }

    /**
     * Manually set the response
     *
     * @param response response to set
     */
    public void setResponse(Response response) {
        RESPONSE = response;
    }

    public String getApiName() {
        return API_NAME;
    }

    /**
     * Manually set the api name for subsequent requests.
     *
     * @param apiName api name from config file
     */
    public void setApiName(String apiName) {
        API_NAME = apiName;
    }

    public String getBaseUri() {
        return BASE_URI;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The base URI is the root URL for the API or web service being tested.
     * All endpoint paths will be appended to this base URI when making requests.
     * For instance, if the base URI is set to "https&#58;//api.example.com", and an endpoint
     * "/users" is used, the resulting URL for the request would be "https&#58;//api.example.com/users".
     * </p>
     *
     * @param baseUri The base URI to be set.
     */
    public void setBaseUri(String baseUri) {
        BASE_URI = baseUri;
        LOGGER.info("BASE URI set to: " + BASE_URI);
    }

    public String getBasePath() {
        return BASE_PATH;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The base path represents a specific segment of the URL, often used
     * as a common prefix for multiple endpoints of the API.
     */
    public void setBasePath(String basePath) {
        BASE_PATH = basePath;
        LOGGER.info("BASE PATH set to: " + BASE_PATH);
    }

    public String getEndpoint() {
        return ENDPOINT;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The endpoint refers to the specific API path you are trying to access. It is usually appended
     * to the base URI to form the complete API URL.
     * <p>
     * For example, if the base URI is https&#58;//api.example.com" and the endpoint is "users",
     * the complete API URL would be "https&#58;//api.example.com/users".
     */
    public void setEndpoint(String endpoint) {
        ENDPOINT = endpoint;
    }

    public Map<String, String> getPathParams() {
        return PATH_PARAMS;
    }

    // Getters

    /**
     * {@inheritDoc}
     * <p>
     * This method clears the Map and then sets newly passed values.
     */
    public void setPathParams(Map<String, String> pathParams) {
        PATH_PARAMS.clear();
        PATH_PARAMS.putAll(pathParams);
    }

    public Map<String, String> getHeaders() {
        return HEADERS;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method clears the Map and then sets newly passed values.
     */
    public void setHeaders(Map<String, String> headers) {
        HEADERS.clear();
        HEADERS.putAll(headers);
    }

    public Map<String, String> getCookies() {
        return COOKIES;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method clears the Map and then sets newly passed values.
     */
    public void setCookies(Map<String, String> cookies) {
        COOKIES.clear();
        COOKIES.putAll(cookies);
    }

    public Map<String, String> getQueryParams() {
        return QUERY_PARAMS;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method clears the Map and then sets newly passed values.
     */
    public void setFormParams(Map<String, String> formParams) {
        FORM_PARAMS.clear();
        FORM_PARAMS.putAll(formParams);
    }

    public Map<String, String> getFormParams() {
        return FORM_PARAMS;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method clears the Map and then sets newly passed values.
     */
    public void setQueryParams(Map<String, String> queryParams) {
        QUERY_PARAMS.clear();
        QUERY_PARAMS.putAll(queryParams);
    }

    public Object getBody() {
        return BODY;
    }

    /**
     * Manually set body for the request.
     *
     * @param body contents for body.
     */
    public void setBody(Object body) {
        BODY = body;
    }

    public String getProxyHost() {
        return PROXY_HOST;
    }

    public Integer getProxyPort() {
        return PROXY_PORT;
    }

    public String getProxyUsername() {
        return PROXY_USERNAME;
    }

    public String getProxyPassword() {
        return PROXY_PASSWORD;
    }

    public String getProxyScheme() {
        return PROXY_SCHEME;
    }


    /**
     * Manually set the proxy host for subsequent requests.
     *
     * @param host proxy host string.
     */
    public void setProxyHost(String host) {
        PROXY_HOST = host;
    }

    /**
     * Manually set the proxy port for subsequent requests.
     *
     * @param port proxy port number.
     */
    public void setProxyPort(Integer port) {
        PROXY_PORT = port;
    }

    /**
     * Manually set the proxy username for subsequent requests.
     *
     * @param username proxy username string.
     */
    public void setProxyUsername(String username) {
        PROXY_USERNAME = username;
    }

    /**
     * Manually set the proxy password for subsequent requests.
     *
     * @param password proxy password string.
     */
    public void setProxyPassword(String password) {
        PROXY_PASSWORD = password;
    }

    /**
     * Manually set the proxy scheme for subsequent requests.
     *
     * @param scheme proxy scheme string (http|https).
     */
    public void setProxyScheme(String scheme) {
        PROXY_SCHEME = scheme;
    }

    /**
     * {@inheritDoc}
     *
     * @param httpMethod HTTP Method as String
     */
    public Response executeRequest(String httpMethod) {
        // Clear response
        RESPONSE = null;
        // Check if endpoint is set
        String endpoint = Objects.requireNonNullElse(ENDPOINT, "");
        try {
            RESPONSE = buildRequestSpec().request(httpMethod, endpoint);
        } catch (Exception e) {
            LOGGER.error("There was an error executing the request: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return RESPONSE;
    }

    /**
     * Constructs and returns a {@code RequestSpecification} object based on the current thread's stored data.
     * The request specification is constructed using variables stored in instance variables
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
    private RequestSpecification buildRequestSpec() {
        RequestSpecification reqSpec = RestAssured.given();

        if (!isNullOrEmpty(BASE_URI)) reqSpec.baseUri(BASE_URI);
        else throw new IllegalStateException("Base URI is not set.");

        if (!isNullOrEmpty(BASE_PATH)) reqSpec.basePath(BASE_PATH);
        if (!isNullOrEmpty(PATH_PARAMS)) reqSpec.pathParams(PATH_PARAMS);
        if (BODY != null) reqSpec.body(BODY);
        if (!isNullOrEmpty(HEADERS)) reqSpec.headers(HEADERS);
        if (!isNullOrEmpty(COOKIES)) reqSpec.cookies(COOKIES);
        if (!isNullOrEmpty(QUERY_PARAMS)) reqSpec.queryParams(QUERY_PARAMS);
        if (!isNullOrEmpty(FORM_PARAMS)) reqSpec.formParams(FORM_PARAMS);
        // Set proxy if available
        // Check host
        if (!isNullOrEmpty(PROXY_HOST)) {
            // Check port
            if (PROXY_PORT != null) {
                // Check credentials
                if (!isNullOrEmpty(PROXY_USERNAME) && !isNullOrEmpty(PROXY_PASSWORD)) {
                    // Host with port and credentials is available
                    reqSpec.proxy(ProxySpecification
                            .host(PROXY_HOST)
                            .withPort(PROXY_PORT)
                            .withAuth(PROXY_USERNAME, PROXY_PASSWORD)
                            .withScheme(PROXY_SCHEME));
                } else {
                    // Host with port is available
                    reqSpec.proxy(ProxySpecification
                            .host(PROXY_HOST)
                            .withPort(PROXY_PORT)
                            .withScheme(PROXY_SCHEME));
                }
            } else {
                // Check credentials
                if (!isNullOrEmpty(PROXY_USERNAME) && !isNullOrEmpty(PROXY_PASSWORD)) {
                    // Host with credentials is available
                    reqSpec.proxy(ProxySpecification
                            .host(PROXY_HOST)
                            .withAuth(PROXY_USERNAME, PROXY_PASSWORD)
                            .withScheme(PROXY_SCHEME));

                } else {
                    // Only host is available
                    reqSpec.proxy(ProxySpecification
                            .host(PROXY_HOST)
                            .withScheme(PROXY_SCHEME));
                }
            }
        }

        reqSpec.relaxedHTTPSValidation();   // Use Relaxed HTTPS Validation by default

        return reqSpec;
    }

    /**
     * {@inheritDoc}
     * This ensures that request data is cleared, preventing potential memory leaks
     * and ensuring a clean state for subsequent requests. This method should be
     * called after each request, especially in contexts like unit cucumber or
     * scenarios where the same thread might be reused for multiple requests.
     */
    public void resetRequest() {
        LOGGER.debug("Resetting Request Data: Start.");
        API_NAME = null;
        BASE_URI = null;
        BASE_PATH = null;
        ENDPOINT = null;
        BODY = null;
        PATH_PARAMS = new HashMap<>();
        HEADERS = new HashMap<>();
        COOKIES = new HashMap<>();
        QUERY_PARAMS = new HashMap<>();
        FORM_PARAMS = new HashMap<>();
        PROXY_HOST = null;
        PROXY_PORT = null;
        PROXY_SCHEME = null;
        PROXY_USERNAME = null;
        PROXY_PASSWORD = null;
        RESPONSE = null;
        LOGGER.debug("Resetting Request Data: Complete.");
    }

    /**
     * Returns a String representation of this class. The string representation is a valid JSON String,
     * where every instance variable name is surrounded by '"' (double quotes), followed by ":" (colon), and the value.
     * Each key-value pair (except for the last) is separated by ",\n" (comma and newline).
     * All instance variables are encapsulated in an object as '{instance_variables}'.
     * All HashMaps are represented as valid JSON objects. Empty HashMaps are represented as empty objects "{}" (opened and closed curly brackets).
     *
     * @return a String representation of this class.
     */
    public String toString() {
        // Language level 15+
//        return String.format("""
//                        {
//                            apiName: %s,
//                            baseUri: %s,
//                            basePath: %s,
//                            endpoint: %s,
//                            pathParams: %s,
//                            headers: %s,
//                            cookies: %s,
//                            queryParams: %s,
//                            body: %s,
//                            proxy: %s,
//                            response: %s
//                        }
//                        """,
//                (API_NAME == null ? "null" : "\"" + API_NAME + "\""),
//                (BASE_URI == null ? "null" : "\"" + BASE_URI + "\""),
//                (BASE_PATH == null ? "null" : "\"" + BASE_PATH + "\""),
//                (ENDPOINT == null ? "null" : "\"" + ENDPOINT + "\""),
//                JsonUtils.toJson(PATH_PARAMS),
//                JsonUtils.toJson(HEADERS),
//                JsonUtils.toJson(COOKIES),
//                JsonUtils.toJson(QUERY_PARAMS),
//                BODY,
//                (PROXY_URL == null ? "null" : "\"" + PROXY_URL + "\""),
//                (RESPONSE == null ? "null" : RESPONSE.asPrettyString())
//        );

        return "{\n" +
                "\t\"apiName\":" + (API_NAME == null ? "null" : "\"" + API_NAME + "\"") + ",\n" +
                "\t\"baseUri\":" + (BASE_URI == null ? "null" : "\"" + BASE_URI + "\"") + ",\n" +
                "\t\"basePath\":" + (BASE_PATH == null ? "null" : "\"" + BASE_PATH + "\"") + ",\n" +
                "\t\"endpoint\":" + (ENDPOINT == null ? "null" : "\"" + ENDPOINT + "\"") + ",\n" +
                "\t\"pathParams\":" + JsonUtils.toJson(PATH_PARAMS) + ",\n" +
                "\t\"headers\":" + JsonUtils.toJson(HEADERS) + ",\n" +
                "\t\"cookies\":" + JsonUtils.toJson(COOKIES) + ",\n" +
                "\t\"queryParams\":" + JsonUtils.toJson(QUERY_PARAMS) + ",\n" +
                "\t\"formParams\":" + JsonUtils.toJson(FORM_PARAMS) + ",\n" +
                "\t\"proxyHost\":" + (PROXY_HOST == null ? "null" : "\"" + PROXY_HOST + "\"") + ",\n" +
                "\t\"proxyPort\":" + (PROXY_PORT == null ? "null" : "\"" + PROXY_PORT + "\"") + ",\n" +
                "\t\"proxyScheme\":" + (PROXY_SCHEME == null ? "null" : "\"" + PROXY_SCHEME + "\"") + ",\n" +
                "\t\"proxyUsername\":" + (PROXY_USERNAME == null ? "null" : "\"" + PROXY_USERNAME + "\"") + ",\n" +
                "\t\"proxyPassword\":" + (PROXY_PASSWORD == null ? "null" : "\"" + secret(PROXY_PASSWORD) + "\"") + ",\n" +
                "\t\"body\":" + BODY + ",\n" +
                "\t\"response\":" + (RESPONSE == null ? "null" : ("\"" + RESPONSE.asPrettyString() + "\"")) + "\n" +
                "}";
    }
}