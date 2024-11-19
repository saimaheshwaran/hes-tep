package org.sm.api.support;

import org.sm.api.config.RestConfig;
import org.sm.api.config.RestConfigKeys;
import org.sm.api.config.RestConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RestConfigHelpers {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfigHelpers.class);
    private final static RestConfig restConfig = RestConfig.getInstance();

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


    public static Boolean getRetryOnErrorEnabled(String apiName) {
        return Objects.requireNonNullElse(RestConfig.getInstance().get(apiName, RestConfigKeys.RETRY_ON_ERROR_ENABLED), Boolean.FALSE);
    }

    public static Integer getMaxRetries(String apiName) {
        return Objects.requireNonNullElse(
                restConfig.get(apiName, RestConfigKeys.RETRY_ON_ERROR_MAX_COUNT),
                RestConfigConstants.DEFAULT_MAX_RETRIES
        );
    }

    public static Integer getMaxBackoffMs(String apiName) {
        return Objects.requireNonNullElse(
                restConfig.get(apiName, RestConfigKeys.RETRY_ON_ERROR_MAX_BACKOFF_MS),
                RestConfigConstants.DEFAULT_MAX_BACKOFF_MS
        );
    }
}
