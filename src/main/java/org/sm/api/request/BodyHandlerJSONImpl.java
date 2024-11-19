package org.sm.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.utilities.JsonManipulator;

/**
 * BodyHandlerJSONImpl provides methods to manipulate and retrieve data from JSON strings
 * using JsonPath expressions and to convert objects to their JSON string representation.
 * <p>
 * It is advised to use {@link JsonManipulator} over
 * BodyHandlerJSONImpl if you know you are only working with JSON.
 *
 */
public final class BodyHandlerJSONImpl implements BodyHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BodyHandlerJSONImpl.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Converts a POJO to its JSON string representation.
     *
     * @param obj The POJO object.
     * @return The JSON string representation of the POJO.
     * @throws RuntimeException If conversion fails.
     */
    @Override
    public String toString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to convert object to JSON", e);
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Updates the JSON content based on the provided JSON path.
     *
     * @param jsonBody     The original JSON content.
     * @param jsonPathExpr The JSON path expression indicating the field(s) to update.
     * @param newValue     The new value to set.
     * @return The updated JSON content.
     * @throws IllegalArgumentException If input JSON content or JSON path expression is null or empty.
     */
    @Override
    public String update(String jsonBody, String jsonPathExpr, Object newValue) {
        validateInput(jsonBody, jsonPathExpr);
        LOGGER.debug("Original JSON body: {}", jsonBody);
        LOGGER.debug("JSON path expression: {}", jsonPathExpr);
        LOGGER.debug("New value: {}", newValue);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        jm.writeOrUpdate(jsonPathExpr, newValue);
        return jm.toString();
    }

    /**
     * Deletes a specified field or node from the provided JSON content based on the given JSON path expression.
     *
     * @param jsonBody     The original JSON content.
     * @param jsonPathExpr The JSON path expression indicating the field(s) or node(s) to delete.
     * @return The updated JSON content after deletion.
     * @throws IllegalArgumentException If input JSON content or JSON path expression is null or empty.
     */
    @Override
    public String delete(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        jm.delete(jsonPathExpr);
        return jm.toString();
    }

    // Default to return as String
    @Override
    public String read(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        return jm.read(jsonPathExpr).asText();
    }

    /**
     * Extracts a typed value from the provided JSON content based on the specified JSON path expression.
     *
     * @param <T>          The expected type of the value.
     * @param jsonBody     The JSON content from which to extract the value.
     * @param jsonPathExpr The JSON path expression.
     * @param type         The class type of the expected value.
     * @return The typed value corresponding to the provided JSON path.
     * @throws IllegalArgumentException If input JSON content or JSON path expression is null or empty.
     */
    @Override
    public <T> T read(String jsonBody, String jsonPathExpr, Class<T> type) {
        validateInput(jsonBody, jsonPathExpr);
        try {
            DocumentContext jsonContext = JsonPath.parse(jsonBody);
            return jsonContext.read(jsonPathExpr, type);
        } catch (Exception e) {
            LOGGER.error("Failed to read object from JSON", e);
            throw new RuntimeException("Failed to read object from JSON", e);
        }
    }

    /**
     * Validates the input JSON content and JSON path expression to ensure they're neither null nor empty.
     *
     * @param jsonBody     The JSON content to validate.
     * @param jsonPathExpr The JSON path expression to validate.
     * @throws IllegalArgumentException If input JSON content or JSON path expression is null or empty.
     */
    private void validateInput(String jsonBody, String jsonPathExpr) {
        if (jsonBody == null || jsonBody.isEmpty()) {
            LOGGER.error("Input JSON content should not be null or empty.");
            throw new IllegalArgumentException("Input JSON content should not be null or empty.");
        }
        if (jsonPathExpr == null || jsonPathExpr.isEmpty()) {
            LOGGER.error("Input JSON path expression should not be null or empty.");
            throw new IllegalArgumentException("Input JSON path expression should not be null or empty.");
        }
    }
}