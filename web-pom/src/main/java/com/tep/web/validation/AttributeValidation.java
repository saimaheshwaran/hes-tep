package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The AttributeValidation class provides methods to verify the value of an element's attribute.
 * It allows you to compare the actual value of an attribute with the expected value,
 * and assert whether they should match or not.
 */
public class AttributeValidation {

    /** The instance of SeleniumWaits used to wait for elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the AttributeValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public AttributeValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies the value of an element's attribute against an expected value.
     * This method checks whether the actual attribute value matches the expected value or not,
     * based on the value of the `shouldBeMatched` parameter.
     *
     * @param objName The object name used to locate the element.
     * @param attributeName The name of the attribute to verify.
     * @param expectedAttributeValue The expected value of the attribute.
     * @param shouldBeMatched A boolean indicating whether the attribute value should match the expected value.
     */
    public void verify(String objName, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        verify(seleniumDriver.getElement(objName), attributeName, expectedAttributeValue, shouldBeMatched);
    }

    /**
     * Verifies the value of an element's attribute against an expected value.
     * This method checks whether the actual attribute value matches the expected value or not,
     * based on the value of the `shouldBeMatched` parameter.
     *
     * @param webElement The WebElement whose attribute is being verified.
     * @param attributeName The name of the attribute to verify.
     * @param expectedAttributeValue The expected value of the attribute.
     * @param shouldBeMatched A boolean indicating whether the attribute value should match the expected value.
     */
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
