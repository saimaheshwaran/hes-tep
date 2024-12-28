package com.tep.web.browser;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

/**
 * The WindowEvent class handles various browser window and frame interactions,
 * such as switching between windows, handling alerts, and interacting with iframes.
 * It provides methods for switching between windows and tabs, handling alerts,
 * and refreshing pages.
 */
public class WindowEvent {

    /**
     * The SeleniumWaits instance used to wait for elements to be visible before interacting with them.
     */
    private final SeleniumWaits seleniumWaits;

    /**
     * The handle of the original (old) window before switching.
     */
    private String oldWindowHandle;

    /**
     * The handle of the most recently switched window or tab.
     */
    private String lastWindowHandle;

    /**
     * The SeleniumDriver instance used to interact with the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor that initializes the WindowEvent with the provided SeleniumDriver.
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public WindowEvent(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Handles an alert by either accepting or dismissing it.
     * @param decision The decision to make on the alert ("accept" or "dismiss").
     */
    public void handleAlert(String decision) {
        if ("accept".equalsIgnoreCase(decision)) {
            seleniumDriver.getBrowser().switchTo().alert().accept();
        } else {
            seleniumDriver.getBrowser().switchTo().alert().dismiss();
        }
    }

    /**
     * Retrieves the text of the current alert.
     * @return The text of the alert.
     */
    public String getAlertText() {
        String alertText = seleniumDriver.getBrowser().switchTo().alert().getText();
        return alertText;
    }

    /**
     * Switches to the most recently opened window or tab.
     * This method stores the current window handle and switches to the new one.
     */
    public void switchToNewWindow() {
        oldWindowHandle = seleniumDriver.getBrowser().getWindowHandle();
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        seleniumDriver.getBrowser().switchTo().window(lastWindowHandle);
    }

    /**
     * Switches back to the original window or tab.
     * This method returns to the window handle that was stored before switching.
     */
    public void switchToOldWindow() {
        seleniumDriver.getBrowser().switchTo().window(oldWindowHandle);
    }

    /**
     * Switches to the most recently opened tab.
     * This method stores the current window handle and switches to the new one.
     */
    public void switchToNewTab() {
        oldWindowHandle = seleniumDriver.getBrowser().getWindowHandle();
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        seleniumDriver.getBrowser().switchTo().window(lastWindowHandle);
    }

    /**
     * Switches back to the original tab.
     * This method returns to the tab handle that was stored before switching.
     */
    public void switchToOldTab() {
        seleniumDriver.getBrowser().switchTo().window(oldWindowHandle);
    }

    /**
     * Switches to a window based on the specified window title.
     * This method searches for a window with the given title and switches to it.
     *
     * @param windowTitle The title of the window to switch to.
     * @throws Exception If no window with the given title is found.
     */
    public void switchToWindowByTitle(String windowTitle) throws Exception {
        oldWindowHandle = seleniumDriver.getBrowser().getWindowHandle();
        boolean windowFound = false;
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            String title = seleniumDriver.getBrowser().switchTo().window(winHandle).getTitle();
            if (title.equals(windowTitle)) {
                windowFound = true;
                break;
            }
        }
        if (!windowFound) {
            throw new Exception("Window with title " + windowTitle + " not found");
        }
    }

    /**
     * Closes the most recently opened window and returns to the main window.
     */
    public void closeNewWindow() {
        seleniumDriver.closeBrowser();
    }

    /**
     * Switches to a frame identified by a WebElement.
     * The method waits for the element to be displayed before switching to it.
     *
     * @param webElement The WebElement representing the frame to switch to.
     */
    public void switchFrame(WebElement webElement) {
        seleniumWaits.untilElementDisplayed(webElement);
        seleniumDriver.getBrowser().switchTo().frame(webElement);
    }

    /**
     * Switches to a frame identified by a page object name.
     * The method retrieves the WebElement for the frame and switches to it.
     *
     * @param objName The name of the page object representing the frame.
     */
    public void switchFrame(String objName) {
        switchFrame(seleniumDriver.getElement(objName));
    }

    /**
     * Switches to the default content of the page, leaving any frames.
     */
    public void switchToDefaultContent() {
        seleniumDriver.getBrowser().switchTo().defaultContent();
    }

    /**
     * Refreshes the current page in the browser.
     * This method simulates pressing the refresh button or F5 in the browser.
     */
    public void refreshPage() {
        seleniumDriver.getBrowser().navigate().refresh();
    }

}
