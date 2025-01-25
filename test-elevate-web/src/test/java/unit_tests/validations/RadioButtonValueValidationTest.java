package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class RadioButtonValueValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void radioButtonIsSelected() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumRadioButton.select(w3schools.getHtml());
        driver.radioButtonValidation.isSelected(w3schools.getHtml(), true);
    }

    @Test
    public void radioButtonIsNotSelected() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.radioButtonValidation.isSelected(w3schools.getHtml(), false);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}