package com.tep.web.element.getter;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * GetAttribute class to handle retrieving attributes and text from web elements.
 */
public class GetAttribute {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(GetAttribute.class);

    /**
     * Constructor to initialize the GetAttribute with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public GetAttribute(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("GetAttribute initialized with WebDriver, Waits, and Element.");
    }

    /**
     * Constructor to initialize the GetAttribute with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public GetAttribute(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("GetAttribute initialized with WebDriver, PageObjects, Waits, and Element.");
    }

    /**
     * Retrieves the text of the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return the text of the element.
     */
    public String text(String objName) {
        return text(objects.get(objName));
    }

    /**
     * Retrieves the By locator for the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return the By locator of the element.
     */
    public By by(String objName) {
        return by(objects.get(objName));
    }

    /**
     * Fetches the specified attribute value of the element identified by the object name.
     *
     * @param objName       the name of the object whose locator is to be retrieved.
     * @param attributeName the name of the attribute to fetch.
     * @return the attribute value of the element.
     */
    public String fetch(String objName, String attributeName) {
        return fetch(objects.get(objName), attributeName);
    }

    /**
     * Retrieves the text of the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the text of the element.
     */
    public String text(Map.Entry<String, String> locatorPair) {
        return element.get(locatorPair).getText();
    }

    /**
     * Retrieves the By locator for the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the By locator of the element.
     */
    public By by(Map.Entry<String, String> locatorPair) {
        return element.getBy(locatorPair);
    }

    /**
     * Fetches the specified attribute value of the element identified by the locator pair.
     *
     * @param locatorPair    a Map.Entry containing the locator type and value.
     * @param attributeName  the name of the attribute to fetch.
     * @return the attribute value of the element.
     */
    public String fetch(Map.Entry<String, String> locatorPair, String attributeName) {
        return element.get(locatorPair).getAttribute(attributeName);
    }

    public String text(WebElement webElement) { return webElement.getText(); }

    public String fetch(WebElement webElement, String attributeName) {
        return webElement.getAttribute(attributeName);
    }

}
