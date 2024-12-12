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

import java.util.Map;

/**
 * CheckBoxValidation class to handle validation of checkbox elements.
 */
public class CheckBoxValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    /**
     * Constructor to initialize the CheckBoxValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public CheckBoxValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Constructor to initialize the CheckBoxValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public CheckBoxValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    /**
     * Validates if the checkbox identified by the object name is checked or unchecked.
     *
     * @param objName          the name of the object whose locator is to be retrieved.
     * @param shouldBeChecked  true if the checkbox should be checked, false otherwise.
     */
    public void isChecked(String objName, boolean shouldBeChecked) {
        isChecked(objects.get(objName), shouldBeChecked);
    }

    /**
     * Validates if the checkbox identified by the locator pair is checked or unchecked.
     *
     * @param locatorPair      a Map.Entry containing the locator type and value.
     * @param shouldBeChecked  true if the checkbox should be checked, false otherwise.
     */
    public void isChecked(Map.Entry<String, String> locatorPair, boolean shouldBeChecked) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement checkBox = element.get(locatorPair);
        if (!checkBox.isSelected() && shouldBeChecked) {
            logger.error("Expected: Checkbox (" + element.getBy(locatorPair) + ") should be checked. But checkbox is unchecked.");
            Assertion.equalsTrue(false, "Expected: Checkbox (" + element.getBy(locatorPair) + ") should be checked. But checkbox is unchecked.");
        } else if (checkBox.isSelected() && !shouldBeChecked) {
            logger.error("Expected: Checkbox (" + element.getBy(locatorPair) + ") should not be checked. But checkbox is checked.");
            Assertion.equalsFalse(true, "Expected: Checkbox (" + element.getBy(locatorPair) + ") should not be checked. But checkbox is checked.");
        }
    }
}
