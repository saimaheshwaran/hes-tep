package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptCheckBox {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void check(String objName) { check(seleniumDriver.getElement(objName)); }

    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript("var checkbox=arguments[0]; if(!checkbox.checked){checkbox.checked=true;}", webElement);
        } catch (StaleElementReferenceException e) {
            check(webElement);
        }
    }

    public void uncheck(String objName) { uncheck(seleniumDriver.getElement(objName)); }

    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript("var checkbox=arguments[0]; if(checkbox.checked){checkbox.checked=false;}", webElement);
        } catch (StaleElementReferenceException e) {
            uncheck(webElement);
        }
    }

    public void toggle(String objName) { toggle(seleniumDriver.getElement(objName)); }

    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript("var checkbox=arguments[0]; checkbox.checked=!checkbox.checked;", webElement);
        } catch (StaleElementReferenceException e) {
            toggle(webElement);
        }
    }
}
