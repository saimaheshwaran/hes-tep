package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The TextValidation class provides methods to validate text inside web elements.
 * It checks whether the text in the element matches exactly or partially with the expected text.
 */
public class TextValidation {

    /** The instance of SeleniumWaits used for waiting on elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the TextValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public TextValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether the text of a web element exactly matches the expected text based on the given condition.
     *
     * @param objName The name or identifier of the web element.
     * @param expectedText The expected text that should match the element's text.
     * @param condition A boolean flag indicating whether the texts should match (true) or not match (false).
     */
    public void isMatching(String objName, String expectedText, boolean condition) {
        isMatching(seleniumDriver.getElement(objName), expectedText, condition);
    }

    /**
     * Verifies whether the text of a web element exactly matches the expected text based on the given condition.
     *
     * @param webElement The WebElement to validate.
     * @param expectedText The expected text that should match the element's text.
     * @param condition A boolean flag indicating whether the texts should match (true) or not match (false).
     */
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

    /**
     * Verifies whether the text of a web element partially matches the expected text based on the given condition.
     *
     * @param objName The name or identifier of the web element.
     * @param expectedText The expected text that should partially match the element's text.
     * @param condition A boolean flag indicating whether the texts should partially match (true) or not match (false).
     */
    public void isPartiallyMatching(String objName, String expectedText, boolean condition) {
        isPartiallyMatching(seleniumDriver.getElement(objName), expectedText, condition);
    }

    /**
     * Verifies whether the text of a web element partially matches the expected text based on the given condition.
     *
     * @param webElement The WebElement to validate.
     * @param expectedText The expected text that should partially match the element's text.
     * @param condition A boolean flag indicating whether the texts should partially match (true) or not match (false).
     */
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
