package org.sm.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
        // Private constructor to prevent instantiation
    }

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
