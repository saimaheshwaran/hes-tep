package com.tep.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code JsonUtils} class provides utility methods for JSON operations such as converting objects to JSON strings.
 * This class cannot be instantiated.
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private JsonUtils() {
        // Prevent instantiation
    }

    /**
     * Converts an object to its JSON string representation.
     *
     * @param object The object to be converted to JSON.
     * @return The JSON string representation of the object, or "null" if the object is null.
     *         If an error occurs during conversion, returns "\"serialization_error\"".
     */
    public static String toJson(Object object) {
        if (object == null) return "null";
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error during conversion of Object to JSON string", e);
            // Handle error, return a default value
            return "\"serialization_error\"";
        }
    }
}

