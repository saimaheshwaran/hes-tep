package com.hes.api;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hes.api.support.DriverHelpers;

import java.util.Map;
import java.util.Objects;

import static com.hes.api.support.DriverHelpers.executeRequestWithRetryMechanism;
import static com.hes.api.support.RestConfigHelpers.getRetryOnErrorEnabled;
import static com.hes.utilities.MapUtils.updateMap;

/**
 * The {@code DriverApi} class provides methods for configuring and executing REST API requests.
 * It supports setting various request parameters, executing requests with retry mechanisms, and handling responses.
 */
public class DriverApi extends DriverApiOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverApi.class);
    private static final ThreadLocal<DriverApi> threadLocalInstance = new ThreadLocal<>();

    /**
     * Private constructor to initialize the {@code DriverApi} instance.
     * Configures RestAssured with default settings.
     */
    private DriverApi() {
        resetRequest();
        RestAssured.config = RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig()
                        .enablePrettyPrinting(true)
                        .enableLoggingOfRequestAndResponseIfValidationFails())
                .and()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000));
    }

    /**
     * Retrieves the singleton instance of {@code DriverApi} for the current thread.
     *
     * @return the shared {@code DriverApi} instance.
     */
    public static DriverApi getInstance() {
        if (threadLocalInstance.get() == null) {
            threadLocalInstance.set(new DriverApi());
        }
        return threadLocalInstance.get();
    }

    /**
     * Sets the proxy URL for the API requests.
     *
     * @param url The proxy URL to be set.
     */
    public void setProxyUrl(String url) {
        DriverHelpers.parseAndSetProxyUrl(url);
    }

    /**
     * Executes an API request with the specified HTTP method.
     * If the retry mechanism is enabled, it will retry the request upon failure.
     *
     * @param httpMethod The HTTP method to be used for the request (e.g., "GET", "POST").
     * @return The {@code Response} object containing the API response.
     * @throws RuntimeException if there is an error executing the request.
     */
    public Response executeRequest(String httpMethod) {
        // Clear response
        setResponse(null);
        // Check if endpoint is set
        String endpoint = Objects.requireNonNullElse(getEndpoint(), "");
        // Check if retry mechanism is enabled
        Boolean retryOnErrorEnabled = getRetryOnErrorEnabled(getApiName());

        if (retryOnErrorEnabled) {
            setResponse(executeRequestWithRetryMechanism(httpMethod, endpoint));
        } else {
            try {
                setResponse(DriverHelpers.buildRequestSpec().request(httpMethod, endpoint));
            } catch (Exception e) {
                LOGGER.error("There was an error executing the request: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return getResponse();
    }

    /**
     * Sets the API details from a YAML configuration file.
     *
     * @param apiName The name of the API to be configured.
     */
    public void setApiDetailsFromYaml(String apiName) {
        DriverHelpers.setApiDetailsFromYaml(apiName);
    }

    /**
     * Sets the query parameters for the API request.
     *
     * @param queryParams The query parameters to be set.
     * @param mode        The mode to modify the query parameters ("add", "replace", "remove").
     */
    public void setQueryParams(Map<String, String> queryParams, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify QUERY PARAMETERS with %s", mode.toUpperCase(), queryParams));
        setQueryParams(updateMap(mode, queryParams, getQueryParams()));
        LOGGER.info("Current QUERY PARAMETERS: {}", getQueryParams());
    }

    /**
     * Sets the form parameters for the API request.
     *
     * @param formParams The form parameters to be set.
     * @param mode       The mode to modify the form parameters ("add", "replace", "remove").
     */
    public void setFormParams(Map<String, String> formParams, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify FORM PARAMETERS with %s", mode.toUpperCase(), formParams));
        setFormParams(updateMap(mode, formParams, getFormParams()));
        LOGGER.info("Current FORM PARAMETERS: {}", getFormParams());
    }

    /**
     * Sets the cookies for the API request.
     *
     * @param cookies The cookies to be set.
     * @param mode    The mode to modify the cookies ("add", "replace", "remove").
     */
    public void setCookies(Map<String, String> cookies, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify COOKIES with %s", mode.toUpperCase(), cookies));
        setCookies(updateMap(mode, cookies, getCookies()));
        LOGGER.info("Current COOKIES: {}", getCookies());
    }

    /**
     * Sets the headers for the API request.
     *
     * @param headers The headers to be set.
     * @param mode    The mode to modify the headers ("add", "replace", "remove").
     */
    public void setHeaders(Map<String, String> headers, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify HEADERS with %s", mode.toUpperCase(), headers));
        setHeaders(updateMap(mode, headers, getHeaders()));
        LOGGER.info("Current HEADERS: {}", getHeaders());
    }

    /**
     * Clears the current API request configuration and removes the {@code DriverApi} instance from the current thread.
     */
    public void clear() {
        resetRequest();
        threadLocalInstance.remove();
    }
}
