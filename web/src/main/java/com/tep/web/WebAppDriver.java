package com.tep.web;

import com.tep.web.browser.*;
import com.tep.web.validation.*;
import com.tep.web.config.WebEnums;
import org.openqa.selenium.WebDriver;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.base.SeleniumDriver;
import com.tep.web.element.click.ActionClick;
import com.tep.web.element.getter.GetElement;
import com.tep.web.element.click.SeleniumClick;
import com.tep.web.element.getter.GetAttribute;
import com.tep.web.element.click.JavaScriptClick;
import com.tep.web.element.sendkey.ActionSendKeys;
import com.tep.web.element.checkbox.ActionCheckBox;
import com.tep.web.element.dropdown.ActionDropdown;
import com.tep.web.element.sendkey.SeleniumSendKeys;
import com.tep.web.element.checkbox.SeleniumCheckBox;
import com.tep.web.element.dropdown.SeleniumDropdown;
import com.tep.web.element.sendkey.JavaScriptSendKeys;
import com.tep.web.element.checkbox.JavaScriptCheckBox;
import com.tep.web.element.dropdown.JavaScriptDropdown;
import com.tep.web.element.mousehover.ActionMouseHover;
import com.tep.web.element.radiobutton.ActionRadioButton;
import com.tep.web.element.radiobutton.SeleniumRadioButton;
import com.tep.web.element.mousehover.JavaScriptMouseHover;
import com.tep.web.element.radiobutton.JavaScriptRadioButton;

/**
 * WebAppDriver is a class that encapsulates the initialization and management of the web browser and various web automation actions.
 * It provides methods to interact with web elements, perform browser operations, and validate the page state.
 */
public class WebAppDriver {

    /** The SeleniumDriver instance used for managing the browser. */
    protected SeleniumDriver driver = new SeleniumDriver();

    // Browser functions
    /** The TabEvent instance for handling browser tab operations. */
    public TabEvent tabEvent;

    /** The WindowEvent instance for handling browser window operations. */
    public WindowEvent windowEvent;

    /** The BrowserEvent instance for handling browser-specific events. */
    public BrowserEvent browserEvent;

    /** The WindowScrolling instance for managing window scrolling actions. */
    public WindowScrolling windowScrolling;

    /** The WindowManipulation instance for performing window manipulations. */
    public WindowManipulation windowManipulation;

    // Element action functions
    /** The ActionClick instance for handling element click actions. */
    public ActionClick actionClick;

    /** The ActionSendKeys instance for sending keystrokes to elements. */
    public ActionSendKeys actionSendKeys;

    /** The ActionCheckBox instance for interacting with checkboxes. */
    public ActionCheckBox actionCheckBox;

    /** The ActionDropdown instance for interacting with dropdowns. */
    public ActionDropdown actionDropdown;

    /** The ActionMouseHover instance for performing mouse hover actions. */
    public ActionMouseHover actionMouseHover;

    /** The ActionRadioButton instance for interacting with radio buttons. */
    public ActionRadioButton actionRadioButton;

    // Element JavaScript functions
    /** The JavaScriptClick instance for performing click actions using JavaScript. */
    public JavaScriptClick javaScriptClick;

    /** The JavaScriptSendKeys instance for sending keystrokes using JavaScript. */
    public JavaScriptSendKeys javaScriptSendKeys;

    /** The JavaScriptCheckBox instance for interacting with checkboxes using JavaScript. */
    public JavaScriptCheckBox javaScriptCheckBox;

    /** The JavaScriptDropdown instance for interacting with dropdowns using JavaScript. */
    public JavaScriptDropdown javaScriptDropdown;

    /** The JavaScriptMouseHover instance for performing mouse hover actions using JavaScript. */
    public JavaScriptMouseHover javaScriptMouseHover;

    /** The JavaScriptRadioButton instance for interacting with radio buttons using JavaScript. */
    public JavaScriptRadioButton javaScriptRadioButton;

    // Element Selenium functions
    /** The SeleniumClick instance for performing element click actions using Selenium. */
    public SeleniumClick seleniumClick;

    /** The SeleniumSendKeys instance for sending keystrokes using Selenium. */
    public SeleniumSendKeys seleniumSendKeys;

    /** The SeleniumCheckBox instance for interacting with checkboxes using Selenium. */
    public SeleniumCheckBox seleniumCheckBox;

    /** The SeleniumDropdown instance for interacting with dropdowns using Selenium. */
    public SeleniumDropdown seleniumDropdown;

    /** The SeleniumRadioButton instance for interacting with radio buttons using Selenium. */
    public SeleniumRadioButton seleniumRadioButton;

    // Validation functions
    /** The PageValidation instance for validating page properties such as title and loading status. */
    public PageValidation pageValidation;

    /** The TextValidation instance for validating text values on the page. */
    public TextValidation textValidation;

    /** The TypeValidation instance for validating element types. */
    public TypeValidation typeValidation;

    /** The CheckBoxValidation instance for validating checkbox states. */
    public CheckBoxValidation checkBoxValidation;

