package unit_tests.browser;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.unitTestPages.W3Schools;

public class BrowserEventTest {

    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
        }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void testNavigation() {
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
        driver.actionSendKeys.sendKeys(w3schools.getUsername(), "test");
        driver.actionSendKeys.sendKeys(w3schools.getPassword(), "test");

        //Click login
        driver.seleniumClick.click(w3schools.getLogin());

        driver.browserEvent.back();
        driver.browserEvent.forward();
    }

    @Test
    public void testUrlNavigation() {
        driver.browserEvent.goToUrl("https://www.w3schools.com");
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_disabled");
    }

    @Test
    public void refreshTheBrowser() {
        driver.browserEvent.goToUrl("https://www.google.com/");
        driver.browserEvent.refresh();
    }


    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}