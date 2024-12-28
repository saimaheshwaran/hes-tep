package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptClick {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void click(String objName) { click(seleniumDriver.getElement(objName)); }

    public void click(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (webElement.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
                executor.executeScript("arguments[0].click();", webElement);
            }
        } catch (StaleElementReferenceException e) {
            click(webElement);
        }
    }

    public void doubleClick(String objName) { doubleClick(seleniumDriver.getElement(objName)); }

    public void doubleClick(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (webElement.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
                executor.executeScript("arguments[0].click(); arguments[0].click();", webElement);
            }
        } catch (StaleElementReferenceException e) {
            doubleClick(webElement);
        }
    }

}
