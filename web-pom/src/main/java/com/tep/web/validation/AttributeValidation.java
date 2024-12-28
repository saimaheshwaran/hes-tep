package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class AttributeValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public AttributeValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void verify(String objName, String attributeName, String expectedAttributeValue, boolean shouldbeMatched) { verify(seleniumDriver.getElement(objName), attributeName, expectedAttributeValue, shouldbeMatched); }

    public void verify(WebElement webElement, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        seleniumWaits.untilElementDisplayed(webElement);
        String displayedAttributeValue = webElement.getAttribute(attributeName);

        if (shouldBeMatched) {
            assert displayedAttributeValue != null;
            if (!displayedAttributeValue.equals(expectedAttributeValue)) {
                Assertion.equalsTrue(false, "Expected: Attribute value \"" + expectedAttributeValue + "\" should match with actual attribute value \"" + displayedAttributeValue + "\". But attribute value is not matched.");
            }
        } else {
            assert displayedAttributeValue != null;
            if (displayedAttributeValue.equals(expectedAttributeValue)) {
                Assertion.equalsFalse(true, "Expected: Attribute value \"" + expectedAttributeValue + "\" should not match with actual attribute value \"" + displayedAttributeValue + "\". But attribute value is matched.");
            }
        }
    }

}
