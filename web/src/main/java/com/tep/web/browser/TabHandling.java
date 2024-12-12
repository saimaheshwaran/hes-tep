package com.tep.web.browser;

import com.tep.utilities.PropUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * TabHandling class to handle browser tab operations.
 */
public class TabHandling {

    private WebDriver driver;
    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    /**
     * Constructor to initialize the TabHandling with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public TabHandling(WebDriver driver) {
        logger.info("TabHandling initialized with WebDriver.");
        this.driver = driver;
    }

    /**
     * Creates a new browser tab and navigates to the specified URL.
     *
     * @param url the URL to navigate to in the new tab.
     */
    public void createNew(String url) {
        String script = "window.open('about:blank','_blank');";
        ((JavascriptExecutor) driver).executeScript(script);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            logger.info("Switched to new tab with handle: {}", winHandle);
        }
        driver.get(url);
    }

    /**
     * Switches to the browser tab with the specified title.
     *
     * @param tabTitle the title of the tab to switch to.
     */
    public void switchTo(String tabTitle) {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            if (driver.getTitle().equalsIgnoreCase(tabTitle)) {
                logger.info("Switched to the tab with title: {}", tabTitle);
                break;
            }
        }
    }

    /**
     * Switches to the first browser tab.
     */
    public void switchToFirstTab() {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            logger.info("Switched to the first tab with handle: {}", handle);
            break;
        }
    }
}
