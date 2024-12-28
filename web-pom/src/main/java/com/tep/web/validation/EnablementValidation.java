package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class EnablementValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public EnablementValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void verify(String objName, boolean elementEnabled) { verify(seleniumDriver.getElement(objName), elementEnabled); }

    public void verify(WebElement webElement, boolean elementEnabled) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (!webElement.isEnabled() && elementEnabled) {
            Assertion.equalsTrue(webElement.isEnabled(), "Expected: Element " + webElement + " should be enabled. But element is not enabled.");
        } else if (webElement.isEnabled() && !elementEnabled) {
            Assertion.equalsFalse(webElement.isEnabled(), "Expected: Element " + webElement + " should not be enabled. But element is enabled.");
        }
    }

}
