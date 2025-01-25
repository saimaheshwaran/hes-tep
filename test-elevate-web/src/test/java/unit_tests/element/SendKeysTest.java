package unit_tests.element;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import pages.unitTestPages.W3Schools;


public class SendKeysTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }


    @Test
    public void sendKeysSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumSendKeys.sendKeys(w3schools.getFirstName(), "Testing");
        driver.seleniumSendKeys.clearInputs(w3schools.getFirstName());
    }

    @Test
    public void sendKeysActions() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionSendKeys.sendKeys(w3schools.getFirstName(), "testing");
    }

    @Test
    public void sendKeysJavaScript() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptSendKeys.sendKeys(w3schools.getFirstName(), "testing");
    }


    @Test
    public void enterKey() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionSendKeys.enterKeys(w3schools.getSubmit(), Keys.ENTER);
    }


    @Test
    public void testEnterKeyCombinations() throws Exception {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        String[] str = new String[]{"SHIFT", "n"};
        driver.actionSendKeys.enterKeyCombinations(w3schools.getFirstName(), str);
    }

    @Test
    public void testHitKeyCombinations() throws Exception {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_test");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        String[] str = new String[]{"ALT", "TAB"};
        driver.actionSendKeys.hitKeyCombinations(str);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}