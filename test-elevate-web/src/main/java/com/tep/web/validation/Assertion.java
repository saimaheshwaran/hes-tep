package com.tep.web.validation;

import com.tep.web.base.SeleniumDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * The Assertion class provides methods for both hard and soft assertions.
 * It allows switching between different assertion types and helps in validating
 * expected conditions during test execution. Soft assertions do not stop the test execution
 * immediately upon failure, whereas hard assertions halt the execution when a failure occurs.
 */
public class Assertion {

    /** The instance of SeleniumDriver used to interact with the browser. */
    protected SeleniumDriver seleniumDriver;

    /** The instance of SoftAssert used for soft assertions in tests. */
    protected static SoftAssert softAssert = new SoftAssert();

    /**
     * Static block to set the default assertion type to soft.
     * The system property "default_assertion" is set to "soft" by default.
     */
    static {
        System.setProperty("default_assertion", "soft");
    }

    /**
     * Constructor to initialize the Assertion class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public Assertion(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }

    /**
     * Sets the SoftAssert instance to be used for soft assertions.
     *
     * @param softAssert The SoftAssert instance to set.
     */
    public static void setSoftAssert(SoftAssert softAssert) {
        Assertion.softAssert = softAssert;
    }

    /**
     * Switches the type of assertion to be used (hard or soft).
     * This method updates the "default_assertion" system property to either "soft" or another value.
     *
     * @param assertionType The type of assertion to be used ("soft" or "hard").
     */
    public void switchAssertion(String assertionType) {
        System.setProperty("default_assertion", assertionType);
    }

    /**
     * Asserts that the provided condition is true.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param condition The condition to assert as true.
     * @param message The message to display if the assertion fails.
     */
    public static void equalsTrue(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertTrue(condition, message);
        } else {
            assertTrue(condition, message);
        }
    }

    /**
     * Asserts that the provided condition is false.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param condition The condition to assert as false.
     * @param message The message to display if the assertion fails.
     */
    public static void equalsFalse(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertFalse(condition, message);
        } else {
            assertFalse(condition, message);
        }
    }

    /**
     * Asserts that the actual value equals the expected value.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param actualValue The actual value to compare.
     * @param expectedValue The expected value to compare against.
     * @param message The message to display if the assertion fails.
     */
    public static void equals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertEquals(actualValue, expectedValue, message);
        } else {
            Assert.assertEquals(actualValue, expectedValue, message);
        }
    }

    /**
     * Asserts that the actual value does not equal the expected value.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param actualValue The actual value to compare.
     * @param expectedValue The expected value to compare against.
     * @param message The message to display if the assertion fails.
     */
    public static void notEquals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertNotEquals(actualValue, expectedValue, message);
        } else {
            Assert.assertNotEquals(actualValue, expectedValue, message);
        }
    }

    /**
     * Asserts that the actual object equals the expected object.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param actualValue The actual object to compare.
     * @param expectedValue The expected object to compare against.
     */
    public static void equals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertEquals(actualValue, expectedValue);
        } else {
            Assert.assertEquals(actualValue, expectedValue);
        }
    }

    /**
     * Asserts that the actual object does not equal the expected object.
     * If the default assertion type is soft, a soft assertion is used; otherwise, a hard assertion is used.
     *
     * @param actualValue The actual object to compare.
     * @param expectedValue The expected object to compare against.
     */
    public static void notEquals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertNotEquals(actualValue, expectedValue);
        } else {
            Assert.assertNotEquals(actualValue, expectedValue);
        }
    }
}
