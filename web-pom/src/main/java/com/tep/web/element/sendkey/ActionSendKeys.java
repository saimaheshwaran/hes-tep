package com.tep.web.element.sendkey;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

public class ActionSendKeys {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public ActionSendKeys(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void sendKeys(String objName, String text) { sendKeys(seleniumDriver.getElement(objName), text); }

    public void sendKeys(WebElement webElement, String text) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().sendKeys(text).build().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            sendKeys(webElement, text);
        }
    }

    public void enterKey(String objName, Keys key) { enterKeys(seleniumDriver.getElement(objName), key); }

    public void enterKeys(WebElement webElement, Keys key) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            webElement.sendKeys(key);
        } catch (StaleElementReferenceException e) {
            enterKeys(webElement, key);
        }
    }

    public void enterKeys(String objName, String key) { enterKeys(seleniumDriver.getElement(objName), key); }

    public void enterKeys(WebElement webElement, String keys) {
        enterKeys(webElement, identifyKey(keys));
    }

    public void enterKeyCombinations(String objName, String[] keystrings) { enterKeyCombinations(seleniumDriver.getElement(objName), keystrings); }

    public void enterKeyCombinations(WebElement webElement, String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = identifyKey(keystrings[i]);
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];
                }
            }
            seleniumWaits.untilElementDisplayed(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).click().sendKeys(Keys.chord(keys)).perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            enterKeyCombinations(webElement, keystrings);
        }
    }

    public void hitKeyCombinations(String[] keystrings) {
        try {
            CharSequence[] keys = new CharSequence[keystrings.length];
            for (int i = 0; i < keystrings.length; i++) {
                Keys key = identifyKey(keystrings[i]);
                if (!key.equals(Keys.NULL)) {
                    keys[i] = key;
                } else {
                    keys[i] = keystrings[i];
                }
            }
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.sendKeys(Keys.chord(keys)).perform();
            actions.release().perform();
        } catch (StaleElementReferenceException e) {
            hitKeyCombinations(keystrings);
        }
    }

    private Keys identifyKey(String key) {
        return switch (key.toUpperCase()) {
            case "NULL" -> Keys.NULL;
            case "CANCEL" -> Keys.CANCEL;
            case "HELP" -> Keys.HELP;
            case "BACK_SPACE" -> Keys.BACK_SPACE;
            case "TAB" -> Keys.TAB;
            case "CLEAR" -> Keys.CLEAR;
            case "RETURN" -> Keys.RETURN;
            case "ENTER" -> Keys.ENTER;
            case "SHIFT" -> Keys.SHIFT;
            case "LEFT_SHIFT" -> Keys.LEFT_SHIFT;
            case "CONTROL" -> Keys.CONTROL;
            case "LEFT_CONTROL" -> Keys.LEFT_CONTROL;
            case "ALT" -> Keys.ALT;
            case "LEFT_ALT" -> Keys.LEFT_ALT;
            case "PAUSE" -> Keys.PAUSE;
            case "ESCAPE" -> Keys.ESCAPE;
            case "SPACE" -> Keys.SPACE;
            case "PAGE_UP" -> Keys.PAGE_UP;
            case "PAGE_DOWN" -> Keys.PAGE_DOWN;
            case "END" -> Keys.END;
            case "HOME" -> Keys.HOME;
            case "LEFT" -> Keys.LEFT;
            case "ARROW_LEFT" -> Keys.ARROW_LEFT;
            case "UP" -> Keys.UP;
            case "ARROW_UP" -> Keys.ARROW_UP;
            case "RIGHT" -> Keys.RIGHT;
            case "ARROW_RIGHT" -> Keys.ARROW_RIGHT;
            case "DOWN" -> Keys.DOWN;
            case "ARROW_DOWN" -> Keys.ARROW_DOWN;
            case "INSERT" -> Keys.INSERT;
            case "DELETE" -> Keys.DELETE;
            case "SEMICOLON" -> Keys.SEMICOLON;
            case "EQUALS" -> Keys.EQUALS;
            case "NUMPAD0" -> Keys.NUMPAD0;
            case "NUMPAD1" -> Keys.NUMPAD1;
            case "NUMPAD2" -> Keys.NUMPAD2;
            case "NUMPAD3" -> Keys.NUMPAD3;
            case "NUMPAD4" -> Keys.NUMPAD4;
            case "NUMPAD5" -> Keys.NUMPAD5;
            case "NUMPAD6" -> Keys.NUMPAD6;
            case "NUMPAD7" -> Keys.NUMPAD7;
            case "NUMPAD8" -> Keys.NUMPAD8;
            case "NUMPAD9" -> Keys.NUMPAD9;
            case "MULTIPLY" -> Keys.MULTIPLY;
            case "ADD" -> Keys.ADD;
            case "SEPARATOR" -> Keys.SEPARATOR;
            case "SUBTRACT" -> Keys.SUBTRACT;
            case "DECIMAL" -> Keys.DECIMAL;
            case "DIVIDE" -> Keys.DIVIDE;
            case "F1" -> Keys.F1;
            case "F2" -> Keys.F2;
            case "F3" -> Keys.F3;
            case "F4" -> Keys.F4;
            case "F5" -> Keys.F5;
            case "F6" -> Keys.F6;
            case "F7" -> Keys.F7;
            case "F8" -> Keys.F8;
            case "F9" -> Keys.F9;
            case "F10" -> Keys.F10;
            case "F11" -> Keys.F11;
            case "F12" -> Keys.F12;
            default -> Keys.NULL;
        };
    }
}
