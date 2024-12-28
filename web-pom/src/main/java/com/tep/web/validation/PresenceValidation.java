package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.TimeoutException;

public class PresenceValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public PresenceValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void verify(String objName, boolean elementPresent) { verify(seleniumDriver.getElement(objName), elementPresent ); }

    public void verify(WebElement webElement, boolean elementPresent) {
        if (elementPresent) {
            try {
                seleniumWaits.untilElementDisplayed(webElement);
            } catch (TimeoutException e) {
                Assertion.equalsTrue(false, "Expected: Element " + webElement + " should be present. But element is not present.");
            }
        } else {
            try {
                seleniumWaits.untilElementDisplayed(webElement);
                Assertion.equalsFalse(true, "Expected: Element " + webElement + " should not be present. But element is present.");
            } catch (TimeoutException ignored) {
            }
        }
    }

    public void verify(String objName, boolean elementPresent, int waitTime) { verify(seleniumDriver.getElement(objName), elementPresent, waitTime); }

    public void verify(WebElement webElement, boolean elementPresent, int waitTime) {
        if (elementPresent) {
            try {
                seleniumWaits.untilElementDisplayed(webElement, waitTime);
            } catch (TimeoutException e) {
                Assertion.equalsTrue(false, "Expected: Element " + webElement + " should be present. But element is not present.");
            }
        } else {
            try {
                seleniumWaits.untilElementDisplayed(webElement, waitTime);
                Assertion.equalsFalse(true, "Expected: Element " + webElement + " should not be present. But element is present.");
            } catch (TimeoutException ignored) {
            }
        }
    }

}
