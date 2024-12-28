package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class RadioButtonValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public RadioButtonValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void isSelected(String objName, boolean shouldBeSelected) {isSelected(seleniumDriver.getElement(objName), shouldBeSelected );}

    public void isSelected(WebElement webElement, boolean shouldBeSelected) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (!webElement.isSelected() && shouldBeSelected) {
            Assertion.equalsTrue(false, "Expected: Radio button (" + webElement + ") should be selected. But Radio button is not selected.");
        } else if (webElement.isSelected() && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: Radio button (" + webElement + ") should not be selected. But Radio button is selected.");
        }
    }
}
