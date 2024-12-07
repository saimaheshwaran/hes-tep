package com.tep.web.element.getter;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.List;
import java.util.Map;

public class GetElement {
    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public GetElement(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public GetElement(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public WebElement fetch(Map.Entry<String, String> locatorPair) {
        return element.get(locatorPair);
    }

    public List<WebElement> fetchAll(Map.Entry<String, String> locatorPair) {
        return waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
    }

    public boolean isSelected(Map.Entry<String, String> locatorPair) {
        boolean elementSelected = false;
        try {
            WebElement element = fetch(locatorPair);
            elementSelected = element.isSelected();
        }catch(StaleElementReferenceException e){
            isSelected(locatorPair);
        }
        return elementSelected;
    }

    public boolean isEnabled(Map.Entry<String, String> locatorPair) {
        boolean elementEnabled = false;
        try {
            WebElement element = fetch(locatorPair);
            elementEnabled = element.isEnabled();
        }catch(StaleElementReferenceException e){
            isSelected(locatorPair);
        }
        return elementEnabled;
    }

    public boolean isDisplayed(Map.Entry<String, String> locatorPair) {
        boolean elementDisplayed = false;
        try {
            WebElement element = fetch(locatorPair);
            elementDisplayed = element.isEnabled();
        }catch(StaleElementReferenceException e){
            isSelected(locatorPair);
        } catch (NoSuchElementException e) {
            elementDisplayed = false;
        }
        return elementDisplayed;
    }

    public WebElement fetch(String objName) { return fetch(objects.get(objName)); }

    public List<WebElement> fetchAll(String objName) { return fetchAll(objects.get(objName)); }

    public boolean isSelected(String objName) { return isSelected(objects.get(objName)); }

    public boolean isEnabled(String objName) { return isEnabled(objects.get(objName)); }

    public boolean isDisplayed(String objName) { return isDisplayed(objects.get(objName)); }

}
