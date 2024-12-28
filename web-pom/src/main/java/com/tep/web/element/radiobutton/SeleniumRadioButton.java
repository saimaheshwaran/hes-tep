package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

public class SeleniumRadioButton {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;
    private final SeleniumClick seleniumClick;

    public SeleniumRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.seleniumClick = new SeleniumClick(seleniumDriver);
    }

    public void select(String objName) { select(seleniumDriver.getElement(objName)); }

    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            if (!webElement.isSelected()) {
                seleniumClick.click(webElement);
            }
        } catch (StaleElementReferenceException ignored) {
            select(webElement);
        }
    }
}
