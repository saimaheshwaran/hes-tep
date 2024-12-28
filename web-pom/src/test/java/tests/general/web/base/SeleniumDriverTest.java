package tests.general.web.base;

import com.tep.web.base.SeleniumDriver;
import com.tep.web.config.Enums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumDriverTest {

    @Test
    public void chromeDriverInitializationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver();
        seleniumDriver.openBrowser(Enums.BrowserType.CHROME);
        Assertions.assertInstanceOf(ChromeDriver.class, seleniumDriver.getBrowser());
        seleniumDriver.closeBrowser();
    }

    @Test
    public void firefoxDriverInitializationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.FIREFOX);
        //driver.openBrowser(Enums.BrowserType.FIREFOX);
        Assertions.assertInstanceOf(FirefoxDriver.class, seleniumDriver.getBrowser());
        seleniumDriver.closeBrowser();
    }

    @Test
    public void safariDriverInitializationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver();
        seleniumDriver.openBrowser(Enums.BrowserType.SAFARI);
        Assertions.assertInstanceOf(SafariDriver.class, seleniumDriver.getBrowser());
        seleniumDriver.closeBrowser();
    }

    @Test
    public void edgeDriverInitializationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver();
        seleniumDriver.openBrowser(Enums.BrowserType.EDGE);
        Assertions.assertInstanceOf(EdgeDriver.class, seleniumDriver.getBrowser());
        seleniumDriver.closeBrowser();
    }

}
