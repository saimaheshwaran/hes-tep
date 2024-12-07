package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.Map;

public class CheckBoxValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public CheckBoxValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public CheckBoxValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void isChecked(String objName, boolean shouldBeChecked) { isChecked(objects.get(objName), shouldBeChecked); }

    public void isChecked(Map.Entry<String, String> locatorPair, boolean shouldBeChecked) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement checkBox = element.get(locatorPair);
        if ((!checkBox.isSelected()) && shouldBeChecked) {
            Assertion.equalsTrue(false, "Expected : checkbox ("+ element.getBy(locatorPair) +") should be checked. But checkbox is unchecked\n");
        }else if (checkBox.isSelected() && !shouldBeChecked) {
            Assertion.equalsFalse(true, "Expected : checkbox ("+ element.getBy(locatorPair) +") should not be checked. But checkbox is checked\n");
        }
    }

}
