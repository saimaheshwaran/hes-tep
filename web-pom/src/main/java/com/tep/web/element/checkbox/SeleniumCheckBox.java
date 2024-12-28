package com.tep.web.element.checkbox;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

public class SeleniumCheckBox {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumClick click;
    private final SeleniumDriver seleniumDriver;

    public SeleniumCheckBox(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.click = new SeleniumClick(seleniumDriver);
    }

    public void check(String objName) { check(seleniumDriver.getElement(objName)); }

    public void check(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (!webElement.isSelected()) {
                click.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            check(webElement);
        }
    }

    public void uncheck(String objName) { uncheck(seleniumDriver.getElement(objName)); }

    public void uncheck(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (webElement.isSelected()) {
                click.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            uncheck(webElement);
        }
    }

    public void toggle(String objName) { toggle(seleniumDriver.getElement(objName)); }

    public void toggle(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            click.click(webElement);
        } catch (StaleElementReferenceException ignored) {
            toggle(webElement);
        }
    }

}
