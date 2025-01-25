package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The SeleniumSendKeys class provides methods to send text to web elements and clear input fields.
 * It interacts with web elements using the Selenium WebDriver to simulate typing and clearing of inputs.
 */
public class SeleniumSendKeys {

    /** The instance of SeleniumWaits used to wait for elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the SeleniumSendKeys class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public SeleniumSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Sends the specified text to the web element identified by its object name.
     * It waits for the element to be displayed and then sends the text to the element.
     *
     * @param objName The name of the web element.
     * @param text The text to send to the web element.
     */
    public void sendKeys(String objName, String text) { sendKeys(seleniumDriver.getElement(objName), text); }

    /**
     * Sends the specified text to the provided web element.
     * It waits for the element to be displayed and then sends the text to the element.
     *
     * @param webElement The WebElement to which the text will be sent.
     * @param value The text to send to the web element.
     */
    public void sendKeys(WebElement webElement, String value) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            webElement.sendKeys(value);  // Sends the value to the web element.
        } catch (StaleElementReferenceException e) {
            webElement.sendKeys(value);  // Retries if the element reference becomes stale.
        }
    }

    /**
     * Clears the text of the web element identified by its object name.
     * It waits for the element to be displayed and then clears its value.
     *
     * @param objName The name of the web element to be cleared.
     */
    public void clearInputs(String objName) { clearInputs(seleniumDriver.getElement(objName)); }

    /**
     * Clears the text of the provided web element.
     * It waits for the element to be displayed and then clears its value.
     *
     * @param webElement The WebElement to be cleared.
     */
    public void clearInputs(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            webElement.clear();  // Clears the text of the element.
        } catch (StaleElementReferenceException e) {
            clearInputs(webElement);  // Retries if the element reference becomes stale.
        }
    }
}
