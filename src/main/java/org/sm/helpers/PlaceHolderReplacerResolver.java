package org.sm.helpers;

import org.sm.api.config.RestConfigConstants;
import org.sm.utilities.MapEnvValueResolver;
import org.sm.utilities.PlaceHolderReplacer;

import java.util.HashMap;
import java.util.Map;

public class PlaceHolderReplacerResolver extends PlaceHolderReplacer {

    private final MapEnvValueResolver mapEnvValueResolver;
    private final String env;

    /**
     * Initializes a new instance of the PlaceholderReplacerResolver class.
     *
     * @param inputMap The map containing variables and their values.
     */
    public PlaceHolderReplacerResolver(Map<String, String> inputMap, String env, String envSep) {
        super(injectInputMap(inputMap, env));
        this.env = env;
        mapEnvValueResolver = new MapEnvValueResolver(injectInputMap(inputMap, env), envSep);
    }

    public PlaceHolderReplacerResolver(Map<String, String> inputMap, String envSep) {
        this(inputMap, RestConfigConstants.ENVIRONMENT, envSep);
    }


    /**
     * Prepares the input map by adding a certain key-value pair before it's used in the superclass constructor.
     *
     * @param inputMap The original map containing variables and their values.
     * @return A modified map with the additional key-value pair.
     */
    private static Map<String, String> injectInputMap(Map<String, String> inputMap, String env) {
        Map<String, String> map = new HashMap<>(inputMap);
        Map<String, String> defaultMap;
        defaultMap = Map.of(
                "env", env,
                "environment", env
        );
        map.putAll(defaultMap);
        return map;
    }

    /**
     * Fetches the replacement value for a given variable from the input map.
     * This method overrides the superclass method to change the replacement behavior.
     *
     * @param variable The variable for which to fetch the replacement value.
     * @return The corresponding value from the map as a string. If the key is not found,
     * returns a custom message or performs a custom action.
     */
    @Override
    protected String getReplacement(String variable) {
        variable = variable.toLowerCase();
        String replacement = mapEnvValueResolver.getEnvSpecificValue(variable, env);
        if (replacement == null) {
            if (replaceUnknown) {
                return "VALUE_NOT_SET";
            } else {
                return "\\$\\{" + variable + "\\}";
            }
        }
        return replacement;
    }

}
