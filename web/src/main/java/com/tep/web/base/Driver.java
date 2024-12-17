package com.tep.web.base;

import com.tep.utilities.PropUtils;
import com.tep.web.config.Constants;
import com.tep.web.config.Enums;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Driver class to initialize and manage WebDriver instances for different browsers.
 */
@Data
public class Driver {

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    PropUtils webProps = new PropUtils(Constants.WEB_PROP_PATH);
    private WebDriver driver;

    /**
     * Constructor to initialize the WebDriver based on the specified browser type.
     */
    public Driver() {
    }

    /**
     * Opens the WebDriver instance.
     */
    public void open(Enums.BrowserType browser) {

        if (driver != null) {
            logger.info("Existing WebDriver instance detected. Closing the current driver.");
            close();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("takesScreenshot", true);
        logger.info("Desired capabilities set: {}", capabilities);

        if(browser == Enums.BrowserType.DEFAULT) browser = Enums.BrowserType.valueOf(webProps.get("browser").toUpperCase());

        switch (browser) {
            case CHROME -> initializeChromeDriver(capabilities);
            case FIREFOX -> initializeFirefoxDriver(capabilities);
            case EDGE -> initializeEdgeDriver(capabilities);
            case SAFARI -> initializeSafariDriver();
            default -> driver = null;
        }
        logger.info(browser + " Browser initialization complete.");

    }

    /**
     * Initializes the Chrome WebDriver with the specified capabilities.
     *
     * @param capabilities the desired capabilities for the Chrome WebDriver.
     */
    private void initializeChromeDriver(DesiredCapabilities capabilities) {
        logger.info("Initializing Chrome Driver...");
        ChromeOptions chromeOptions = new ChromeOptions();

        if (Constants.BROWSER_HEADLESS) {
            logger.info("Running Chrome in headless mode.");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
        }

        if (Constants.BROWSER_MAXIMIZE) {
            logger.info("Maximizing Chrome window on start.");
            chromeOptions.addArguments("start-maximized");
        }
        logger.info("Adding Chrome arguments for automation, sandbox, infobars and other settings.");
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-browser-side-navigation");
        chromeOptions.addArguments("--remote-allow-origins=*");

        logger.info("Setting Chrome preferences for popups.");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        chromeOptions.merge(capabilities);

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .withSilent(true).usingAnyFreePort().build();

        driver = new ChromeDriver(chromeDriverService, chromeOptions);
        if (Constants.BROWSER_MAXIMIZE) {
            driver.manage().window().maximize();
            logger.info("ChromeDriver window maximized.");
        }
        logger.info("ChromeDriver initialized successfully.");
    }

    /**
     * Initializes the Firefox WebDriver with the specified capabilities.
     *
     * @param capabilities the desired capabilities for the Firefox WebDriver.
     */
    private void initializeFirefoxDriver(DesiredCapabilities capabilities) {
        logger.info("Initializing Firefox Driver...");
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (Constants.BROWSER_HEADLESS) {
            logger.info("Running Firefox in headless mode.");
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--disable-gpu");
        }
        logger.info("Adding Firefox arguments to disable certificate errors, extensions, and more.");
        firefoxOptions.addArguments("--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
        firefoxOptions.addArguments("--disable-infobars", "--disable-browser-side-navigation");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("pdfjs.disabled", true);

        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.merge(capabilities);

        GeckoDriverService geckoDriverService = null;
        try {
            geckoDriverService = new GeckoDriverService.Builder().usingAnyFreePort().build();
        } catch (Exception ignored) {
            logger.error("Failed to start GeckoDriverService: {}", ignored.getMessage());
        }

        driver = new FirefoxDriver(geckoDriverService, firefoxOptions);
        logger.info("FirefoxDriver initialized successfully.");

        if (Constants.BROWSER_MAXIMIZE) {
            logger.info("Maximizing Firefox browser window.");
            driver.manage().window().maximize();
        }
    }

    /**
     * Initializes the Edge WebDriver with the specified capabilities.
     *
     * @param capabilities the desired capabilities for the Edge WebDriver.
     */
    private void initializeEdgeDriver(DesiredCapabilities capabilities) {
        logger.info("Initializing Edge WebDriver.");
        EdgeOptions edgeOptions = new EdgeOptions();

        edgeOptions.setCapability("requireWindowFocus", true);
        edgeOptions.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
        edgeOptions.setCapability("ignoreProtectedModeSettings", true);
        edgeOptions.setCapability("ignoreZoomSetting", true);
        edgeOptions.setCapability("initialBrowserUrl", "http://www.google.com");
        edgeOptions.setCapability("enablePersistentHover", true);
        edgeOptions.setCapability("enableElementCacheCleanup", true);
        edgeOptions.setCapability("browserAttachTimeout", 0);
        edgeOptions.setCapability("edge.ensureCleanSession", true);
        if (Constants.BROWSER_MAXIMIZE) {
            edgeOptions.addArguments("--window-size=1920,1200");
            logger.info("Browser window maximized with size: 1920x1200.");
        }
        if (Constants.BROWSER_HEADLESS) {
            edgeOptions.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
            logger.info("Running in headless mode.");
        } else {
            edgeOptions.addArguments("--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
            logger.info("Running in normal mode (headless is off).");
        }

        edgeOptions.merge(capabilities);
        logger.info("Additional capabilities merged with Edge options.");

        EdgeDriverService edgeDriverService = null;
        try {
            edgeDriverService = new EdgeDriverService.Builder().usingAnyFreePort().build();
            logger.info("EdgeDriverService initialized successfully.");
        } catch (Exception ignored) {
            logger.error("Failed to initialize EdgeDriverService.", ignored);
        }
        try {
            driver = new EdgeDriver(edgeDriverService, edgeOptions);

            if (Constants.BROWSER_MAXIMIZE) {
                driver.manage().window().maximize();
                logger.info("Edge browser window maximized.");
            }
        } catch (Exception e) {
            logger.error("Error initializing EdgeDriver.", e);
        }

    }

    /**
     * Initializes the Safari WebDriver.
     */
    private void initializeSafariDriver() {
        logger.debug("Initializing SafariDriver.");
        SafariOptions safariOptions = new SafariOptions();
        driver = new SafariDriver(safariOptions);
        logger.info("SafariDriver initialized successfully.");

        if (Constants.BROWSER_MAXIMIZE) {
            driver.manage().window().maximize();
            logger.info("Browser window maximized.");
        }
    }

    /**
     * Closes the WebDriver instance.
     */
    public void close() {
        try {
            driver.close();
            logger.info("Browser window closed.");
            if (Constants.BROWSER_QUIT) {
                driver.quit();
                driver = null;
                logger.info("Browser quit and driver instance set to null.");
            }
        } catch (Exception ignored) {
            logger.error("An error occurred while closing the browser: ", ignored);
        }
    }
}
