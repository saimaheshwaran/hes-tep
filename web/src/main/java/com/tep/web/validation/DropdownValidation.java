package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * DropdownValidation class to handle validation of dropdown elements.
 */
public class DropdownValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(DropdownValidation.class);

    /**
     * Constructor to initialize the DropdownValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public DropdownValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("DropdownValidation with webdriver initialized successfully");
    }

    /**
     * Constructor to initialize the DropdownValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public DropdownValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("DropdownValidation with Webdriver and Pageobject initialized successfully");
    }

    /**
     * Validates if the specified option is selected in the dropdown identified by the object name.
     *
     * @param objName           the name of the object whose locator is to be retrieved.
     * @param type              the type of selection (text or value).
     * @param option            the option to validate.
     * @param shouldBeSelected  true if the option should be selected, false otherwise.
     */
    public void isSelected(String objName, String type, String option, boolean shouldBeSelected) {
        logger.info("Verifying if the option '" + option + "' of the " + type + " '" + objName + "' is selected as expected");
        isSelected(objects.get(objName), type, option, shouldBeSelected);
    }

    /**
     * Validates if the specified option is selected in the dropdown identified by the locator pair.
     *
     * @param locatorPair       a Map.Entry containing the locator type and value.
     * @param type              the type of selection (text or value).
     * @param option            the option to validate.
     * @param shouldBeSelected  true if the option should be selected, false otherwise.
     */
    public void isSelected(Map.Entry<String, String> locatorPair, String type, String option, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement dropDown = element.get(locatorPair);
        Select selectList = new Select(dropDown);
        String actualValue = "";

        if (type.equals("text")) {
            actualValue = selectList.getFirstSelectedOption().getText();
        } else {
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        }

        if (!actualValue.equals(option) && shouldBeSelected) {
            logger.error("Expected option '{}' should be selected, but found '{}'", option, actualValue);
            Assertion.equalsTrue(false, "Expected: " + option + " should be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        } else if (actualValue.equals(option) && !shouldBeSelected) {
            logger.error("Expected option '{}' should not be selected ", option);
            Assertion.equalsFalse(true, "Expected: " + option + " should not be selected from Dropdown. But " + actualValue + " is selected from Dropdown.");
        }
    }
}
