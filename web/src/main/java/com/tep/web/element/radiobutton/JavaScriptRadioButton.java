package com.tep.web.element.radiobutton;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class JavaScriptRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public JavaScriptRadioButton(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public JavaScriptRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void select(String objName) { select(objects.get(objName)); }

    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement radioButton = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0];" +
                    "if(!checkbox.selected){arguments[0].checked=true;}", radioButton);
        } catch (StaleElementReferenceException e) {
            select(locatorPair);
        }
    }

}
