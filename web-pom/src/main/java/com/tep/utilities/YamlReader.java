package com.tep.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.LinkedHashMap;

public class YamlReader {

    private final YAMLMapper yamlMapper;
    private final ObjectMapper objectMapper;

    public YamlReader() {
        this.yamlMapper = new YAMLMapper();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

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

    public String convertYamlDataToJson(Map<String, Object> yamlData) {
        Objects.requireNonNull(yamlData, "YAML data cannot be null");
        try {
            return objectMapper.writeValueAsString(yamlData);
        } catch (IOException e) {
            return null;
        }
    }

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
