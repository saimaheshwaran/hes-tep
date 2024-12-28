package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides methods for performing click and double-click actions on WebElements
 * using standard Selenium interactions. The actions will be retried if the element becomes stale
 * during the interaction.
 */
public class SeleniumClick {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements before interacting with them.
     */
    private final SeleniumWaits wait;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for the SeleniumClick class. Initializes instances of SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public SeleniumClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.wait = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Clicks the element identified by its object name.
     * It waits for the element to be displayed before performing the click action.
     *
     * @param objName The object name of the element to be clicked.
     */
    public void click(String objName) {
        click(seleniumDriver.getElement(objName));
    }

    /**
     * Clicks the provided WebElement.
     * It waits for the element to be displayed before performing the click action.
     * In case the element becomes stale, it retries the click action.
     *
     * @param element The WebElement to be clicked.
     */
    public void click(WebElement element) {
        try {
            wait.untilElementDisplayed(element);  // Wait for the element to be displayed
            element.click();  // Perform the click action
        } catch (StaleElementReferenceException e) {
            click(element);  // Retry the click action if the element becomes stale
        }
    }

    /**
     * Double-clicks the element identified by its object name.
     * It waits for the element to be displayed before performing the double-click action.
     *
     * @param objName The object name of the element to be double-clicked.
     */
    public void doubleClick(String objName) {
        doubleClick(seleniumDriver.getElement(objName));
    }

    /**
     * Double-clicks the provided WebElement.
     * It waits for the element to be displayed before performing the double-click action.
     * In case the element becomes stale, it retries the double-click action.
     *
     * @param element The WebElement to be double-clicked.
     */
    public void doubleClick(WebElement element) {
        try {
            wait.untilElementDisplayed(element);  // Wait for the element to be displayed
            element.click();  // Perform the first click
            element.click();  // Perform the second click to complete the double-click action
        } catch (StaleElementReferenceException e) {
            doubleClick(element);  // Retry the double-click action if the element becomes stale
        }
    }
}
