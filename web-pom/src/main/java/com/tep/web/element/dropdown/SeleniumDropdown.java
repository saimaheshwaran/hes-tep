package com.tep.web.element.dropdown;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

public class SeleniumDropdown {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;
    private final SeleniumClick seleniumClick;

    public SeleniumDropdown(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.seleniumClick = new SeleniumClick(seleniumDriver);
    }

    public void select(String option, String optionBy, String objName) { select(option, optionBy, seleniumDriver.getElement(objName)); }

    public void select(String option, String optionBy, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
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
            selectList.deselectAll();
        } catch (StaleElementReferenceException ignored) {
            deselectAll(webElement);
        }
    }

    public void multiSelect(String[] options, String objName) { multiSelect(options, seleniumDriver.getElement(objName)); }

    public void multiSelect(String[] options, WebElement webElement) {
        try {
            seleniumWaits.untilElementDisplayed(webElement);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            for (String item : options) {
                for (WebElement wbElement : elementCount) {
                    if (webElement.getText().equals(item)) {
                        seleniumClick.click(wbElement);
                    }
                }
            }
        } catch (StaleElementReferenceException e) {
            multiSelect(options, webElement);
        }
    }

}
