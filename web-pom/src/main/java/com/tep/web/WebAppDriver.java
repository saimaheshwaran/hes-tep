package com.tep.web;

import com.tep.web.base.Driver;
import com.tep.web.base.Waits;
import com.tep.web.browser.*;
import com.tep.web.config.Enums;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import com.tep.web.element.checkbox.JavaScriptCheckBox;
import com.tep.web.element.checkbox.SeleniumCheckBox;
import com.tep.web.element.click.ActionClick;
import com.tep.web.element.click.JavaScriptClick;
import com.tep.web.element.click.SeleniumClick;
import com.tep.web.element.dropdown.ActionDropdown;
import com.tep.web.element.dropdown.JavaScriptDropdown;
import com.tep.web.element.dropdown.SeleniumDropdown;
import com.tep.web.element.getter.GetAttribute;
import com.tep.web.element.getter.GetElement;
import com.tep.web.element.mousehover.ActionMouseHover;
import com.tep.web.element.mousehover.JavaScriptMouseHover;
import com.tep.web.element.radiobutton.ActionRadioButton;
import com.tep.web.element.radiobutton.JavaScriptRadioButton;
import com.tep.web.element.radiobutton.SeleniumRadioButton;
import com.tep.web.element.sendKey.ActionSendKeys;
import com.tep.web.element.sendKey.JavaScriptSendKeys;
import com.tep.web.element.sendKey.SeleniumSendKeys;
import com.tep.web.validation.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


import java.util.Map;

/**
 * WebAppDriver class to initialize and manage web driver and various element actions and validations.
 */
public class WebAppDriver {

    protected Driver driver;
    protected PageObjects objects;
    protected Map.Entry<String, String> locatorPair;
    private static final Logger logger = LoggerFactory.getLogger(WebAppDriver.class);

    // Browser functions
    public BrowserHandling browserHandling;
    public TabHandling tabHandling;
    public WindowHandling windowHandling;
    public WindowManipulation windowManipulation;
    public WindowScrolling windowScrolling;

    // Element action functions
    public ActionClick actionClick;
    public ActionCheckBox actionCheckBox;
    public ActionDropdown actionDropdown;
    public ActionMouseHover actionMouseHover;
    public ActionRadioButton actionRadioButton;
    public ActionSendKeys actionSendKeys;

    // Element JavaScript functions
    public JavaScriptClick javaScriptClick;
    public JavaScriptCheckBox javaScriptCheckBox;
    public JavaScriptDropdown javaScriptDropdown;
    public JavaScriptMouseHover javaScriptMouseHover;
    public JavaScriptRadioButton javaScriptRadioButton;
    public JavaScriptSendKeys javaScriptSendKeys;

    // Element Selenium functions
    public SeleniumClick seleniumClick;
    public SeleniumCheckBox seleniumCheckBox;
    public SeleniumDropdown seleniumDropdown;
    public SeleniumRadioButton seleniumRadioButton;
    public SeleniumSendKeys seleniumSendKeys;

    // Validation functions
    public PageValidation pageValidation;
    public AttributeValidation attributeValidation;
    public CheckBoxValidation checkBoxValidation;
    public DropdownValidation dropdownValidation;
    public EnablementValidation enablementValidation;
    public PresenceValidation presenceValidation;
    public RadioButtonValidation radioButtonValidation;
    public TextValidation textValidation;
    public TypeValidation typeValidation;

    // Getters
    public GetElement getElement;
    public GetAttribute getAttribute;

    // Waits
    public Waits waits;

    /**
     * Constructor to initialize the WebAppDriver with a specified browser type.
     *
     */
    public WebAppDriver() {
        try {
            this.driver = new Driver();
            this.objects = new PageObjects();
            initializeDriver(driver.getDriver(), objects);
        } catch (Exception e) {
            logger.info("Failed to initialize WebDriver:");
            Assert.fail("Failed to initialize WebDriver: " + e.getMessage());
        }
    }

    /**
     * Constructor to initialize the WebAppDriver with a specified browser type and PageObjects.
     *
     * @param objects     the PageObjects instance to use.
     */
    public WebAppDriver(PageObjects objects) {
        try {
            this.driver = new Driver();
            this.objects = objects;
        } catch (Exception e) {
            logger.info("Failed to initialize WebDriver:");
            Assert.fail("Failed to initialize WebDriver: " + e.getMessage());
        }
    }

    public void open(Enums.BrowserType browserType) {
        this.driver.open(browserType);
        initializeDriver(driver.getDriver(), objects);
    }

    /**
     * Initializes the driver and various element actions and validations.
     *
     * @param driver  the WebDriver instance to use.
     * @param objects the PageObjects instance to use.
     */
    protected void initializeDriver(WebDriver driver, PageObjects objects) {
        // Browser functions
        browserHandling = new BrowserHandling(driver, objects);
        tabHandling = new TabHandling(driver);
        windowHandling = new WindowHandling(driver);
        windowManipulation = new WindowManipulation(driver, objects);
        windowScrolling = new WindowScrolling(driver, objects);

        // Element action functions
        actionClick = new ActionClick(driver, objects);
        actionCheckBox = new ActionCheckBox(driver, objects);
        actionDropdown = new ActionDropdown(driver, objects);
        actionMouseHover = new ActionMouseHover(driver, objects);
        actionRadioButton = new ActionRadioButton(driver, objects);
        actionSendKeys = new ActionSendKeys(driver, objects);

        // Element JavaScript functions
        javaScriptClick = new JavaScriptClick(driver, objects);
        javaScriptCheckBox = new JavaScriptCheckBox(driver, objects);
        javaScriptDropdown = new JavaScriptDropdown(driver, objects);
        javaScriptMouseHover = new JavaScriptMouseHover(driver, objects);
        javaScriptRadioButton = new JavaScriptRadioButton(driver, objects);
        javaScriptSendKeys = new JavaScriptSendKeys(driver, objects);

        // Element Selenium functions
        seleniumClick = new SeleniumClick(driver, objects);
        seleniumCheckBox = new SeleniumCheckBox(driver, objects);
        seleniumDropdown = new SeleniumDropdown(driver, objects);
        seleniumRadioButton = new SeleniumRadioButton(driver, objects);
        seleniumSendKeys = new SeleniumSendKeys(driver, objects);

        // Validation functions
        pageValidation = new PageValidation(driver);
        attributeValidation = new AttributeValidation(driver, objects);
        checkBoxValidation = new CheckBoxValidation(driver, objects);
        dropdownValidation = new DropdownValidation(driver, objects);
        enablementValidation = new EnablementValidation(driver, objects);
        presenceValidation = new PresenceValidation(driver, objects);
        radioButtonValidation = new RadioButtonValidation(driver, objects);
        textValidation = new TextValidation(driver, objects);
        typeValidation = TypeValidation.getInstance();

        // Getters
        getAttribute = new GetAttribute(driver, objects);
        getElement = new GetElement(driver, objects);

        // Waits
        waits = new Waits(driver);
    }

    public WebDriver getBrowser() {
        if(driver.getDriver() == null)
            return null;
        else
            return driver.getDriver();
    }

    /**
     * Closes the driver and unloads the PageObjects.
     */
    public void close() {
        try {
            objects.unload();
            driver.close();
        } catch (Exception e) {
            logger.info("Failed to close WebDriver:");
            Assert.fail("Failed to close WebDriver: " + e.getMessage());

        }
    }

}
