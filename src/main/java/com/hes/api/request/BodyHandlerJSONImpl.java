package com.hes.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hes.utilities.JsonManipulator;

/**
 * The {@code BodyHandlerJSONImpl} class implements the {@code BodyHandler} interface for handling JSON content.
 * It provides methods to convert objects to JSON strings, read JSON content, update JSON content, and delete nodes in JSON content.
 */
public final class BodyHandlerJSONImpl implements BodyHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BodyHandlerJSONImpl.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Converts a given object to its JSON string representation.
     *
     * @param obj The object to be converted.
     * @return The JSON string representation of the object.
     * @throws RuntimeException if the object cannot be converted to JSON.
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
     * Updates the JSON content based on the provided JSON path expression with the given value.
     *
     * @param jsonBody     The source JSON content.
     * @param jsonPathExpr The JSON path expression indicating where to update.
     * @param newValue     The new value to be set.
     * @return The updated JSON content.
     * @throws IllegalArgumentException if the input JSON content or JSON path expression is null or empty.
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
     * Deletes a specific field or node from the JSON content based on the given JSON path expression.
     *
     * @param jsonBody     The source JSON content.
     * @param jsonPathExpr The JSON path expression indicating what to delete.
     * @return The JSON content after deletion.
     * @throws IllegalArgumentException if the input JSON content or JSON path expression is null or empty.
     */
    @Override
    public String delete(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        jm.delete(jsonPathExpr);
        return jm.toString();
    }

    /**
     * Reads content from the JSON based on the provided JSON path expression and returns the string representation.
     *
     * @param jsonBody     The source JSON content.
     * @param jsonPathExpr The JSON path expression to extract data.
     * @return The string representation of the extracted content.
     * @throws IllegalArgumentException if the input JSON content or JSON path expression is null or empty.
     */
    @Override
    public String read(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        return jm.read(jsonPathExpr).asText();
    }

    /**
     * Reads content from the JSON based on the provided JSON path expression and returns a typed value.
     *
     * @param <T>          The expected type of the value.
     * @param jsonBody     The source JSON content.
     * @param jsonPathExpr The JSON path expression to extract data.
     * @param type         The class type of the expected value.
     * @return The typed value corresponding to the provided JSON path.
     * @throws RuntimeException if the object cannot be read from JSON.
     * @throws IllegalArgumentException if the input JSON content or JSON path expression is null or empty.
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
     * Validates the input JSON content and JSON path expression.
     *
     * @param jsonBody     The JSON content to be validated.
     * @param jsonPathExpr The JSON path expression to be validated.
     * @throws IllegalArgumentException if the input JSON content or JSON path expression is null or empty.
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
