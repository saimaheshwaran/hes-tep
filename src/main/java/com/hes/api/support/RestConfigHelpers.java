package com.hes.api.support;

import com.hes.api.config.RestConfig;
import com.hes.api.config.RestConfigKeys;
import com.hes.api.config.RestConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * The {@code RestConfigHelpers} class provides utility methods for accessing and managing REST configuration settings.
 * It includes methods for retrieving proxy settings, retry configurations, and other configuration data.
 */
public class RestConfigHelpers {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfigHelpers.class);
    private static final RestConfig restConfig = RestConfig.getInstance();

    /**
     * Retrieves the proxy URL from the configuration if proxy is enabled for the specified API.
     *
     * @param apiName The name of the API.
     * @return The proxy URL if proxy is enabled, otherwise {@code null}.
     */
    public static String getProxyUrlFromConfigIfEnabled(String apiName) {
        Boolean proxyEnabled = Objects.requireNonNullElse(restConfig.get(apiName, RestConfigKeys.PROXY_ENABLED), Boolean.FALSE);
        if (proxyEnabled) {
            String proxyUrl = restConfig.get(apiName, RestConfigKeys.PROXY_URL);
            if (proxyUrl == null) {
                LOGGER.error(String.format("'%s' is true but '%s' was not found in the config.", RestConfigKeys.PROXY_ENABLED, RestConfigKeys.PROXY_URL));
            }
            return proxyUrl;
        }
        return null;
    }

    /**
     * Retrieves proxy data from the configuration if proxy is enabled for the specified API.
     *
     * @param apiName The name of the API.
     * @param key     The key for the proxy data to be retrieved.
     * @return The proxy data if proxy is enabled, otherwise {@code null}.
     */
    public static String getProxyDataFromConfigIfEnabled(String apiName, String key) {
        Boolean proxyEnabled = Objects.requireNonNullElse(restConfig.get(apiName, RestConfigKeys.PROXY_ENABLED), Boolean.FALSE);
        if (proxyEnabled) {
            String data = restConfig.get(apiName, key);
            if (data == null) {
                LOGGER.info(String.format("'%s' is true but '%s' was not found in the config.", RestConfigKeys.PROXY_ENABLED, key));
            }
            return data;
        }
        return null;
    }

    /**
     * Retrieves the retry-on-error enabled status from the configuration for the specified API.
     *
     * @param apiName The name of the API.
     * @return {@code true} if retry-on-error is enabled, otherwise {@code false}.
     */
    public static Boolean getRetryOnErrorEnabled(String apiName) {
        return Objects.requireNonNullElse(RestConfig.getInstance().get(apiName, RestConfigKeys.RETRY_ON_ERROR_ENABLED), Boolean.FALSE);
    }

    /**
     * Retrieves the maximum number of retries from the configuration for the specified API.
     * If not configured, returns the default maximum retries.
     *
     * @param apiName The name of the API.
     * @return The maximum number of retries.
     */
    public static Integer getMaxRetries(String apiName) {
        return Objects.requireNonNullElse(
                restConfig.get(apiName, RestConfigKeys.RETRY_ON_ERROR_MAX_COUNT),
                RestConfigConstants.DEFAULT_MAX_RETRIES
        );
    }

    /**
     * Retrieves the maximum backoff time in milliseconds from the configuration for the specified API.
     * If not configured, returns the default maximum backoff time.
     *
     * @param apiName The name of the API.
     * @return The maximum backoff time in milliseconds.
     */
    public static Integer getMaxBackoffMs(String apiName) {
        return Objects.requireNonNullElse(
                restConfig.get(apiName, RestConfigKeys.RETRY_ON_ERROR_MAX_BACKOFF_MS),
                RestConfigConstants.DEFAULT_MAX_BACKOFF_MS
        );
    }
}
