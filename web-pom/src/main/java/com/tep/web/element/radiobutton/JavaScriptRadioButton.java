package com.tep.web.element.radiobutton;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptRadioButton {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptRadioButton(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void select(String objName) { select(seleniumDriver.getElement(objName)); }

    public void select(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript("var radio=arguments[0]; if(!radio.checked){radio.checked=true;}", webElement);
        } catch (StaleElementReferenceException ignored) {
            select(webElement);
        }
    }

}
