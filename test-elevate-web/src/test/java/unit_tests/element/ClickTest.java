package unit_tests.element;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;
import pages.unitTestPages.W3Schools;

public class ClickTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
    }


    @Test
    public void clickSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_onclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumClick.click(w3schools.getClickButton());
    }

    @Test
    public void clickJavaScript() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_onclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptClick.click(w3schools.getClickButton());
    }

    @Test
    public void clickAction() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_onclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionClick.click(w3schools.getClickButton());
    }

    @Test
    public void doubleClickSelenium() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_ondblclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.seleniumClick.doubleClick(w3schools.getDoubleClickButton());
    }

    @Test
    public void doubleClickAction() {
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_ondblclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.actionClick.doubleClick(w3schools.getDoubleClickButton());
    }

    @Test
    public void testJavaScriptDoubleClickAction(){
        driver.browserEvent.goToUrl("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_ondblclick");
        driver.windowEvent.switchFrame(w3schools.getFrame());
        driver.javaScriptClick.doubleClick(w3schools.getDoubleClickButton());
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }

}