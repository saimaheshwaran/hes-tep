package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * RadioButtonValidation class to handle validation of radio button elements.
 */
public class RadioButtonValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(RadioButtonValidation.class);

    /**
     * Constructor to initialize the RadioButtonValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public RadioButtonValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("RadioButtonValidation with Webdriver initialized successfully");
    }

    /**
     * Constructor to initialize the RadioButtonValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public RadioButtonValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("RadioButtonValidation with Webdriver and PageObjects");
    }

    /**
     * Validates if the radio button identified by the object name is selected or not.
     *
     * @param objName          the name of the object whose locator is to be retrieved.
     * @param shouldBeSelected true if the radio button should be selected, false otherwise.
     */
    public void isSelected(String objName, boolean shouldBeSelected) {
        isSelected(objects.get(objName), shouldBeSelected);
    }

    /**
     * Validates if the specified option is selected in the radio button group identified by the object name.
     *
     * @param objName          the name of the object whose locator is to be retrieved.
     * @param option           the option to validate.
     * @param selectionType    the type of selection (value or text).
     * @param shouldBeSelected true if the option should be selected, false otherwise.
     */
    public void isSelectedFromRadioButtonGroup(String objName, String option, String selectionType, boolean shouldBeSelected) {
        isSelectedFromRadioButtonGroup(objects.get(objName), option, selectionType, shouldBeSelected);
    }

    /**
     * Validates if the radio button identified by the locator pair is selected or not.
     *
     * @param locatorPair      a Map.Entry containing the locator type and value.
     * @param shouldBeSelected true if the radio button should be selected, false otherwise.
     */
    public void isSelected(Map.Entry<String, String> locatorPair, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement radioButton = element.get(locatorPair);
        if (!radioButton.isSelected() && shouldBeSelected) {
            logger.error("Expected radio button ({}) to be selected, but it is not", element.getBy(locatorPair));
            Assertion.equalsTrue(false, "Expected: Radio button (" + element.getBy(locatorPair) + ") should be selected. But Radio button is not selected.");
        } else if (radioButton.isSelected() && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: Radio button (" + element.getBy(locatorPair) + ") should not be selected. But Radio button is selected.");
        }
    }

    /**
     * Validates if the specified option is selected in the radio button group identified by the locator pair.
     *
     * @param locatorPair      a Map.Entry containing the locator type and value.
     * @param option           the option to validate.
     * @param selectionType    the type of selection (value or text).
     * @param shouldBeSelected true if the option should be selected, false otherwise.
     */
    public void isSelectedFromRadioButtonGroup(Map.Entry<String, String> locatorPair, String option, String selectionType, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        List<WebElement> radioButtonGroup = driver.findElements(this.element.getBy(locatorPair));
        for (WebElement radioButton : radioButtonGroup) {
            if (selectionType.equals("value")) {
                if (radioButton.getAttribute("value").equals(option)) {
                    if (!radioButton.isSelected() && shouldBeSelected) {
                        Assertion.equalsTrue(false, "Expected: Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should be selected with option " + option + ". But radio button is not selected.");
                    } else if (radioButton.isSelected() && !shouldBeSelected) {
                        Assertion.equalsTrue(false, "Expected: Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should not be selected with option " + option + ". But radio button is selected.");
                    }
                    break;
                }
            } else if (radioButton.getText().equals(option)) {
                if (!radioButton.isSelected() && shouldBeSelected) {
                    Assertion.equalsTrue(false, "Expected: Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should be selected with text " + option + ". But radio button is not selected.");
                } else if (radioButton.isSelected() && !shouldBeSelected) {
                    Assertion.equalsTrue(false, "Expected: Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should not be selected with text " + option + ". But radio button is selected.");
                }
                break;
            }
            logger.error("Option '{}' with selection type '{}' not found in radio button group with locator {}={}", option, selectionType, locatorPair.getKey(), locatorPair.getValue());
        }
    }

    public void isSelected(WebElement webElement, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
        if (!webElement.isSelected() && shouldBeSelected) {
            logger.error("Expected radio button ({}) to be selected, but it is not", webElement);
            Assertion.equalsTrue(false, "Expected: Radio button (" + webElement + ") should be selected. But Radio button is not selected.");
        } else if (webElement.isSelected() && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected: Radio button (" + webElement + ") should not be selected. But Radio button is selected.");
        }
    }

}
