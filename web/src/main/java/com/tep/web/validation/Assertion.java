package com.tep.web.validation;

import com.tep.web.base.Element;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static java.lang.System.getProperty;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Assertion {

    protected WebDriver driver;
    protected Element element;
    protected static SoftAssert softAssert = new SoftAssert();

    static { System.setProperty("default_assertion", "soft"); }

    public Assertion(WebDriver driver) {
        this.driver = driver;
        this.element = new Element(driver);
    }

    public static void setSoftAssert(SoftAssert softAssert) {
        Assertion.softAssert = softAssert;
    }

    public void switchAssertion(String assertionType) {
        System.setProperty("default_assertion", assertionType);
    }

    public static void equalsTrue(boolean condition, String message) {
        //Soft assertion
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertTrue(condition,message);
        } else {
            //Hard Assertion
            assertTrue(condition,message);
        }
    }

    public static void equalsFalse(boolean condition, String message) {
        if (getProperty("default_assertion").equals("soft")) {
            softAssert.assertFalse(condition,message);
        } else {
            assertFalse(condition,message);
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
