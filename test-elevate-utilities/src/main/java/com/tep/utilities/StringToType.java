package com.tep.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The {@code StringToType} class provides utility methods to convert string representations of values to their respective data types.
 * This class cannot be instantiated.
 */
public class StringToType {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private StringToType() {
        // Prevent instantiation
    }

    /**
     * Parses the string value into a data type according to the suffix.
     *
     * @param typedValue The string value with a suffix indicating the data type (e.g., ":string", ":bool", ":int", ":float", ":long", ":null", ":json").
     * @return The value converted to the specified data type, or the original string if no suffix is provided.
     * @throws RuntimeException if the JSON parsing fails or if the value cannot be converted to the specified data type.
     */
    public static Object parseValue(String typedValue) {
        if (typedValue.endsWith(":string") || typedValue.endsWith(":str")) {
            return typedValue.substring(0, typedValue.lastIndexOf(":"));
        } else if (typedValue.endsWith(":bool") || typedValue.endsWith(":boolean")) {
            return Boolean.parseBoolean(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":int") || typedValue.endsWith(":integer")) {
            return Integer.parseInt(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":float") || typedValue.endsWith(":double") || typedValue.endsWith(":decimal")) {
            return Double.parseDouble(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":long")) {
            return Long.parseLong(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":null")) {
            return null;
        } else if (typedValue.endsWith(":json")) {
            String json = typedValue.substring(0, typedValue.lastIndexOf(":json"));
            try {
                return OBJECT_MAPPER.readTree(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error parsing JSON value: " + json, e);
            }
        } else {
            // Default to string if no type indicator is provided
            return typedValue;
        }
    }
}
