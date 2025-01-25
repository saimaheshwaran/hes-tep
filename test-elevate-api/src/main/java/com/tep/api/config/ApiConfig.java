package com.tep.api.config;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.tep.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tep.utilities.PlaceHolderReplacer;
import com.tep.utilities.PropUtils;
import com.tep.utilities.YamlReader;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

/**
 * Singleton configuration class for API settings.
 * Responsible for loading, parsing, and managing API configuration from YAML and property files.
 */
public class ApiConfig {

    // Static fields for configuration management
    private static DocumentContext content;
    private static final ApiConfig configInstance;
    private static PropUtils apiProps;

    // Configuration for JSONPath using Jackson
    private static final Configuration JACKSON_CONFIGURATION = Configuration.builder()
            .mappingProvider(new JacksonMappingProvider())
            .jsonProvider(new JacksonJsonProvider())
            .build();

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(ApiConfig.class);
    private final LoggerContext logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final ch.qos.logback.classic.Logger log = logContext.getLogger("com.jayway.jsonpath.internal.path.CompiledPath");

    static {
        try {
            configInstance = new ApiConfig();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize API configurations.", e);
        }
    }

    /**
     * Private constructor for singleton initialization.
     * Loads and processes API configuration from YAML and property files.
     */
    private ApiConfig() {
        logger.debug("Starting API configuration setup.");
        log.setLevel(ch.qos.logback.classic.Level.INFO);

        // Load TEP properties
        if (!Files.exists(Path.of(Constants.TEP_PROP_PATH))) {
            logger.warn("TEP properties file does not exist at '{}'", Constants.TEP_PROP_PATH);
        } else {
            if (!Boolean.parseBoolean(Constants.TEP_PROPERTIES.getProperty("api"))) {
                logger.warn("API testing is disabled. Set 'api=true' in '{}'.", Constants.TEP_PROP_PATH);
            } else {
                // Load API configuration files
                loadApiConfiguration();

                // Load API properties and replace placeholders
                loadApiProperties();
            }
        }

        logger.debug("API configuration setup complete.");
    }

    /**
     * Loads API configuration from the YAML files in the specified folder.
     */
    private void loadApiConfiguration() {
        String apiFolder = Constants.TEST_DATA_INPUT_PATH + File.separator + "api";
        logger.info("Checking if API folder exists at '{}'", apiFolder);

        if (!Files.exists(Path.of(apiFolder))) {
            logger.warn("API configuration folder does not exist at '{}'", apiFolder);
        } else {
            logger.info("Loading API configurations from '{}'", apiFolder);
            YamlReader yamlReader = new YamlReader();
            Map<String, Object> yamlData = yamlReader.getYamlDataFromFolder(apiFolder);

            if (yamlData == null || yamlData.isEmpty()) {
                logger.error("No YAML contents found at '{}'", apiFolder);
            } else {
                String jsonData = yamlReader.convertYamlDataToJson(yamlData);
                setContent(jsonData);
                unload(yamlData);
                logger.info("API configurations loaded from '{}'", apiFolder);
            }
        }
    }

    /**
     * Loads API properties and updates configuration placeholders with environment variables.
     */
    private void loadApiProperties() {

        logger.info("Checking if API properties file exists at '{}'", Constants.API_PROP_PATH);

        if (!Files.exists(Path.of(Constants.API_PROP_PATH))) {
            logger.warn("API properties file does not exist at '{}'", Constants.API_PROP_PATH);
        } else {
            apiProps = new PropUtils(Constants.API_PROP_PATH);

            try {
                String jsonContent = content.jsonString();
                PlaceHolderReplacer placeHolderReplacer = new PlaceHolderReplacer((Map<String, String>) apiProps.getAsMap());
                String updatedJsonContent = placeHolderReplacer.replace(jsonContent);
                setContent(updatedJsonContent);
                logger.info("API properties loaded and placeholders replaced from '{}'.", Constants.API_PROP_PATH);
            } catch (Exception e) {
                logger.error("Failed to update API config with environment variables: {}", e.getMessage(), e);
            }
        }
    }

    /**
     * Singleton instance accessor.
     *
     * @return the singleton instance of {@link ApiConfig}.
     */
    private static ApiConfig getInstance() {
        return configInstance;
    }

    /**
     * Updates the configuration content with the given JSON string.
     *
     * @param jsonContent the JSON content to set.
     * @throws NullPointerException if {@code jsonContent} is null.
     */
    public static void setContent(String jsonContent) {
        Objects.requireNonNull(jsonContent, "JSON content cannot be null");
        content = JsonPath.using(JACKSON_CONFIGURATION).parse(jsonContent);
    }

    /**
     * Retrieves the configuration content as a {@link DocumentContext}.
     *
     * @return the current configuration content.
     */
    public static DocumentContext getContent() {
        return content;
    }

    /**
     * Executes a JSONPath query on the configuration content.
     *
     * @param jsonPath the JSONPath query string.
     * @return the result of the query, or {@code null} if not found or an error occurs.
     */
    public static <T> T query(String jsonPath) {
        Objects.requireNonNull(jsonPath, "JSONPath cannot be null");
        try {
            return (T) content.read(jsonPath, Object.class);
        } catch (PathNotFoundException e) {
            //logger.info("No value found for JSONPath '{}': {}", jsonPath, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Error during JSONPath query '{}': {}", jsonPath, e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a configuration value based on API name and path.
     *
     * @param apiName the API name.
     * @param path    the configuration path within the API.
     * @return the configuration value, or {@code null} if not found.
     */
    public static <T> T get(String apiName, String path) {
        if (apiName == null || apiName.isEmpty()) {
            logger.warn("API name is not set. Skipping configuration lookup.");
            return null;
        }

        String[] lookupPaths = {
                String.format("%s.%s.%s", apiProps.get("ENVIRONMENT"), apiName, path),
                String.format("%s.%s.%s", apiProps.get("FALLBACK_ENVIRONMENT"), apiName, path)
        };

        for (String lookupPath : lookupPaths) {
            T value = query(lookupPath);
            if (value != null) {
                logger.info("Found value for '{}'", lookupPath);
                return value;
            }
        }
        return null;
    }

    /**
     * Utility method to unload YAML data into both YAML and JSON files.
     *
     * <p>This method takes a map of YAML data and writes it to both a YAML file and a JSON file
     * in the specified target directory. If the target directory does not exist, it will be created.</p>
     *
     * @param yamlData the YAML data to be written to files
     */
    private void unload(Map<String, Object> yamlData) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringObj = new StringWriter();
        Yaml yaml = new Yaml();
        String folderPath = Constants.TARGET_PATH + File.separator + "api";
        try {
            if(Files.notExists(Paths.get(folderPath))) Files.createDirectories(Paths.get(folderPath));
            FileWriter yamlWriteFile = new FileWriter(folderPath + File.separator + "apiConfig" + ".yaml");
            yaml.dump(yamlData, yamlWriteFile);
            yamlWriteFile.close();

            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(stringObj, yamlData);
            FileWriter jsonWriteFile = new FileWriter(folderPath + File.separator + "apiConfig" + ".json");
            jsonWriteFile.write(stringObj.toString());
            jsonWriteFile.close();
        } catch (Exception ignored) {
            System.out.println(ignored.getMessage());
        }
    }
}
