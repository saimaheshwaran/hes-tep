package com.tep.web.element.checkbox;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * SeleniumCheckBox class to handle checkbox interactions using Selenium.
 */
public class SeleniumCheckBox {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private ActionClick actionClick;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumCheckBox.class);

    /**
     * Constructor to initialize the SeleniumCheckBox with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public SeleniumCheckBox(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.actionClick = new ActionClick(driver);
        logger.info("SeleniumCheckBox initialized with WebDriver, Waits, Element, and ActionClick helpers.");
    }

    /**
     * Constructor to initialize the SeleniumCheckBox with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public SeleniumCheckBox(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.actionClick = new ActionClick(driver);
        logger.info("SeleniumCheckBox initialized with WebDriver, Waits, Element, PageObjects and ActionClick helpers.");
    }

    /**
     * Checks the checkbox identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void check(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            if (!checkBox.isSelected()) {
                actionClick.click(locatorPair);
            }
            logger.info("Checkbox is selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught, retrying check operation.");
            check(locatorPair);
        }
    }

    /**
     * Unchecks the checkbox identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void uncheck(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            if (checkBox.isSelected()) {
                actionClick.click(locatorPair);
            }
            logger.info("Checkbox is un-checked successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during uncheck, retrying.");
            uncheck(locatorPair);
        }
    }

    /**
     * Toggles the checkbox identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void toggle(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            actionClick.click(locatorPair);
            logger.info("Checkbox state toggled successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during toggle, retrying.");
            toggle(locatorPair);
        }
    }

    /**
     * Checks the checkbox identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void check(String objName) {
        check(objects.get(objName));
    }

    /**
     * Unchecks the checkbox identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void uncheck(String objName) {
        uncheck(objects.get(objName));
    }

    /**
     * Toggles the checkbox identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void toggle(String objName) {
        toggle(objects.get(objName));
    }
}
