package com.tep.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The Constants class provides various static constants and utility methods used across the framework.
 * It loads configuration properties from the 'web.properties' file and provides paths for resources, input/output data, and browser settings.
 * It also provides methods for getting the current date and time in various formats.
 */
public class Constants {

    /**
     * The base path of the current project directory.
     */
    public static final String BASE_PATH = System.getProperty("user.dir");

    /**
     * The file separator for the current operating system.
     */
    public static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();

    /**
     * The path to the 'main' directory in the project.
     */
    public static final String MAIN_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main";

    /**
     * The path to the 'test' directory in the project.
     */
    public static final String TEST_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test";

    /**
     * The target directory where build artifacts are placed.
     */
    public static final String TARGET_PATH = BASE_PATH + FILE_SEPARATOR + "target";

    /**
     * The path to the 'web.properties' configuration file.
     */
    public static final String WEB_PROP_PATH = MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties";

    /**
     * The path to the 'web.properties' configuration file.
     */
    public static final String TEP_PROP_PATH = MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "tep.properties";

    /**
     * The path to the 'api.properties' configuration file.
     */
    public static final String API_PROP_PATH = MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "api.properties";

    /**
     * The path to the test application directory, loaded from the 'web.properties' file.
     */
    public static final String TEST_APP_PATH;

    /**
     * The path to the 'features' directory inside the test application, loaded from the 'web.properties' file.
     */
    public static final String TEST_APP_FEATURE_PATH;

    /**
     * The path to the test data input directory for web-related data, loaded from the 'web.properties' file.
     */
    public static final String TEST_DATA_INPUT_PATH;

    /**
     * The path to the test data output directory for web-related results.
     */
    public static final String TEST_DATA_OUTPUT_PATH;

    /**
     * A Properties object containing all the loaded properties from the 'tep.properties' file.
     */
    public static final Properties TEP_PROPERTIES;

    // Static block to load the properties from the 'web.properties' file
    static {
        TEP_PROPERTIES = new Properties();
        try {
            TEP_PROPERTIES.load(new FileInputStream(TEP_PROP_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + TEP_PROP_PATH, e);
        }
        TEST_APP_PATH = TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + TEP_PROPERTIES.getProperty("project.name");
        TEST_APP_FEATURE_PATH = TEST_APP_PATH + FILE_SEPARATOR + "features";
        TEST_DATA_INPUT_PATH = TEST_APP_PATH + FILE_SEPARATOR + "input";
        TEST_DATA_OUTPUT_PATH = TARGET_PATH + FILE_SEPARATOR + "output";
    }

    /**
     * Returns the current date and time formatted as 'yyyy/MM/dd hh:mm:ss a'.
     *
     * @return The formatted current date and time.
     */
    public static String getCurrentDateTime() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a").format(LocalDateTime.now());
    }

    /**
     * Returns the current date formatted as 'yyyy/MM/dd'.
     *
     * @return The formatted current date.
     */
    public static String getCurrentDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    /**
     * Returns the current time formatted as 'hh:mm:ss a'.
     *
     * @return The formatted current time.
     */
    public static String getCurrentTime() {
        return DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());
    }

}
