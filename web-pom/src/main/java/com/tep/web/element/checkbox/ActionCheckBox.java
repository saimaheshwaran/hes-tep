package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides actions to interact with checkboxes on a webpage.
 * It includes methods to check, uncheck, and toggle the checkbox elements.
 * It utilizes Selenium WebDriver and Actions class to perform the actions on checkboxes.
 */
public class ActionCheckBox {

    /**
     * An instance of SeleniumWaits to handle explicit waits for elements.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver to interact with the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * An instance of ActionClick to simulate clicks on web elements.
     */
    private final ActionClick actionClick;

    /**
     * Constructor for ActionCheckBox class. Initializes the instances of
     * SeleniumWaits, SeleniumDriver, and ActionClick.
     *
     * @param seleniumDriver The SeleniumDriver instance that provides the browser interaction.
     */
    public ActionCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.actionClick = new ActionClick(seleniumDriver);
    }

    /**
     * Checks a checkbox identified by its object name.
     * It will interact with the checkbox if it is not already selected.
     *
     * @param objName The object name of the checkbox element.
     */
    public void check(String objName) {
        check(seleniumDriver.getElement(objName));
    }

    /**
     * Checks the provided checkbox WebElement.
     * It will interact with the checkbox if it is not already selected.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();  // Move to element before clicking
            actions.release().perform();  // Release the action after movement
            if (!webElement.isSelected()) {
                actionClick.click(webElement);  // Click to check the checkbox
            }
        } catch (StaleElementReferenceException ignored) {
            check(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Unchecks a checkbox identified by its object name.
     * It will interact with the checkbox if it is already selected.
     *
     * @param objName The object name of the checkbox element.
     */
    public void uncheck(String objName) {
        uncheck(seleniumDriver.getElement(objName));
    }

    /**
     * Unchecks the provided checkbox WebElement.
     * It will interact with the checkbox if it is already selected.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();  // Move to element before clicking
            actions.release().perform();  // Release the action after movement
            if (webElement.isSelected()) {
                actionClick.click(webElement);  // Click to uncheck the checkbox
            }
        } catch (StaleElementReferenceException ignored) {
            uncheck(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Toggles the checkbox identified by its object name.
     * It will check the checkbox if it is unchecked and uncheck it if it is checked.
     *
     * @param objName The object name of the checkbox element.
     */
    public void toggle(String objName) {
        toggle(seleniumDriver.getElement(objName));
    }

    /**
     * Toggles the provided checkbox WebElement.
     * It will check the checkbox if it is unchecked and uncheck it if it is checked.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().perform();  // Move to and click the checkbox
            actions.release().perform();  // Release the action after click
        } catch (StaleElementReferenceException ignored) {
            toggle(webElement);  // Retry if the element becomes stale
        }
    }
}
