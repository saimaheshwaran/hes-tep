package org.sm.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.helpers.JsonPathYamlProcessor;
import org.sm.utilities.EnvReader;
import org.sm.utilities.PlaceHolderReplacer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * RestConfig class is responsible for loading and managing REST configuration files.
 * It supports loading configurations from YAML files and environment variables.
 */
public class RestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);
    private final Map<String, String> env = new HashMap<>();
    private final JsonPathYamlProcessor queryableRestConfig;
    private static final RestConfig SHARED_INSTANCE;

    static {
        try {
            SHARED_INSTANCE = new RestConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Private constructor to initialize the RestConfig instance.
     * Loads configuration files and environment variables.
     *
     * @throws IOException if there is an error reading the configuration files.
     */
    private RestConfig() throws IOException {
        LOGGER.info("Loading REST config files from folder path: '{}'.", RestConfigConstants.API_FOLDER_PATH);

        // Check if the configuration folder exists
        if (!Files.exists(Path.of(RestConfigConstants.API_FOLDER_PATH))) {
            LOGGER.warn("REST Config folder '{}' does not exist.", RestConfigConstants.API_FOLDER_PATH);
        }

        // Load the configuration files
        queryableRestConfig = new JsonPathYamlProcessor(RestConfigConstants.API_FOLDER_PATH);

        // Check if any data was loaded
        if (queryableRestConfig.isEmpty()) {
            LOGGER.warn("No data was loaded from '{}'.", RestConfigConstants.API_FOLDER_PATH);
        }

        // Load environment variables if the path is provided
        if (RestConfigConstants.ENV_FILE_PATH != null) {
            LOGGER.info("Loading env file '{}'.", RestConfigConstants.ENV_FILE_PATH);
            env.putAll(EnvReader.getEnv(RestConfigConstants.ENV_FILE_PATH));
            LOGGER.info("Updating RestConfig with env variables from '{}'.", RestConfigConstants.ENV_FILE_PATH);

            try {
                // Replace placeholders in the configuration with environment variables
                String jsonContent = queryableRestConfig.getContent().jsonString();
                PlaceHolderReplacer placeHolderReplacer = new PlaceHolderReplacer(env);
                String updatedJson = placeHolderReplacer.replace(jsonContent);
                queryableRestConfig.setContent(updatedJson);
            } catch (Exception e) {
                LOGGER.error("Failed to update RestConfig with env variables.", e);
            }
        }
    }

    /**
     * Retrieves the singleton instance of RestConfig
     * @return the shared RestConfig instance
     */
    public static RestConfig getInstance() {return SHARED_INSTANCE;}

    /**
     * Retrieves a configuration value for a given API name and path.
     * It first attempts to find the value in the primary environment, then in the fallback environment.
     *
     * @param apiName the name of the API.
     * @param path the path to the configuration value.
     * @param <T> the type of the configuration value.
     * @return the configuration value, or null if not found.
     */
    public <T> T get(String apiName, String path) {
        if (apiName == null || apiName.isEmpty()) {
            LOGGER.warn("API name not set. Omitting configuration lookup.");
            return null;
        }

        // Define lookup paths for primary and fallback environments
        String[] lookupPaths = {
                String.format("%s.%s.%s", RestConfigConstants.ENVIRONMENT, apiName, path),
                String.format("%s.%s.%s", RestConfigConstants.FALLBACK_ENVIRONMENT, apiName, path)
        };

        // Attempt to find the configuration value in the defined paths
        for (String lookupPath : lookupPaths) {
            T value = get(lookupPath);
            if (value != null) {
                LOGGER.info("Found value for '{}'.", lookupPath);
                return value;
            }
        }

        return null; // If all attempts fail, return null
    }

    /**
     * Retrieves a configuration value for a given path.
     *
     * @param path the path to the configuration value.
     * @param <T> the type of the configuration value.
     * @return the configuration value, or null if not found.
     */
    public <T> T get(String path) {
        return queryableRestConfig.query(path);
    }

    /**
     * Retrieves the loaded environment variables.
     *
     * @return a map of environment variables.
     */
    public Map<String, String> getEnv() {
        return env;
    }
}
