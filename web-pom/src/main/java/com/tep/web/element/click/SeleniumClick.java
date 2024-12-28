package com.tep.web.element.click;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.StaleElementReferenceException;

public class SeleniumClick {

    private final SeleniumWaits wait;
    private final SeleniumDriver seleniumDriver;

    public SeleniumClick(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.wait = new SeleniumWaits(seleniumDriver);
    }

    public void click(String objName) { click(seleniumDriver.getElement(objName)); }

    public void click(WebElement element) {
        try {
            wait.untilElementDisplayed(element);
            element.click();
        } catch (StaleElementReferenceException e) { click(element); }

    }

    public void doubleClick(String objName) { doubleClick(seleniumDriver.getElement(objName)); }

    public void doubleClick(WebElement element) {
        try {
            wait.untilElementDisplayed(element);
            element.click();
            element.click();
        } catch (StaleElementReferenceException e) { doubleClick(element); }

    }

}
