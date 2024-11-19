package org.sm.api;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.support.DriverHelpers;

import java.util.Map;
import java.util.Objects;

import static org.sm.api.support.DriverHelpers.executeRequestWithRetryMechanism;
import static org.sm.api.support.RestConfigHelpers.getRetryOnErrorEnabled;
import static org.sm.utilities.MapUtils.updateMap;

public class DriverApi extends DriverApiOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverApi.class);
    private static final ThreadLocal<DriverApi> threadLocalInstance = new ThreadLocal<>();

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

    //public static DriverApi getRest() { return getInstance(); }

    public static DriverApi getInstance() {
        if(threadLocalInstance.get() == null) {
            threadLocalInstance.set(new DriverApi());
        }
        return threadLocalInstance.get();
    }

    /**
     * Set host, scheme and port from the proxy url
     *
     * @param url host | host:port | scheme:host:port | scheme:host url
     */
    public void setProxyUrl(String url) {
        DriverHelpers.parseAndSetProxyUrl(url);
    }

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

    public void setApiDetailsFromYaml(String apiName) {
        DriverHelpers.setApiDetailsFromYaml(apiName);
    }

    public void setQueryParams(Map<String, String> queryParams, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify QUERY PARAMETERS with %s", mode.toUpperCase(), queryParams));
        setQueryParams(updateMap(mode, queryParams, getQueryParams()));
        LOGGER.info("Current QUERY PARAMETERS: {}", getQueryParams());
    }

    public void setFormParams(Map<String, String> formParams, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify FORM PARAMETERS with %s", mode.toUpperCase(), formParams));
        setFormParams(updateMap(mode, formParams, getFormParams()));
        LOGGER.info("Current FROM PARAMETERS: {}", getFormParams());
    }

    public void setCookies(Map<String, String> cookies, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify COOKIES with %s", mode.toUpperCase(), cookies));
        setCookies(updateMap(mode, cookies, getCookies()));
        LOGGER.info("Current COOKIES: {}", getCookies());
    }

    public void setHeaders(Map<String, String> headers, String mode) {
        LOGGER.debug(String.format("Using %s mode to modify HEADERS with %s", mode.toUpperCase(), headers));
        setHeaders(updateMap(mode, headers, getHeaders()));
        LOGGER.info("Current HEADERS: {}", getHeaders());
    }

    public void clear() {
        resetRequest();
        threadLocalInstance.remove();
    }
}
