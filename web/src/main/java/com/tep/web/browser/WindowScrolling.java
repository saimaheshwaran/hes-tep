package com.tep.web.browser;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;

import java.util.Map;

public class WindowScrolling {

    private WebDriver driver;
    private PageObjects objects;
    private Waits waits;
    private Element element;
    private JavascriptExecutor js;

    public WindowScrolling(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public WindowScrolling(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void scrollToEnd(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop(){
        js.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToElement(String objName) { scrollToElement(objects.get(objName));}

    public void scrollToElement(Map.Entry<String, String> locatorPair){
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
        js.executeScript("arguments[0].scrollIntoView();", element.get(locatorPair) );
    }

    public void scrollVerticalPixels(int PixelsToScroll){
        js.executeScript("window.scrollBy(0,"+PixelsToScroll+")");
    }

    public void scrollHorizontalPixels(int PixelsToScroll){
        js.executeScript("window.scrollBy(0,"+PixelsToScroll+")");
    }

}
