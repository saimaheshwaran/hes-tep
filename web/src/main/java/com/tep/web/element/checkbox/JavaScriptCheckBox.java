package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides actions to interact with checkboxes on a webpage using JavaScript execution.
 * It includes methods to check, uncheck, and toggle the checkbox elements using JavaScript.
 */
public class JavaScriptCheckBox {

    /**
     * An instance of SeleniumWaits to handle explicit waits for elements.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver to interact with the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for JavaScriptCheckBox class. Initializes the instances of
     * SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance that provides the browser interaction.
     */
    public JavaScriptCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Checks a checkbox identified by its object name using JavaScript.
     * It sets the checkbox to be selected if it is not already selected.
     *
     * @param objName The object name of the checkbox element.
     */
    public void check(String objName) {
        check(seleniumDriver.getElement(objName));
    }

    /**
     * Checks the provided checkbox WebElement using JavaScript.
     * It sets the checkbox to be selected if it is not already selected.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            // Executes JavaScript to check the checkbox
            executor.executeScript("var checkbox=arguments[0]; if(!checkbox.checked){checkbox.checked=true;}", webElement);
        } catch (StaleElementReferenceException e) {
            check(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Unchecks a checkbox identified by its object name using JavaScript.
     * It sets the checkbox to be unselected if it is already selected.
     *
     * @param objName The object name of the checkbox element.
     */
    public void uncheck(String objName) {
        uncheck(seleniumDriver.getElement(objName));
    }

    /**
     * Unchecks the provided checkbox WebElement using JavaScript.
     * It sets the checkbox to be unselected if it is already selected.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            // Executes JavaScript to uncheck the checkbox
            executor.executeScript("var checkbox=arguments[0]; if(checkbox.checked){checkbox.checked=false;}", webElement);
        } catch (StaleElementReferenceException e) {
            uncheck(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Toggles the checkbox identified by its object name using JavaScript.
     * It will check the checkbox if it is unchecked and uncheck it if it is checked.
     *
     * @param objName The object name of the checkbox element.
     */
    public void toggle(String objName) {
        toggle(seleniumDriver.getElement(objName));
    }

    /**
     * Toggles the provided checkbox WebElement using JavaScript.
     * It will check the checkbox if it is unchecked and uncheck it if it is checked.
     *
     * @param webElement The WebElement representing the checkbox.
     */
    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            // Executes JavaScript to toggle the checkbox
            executor.executeScript("var checkbox=arguments[0]; checkbox.checked=!checkbox.checked;", webElement);
        } catch (StaleElementReferenceException e) {
            toggle(webElement);  // Retry if the element becomes stale
        }
    }
}
