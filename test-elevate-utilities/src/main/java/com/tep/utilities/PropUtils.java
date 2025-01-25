package com.tep.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class for managing properties files.
 * Provides methods to read, write, and manipulate key-value pairs in a properties file.
 */
public class PropUtils {

    /**
     * The file path of the properties file to manage.
     */
    private final String filePath;

    /**
     * The {@link Properties} object that holds the key-value pairs loaded from the file.
     */
    private final Properties properties = new Properties();

    /**
     * Constructor that initializes the utility with the specified properties file path.
     * Loads all existing properties from the file into memory.
     *
     * @param filePath the path of the properties file to manage.
     */
    public PropUtils(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fileInputStream = new FileInputStream(this.filePath)) {
            properties.load(fileInputStream);
        } catch (IOException ignored) {
        }
    }

    /**
     * Retrieves the value associated with a given key from the properties file.
     *
     * @param name the key whose value is to be retrieved.
     * @return the value associated with the key, or {@code null} if the key is not found.
     */
    public String get(String name) {
        return properties.getProperty(name);
    }

    /**
     * Retrieves all properties as a {@link Properties} object.
     *
     * @return the {@link Properties} object containing all key-value pairs.
     */
    public Properties getAllProperties() {
        return properties;
    }

    /**
     * Converts all properties into a {@link Map} for easier manipulation.
     *
     * @return a {@link Map} containing all key-value pairs from the properties file.
     */
    public Map<String, String> getAsMap() {
        Map<String, String> map = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }
        return map;
    }

    /**
     * Sets a new key-value pair or updates the value of an existing key in the properties file.
     * The updated properties are written back to the file.
     *
     * @param name  the key to set or update.
     * @param value the value to associate with the key.
     */
    public void set(String name, String value) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            properties.setProperty(name, value);
            properties.store(fileOutputStream, null);
        } catch (IOException ignored) {
        }
    }

}
