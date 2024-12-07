package com.tep.web.element.radiobutton;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import com.tep.web.element.click.ActionClick;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;

import java.util.Map;

public class ActionRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private ActionClick actionClick;

    public ActionRadioButton(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
        actionClick = new ActionClick(driver);
    }

    public ActionRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
        actionClick = new ActionClick(driver);
    }

    public void select(String objName) { select(objects.get(objName)); }

    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            WebElement radioButton = this.element.get(locatorPair);
            actions.moveToElement(radioButton).perform();
            if(!radioButton.isSelected()) {
                actionClick.click(locatorPair);
            }
            actions.release().perform();
        } catch (StaleElementReferenceException ignored) {
            select(locatorPair);
        }
    }

}
