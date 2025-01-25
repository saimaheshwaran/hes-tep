package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class DropDownValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void dropDownValueSelected() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_elem_select");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.dropdownValidation.isSelected(w3schools.getDropdown(), "value", "volvo", true);
    }

    @Test
    public void dropDownValueNotSelected() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_elem_select");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.dropdownValidation.isSelected(w3schools.getDropdown(), "value", "Audi", false);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}