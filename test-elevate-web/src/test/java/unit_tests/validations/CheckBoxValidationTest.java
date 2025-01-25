package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class CheckBoxValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void validateCheckBoxIsChecked() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumCheckBox.check(w3schools.getVehicle1());
        driver.checkBoxValidation.isChecked(w3schools.getVehicle1(), true);
    }

    @Test
    public void validateCheckBoxIsCheckedNegative() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.checkBoxValidation.isChecked(w3schools.getVehicle1(), false);
    }

    @Test
    public void validateCheckBoxIsUnChecked() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.checkBoxValidation.isChecked(w3schools.getVehicle1(), false);
    }

    @Test
    public void validateCheckBoxIsUnCheckedNegative() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumCheckBox.check(w3schools.getVehicle1());
        driver.checkBoxValidation.isChecked(w3schools.getVehicle1(), true);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}