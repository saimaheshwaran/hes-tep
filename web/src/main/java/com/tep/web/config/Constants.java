package com.tep.web.config;

import com.tep.utilities.PropUtils;

import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {

    public static String BASE_PATH = System.getProperty("user.dir");
    public static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static String MAIN_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main";
    public static String TEST_PATH = BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test";
    public static String TARGET_PATH = BASE_PATH + FILE_SEPARATOR + "target";
    public static String WEB_PROP_PATH = MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties";
    public static String TEST_APP_PATH;
    public static String TEST_APP_FEATURE_PATH;
    public static String TEST_DATA_INPUT_PATH;
    public static String TEST_DATA_OUTPUT_PATH;
    public static String PAGE_OBJECT_TYPE;
    public static boolean REPORT_ENABLED;
    public static boolean BROWSER_QUIT;
    public static boolean BROWSER_HEADLESS;
    public static boolean BROWSER_MAXIMIZE;
    public static boolean STEPWISE_SCREENSHOT;
    public static boolean DESKTOP_SCREENSHOT;
    public static int DEFAULT_WAIT_TIME_SEC;
    public static int IMPLICIT_WAIT_TIME_SEC;
    public static PropUtils Config;

    static {
        Config = new PropUtils(MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties");
        TEST_APP_PATH = TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + Config.get("project.name");
        TEST_APP_FEATURE_PATH = "src/test/resources/" + Config.get("application");
        TEST_DATA_INPUT_PATH = TEST_APP_PATH + FILE_SEPARATOR + "input" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        TEST_DATA_OUTPUT_PATH = TEST_APP_PATH + FILE_SEPARATOR + "output" + FILE_SEPARATOR + "web" + FILE_SEPARATOR;
        REPORT_ENABLED = Config.get("reports").equalsIgnoreCase("true");
        BROWSER_QUIT = Config.get("browser.quit").equalsIgnoreCase( "true");
        BROWSER_HEADLESS = Config.get("browser.headless").equalsIgnoreCase( "true");
        BROWSER_MAXIMIZE = Config.get("browser.maximize").equalsIgnoreCase( "true");
        STEPWISE_SCREENSHOT = Config.get("stepwise.screenshot").equalsIgnoreCase("true");
        DESKTOP_SCREENSHOT = Config.get("desktop.screenshot").equalsIgnoreCase("true");
        PAGE_OBJECT_TYPE = Config.get("page.object.type").toLowerCase();
        DEFAULT_WAIT_TIME_SEC = Integer.parseInt(Config.get("default.wait.time.sec"));
        IMPLICIT_WAIT_TIME_SEC = Integer.parseInt(Config.get("implicit.wait.time.sec"));
    }

    public static String CURRENT_DATETIME() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a").format(LocalDateTime.now());
    }

    public static String CURRENT_DATE() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    public static String CURRENT_TIME() {
        return DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());
    }

}


