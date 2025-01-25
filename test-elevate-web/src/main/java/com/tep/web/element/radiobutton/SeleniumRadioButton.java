package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The SeleniumRadioButton class provides a method to select a radio button element.
 * It checks if the radio button is selected, and if not, it clicks on it using Selenium's WebDriver.
 */
public class SeleniumRadioButton {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /** The instance of SeleniumClick used to perform click actions. */
    private final SeleniumClick seleniumClick;

    /**
     * Constructor to initialize the SeleniumRadioButton class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public SeleniumRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.seleniumClick = new SeleniumClick(seleniumDriver);
    }

    /**
     * Selects the radio button identified by its object name.
     * It waits for the element to be displayed, checks if it is selected,
     * and clicks it if it is not already selected.
     *
     * @param objName The name of the radio button element.
     */
    public void select(String objName) {
        select(seleniumDriver.getElement(objName));
    }

    /**
     * Selects the radio button represented by the provided WebElement.
     * It waits for the element to be displayed, checks if it is selected,
     * and clicks it if it is not already selected.
     *
     * @param webElement The WebElement representing the radio button.
     */
    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the radio button to be visible.
            if (!webElement.isSelected()) {  // Checks if the radio button is not already selected.
                seleniumClick.click(webElement);  // Clicks the radio button if it is not selected.
            }
        } catch (StaleElementReferenceException ignored) {
            select(webElement);  // Retries the operation if the element reference becomes stale.
        }
    }
}
