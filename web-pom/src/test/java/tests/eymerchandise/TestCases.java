package tests.eymerchandise;

import com.tep.web.WebAppDriver;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.browser.BrowserEvent;
import com.tep.web.config.Enums;
import com.tep.web.element.click.SeleniumClick;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.SearchPage;
import pages.eymerchandise.ProductPage;

import java.nio.file.FileSystems;

import static com.tep.web.config.Constants.*;
import static com.tep.web.config.Constants.PROPERTIES;

public class TestCases {

    @Test
    public void testPo1() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(Enums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());

        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Pen");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductPen());
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);

        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }

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

    @Test
    public void waitUntilElementDisplayedTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        WebDriver webDriver =  seleniumDriver.getBrowser();
        webDriver.navigate().to("https://ey.corpmerchandise.com");
        WebElement element = webDriver.findElement(By.id("searchTextBox"));
        seleniumWaits.untilElementDisplayed(element);
        element.sendKeys("Pen");
        seleniumWaits.sleep(3);
        seleniumDriver.closeBrowser();
    }

    @Test
    public void goToTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

    @Test
    public void navigationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")).click();
        seleniumWaits.sleep(2); browserEvent.back();
        seleniumWaits.sleep(2); browserEvent.forward();
        seleniumWaits.sleep(2); browserEvent.refresh();
        seleniumDriver.closeBrowser();
    }

    @Test
    public void checkConstants() {
        Assertions.assertEquals(BASE_PATH, System.getProperty("user.dir"));
        Assertions.assertEquals(FILE_SEPARATOR,  FileSystems.getDefault().getSeparator());
        Assertions.assertEquals(MAIN_PATH, BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main");
        Assertions.assertEquals(TEST_PATH, BASE_PATH + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test");
        Assertions.assertEquals(TARGET_PATH, BASE_PATH + FILE_SEPARATOR + "target");
        Assertions.assertEquals(WEB_PROP_PATH, MAIN_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "web.properties");
        Assertions.assertEquals(TEST_APP_PATH,TEST_PATH + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + PROPERTIES.getProperty("project.name"));
        Assertions.assertEquals(TEST_APP_FEATURE_PATH, TEST_APP_PATH + FILE_SEPARATOR + "features");
        Assertions.assertEquals(TEST_DATA_INPUT_PATH, TEST_APP_PATH + FILE_SEPARATOR + "input" + FILE_SEPARATOR + "web" + FILE_SEPARATOR);
        Assertions.assertEquals(TEST_DATA_OUTPUT_PATH, TARGET_PATH + FILE_SEPARATOR + "output" + FILE_SEPARATOR + "web" + FILE_SEPARATOR);;
        Assertions.assertEquals(REPORT_ENABLED, PROPERTIES.getProperty("reports").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_QUIT, PROPERTIES.getProperty("browser.quit").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_HEADLESS, PROPERTIES.getProperty("browser.headless").equalsIgnoreCase("true"));
        Assertions.assertEquals(BROWSER_MAXIMIZE, PROPERTIES.getProperty("browser.maximize").equalsIgnoreCase("true"));
        Assertions.assertEquals(STEPWISE_SCREENSHOT, PROPERTIES.getProperty("stepwise.screenshot").equalsIgnoreCase("true"));
        Assertions.assertEquals(DESKTOP_SCREENSHOT, PROPERTIES.getProperty("desktop.screenshot").equalsIgnoreCase("true"));
        Assertions.assertEquals(PAGE_OBJECT_TYPE, PROPERTIES.getProperty("page.object.type").toLowerCase());
        Assertions.assertEquals(DEFAULT_WAIT_TIME_SEC, Integer.parseInt(PROPERTIES.getProperty("default.wait.time.sec")));
        Assertions.assertEquals(IMPLICIT_WAIT_TIME_SEC, Integer.parseInt(PROPERTIES.getProperty("implicit.wait.time.sec")));
    }

    @Test
    public void singleClickTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumClick seleniumClick = new SeleniumClick(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumClick.click(seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")));
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

    //@Test
    public void doubleClickTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumClick seleniumClick = new SeleniumClick(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumClick.doubleClick(seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")));
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

}
