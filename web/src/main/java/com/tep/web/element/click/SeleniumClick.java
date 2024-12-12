package com.tep.web.element.click;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * SeleniumClick class to handle click actions on web elements using Selenium.
 */
public class SeleniumClick {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumClick.class);

    /**
     * Constructor to initialize the SeleniumClick with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public SeleniumClick(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WebDriver, Waits, and Element instances have been initialized.");
    }

    /**
     * Constructor to initialize the SeleniumClick with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public SeleniumClick(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WebDriver, PageObjects, Waits, and Element instances have been initialized.");
    }

    /**
     * Clicks on the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void click(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).click();
            logger.info("Click action performed successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during click, retrying.", e);
            click(locatorPair);
        }
    }

    /**
     * Double-clicks on the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void doubleClick(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).click();
            element.get(locatorPair).click();
            logger.info("Double-click action performed successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during double-click, retrying.", e);
            doubleClick(locatorPair);
        }
    }

    /**
     * Clicks on the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void click(String objName) {
        click(objects.get(objName));
    }

    /**
     * Double-clicks on the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void doubleClick(String objName) {
        doubleClick(objects.get(objName));
    }
}
