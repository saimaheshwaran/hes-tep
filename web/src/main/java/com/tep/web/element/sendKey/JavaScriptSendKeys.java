package com.tep.web.element.sendKey;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class JavaScriptSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public JavaScriptSendKeys(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public JavaScriptSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void sendKeys(String objName, String text) { sendKeys(objects.get(objName), text); }

    public void sendKeys(Map.Entry<String, String> locatorPair, String text) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if(element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].setAttribute('value', arguments[1])",element, text);
            }
        } catch (StaleElementReferenceException e) {
            sendKeys(locatorPair, text);
        }
    }

}
