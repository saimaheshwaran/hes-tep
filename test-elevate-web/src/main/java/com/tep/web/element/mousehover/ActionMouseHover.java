package com.tep.web.element.mousehover;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The ActionMouseHover class provides a method to simulate a mouse hover over an element on the webpage.
 * It uses Selenium's Actions class to move the mouse pointer to a specified WebElement,
 * and it ensures the element is displayed before performing the action.
 */
public class ActionMouseHover {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the ActionMouseHover class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public ActionMouseHover(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Simulates a mouse hover over an element identified by its object name.
     *
     * @param objName The name of the element to hover over.
     */
    public void mouseHover(String objName) { mouseHover(seleniumDriver.getElement(objName)); }

    /**
     * Simulates a mouse hover over the specified WebElement.
     *
     * @param webElement The WebElement to hover over.
     */
    public void mouseHover(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be displayed.
            Actions actions = new Actions(seleniumDriver.getBrowser());  // Creates an instance of Actions class.
            actions.moveToElement(webElement).perform();  // Moves the mouse to the element and performs the action.
        } catch (StaleElementReferenceException e) {
            mouseHover(webElement);  // Retries the action if the element reference becomes stale.
        }
    }
}
