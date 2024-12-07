package com.tep.web.element.getter;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class GetAttribute {
    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public GetAttribute(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public GetAttribute(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public String text(Map.Entry<String, String> locatorPair){
        return element.get(locatorPair).getText();
    }

    public By by(Map.Entry<String, String> locatorPair){
        return element.getBy(locatorPair);
    }

    public String fetch(Map.Entry<String, String> locatorPair, String attributeName) {
        return element.get(locatorPair).getAttribute(attributeName);
    }

    public String text(String objName) { return text(objects.get(objName)); }

    public By by(String objName, String attributeName) { return by(objects.get(objName)); }

    public String fetch(String objName, String attributeName) { return fetch(objects.get(objName), attributeName); }

}
