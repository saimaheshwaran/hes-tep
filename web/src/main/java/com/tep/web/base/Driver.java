package com.tep.web.base;

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

import java.util.HashMap;

@Data
public class Driver {

    private WebDriver driver;

    public Driver(Enums.BrowserType browser) {

        if (driver != null) close();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("takesScreenshot", true);

        switch(browser) {

            case CHROME -> {

                ChromeOptions chromeOptions = new ChromeOptions();

                if (Constants.BROWSER_HEADLESS) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }

                if(Constants.BROWSER_MAXIMIZE)
                    chromeOptions.addArguments("start-maximized");

                chromeOptions.addArguments("enable-automation");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-browser-side-navigation");
                chromeOptions.addArguments("--remote-allow-origins=*");

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                chromeOptions.merge(capabilities);

                ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                        .withSilent(true).usingAnyFreePort().build();

                driver = new ChromeDriver(chromeDriverService, chromeOptions);

            }

            case FIREFOX -> {

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (Constants.BROWSER_HEADLESS) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");
                }

                firefoxOptions.addArguments("--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
                firefoxOptions.addArguments("--disable-infobars", "--disable-browser-side-navigation");

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("pdfjs.disabled", true);

                firefoxOptions.setProfile(firefoxProfile);
                firefoxOptions.merge(capabilities);

                GeckoDriverService geckoDriverService = null;
                try { geckoDriverService = new GeckoDriverService.Builder().usingAnyFreePort().build();
                } catch (Exception ignored) {}

                driver = new FirefoxDriver(geckoDriverService, firefoxOptions);

                if(Constants.BROWSER_MAXIMIZE)
                    driver.manage().window().maximize();

            }

            case EDGE -> {

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

                if(Constants.BROWSER_MAXIMIZE)
                    edgeOptions.addArguments("--window-size=1920,1200");

                if(Constants.BROWSER_HEADLESS)
                    edgeOptions.addArguments("--headless", "--disable-gpu","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
                else
                    edgeOptions.addArguments("--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");

                edgeOptions.merge(capabilities);

                EdgeDriverService edgeDriverService = null;
                try { edgeDriverService = new EdgeDriverService.Builder().usingAnyFreePort().build(); }
                catch(Exception Ignored) {}

                driver = new EdgeDriver(edgeDriverService, edgeOptions);

            }

            case SAFARI -> {
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                if(Constants.BROWSER_MAXIMIZE)
                    driver.manage().window().maximize();
            }

            default -> driver = null;

        }
    }

    public void close() {
        try {
            driver.close();
            if (Constants.BROWSER_QUIT) {
                driver.quit();
                driver = null;
            }
        } catch (Exception ignored) {}
    }

}
