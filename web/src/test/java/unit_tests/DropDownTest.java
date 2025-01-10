package unit_tests;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;
import pages.unitTestPages.W3Schools;

public class DropDownTest {
    WebAppDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        homePage = new HomePage(driver.getBrowser());
        searchPage = new SearchPage(driver.getBrowser());
        productPage = new ProductPage(driver.getBrowser());
        w3schools = new W3Schools(driver.getBrowser());
    }

    /* For Dropdown*/
    @Test
    public void selectDropDownSeleniumSingle() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumDropdown.select("opel", "value", w3schools.getDropdown());
    }

    @Test
    public void deSelectDropDownSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumDropdown.select("opel", "value", w3schools.getDropdown());
        driver.seleniumDropdown.deselect("opel", "value", w3schools.getDropdown());
    }

    @Test
    public void deSelectAllOptionInDropDownSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumDropdown.select("opel", "value", w3schools.getDropdown());
        driver.seleniumDropdown.select("Volvo", "text", w3schools.getDropdown());
        driver.seleniumDropdown.select("4", "index", w3schools.getDropdown());
        driver.seleniumDropdown.deselectAll(w3schools.getDropdown());
    }

    @Test
    public void selectDropDownJavaScriptExecutor() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptDropdown.select("opel", "value", w3schools.getDropdown());
        driver.javaScriptDropdown.deselect("opel", "value", w3schools.getDropdown());
    }

    @Test
    public void deSelectAllOptionInDropDownJavaScriptExecutor() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptDropdown.select("opel", "value", w3schools.getDropdown());
        driver.javaScriptDropdown.select("Audi", "text", w3schools.getDropdown());
        driver.javaScriptDropdown.select("2", "index", w3schools.getDropdown());
        driver.javaScriptDropdown.deselectAll(w3schools.getDropdown());
        driver.javaScriptDropdown.selectAll(w3schools.getDropdown());
        driver.javaScriptDropdown.deselectAll(w3schools.getDropdown());
    }

    @Test
    public void selectDropDownActionsClass() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionDropdown.select("2", "index", w3schools.getDropdown());
    }

    @Test
    public void selectDropDownMActionsClass() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionDropdown.select("2", "index", w3schools.getDropdown());
        driver.actionDropdown.deselect("2", "index", w3schools.getDropdown());
    }

    @Test
    public void selectDropDownMultipleOptionsActionsClass() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionDropdown.select("2", "index", w3schools.getDropdown());
        driver.actionDropdown.select("audi", "value", w3schools.getDropdown());
        driver.actionDropdown.select("Opel", "text", w3schools.getDropdown());
    }

    @Test
    public void deSelectAllOptionsInDropDownAction() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionDropdown.select("2", "index", w3schools.getDropdown());
        driver.actionDropdown.select("audi", "value", w3schools.getDropdown());
        driver.actionDropdown.select("Opel", "text", w3schools.getDropdown());
        driver.actionDropdown.deselectAll(w3schools.getDropdown());
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}