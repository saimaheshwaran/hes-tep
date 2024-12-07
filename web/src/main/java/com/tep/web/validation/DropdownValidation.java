package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.tep.web.config.Constants;

import java.util.Map;

public class DropdownValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public DropdownValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public DropdownValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void isSelected(String objName, String type, String option, boolean shouldBeSelected) { isSelected(objects.get(objName), type, option, shouldBeSelected); }

    public void isSelected(Map.Entry<String, String> locatorPair, String type, String option, boolean shouldBeSelected) {
        Select selectList = null;
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement dropDown = element.get(locatorPair);
        selectList = new Select(dropDown);
        String actualValue = "";
        if (type.equals("text"))
            actualValue = selectList.getFirstSelectedOption().getText();
        else
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        if ((!actualValue.equals(option)) && (shouldBeSelected))
            Assertion.equalsTrue(false,"Expected : " + option + " should be selected from Dropdown. But "+ actualValue +" is selected from Dropdown\n");
        else if ((actualValue.equals(option)) && (!shouldBeSelected))
            Assertion.equalsFalse(true,"Expected : " + option + " should not be selected from Dropdown. But "+ actualValue +" is selected from Dropdown \n");
    }

}
