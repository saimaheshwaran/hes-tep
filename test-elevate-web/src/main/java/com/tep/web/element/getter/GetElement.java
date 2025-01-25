package com.tep.web.element.getter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * The GetElement class provides utility methods to check the state of a WebElement.
 * These methods handle common scenarios such as checking if an element is selected, enabled, or displayed.
 * The class also accounts for potential stale or missing elements during interaction.
 */
public class GetElement {

    /**
     * Checks if the provided WebElement is selected (for elements like checkboxes or radio buttons).
     *
     * @param webElement The WebElement to check.
     * @return True if the element is selected, false otherwise.
     */
    public boolean isSelected(WebElement webElement) {
        boolean elementSelected = false;
        try {
            elementSelected = webElement.isSelected();  // Checks if the element is selected.
        } catch (StaleElementReferenceException e) {
            elementSelected = isSelected(webElement);  // Retry if element reference becomes stale.
        }
        return elementSelected;
    }

    /**
     * Checks if the provided WebElement is enabled (i.e., can be interacted with).
     *
     * @param webElement The WebElement to check.
     * @return True if the element is enabled, false otherwise.
     */
    public boolean isEnabled(WebElement webElement) {
        boolean elementEnabled = false;
        try {
            elementEnabled = webElement.isEnabled();  // Checks if the element is enabled.
        } catch (StaleElementReferenceException e) {
            elementEnabled = isEnabled(webElement);  // Retry if element reference becomes stale.
        }
        return elementEnabled;
    }

    /**
     * Checks if the provided WebElement is displayed (i.e., visible on the page).
     *
     * @param webElement The WebElement to check.
     * @return True if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(WebElement webElement) {
        boolean elementDisplayed = false;
        try {
            elementDisplayed = webElement.isDisplayed();  // Checks if the element is visible.
        } catch (StaleElementReferenceException e) {
            elementDisplayed = isDisplayed(webElement);  // Retry if element reference becomes stale.
        } catch (NoSuchElementException e) {
            elementDisplayed = false;  // Return false if the element does not exist on the page.
        }
        return elementDisplayed;
    }
}
