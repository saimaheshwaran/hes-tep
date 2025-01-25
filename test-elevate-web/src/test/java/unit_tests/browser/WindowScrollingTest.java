package unit_tests.browser;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.unitTestPages.W3Schools;

public class WindowScrollingTest {
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
    public void testScrollEndTop() throws InterruptedException {
        driver.browserEvent.goToUrl("https://www.w3schools.com");
        driver.windowScrolling.scrollVerticalPixels(2000);
        Thread.sleep(3000);
        driver.windowScrolling.scrollToTop();
        Thread.sleep(3000);
        driver.windowScrolling.scrollToEnd();
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void testScrolltoElement() {
        driver.seleniumSendKeys.sendKeys(w3school.getUsername(), "test");
        driver.seleniumSendKeys.sendKeys(w3school.getPassword(), "test");
        driver.seleniumClick.click(w3school.getLogin());
        driver.windowScrolling.scrollToElement(w3school.getTableData());
    }

    @Test
    public void testScrollHorizontalPixels() {
        driver.browserEvent.goToUrl("https://www.w3schools.com");
         driver.windowScrolling.scrollHorizontalPixels(100);
    }


    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}