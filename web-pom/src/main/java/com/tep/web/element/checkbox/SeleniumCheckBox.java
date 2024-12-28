package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides methods for interacting with checkbox elements on a webpage using Selenium WebDriver.
 * It includes methods to check, uncheck, and toggle checkbox elements.
 */
public class SeleniumCheckBox {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumClick used to perform click actions on elements.
     */
    private final SeleniumClick click;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for SeleniumCheckBox class. Initializes instances of SeleniumWaits, SeleniumClick, and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance that provides interaction with the browser.
     */
    public SeleniumCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.click = new SeleniumClick(seleniumDriver);
    }

    /**
     * Checks the checkbox identified by its object name.
     * If the checkbox is not already checked, it will be clicked to check it.
     *
     * @param objName The object name of the checkbox element.
     */
    public void check(String objName) {
        check(seleniumDriver.getElement(objName));
    }

    /**
     * Checks the provided checkbox WebElement.
     * If the checkbox is not already checked, it will be clicked to check it.
     *
     * @param webElement The WebElement representing the checkbox to be checked.
     */
    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement); // Wait for the element to be displayed
            if (!webElement.isSelected()) {  // If checkbox is not checked, click it
                click.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            check(webElement);  // Retry in case the element becomes stale
        }
    }

    /**
     * Unchecks the checkbox identified by its object name.
     * If the checkbox is checked, it will be clicked to uncheck it.
     *
     * @param objName The object name of the checkbox element.
     */
    public void uncheck(String objName) {
        uncheck(seleniumDriver.getElement(objName));
    }

    /**
     * Unchecks the provided checkbox WebElement.
     * If the checkbox is checked, it will be clicked to uncheck it.
     *
     * @param webElement The WebElement representing the checkbox to be unchecked.
     */
    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            if (webElement.isSelected()) {  // If checkbox is checked, click it
                click.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            uncheck(webElement);  // Retry in case the element becomes stale
        }
    }

    /**
     * Toggles the checkbox identified by its object name.
     * If the checkbox is checked, it will be unchecked, and vice versa.
     *
     * @param objName The object name of the checkbox element.
     */
    public void toggle(String objName) {
        toggle(seleniumDriver.getElement(objName));
    }

    /**
     * Toggles the provided checkbox WebElement.
     * If the checkbox is checked, it will be unchecked, and vice versa.
     *
     * @param webElement The WebElement representing the checkbox to be toggled.
     */
    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            click.click(webElement);  // Click the checkbox to toggle its state
        } catch (StaleElementReferenceException ignored) {
            toggle(webElement);  // Retry in case the element becomes stale
        }
    }
}
