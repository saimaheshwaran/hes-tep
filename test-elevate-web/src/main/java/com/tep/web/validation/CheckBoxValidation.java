package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The CheckBoxValidation class provides methods to verify the state of a checkbox element.
 * It checks whether a checkbox is selected or not, and asserts if the actual state matches the expected state.
 */
public class CheckBoxValidation {

    /** The instance of SeleniumWaits used to wait for elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the CheckBoxValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public CheckBoxValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether a checkbox identified by its object name is in the expected state (checked or unchecked).
     *
     * @param objName The object name used to locate the checkbox element.
     * @param shouldBeChecked A boolean indicating whether the checkbox should be checked or unchecked.
     */
    public void isChecked(String objName, boolean shouldBeChecked) {
        isChecked(seleniumDriver.getElement(objName), shouldBeChecked);
    }

    /**
     * Verifies whether a checkbox WebElement is in the expected state (checked or unchecked).
     *
     * @param webElement The WebElement representing the checkbox.
     * @param shouldBeChecked A boolean indicating whether the checkbox should be checked or unchecked.
     */
    public void isChecked(WebElement webElement, boolean shouldBeChecked) {
        seleniumWaits.untilElementDisplayed(webElement);

        // Assert that the checkbox is in the expected state
        if (!webElement.isSelected() && shouldBeChecked) {
            Assertion.equalsTrue(false, "Expected: Checkbox (" + webElement + ") should be checked. But checkbox is unchecked.");
        } else if (webElement.isSelected() && !shouldBeChecked) {
            Assertion.equalsFalse(true, "Expected: Checkbox (" + webElement + ") should not be checked. But checkbox is checked.");
        }
    }
}
