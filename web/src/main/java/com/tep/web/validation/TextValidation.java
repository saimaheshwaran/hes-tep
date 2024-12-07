package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class TextValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public TextValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public TextValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void isMatching(String objName, String expectedText, boolean condition) { isMatching(objects.get(objName), expectedText, condition); }

    public void isMatching(Map.Entry<String, String> locatorPair, String expectedText, boolean condition) {
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if (condition) {
            if (!expectedText.equals(attributeElement.getText())) {
                Assertion.equalsTrue(false, "Expected : \"" + expectedText + "\" should match with actual Text \"" +  attributeElement.getText()+ "\". But Text is Not Matched\n");
            }
        }else {
            if (expectedText.equals(attributeElement.getText())){
                Assertion.equalsFalse(true, "Expected : \"" + expectedText + "\" should not match with actual text \"" +  attributeElement.getText() + "\". But Text is Matched\n");
            }
        }
    }

    public void isPartiallyMatching(String objName, String expectedText, boolean condition) { isPartiallyMatching(objects.get(objName), expectedText, condition); }

    public void isPartiallyMatching(Map.Entry<String, String> locatorPair, String expectedText, boolean condition) {
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if (condition){
            if(!attributeElement.getText().toLowerCase().contains(expectedText.toLowerCase())){
                Assertion.equalsTrue(false, "Expected : Element should have partial text. But actual element text \"" + attributeElement.getText() + "\" does not contain \"" +  expectedText+ "\"\n");
            }
        }else {
            if(attributeElement.getText().toLowerCase().contains(expectedText.toLowerCase())){
                Assertion.equalsFalse(true, "Expected : Element should not contain partial text. But actual element text \"" + attributeElement.getText() + "\" does contain \"" +  expectedText+ "\"\n");
            }
        }
    }

}
