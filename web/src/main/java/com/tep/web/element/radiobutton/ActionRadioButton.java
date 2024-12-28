package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The ActionRadioButton class provides a method to select a radio button element.
 * It uses Selenium's Actions class to move the mouse to the radio button and clicks on it
 * if it is not already selected.
 */
public class ActionRadioButton {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /** The instance of ActionClick used to perform the click action on the radio button. */
    private final ActionClick actionClick;

    /**
     * Constructor to initialize the ActionRadioButton class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public ActionRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.actionClick = new ActionClick(seleniumDriver);
    }

    /**
     * Selects the radio button identified by its object name.
     * It waits for the element to be displayed, moves the mouse to it,
     * and clicks it if it is not already selected.
     *
     * @param objName The name of the radio button element.
     */
    public void select(String objName) {
        select(seleniumDriver.getElement(objName));
    }

    /**
     * Selects the radio button represented by the provided WebElement.
     * It waits for the element to be displayed, moves the mouse to it,
     * and clicks it if it is not already selected.
     *
     * @param webElement The WebElement representing the radio button.
     */
    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            Actions actions = new Actions(seleniumDriver.getBrowser());  // Creates an Actions instance for mouse actions.
            actions.moveToElement(webElement).perform();  // Moves the mouse to the radio button.
            if (!webElement.isSelected()) {  // Checks if the radio button is not already selected.
                actionClick.click(webElement);  // Clicks the radio button if it is not selected.
            }
            actions.release().perform();  // Releases the action after clicking.
        } catch (StaleElementReferenceException ignored) {
            select(webElement);  // Retries the operation if the element reference becomes stale.
        }
    }

}
