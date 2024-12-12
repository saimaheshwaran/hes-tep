package com.tep.web.element.sendKey;

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
 * JavaScriptSendKeys class to handle sending keys to web elements using JavaScript.
 */
public class JavaScriptSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    /**
     * Constructor to initialize the JavaScriptSendKeys with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptSendKeys(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the JavaScriptSendKeys with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Sends the specified text to the element identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param text    the text to send.
     */
    public void sendKeys(String objName, String text) {
        sendKeys(objects.get(objName), text);
    }

    /**
     * Sends the specified text to the element identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param text        the text to send.
     */
    public void sendKeys(Map.Entry<String, String> locatorPair, String text) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            if (element.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].setAttribute('value', arguments[1])", element, text);
            }
        } catch (StaleElementReferenceException e) {
            sendKeys(locatorPair, text);
        }
    }
}
