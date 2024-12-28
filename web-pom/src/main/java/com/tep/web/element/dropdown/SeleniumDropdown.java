package com.tep.web.element.dropdown;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * SeleniumDropdown class to handle dropdown interactions using Selenium.
 */
public class SeleniumDropdown {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private SeleniumClick seleniumClick;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumDropdown.class);

    /**
     * Constructor to initialize the SeleniumDropdown with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public SeleniumDropdown(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.seleniumClick = new SeleniumClick(driver);
        logger.info("SeleniumDropdown initialized with WebDriver, Waits, Element, and SeleniumClick helpers.");
    }

    /**
     * Constructor to initialize the SeleniumDropdown with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public SeleniumDropdown(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.seleniumClick = new SeleniumClick(driver);
        logger.info("SeleniumDropdown initialized with WebDriver, PageObjects, Waits, Element, and SeleniumClick helpers.");
    }

    /**
     * Selects an option from the dropdown identified by the object name.
     *
     * @param option   the option to select.
     * @param optionBy the method to select the option (index, value, or text).
     * @param objName  the name of the object whose locator is to be retrieved.
     */
    public void select(String option, String optionBy, String objName) {
        select(option, optionBy, objects.get(objName));
    }

    /**
     * Deselects an option from the dropdown identified by the object name.
     *
     * @param option   the option to deselect.
     * @param optionBy the method to deselect the option (index, value, or text).
     * @param objName  the name of the object whose locator is to be retrieved.
     */
    public void deselect(String option, String optionBy, String objName) {
        deselect(option, optionBy, objects.get(objName));
    }

    /**
     * Deselects all options from the dropdown identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void deselectAll(String objName) {
        deselectAll(objects.get(objName));
    }

    /**
     * Selects multiple options from the dropdown identified by the object name.
     *
     * @param options the options to select.
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void multiSelect(String[] options, String objName) {
        multiSelect(options, objects.get(objName));
    }

    /**
     * Selects an option from the dropdown identified by the locator pair.
     *
     * @param option      the option to select.
     * @param optionBy    the method to select the option (index, value, or text).
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void select(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
            logger.info("Option selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during select, retrying.");
            select(option, optionBy, locatorPair);
        }
    }

    /**
     * Deselects an option from the dropdown identified by the locator pair.
     *
     * @param option      the option to deselect.
     * @param optionBy    the method to deselect the option (index, value, or text).
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void deselect(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
            logger.info("Option deselected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselect, retrying.");
            deselect(option, optionBy, locatorPair);
        }
    }

    /**
     * Deselects all options from the dropdown identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void deselectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            selectList.deselectAll();
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(locatorPair);
        }
    }

    /**
     * Selects multiple options from the dropdown identified by the locator pair.
     *
     * @param options     the options to select.
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void multiSelect(String[] options, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            List<WebElement> elementCount = selectList.getOptions();
            for (String item : options) {
                for (WebElement webElement : elementCount) {
                    if (webElement.getText().equals(item)) {
                        seleniumClick.click(locatorPair);
                    }
                }
            }
            logger.info("Multiple options selected successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during multiSelect, retrying.", e);
            multiSelect(options, locatorPair);
        }
    }

    public void select(String option, String optionBy, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
            logger.info("Option selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during select, retrying.");
            select(option, optionBy, webElement);
        }
    }

    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
            logger.info("Option deselected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselect, retrying.");
            deselect(option, optionBy, webElement);
        }
    }

    public void deselectAll(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            selectList.deselectAll();
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(webElement);
        }
    }

    public void multiSelect(String[] options, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            for (String item : options) {
                for (WebElement wbElement : elementCount) {
                    if (webElement.getText().equals(item)) {
                        seleniumClick.click(wbElement);
                    }
                }
            }
            logger.info("Multiple options selected successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during multiSelect, retrying.", e);
            multiSelect(options, webElement);
        }
    }


}
