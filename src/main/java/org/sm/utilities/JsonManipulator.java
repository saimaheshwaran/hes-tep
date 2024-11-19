package org.sm.utilities;

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
 * Json Manipulator class.
 * Supports the creation of the nodes absent on the path.
 *
 */
public class JsonManipulator {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonManipulator.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private JsonNode json;

    public JsonManipulator(String json) {
        try {
            this.json = mapper.readTree(json);
        } catch (Exception e) {
            LOGGER.error("Error during JsonManipulator initialization. Defaulting to empty json object \\{}.", e);
            this.json = mapper.createObjectNode(); // default to an empty object
        }
    }

    /**
     * Read data by path.
     *
     * @param jsonPath json path expression.
     * @return Use methods .toText(), toInt() on the return object.
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
     * Delete a node and all of it's nested nodes.
     *
     * @param jsonPath json path expression
     */
    public void delete(String jsonPath) {
        try {
            DocumentContext jsonContext = JsonPath.parse(this.json.toString());
            this.json = mapper.readTree(jsonContext.delete(jsonPath).jsonString());
        } catch (Exception e) {
            throw new RuntimeException("Error when reading path " + jsonPath, e);
        }
    }

    /**
     * Write or update a value of json object or array based on the path.
     * First attempt to use jsonpath directly. On error, use jackson to create the path.
     *
     * @param jsonPath json path expression
     * @param value    value to set or update
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
     * Used when jayway.JsonPath failed to write a value for path.
     * If path ends with [+] it means append the value at a new index in the array.
     * If path ends with [] it means initialize a new array or overwrite existing
     * node of the same name with array, and add a value to it.
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

    // Append passed data to the json array
    private void appendToArray(String jsonPointer, Object value) {
        JsonNode arrayNode = this.json.at(jsonPointer);
        if (arrayNode.isArray()) {
            ((ArrayNode) arrayNode).add(mapper.valueToTree(value));
        } else {
            System.err.println("The specified path is not an array: " + jsonPointer);
        }
    }

    // Initialize new array at the path or overwrite existing node and make it an array
    private void createOrOverwriteArray(String jsonPointer, Object value) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(mapper.valueToTree(value));
        setAtPath(jsonPointer, arrayNode);
    }

    /**
     * Converts jsonPath to JsonPointer for work with Jackson library
     *
     * @param jsonPath json path expression
     * @return String Json Pointer
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
     * Standard write or update operation using Jackson.
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

    @Override
    public String toString() {
        return this.json.toString();
    }
}

