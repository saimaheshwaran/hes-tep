package com.hes.utilities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Provides functionality to substitute placeholders in the format `${key}`
 * within a string with their corresponding values from a passed map.
 *
 */
public class PlaceHolderReplacer {

    /**
     * A map containing the variables and their respective values.
     */
    private final Map<String, String> inputMap;
    /**
     * A precompiled pattern to detect placeholders in the format `${key}`.
     */
    private final Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    /**
     * A config flag to set behavior on whether to replace unknown placeholders
     * with "VALUE_NOT_SET" value
     */
    public Boolean replaceUnknown = false;

    /**
     * Initializes a new instance of the PlaceholderReplacer class.
     *
     * @param inputMap The map containing variables and their values.
     *                 Keys are stored in lowercase to ensure case-insensitivity during replacements.
     */
    public PlaceHolderReplacer(Map<String, String> inputMap) {
        this.inputMap = inputMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toLowerCase(),  // Convert key to lowercase
                        Map.Entry::getValue                     // Preserve the original value
                ));
    }

    /**
     * Replaces all occurrences of `${key}` in the provided input string with their corresponding values.
     *
     * @param input The input string containing placeholders.
     * @return The input string with all placeholders replaced. If a key is not found in the map,
     * the placeholder remains.
     */
    public String replace(String input) {
        if (input == null || !input.contains("${")) {
            return input;
        }
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String variable = matcher.group(1);
            String replacement = getReplacement(variable);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Fetches the replacement value for a given variable from the input map.
     *
     * @param variable The variable for which to fetch the replacement value.
     * @return The corresponding value from the map as a string. If the key is not found,
     * returns "VALUE_NOT_SET".
     */
    protected String getReplacement(String variable) {
        variable = variable.toLowerCase();
        String replacement = inputMap.get(variable);
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
