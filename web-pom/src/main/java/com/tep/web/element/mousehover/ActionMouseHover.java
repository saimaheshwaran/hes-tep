package com.tep.web.element.mousehover;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

public class ActionMouseHover {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public ActionMouseHover(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void mouseHover(String objName) { mouseHover(seleniumDriver.getElement(objName)); }

    public void mouseHover(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
        } catch (StaleElementReferenceException e) {
            mouseHover(webElement);
        }
    }

}
