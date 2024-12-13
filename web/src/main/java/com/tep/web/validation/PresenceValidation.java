package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * PresenceValidation class to handle validation of element presence.
 */
public class PresenceValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(PresenceValidation.class);


    /**
     * Constructor to initialize the PresenceValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public PresenceValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("PresenceValidation with Webdriver initialized successfully");
    }

    /**
     * Constructor to initialize the PresenceValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public PresenceValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("PresenceValidation with Webdriver and Pageobjects initialized successfully");
    }

    /**
     * Verifies if the element identified by the object name is present or not.
     *
     * @param objName       the name of the object whose locator is to be retrieved.
     * @param elementPresent true if the element should be present, false otherwise.
     */
    public void verify(String objName, boolean elementPresent) {
        verify(objects.get(objName), elementPresent);
    }

    /**
     * Verifies if the element identified by the locator pair is present or not.
     *
     * @param locatorPair   a Map.Entry containing the locator type and value.
     * @param elementPresent true if the element should be present, false otherwise.
     */
    public void verify(Map.Entry<String, String> locatorPair, boolean elementPresent) {
        if (elementPresent) {
            try {
                waits.waitForElementToDisplay(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
            } catch (TimeoutException e) {
                logger.error("Expected element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" to be present, but it is not.");
                Assertion.equalsTrue(false, "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should be present. But element is not present.");
            }
        } else {
            try {
                waits.waitForElementToDisplay(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
                logger.info("Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" is present when it should not be.");
                Assertion.equalsFalse(true, "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should not be present. But element is present.");
            } catch (TimeoutException ignored) {
            }
        }
    }

    /**
     * Verifies if the element identified by the object name is present or not within the specified wait time.
     *
     * @param objName       the name of the object whose locator is to be retrieved.
     * @param elementPresent true if the element should be present, false otherwise.
     * @param waitTime      the wait time in seconds.
     */
    public void verify(String objName, boolean elementPresent, int waitTime) {
        verify(objects.get(objName), elementPresent, waitTime);
    }

    /**
     * Verifies if the element identified by the locator pair is present or not within the specified wait time.
     *
     * @param locatorPair   a Map.Entry containing the locator type and value.
     * @param elementPresent true if the element should be present, false otherwise.
     * @param waitTime      the wait time in seconds.
     */
    public void verify(Map.Entry<String, String> locatorPair, boolean elementPresent, int waitTime) {
        if (elementPresent) {
            try {
                waits.waitForElementToDisplay(locatorPair, waitTime);
            } catch (TimeoutException e) {
                logger.error("Expected element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" to be present, but it is not.");
                Assertion.equalsTrue(false, "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should be present. But element is not present.");
            }
        } else {
            try {
                waits.waitForElementToDisplay(locatorPair, waitTime);
                logger.info("Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" is present when it should not be.");
                Assertion.equalsFalse(true, "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should not be present. But element is present.");
            } catch (TimeoutException ignored) {
            }
        }
    }
}
