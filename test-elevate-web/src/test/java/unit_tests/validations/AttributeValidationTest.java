package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class AttributeValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void checkElementAttributeMatched() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.attributeValidation.verify(w3schools.getVehicle1(), "value", "Bike", true);
    }

    @Test
    public void checkElementAttributeNotMatched() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.attributeValidation.verify(w3schools.getVehicle1(), "value", "car", true);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}