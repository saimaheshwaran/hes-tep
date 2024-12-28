package tests.general.web.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.file.FileSystems;

import static com.tep.web.config.Constants.*;

public class ConstantsTest {

    @Test
    public void checkConstants() {
        Assertions.assertEquals(BASE_PATH, System.getProperty("user.dir"));
        Assertions.assertEquals(FILE_SEPARATOR,  FileSystems.getDefault().getSeparator());
        Assertions.assertEquals(MAIN_PATH, BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main");
        Assertions.assertEquals(TEST_PATH, BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test");
        Assertions.assertEquals(TARGET_PATH, BASE_PATH + FILE_SEPARATOR + "target");
        Assertions.assertEquals(WEB_PROP_PATH, MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties");
        Assertions.assertEquals(TEST_APP_PATH,TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + PROPERTIES.getProperty("project.name"));
        Assertions.assertEquals(TEST_APP_FEATURE_PATH, TEST_APP_PATH + FILE_SEPARATOR + "features");
        Assertions.assertEquals(TEST_DATA_INPUT_PATH, TEST_APP_PATH + FILE_SEPARATOR + "input" + FILE_SEPARATOR + "web" + FILE_SEPARATOR);
        Assertions.assertEquals(TEST_DATA_OUTPUT_PATH, TARGET_PATH + FILE_SEPARATOR + "output" + FILE_SEPARATOR + "web" + FILE_SEPARATOR);;
        Assertions.assertEquals(REPORT_ENABLED, PROPERTIES.getProperty("reports").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_QUIT, PROPERTIES.getProperty("browser.quit").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_HEADLESS, PROPERTIES.getProperty("browser.headless").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_MAXIMIZE, PROPERTIES.getProperty("browser.maximize").equalsIgnoreCase("true"));
        Assertions.assertEquals(STEPWISE_SCREENSHOT, PROPERTIES.getProperty("stepwise.screenshot").equalsIgnoreCase("true"));
        Assertions.assertEquals(DESKTOP_SCREENSHOT, PROPERTIES.getProperty("desktop.screenshot").equalsIgnoreCase("true"));
        Assertions.assertEquals(PAGE_OBJECT_TYPE, PROPERTIES.getProperty("page.object.type").toLowerCase());
        Assertions.assertEquals(DEFAULT_WAIT_TIME_SEC, Integer.parseInt(PROPERTIES.getProperty("default.wait.time.sec")));
        Assertions.assertEquals(IMPLICIT_WAIT_TIME_SEC, Integer.parseInt(PROPERTIES.getProperty("implicit.wait.time.sec")));
    }
}
