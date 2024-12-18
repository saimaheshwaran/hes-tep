package com.tep.web.element.dropdown;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * JavaScriptDropdown class to handle dropdown interactions using JavaScript.
 */
public class JavaScriptDropdown {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptDropdown.class);

    /**
     * Constructor to initialize the JavaScriptDropdown with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public JavaScriptDropdown(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptDropdown initialized with WebDriver, Waits and Element helpers.");
    }

    /**
     * Constructor to initialize the JavaScriptDropdown with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public JavaScriptDropdown(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("JavaScriptDropdown initialized with WebDriver, PageObjects, Waits and Element helpers.");
    }

    /**
     * Selects an option from the dropdown identified by the object name using JavaScript.
     *
     * @param option   the option to select.
     * @param optionBy the method to select the option (index, value, or text).
     * @param objName  the name of the object whose locator is to be retrieved.
     */
    public void select(String option, String optionBy, String objName) {
        select(option, optionBy, objects.get(objName));
    }

    /**
     * Deselects an option from the dropdown identified by the object name using JavaScript.
     *
     * @param option   the option to deselect.
     * @param optionBy the method to deselect the option (index, value, or text).
     * @param objName  the name of the object whose locator is to be retrieved.
     */
    public void deselect(String option, String optionBy, String objName) {
        deselect(option, optionBy, objects.get(objName));
    }

    /**
     * Selects all options from the dropdown identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void selectAll(String objName) {
        selectAll(objects.get(objName));
    }

    /**
     * Deselects all options from the dropdown identified by the object name using JavaScript.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void deselectAll(String objName) {
        deselectAll(objects.get(objName));
    }

    /**
     * Selects an option from the dropdown identified by the locator pair using JavaScript.
     *
     * @param option      the option to select.
     * @param optionBy    the method to select the option (index, value, or text).
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void select(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = true;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = true; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }";
            }
            executor.executeScript(script, element, option);
            logger.info("Option selected successfully using JavaScript.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during select, retrying.");
            select(option, optionBy, locatorPair);
        }
    }

    /**
     * Deselects an option from the dropdown identified by the locator pair using JavaScript.
     *
     * @param option      the option to deselect.
     * @param optionBy    the method to deselect the option (index, value, or text).
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void deselect(String option, String optionBy, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = false;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = false; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = false; } }";
            }
            executor.executeScript(script, element, option);
            logger.info("Option deselected successfully using JavaScript.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselect, retrying.");
            deselect(option, optionBy, locatorPair);
        }
    }

    /**
     * Deselects all options from the dropdown identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void deselectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = false;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, element);
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(locatorPair);
        }
    }

    /**
     * Selects all options from the dropdown identified by the locator pair using JavaScript.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void selectAll(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement element = this.element.get(locatorPair);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(!select.options[i].selected){select.options[i].selected = true;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, element);
            logger.info("All options have been selected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selectAll, retrying.");
            selectAll(locatorPair);
        }
    }

    public void select(String option, String optionBy, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = true;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = true; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }";
            }
            assert script != null;
            executor.executeScript(script, webElement, option);
            logger.info("Option selected successfully using JavaScript.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during select, retrying.");
            select(option, optionBy, webElement);
        }
    }

    public void deselect(String option, String optionBy, WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String script = null;
            switch (optionBy) {
                case "index" -> {
                    int index = Integer.parseInt(option) - 1;
                    script = "var select = arguments[0]; { select.options[" + index + "].selected = false;  }";
                }
                case "value" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].value == arguments[1]){ select.options[i].selected = false; } }";
                case "text" ->
                        script = "var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = false; } }";
            }
            assert script != null;
            executor.executeScript(script, webElement, option);
            logger.info("Option deselected successfully using JavaScript.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselect, retrying.");
            deselect(option, optionBy, webElement);
        }
    }

    public void deselectAll(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(select.options[i].selected){select.options[i].selected = false;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, webElement);
            logger.info("All selected options have been deselected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during deselectAll, retrying.");
            deselectAll(webElement);
        }
    }

    public void selectAll(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            String script = "var select = arguments[0];" +
                    "for(var i = 0; i < select.options.length; i++){ " +
                    "if(!select.options[i].selected){select.options[i].selected = true;}}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script, webElement);
            logger.info("All options have been selected.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught during selectAll, retrying.");
            selectAll(webElement);
        }
    }

}