    /** The DropdownValidation instance for validating dropdown values. */
    public DropdownValidation dropdownValidation;

    /** The PresenceValidation instance for validating element presence on the page. */
    public PresenceValidation presenceValidation;

    /** The AttributeValidation instance for validating element attributes. */
    public AttributeValidation attributeValidation;

    /** The EnablementValidation instance for validating whether an element is enabled or disabled. */
    public EnablementValidation enablementValidation;

    /** The RadioButtonValidation instance for validating radio button selections. */
    public RadioButtonValidation radioButtonValidation;

    // Getters
    /** The GetElement instance for retrieving elements from the page. */
    public GetElement getElement;

    /** The GetAttribute instance for retrieving element attributes. */
    public GetAttribute getAttribute;

    // Waits
    /** The SeleniumWaits instance for waiting for page elements and conditions. */
    public SeleniumWaits seleniumWaits;

    /**
     * Default constructor for WebAppDriver. Initializes the WebDriver instance.
     */
    public WebAppDriver() {
        try {
            this.driver = new SeleniumDriver();
        } catch (Exception e) {
            Assertion.equalsTrue(false, "Failed to initialize WebAppDriver: " + e.getMessage());
        }
    }

    /**
     * Constructor to initialize the WebAppDriver with a specified browser type.
     *
     * @param browserType The type of browser to use for the Selenium WebDriver.
     */
    public WebAppDriver(WebEnums.BrowserType browserType) {
        try {
            this.driver = new SeleniumDriver(browserType);
            initializeDriver(this.driver);
        } catch (Exception e) {
            Assertion.equalsTrue(false, "Failed to initialize WebAppDriver: " + e.getMessage());
        }
    }

    /**
     * Initializes various actions and validation functions used by the WebAppDriver.
     * This method is called when the WebDriver instance is created or when switching browsers.
     *
     * @param driver The SeleniumDriver instance to initialize the actions and validations.
     */
    public void initializeDriver(SeleniumDriver driver) {

        // Browser functions
        tabEvent = new TabEvent(driver);
        windowEvent = new WindowEvent(driver);
        browserEvent = new BrowserEvent(driver);
        windowScrolling = new WindowScrolling(driver);
        windowManipulation = new WindowManipulation(driver);

        // Element action functions
        actionClick = new ActionClick(driver);
        actionSendKeys = new ActionSendKeys(driver);
        actionCheckBox = new ActionCheckBox(driver);
        actionDropdown = new ActionDropdown(driver);
        actionMouseHover = new ActionMouseHover(driver);
        actionRadioButton = new ActionRadioButton(driver);

        // Element JavaScript functions
        javaScriptClick = new JavaScriptClick(driver);
        javaScriptSendKeys = new JavaScriptSendKeys(driver);
        javaScriptCheckBox = new JavaScriptCheckBox(driver);
        javaScriptDropdown = new JavaScriptDropdown(driver);
        javaScriptMouseHover = new JavaScriptMouseHover(driver);
        javaScriptRadioButton = new JavaScriptRadioButton(driver);

        // Element Selenium functions
        seleniumClick = new SeleniumClick(driver);
        seleniumSendKeys = new SeleniumSendKeys(driver);
        seleniumCheckBox = new SeleniumCheckBox(driver);
        seleniumDropdown = new SeleniumDropdown(driver);
        seleniumRadioButton = new SeleniumRadioButton(driver);

        // Validation functions
        pageValidation = new PageValidation(driver);
        textValidation = new TextValidation(driver);
        typeValidation = TypeValidation.getInstance();
        checkBoxValidation = new CheckBoxValidation(driver);
        dropdownValidation = new DropdownValidation(driver);
        presenceValidation = new PresenceValidation(driver);
        attributeValidation = new AttributeValidation(driver);
        enablementValidation = new EnablementValidation(driver);
        radioButtonValidation = new RadioButtonValidation(driver);

        // Getters
        getElement = new GetElement();
        getAttribute = new GetAttribute(driver);

        // Waits
        seleniumWaits = new SeleniumWaits(driver);
    }

    /**
     * Opens the browser and initializes the WebDriver with the specified browser type.
     *
     * @param browserType The type of browser to launch.
     */
    public void openBrowser(WebEnums.BrowserType browserType) {
        this.driver.openBrowser(browserType);
        initializeDriver(this.driver);
    }

    /**
     * Retrieves the WebDriver instance that controls the browser.
     *
     * @return The WebDriver instance, or null if the browser is not initialized.
     */
    public WebDriver getBrowser() {
        if(driver.getBrowser() == null)
            return null;
        else
            return driver.getBrowser();
    }

    /**
     * Closes the browser and releases any resources used by the WebDriver.
     */
    public void closeBrowser() {
        try {
            driver.getPageObjects().unload();
            driver.closeBrowser();
        } catch (Exception e) {
            Assertion.equalsTrue(false, "Failed to close WebDriver: " + e.getMessage());
        }
    }
}
