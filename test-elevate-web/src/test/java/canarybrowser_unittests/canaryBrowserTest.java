package canarybrowser_unittests;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.eymerchandise.HomePage;
import pages.unitTestPages.W3Schools;

public class canaryBrowserTest {

    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME_CANARY);
        w3schools = new W3Schools(driver.getBrowser());
    }

    @Test
    public void testUrlNavigation() {
        driver.browserEvent.goToUrl("https://www.w3schools.com");
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
        driver.browserEvent.goToUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_disabled");
    }

    @Test
    public void refreshTheBrowser() {
        driver.browserEvent.goToUrl("https://www.google.com/");
        driver.browserEvent.refresh();
    }

    @Test
    public void testTabSwitch() throws InterruptedException {
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.createNew("http://google.com");
        driver.tabEvent.createNew("https://www.w3schools.com");
        driver.tabEvent.switchTo("google");
        driver.tabEvent.switchToFirstTab();
    }

    @Test
    public void acceptTheAlert() {
        driver.browserEvent.goToUrl("http://demo.guru99.com/test/delete_customer.php");
        driver.seleniumClick.click(w3schools.getSubmitGURU99());
        System.out.println(driver.windowEvent.getAlertText());
        driver.windowEvent.handleAlert("accept");
    }

    @Test
    public void dismissTheAlert() {
        driver.browserEvent.goToUrl("http://demo.guru99.com/test/delete_customer.php");
        driver.seleniumClick.click(w3schools.getSubmitGURU99());
        System.out.println(driver.windowEvent.getAlertText());
        driver.windowEvent.handleAlert("dismiss");
    }

    @Test
    public void testverifypage_chromecanary() {
        HomePage homePage = new HomePage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.pageValidation.checkPageTitle("Search", true);
        driver.closeBrowser();
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}

