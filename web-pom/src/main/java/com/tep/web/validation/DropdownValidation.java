package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public DropdownValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void isSelected(String objName, String type, String option, boolean shouldBeSelected) { isSelected(seleniumDriver.getElement(objName), type, option, shouldBeSelected); }

    public void isSelected(WebElement webElement, String type, String option, boolean shouldBeSelected) {
        seleniumWaits.untilElementDisplayed(webElement);
        Select selectList = new Select(webElement);
        String actualValue = "";
        if (type.equals("text")) {
            actualValue = selectList.getFirstSelectedOption().getText();
        } else {
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        }
        assert actualValue != null;
        if (!actualValue.equals(option) && shouldBeSelected) {
            Assertion.equalsTrue(false, "Expected: " + option + " should be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        } else if (actualValue.equals(option) && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: " + option + " should not be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        }
    }

}
