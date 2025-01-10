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

public class CheckBoxTest {
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

    @Test
    public void selectCheckBoxUsingSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumCheckBox.check(w3schools.getVehicle1());
        driver.seleniumCheckBox.check(w3schools.getVehicle1());
    }

    @Test
    public void selectCheckBoxUsingJavaScript() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
            driver.javaScriptCheckBox.check(w3schools.getVehicle1());
        driver.javaScriptCheckBox.uncheck(w3schools.getVehicle1());
    }

    @Test
    public void selectCheckBoxUsingAction() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionCheckBox.check(w3schools.getVehicle1());
        driver.actionCheckBox.uncheck(w3schools.getVehicle1());
    }

    @Test
    public void selectToggleButton() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_switch");
        driver.windowEvent.switchFrame(w3schools.getFrame());
       driver.actionCheckBox.toggle(w3schools.getToggle());
    }


    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}