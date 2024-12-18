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
 * EnablementValidation class to handle validation of element enablement.
 */
public class EnablementValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private static final Logger logger = LoggerFactory.getLogger(EnablementValidation.class);

    /**
     * Constructor to initialize the EnablementValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public EnablementValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("EnablementValidation initialized with Webdriver successfully");
    }

    /**
     * Constructor to initialize the EnablementValidation with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public EnablementValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("EnablementValidation with Webdriver and Pageobjects initialized successfully");
    }

    /**
     * Verifies if the element identified by the object name is enabled or disabled.
     *
     * @param objName       the name of the object whose locator is to be retrieved.
     * @param elementEnabled true if the element should be enabled, false otherwise.
     */
    public void verify(String objName, boolean elementEnabled) {
        verify(objects.get(objName), elementEnabled);
    }

    /**
     * Verifies if the element identified by the locator pair is enabled or disabled.
     *
     * @param locatorPair   a Map.Entry containing the locator type and value.
     * @param elementEnabled true if the element should be enabled, false otherwise.
     */
    public void verify(Map.Entry<String, String> locatorPair, boolean elementEnabled) {
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement attributeElement = element.get(locatorPair);
        if (!attributeElement.isEnabled() && elementEnabled) {
            logger.error("Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should be enabled, but it is not.");
            Assertion.equalsTrue(attributeElement.isEnabled(), "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should be enabled. But element is not enabled.");
        } else if (attributeElement.isEnabled() && !elementEnabled) {
            logger.error ("Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should not be enabled, but it is.");
            Assertion.equalsFalse(attributeElement.isEnabled(), "Expected: Element with attribute \"" + locatorPair.getKey() + "=" + locatorPair.getValue() + "\" should not be enabled. But element is enabled.");
        }
    }

    public void verify(WebElement webElement, boolean elementEnabled) {
        waits.waitForPresenceOfElementsLocated(webElement, Constants.IMPLICIT_WAIT_TIME_SEC);
        if (!webElement.isEnabled() && elementEnabled) {
            logger.error("Element " + webElement + "=" + " should be enabled, but it is not.");
            Assertion.equalsTrue(webElement.isEnabled(), "Expected: Element " + webElement + " should be enabled. But element is not enabled.");
        } else if (webElement.isEnabled() && !elementEnabled) {
            logger.error ("Element " + webElement + " should not be enabled, but it is.");
            Assertion.equalsFalse(webElement.isEnabled(), "Expected: Element " + webElement + " should not be enabled. But element is enabled.");
        }
    }

}
