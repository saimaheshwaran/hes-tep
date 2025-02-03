package com.tep.utilities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A utility class to replace placeholders in a given string with their corresponding values
 * from a provided map. Placeholders are defined in the format `${placeholder}`.
 */
public class PlaceHolderReplacer {

    // Map storing input key-value pairs, with keys converted to lowercase for case-insensitive matching
    private final Map<String, String> inputMap;

    // Regex pattern to match placeholders in the format ${placeholder}
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{([^}]+)}");

    // Flag indicating whether to replace unknown placeholders with a default value
    private boolean replaceUnknown = false;

    /**
     * Constructor to initialize the placeholder replacer with a map of values.
     * Keys are converted to lowercase to enable case-insensitive matching.
     *
     * @param inputMap the map containing key-value pairs for placeholder replacement
     */
    public PlaceHolderReplacer(Map<String, String> inputMap) {
        this.inputMap = inputMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toLowerCase(), // Convert keys to lowercase
                        Map.Entry::getValue                    // Preserve original values
                ));
    }

    /**
     * Replaces placeholders in the input string with their corresponding values from the map.
     *
     * @param input the string containing placeholders in the format `${placeholder}`
     * @return the input string with placeholders replaced, or the original string if no placeholders are found
     */
    public String replace(String input) {
        // Return the input as is if null or no placeholders are present
        if (input == null || !input.contains("${")) {
            return input;
        }

        Matcher matcher = PLACEHOLDER_PATTERN.matcher(input);
        StringBuilder result = new StringBuilder();

        // Iterate through all matches and replace placeholders
        while (matcher.find()) {
            String variable = matcher.group(1); // Extract placeholder name without `${}` brackets
            String replacement = getReplacement(variable);
            matcher.appendReplacement(result, replacement);
        }

        // Append the remaining part of the input string
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Retrieves the replacement value for a given placeholder.
     *
     * @param variable The variable name for which the replacement value is to be retrieved.
     *  *                 The variable name matching is case-insensitive.
     *  * @return The replacement value associated with the variable if it exists in the map,
     *  *         "VALUE_NOT_SET" if the variable is not found and 'replaceUnknown' is true,
     *  *         or the original placeholder in escaped form if the variable is not found and 'replaceUnknown' is false.
     *  */
    protected String getReplacement(String variable) {
        // Convert the variable name to lowercase for case-insensitive matching
        variable = variable.toLowerCase();

        // Retrieve the replacement value from the map
        String replacement = inputMap.get(variable);

        if (replacement == null) {
            // Handle unknown placeholders
            if (replaceUnknown) {
                return "VALUE_NOT_SET"; // Default value for unknown placeholders
            } else {
                // Preserve the placeholder in its original format if unknown
                return "\\$\\{" + variable + "\\}";
            }
        }

        return replacement;
    }

    /**
     * Sets the behavior for handling unknown placeholders.
     *
     * @param replaceUnknown if true, unknown placeholders are replaced with "VALUE_NOT_SET";
     *                       otherwise, they remain unchanged in the output
     */
    public void setReplaceUnknown(boolean replaceUnknown) {
        this.replaceUnknown = replaceUnknown;
    }
}
