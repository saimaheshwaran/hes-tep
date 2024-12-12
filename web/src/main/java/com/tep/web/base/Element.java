package com.tep.web.base;

import com.tep.utilities.PropUtils;
import com.tep.web.config.Constants;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tep.web.config.Enums;

import java.util.List;
import java.util.Map;

/**
 * Element class to handle WebElement interactions.
 */
public class Element {

    private Waits waits;
    private WebDriver driver;
    private WebElement element;
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    /**
     * Constructor to initialize the Element with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public Element(WebDriver driver) {
        logger.debug("Initializing Element with WebDriver instance.");
        this.driver = driver;
    }

    /**
     * Retrieves a WebElement based on the provided locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the located WebElement.
     */
    public WebElement get(Map.Entry<String, String> locatorPair) {
        switch (locatorPair.getKey().toLowerCase()) {
            case "id" -> element = driver.findElement(By.id(locatorPair.getValue()));
            case "name" -> element = driver.findElement(By.name(locatorPair.getValue()));
            case "xpath" -> element = driver.findElement(By.xpath(locatorPair.getValue()));
            case "css" -> element = driver.findElement(By.cssSelector(locatorPair.getValue()));
            case "tagname" -> element = driver.findElement(By.tagName(locatorPair.getValue()));
            case "linktest" -> element = driver.findElement(By.linkText(locatorPair.getValue()));
            case "classname" -> element = driver.findElement(By.className(locatorPair.getValue()));
            case "partiallinktest" -> element = driver.findElement(By.partialLinkText(locatorPair.getValue()));
            default -> element = null;
        }
        scrollTo(element);
        logger.error("Error occurred while generating By object for locator pair: Key = {}, Value = {}", locatorPair.getKey(), locatorPair.getValue());
        return element;
    }

    /**
     * Retrieves a By locator based on the provided locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the By locator.
     */
    public By getBy(Map.Entry<String, String> locatorPair) {
        By by;
        switch (locatorPair.getKey().toLowerCase()) {
            case "id" -> by = By.id(locatorPair.getValue());
            case "name" -> by = By.name(locatorPair.getValue());
            case "xpath" -> by = By.xpath(locatorPair.getValue());
            case "css" -> by = By.cssSelector(locatorPair.getValue());
            case "tagname" -> by = By.tagName(locatorPair.getValue());
            case "linktest" -> by = By.linkText(locatorPair.getValue());
            case "classname" -> by = By.className(locatorPair.getValue());
            case "partiallinktest" -> by = By.partialLinkText(locatorPair.getValue());
            default -> by = null;
        }
        return by;
    }

    /**
     * Scrolls to the specified WebElement.
     *
     * @param element the WebElement to scroll to.
     */
    public void scrollTo(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style','background:GreenYellow; border: 0px solid blue;')", element);
            Thread.sleep(100);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style','background:; border: 0px solid blue;')", element);
        } catch (Exception ignored) {
            logger.error("Exception occurred while scrolling to WebElement: {}", element, ignored);
        }
    }

    /**
     * Retrieves a list of WebElements based on the provided locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return the list of located WebElements.
     */
    public List<WebElement> getList(Map.Entry<String, String> locatorPair) {
        return waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
    }

    /**
     * Checks if the WebElement located by the provided locator pair is selected.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is selected, false otherwise.
     */
    public boolean isSelected(Map.Entry<String, String> locatorPair) {
        logger.debug("Checking if WebElement is selected using locator: {}", locatorPair);
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isSelected();
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while checking if WebElement is selected: {}", locatorPair, e);
            isSelected(locatorPair);
        }
        return elementSelected;
    }

    /**
     * Checks if the WebElement located by the provided locator pair is enabled.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is enabled, false otherwise.
     */
    public boolean isEnabled(Map.Entry<String, String> locatorPair) {
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isEnabled();
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while checking element with locator: {}", locatorPair.getKey());
            isEnabled(locatorPair);
        }
        return elementSelected;
    }

    /**
     * Checks if the WebElement located by the provided locator pair is displayed.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(Map.Entry<String, String> locatorPair) {
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isDisplayed();
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while checking element with locator: {}", locatorPair.getKey());
            isDisplayed(locatorPair);
        }
        return elementSelected;
    }
}
