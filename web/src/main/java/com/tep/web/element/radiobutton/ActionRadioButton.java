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

/**
 * ActionRadioButton class to handle radio button interactions.
 */
public class ActionRadioButton {

    private Waits waits;
    private WebDriver driver;
    private Element element;
    private PageObjects objects;
    private ActionClick actionClick;

    /**
     * Constructor to initialize the ActionRadioButton with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public ActionRadioButton(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.actionClick = new ActionClick(driver);
    }

    /**
     * Constructor to initialize the ActionRadioButton with a WebDriver instance and PageObjects.
     *
     * @param driver  the WebDriver instance to interact with.
     * @param objects the PageObjects instance to retrieve element locators.
     */
    public ActionRadioButton(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        this.actionClick = new ActionClick(driver);
    }

    /**
     * Selects the radio button identified by the object name.
     *
     * @param objName the name of the object whose locator is to be retrieved.
     */
    public void select(String objName) {
        select(objects.get(objName));
    }

    /**
     * Selects the radio button identified by the locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void select(Map.Entry<String, String> locatorPair) {
        try {
            waits.waitForElementToDisplay(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
            Actions actions = new Actions(driver);
            WebElement radioButton = this.element.get(locatorPair);
            actions.moveToElement(radioButton).perform();
            if (!radioButton.isSelected()) {
                actionClick.click(locatorPair);
            }
            actions.release().perform();
        } catch (StaleElementReferenceException ignored) {
            select(locatorPair);
        }
    }
}
