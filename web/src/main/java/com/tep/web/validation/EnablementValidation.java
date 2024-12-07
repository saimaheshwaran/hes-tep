package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class EnablementValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public EnablementValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public EnablementValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void verify(String objName, boolean elementEnabled) { verify(objects.get(objName), elementEnabled); }

    public void verify(Map.Entry<String, String> locatorPair, boolean elementEnabled) {
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if(!attributeElement.isEnabled() && elementEnabled){
            Assertion.equalsTrue(attributeElement.isEnabled(), "Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should be enabled. But element is not enabled\n");
        } else if(attributeElement.isEnabled() && !elementEnabled){
            Assertion.equalsFalse(attributeElement.isEnabled(),"Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should not be enabled. But element is enabled\n");
        }
    }

}
