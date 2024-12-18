package com.tep.web.element.dropdown;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * ActionDropdown class to handle dropdown interactions using Selenium.
 */
public class ActionDropdown {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(ActionDropdown.class);


    /**
     * Constructor to initialize the ActionDropdown with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public ActionDropdown(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionDropdown initialized with WebDriver, Waits and Element helpers.");
    }

    /**
     * Constructor to initialize the ActionDropdown with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public ActionDropdown(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("ActionDropdown initialized with WebDriver, PageObjects, Waits and Element helpers.");
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
     * Selects all options from the dropdown identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void selectAll(String objName) {
        selectAll(objects.get(objName));
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
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
            logger.info("Option selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selection, retrying.");
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
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
            logger.info("Option deselected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselection, retrying.");
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
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(driver);
            for (WebElement webElement : elementCount) {
                if (webElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(webElement);
                }
            }
            actions.build().perform();
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(locatorPair);
        }
    }

    /**
     * Selects all options from the dropdown identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void selectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            Select selectList = new Select(element);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(driver);
            for (WebElement webElement : elementCount) {
                if (!webElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(webElement);
                }
            }
            actions.build().perform();
            logger.info("All options have been selected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selectAll, retrying.");
            selectAll(locatorPair);
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
            Actions action = new Actions(driver);
            for (String option : options) {
                for (WebElement webElement : elementCount) {
                    if (webElement.getText().equals(option)) {
                        action.keyDown(Keys.CONTROL).click(webElement);
                    }
                }
            }
            action.build().perform();
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
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
            switch (optionBy) {
                case "index" -> selectList.selectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.selectByValue(option);
                case "text" -> selectList.selectByVisibleText(option);
            }
            logger.info("Option selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selection, retrying.");
            select(option, optionBy, webElement);
        }
    }

    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
            switch (optionBy) {
                case "index" -> selectList.deselectByIndex(Integer.parseInt(option) - 1);
                case "value" -> selectList.deselectByValue(option);
                case "text" -> selectList.deselectByVisibleText(option);
            }
            logger.info("Option deselected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselection, retrying.");
            deselect(option, optionBy, webElement);
        }
    }

    public void deselectAll(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(driver);
            for (WebElement wbElement : elementCount) {
                if (wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);
                }
            }
            actions.build().perform();
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(webElement);
        }
    }

    public void selectAll(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions actions = new Actions(driver);
            for (WebElement wbElement : elementCount) {
                if (!wbElement.isSelected()) {
                    actions.keyDown(Keys.CONTROL).click(wbElement);
                }
            }
            actions.build().perform();
            logger.info("All options have been selected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selectAll, retrying.");
            selectAll(webElement);
        }
    }

    public void multiSelect(String[] options, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            Select selectList = new Select(webElement);
            List<WebElement> elementCount = selectList.getOptions();
            Actions action = new Actions(driver);
            for (String option : options) {
                for (WebElement wbElement : elementCount) {
                    if (wbElement.getText().equals(option)) {
                        action.keyDown(Keys.CONTROL).click(wbElement);
                    }
                }
            }
            action.build().perform();
            logger.info("Multiple options selected successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught during multiSelect, retrying.", e);
            multiSelect(options, webElement);
        }
    }

}
