package com.tep.web.element.mousehover;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

/**
 * JavaScriptMouseHover class to handle mouse hover actions on web elements using JavaScript.
 */
public class JavaScriptMouseHover {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    /**
     * Constructor to initialize the JavaScriptMouseHover with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptMouseHover(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the JavaScriptMouseHover with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptMouseHover(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Performs a mouse hover action on the element identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void mouseHover(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} " +
                    "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, element);
            waits.sleep(5);
        } catch (StaleElementReferenceException e) {
            mouseHover(locatorPair);
        }
    }

    /**
     * Performs a mouse hover action on the element identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void mouseHover(String objName) {
        mouseHover(objects.get(objName));
    }
}
