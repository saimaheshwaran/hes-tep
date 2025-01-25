package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

/**
 * This class provides methods for interacting with dropdown elements (select elements)
 * using Selenium WebDriver. The actions performed include selecting, deselecting, and handling
 * multi-select options in dropdown lists.
 */
public class ActionDropdown {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for the ActionDropdown class. Initializes instances of SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public ActionDropdown(SeleniumDriver seleniumDriver) {
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
            Select selectList = new Select(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();  // Move the cursor to the dropdown element
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
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
            Select selectList = new Select(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();  // Move the cursor to the dropdown element
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
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
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(seleniumDriver.getBrowser());
            for (WebElement wbElement : elementCount) {
                if (wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);  // Deselect using CONTROL + click
                }
            }
            actions.build().perform();
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
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(seleniumDriver.getBrowser());
            for (WebElement wbElement : elementCount) {
                if (!wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);  // Select using CONTROL + click
                }
            }
            actions.build().perform();
        } catch (StaleElementReferenceException ignored) {
            selectAll(webElement);  // Retry if the element becomes stale
        }
    }

    /**
     * Selects multiple options in a multi-select dropdown.
     * The element is identified by its object name.
     *
     * @param options The options to select.
     * @param objName The object name of the dropdown element.
     */
    public void multiSelect(String[] options, String objName) {
        multiSelect(options, seleniumDriver.getElement(objName));
    }

    /**
     * Selects multiple options in a multi-select dropdown.
     *
     * @param options   The options to select.
     * @param webElement The dropdown WebElement.
     */
    public void multiSelect(String[] options, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions action = new Actions(seleniumDriver.getBrowser());
            for (String option : options) {
                for (WebElement wbElement : elementCount) {
                    if (wbElement.getText().equals(option)) {
                        action.keyDown(Keys.CONTROL).click(wbElement);  // Select using CONTROL + click
                    }
                }
            }
            action.build().perform();
        } catch (StaleElementReferenceException e) {
            multiSelect(options, webElement);  // Retry if the element becomes stale
        }
    }
}
