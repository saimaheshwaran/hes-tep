package com.tep.web.element.sendKey;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * SeleniumSendKeys class to handle sending keys to web elements using Selenium.
 */
public class SeleniumSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumSendKeys.class);

    /**
     * Constructor to initialize the SeleniumSendKeys with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public SeleniumSendKeys(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("SeleniumSendKeys initialized with WebDriver, Waits, and Element.");
    }

    /**
     * Constructor to initialize the SeleniumSendKeys with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public SeleniumSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("SeleniumSendKeys initialized with WebDriver, PageObjects, Waits, and Element.");

    }

    /**
     * Sends the specified text to the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param text    the text to send.
     */
    public void sendKeys(String objName, String text) {
        sendKeys(objects.get(objName), text);

    }

    /**
     * Sends the specified text to the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param value       the text to send.
     */
    public void sendKeys(Map.Entry<String, String> locatorPair, String value) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).sendKeys(value);
            logger.info("Keys sent to element with locator.", value, locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while sending keys.", e);
            sendKeys(locatorPair, value);
        }
    }

    /**
     * Clears the input field identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void clearInputs(String objName) {
        clearInputs(objects.get(objName));
    }

    /**
     * Clears the input field identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void clearInputs(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).clear();
            logger.info("Input field cleared with locator.", locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while clearing input field.", e);
            clearInputs(locatorPair);
        }
    }
}
