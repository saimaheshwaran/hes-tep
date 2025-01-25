package com.tep.web.browser;

import org.openqa.selenium.*;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * The WindowManipulation class provides methods to manipulate the browser window,
 * such as zooming in and out, resizing the browser, maximizing the browser window,
 * and interacting with specific elements to adjust the zoom level until they become visible.
 */
public class WindowManipulation {

    /**
     * The SeleniumWaits instance used to wait for elements to be visible before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * The SeleniumDriver instance used to interact with the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor that initializes the WindowManipulation with the provided SeleniumDriver.
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public WindowManipulation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Retrieves the appropriate key for performing key combinations based on the operating system.
     * This method returns either the "Control" key for Windows/Linux or the "Command" key for macOS.
     *
     * @return The appropriate modifier key for the current operating system.
     */
    public Keys getKey() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win") || os.contains("nux") || os.contains("nix")) {
            return Keys.CONTROL;
        } else if (os.contains("mac")) {
            return Keys.COMMAND;
        } else {
            return null;
        }
    }

    /**
     * Zooms in, zooms out, or resets the zoom level of the browser window.
     * The zoom action is triggered by sending key combinations to the "html" element.
     *
     * @param inOut The zoom action. Can be "ADD" to zoom in, "SUBTRACT" to zoom out, or "reset" to reset zoom.
     */
    public void zoomInOut(String inOut) {
        WebElement element = seleniumDriver.getBrowser().findElement(By.tagName("html"));
        if ("ADD".equalsIgnoreCase(inOut)) {
            element.sendKeys(Keys.chord(getKey(), Keys.ADD));
        } else if ("SUBTRACT".equalsIgnoreCase(inOut)) {
            element.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
        } else if ("reset".equalsIgnoreCase(inOut)) {
            element.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
        }
    }

    /**
     * Zooms in or out until the specified element is displayed on the screen.
     * This method repeatedly performs zoom actions until the element becomes visible.
     *
     * @param objName The name of the object (web element) to be located.
     * @param inOut The zoom action. Can be "ADD" to zoom in or "SUBTRACT" to zoom out.
     */
    public void zoomInOutTillElementDisplay(String objName, String inOut) {
        zoomInOutTillElementDisplay(seleniumDriver.getElement(objName), inOut);
    }

    /**
     * Zooms in or out until the specified WebElement is displayed on the screen.
     * This method repeatedly performs zoom actions until the element becomes visible.
     *
     * @param webElement The WebElement to be located and interacted with.
     * @param inOut The zoom action. Can be "ADD" to zoom in or "SUBTRACT" to zoom out.
     */
    public void zoomInOutTillElementDisplay(WebElement webElement, String inOut) {
        Actions action = new Actions(seleniumDriver.getBrowser());
        seleniumWaits.untilElementDisplayed(webElement);
        while (true) {
            if (webElement.isDisplayed()) {
                break;
            } else {
                action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
            }
        }
    }

    /**
     * Resizes the browser window to the specified width and height.
     *
     * @param width The desired width of the browser window in pixels.
     * @param height The desired height of the browser window in pixels.
     */
    public void resizeBrowser(int width, int height) {
        seleniumDriver.getBrowser().manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Maximizes the browser window.
     * This method ensures the browser window is in its maximized state.
     */
    public void maximizeBrowser() {
        seleniumDriver.getBrowser().manage().window().maximize();
    }

    /**
     * Sets the zoom level of the page using a percentage value.
     * This method directly adjusts the document body's zoom style via JavaScript.
     *
     * @param value The desired zoom value in percentage (e.g., "100" for 100% zoom).
     */
    public void zoomInAndOut(String value) {
        JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver;
        executor.executeScript("document.body.style.zoom = '" + value + "%'");
    }
}
