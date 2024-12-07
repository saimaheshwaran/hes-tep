package com.tep.web.element.sendKey;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;

import java.util.Map;

public class SeleniumSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public SeleniumSendKeys(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public SeleniumSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void sendKeys(String objName, String text) { sendKeys(objects.get(objName), text); }

    public void sendKeys(Map.Entry<String, String> locatorPair, String value) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).sendKeys(value);
        } catch (StaleElementReferenceException e) {
            sendKeys(locatorPair, value);
        }
    }

    public void clearInputs(String objName) { clearInputs(objects.get(objName)); }

    public void clearInputs(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).clear();
        } catch (StaleElementReferenceException e) {
            clearInputs(locatorPair);
        }
    }
}
