package com.tep.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for reading and converting YAML files.
 * Provides methods to load YAML data from files or directories
 * and convert YAML data to JSON format.
 */
public class YamlReader {

    // YAML and JSON mappers for parsing and formatting
    private final YAMLMapper yamlMapper;
    private final ObjectMapper objectMapper;

    // Logger for logging errors and important events
    private static final Logger logger = LoggerFactory.getLogger(YamlReader.class);

    /**
     * Constructor to initialize the YAML and JSON mappers.
     * The JSON mapper is configured for pretty printing.
     */
    public YamlReader() {
        this.yamlMapper = new YAMLMapper();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Reads all YAML files from a given folder and merges their content into a single map.
     *
     * @param folderPath the path to the folder containing YAML files
     * @return a {@link Map} containing merged data from all YAML files in the folder,
     *         or an empty map if no valid files are found
     */
    public Map<String, Object> getYamlDataFromFolder(String folderPath) {
        Objects.requireNonNull(folderPath, "Folder path cannot be null");
        File folder = new File(folderPath);
        Map<String, Object> data = new LinkedHashMap<>();

        if (!folder.exists()) {
            logger.error("Folder does not exist at {}", folderPath);
            return data;
        }

        // Filter YAML files in the folder
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".yml") || name.endsWith(".yaml"));
        if (files == null || files.length == 0) {
            return data;
        }

        // Read each YAML file and merge its content
        for (File file : files) {
            try (FileInputStream fis = new FileInputStream(file)) {
                Map<String, Object> yamlData = yamlMapper.readValue(fis, Map.class);
                if (yamlData != null) {
                    data.putAll(yamlData);
                }
            } catch (IOException e) {
                logger.error("Error reading file {}", file.getAbsolutePath(), e);
            }
        }

        return data;
    }

    /**
     * Reads YAML data from a single file and returns it as a map.
     *
     * @param filePath the path to the YAML file
     * @return a {@link Map} containing the data from the file, or an empty map if the file is invalid
     */
    public Map<String, Object> getYamlDataFromFile(String filePath) {
        Objects.requireNonNull(filePath, "File path cannot be null");
        File file = new File(filePath);

        if (!file.isFile()) {
            logger.error("File does not exist at {}", filePath);
            return new LinkedHashMap<>();
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            return yamlMapper.readValue(fis, Map.class);
        } catch (IOException e) {
            logger.error("Error reading file {}", file.getAbsolutePath(), e);
            return new LinkedHashMap<>();
        }
    }

    /**
     * Converts YAML data from a map to a formatted JSON string.
     *
     * @param yamlData the YAML data as a {@link Map}
     * @return the JSON representation of the YAML data, or {@code null} if conversion fails
     */
    public String convertYamlDataToJson(Map<String, Object> yamlData) {
        Objects.requireNonNull(yamlData, "YAML data cannot be null");
        try {
            return objectMapper.writeValueAsString(yamlData);
        } catch (IOException e) {
            logger.error("Error converting YAML data to JSON", e);
            return null;
        }
    }

    /**
     * Converts a YAML string to a formatted JSON string.
     *
     * @param yamlString the YAML data as a string
     * @return the JSON representation of the YAML string
     * @throws RuntimeException if conversion fails
     */
    public String convertYamlStringToJson(String yamlString) {
        Objects.requireNonNull(yamlString, "YAML string cannot be null");
        try {
            Map<String, Object> yamlData = yamlMapper.readValue(yamlString, Map.class);
            return convertYamlDataToJson(yamlData);
        } catch (IOException e) {
            throw new RuntimeException("Error converting YAML string to JSON", e);
        }
    }
}
