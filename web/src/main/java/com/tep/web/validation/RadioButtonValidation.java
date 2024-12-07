package com.tep.web.validation;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.List;
import java.util.Map;

public class RadioButtonValidation {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;

    public RadioButtonValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public RadioButtonValidation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
    }

    public void isSelected(String objName, boolean shouldBeSelected) { isSelected(objects.get(objName), shouldBeSelected); }

    public void isSelected(Map.Entry<String, String> locatorPair, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        WebElement radioButton = element.get(locatorPair);
        if ((!radioButton.isSelected()) && shouldBeSelected) {
            Assertion.equalsTrue(false, "Expected : Radio button (" + element.getBy(locatorPair) + ") should be selected. But Radio button is not selected\n");
        } else if (radioButton.isSelected() && !shouldBeSelected) {
            Assertion.equalsFalse(true, "Expected : Radio button (" + element.getBy(locatorPair) + ") should not be selected. But Radio button is not selected\n");
        }
    }

    public void isSelectedFromRadioButtonGroup(String objName, String option, String selectionType, boolean shouldBeSelected) {
        isSelectedFromRadioButtonGroup(objects.get(objName), option, selectionType, shouldBeSelected);
    }

    public void isSelectedFromRadioButtonGroup(Map.Entry<String, String> locatorPair, String option, String selectionType, boolean shouldBeSelected) {
        waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
        List<WebElement> radioButtonGroup = driver.findElements(this.element.getBy(locatorPair));
        for (WebElement radiobutton : radioButtonGroup) {
            if (selectionType.equals("value")) {
                if (radiobutton.getAttribute("value").equals(option)) {
                    if ((!radiobutton.isSelected()) && shouldBeSelected)
                        Assertion.equalsTrue(false, "Expected : Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should be selected with option " + option + " But radio button is not selected\n");
                    else if (radiobutton.isSelected() && !shouldBeSelected)
                        Assertion.equalsTrue(false, "Expected : Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should not be selected with option " + option + " But radio button is selected\n");
                    break;
                }
            } else if (radiobutton.getText().equals(option)) {
                if ((!radiobutton.isSelected()) && shouldBeSelected)
                    Assertion.equalsTrue(false, "Expected : Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should be selected with text " + option + ". But radio button is not selected\n");
                else if (radiobutton.isSelected() && !shouldBeSelected)
                    Assertion.equalsTrue(false, "Expected : Radio button By." + locatorPair.getKey() + ":" + locatorPair.getValue() + " should not be selected with text " + option + ". But radio button is selected\n");
                break;
            }
        }
    }

}
