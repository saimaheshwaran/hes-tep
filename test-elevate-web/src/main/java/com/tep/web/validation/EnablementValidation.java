package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The EnablementValidation class provides methods to verify whether a web element is enabled or disabled.
 * It asserts that the element's enabled state matches the expected state (enabled or disabled).
 */
public class EnablementValidation {

    /** The instance of SeleniumWaits used to wait for elements to be displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the EnablementValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public EnablementValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether a web element is enabled or disabled based on its object name.
     *
     * @param objName The object name used to locate the web element.
     * @param elementEnabled A boolean indicating whether the element should be enabled (true) or disabled (false).
     */
    public void verify(String objName, boolean elementEnabled) {
        verify(seleniumDriver.getElement(objName), elementEnabled);
    }

    /**
     * Verifies whether a web element is enabled or disabled based on the given WebElement.
     *
     * @param webElement The WebElement to check.
     * @param elementEnabled A boolean indicating whether the element should be enabled (true) or disabled (false).
     */
    public void verify(WebElement webElement, boolean elementEnabled) {
        seleniumWaits.untilElementDisplayed(webElement);

        // Check if the element's enabled state matches the expected state
        if (!webElement.isEnabled() && elementEnabled) {
            Assertion.equalsTrue(webElement.isEnabled(), "Expected: Element " + webElement + " should be enabled. But element is not enabled.");
        } else if (webElement.isEnabled() && !elementEnabled) {
            Assertion.equalsFalse(webElement.isEnabled(), "Expected: Element " + webElement + " should not be enabled. But element is enabled.");
        }
    }
}
