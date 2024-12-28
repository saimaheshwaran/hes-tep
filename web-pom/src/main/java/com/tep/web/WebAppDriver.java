package com.tep.web;

import com.tep.web.base.SeleniumDriver;
import com.tep.web.browser.*;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.validation.*;
import com.tep.web.config.Enums;
import org.openqa.selenium.WebDriver;
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

public class WebAppDriver {

    protected SeleniumDriver driver = new SeleniumDriver();

    // Browser functions
    public TabEvent tabEvent;
    public WindowEvent windowEvent;
    public BrowserEvent browserEvent;
    public WindowScrolling windowScrolling;
    public WindowManipulation windowManipulation;

    // Element action functions
    public ActionClick actionClick;
    public ActionSendKeys actionSendKeys;
    public ActionCheckBox actionCheckBox;
    public ActionDropdown actionDropdown;
    public ActionMouseHover actionMouseHover;
    public ActionRadioButton actionRadioButton;

    // Element JavaScript functions
    public JavaScriptClick javaScriptClick;
    public JavaScriptSendKeys javaScriptSendKeys;
    public JavaScriptCheckBox javaScriptCheckBox;
    public JavaScriptDropdown javaScriptDropdown;
    public JavaScriptMouseHover javaScriptMouseHover;
    public JavaScriptRadioButton javaScriptRadioButton;

    // Element Selenium functions
    public SeleniumClick seleniumClick;
    public SeleniumSendKeys seleniumSendKeys;
    public SeleniumCheckBox seleniumCheckBox;
    public SeleniumDropdown seleniumDropdown;
    public SeleniumRadioButton seleniumRadioButton;

    // Validation functions
    public PageValidation pageValidation;
    public TextValidation textValidation;
    public TypeValidation typeValidation;
    public CheckBoxValidation checkBoxValidation;
    public DropdownValidation dropdownValidation;
    public PresenceValidation presenceValidation;
    public AttributeValidation attributeValidation;
    public EnablementValidation enablementValidation;
    public RadioButtonValidation radioButtonValidation;

    // Getters
    public GetElement getElement;
    public GetAttribute getAttribute;

    // Waits
    public SeleniumWaits seleniumWaits;

    public WebAppDriver() {
        try {
            this.driver = new SeleniumDriver();
        } catch (Exception e) {
            Assertion.equalsTrue(false,"Failed to initialize WebAppDriver: " + e.getMessage());
        }
    }

    public WebAppDriver(Enums.BrowserType browserType) {
        try {
            this.driver = new SeleniumDriver(browserType);
            initializeDriver(this.driver);
        } catch (Exception e) {
            Assertion.equalsTrue(false, "Failed to initialize WebAppDriver: " + e.getMessage());
        }
    }

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

    public void openBrowser(Enums.BrowserType browserType) {
        this.driver.openBrowser(browserType);
        initializeDriver(this.driver);
    }

    public WebDriver getBrowser() {
        if(driver.getBrowser() == null)
            return null;
        else
            return driver.getBrowser();
    }

    public void closeBrowser() {
        try {
            driver.getPageObjects().unload();
            driver.closeBrowser();
        } catch (Exception e) {
            Assertion.equalsTrue(false, "Failed to close WebDriver: " + e.getMessage());
        }
    }

}
