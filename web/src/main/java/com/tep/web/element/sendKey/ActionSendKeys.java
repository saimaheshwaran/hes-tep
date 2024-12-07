package com.tep.web.element.sendKey;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;
import com.tep.utilities.Strings;

import java.util.Map;

public class ActionSendKeys {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public ActionSendKeys(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public ActionSendKeys(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void sendKeys(String objName, String text) { sendKeys(objects.get(objName), text); }

    public void sendKeys(Map.Entry<String, String> locatorPair, String text) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            actions.moveToElement(element.get(locatorPair)).click().sendKeys(text).build().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            sendKeys(locatorPair, text);
        }
    }

    public void enterKeyCombinations(String objName, String[] keystrings) { enterKeyCombinations(objects.get(objName), keystrings); }

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
        } catch (StaleElementReferenceException e) {
            enterKeyCombinations(locatorPair, keystrings);
        }
    }

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
        } catch (StaleElementReferenceException e) {
            hitKeyCombinations(keystrings);
        }
    }

    public void enterKeys(String objName, Keys keys) { enterKeys(objects.get(objName), keys); }

    public void enterKeys(String objName, String keys) { enterKeys(objects.get(objName), Strings.identifyKey(keys)); }

    public void enterKeys(Map.Entry<String, String> locatorPair, Keys keys) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            element.get(locatorPair).sendKeys(keys);
        } catch (StaleElementReferenceException e) {
            enterKeys(locatorPair, keys);
        }
    }

}
