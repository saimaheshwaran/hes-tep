package com.tep.web.element.mousehover;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The JavaScriptMouseHover class provides a method to simulate a mouse hover over an element using JavaScript.
 * This method executes a custom JavaScript snippet to trigger the 'mouseover' event on the specified WebElement.
 */
public class JavaScriptMouseHover {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the JavaScriptMouseHover class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public JavaScriptMouseHover(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Simulates a mouse hover over an element identified by its object name using JavaScript.
     *
     * @param objName The name of the element to hover over.
     */
    public void mouseHover(String objName) { mouseHover(seleniumDriver.getElement(objName)); }

    /**
     * Simulates a mouse hover over the specified WebElement using JavaScript.
     * A custom 'mouseover' event is triggered using JavaScript execution.
     *
     * @param webElement The WebElement to hover over.
     */
    public void mouseHover(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be displayed.
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} " +
                    "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";  // JavaScript to trigger mouseover event.
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();  // Get the browser instance.
            executor.executeScript(mouseOverScript, webElement);  // Executes the JavaScript code on the WebElement.
            seleniumWaits.sleep(5);  // Waits for 5 seconds after the hover to simulate user behavior.
        } catch (StaleElementReferenceException e) {
            mouseHover(webElement);  // Retries if the element reference becomes stale.
        }
    }
}
