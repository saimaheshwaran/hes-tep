package com.tep.web.element.radiobutton;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * JavaScriptRadioButton class to handle radio button interactions using JavaScript.
 */
public class JavaScriptRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptRadioButton.class);

    /**
     * Constructor to initialize the JavaScriptRadioButton with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptRadioButton(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptRadioButton initialized with WebDriver, Waits, and Element helpers.");
    }

    /**
     * Constructor to initialize the JavaScriptRadioButton with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptRadioButton initialized with WebDriver, PageObjects, Waits, and Element helpers.");
    }

    /**
     * Selects the radio button identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void select(String objName) {
        select(objects.get(objName));
    }

    /**
     * Selects the radio button identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement radioButton = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("var radio=arguments[0]; if(!radio.checked){radio.checked=true;}", radioButton);
            logger.info("Radio button selected successfully using JavaScript.");
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while selecting radio button using JavaScript.", e);
            select(locatorPair);
        }
    }
}
