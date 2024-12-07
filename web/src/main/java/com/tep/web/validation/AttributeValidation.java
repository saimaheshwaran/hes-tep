package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class AttributeValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public AttributeValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public AttributeValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void verify(String objName, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        verify(objects.get(objName), attributeName, expectedAttributeValue, shouldBeMatched);
    }

    public void verify(Map.Entry<String, String> locatorPair, String attributeName, String expectedAttributeValue, boolean shouldBeMatched) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        String displayedAttributeValue = attributeElement.getAttribute(attributeName);
        String actualValue = "";
        if (shouldBeMatched) {
            if (!displayedAttributeValue.equals(expectedAttributeValue))
                Assertion.equalsTrue(false, "Expected : Attribute value \"" + expectedAttributeValue + "\" should match with actual attribute value  \"" +  displayedAttributeValue+ "\". But attribute value is Not Matched\n");
        } else {
            if (displayedAttributeValue.equals(expectedAttributeValue))
                Assertion.equalsFalse(true, "Expected : Attribute value \"" + expectedAttributeValue + "\" should not match with actual attribute value  \"" +  displayedAttributeValue+ "\". But attribute value is Matched\n");
        }
    }

}
