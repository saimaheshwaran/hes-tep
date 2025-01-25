package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The JavaScriptSendKeys class allows sending text to a web element using JavaScript execution.
 * It sets the "value" attribute of the specified element to simulate typing text into input fields.
 */
public class JavaScriptSendKeys {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the JavaScriptSendKeys class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public JavaScriptSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Sends text to the specified web element by using JavaScript execution.
     * It sets the "value" attribute of the element to the provided text.
     * The element is checked for visibility and enabled state before executing the script.
     *
     * @param webElement The WebElement to which the text will be sent.
     * @param text The text to send to the web element.
     */
    public void sendKeys(WebElement webElement, String text) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            if (webElement.isEnabled()) {  // Checks if the element is enabled.
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();  // Casts the driver to JavascriptExecutor.
                // Executes the JavaScript to set the "value" attribute of the element to the given text.
                executor.executeScript("arguments[0].setAttribute('value', arguments[1])", webElement, text);
            }
        } catch (StaleElementReferenceException e) {
            sendKeys(webElement, text);  // Retries the operation if the element reference becomes stale.
        }
    }
}
