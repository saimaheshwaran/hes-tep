package com.tep.web.element.radiobutton;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.SeleniumCheckBox;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * SeleniumRadioButton class to handle radio button interactions using Selenium.
 */
public class SeleniumRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private SeleniumClick seleniumClick;
    private static final Logger logger = LoggerFactory.getLogger(SeleniumRadioButton.class);

    /**
     * Constructor to initialize the SeleniumRadioButton with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public SeleniumRadioButton(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.seleniumClick = new SeleniumClick(driver);
        logger.info("SeleniumRadioButton initialized with WebDriver, Waits, Element, and SeleniumClick helpers.");
    }

    /**
     * Constructor to initialize the SeleniumRadioButton with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public SeleniumRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.seleniumClick = new SeleniumClick(driver);
        logger.info("SeleniumRadioButton initialized with WebDriver, PageObjects, Waits, Element, and SeleniumClick helpers.");
    }

    /**
     * Selects the radio button identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void select(String objName) {
        select(objects.get(objName));
    }

    /**
     * Selects a radio button from a group identified by the object name.
     *
     * @param option        the option to select.
     * @param selectionType the method to select the option (value or text).
     * @param objName       the name of the object whose locator is to be retrieved.
     */
    public void selectFromRadioButtonGroup(String option, String selectionType, String objName) {
        selectFromRadioButtonGroup(option, selectionType, objects.get(objName));
    }

    /**
     * Selects the radio button identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement radioButton = this.element.get(locatorPair);
            if (!radioButton.isSelected()) {
                seleniumClick.click(locatorPair);
            }
            logger.info("RadioButton is selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught, retrying check operation.",ignored);
            select(locatorPair);
        }
    }

    /**
     * Selects a radio button from a group identified by the locator pair.
     *
     * @param option        the option to select.
     * @param selectionType the method to select the option (value or text).
     * @param locatorPair   a Map.Entry containing the locator type and value.
     */
    public void selectFromRadioButtonGroup(String option, String selectionType, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            List<WebElement> radioButtonGroup = driver.findElements(this.element.getBy(locatorPair));
            for (WebElement radioButton : radioButtonGroup) {
                if (selectionType.equals("value")) {
                    if (radioButton.getAttribute("value").equals(option) && !radioButton.isSelected()) {
                        radioButton.click();
                        logger.info("Radio button with value selected.", option);
                    }
                } else if (selectionType.equals("text")) {
                    if (radioButton.getText().equals(option) && !radioButton.isSelected()) {
                        radioButton.click();
                        logger.info("Radio button with text selected.", option);
                    }
                }
            }
          } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException occurred while selecting from radio button group. Retrying...", option, selectionType, locatorPair, e);
            selectFromRadioButtonGroup(option, selectionType, locatorPair);
        }
    }

    public void select(WebElement webElement) {
        try {
            waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
            if (!webElement.isSelected()) {
                seleniumClick.click(webElement);
            }
            logger.info("RadioButton is selected successfully.");
        } catch (StaleElementReferenceException ignored) {
            logger.error("StaleElementReferenceException caught, retrying check operation.",ignored);
            select(webElement);
        }
    }

}
