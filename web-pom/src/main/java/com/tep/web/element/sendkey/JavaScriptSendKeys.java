package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptSendKeys {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void sendKeys(WebElement webElement, String text) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (webElement.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
                executor.executeScript("arguments[0].setAttribute('value', arguments[1])", webElement, text);
            }
        } catch (StaleElementReferenceException e) {
            sendKeys(webElement, text);
        }
    }

}
