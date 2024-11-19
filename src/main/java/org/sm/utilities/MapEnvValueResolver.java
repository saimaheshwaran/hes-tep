package org.sm.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.sm.utilities.ExceptionUtils.logErrorAndThrowIfNull;

public class MapEnvValueResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapEnvValueResolver.class);
    private final Map<String, String> map;
    private final String envSep;

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

    public String getEnvSpecificValue(String key, String env) {
        logErrorAndThrowIfNull(LOGGER, "Lookup Key", key);
        // Check key directly first
        env = env.toLowerCase(Locale.ROOT);
        key = key.toLowerCase(Locale.ROOT);
        if (!map.containsKey(key)) {
            return map.get(env + envSep + key);
        }
        return map.get(key);
    }

    public String getEnvSpecificValue(String key) {
        return getEnvSpecificValue(key, "UNSPECIFIED");
    }
}
