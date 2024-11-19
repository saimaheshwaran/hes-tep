package com.hes.api.support;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hes.utilities.YamlReader;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Processor for handling YAML data using JsonPath queries.
 * <p>
 * This class reads YAML files from a folder, converts their content to JSON,
 * and enables querying using JsonPath expressions.
 * </p>
 */
public class JsonPathYamlProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPathYamlProcessor.class);

    private static final Configuration JACKSON_CONFIGURATION = Configuration.builder()
            .mappingProvider(new JacksonMappingProvider())
            .jsonProvider(new JacksonJsonProvider())
            .build();

    private DocumentContext content;

    /**
     * Constructs a {@code JsonPathYamlProcessor} with the YAML data from the specified folder.
     *
     * @param folderPath the path to the folder containing YAML files
     * @throws IOException if an error occurs during reading or conversion
     */
    public JsonPathYamlProcessor(String folderPath) throws IOException {
        Objects.requireNonNull(folderPath, "Folder path must not be null.");

        YamlReader yamlReader = new YamlReader();
        Map<?, ?> yamlData = yamlReader.getYamlDataFromFolder(folderPath);

        if (yamlData == null || yamlData.isEmpty()) {
            throw new IllegalArgumentException("No valid YAML data found in folder: " + folderPath);
        }

        String jsonData = yamlReader.convertYamlDataToJson(yamlData);
        setContent(jsonData);
    }

    /**
     * Retrieves the content of the loaded YAML data as a {@code DocumentContext}.
     *
     * @return the content as a {@code DocumentContext}
     */
    public DocumentContext getContent() {
        return content;
    }

    /**
     * Sets the content of the processor from a JSON string.
     *
     * @param jsonContent the JSON-formatted string representing the data
     * @throws IllegalArgumentException if the input is null or invalid
     */
    public void setContent(String jsonContent) {
        Objects.requireNonNull(jsonContent, "JSON content must not be null.");
        this.content = JsonPath.using(JACKSON_CONFIGURATION).parse(jsonContent);
    }

    /**
     * Executes a JsonPath query on the loaded content.
     *
     * @param jsonPath the JsonPath expression to query
     * @param <T>      the expected return type of the query result
     * @return the result of the query, or {@code null} if no value is found or an error occurs
     */
    public <T> T query(String jsonPath) {
        Objects.requireNonNull(jsonPath, "JsonPath expression must not be null.");

        try {
            return (T) content.read(jsonPath, Object.class);
        } catch (PathNotFoundException e) {
            LOGGER.info("No value found for JsonPath '{}': {}", jsonPath, e.getMessage());
            return null;
        } catch (ClassCastException e) {
            LOGGER.error("Type casting error during JsonPath query: {}", jsonPath, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during JsonPath query: {}", jsonPath, e);
            return null;
        }
    }

    public boolean isEmpty() { return (content == null || content.json().toString().isEmpty()); }
}
