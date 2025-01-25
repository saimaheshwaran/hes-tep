package com.tep.web.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.tep.web.config.WebEnums;
import com.tep.web.config.WebConstants;
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

/**
 * SeleniumDriver is a utility class for managing WebDriver instances for multiple browsers (Chrome, Firefox, Edge, Safari).
 * This class handles browser initialization, element search, and browser management tasks.
 */
@NoArgsConstructor
public class SeleniumDriver {

    /**
     * The WebDriver instance for the currently selected browser.
     */
    private WebDriver driver;

    /**
     * The PageObjects instance used to get locators for elements.
     */
    @Getter
    private PageObjects pageObjects = new PageObjects();

    /**
     * Constructor that initializes the browser based on the specified browser type.
     * @param browserType The browser type to initialize.
     */
    public SeleniumDriver(WebEnums.BrowserType browserType) {
        openBrowser(browserType);
    }

    /**
     * Opens a browser based on the specified browser type.
     * If the browser type is DEFAULT, it falls back to the default browser type defined in Constants.BROWSER_TYPE.
     * @param browserType The browser type to initialize.
     */
    public void openBrowser(WebEnums.BrowserType browserType) {

        if (driver != null)
            closeBrowser();

        if(browserType == WebEnums.BrowserType.DEFAULT)
            browserType = WebEnums.BrowserType.valueOf(WebConstants.BROWSER_TYPE);

        switch (browserType) {
            case CHROME -> initializeChromeBrowser();
            case FIREFOX -> initializeFirefoxBrowser();
            case EDGE -> initializeEdgeBrowser();
            case SAFARI -> initializeSafariBrowser();
            default -> driver = null;
        }

    }

    /**
     * Returns the current WebDriver instance.
     * @return The WebDriver instance.
     */
    public WebDriver getBrowser() {
        return driver != null ? driver : null;
    }

    /**
     * Closes the current browser and quits the WebDriver instance.
     * If Constants.BROWSER_QUIT is true, the browser will be fully closed (quit).
     */
    public void closeBrowser() {
        try {
            driver.close();
            if (WebConstants.BROWSER_QUIT) {
                driver.quit();
                driver = null;
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Retrieves a WebElement based on the object name from the PageObjects.
     * @param objName The object name defined in PageObjects.
     * @return The WebElement associated with the object name.
     */
    public WebElement getElement(String objName) {
        return getElement(pageObjects.get(objName));
    }

    /**
     * Retrieves a WebElement based on a Map entry containing the locator and value.
     * @param locatorPair A Map.Entry containing the locator type and its corresponding value.
     * @return The WebElement located by the specified locator and value.
     */
    public WebElement getElement(Map.Entry<String, String> locatorPair) {
        return getElement(locatorPair.getKey(), locatorPair.getValue());
    }

    /**
     * Retrieves a WebElement based on the specified locator type and value.
     * Supported locator types: "id", "name", "xpath", "css", "tagname", "linktext", "classname", "partiallinktext".
     * @param locator The locator type (e.g., "id", "xpath").
     * @param value The locator value (e.g., element's id or xpath expression).
     * @return The WebElement located by the specified locator and value.
     */
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

    /**
     * Extracts and returns the locator string of a given {@link WebElement}.
     * This method parses the {@code toString()} output of the WebElement to
     * isolate the locator details.
     *
     * @param element The WebElement whose locator string is to be retrieved.
     * @return A string representation of the locator, typically including the
     *         locator strategy (e.g., xpath, id, css) and its value.
     * @throws StringIndexOutOfBoundsException If the {@code toString()} format
     *         of the WebElement is not as expected.
     */
    public String getElementLocatorString(WebElement element) {
        String elementString = element.toString();
        return elementString.substring(elementString.indexOf(" -> ") + 4);
    }

    /**
     * Returns a By object for locating elements using the specified locator type and value.
     * @param locator The locator type (e.g., "id", "xpath").
     * @param value The locator value (e.g., element's id or xpath expression).
     * @return The By object used for locating the element.
     */
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

    /**
     * Initializes a Chrome browser with the specified options.
     * Configures headless mode, window size, and various Chrome options for automation.
     */
    public void initializeChromeBrowser() {

        ChromeOptions options = new ChromeOptions();

        if (WebConstants.BROWSER_HEADLESS) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebConstants.IMPLICIT_WAIT_TIME_SEC));
        if (WebConstants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();

    }


    /**
     * Initializes a Firefox browser with the specified options.
     * Configures headless mode, disables PDF viewer, and sets other Firefox preferences.
     */
    public void initializeFirefoxBrowser() {

        FirefoxOptions options = new FirefoxOptions();

        if (WebConstants.BROWSER_HEADLESS) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-browser-side-navigation");
        options.setCapability("se:Bidi", true);

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebConstants.IMPLICIT_WAIT_TIME_SEC));
        if (WebConstants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();
    }

    /**
     * Initializes an Edge browser with the specified options.
     * Configures headless mode, window size, and other Edge options.
     */
    public void initializeEdgeBrowser() {

        EdgeOptions options = new EdgeOptions();

        if (WebConstants.BROWSER_MAXIMIZE) {
            options.addArguments("--window-size=1920,1200");
        }

        if (WebConstants.BROWSER_HEADLESS) {
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
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebConstants.IMPLICIT_WAIT_TIME_SEC));
            if (WebConstants.BROWSER_MAXIMIZE)
                driver.manage().window().maximize();
        } catch (Exception ignored) {
        }

    }

    /**
     * Initializes a Safari browser with the specified options.
     * Configures the SafariDriver and sets implicit wait time.
     */
    public void initializeSafariBrowser() {

        SafariOptions options = new SafariOptions();
        driver = new SafariDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebConstants.IMPLICIT_WAIT_TIME_SEC));

        if (WebConstants.BROWSER_MAXIMIZE)
            driver.manage().window().maximize();
    }

}
