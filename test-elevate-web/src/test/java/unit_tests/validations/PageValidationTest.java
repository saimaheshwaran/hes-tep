package unit_tests.validations;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class PageValidationTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void pageNotLoaded() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.isPageLoaded(1);
    }

    @Test
    public void PageLoaded() {
        driver.browserEvent.goToUrl("https://edition.cnn.com/");
        driver.seleniumSendKeys.sendKeys(w3schools.getBody(), "Keys.ESCAPE");
        driver.pageValidation.isPageLoaded(10);
        driver.seleniumSendKeys.sendKeys(w3schools.getBody(), "Keys.F5");
        driver.pageValidation.isPageLoaded(10);
    }

    @Test
    public void verifyPageTitle() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.checkPageTitle("W3Schools Tryit Editor", true);
    }

    @Test
    public void verifyPageTitleNegativeFlow() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.checkPageTitle("Tryit Editor ", false);
    }

    @Test
    public void verifyPartialPageTitle() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.checkPartialPageTitle("Tryit Editor", true);
    }

    @Test
    public void verifyPartialPageTitleNegativeFlow() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.checkPartialPageTitle("Try Editor", false);
    }

    @Test
    public void javaScriptWaitForPageToLoad() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.pageValidation.javaScriptWaitForPageToLoad(10, true);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}
