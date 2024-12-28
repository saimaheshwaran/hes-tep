package com.tep.web.browser;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * WindowHandling class to handle browser window and alert operations.
 */
public class WindowHandling {

    // Logger for logging important events and errors
    private static final Logger logger = LoggerFactory.getLogger(WindowHandling.class);
    private WebDriver driver;
    private Waits waits;
    private Element element;
    private String oldWindowHandle;
    private String lastWindowHandle;

    /**
     * Constructor to initialize the WindowHandling with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public WindowHandling(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
        logger.info("WindowHandling instance created");
    }

    /**
     * Handles browser alerts based on the decision provided.
     *
     * @param decision the decision to either accept or dismiss the alert.
     */
    public void handleAlert(String decision) {
        if ("accept".equalsIgnoreCase(decision)) {
            logger.info("Accepting the alert");
            driver.switchTo().alert().accept();
        } else {
            logger.info("Dismiss the alert");
            driver.switchTo().alert().dismiss();
        }
    }

    /**
     * Retrieves the text from the browser alert.
     *
     * @return the text from the alert.
     */
    public String getAlertText() {
        String alertText = driver.switchTo().alert().getText();
        logger.info("Retrieved alert text: {}", alertText);
        return alertText;
    }

    /**
     * Switches to a new browser window.
     */
    public void switchToNewWindow() {
        oldWindowHandle = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        driver.switchTo().window(lastWindowHandle);
        logger.info("Switched to new window: {}", lastWindowHandle);
    }

    /**
     * Switches back to the old browser window.
     */
    public void switchToOldWindow() {
        driver.switchTo().window(oldWindowHandle);
        logger.info("Switching back to the old window with handle: {}", oldWindowHandle);
    }

    /**
     * Switches to a new browser tab.
     */
    public void switchToNewTab() {
        oldWindowHandle = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        driver.switchTo().window(lastWindowHandle);
        logger.info("Switched to new tab: {}", lastWindowHandle);
    }

    /**
     * Switches back to the old browser tab.
     */
    public void switchToOldTab() {
        driver.switchTo().window(oldWindowHandle);
        logger.info("Switching back to the old tab with handle: {}", oldWindowHandle);
    }

    /**
     * Switches to a browser window by its title.
     *
     * @param windowTitle the title of the window to switch to.
     * @throws Exception if the window with the specified title is not found.
     */
    public void switchToWindowByTitle(String windowTitle) throws Exception {
        oldWindowHandle = driver.getWindowHandle();
        logger.info("Current window handle: {}", oldWindowHandle);
        boolean windowFound = false;
        for (String winHandle : driver.getWindowHandles()) {
            String title = driver.switchTo().window(winHandle).getTitle();
            if (title.equals(windowTitle)) {
                logger.info("Switched to window with title: {}", windowTitle);
                windowFound = true;
                break;
            }
        }
        if (!windowFound) {
            logger.error("Window with title '{}' not found", windowTitle);
            throw new Exception("Window with title " + windowTitle + " not found");
        }
    }

    /**
     * Closes the current browser window.
     */
    public void closeNewWindow() {
        logger.info("Closing the current window");
        driver.close();
    }

    /**
     * Switches to a frame identified by the provided locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void switchFrame(Map.Entry<String, String> locatorPair) {
        if ("index".equalsIgnoreCase(locatorPair.getKey())) {
            driver.switchTo().frame(locatorPair.getValue());
        } else {
            waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
            driver.switchTo().frame(element.get(locatorPair));
        }
        logger.info("Switched to frame successfully");
    }

    /**
     * Switches back to the default content from a frame.
     */
    public void switchToDefaultContent() {
        logger.info("Switching to the default content (main document)");
        driver.switchTo().defaultContent();
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        logger.info("Refreshing the current page");
        driver.navigate().refresh();
    }
}
