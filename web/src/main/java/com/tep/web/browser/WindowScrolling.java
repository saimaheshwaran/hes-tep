package com.tep.web.browser;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;

/**
 * The WindowScrolling class provides methods to manipulate the scrolling behavior of a web page.
 * It allows scrolling to the top, bottom, specific elements, or by specific pixel amounts (vertically and horizontally).
 */
public class WindowScrolling {

    /**
     * The SeleniumWaits instance used to wait for elements to be displayed before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * The SeleniumDriver instance used to interact with the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * The JavascriptExecutor instance used to execute JavaScript for scrolling actions.
     */
    private final JavascriptExecutor js;

    /**
     * Constructor that initializes the WindowScrolling with the provided SeleniumDriver.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public WindowScrolling(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
        this.js = (JavascriptExecutor) seleniumDriver.getBrowser();
    }

    /**
     * Scrolls to the bottom of the page.
     * This method scrolls the window to the end of the page by setting the scroll position
     * to the maximum scroll height of the document body.
     */
    public void scrollToEnd() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scrolls to the top of the page.
     * This method scrolls the window back to the top by setting the scroll position to (0, 0).
     */
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scrolls to the specific WebElement on the page.
     * This method waits for the element to be displayed, then scrolls the window to bring the element into view.
     *
     * @param objName The name of the object (web element) to be located and scrolled to.
     */
    public void scrollToElement(String objName) {
        scrollToElement(seleniumDriver.getElement(objName));
    }

    /**
     * Scrolls to the specific WebElement on the page.
     * This method waits for the element to be displayed, then scrolls the window to bring the element into view.
     *
     * @param element The WebElement to be located and scrolled to.
     */
    public void scrollToElement(WebElement element) {
        seleniumWaits.untilElementDisplayed(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Scrolls the window vertically by a specified number of pixels.
     * Positive values scroll down, while negative values scroll up.
     *
     * @param pixelsToScroll The number of pixels to scroll vertically. Positive values scroll down, negative values scroll up.
     */
    public void scrollVerticalPixels(int pixelsToScroll) {
        js.executeScript("window.scrollBy(0," + pixelsToScroll + ")");
    }

    /**
     * Scrolls the window horizontally by a specified number of pixels.
     * Positive values scroll to the right, while negative values scroll to the left.
     *
     * @param pixelsToScroll The number of pixels to scroll horizontally. Positive values scroll right, negative values scroll left.
     */
    public void scrollHorizontalPixels(int pixelsToScroll) {
        js.executeScript("window.scrollBy(" + pixelsToScroll + ", 0)");
    }
}
