package com.tep.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code JsonManipulator} class provides methods for reading, writing, updating, and deleting JSON data.
 * It uses Jackson for JSON parsing and manipulation, and JsonPath for querying JSON data.
 */
public class JsonManipulator {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonManipulator.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private JsonNode json;

    /**
     * Constructs a {@code JsonManipulator} with the given JSON string.
     *
     * @param json The JSON string to be manipulated.
     */
    public JsonManipulator(String json) {
        try {
            this.json = mapper.readTree(json);
        } catch (Exception e) {
            LOGGER.error("Error during JsonManipulator initialization. Defaulting to empty JSON object {}.", e);
            this.json = mapper.createObjectNode(); // default to an empty object
        }
    }

    /**
     * Reads a value from the JSON data using the specified JSON path.
     *
     * @param jsonPath The JSON path to read the value from.
     * @return The {@code JsonNode} representing the value at the specified JSON path.
     * @throws RuntimeException If an error occurs while reading the JSON path.
     */
    public JsonNode read(String jsonPath) {
        try {
            DocumentContext jsonContext = JsonPath.parse(this.json.toString());
            Object result = jsonContext.read(jsonPath);
            if (result instanceof String) {
                // Ensure the string is properly quoted as JSON
                return mapper.readTree("\"" + result + "\"");
            } else {
                // Convert the result back to JSON string and parse it
                return mapper.readTree(mapper.writeValueAsString(result));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error when reading JSON path " + jsonPath, e);
        }
    }

    /**
     * Deletes a value from the JSON data using the specified JSON path.
     *
     * @param jsonPath The JSON path to delete the value from.
     * @throws RuntimeException If an error occurs while deleting the JSON path.
     */
    public void delete(String jsonPath) {
        try {
            DocumentContext jsonContext = JsonPath.parse(this.json.toString());
            this.json = mapper.readTree(jsonContext.delete(jsonPath).jsonString());
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting path " + jsonPath, e);
        }
    }

    /**
     * Writes or updates a value in the JSON data using the specified JSON path.
     *
     * @param jsonPath The JSON path to write or update the value.
     * @param value    The value to be written or updated.
     * @throws RuntimeException If an error occurs while writing or updating the JSON data.
     */
    public void writeOrUpdate(String jsonPath, Object value) {
        try {
            DocumentContext jsonContext = JsonPath.parse(this.json.toString());
            if (value instanceof JsonNode) {
                // Convert JsonNode to JSON String to ensure it's handled correctly by JsonPath
                String jsonString = mapper.writeValueAsString(value);
                jsonContext.set(jsonPath, Configuration.defaultConfiguration().jsonProvider().parse(jsonString));
            } else {
                // Directly set the value if it's not a JsonNode
                jsonContext.set(jsonPath, value);
            }
            this.json = mapper.readTree(jsonContext.jsonString());
        } catch (InvalidPathException e) {
            handleJacksonWriteOrUpdate(jsonPath, value);
        } catch (Exception e) {
            throw new RuntimeException("Error when writing or updating the JSON using JsonPath.", e);
        }
    }

    /**
     * Handles writing or updating JSON data using Jackson when JsonPath fails.
     *
     * @param jsonPath The JSON path to write or update the value.
     * @param value    The value to be written or updated.
     */
    private void handleJacksonWriteOrUpdate(String jsonPath, Object value) {
        String jsonPointer = convertToJsonPointer(jsonPath);
        if (jsonPath.endsWith("[+]")) {
            // Remove the last three characters "[+]"
            appendToArray(jsonPointer.substring(0, jsonPointer.length() - 2), value);
        } else if (jsonPath.endsWith("[]")) {
            // Remove the last two characters "[]"
            createOrOverwriteArray(jsonPointer.substring(0, jsonPointer.length() - 1), value);
        } else {
            // Standard write or update operation using Jackson
            setAtPath(jsonPointer, mapper.valueToTree(value));
        }
    }

    /**
     * Appends a value to an array in the JSON data.
     *
     * @param jsonPointer The JSON pointer to the array.
     * @param value       The value to be appended to the array.
     */
    private void appendToArray(String jsonPointer, Object value) {
        JsonNode arrayNode = this.json.at(jsonPointer);
        if (arrayNode.isArray()) {
            ((ArrayNode) arrayNode).add(mapper.valueToTree(value));
        } else {
            LOGGER.error("The specified path is not an array: {}", jsonPointer);
        }
    }

    /**
     * Creates or overwrites an array in the JSON data with the specified value.
     *
     * @param jsonPointer The JSON pointer to the array.
     * @param value       The value to be set in the array.
     */
    private void createOrOverwriteArray(String jsonPointer, Object value) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(mapper.valueToTree(value));
        setAtPath(jsonPointer, arrayNode);
    }

    /**
     * Converts a JSON path to a JSON pointer.
     *
     * @param jsonPath The JSON path to be converted.
     * @return The JSON pointer corresponding to the JSON path.
     */
    private String convertToJsonPointer(String jsonPath) {
        // Ensure no extra '/' at the beginning if the path already correctly starts from the root
        if (jsonPath.startsWith("$")) {
            jsonPath = jsonPath.substring(1); // Remove '$' if present
        }
        jsonPath = jsonPath.replace(".", "/").replace("[", "/").replace("]", "");
        if (!jsonPath.startsWith("/")) {
            jsonPath = "/" + jsonPath; // Ensure it starts with '/' to denote root
        }
        return jsonPath;
    }

    /**
     * Sets a value at the specified JSON pointer in the JSON data.
     *
     * @param jsonPointer The JSON pointer to set the value.
     * @param newValue    The new value to be set.
     */
    private void setAtPath(String jsonPointer, JsonNode newValue) {
        if (jsonPointer.startsWith("//")) {
            jsonPointer = jsonPointer.substring(1); // Correct paths that start with '//' due to root confusion
        }
        String[] paths = jsonPointer.split("/");
        JsonNode currentNode = this.json;

        for (int i = 1; i < paths.length; i++) { // Start from 1 to skip empty initial split result from leading '/'
            String pathSegment = paths[i];
            if (pathSegment.isEmpty()) continue; // Skip any empty segments caused by consecutive slashes

            boolean isLast = i == paths.length - 1;
            try {
                int index = Integer.parseInt(pathSegment); // Attempt to parse the segment as an array index
                if (!isLast) {
                    currentNode = currentNode.get(index);
                } else {
                    ((ArrayNode) currentNode).set(index, newValue); // Set the new value at the index
                    return;
                }
            } catch (NumberFormatException e) {
                if (currentNode.isObject()) {
                    if (!currentNode.has(pathSegment)) {
                        if (!isLast) {
                            currentNode = currentNode.path(pathSegment).isObject() ? currentNode.get(pathSegment) : ((ObjectNode) currentNode).putObject(pathSegment);
                        } else {
                            ((ObjectNode) currentNode).set(pathSegment, newValue);
                        }
                    } else {
                        if (isLast) {
                            ((ObjectNode) currentNode).replace(pathSegment, newValue);
                        } else {
                            currentNode = currentNode.get(pathSegment);
                        }
                    }
                } else if (currentNode.isArray() && !isLast) {
                    throw new RuntimeException("Cannot access non-integer index on an array: " + pathSegment);
                }
            }
        }
    }

    /**
     * Returns a string representation of the JSON data.
     *
     * @return A string representation of the JSON data.
     */
    @Override
    public String toString() {
        return this.json.toString();
    }
}
