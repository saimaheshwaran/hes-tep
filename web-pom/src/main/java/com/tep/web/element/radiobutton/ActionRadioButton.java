package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

public class ActionRadioButton {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;
    private final ActionClick actionClick;

    public ActionRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.actionClick = new ActionClick(seleniumDriver);
    }

    public void select(String objName) { select(seleniumDriver.getElement(objName)); }

    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
            if (!webElement.isSelected()) {
                actionClick.click(webElement);
            }
            actions.release().perform();
        } catch (StaleElementReferenceException ignored) {
            select(webElement);
        }
    }

}
