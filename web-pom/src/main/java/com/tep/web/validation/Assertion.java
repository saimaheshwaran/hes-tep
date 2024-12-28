package com.tep.web.validation;

import com.tep.web.base.SeleniumDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Assertion {

    protected SeleniumDriver seleniumDriver;
    protected static SoftAssert softAssert = new SoftAssert();

    static {
        System.setProperty("default_assertion", "soft");
    }

    public Assertion(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }

    public static void setSoftAssert(SoftAssert softAssert) {
        Assertion.softAssert = softAssert;
    }

    public void switchAssertion(String assertionType) {
        System.setProperty("default_assertion", assertionType);
    }

    public static void equalsTrue(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertTrue(condition, message);
        } else {
            assertTrue(condition, message);
        }
    }

    public static void equalsFalse(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertFalse(condition, message);
        } else {
            assertFalse(condition, message);
        }
    }

    public static void equals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertEquals(actualValue, expectedValue, message);
        } else {
            Assert.assertEquals(actualValue, expectedValue, message);
        }
    }

    public static void notEquals(String actualValue, String expectedValue, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertNotEquals(actualValue, expectedValue, message);
        } else {
            Assert.assertNotEquals(actualValue, expectedValue, message);
        }
    }

    public static void equals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertEquals(actualValue, expectedValue);
        } else {
            Assert.assertEquals(actualValue, expectedValue);
        }
    }

    public static void notEquals(Object actualValue, Object expectedValue) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertNotEquals(actualValue, expectedValue);
        } else {
            Assert.assertNotEquals(actualValue, expectedValue);
        }
    }
}
