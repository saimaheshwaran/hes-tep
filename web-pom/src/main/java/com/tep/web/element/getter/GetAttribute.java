package com.tep.web.element.getter;

import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The GetAttribute class provides methods to fetch the text or attribute value of a given WebElement.
 * This class helps interact with elements and retrieve specific properties such as the text content
 * or other attributes of the elements.
 */
public class GetAttribute {

    /**
     * An instance of SeleniumDriver used to interact with the browser and locate elements.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor for the GetAttribute class. Initializes the SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public GetAttribute(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
    }

    /**
     * Retrieves the text content of the element identified by its object name.
     *
     * @param objName The object name of the element.
     * @return The text content of the element.
     */
    public String text(String objName) {
        return text(seleniumDriver.getElement(objName));
    }

    /**
     * Retrieves the text content of the provided WebElement.
     *
     * @param webElement The WebElement whose text content is to be fetched.
     * @return The text content of the WebElement.
     */
    public String text(WebElement webElement) {
        return webElement.getText();  // Returns the visible text of the element.
    }

    /**
     * Retrieves the value of a specific attribute of the element identified by its object name.
     *
     * @param objName       The object name of the element.
     * @param attributeName The name of the attribute whose value is to be fetched.
     * @return The value of the specified attribute.
     */
    public String fetch(String objName, String attributeName) {
        return fetch(seleniumDriver.getElement(objName), attributeName);
    }

    /**
     * Retrieves the value of a specific attribute of the provided WebElement.
     *
     * @param webElement    The WebElement whose attribute value is to be fetched.
     * @param attributeName The name of the attribute whose value is to be fetched.
     * @return The value of the specified attribute.
     */
    public String fetch(WebElement webElement, String attributeName) {
        return webElement.getAttribute(attributeName);  // Returns the value of the specified attribute.
    }
}
