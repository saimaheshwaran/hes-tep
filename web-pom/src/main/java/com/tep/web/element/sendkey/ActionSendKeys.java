package com.tep.web.element.sendKey;

import com.tep.web.base.Driver;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;
import com.tep.utilities.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ActionSendKeys class to handle sending keys and key combinations to web elements.
 */
public class ActionSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(ActionSendKeys.class);

    /**
     * Constructor to initialize the ActionSendKeys with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public ActionSendKeys(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionSendKeys initialized with WebDriver, Waits, and Element.");
    }

    /**
     * Constructor to initialize the ActionSendKeys with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public ActionSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionSendKeys initialized with WebDriver, PageObjects, Waits, and Element.");
    }

    /**
     * Sends the specified text to the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param text    the text to send.
     */
    public void sendKeys(String objName, String text) {
        sendKeys(objects.get(objName), text);
    }

    /**
     * Enters the specified key to the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param keys    the key to enter.
     */
    public void enterKeys(String objName, Keys keys) {
        enterKeys(objects.get(objName), keys);
    }

    /**
     * Enters the specified key to the element identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     * @param keys    the key to enter as a string.
     */
    public void enterKeys(String objName, String keys) {
        enterKeys(objects.get(objName), Strings.identifyKey(keys));
    }

    /**
     * Enters the specified key combinations to the element identified by the object name.
     *
     * @param objName    the name of the object whose locator is to be retrieved.
     * @param keystrings the key combinations to enter.
     */
    public void enterKeyCombinations(String objName, String[] keystrings) {
        enterKeyCombinations(objects.get(objName), keystrings);
    }

    /**
     * Sends the specified text to the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param text        the text to send.
     */
    public void sendKeys(Map.Entry<String, String> locatorPair, String text) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).click().sendKeys(text).build().perform();
            actions.release().perform();
            logger.info("Text sent to element with locator.", text, locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while sending text.", e);
            sendKeys(locatorPair, text);
        }
    }

    /**
     * Enters the specified key to the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param keys        the key to enter.
     */
    public void enterKeys(Map.Entry<String, String> locatorPair, Keys keys) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).sendKeys(keys);
            logger.info("keys entered on element with locator.", keys, locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while entering keys. Retrying...", e);
            enterKeys(locatorPair, keys);
        }
    }

    public void enterKeys(Map.Entry<String, String> locatorPair, String keys) {
        enterKeys(locatorPair, Strings.identifyKey(keys));
    }

    /**
     * Enters the specified key combinations to the element identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param keystrings  the key combinations to enter.
     */
    public void enterKeyCombinations(Map.Entry<String, String> locatorPair, String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = Strings.identifyKey(keystrings[i]);
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];
                }
            }
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).click().sendKeys(Keys.chord(keys)).perform();
            actions.release().perform();
            logger.info("Key combinations entered successfully on element with locator.", locatorPair);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while entering key combinations.", e);
            enterKeyCombinations(locatorPair, keystrings);
        }
    }

    public void sendKeys(WebElement webElement, String text) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click().sendKeys(text).build().perform();
            actions.release().perform();
            logger.info("Text {} sent to element {}.", text, webElement);
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException occurred while sending text.", e);
            sendKeys(webElement, text);
        }
    }

    public void enterKeys(WebElement webElement, Keys key) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            webElement.sendKeys(key);
            logger.info("keys entered on element with locator: " + webElement + " Key: " + key);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while entering keys. Retrying...", e);
            enterKeys(webElement, key);
        }
    }

    public void enterKeys(WebElement webElement, String keys) {
        enterKeys(webElement, Strings.identifyKey(keys));
    }

    public void enterKeyCombinations(WebElement webElement, String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = Strings.identifyKey(keystrings[i]);
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];
                }
            }
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click().sendKeys(Keys.chord(keys)).perform();
            actions.release().perform();
            logger.info("Key combinations entered successfully on element: {}", webElement);
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while entering key combinations.", e);
            enterKeyCombinations(webElement, keystrings);
        }
    }

    /**
     * Hits the specified key combinations.
     *
     * @param keystrings the key combinations to hit.
     */
    public void hitKeyCombinations(String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = Strings.identifyKey(keystrings[i]);
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];
                }
            }
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.chord(keys)).perform();
            actions.release().perform();
            logger.info("Key combinations hit successfully.");
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while hitting key combinations.", e);
            hitKeyCombinations(keystrings);
        }
    }
}
