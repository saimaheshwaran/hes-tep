package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.StaleElementReferenceException;

public class SeleniumSendKeys {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public SeleniumSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void sendKeys(String objName, String text) { sendKeys(seleniumDriver.getElement(objName), text); }

    public void sendKeys(WebElement webElement, String value) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            webElement.sendKeys(value);
        } catch (StaleElementReferenceException e) {
            webElement.sendKeys(value);
        }
    }

    public void clearInputs(String objName, String text) { clearInputs(seleniumDriver.getElement(objName)); }

    public void clearInputs(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            webElement.clear();
        } catch (StaleElementReferenceException e) {
            clearInputs(webElement);
        }
    }

}
