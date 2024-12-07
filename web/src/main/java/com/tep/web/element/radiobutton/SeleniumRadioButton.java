package com.tep.web.element.radiobutton;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.click.SeleniumClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.tep.web.config.Constants;

import java.util.List;
import java.util.Map;

public class SeleniumRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private SeleniumClick seleniumClick;

    public SeleniumRadioButton(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
        seleniumClick = new SeleniumClick(driver);
    }

    public SeleniumRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
        seleniumClick = new SeleniumClick(driver);
    }

    public void select(String objName) { select(objects.get(objName)); }

    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement radioButton = this.element.get(locatorPair);
            if(!radioButton.isSelected()) {
                seleniumClick.click(locatorPair);
            }
        } catch (StaleElementReferenceException ignored) {
            select(locatorPair);
        }
    }

    public void selectFromRadioButtonGroup(String option, String selectionType, String objName) {
        selectFromRadioButtonGroup(option, selectionType, objects.get(objName));
    }

    public void selectFromRadioButtonGroup(String option, String selectionType, Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            List<WebElement> radioButtonGroup = driver.findElements(this.element.getBy(locatorPair));
            for (WebElement radiobutton : radioButtonGroup) {
                if (selectionType.equals("value")) {
                    if (radiobutton.getAttribute("value").equals(option) && !radiobutton.isSelected())
                        radiobutton.click();
                } else if (selectionType.equals("text")) {
                    if (radiobutton.getText().equals(option) && !radiobutton.isSelected())
                        radiobutton.click();
                }
            }
        } catch (StaleElementReferenceException e) {
            selectFromRadioButtonGroup( option,  selectionType, locatorPair);
        }
    }

}
