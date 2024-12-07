package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;

import java.util.Map;

public class PresenceValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public PresenceValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public PresenceValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void verify(String objName, boolean elementPresent) { verify(objects.get(objName), elementPresent); }

    public void verify(Map.Entry<String, String> locatorPair, boolean elementPresent) {
        if(elementPresent){
            try{
                waits.waitForElementToDisplay(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
            }catch(TimeoutException e){
                Assertion.equalsTrue(false, "Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should be present. But element is not present\n");
            }
        }
        else
        {
            try{
                waits.waitForElementToDisplay(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
                Assertion.equalsFalse(true, "Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should not be present. But element is present\n");
            }catch(TimeoutException ignored){}
        }
    }

    public void verify(String objName, boolean elementPresent, int waitTime) { verify(objects.get(objName), elementPresent, waitTime); }

    public void verify(Map.Entry<String, String> locatorPair, boolean elementPresent, int waitTime) {
        if(elementPresent){
            try{
                waits.waitForElementToDisplay(locatorPair, waitTime);
            }catch(TimeoutException e){
                Assertion.equalsTrue(false, "Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should be present. But element is not present\n");
            }
        }
        else
        {
            try{
                waits.waitForElementToDisplay(locatorPair, waitTime);
                Assertion.equalsFalse(true, "Expected : Element with attribute \""+ locatorPair.getKey() +"="+ locatorPair.getValue() +"\""+ " should not be present. But element is present\n");
            }catch(TimeoutException ignored){}
        }
    }

}
