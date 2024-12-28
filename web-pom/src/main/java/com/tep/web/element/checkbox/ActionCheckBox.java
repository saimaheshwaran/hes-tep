package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

public class ActionCheckBox {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;
    private final ActionClick actionClick;

    public ActionCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.actionClick = new ActionClick(seleniumDriver);
    }

    public void check(String objName) { check(seleniumDriver.getElement(objName)); }

    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
            actions.release().perform();
            if (!webElement.isSelected()) {
                actionClick.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            check(webElement);
        }
    }

    public void uncheck(String objName) { uncheck(seleniumDriver.getElement(objName)); }

    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
            actions.release().perform();
            if (webElement.isSelected()) {
                actionClick.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            uncheck(webElement);
        }
    }

    public void toggle(String objName) { toggle(seleniumDriver.getElement(objName)); }

    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException ignored) {
            toggle(webElement);
        }
    }
}
