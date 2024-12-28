package com.tep.web.config;

import java.io.IOException;
import java.util.Properties;
import java.time.LocalDateTime;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.time.format.DateTimeFormatter;

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
    public static final String BROWSER_TYPE;
    public static final boolean REPORT_ENABLED;
    public static final boolean BROWSER_QUIT;
    public static final boolean BROWSER_HEADLESS;
    public static final boolean BROWSER_MAXIMIZE;
    public static final boolean STEPWISE_SCREENSHOT;
    public static final boolean DESKTOP_SCREENSHOT;
    public static final int DEFAULT_WAIT_TIME_SEC;
    public static final int IMPLICIT_WAIT_TIME_SEC;
    public static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(WEB_PROP_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public static String getCurrentDateTime() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a").format(LocalDateTime.now());
    }

    public static String getCurrentDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    public static String getCurrentTime() {
        return DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());
    }
}
