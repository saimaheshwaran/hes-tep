package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class CheckBoxValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public CheckBoxValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void isChecked(String objName, boolean shouldBeChecked) { isChecked(seleniumDriver.getElement(objName), shouldBeChecked); }

    public void isChecked(WebElement webElement, boolean shouldBeChecked) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (!webElement.isSelected() && shouldBeChecked) {
            Assertion.equalsTrue(false, "Expected: Checkbox (" + webElement + ") should be checked. But checkbox is unchecked.");
        } else if (webElement.isSelected() && !shouldBeChecked) {
            Assertion.equalsFalse(true, "Expected: Checkbox (" + webElement + ") should not be checked. But checkbox is checked.");
        }
    }

}
