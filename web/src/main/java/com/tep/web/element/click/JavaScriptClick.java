package com.tep.web.element.click;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * JavaScriptClick class to handle click actions on web elements using JavaScript.
 */
public class JavaScriptClick {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptClick.class);

    /**
     * Constructor to initialize the JavaScriptClick with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptClick(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WebDriver, Waits, and Element instances have been initialized.");
    }

    /**
     * Constructor to initialize the JavaScriptClick with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptClick(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WebDriver, PageObjects, Waits, and Element instances have been initialized.");
    }

    /**
     * Clicks on the element identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void click(String objName) {
        click(objects.get(objName));
    }

    /**
     * Double-clicks on the element identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void doubleClick(String objName) {
        doubleClick(objects.get(objName));
    }

    /**
     * Clicks on the element identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void click(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if (element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
                logger.info("Element is enabled, executed JavaScript for click action.");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught, retrying click.", e);
            click(locatorPair);
        }
    }

    /**
     * Double-clicks on the element identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void doubleClick(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if (element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click(); arguments[0].click();", element);
                logger.info("Element is enabled, executed JavaScript for double click.");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught, retrying click.", e);
            doubleClick(locatorPair);
        }
    }

    public void click(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            if (webElement.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);
                logger.info("Element is enabled, executed JavaScript for click action.");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught, retrying click.", e);
            click(webElement);
        }
    }

    public void doubleClick(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            if (webElement.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click(); arguments[0].click();", webElement);
                logger.info("Element is enabled, executed JavaScript for double click.");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught, retrying click.", e);
            doubleClick(webElement);
        }
    }

}
