package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class TextValidationTest {
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
    public void verifyTextMatch() {
        driver.textValidation.isMatching(w3schools.getLogin(), "Log In", true);
    }

    @Test
    public void verifyPartialTextMismatch() {
        driver.textValidation.isMatching(w3schools.getLogin(), "Log Out", false);
    }

    @Test
    public void verifyPartialTextMatch() {
        driver.textValidation.isPartiallyMatching(w3schools.getLogin(), "Log", true);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}