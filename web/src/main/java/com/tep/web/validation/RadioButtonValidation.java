package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The RadioButtonValidation class provides methods to validate the selection state of radio buttons on a web page.
 * It checks whether a radio button is selected or not based on the given condition.
 */
public class RadioButtonValidation {

    /** The instance of SeleniumWaits used for waiting on elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the RadioButtonValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public RadioButtonValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether a radio button is selected or not based on the given boolean flag.
     *
     * @param objName The name or identifier of the radio button.
     * @param shouldBeSelected A boolean indicating whether the radio button should be selected (true) or not (false).
     */
    public void isSelected(String objName, boolean shouldBeSelected) {
        isSelected(seleniumDriver.getElement(objName), shouldBeSelected);
    }

    /**
     * Verifies whether the given radio button is selected or not based on the given boolean flag.
     *
     * @param webElement The WebElement representing the radio button to check.
     * @param shouldBeSelected A boolean indicating whether the radio button should be selected (true) or not (false).
     */
    public void isSelected(WebElement webElement, boolean shouldBeSelected) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (!webElement.isSelected() && shouldBeSelected) {
            Assertion.equalsTrue(false, "Expected: Radio button (" + webElement + ") should be selected. But Radio button is not selected.");
        } else if (webElement.isSelected() && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: Radio button (" + webElement + ") should not be selected. But Radio button is selected.");
        }
    }
}
