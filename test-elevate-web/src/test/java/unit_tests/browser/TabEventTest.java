package unit_tests.browser;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabEventTest {
    WebAppDriver driver;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
    }

    @Test
    public void testTabSwitch() throws InterruptedException {
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.createNew("http://google.com");
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.switchTo("google");
        driver.tabEvent.switchToFirstTab();
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}