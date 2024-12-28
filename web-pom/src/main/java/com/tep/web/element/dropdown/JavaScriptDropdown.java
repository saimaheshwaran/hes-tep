package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides methods for interacting with dropdown elements (select elements)
 * using JavaScript execution. The actions include selecting, deselecting, and handling
 * multi-select options in dropdown lists.
 */
public class JavaScriptDropdown {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for the JavaScriptDropdown class. Initializes instances of SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public JavaScriptDropdown(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Selects an option in a dropdown based on the given selection criteria (index, value, text).
     * The element is identified by its object name.
     *
     * @param option   The option to select (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param objName  The object name of the dropdown element.
     */
    public void select(String option, String optionBy, String objName) {
        select(option, optionBy, seleniumDriver.getElement(objName));
    }

    /**
     * Selects an option in a dropdown based on the given selection criteria (index, value, text).
     *
     * @param option   The option to select (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param webElement The dropdown WebElement.
     */
    public void select(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = true;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = true; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }";
            }
            assert script != null;
            executor.executeScript(script, webElement, option);  // Execute JavaScript to select the option
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Deselects an option in a dropdown based on the given selection criteria (index, value, text).
     * The element is identified by its object name.
     *
     * @param option   The option to deselect (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param objName  The object name of the dropdown element.
     */
    public void deselect(String option, String optionBy, String objName) {
        deselect(option, optionBy, seleniumDriver.getElement(objName));
    }

    /**
     * Deselects an option in a dropdown based on the given selection criteria (index, value, text).
     *
     * @param option   The option to deselect (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param webElement The dropdown WebElement.
     */
    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = false;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = false; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = false; } }";
            }
            assert script != null;
            executor.executeScript(script, webElement, option);  // Execute JavaScript to deselect the option
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Deselects all options in a multi-select dropdown.
     * The element is identified by its object name.
     *
     * @param objName The object name of the dropdown element.
     */
    public void deselectAll(String objName) {
        deselectAll(seleniumDriver.getElement(objName));
    }

    /**
     * Deselects all options in a multi-select dropdown.
     *
     * @param webElement The dropdown WebElement.
     */
    public void deselectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = false;}}";
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript(script, webElement);  // Execute JavaScript to deselect all options
        } catch (StaleElementReferenceException ignored) {
            deselectAll(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Selects all options in a multi-select dropdown.
     * The element is identified by its object name.
     *
     * @param objName The object name of the dropdown element.
     */
    public void selectAll(String objName) {
        selectAll(seleniumDriver.getElement(objName));
    }

    /**
     * Selects all options in a multi-select dropdown.
     *
     * @param webElement The dropdown WebElement.
     */
    public void selectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(!select.options[i].selected){select.options[i].selected = true;}}";
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript(script, webElement);  // Execute JavaScript to select all options
        } catch (StaleElementReferenceException ignored) {
            selectAll(webElement);  // Retry if the element becomes stale
        }
    }
}
