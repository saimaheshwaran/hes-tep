package com.tep.web.element.getter;

import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class GetAttribute {

    private final SeleniumDriver seleniumDriver;

    public GetAttribute(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }

    public String text(String objName) { return text(seleniumDriver.getElement(objName)); }

    public String text(WebElement webElement) { return webElement.getText(); }

    public String fetch(String objName, String attributeName) { return fetch(seleniumDriver.getElement(objName), attributeName); }

    public String fetch(WebElement webElement, String attributeName) {
        return webElement.getAttribute(attributeName);
    }

}
