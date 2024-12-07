package com.tep.web.element.click;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class JavaScriptClick {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public JavaScriptClick(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public JavaScriptClick(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void click(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if(element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();",element);
            }
        } catch (StaleElementReferenceException e) {
            click(locatorPair);
        }
    }

    public void doubleClick(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if(element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();arguments[0].click();",element);
            }
        } catch (StaleElementReferenceException e) {
            doubleClick(locatorPair);
        }
    }

    public void click(String objName) { click(objects.get(objName)); }

    public void doubleClick(String objName) { click(objects.get(objName)); }

}
