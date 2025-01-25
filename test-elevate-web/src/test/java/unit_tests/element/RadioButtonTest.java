package unit_tests.element;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class RadioButtonTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
      }

    @Test
    public void selectRadioButtonSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumRadioButton.select(w3schools.getHtml());
    }

    @Test
    public void selectRadioButtonJavaScript() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptRadioButton.select(w3schools.getHtml());
    }

    @Test
    public void selectRadioButtonAction() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionRadioButton.select(w3schools.getHtml());
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}
