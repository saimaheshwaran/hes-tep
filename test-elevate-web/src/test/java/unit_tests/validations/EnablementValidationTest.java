package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class EnablementValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void verifyElementDisable() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_disabled");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.enablementValidation.verify(w3schools.getDisabledButton(), false);
    }

    @Test
    public void verifyElementEnable() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.enablementValidation.verify(w3schools.getEnabledButton(), true);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}