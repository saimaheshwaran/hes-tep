package com.tep.web.config;

import java.io.IOException;
import java.util.Properties;
import java.time.LocalDateTime;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.time.format.DateTimeFormatter;

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
     * The page object model type specified in the configuration.
     */
    public static final String PAGE_OBJECT_TYPE;

    /**
     * The browser type to be used in tests, loaded from the 'web.properties' file.
     */
    public static final String BROWSER_TYPE;

    /**
     * Whether the report generation is enabled, as defined in the 'web.properties' file.
     */
    public static final boolean REPORT_ENABLED;

    /**
     * Whether the browser should quit after tests, as specified in the 'web.properties' file.
     */
    public static final boolean BROWSER_QUIT;

    /**
     * Whether the browser should run in headless mode, as defined in the 'web.properties' file.
     */
    public static final boolean BROWSER_HEADLESS;

    /**
     * Whether the browser should be maximized upon launch, as specified in the 'web.properties' file.
     */
    public static final boolean BROWSER_MAXIMIZE;

    /**
     * Whether stepwise screenshots should be taken, as defined in the 'web.properties' file.
     */
    public static final boolean STEPWISE_SCREENSHOT;

    /**
     * Whether desktop screenshots should be taken, as defined in the 'web.properties' file.
     */
    public static final boolean DESKTOP_SCREENSHOT;

    /**
     * The default wait time (in seconds) for Selenium to wait before performing actions.
     */
    public static final int DEFAULT_WAIT_TIME_SEC;

    /**
     * The implicit wait time (in seconds) for WebDriver, loaded from the 'web.properties' file.
     */
    public static final int IMPLICIT_WAIT_TIME_SEC;

    /**
     * A Properties object containing all the loaded properties from the 'web.properties' file.
     */
    public static final Properties PROPERTIES;

    // Static block to load the properties from the 'web.properties' file
    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(WEB_PROP_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + WEB_PROP_PATH, e);
        }
        TEST_APP_PATH = TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + PROPERTIES.getProperty("project.name");
        TEST_APP_FEATURE_PATH = TEST_APP_PATH + FILE_SEPARATOR + "features";
        TEST_DATA_INPUT_PATH = TEST_APP_PATH + FILE_SEPARATOR + "input" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        TEST_DATA_OUTPUT_PATH = TARGET_PATH + FILE_SEPARATOR + "output" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        BROWSER_TYPE = PROPERTIES.getProperty("browser").toUpperCase();
        REPORT_ENABLED = PROPERTIES.getProperty("reports").equalsIgnoreCase("true");
        BROWSER_QUIT = PROPERTIES.getProperty("browser.quit").equalsIgnoreCase("true");
        BROWSER_HEADLESS = PROPERTIES.getProperty("browser.headless").equalsIgnoreCase("true");
        BROWSER_MAXIMIZE = PROPERTIES.getProperty("browser.maximize").equalsIgnoreCase("true");
        STEPWISE_SCREENSHOT = PROPERTIES.getProperty("stepwise.screenshot").equalsIgnoreCase("true");
        DESKTOP_SCREENSHOT = PROPERTIES.getProperty("desktop.screenshot").equalsIgnoreCase("true");
        PAGE_OBJECT_TYPE = PROPERTIES.getProperty("page.object.type").toLowerCase();
        DEFAULT_WAIT_TIME_SEC = Integer.parseInt(PROPERTIES.getProperty("default.wait.time.sec"));
        IMPLICIT_WAIT_TIME_SEC = Integer.parseInt(PROPERTIES.getProperty("implicit.wait.time.sec"));
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
