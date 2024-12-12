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
 * AttributeValidation class to handle attribute validation of web elements.
 */
public class AttributeValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    /**
     * Constructor to initialize the AttributeValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public AttributeValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the AttributeValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public AttributeValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Verifies the attribute value of the element identified by the object name.
     *
     * @param objName                the name of the object whose locator is to be retrieved.
     * @param attributeName          the name of the attribute to verify.
     * @param expectedAttributeValue the expected value of the attribute.
     * @param shouldBeMatched        true if the attribute value should match, false otherwise.
     */
    public void verify(String objName, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        logger.info("Verifying attribute value for object: " + objName);
        verify(objects.get(objName), attributeName, expectedAttributeValue, shouldBeMatched);
    }

    /**
     * Verifies the attribute value of the element identified by the locator pair.
     *
     * @param locatorPair            a Map.Entry containing the locator type and value.
     * @param attributeName          the name of the attribute to verify.
     * @param expectedAttributeValue the expected value of the attribute.
     * @param shouldBeMatched        true if the attribute value should match, false otherwise.
     */
    public void verify(Map.Entry<String, String> locatorPair, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        String displayedAttributeValue = attributeElement.getAttribute(attributeName);

        if (shouldBeMatched) {
            if (!displayedAttributeValue.equals(expectedAttributeValue)) {
                logger.info("Attribute value does not match. Expected: " + expectedAttributeValue + ", Actual: " + displayedAttributeValue);
                Assertion.equalsTrue(false, "Expected: Attribute value \"" + expectedAttributeValue + "\" should match with actual attribute value \"" + displayedAttributeValue + "\". But attribute value is not matched.");
            }
        } else {
            if (displayedAttributeValue.equals(expectedAttributeValue)) {
                Assertion.equalsFalse(true, "Expected: Attribute value \"" + expectedAttributeValue + "\" should not match with actual attribute value \"" + displayedAttributeValue + "\". But attribute value is matched.");
            }
        }
    }
}
