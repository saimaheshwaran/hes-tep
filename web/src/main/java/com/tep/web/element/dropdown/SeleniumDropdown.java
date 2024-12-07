package com.tep.web.element.dropdown;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.config.Constants;

import java.util.List;
import java.util.Map;

public class SeleniumDropdown {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private SeleniumClick seleniumClick;

    public SeleniumDropdown(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
        seleniumClick = new SeleniumClick(driver);
    }

    public SeleniumDropdown(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
        seleniumClick = new SeleniumClick(driver);
    }

    public void select(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, locatorPair);
        }
    }

    public void deselect(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, locatorPair);
        }
    }

    public void deselectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            selectList.deselectAll();
        } catch (StaleElementReferenceException ignored) {
            deselectAll(locatorPair);
        }
    }

    public void multiSelect(String[] option, Map.Entry<String, String> locatorPair){
        try{
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            List<WebElement> elementCount = selectList.getOptions();
            for(String item : option )
                for(WebElement webElement : elementCount)
                    if (webElement.getText().equals(item))
                        seleniumClick.click(locatorPair);
        }catch (StaleElementReferenceException e){
            multiSelect(option, locatorPair);
        }
    }

    public void select(String option, String optionBy, String objName) { select(option, optionBy, objects.get(objName)); }

    public void deselect(String option, String optionBy, String objName) { deselect(option, optionBy, objects.get(objName)); }

    public void deselectAll(String objName) { deselectAll(objects.get(objName)); }

    public void multiSelect(String[] option, String objName) { multiSelect(option, objects.get(objName)); }

}


