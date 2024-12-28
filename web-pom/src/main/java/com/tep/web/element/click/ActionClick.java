package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

public class ActionClick {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public ActionClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void click(String ObjName) { click(seleniumDriver.getElement(ObjName)); }

    public void click(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            click(webElement);
        }
    }

    public void doubleClick(String ObjName) { doubleClick(seleniumDriver.getElement(ObjName)); }

    public void doubleClick(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).doubleClick().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            doubleClick(webElement);
        }
    }

}
