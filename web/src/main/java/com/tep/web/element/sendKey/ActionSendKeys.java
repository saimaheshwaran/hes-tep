package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

import static com.tep.utilities.Strings.identifyKey;

/**
 * The ActionSendKeys class provides methods for sending keyboard inputs (keys) to a web element.
 * It allows sending plain text, pressing specific keys, and handling key combinations using Selenium's WebDriver.
 */
public class ActionSendKeys {

    /** The instance of SeleniumWaits used for waiting until elements are displayed. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the ActionSendKeys class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance to interact with the browser.
     */
    public ActionSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Sends a sequence of keys (text) to a web element identified by its object name.
     * It waits for the element to be displayed, clicks it, and types the given text.
     *
     * @param objName The name of the web element.
     * @param text The text to send to the element.
     */
    public void sendKeys(String objName, String text) {
        sendKeys(seleniumDriver.getElement(objName), text);
    }

    /**
     * Sends a sequence of keys (text) to the provided web element.
     * It waits for the element to be displayed, clicks it, and types the given text.
     *
     * @param webElement The WebElement representing the target element.
     * @param text The text to send to the element.
     */
    public void sendKeys(WebElement webElement, String text) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            Actions actions = new Actions(seleniumDriver.getBrowser());  // Creates an Actions instance for handling keyboard actions.
            actions.moveToElement(webElement).click().sendKeys(text).build().perform();  // Sends the text to the element.
            actions.release().perform();  // Releases the action after sending the text.
        } catch (StaleElementReferenceException e) {
            sendKeys(webElement, text);  // Retries the operation if the element reference becomes stale.
        }
    }

    /**
     * Sends an Enter key press to a web element identified by its object name.
     *
     * @param objName The name of the web element.
     * @param key The key to be pressed (e.g., ENTER, RETURN).
     */
    public void enterKey(String objName, Keys key) {
        enterKeys(seleniumDriver.getElement(objName), key);
    }

    /**
     * Sends a single key press to the provided web element.
     *
     * @param webElement The WebElement representing the target element.
     * @param key The key to be pressed (e.g., ENTER, RETURN).
     */
    public void enterKeys(WebElement webElement, Keys key) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            webElement.sendKeys(key);  // Sends the key press to the element.
        } catch (StaleElementReferenceException e) {
            enterKeys(webElement, key);  // Retries the operation if the element reference becomes stale.
        }
    }

    /**
     * Sends a key press represented by a string to a web element identified by its object name.
     * The string is converted to a corresponding Selenium Keys value.
     *
     * @param objName The name of the web element.
     * @param key The string representing the key to be pressed (e.g., "ENTER", "BACK_SPACE").
     */
    public void enterKeys(String objName, String key) {
        enterKeys(seleniumDriver.getElement(objName), key);
    }

    /**
     * Sends a key press represented by a string to the provided web element.
     * The string is converted to a corresponding Selenium Keys value.
     *
     * @param webElement The WebElement representing the target element.
     * @param keys The string representing the key to be pressed (e.g., "ENTER", "BACK_SPACE").
     */
    public void enterKeys(WebElement webElement, String keys) {
        enterKeys(webElement, identifyKey(keys));  // Converts the string to a Selenium Keys value and sends it.
    }

    /**
     * Sends a combination of keys (e.g., SHIFT+ENTER) to a web element identified by its object name.
     *
     * @param objName The name of the web element.
     * @param keystrings An array of strings representing the key combinations to be pressed.
     */
    public void enterKeyCombinations(String objName, String[] keystrings) {
        enterKeyCombinations(seleniumDriver.getElement(objName), keystrings);
    }

    /**
     * Sends a combination of keys (e.g., SHIFT+ENTER) to the provided web element.
     *
     * @param webElement The WebElement representing the target element.
     * @param keystrings An array of strings representing the key combinations to be pressed.
     */
    public void enterKeyCombinations(WebElement webElement, String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = identifyKey(keystrings[i]);  // Converts string to corresponding Selenium Keys value.
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];  // Use the string if no valid key is found.
                }
            }
            seleniumWaits.untilElementDisplayed(webElement);  // Waits for the element to be visible.
            Actions actions = new Actions(seleniumDriver.getBrowser());  // Creates an Actions instance for handling keyboard actions.
            actions.moveToElement(webElement).click().sendKeys(Keys.chord(keys)).perform();  // Sends the key combinations to the element.
            actions.release().perform();  // Releases the action after sending the keys.
        } catch (StaleElementReferenceException e) {
            enterKeyCombinations(webElement, keystrings);  // Retries the operation if the element reference becomes stale.
        }
    }

    /**
     * Sends a combination of keys globally (not bound to any specific web element).
     *
     * @param keystrings An array of strings representing the key combinations to be pressed.
     */
    public void hitKeyCombinations(String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = identifyKey(keystrings[i]);  // Converts string to corresponding Selenium Keys value.
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];  // Use the string if no valid key is found.
                }
            }
            Actions actions = new Actions(seleniumDriver.getBrowser());  // Creates an Actions instance for handling keyboard actions.
            actions.sendKeys(Keys.chord(keys)).perform();  // Sends the key combinations globally.
            actions.release().perform();  // Releases the action after sending the keys.
        } catch (StaleElementReferenceException e) {
            hitKeyCombinations(keystrings);  // Retries the operation if the element reference becomes stale.
        }
    }
}
