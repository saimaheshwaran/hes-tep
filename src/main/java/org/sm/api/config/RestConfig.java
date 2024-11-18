package org.sm.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.helpers.JsonPathYamlProcessor;
import org.sm.utilities.EnvReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class RestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);
    private final Map<String, String> env = new HashMap<>();

    private RestConfig() throws IOException {
        LOGGER.info("Loading REST config files from folder path: '{}'.", RestConfigContants.API_FOLDER_PATH);
        if(!Files.exists(Path.of(RestConfigContants.API_FOLDER_PATH))) {
            LOGGER.warn("REST Config folder '{}' does not exist. ", RestConfigContants.API_FOLDER_PATH);
        }
        JsonPathYamlProcessor queryableRestConfig = new JsonPathYamlProcessor(RestConfigContants.API_FOLDER_PATH);
        if(queryableRestConfig.isEmpty()) {
            LOGGER.warn("No data was loaded from '{}'.", RestConfigContants.API_FOLDER_PATH);
        }
        if(RestConfigContants.ENV_FILE_PATH != null) {
            LOGGER.info("Loading env file '{}'.", RestConfigContants.ENV_FILE_PATH);
            env.putAll(EnvReader.getEnv(RestConfigContants.ENV_FILE_PATH));
            LOGGER.info("Updating RestConfig with env variable at '{}'.", RestConfigContants.ENV_FILE_PATH);

        }
    }
}
