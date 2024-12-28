package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * The DropdownValidation class provides methods to verify the selected option in a dropdown element.
 * It checks whether a specific option is selected or not, and asserts if the actual selection matches the expected selection.
 */
public class DropdownValidation {

    /** The instance of SeleniumWaits used to wait for elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the DropdownValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public DropdownValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether a specific option is selected from a dropdown element identified by its object name.
     *
     * @param objName The object name used to locate the dropdown element.
     * @param type The type of the value to compare ("text" or "value").
     * @param option The option to check if it's selected in the dropdown.
     * @param shouldBeSelected A boolean indicating whether the option should be selected or not.
     */
    public void isSelected(String objName, String type, String option, boolean shouldBeSelected) {
        isSelected(seleniumDriver.getElement(objName), type, option, shouldBeSelected);
    }

    /**
     * Verifies whether a specific option is selected from a dropdown WebElement.
     *
     * @param webElement The WebElement representing the dropdown element.
     * @param type The type of the value to compare ("text" or "value").
     * @param option The option to check if it's selected in the dropdown.
     * @param shouldBeSelected A boolean indicating whether the option should be selected or not.
     */
    public void isSelected(WebElement webElement, String type, String option, boolean shouldBeSelected) {
        seleniumWaits.untilElementDisplayed(webElement);
        Select selectList = new Select(webElement);
        String actualValue = "";

        // Get the selected option text or value based on the provided type
        if (type.equals("text")) {
            actualValue = selectList.getFirstSelectedOption().getText();
        } else {
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        }

        assert actualValue != null;

        // Assert if the selected option matches the expected value
        if (!actualValue.equals(option) && shouldBeSelected) {
            Assertion.equalsTrue(false, "Expected: " + option + " should be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        } else if (actualValue.equals(option) && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: " + option + " should not be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        }
    }
}
