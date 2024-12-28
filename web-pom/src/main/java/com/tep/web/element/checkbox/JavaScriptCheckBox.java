package com.tep.web.element.checkbox;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * JavaScriptCheckBox class to handle checkbox interactions using JavaScript.
 */
public class JavaScriptCheckBox {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptCheckBox.class);

    /**
     * Constructor to initialize the JavaScriptCheckBox with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptCheckBox(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptCheckBox initialized with WebDriver, Waits and Element helpers.");
    }

    /**
     * Constructor to initialize the JavaScriptCheckBox with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptCheckBox(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptCheckBox initialized with WebDriver, Waits, PageObjects and Element helpers.");
    }

    /**
     * Checks the checkbox identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void check(String objName) {
        check(objects.get(objName));
    }

    /**
     * Unchecks the checkbox identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void uncheck(String objName) {
        uncheck(objects.get(objName));
    }

    /**
     * Toggles the checkbox identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void toggle(String objName) {
        toggle(objects.get(objName));
    }

    /**
     * Checks the checkbox identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void check(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; if(!checkbox.checked){checkbox.checked=true;}", checkBox);
            logger.info("Checkbox checked successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during check, retrying.", e);
            check(locatorPair);
        }
    }

    /**
     * Unchecks the checkbox identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void uncheck(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; if(checkbox.checked){checkbox.checked=false;}", checkBox);
            logger.info("Checkbox unchecked successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during uncheck, retrying.", e);
            uncheck(locatorPair);
        }
    }

    /**
     * Toggles the checkbox identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void toggle(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; checkbox.checked=!checkbox.checked;", checkBox);
            logger.info("Checkbox state toggled successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during toggle, retrying.", e);
            toggle(locatorPair);
        }
    }

    public void check(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; if(!checkbox.checked){checkbox.checked=true;}", webElement);
            logger.info("Checkbox checked successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during check, retrying.", e);
            check(webElement);
        }
    }

    public void uncheck(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; if(checkbox.checked){checkbox.checked=false;}", webElement);
            logger.info("Checkbox unchecked successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during uncheck, retrying.", e);
            uncheck(webElement);
        }
    }

    public void toggle(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var checkbox=arguments[0]; checkbox.checked=!checkbox.checked;", webElement);
            logger.info("Checkbox state toggled successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during toggle, retrying.", e);
            toggle(webElement);
        }
    }

}
