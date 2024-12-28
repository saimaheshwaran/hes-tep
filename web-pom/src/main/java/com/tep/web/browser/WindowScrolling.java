package com.tep.web.browser;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;


public class WindowScrolling {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;
    private final JavascriptExecutor js;

    public WindowScrolling(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.js = (JavascriptExecutor) seleniumDriver.getBrowser();
    }

    public void scrollToEnd() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToElement(String objName) {
        scrollToElement(seleniumDriver.getElement(objName));
    }

    public void scrollToElement(WebElement element) {
        seleniumWaits.untilElementDisplayed(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollVerticalPixels(int pixelsToScroll) {
        js.executeScript("window.scrollBy(0," + pixelsToScroll + ")");
    }

    public void scrollHorizontalPixels(int pixelsToScroll) {
        js.executeScript("window.scrollBy(" + pixelsToScroll + ", 0)");
    }
}
