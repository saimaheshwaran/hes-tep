package unit_tests.browser;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;

public class WindowManipulationTest {
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
    public void zoomInBroswer() {
        driver.windowManipulation.zoomInOut("120");
    }

    @Test
    public void resizeTheBrowser() {
        driver.windowManipulation.resizeBrowser(500, 500);
    }

    @Test
    public void maximizeTheBrowser() {
        driver.windowManipulation.maximizeBrowser();
    }
    @Test
    public void testZoomIn(){
        driver.windowManipulation.zoomInOut("ADD");
    }
    @Test
    public void testZoomOut(){
        driver.windowManipulation.zoomInOut("SUBTRACT");
    }
    @Test
    public void testZoomReset(){
        driver.windowManipulation.zoomInOut("reset");
    }
    @Test
    public void testZoomInOutTillElementDisplay(){
        driver.windowManipulation.zoomInOutTillElementDisplay(w3school.getCheckBox(),"ADD");
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }

}