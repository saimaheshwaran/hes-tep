package com.tep.web.element.checkbox;

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

public class ActionCheckBox {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private ActionClick actionClick;

    public ActionCheckBox(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        element = new Element(driver);
        actionClick = new ActionClick(driver);
    }

    public ActionCheckBox(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        waits = new Waits(driver);
        element = new Element(driver);
        actionClick = new ActionClick(driver);
    }

    public void check(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBox).perform();
            actions.release().perform();
            if(!checkBox.isSelected()) {
                actionClick.click(locatorPair);
            }
        } catch (StaleElementReferenceException ignored) {
            check(locatorPair);
        }
    }

    public void uncheck(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBox).perform();
            actions.release().perform();
            if(checkBox.isSelected()) {
                actionClick.click(locatorPair);
            }
        } catch (StaleElementReferenceException ignored) {
            uncheck(locatorPair);
        }
    }

    public void toggle(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            WebElement checkBox = this.element.get(locatorPair);
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBox).click().perform();
            actions.release().perform();
        } catch (StaleElementReferenceException ignored) {
            toggle(locatorPair);
        }
    }

    public void check(String objName) { check(objects.get(objName)); }

    public void uncheck(String objName) { uncheck(objects.get(objName)); }

    public void toggle(String objName) { toggle(objects.get(objName)); }
}
