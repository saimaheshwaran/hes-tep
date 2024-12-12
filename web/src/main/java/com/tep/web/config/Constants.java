package com.tep.web.config;

import com.tep.utilities.PropUtils;

import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Constants class to hold configuration constants and utility methods.
 */
public class Constants {

    public static final String BASE_PATH = System.getProperty("user.dir");
    public static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String MAIN_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main";
    public static final String TEST_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test";
    public static final String TARGET_PATH = BASE_PATH + FILE_SEPARATOR + "target";
    public static final String WEB_PROP_PATH = MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties";
    public static final String TEST_APP_PATH;
    public static final String TEST_APP_FEATURE_PATH;
    public static final String TEST_DATA_INPUT_PATH;
    public static final String TEST_DATA_OUTPUT_PATH;
    public static final String PAGE_OBJECT_TYPE;
    public static final boolean REPORT_ENABLED;
    public static final boolean BROWSER_QUIT;
    public static final boolean BROWSER_HEADLESS;
    public static final boolean BROWSER_MAXIMIZE;
    public static final boolean STEPWISE_SCREENSHOT;
    public static final boolean DESKTOP_SCREENSHOT;
    public static final int DEFAULT_WAIT_TIME_SEC;
    public static final int IMPLICIT_WAIT_TIME_SEC;
    public static final PropUtils CONFIG;

    static {
        CONFIG = new PropUtils(MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties");
        TEST_APP_PATH = TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + CONFIG.get("project.name");
        TEST_APP_FEATURE_PATH = "src/test/resources/" + CONFIG.get("application");
        TEST_DATA_INPUT_PATH = TEST_APP_PATH + FILE_SEPARATOR + "input" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        TEST_DATA_OUTPUT_PATH = TARGET_PATH + FILE_SEPARATOR + "output" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        REPORT_ENABLED = CONFIG.get("reports").equalsIgnoreCase("true");
        BROWSER_QUIT = CONFIG.get("browser.quit").equalsIgnoreCase("true");
        BROWSER_HEADLESS = CONFIG.get("browser.headless").equalsIgnoreCase("true");
        BROWSER_MAXIMIZE = CONFIG.get("browser.maximize").equalsIgnoreCase("true");
        STEPWISE_SCREENSHOT = CONFIG.get("stepwise.screenshot").equalsIgnoreCase("true");
        DESKTOP_SCREENSHOT = CONFIG.get("desktop.screenshot").equalsIgnoreCase("true");
        PAGE_OBJECT_TYPE = CONFIG.get("page.object.type").toLowerCase();
        DEFAULT_WAIT_TIME_SEC = Integer.parseInt(CONFIG.get("default.wait.time.sec"));
        IMPLICIT_WAIT_TIME_SEC = Integer.parseInt(CONFIG.get("implicit.wait.time.sec"));
    }

    /**
     * Gets the current date and time in the format "yyyy/MM/dd hh:mm:ss a".
     *
     * @return the current date and time as a formatted string.
     */
    public static String getCurrentDateTime() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a").format(LocalDateTime.now());
    }

    /**
     * Gets the current date in the format "yyyy/MM/dd".
     *
     * @return the current date as a formatted string.
     */
    public static String getCurrentDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    /**
     * Gets the current time in the format "hh:mm:ss a".
     *
     * @return the current time as a formatted string.
     */
    public static String getCurrentTime() {
        return DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());
    }
}
