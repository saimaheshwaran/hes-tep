package com.tep.web.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.tep.web.config.Enums;
import com.tep.web.config.Constants;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Map;
import java.util.HashMap;
import java.time.Duration;

@NoArgsConstructor
public class SeleniumDriver {

    private WebDriver driver;

    @Getter
    private PageObjects pageObjects = new PageObjects();

    public SeleniumDriver(Enums.BrowserType browserType) {
        openBrowser(browserType);
    }

    public void openBrowser(Enums.BrowserType browserType) {

        if (driver != null)
            closeBrowser();

        if(browserType == Enums.BrowserType.DEFAULT)
            browserType = Enums.BrowserType.valueOf(Constants.BROWSER_TYPE);

        switch (browserType) {
            case CHROME -> initializeChromeBrowser();
            case FIREFOX -> initializeFirefoxBrowser();
            case EDGE -> initializeEdgeBrowser();
            case SAFARI -> initializeSafariBrowser();
            default -> driver = null;
        }

    }

    public WebDriver getBrowser() {
        return driver != null ? driver : null;
    }

    public void closeBrowser() {
        try {
            driver.close();
            if (Constants.BROWSER_QUIT) {
                driver.quit();
                driver = null;
            }
        } catch (Exception ignored) {
        }
    }

    public WebElement getElement(String objName) { return getElement(pageObjects.get(objName)); }

    public WebElement getElement(Map.Entry<String, String> locatorPair) { return getElement(locatorPair.getKey(), locatorPair.getValue()); }

    public WebElement getElement(String locator, String value) {
        WebElement element;
        switch (locator.toLowerCase()) {
            case "id" -> element = driver.findElement(By.id(value));
            case "name" -> element = driver.findElement(By.name(value));
            case "xpath" -> element = driver.findElement(By.xpath(value));
            case "css" -> element = driver.findElement(By.cssSelector(value));
            case "tagname" -> element = driver.findElement(By.tagName(value));
            case "linktest" -> element = driver.findElement(By.linkText(value));
            case "classname" -> element = driver.findElement(By.className(value));
            case "partiallinktest" -> element = driver.findElement(By.partialLinkText(value));
            default -> element = null;
        }
        return element;
    }

    public By getBy(String locator, String value) {
        By by;
        switch (locator.toLowerCase()) {
            case "id" -> by = By.id(value);
            case "name" -> by = By.name(value);
            case "xpath" -> by = By.xpath(value);
            case "css" -> by = By.cssSelector(value);
            case "tagname" -> by = By.tagName(value);
            case "linktest" -> by = By.linkText(value);
            case "classname" -> by = By.className(value);
            case "partiallinktest" -> by = By.partialLinkText(value);
            default -> by = null;
        }
        return by;
    }

    public void initializeChromeBrowser() {

        ChromeOptions options = new ChromeOptions();

        if (Constants.BROWSER_HEADLESS) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }

        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--remote-allow-origins=*");

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", chromePrefs);

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .withSilent(true).usingAnyFreePort().build();

        driver = new ChromeDriver(chromeDriverService, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME_SEC));
        if (Constants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();

    }

    public void initializeFirefoxBrowser() {

        FirefoxOptions options = new FirefoxOptions();

        if (Constants.BROWSER_HEADLESS) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-browser-side-navigation");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("pdfjs.disabled", true);
        options.setProfile(firefoxProfile);

        GeckoDriverService geckoDriverService = null;
        try {
            geckoDriverService = new GeckoDriverService.Builder().usingAnyFreePort().build();
        } catch (Exception ignored) {
        }

        assert geckoDriverService != null;
        driver = new FirefoxDriver(geckoDriverService, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME_SEC));
        if (Constants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();
    }

    public void initializeEdgeBrowser() {

        EdgeOptions options = new EdgeOptions();

        if (Constants.BROWSER_MAXIMIZE) {
            options.addArguments("--window-size=1920,1200");
        }

        if (Constants.BROWSER_HEADLESS) {
            options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
        } else {
            options.addArguments("--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
        }

        EdgeDriverService edgeDriverService = null;

        try {
            edgeDriverService = new EdgeDriverService.Builder().usingAnyFreePort().build();
        } catch (Exception ignored) {
        }

        try {
            assert edgeDriverService != null;
            driver = new EdgeDriver(edgeDriverService, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME_SEC));
            if (Constants.BROWSER_MAXIMIZE)
                driver.manage().window().maximize();
        } catch (Exception ignored) {
        }

    }

    public void initializeSafariBrowser() {

        SafariOptions options = new SafariOptions();
        driver = new SafariDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME_SEC));

        if (Constants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();
    }

}
