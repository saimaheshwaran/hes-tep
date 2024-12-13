package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Assertion class to handle different types of assertions.
 */
public class Assertion {

    protected WebDriver driver;
    protected Element element;
    private static final Logger logger = LoggerFactory.getLogger(Assertion.class);
    protected static SoftAssert softAssert = new SoftAssert();

    /**
     * Static constructor to initialize the default assertion type.
     */
    static {
        System.setProperty("default_assertion", "soft");
    }

    /**
     * Constructor to initialize the Assertion with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public Assertion(WebDriver driver) {
        this.driver = driver;
        this.element = new Element(driver);
        logger.info("Assertion instance created successfully");
    }

    /**
     * Sets the SoftAssert instance for soft assertions.
     *
     * @param softAssert the SoftAssert instance to set.
     */
    public static void setSoftAssert(SoftAssert softAssert) {
        Assertion.softAssert = softAssert;
        logger.info("SoftAssert instance set successfully");
    }

    /**
     * Switches the assertion type between "soft" and "hard".
     *
     * @param assertionType the type of assertion to switch to ("soft" or "hard").
     */
    public void switchAssertion(String assertionType) {
        System.setProperty("default_assertion", assertionType);
    }

    /**
     * Asserts that a condition is true.
     *
     * @param condition the condition to check.
     * @param message   the message to display if the assertion fails.
     */
    public static void equalsTrue(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertTrue(condition, message);
            logger.info("Soft assertion performed: " + message);
        } else {
            assertTrue(condition, message);
            logger.info("Hard assertion performed: " + message);
        }
    }

    /**
     * Asserts that a condition is false.
     *
     * @param condition the condition to check.
     * @param message   the message to display if the assertion fails.
     */
    public static void equalsFalse(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertFalse(condition, message);
            logger.info("Soft assertion that condition is false performed: " + message);
        } else {
            assertFalse(condition, message);
            logger.info("Hard assertion that condition is false performed: " + message);
        }
    }

    /**
     * Asserts that two strings are equal.
     *
     * @param actualValue   the actual value.
     * @param expectedValue the expected value.
     * @param message       the message to display if the assertion fails.
     */
    public static void equals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertEquals(actualValue, expectedValue, message);
            logger.info("Soft assertion for equal performed: " + message);
        } else {
            Assert.assertEquals(actualValue, expectedValue, message);
            logger.info("Hard assertion for equal performed: " + message);
        }
    }

    /**
     * Asserts that two strings are not equal.
     *
     * @param actualValue   the actual value.
     * @param expectedValue the expected value.
     * @param message       the message to display if the assertion fails.
     */
    public static void notEquals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            logger.info("Using soft assertion" + message);
            softAssert.assertNotEquals(actualValue, expectedValue, message);
        } else {
            logger.info("Using hard assertion" + message);
            Assert.assertNotEquals(actualValue, expectedValue, message);
        }
        logger.info("Assertion completed: " + message);
    }

    /**
     * Asserts that two objects are equal.
     *
     * @param actualValue   the actual value.
     * @param expectedValue the expected value.
     */
    public static void equals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            logger.info("Using soft assertion");
            softAssert.assertEquals(actualValue, expectedValue);
        } else {
            logger.info("Using hard assertion");
            Assert.assertEquals(actualValue, expectedValue);
        }
    }

    /**
     * Asserts that two objects are not equal.
     *
     * @param actualValue   the actual value.
     * @param expectedValue the expected value.
     */
    public static void notEquals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            logger.info("Using soft assertion");
            softAssert.assertNotEquals(actualValue, expectedValue);
        } else {
            logger.info("Using hard assertion");
            Assert.assertNotEquals(actualValue, expectedValue);
        }
    }
}
