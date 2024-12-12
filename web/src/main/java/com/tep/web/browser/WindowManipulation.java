package com.tep.web.browser;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * WindowManipulation class to handle browser window manipulations and interactions.
 */
public class WindowManipulation {

    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);
    private WebDriver driver;
    private Waits waits;
    private Element element;
    private PageObjects objects;

    /**
     * Constructor to initialize the WindowManipulation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public WindowManipulation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WindowManipulation instance created with WebDriver");
    }

    /**
     * Constructor to initialize the WindowManipulation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public WindowManipulation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WindowManipulation instance created");
    }

    /**
     * Retrieves the appropriate key for the operating system.
     *
     * @return the control key (CTRL for Windows/Linux, COMMAND for Mac).
     */
    public Keys getKey() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win") || os.contains("nux") || os.contains("nix")) {
            logger.debug("Detected OS: {}, using CONTROL key for zooming", os);
            return Keys.CONTROL;
        } else if (os.contains("mac")) {
            logger.debug("Detected OS: Mac, using COMMAND key for zooming");
            return Keys.COMMAND;
        } else {
            logger.error("Unrecognized OS: {}, unable to determine the modifier key for zooming", os);
            return null;
        }
    }

    /**
     * Zooms in or out the browser window based on the specified action.
     *
     * @param inOut the action to perform (ADD to zoom in, SUBTRACT to zoom out, reset to reset zoom).
     */
    public void zoomInOut(String inOut) {
        WebElement element = driver.findElement(By.tagName("html"));
        if ("ADD".equalsIgnoreCase(inOut)) {
            logger.info("Zooming in");
            element.sendKeys(Keys.chord(getKey(), Keys.ADD));
        } else if ("SUBTRACT".equalsIgnoreCase(inOut)) {
            logger.info("Zooming out");
            element.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
        } else if ("reset".equalsIgnoreCase(inOut)) {
            logger.info("Resetting zoom");
            element.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
        } else {
            logger.warn("Invalid zoom command: {}", inOut);
        }
    }

    /**
     * Zooms in or out the browser window until the specified element is displayed.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param inOut   the action to perform (ADD to zoom in, SUBTRACT to zoom out).
     */
    public void zoomInOutTillElementDisplay(String objName, String inOut) {
        logger.info("Attempting to zoom '{}' until element '{}' is displayed", inOut, objName);
        zoomInOutTillElementDisplay(objects.get(objName), inOut);
    }

    /**
     * Zooms in or out the browser window until the specified element is displayed.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param inOut       the action to perform (ADD to zoom in, SUBTRACT to zoom out).
     */
    public void zoomInOutTillElementDisplay(Map.Entry<String, String> locatorPair, String inOut) {
        Actions action = new Actions(driver);
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
        while (true) {
            if (element.get(locatorPair).isDisplayed()) {
                logger.info("Element is now displayed with locator:", locatorPair);
                break;
            } else {
                logger.info("Zooming {} to display element with locator: {}", inOut, locatorPair);
                action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
            }
        }
    }

    /**
     * Resizes the browser window to the specified width and height.
     *
     * @param width  the width to resize to.
     * @param height the height to resize to.
     */
    public void resizeBrowser(int width, int height) {
        logger.info("Resizing browser to width: {} and height: {}", width, height);
        driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Maximizes the browser window.
     */
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    /**
     * Zooms in or out the browser window by the specified percentage value.
     *
     * @param value the percentage value to zoom in or out.
     */
    public void zoomInAndOut(String value) {
        logger.info("Zooming to {}%", value);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '" + value + "%'");
    }
}
