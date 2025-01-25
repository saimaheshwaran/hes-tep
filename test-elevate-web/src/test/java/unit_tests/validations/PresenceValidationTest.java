package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class PresenceValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
    }

    @Test
    public void verifyElementPresent() {
        driver.presenceValidation.verify(w3schools.getLogin(), true);
    }

    @Test
    public void verifyElementNotPresent() {
        driver.presenceValidation.verify(w3schools.getLogin(), false);
    }

    @Test
    public void verifyElementPresentWithTime() {
        driver.presenceValidation.verify(w3schools.getLogin(), true, 10);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}