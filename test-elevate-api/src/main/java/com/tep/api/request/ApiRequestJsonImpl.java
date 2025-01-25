package com.tep.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tep.utilities.JsonManipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link ApiRequest} for handling JSON content.
 * <p>
 * Provides functionalities for reading, updating, and deleting JSON data using JSONPath expressions.
 */
public class ApiRequestJsonImpl implements ApiRequest {

    private static final Logger logger = LoggerFactory.getLogger(ApiRequestJsonImpl.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts an object to a JSON string representation.
     *
     * @param obj the object to be converted
     * @return the JSON string representation of the object
     * @throws RuntimeException if the conversion fails
     */
    @Override
    public String toString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert object to JSON", e);
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Reads a value from the JSON body using a JSONPath expression.
     *
     * @param jsonBody    the JSON body
     * @param jsonPathExpr the JSONPath expression
     * @return the value at the specified JSONPath
     * @throws IllegalArgumentException if the input is invalid
     */
    @Override
    public String read(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        return jm.read(jsonPathExpr).asText();
    }

    /**
     * Deletes a value from the JSON body at the specified JSONPath expression.
     *
     * @param jsonBody    the JSON body
     * @param jsonPathExpr the JSONPath expression
     * @return the updated JSON body as a string
     * @throws IllegalArgumentException if the input is invalid
     */
    @Override
    public String delete(String jsonBody, String jsonPathExpr) {
        validateInput(jsonBody, jsonPathExpr);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        jm.delete(jsonPathExpr);
        return jm.toString();
    }

    /**
     * Reads a value from the JSON body and converts it to the specified type.
     *
     * @param <T>          the type of the value to return
     * @param jsonBody     the JSON body
     * @param jsonPathExpr the JSONPath expression
     * @param type         the type to convert the value to
     * @return the value at the specified JSONPath converted to the given type
     * @throws RuntimeException if the reading fails
     */
    @Override
    public <T> T read(String jsonBody, String jsonPathExpr, Class<T> type) {
        validateInput(jsonBody, jsonPathExpr);
        try {
            DocumentContext jsonContext = JsonPath.parse(jsonBody);
            return jsonContext.read(jsonPathExpr, type);
        } catch (Exception e) {
            logger.error("Failed to read object from JSON", e);
            throw new RuntimeException("Failed to read object from JSON", e);
        }
    }

    /**
     * Updates or adds a value to the JSON body at the specified JSONPath expression.
     *
     * @param jsonBody    the JSON body
     * @param jsonPathExpr the JSONPath expression
     * @param newValue    the new value to be added or updated
     * @return the updated JSON body as a string
     * @throws IllegalArgumentException if the input is invalid
     */
    @Override
    public String update(String jsonBody, String jsonPathExpr, Object newValue) {
        validateInput(jsonBody, jsonPathExpr);
        logger.debug("Original JSON body: {}", jsonBody);
        logger.debug("JSON path expression: {}", jsonPathExpr);
        logger.debug("New value: {}", newValue);
        JsonManipulator jm = new JsonManipulator(jsonBody);
        jm.writeOrUpdate(jsonPathExpr, newValue);
        return jm.toString();
    }

    /**
     * Validates the input JSON body and JSONPath expression.
     *
     * @param jsonBody    the JSON body to validate
     * @param jsonPathExpr the JSONPath expression to validate
     * @throws IllegalArgumentException if any input is null or empty
     */
    private void validateInput(String jsonBody, String jsonPathExpr) {
        if (jsonBody == null || jsonBody.isEmpty()) {
            logger.error("Input JSON content should not be null or empty.");
            throw new IllegalArgumentException("Input JSON content should not be null or empty.");
        }
        if (jsonPathExpr == null || jsonPathExpr.isEmpty()) {
            logger.error("Input JSON path expression should not be null or empty.");
            throw new IllegalArgumentException("Input JSON path expression should not be null or empty.");
        }
    }
}
