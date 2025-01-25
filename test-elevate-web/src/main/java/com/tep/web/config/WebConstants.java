package com.tep.web.config;

import com.tep.utilities.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The Constants class provides various static constants and utility methods used across the framework.
 * It loads configuration properties from the 'web.properties' file and provides paths for resources, input/output data, and browser settings.
 * It also provides methods for getting the current date and time in various formats.
 */
public class WebConstants {

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
    public static final Properties WEB_PROPERTIES;

    // Static block to load the properties from the 'web.properties' file
    static {
        WEB_PROPERTIES = new Properties();
        try {
            WEB_PROPERTIES.load(new FileInputStream(Constants.WEB_PROP_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + Constants.WEB_PROP_PATH, e);
        }
        BROWSER_TYPE = WEB_PROPERTIES.getProperty("browser").toUpperCase();
        REPORT_ENABLED = WEB_PROPERTIES.getProperty("reports").equalsIgnoreCase("true");
        BROWSER_QUIT = WEB_PROPERTIES.getProperty("browser.quit").equalsIgnoreCase("true");
        BROWSER_HEADLESS = WEB_PROPERTIES.getProperty("browser.headless").equalsIgnoreCase("true");
        BROWSER_MAXIMIZE = WEB_PROPERTIES.getProperty("browser.maximize").equalsIgnoreCase("true");
        STEPWISE_SCREENSHOT = WEB_PROPERTIES.getProperty("stepwise.screenshot").equalsIgnoreCase("true");
        DESKTOP_SCREENSHOT = WEB_PROPERTIES.getProperty("desktop.screenshot").equalsIgnoreCase("true");
        PAGE_OBJECT_TYPE = WEB_PROPERTIES.getProperty("page.object.type").toLowerCase();
        DEFAULT_WAIT_TIME_SEC = Integer.parseInt(WEB_PROPERTIES.getProperty("default.wait.time.sec"));
        IMPLICIT_WAIT_TIME_SEC = Integer.parseInt(WEB_PROPERTIES.getProperty("implicit.wait.time.sec"));
    }

}
