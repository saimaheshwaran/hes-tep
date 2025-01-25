package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.TimeoutException;

/**
 * The PresenceValidation class provides methods for validating the presence of elements on a web page.
 * It can verify if an element is present or not and also check it within a specified wait time.
 */
public class PresenceValidation {

    /** The instance of SeleniumWaits used for waiting on elements to appear. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the PresenceValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public PresenceValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Verifies whether an element is present or not based on the given boolean flag.
     *
     * @param objName The name or identifier of the element.
     * @param elementPresent A boolean indicating whether the element should be present (true) or not (false).
     */
    public void verify(String objName, boolean elementPresent) {
        verify(seleniumDriver.getElement(objName), elementPresent);
    }

    /**
     * Verifies whether the given element is present or not based on the given boolean flag.
     *
     * @param webElement The WebElement to check.
     * @param elementPresent A boolean indicating whether the element should be present (true) or not (false).
     */
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

    /**
     * Verifies whether an element is present or not based on the given boolean flag within a specified wait time.
     *
     * @param objName The name or identifier of the element.
     * @param elementPresent A boolean indicating whether the element should be present (true) or not (false).
     * @param waitTime The wait time in seconds for the element to appear.
     */
    public void verify(String objName, boolean elementPresent, int waitTime) {
        verify(seleniumDriver.getElement(objName), elementPresent, waitTime);
    }

    /**
     * Verifies whether the given element is present or not within a specified wait time.
     *
     * @param webElement The WebElement to check.
     * @param elementPresent A boolean indicating whether the element should be present (true) or not (false).
     * @param waitTime The wait time in seconds for the element to appear.
     */
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
