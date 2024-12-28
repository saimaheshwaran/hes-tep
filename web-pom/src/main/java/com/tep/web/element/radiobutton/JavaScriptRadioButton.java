package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The JavaScriptRadioButton class allows selecting a radio button element using JavaScript.
 * It executes a script to check the radio button if it is not already selected.
 */
public class JavaScriptRadioButton {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the JavaScriptRadioButton class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public JavaScriptRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Selects the radio button identified by its object name using JavaScript.
     * It waits for the element to be displayed, and then executes a JavaScript script
     * to check the radio button if it is not already selected.
     *
     * @param objName The name of the radio button element.
     */
    public void select(String objName) {
        select(seleniumDriver.getElement(objName));
    }

    /**
     * Selects the radio button represented by the provided WebElement using JavaScript.
     * It waits for the element to be displayed, and then executes a JavaScript script
     * to check the radio button if it is not already selected.
     *
     * @param webElement The WebElement representing the radio button.
     */
    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the radio button to be visible.
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();  // Casts browser to JavascriptExecutor.
            executor.executeScript("var radio=arguments[0]; if(!radio.checked){radio.checked=true;}", webElement);  // Executes JavaScript to check the radio button.
        } catch (StaleElementReferenceException ignored) {
            select(webElement);  // Retries the operation if the element reference becomes stale.
        }
    }

}
