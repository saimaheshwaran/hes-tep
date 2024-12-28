package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides methods for performing click and double-click actions on WebElements
 * using JavaScript execution. This approach is useful when traditional Selenium actions
 * (such as Actions or WebDriver click) do not work, particularly when the element is not
 * interactable in the usual way.
 */
public class JavaScriptClick {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for JavaScriptClick class. Initializes instances of SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance that provides interaction with the browser.
     */
    public JavaScriptClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Clicks the element identified by its object name using JavaScript execution.
     * It waits for the element to be displayed and enabled before executing the click action.
     *
     * @param objName The object name of the element to be clicked.
     */
    public void click(String objName) {
        click(seleniumDriver.getElement(objName));
    }

    /**
     * Clicks the provided WebElement using JavaScript execution.
     * It waits for the element to be displayed and enabled before executing the click action.
     *
     * @param webElement The WebElement to be clicked.
     */
    public void click(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            if (webElement.isEnabled()) {  // Check if the element is enabled
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
                executor.executeScript("arguments[0].click();", webElement);  // Execute the JavaScript click action
            }
        } catch (StaleElementReferenceException e) {
            click(webElement);  // Retry in case the element becomes stale
        }
    }

    /**
     * Double-clicks the element identified by its object name using JavaScript execution.
     * It waits for the element to be displayed and enabled before executing the double-click action.
     *
     * @param objName The object name of the element to be double-clicked.
     */
    public void doubleClick(String objName) {
        doubleClick(seleniumDriver.getElement(objName));
    }

    /**
     * Double-clicks the provided WebElement using JavaScript execution.
     * It waits for the element to be displayed and enabled before executing the double-click action.
     *
     * @param webElement The WebElement to be double-clicked.
     */
    public void doubleClick(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Wait for the element to be displayed
            if (webElement.isEnabled()) {  // Check if the element is enabled
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
                executor.executeScript("arguments[0].click(); arguments[0].click();", webElement);  // Execute the JavaScript double-click action
            }
        } catch (StaleElementReferenceException e) {
            doubleClick(webElement);  // Retry in case the element becomes stale
        }
    }
}
