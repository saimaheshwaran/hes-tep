package org.sm.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for reading, processing, and converting YAML files.
 * <p>
 * This class provides methods for reading YAML files from a specified folder,
 * merging their contents, converting YAML data to JSON, and generating YAML-formatted strings.
 * </p>
 */
public class YamlReader {

    private final Yaml yaml;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a {@code YamlReader} with default configurations.
     */
    public YamlReader() {
        LoaderOptions loaderOptions = new LoaderOptions();
        this.yaml = new Yaml(new SafeConstructor(loaderOptions));

        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty-print for JSON output
    }

    /**
     * Reads all YAML files from the specified folder and merges their contents into a single map.
     *
     * @param folderPath the path to the folder containing YAML files
     * @return a {@code Map<String, Object>} containing the merged YAML data,
     * or an empty map if no YAML files are found
     * @throws IOException if an error occurs while reading the files
     */
    public Map<String, Object> getYamlDataFromFolder(String folderPath) throws IOException {
        Objects.requireNonNull(folderPath, "Folder path must not be null.");

        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a valid directory: " + folderPath);
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".yaml") || name.endsWith(".yml"));
        if (files == null || files.length == 0) {
            return new LinkedHashMap<>();
        }

        Map<String, Object> mergedYaml = new LinkedHashMap<>();
        for (File file : files) {
            try (FileInputStream fis = new FileInputStream(file)) {
                Map<String, Object> yamlData = yaml.load(fis);
                if (yamlData != null) {
                    mergedYaml.putAll(yamlData);
                }
            } catch (IOException e) {
                throw new IOException("Failed to read file: " + file.getName(), e);
            }
        }

        return mergedYaml;
    }

    public Map<String, Object> getYamlDataFromFile(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "File path must not be null.");

        File file = new File(filePath);
        if (!file.isFile()) {
            throw new IllegalArgumentException("The specified path is not a valid file path: " + filePath);
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            Map<String, Object> yamlData = yaml.load(fis);
            if (yamlData != null) {
                return yamlData;
            }
        } catch (IOException e) {
            throw new IOException("Failed to read file: " + file.getName(), e);
        }
        return Map.of();
    }

    /**
     * Converts a YAML data map to a JSON-formatted string.
     *
     * @param yamlData the YAML data represented as a {@code Map<?, ?>}
     * @return a JSON-formatted string representing the YAML data
     * @throws JsonProcessingException if the conversion to JSON fails
     */
    public String convertYamlDataToJson(Map<?, ?> yamlData) throws JsonProcessingException {
        Objects.requireNonNull(yamlData, "YAML data must not be null.");
        return objectMapper.writeValueAsString(yamlData);
    }

    /**
     * Converts a YAML data map to a YAML-formatted string.
     * <p>
     * The output string uses block-style formatting for better readability.
     * </p>
     *
     * @param yamlData the YAML data represented as a {@code Map<?, ?>}
     * @return a YAML-formatted string representing the input data
     */
    public String convertYamlDataToString(Map<?, ?> yamlData) {
        Objects.requireNonNull(yamlData, "YAML data must not be null.");

        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        return new Yaml(options).dump(yamlData);
    }

    /**
     * Converts a YAML-formatted string to a JSON-formatted string.
     *
     * @param yamlString the YAML-formatted string to convert
     * @return a JSON-formatted string
     * @throws IOException if the conversion fails
     */
    public String convertYamlStringToJson(String yamlString) throws IOException {
        Objects.requireNonNull(yamlString, "YAML string must not be null.");

        Map<String, Object> yamlData = yaml.load(yamlString);
        return convertYamlDataToJson(yamlData);
    }
}