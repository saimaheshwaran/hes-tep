package com.tep.web.browser;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * WindowScrolling class to handle browser window scrolling operations.
 */
public class WindowScrolling {

    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);
    private WebDriver driver;
    private PageObjects objects;
    private Waits waits;
    private Element element;
    private JavascriptExecutor js;

    /**
     * Constructor to initialize the WindowScrolling with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public WindowScrolling(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.js = (JavascriptExecutor) driver;
        logger.info("WindowScrolling object created with the provided WebDriver instance.");
    }

    /**
     * Constructor to initialize the WindowScrolling with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public WindowScrolling(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.js = (JavascriptExecutor) driver;
        logger.info("WindowScrolling object created with provided WebDriver and PageObjects.");
    }

    /**
     * Scrolls to the end of the page.
     */
    public void scrollToEnd() {
        logger.info("Scrolling to the end of the page");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scrolls to the top of the page.
     */
    public void scrollToTop() {
        logger.info("Scrolling to the top of the page");
        js.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scrolls to the specified element by its object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void scrollToElement(String objName) {
        logger.info("Scrolling to element with object name: {}", objName);
        scrollToElement(objects.get(objName));
    }

    /**
     * Scrolls to the specified element by its locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void scrollToElement(Map.Entry<String, String> locatorPair) {
        logger.info("Scrolling to element with locator: {}", locatorPair);
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
        js.executeScript("arguments[0].scrollIntoView();", element.get(locatorPair));
    }

    /**
     * Scrolls vertically by the specified number of pixels.
     *
     * @param pixelsToScroll the number of pixels to scroll vertically.
     */
    public void scrollVerticalPixels(int pixelsToScroll) {
        logger.info("Scrolling vertically by {} pixels", pixelsToScroll);
        js.executeScript("window.scrollBy(0," + pixelsToScroll + ")");
    }

    /**
     * Scrolls horizontally by the specified number of pixels.
     *
     * @param pixelsToScroll the number of pixels to scroll horizontally.
     */
    public void scrollHorizontalPixels(int pixelsToScroll) {
        logger.info("Scrolling horizontally by {} pixels", pixelsToScroll);
        js.executeScript("window.scrollBy(" + pixelsToScroll + ", 0)");
    }
}
