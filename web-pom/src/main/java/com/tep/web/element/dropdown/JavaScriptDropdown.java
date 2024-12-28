package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptDropdown {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptDropdown(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void select(String option, String optionBy, String objName) { select(option, optionBy, seleniumDriver.getElement(objName)); }

    public void select(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
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
            assert script != null;
            executor.executeScript(script, webElement, option);
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, webElement);
        }
    }

    public void deselect(String option, String optionBy, String objName) { deselect(option, optionBy, seleniumDriver.getElement(objName)); }

    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
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
            assert script != null;
            executor.executeScript(script, webElement, option);
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, webElement);
        }
    }

    public void deselectAll(String objName) { deselectAll(seleniumDriver.getElement(objName)); }

    public void deselectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = false;}}";
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript(script, webElement);
        } catch (StaleElementReferenceException ignored) {
            deselectAll(webElement);
        }
    }

    public void selectAll(String objName) { selectAll(seleniumDriver.getElement(objName)); }

    public void selectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(!select.options[i].selected){select.options[i].selected = true;}}";
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript(script, webElement);
        } catch (StaleElementReferenceException ignored) {
            selectAll(webElement);
        }
    }

}
