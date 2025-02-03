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
     * Parses a string value with a type indicator and returns the corresponding typed object.
     * The type indicator should be appended to the value with a colon separator. Supported type indicators are:
     * ":string" or ":str" for String,
     * ":bool" or ":boolean" for Boolean,
     * ":int" or ":integer" for Integer,
     * ":float", ":double", or ":decimal" for Double,
     * ":long" for Long,
     * ":null" for null,
     * ":json" for a parsed JSON tree using OBJECT_MAPPER.
     * If no type indicator is provided, the value is returned as a String.
     *
     * @param typedValue The string value with a type indicator to parse.
     * @return The parsed object of the specified type, or the original string if no type indicator is provided.
     * @throws RuntimeException If the ":json" type indicator is provided and JSON parsing fails.
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
