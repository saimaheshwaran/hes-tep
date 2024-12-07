package com.tep.web.element.dropdown;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class JavascritpDropdown {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public JavascritpDropdown(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public JavascritpDropdown(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void select(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = true;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = true; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }";
            }
            executor.executeScript(script,element,option);
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, locatorPair);
        }
    }

    public void deselect(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = false;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = false; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = false; } }";
            }
            executor.executeScript(script,element,option);
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, locatorPair);
        }
    }

    public void deselectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = false;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, element);
        } catch (StaleElementReferenceException ignored) {
            deselectAll(locatorPair);
        }
    }

    public void selectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = true;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, element);
        } catch (StaleElementReferenceException ignored) {
            selectAll(locatorPair);
        }
    }

    public void select(String option, String optionBy, String objName) { select(option, optionBy, objects.get(objName)); }

    public void deselect(String option, String optionBy, String objName) { deselect(option, optionBy, objects.get(objName)); }

    public void selectAll(String objName) { selectAll(objects.get(objName)); }

    public void deselectAll(String objName) { deselectAll(objects.get(objName)); }

}
