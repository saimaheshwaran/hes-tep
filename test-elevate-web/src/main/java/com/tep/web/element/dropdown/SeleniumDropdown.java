package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

/**
 * The SeleniumDropdown class provides methods for interacting with standard HTML select dropdowns
 * using Selenium WebDriver. It includes methods to select, deselect, and multi-select options
 * from dropdown lists.
 */
public class SeleniumDropdown {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * An instance of SeleniumClick used to perform click actions on elements.
     */
    private final SeleniumClick seleniumClick;

    /**
     * Constructor for the SeleniumDropdown class. Initializes instances of SeleniumWaits, SeleniumDriver,
     * and SeleniumClick.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public SeleniumDropdown(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.seleniumClick = new SeleniumClick(seleniumDriver);
    }

    /**
     * Selects an option from the dropdown by index, value, or text. The element is identified by its object name.
     *
     * @param option   The option to select (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param objName  The object name of the dropdown element.
     */
    public void select(String option, String optionBy, String objName) {
        select(option, optionBy, seleniumDriver.getElement(objName));
    }

    /**
     * Selects an option from the dropdown by index, value, or text.
     *
     * @param option    The option to select (index, value, or text).
     * @param optionBy  The method to identify the option (index, value, text).
     * @param webElement The dropdown WebElement.
     */
    public void select(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            Select selectList = new Select(webElement);
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);  // Select by index
                case "value" -> selectList.selectByValue(option);  // Select by value
                case "text" -> selectList.selectByVisibleText(option);  // Select by visible text
            }
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Deselects an option from the dropdown by index, value, or text. The element is identified by its object name.
     *
     * @param option   The option to deselect (index, value, or text).
     * @param optionBy The method to identify the option (index, value, text).
     * @param objName  The object name of the dropdown element.
     */
    public void deselect(String option, String optionBy, String objName) {
        deselect(option, optionBy, seleniumDriver.getElement(objName));
    }

    /**
     * Deselects an option from the dropdown by index, value, or text.
     *
     * @param option    The option to deselect (index, value, or text).
     * @param optionBy  The method to identify the option (index, value, text).
     * @param webElement The dropdown WebElement.
     */
    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            Select selectList = new Select(webElement);
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);  // Deselect by index
                case "value" -> selectList.deselectByValue(option);  // Deselect by value
                case "text" -> selectList.deselectByVisibleText(option);  // Deselect by visible text
            }
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Deselects all options in a multi-select dropdown. The element is identified by its object name.
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
            Select selectList = new Select(webElement);
            selectList.deselectAll();  // Deselect all options
        } catch (StaleElementReferenceException ignored) {
            deselectAll(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Selects multiple options in a multi-select dropdown. The element is identified by its object name.
     *
     * @param options The array of options to select (by index, value, or text).
     * @param objName The object name of the dropdown element.
     */
    public void multiSelect(String[] options, String objName) {
        multiSelect(options, seleniumDriver.getElement(objName));
    }

    /**
     * Selects multiple options in a multi-select dropdown.
     *
     * @param options The array of options to select (by index, value, or text).
     * @param webElement The dropdown WebElement.
     */
    public void multiSelect(String[] options, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();  // Get all options in the dropdown
            for (String item : options) {
                for (WebElement wbElement : elementCount) {
                    if (wbElement.getText().equals(item)) {  // Check if the option matches
                        seleniumClick.click(wbElement);  // Click to select the option
                    }
                }
            }
        } catch (StaleElementReferenceException e) {
            multiSelect(options, webElement);  // Retry if the element becomes stale
        }
    }
}
