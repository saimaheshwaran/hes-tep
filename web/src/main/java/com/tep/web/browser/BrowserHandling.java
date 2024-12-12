package com.tep.web.browser;

import com.tep.utilities.PropUtils;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BrowserHandling class to handle browser navigation actions.
 */
public class BrowserHandling {

    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);
    private WebDriver driver;
    private PageObjects objects;

    /**
     * Constructor to initialize the BrowserHandling with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve URL values.
     */
    public BrowserHandling(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        logger.info("BrowserHandling initialized with WebDriver and PageObjects.");
    }

    /**
     * Constructor to initialize the BrowserHandling with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public BrowserHandling(WebDriver driver) {
        this.driver = driver;
        logger.info("BrowserHandling initialized with WebDriver.");
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url the URL to navigate to.
     */
    public void toUrl(String url) {
        logger.info("Navigating to URL:", url);
        driver.navigate().to(url);
    }

    /**
     * Navigates to the URL associated with the specified object name.
     *
     * @param objName the name of the object whose URL value is to be retrieved.
     */
    public void goTo(String objName) {
        logger.info("Navigating to URL for object '{}': {}", objName);
        driver.navigate().to(objects.get(objName, "value"));
    }

    /**
     * Navigates back to the previous page.
     */
    public void back() {
        logger.info("Navigating back to the previous page");
        driver.navigate().back();
    }

    /**
     * Navigates forward to the next page.
     */
    public void forward() {
        logger.info("Navigating forward to the next page");
        driver.navigate().forward();
    }

    /**
     * Refreshes the current page.
     */
    public void refresh() {
        logger.info("Refreshing the current page");
        driver.navigate().refresh();
    }
}
