package com.hes.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hes.utilities.ExceptionUtils.logErrorAndThrowIfNull;

/**
 * The {@code MapEnvValueResolver} class provides methods to resolve environment-specific values from a map.
 * It supports looking up values based on a key and an environment, with case-insensitive keys.
 */
public class MapEnvValueResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapEnvValueResolver.class);
    private final Map<String, String> map;
    private final String envSep;

    /**
     * Constructs a {@code MapEnvValueResolver} with the given input map and environment separator.
     *
     * @param inputMap      The input map containing key-value pairs.
     * @param envSeparator  The separator used to concatenate environment and key.
     * @throws IllegalArgumentException if the input map or environment separator is null.
     */
    public MapEnvValueResolver(Map<String, String> inputMap, String envSeparator) {
        logErrorAndThrowIfNull(LOGGER, "Input Map", inputMap);
        logErrorAndThrowIfNull(LOGGER, "Environment Separator", envSeparator);
        this.map = inputMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toLowerCase(),  // Convert key to lowercase
                        Map.Entry::getValue                     // Preserve the original value
                ));
        this.envSep = envSeparator;
    }

    /**
     * Retrieves the environment-specific value for the given key and environment.
     * If the key is not found directly, it attempts to find the value using the environment and separator.
     *
     * @param key The key to look up.
     * @param env The environment to use for the lookup.
     * @return The environment-specific value, or {@code null} if not found.
     * @throws IllegalArgumentException if the key is null.
     */
    public String getEnvSpecificValue(String key, String env) {
        logErrorAndThrowIfNull(LOGGER, "Lookup Key", key);
        env = env.toLowerCase(Locale.ROOT);
        key = key.toLowerCase(Locale.ROOT);
        if (!map.containsKey(key)) {
            return map.get(env + envSep + key);
        }
        return map.get(key);
    }

    /**
     * Retrieves the environment-specific value for the given key using the default environment "UNSPECIFIED".
     *
     * @param key The key to look up.
     * @return The environment-specific value, or {@code null} if not found.
     * @throws IllegalArgumentException if the key is null.
     */
    public String getEnvSpecificValue(String key) {
        return getEnvSpecificValue(key, "UNSPECIFIED");
    }
}
