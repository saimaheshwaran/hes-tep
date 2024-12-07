package com.tep.web;

import com.tep.web.base.Driver;
import com.tep.web.base.Waits;
import com.tep.web.browser.*;
import com.tep.web.config.Enums;
import com.tep.web.config.PageObjects;
import com.tep.web.element.checkbox.ActionCheckBox;
import com.tep.web.element.checkbox.JavaScriptChekBox;
import com.tep.web.element.checkbox.SeleniumCheckBox;
import com.tep.web.element.click.ActionClick;
import com.tep.web.element.click.JavaScriptClick;
import com.tep.web.element.click.SeleniumClick;
import com.tep.web.element.dropdown.ActionDropdown;
import com.tep.web.element.dropdown.JavascritpDropdown;
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

import java.util.Map;

public class WebAppDriver {

    protected Driver driver;
    protected PageObjects objects;
    protected Map.Entry<String, String> locatorPair;

    //Browser functions
    public BrowserHandling browserHandling;
    public TabHandling tabHandling;
    public WindowHandling windowHandling;
    public WindowManipulation windowManipulation;
    public WindowScrolling windowScrolling;

    //Element action functions
    public ActionClick actionClick;
    public ActionCheckBox actionCheckBox;
    public ActionDropdown actionDropdown;
    public ActionMouseHover actionMouseHover;
    public ActionRadioButton actionRadioButton;
    public ActionSendKeys actionSendKeys;

    //Element Javascript functions
    public JavaScriptClick javaScriptClick;
    public JavaScriptChekBox javaScriptChekBox;
    public JavascritpDropdown javascritpDropdown;
    public JavaScriptMouseHover javaScriptMouseHover;
    public JavaScriptRadioButton javaScriptRadioButton;
    public JavaScriptSendKeys javaScriptSendKeys;

    //Element Selenium functions
    public SeleniumClick seleniumClick;
    public SeleniumCheckBox seleniumCheckBox;
    public SeleniumDropdown seleniumDropdown;
    public SeleniumRadioButton seleniumRadioButton;
    public SeleniumSendKeys seleniumSendKeys;

    //Validation functions
    public PageValidation pageValidation;
    public AttributeValidation attributeValidation;
    public CheckBoxValidation checkBoxValidation;
    public DropdownValidation dropdownValidation;
    public EnablementValidation enablementValidation;
    public PresenceValidation presenceValidation;
    public RadioButtonValidation radioButtonValidation;
    public TextValidation textValidation;
    public TypeValidation typeValidation;

    //Getters
    public GetElement getElement;
    public GetAttribute getAttribute;

    //Wais
    public Waits waits;

    public WebAppDriver(Enums.BrowserType browserType) {
        this.driver = new Driver(browserType);
        this.objects = new PageObjects();
        intializeDriver(driver.getDriver(), objects);
    }

    public WebAppDriver(Enums.BrowserType browserType, PageObjects objects) {
        this.driver = new Driver(browserType);
        this.objects = objects;
        intializeDriver(driver.getDriver(), objects);
    }

    protected void intializeDriver(WebDriver driver, PageObjects objects) {

        //Browser functions
        browserHandling = new BrowserHandling(driver, objects);
        tabHandling = new TabHandling(driver);
        windowHandling = new WindowHandling(driver);
        windowManipulation = new WindowManipulation(driver, objects);
        windowScrolling = new WindowScrolling(driver, objects);

        //Element action functions
        actionClick = new ActionClick(driver, objects);
        actionCheckBox = new ActionCheckBox(driver, objects);
        actionDropdown = new ActionDropdown(driver, objects);
        actionMouseHover = new ActionMouseHover(driver, objects);
        actionRadioButton = new ActionRadioButton(driver, objects);
        actionSendKeys = new ActionSendKeys(driver, objects);

        //Element javascritp functions
        javaScriptClick = new JavaScriptClick(driver, objects);
        javaScriptChekBox = new JavaScriptChekBox(driver, objects);
        javascritpDropdown = new JavascritpDropdown(driver, objects);
        javaScriptMouseHover = new JavaScriptMouseHover(driver, objects);
        javaScriptRadioButton = new JavaScriptRadioButton(driver, objects);
        javaScriptSendKeys = new JavaScriptSendKeys(driver, objects);

        //Element selenium functions
        seleniumClick = new SeleniumClick(driver, objects);
        seleniumCheckBox = new SeleniumCheckBox(driver, objects);
        seleniumDropdown = new SeleniumDropdown(driver, objects);
        seleniumRadioButton = new SeleniumRadioButton(driver, objects);
        seleniumSendKeys = new SeleniumSendKeys(driver, objects);

        //Validation functions
        pageValidation = new PageValidation(driver);
        attributeValidation = new AttributeValidation(driver, objects);
        checkBoxValidation = new CheckBoxValidation(driver, objects);
        dropdownValidation = new DropdownValidation(driver, objects);
        enablementValidation = new EnablementValidation(driver, objects);
        presenceValidation = new PresenceValidation(driver, objects);
        radioButtonValidation = new RadioButtonValidation(driver, objects);
        textValidation = new TextValidation(driver, objects);
        typeValidation = new TypeValidation();

        //Getters
        getAttribute = new GetAttribute(driver, objects);
        getElement = new GetElement(driver, objects);

        //Waits
        waits = new Waits(driver);

    }

    public void close() {
        objects.unload();
        driver.close();
    }

}
