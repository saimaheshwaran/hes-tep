package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * This class provides methods for performing click actions on WebElements using the Actions class in Selenium WebDriver.
 * It includes methods for regular clicks and double clicks.
 */
public class ActionClick {

    /**
     * An instance of SeleniumWaits used to apply explicit waits on elements.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for ActionClick class. Initializes instances of SeleniumWaits and SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance that provides interaction with the browser.
     */
    public ActionClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Clicks the element identified by its object name.
     * It waits for the element to be displayed, then performs a click using the Actions class.
     *
     * @param ObjName The object name of the element to be clicked.
     */
    public void click(String ObjName) {
        click(seleniumDriver.getElement(ObjName));
    }

    /**
     * Clicks the provided WebElement.
     * It waits for the element to be displayed, then performs a click using the Actions class.
     *
     * @param webElement The WebElement to be clicked.
     */
    public void click(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement); // Wait for the element to be displayed
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().perform();  // Perform the click action
            actions.release().perform();  // Release the actions
        } catch (StaleElementReferenceException e) {
            click(webElement);  // Retry in case the element becomes stale
        }
    }

    /**
     * Double-clicks the element identified by its object name.
     * It waits for the element to be displayed, then performs a double-click using the Actions class.
     *
     * @param ObjName The object name of the element to be double-clicked.
     */
    public void doubleClick(String ObjName) {
        doubleClick(seleniumDriver.getElement(ObjName));
    }

    /**
     * Double-clicks the provided WebElement.
     * It waits for the element to be displayed, then performs a double-click using the Actions class.
     *
     * @param webElement The WebElement to be double-clicked.
     */
    public void doubleClick(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement); // Wait for the element to be displayed
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).doubleClick().perform();  // Perform the double-click action
            actions.release().perform();  // Release the actions
        } catch (StaleElementReferenceException e) {
            doubleClick(webElement);  // Retry in case the element becomes stale
        }
    }
}
