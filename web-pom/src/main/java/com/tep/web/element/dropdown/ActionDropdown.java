package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

public class ActionDropdown {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public ActionDropdown(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void select(String option, String optionBy, String objName) { select(option, optionBy, seleniumDriver.getElement(objName)); }

    public void select(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
        } catch (StaleElementReferenceException ignored) {
            select(option, optionBy, webElement);
        }
    }

    public void deselect(String option, String optionBy, String objName) { deselect(option, optionBy, seleniumDriver.getElement(objName)); }

    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            Actions actions = new Actions(seleniumDriver.getBrowser());
            actions.moveToElement(webElement).perform();
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
        } catch (StaleElementReferenceException ignored) {
            deselect(option, optionBy, webElement);
        }
    }

    public void deselectAll(String objName) { deselectAll(seleniumDriver.getElement(objName)); }

    public void deselectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(seleniumDriver.getBrowser());
            for (WebElement wbElement : elementCount) {
                if (wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);
                }
            }
            actions.build().perform();
        } catch (StaleElementReferenceException ignored) {
            deselectAll(webElement);
        }
    }

    public void selectAll(String objName) { selectAll(seleniumDriver.getElement(objName)); }

    public void selectAll(WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(seleniumDriver.getBrowser());
            for (WebElement wbElement : elementCount) {
                if (!wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);
                }
            }
            actions.build().perform();
        } catch (StaleElementReferenceException ignored) {
            selectAll(webElement);
        }
    }

    public void multiSelect(String[] options, String objName) { multiSelect(options, seleniumDriver.getElement(objName)); }

    public void multiSelect(String[] options, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions action = new Actions(seleniumDriver.getBrowser());
            for (String option : options) {
                for (WebElement wbElement : elementCount) {
                    if (wbElement.getText().equals(option)) {
                        action.keyDown(Keys.CONTROL).click(wbElement);
                    }
                }
            }
            action.build().perform();
        } catch (StaleElementReferenceException e) {
            multiSelect(options, webElement);
        }
    }

}
