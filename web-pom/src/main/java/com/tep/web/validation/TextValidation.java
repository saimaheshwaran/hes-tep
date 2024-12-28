package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class TextValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public TextValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void isMatching(String objName, String expectedText, boolean condition) { isMatching(seleniumDriver.getElement(objName), expectedText, condition); }

    public void isMatching(WebElement webElement, String expectedText, boolean condition) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (condition) {
            if (!expectedText.equals(webElement.getText())) {
                Assertion.equalsTrue(false, "Expected: \"" + expectedText + "\" should match with actual text \"" + webElement.getText() + "\". But text is not matched.");
            }
        } else {
            if (expectedText.equals(webElement.getText())) {
                Assertion.equalsFalse(true, "Expected: \"" + expectedText + "\" should not match with actual text \"" + webElement.getText() + "\". But text is matched.");
            }
        }
    }

    public void isPartiallyMatching(String objName, String expectedText, boolean condition) { isPartiallyMatching(seleniumDriver.getElement(objName), expectedText, condition); }

    public void isPartiallyMatching(WebElement webElement, String expectedText, boolean condition) {
        seleniumWaits.untilElementDisplayed(webElement);
        if (condition) {
            if (!webElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                Assertion.equalsTrue(false, "Expected: Element should have partial text. But actual element text \"" + webElement.getText() + "\" does not contain \"" + expectedText + "\".");
            }
        } else {
            if (webElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                Assertion.equalsFalse(true, "Expected: Element should not contain partial text. But actual element text \"" + webElement.getText() + "\" does contain \"" + expectedText + "\".");
            }
        }
    }

}
