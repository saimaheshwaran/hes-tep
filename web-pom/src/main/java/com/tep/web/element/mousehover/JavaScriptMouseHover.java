package com.tep.web.element.mousehover;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class JavaScriptMouseHover {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public JavaScriptMouseHover(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void mouseHover(String objName) { mouseHover(seleniumDriver.getElement(objName)); }

    public void mouseHover(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} " +
                    "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver.getBrowser();
            executor.executeScript(mouseOverScript, webElement);
            seleniumWaits.sleep(5);
        } catch (StaleElementReferenceException e) {
            mouseHover(webElement);
        }
    }

}
