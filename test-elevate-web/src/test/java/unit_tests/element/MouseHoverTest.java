package unit_tests.element;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class MouseHoverTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
    }

    @Test
    public void mouseHoverByActionClass() {
       driver.actionMouseHover.mouseHover(w3schools.getUsername());
        driver.actionMouseHover.mouseHover(w3schools.getPassword());
           }

    @Test
    public void mouseHoverByJavaScriptExecutor() {
        driver.javaScriptMouseHover.mouseHover(w3schools.getUsername());
        driver.javaScriptMouseHover.mouseHover(w3schools.getPassword());
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}