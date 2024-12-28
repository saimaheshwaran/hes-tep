package com.tep.web.element.getter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class GetElement {

    public boolean isSelected(WebElement webElement) {
        boolean elementSelected = false;
        try {
            elementSelected = webElement.isSelected();
        } catch (StaleElementReferenceException e) {
            elementSelected = isSelected(webElement);
        }
        return elementSelected;
    }

    public boolean isEnabled(WebElement webElement) {
        boolean elementEnabled = false;
        try {
            elementEnabled = webElement.isEnabled();
        } catch (StaleElementReferenceException e) {
            elementEnabled = isEnabled(webElement);
        }
        return elementEnabled;
    }

    public boolean isDisplayed(WebElement webElement) {
        boolean elementDisplayed = false;
        try {
            elementDisplayed = webElement.isDisplayed();
        } catch (StaleElementReferenceException e) {
            elementDisplayed = isDisplayed(webElement);
        } catch (NoSuchElementException e) {
            elementDisplayed = false;
        }
        return elementDisplayed;
    }
}
