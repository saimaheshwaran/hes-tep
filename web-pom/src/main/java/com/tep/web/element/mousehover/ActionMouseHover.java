package com.tep.web.element.mousehover;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ActionMouseHover class to handle mouse hover actions on web elements.
 */
public class ActionMouseHover {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(ActionMouseHover.class);

    /**
     * Constructor to initialize the ActionMouseHover with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public ActionMouseHover(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionMouseHover initialized with WebDriver, Waits, and Element helpers.");
    }

    /**
     * Constructor to initialize the ActionMouseHover with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public ActionMouseHover(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionMouseHover initialized with WebDriver, PageObjects, Waits, and Element helpers.");
    }

    /**
     * Performs a mouse hover action on the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void mouseHover(String objName) {
        mouseHover(objects.get(objName));
    }

    /**
     * Performs a mouse hover action on the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void mouseHover(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).perform();
            logger.info("Mouse hover performed on element with locator", locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while performing mouse hover. Retrying..", locatorPair, e);
            mouseHover(locatorPair);
        }
    }

    public void mouseHover(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
            logger.info("Mouse hover performed on element: {}", webElement);
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while performing mouse hover. Retrying..{}", webElement, e);
            mouseHover(webElement);
        }
    }

}
