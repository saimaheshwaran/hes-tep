package unit_tests.browser;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class WindowEventTest {
    WebAppDriver driver;
    W3Schools w3school;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3school = new W3Schools(driver.getBrowser());
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
    }

    @Test
    public void acceptTheAlert() {
        driver.browserEvent.goToUrl("http://demo.guru99.com/test/delete_customer.php");
        driver.seleniumClick.click(w3school.getSubmitGURU99());
        System.out.println(driver.windowEvent.getAlertText());
        driver.windowEvent.handleAlert("accept");
    }

    @Test
    public void dismissTheAlert() {
        driver.browserEvent.goToUrl("http://demo.guru99.com/test/delete_customer.php");
        driver.seleniumClick.click(w3school.getSubmitGURU99());
        System.out.println(driver.windowEvent.getAlertText());
        driver.windowEvent.handleAlert("dismiss");
    }

    @Test
    public void switchToNewTabAndComeBackToOldTab()  {
        driver.browserEvent.goToUrl("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
        driver.seleniumClick.click(w3school.getWindow1());
        driver.windowEvent.switchToNewTab();
        driver.seleniumSendKeys.sendKeys(w3school.getSearch(), "Testing");
        driver.windowEvent.switchToOldTab();
           }

    @Test
    public void switchToNewTabbasedOnTitle() throws Exception {
        driver.browserEvent.goToUrl("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
        driver.seleniumClick.click(w3school.getWindow1());
        driver.windowEvent.switchToWindowByTitle("Google");
        driver.seleniumSendKeys.sendKeys(w3school.getSearch(), "Testing");
        driver.windowEvent.refreshPage();
        driver.windowEvent.switchToOldTab();
            }

    @Test
    public void switchToNewWindowAndComeBackToOldWindow() {
        driver.browserEvent.goToUrl("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
        driver.seleniumClick.click(w3school.getWindow2());
        driver.windowEvent.switchToNewWindow();
        driver.seleniumSendKeys.sendKeys(w3school.getSearch(), "Testing");
        driver.windowEvent.switchToOldWindow();
    }

    @Test
    public void switchFrame() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3school.getFrame());
        driver.seleniumClick.click(w3school.getVehicle1());
        driver.windowEvent.switchToDefaultContent();
        driver.seleniumClick.click(w3school.getMenu());
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }

}