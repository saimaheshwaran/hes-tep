package com.tep.web.element.getter;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.List;
import java.util.Map;

/**
 * GetElement class to handle retrieving web elements and their properties.
 */
public class GetElement {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    /**
     * Constructor to initialize the GetElement with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public GetElement(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the GetElement with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public GetElement(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Fetches the web element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the web element.
     */
    public WebElement fetch(Map.Entry<String, String> locatorPair) {
        return element.get(locatorPair);
    }

    /**
     * Fetches all web elements identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the list of web elements.
     */
    public List<WebElement> fetchAll(Map.Entry<String, String> locatorPair) {
        return waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Checks if the web element identified by the locator pair is selected.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is selected, false otherwise.
     */
    public boolean isSelected(Map.Entry<String, String> locatorPair) {
        boolean elementSelected = false;
        try {
            WebElement element = fetch(locatorPair);
            elementSelected = element.isSelected();
        } catch (StaleElementReferenceException e) {
            elementSelected = isSelected(locatorPair);
        }
        return elementSelected;
    }

    /**
     * Checks if the web element identified by the locator pair is enabled.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is enabled, false otherwise.
     */
    public boolean isEnabled(Map.Entry<String, String> locatorPair) {
        boolean elementEnabled = false;
        try {
            WebElement element = fetch(locatorPair);
            elementEnabled = element.isEnabled();
        } catch (StaleElementReferenceException e) {
            elementEnabled = isEnabled(locatorPair);
        }
        return elementEnabled;
    }

    /**
     * Checks if the web element identified by the locator pair is displayed.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(Map.Entry<String, String> locatorPair) {
        boolean elementDisplayed = false;
        try {
            WebElement element = fetch(locatorPair);
            elementDisplayed = element.isDisplayed();
        } catch (StaleElementReferenceException e) {
            elementDisplayed = isDisplayed(locatorPair);
        } catch (NoSuchElementException e) {
            elementDisplayed = false;
        }
        return elementDisplayed;
    }

    /**
     * Fetches the web element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return the web element.
     */
    public WebElement fetch(String objName) {
        return fetch(objects.get(objName));
    }

    /**
     * Fetches all web elements identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return the list of web elements.
     */
    public List<WebElement> fetchAll(String objName) {
        return fetchAll(objects.get(objName));
    }

    /**
     * Checks if the web element identified by the object name is selected.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return true if the element is selected, false otherwise.
     */
    public boolean isSelected(String objName) {
        return isSelected(objects.get(objName));
    }

    /**
     * Checks if the web element identified by the object name is enabled.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return true if the element is enabled, false otherwise.
     */
    public boolean isEnabled(String objName) {
        return isEnabled(objects.get(objName));
    }

    /**
     * Checks if the web element identified by the object name is displayed.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(String objName) {
        return isDisplayed(objects.get(objName));
    }
}
