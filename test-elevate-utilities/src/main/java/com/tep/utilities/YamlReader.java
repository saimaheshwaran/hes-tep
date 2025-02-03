package com.tep.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for reading and processing YAML files.
 * Provides methods to read YAML data from files or directories,
 * and to convert YAML data to JSON format.
 */
public class YamlReader {

    /**
     * Mapper for reading YAML data.
     */
    private final YAMLMapper yamlMapper;

    /**
     * Mapper for serializing objects to JSON.
     */
    private final ObjectMapper objectMapper;

    /**
     * Default constructor that initializes the mappers with default configurations.
     */
    public YamlReader() {
        this.yamlMapper = new YAMLMapper();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Reads and merges YAML data from all `.yml` or `.yaml` files in a given folder.
     *
     * @param folderPath the path of the folder containing YAML files.
     * @return a {@link Map} containing the merged YAML data from all files in the folder,
     * or an empty map if the folder does not exist or contains no YAML files.
     * @throws NullPointerException if the folder path is null.
     */
    public Map<String, Object> getYamlDataFromFolder(String folderPath) {
        Objects.requireNonNull(folderPath, "Folder path cannot be null");
        File folder = new File(folderPath);
        Map<String, Object> data = new LinkedHashMap<>();

        if (!folder.exists()) {
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
            } catch (IOException ignored) {
            }
        }
        return data;
    }

    /**
     * Reads YAML data from a single file.
     *
     * @param filePath the path of the YAML file.
     * @return a {@link Map} containing the YAML data,
     * or an empty map if the file does not exist or is invalid.
     * @throws NullPointerException if the file path is null.
     */
    public Map<String, Object> getYamlDataFromFile(String filePath) {
        Objects.requireNonNull(filePath, "File path cannot be null");
        File file = new File(filePath);

        if (!file.isFile()) {
            return new LinkedHashMap<>();
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            return yamlMapper.readValue(fis, Map.class);
        } catch (IOException e) {
            return new LinkedHashMap<>();
        }
    }

    /**
     * Converts a map of YAML data into a JSON string.
     *
     * @param yamlData the map containing YAML data.
     * @return a JSON-formatted string representing the YAML data,
     * or {@code null} if an error occurs during conversion.
     * @throws NullPointerException if the YAML data map is null.
     */
    public String convertYamlDataToJson(Map<String, Object> yamlData) {
        Objects.requireNonNull(yamlData, "YAML data cannot be null");
        try {
            return objectMapper.writeValueAsString(yamlData);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Converts a YAML-formatted string into a JSON string.
     * This method first parses the YAML string into a map representation,
     * then serializes the map into a JSON-formatted string using the ObjectMapper.
     *
     * @param yamlString the YAML string to convert. Must not be null.
     * @return a JSON-formatted string representing the data contained in the YAML string.
     * @throws NullPointerException if the YAML string is null, with a message indicating that the YAML string cannot be null.
     * @throws RuntimeException if an error occurs during the parsing of the YAML string or serialization to JSON. The original IOException is included as the cause of this RuntimeException.
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
