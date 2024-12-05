package com.tep.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class for managing property files.
 * Provides methods to read, write, and retrieve properties from a file.
 */
public class PropUtils {

    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    // Properties object to manage property key-value pairs
    private final Properties properties = new Properties();

    // Path to the property file
    private final String filePath;

    /**
     * Constructor to initialize the utility with a property file.
     * Loads properties from the specified file.
     *
     * @param filePath the path to the properties file
     */
    public PropUtils(String filePath) {
        this.filePath = filePath;

        try (FileInputStream fileInputStream = new FileInputStream(this.filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Unable to load properties file: {}", filePath, e);
        }
    }

    /**
     * Retrieves the value of a specific property by name.
     *
     * @param name the name of the property
     * @return the value of the property, or {@code null} if not found
     */
    public String get(String name) {
        return properties.getProperty(name);
    }

    /**
     * Retrieves all properties as a {@link Properties} object.
     *
     * @return the {@link Properties} object containing all key-value pairs
     */
    public Properties getAllProperties() {
        return properties;
    }

    /**
     * Retrieves all properties as a {@link Map}.
     *
     * @return a {@link Map} representation of the properties
     */
    public Map<String, String> getAsMap() {
        Map<String, String> map = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }
        return map;
    }

    /**
     * Sets or updates a property in the file.
     * Saves the updated properties back to the file.
     *
     * @param name  the name of the property to set
     * @param value the value of the property to set
     */
    public void set(String name, String value) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            properties.setProperty(name, value);
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            logger.error("Unable to set property: {} = {}", name, value, e);
        }
    }
}

