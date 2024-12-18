package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TextValidation class to handle validation of text in web elements.
 */
public class TextValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(TextValidation.class);

    /**
     * Constructor to initialize the TextValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public TextValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the TextValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public TextValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Validates if the text of the element identified by the object name matches the expected text.
     *
     * @param objName      the name of the object whose locator is to be retrieved.
     * @param expectedText the expected text to validate.
     * @param condition    true if the text should match, false otherwise.
     */
    public void isMatching(String objName, String expectedText, boolean condition) {
        isMatching(objects.get(objName), expectedText, condition);
    }

    /**
     * Validates if the text of the element identified by the object name partially matches the expected text.
     *
     * @param objName      the name of the object whose locator is to be retrieved.
     * @param expectedText the expected text to validate.
     * @param condition    true if the text should partially match, false otherwise.
     */
    public void isPartiallyMatching(String objName, String expectedText, boolean condition) {
        logger.info("Verifying if the text of the object '{}' partially matches the expected text '{}' under the condition {}", objName, expectedText, condition);
        isPartiallyMatching(objects.get(objName), expectedText, condition);
    }

    /**
     * Validates if the text of the element identified by the locator pair matches the expected text.
     *
     * @param locatorPair  a Map.Entry containing the locator type and value.
     * @param expectedText the expected text to validate.
     * @param condition    true if the text should match, false otherwise.
     */
    public void isMatching(Map.Entry<String, String> locatorPair, String expectedText, boolean condition) {
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if (condition) {
            if (!expectedText.equals(attributeElement.getText())) {
                logger.info("Text mismatch: Expected '" + expectedText + "' but found '" + attributeElement.getText() + "'");
                Assertion.equalsTrue(false, "Expected: \"" + expectedText + "\" should match with actual text \"" + attributeElement.getText() + "\". But text is not matched.");
            }
        } else {
            if (expectedText.equals(attributeElement.getText())) {
                logger.info("Text should not match but does: Expected '" + expectedText + "' should not match with actual text '" + attributeElement.getText() + "'");
                Assertion.equalsFalse(true, "Expected: \"" + expectedText + "\" should not match with actual text \"" + attributeElement.getText() + "\". But text is matched.");
            }
        }
    }

    /**
     * Validates if the text of the element identified by the locator pair partially matches the expected text.
     *
     * @param locatorPair  a Map.Entry containing the locator type and value.
     * @param expectedText the expected text to validate.
     * @param condition    true if the text should partially match, false otherwise.
     */
    public void isPartiallyMatching(Map.Entry<String, String> locatorPair, String expectedText, boolean condition) {
        logger.info("Checking partial text match for element with locator: {}", locatorPair.getKey());
        logger.info("Expected text: '{}', Condition: {}", expectedText, condition);
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if (condition) {
            if (!attributeElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                logger.info("Expected: Element should have partial text. But actual element text \"" + attributeElement.getText() + "\" does not contain \"" + expectedText + "\".");
                Assertion.equalsTrue(false, "Expected: Element should have partial text. But actual element text \"" + attributeElement.getText() + "\" does not contain \"" + expectedText + "\".");
            }
        } else {
            if (attributeElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                logger.info("Expected: Element should not contain partial text. But actual element text \"" + attributeElement.getText() + "\" does contain \"" + expectedText + "\".");
                Assertion.equalsFalse(true, "Expected: Element should not contain partial text. But actual element text \"" + attributeElement.getText() + "\" does contain \"" + expectedText + "\".");
            }
        }
    }

    public void isMatching(WebElement webElement, String expectedText, boolean condition) {
        waits.waitForPresenceOfElementsLocated(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
        if (condition) {
            if (!expectedText.equals(webElement.getText())) {
                logger.info("Text mismatch: Expected '" + expectedText + "' but found '" + webElement.getText() + "'");
                Assertion.equalsTrue(false, "Expected: \"" + expectedText + "\" should match with actual text \"" + webElement.getText() + "\". But text is not matched.");
            }
        } else {
            if (expectedText.equals(webElement.getText())) {
                logger.info("Text should not match but does: Expected '" + expectedText + "' should not match with actual text '" + webElement.getText() + "'");
                Assertion.equalsFalse(true, "Expected: \"" + expectedText + "\" should not match with actual text \"" + webElement.getText() + "\". But text is matched.");
            }
        }
    }

    public void isPartiallyMatching(WebElement webElement, String expectedText, boolean condition) {
        logger.info("Checking partial text match for element: {}", webElement);
        logger.info("Expected text: '{}', Condition: {}", expectedText, condition);
        waits.waitForPresenceOfElementsLocated(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
        if (condition) {
            if (!webElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                logger.info("Expected: Element should have partial text. But actual element text \"" + webElement.getText() + "\" does not contain \"" + expectedText + "\".");
                Assertion.equalsTrue(false, "Expected: Element should have partial text. But actual element text \"" + webElement.getText() + "\" does not contain \"" + expectedText + "\".");
            }
        } else {
            if (webElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                logger.info("Expected: Element should not contain partial text. But actual element text \"" + webElement.getText() + "\" does contain \"" + expectedText + "\".");
                Assertion.equalsFalse(true, "Expected: Element should not contain partial text. But actual element text \"" + webElement.getText() + "\" does contain \"" + expectedText + "\".");
            }
        }
    }
}
