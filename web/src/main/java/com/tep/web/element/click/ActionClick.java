package com.tep.web.element.click;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ActionClick class to handle click actions on web elements.
 */
public class ActionClick {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(ActionClick.class);

    /**
     * Constructor to initialize the ActionClick with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public ActionClick(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WebDriver, Waits, and Element instances have been initialized.");
    }

    /**
     * Constructor to initialize the ActionClick with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public ActionClick(WebDriver driver, PageObjects objects) {
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
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).click().perform();
            actions.release().perform();
            logger.info("Click action performed successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught, retrying click.", e);
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
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).doubleClick().perform();
            actions.release().perform();
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
