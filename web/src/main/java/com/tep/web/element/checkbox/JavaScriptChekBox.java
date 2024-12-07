package com.tep.web.element.checkbox;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class JavaScriptChekBox {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public JavaScriptChekBox(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public JavaScriptChekBox(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void check(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0];" +
                    "if(!checkbox.selected){arguments[0].checked=true;}", checkBox);
        } catch (StaleElementReferenceException e) {
            check(locatorPair);
        }
    }

    public void uncheck(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0];" +
                    "if(checkbox.selected){arguments[0].checked=false;}", checkBox);
        } catch (StaleElementReferenceException e) {
            uncheck(locatorPair);
        }
    }

    public void toggle(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0];" +
                    "arguments[0].checked=true;", checkBox);
        } catch (StaleElementReferenceException e) {
            toggle(locatorPair);
        }
    }

    public void check(String objName) { check(objects.get(objName)); }

    public void uncheck(String objName) { uncheck(objects.get(objName)); }

    public void toggle(String objName) { toggle(objects.get(objName)); }

}
