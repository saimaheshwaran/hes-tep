package com.tep.web.base;

import com.tep.web.config.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Waits class to handle various wait conditions for WebDriver.
 */
public class Waits {

    private WebDriver driver;
    private Element element;
    private static final Logger logger = LoggerFactory.getLogger(Waits.class);

    /**
     * Constructor to initialize the Waits with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public Waits(WebDriver driver) {
        this.driver = driver;
        this.element = new Element(driver);
    }

    /**
     * Pauses the execution for a specified number of seconds.
     *
     * @param seconds the number of seconds to pause.
     */
    public void sleep(int seconds) {
        try {
            int duration = seconds;
            Thread.sleep(duration * 1000);
        } catch (InterruptedException ignored) {
            logger.error("Sleep interrupted.", ignored);
        }
    }

    /**
     * Waits for the presence of elements located by the specified locator pair.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param duration    the maximum time to wait in seconds.
     * @return the list of located WebElements.
     */
    public List<WebElement> waitForPresenceOfElementsLocated(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        return getWait(duration).until(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getBy(locatorPair)));
    }

    /**
     * Waits for an element to be displayed.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param duration    the maximum time to wait in seconds.
     */
    public void waitForElementToDisplay(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        try {
            getWait(duration).until(ExpectedConditions.visibilityOfElementLocated(element.getBy(locatorPair)));
            logger.info("Element is now visible. Locator", locatorPair);
        } catch (TimeoutException e) {
            logger.error("Timed out after seconds waiting for element to become visible.", duration, locatorPair, e);
            // Depending on your error handling strategy, you might want to rethrow the exception, return, or take other actions.
        }
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param duration    the maximum time to wait in seconds.
     */
    public void waitForElementToClick(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        try {
            getWait(duration).until(ExpectedConditions.elementToBeClickable(element.getBy(locatorPair)));
            logger.info("Element is now clickable.", locatorPair);
        } catch (TimeoutException e) {
            logger.error("Timed out after seconds waiting for element to become clickable. Locator: {}", duration, locatorPair, e);
        }
    }

    /**
     * Waits for an element to not be displayed.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param duration    the maximum time to wait in seconds.
     */
    public void waitForElementNotToDisplay(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        try {
            getWait(duration).until(ExpectedConditions.invisibilityOfElementLocated(element.getBy(locatorPair)));
            logger.info("Element is now visible. Locator: {}", locatorPair);
        } catch (TimeoutException e) {
            logger.error("Timed out after seconds waiting for element to become invisible.", duration, locatorPair, e);
        }
    }

    /**
     * Waits for an element to not be clickable.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param duration    the maximum time to wait in seconds.
     */
    public void waitForElementNotToClick(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        try {
            getWait(duration).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element.getBy(locatorPair))));
            logger.info("Element is now non-clickable. Locator: {}", locatorPair);
        } catch (TimeoutException e) {
            logger.error("Timed out afterseconds waiting for element to become non-clickable.", duration, locatorPair, e);
        }
    }

    /**
     * Waits until the specified element is visible.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     * @param element     the WebElement to wait for.
     * @param duration    the maximum time to wait in seconds.
     */
    public void waitTillElementIsVisible(Map.Entry<String, String> locatorPair, WebElement element, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        try {
            getWait(duration).until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is now visible. Locator.", locatorPair);
        } catch (TimeoutException e) {
            logger.error("Timed out afterseconds waiting for element to be visible. Locator.", duration, locatorPair, e);
        }
    }


    /**
     * Waits for the page title to contain the specified text.
     *
     * @param pageTitle the text to check in the page title.
     * @param duration  the maximum time to wait in seconds.
     * @return true if the page title contains the text, false otherwise.
     */
    public boolean waitForTitleContains(String pageTitle, int duration) {
        boolean isPageTitleFound;
        waitForJSandJQueryToLoad();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        try {
            isPageTitleFound = wait.until(ExpectedConditions.titleContains(pageTitle));
            logger.info("Page title now contains .", pageTitle);
        } catch (TimeoutException e) {
            logger.error("Timed out after seconds waiting for the page title to contain.", duration, pageTitle, e);
            isPageTitleFound = false;
        }
        return isPageTitleFound;
    }

    /**
     * Waits for an AJAX call to complete.
     *
     * @param locatorPair a Map.Entry containing the locator type and value.
     */
    public void waitForAjaxCall(Map.Entry<String, String> locatorPair) {
        try {
            Thread.sleep(100);
            By by = element.getBy(locatorPair);
            if (driver.findElements(by).size() > 0) {
                logger.info("Element found by locator = " + locatorPair.getKey() + ":" + locatorPair.getValue());
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME_SEC), Duration.ofMillis(30));
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                logger.info("Element is now present, AJAX call has completed.");
            }
        } catch (Exception ignored) {
            logger.error("An error occurred while waiting for AJAX call.", ignored.getMessage());
        }
    }

    /**
     * Waits for JavaScript and jQuery to load.
     *
     * @return true if both JavaScript and jQuery are loaded, false otherwise.
     */
    public boolean waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME_SEC));
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    boolean isJQueryActive = ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                    logger.debug("jQuery active check returned.", isJQueryActive);
                    return isJQueryActive;
                } catch (Exception ignored) {
                    logger.error("Exception occurred while checking jQuery active status.", ignored);
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    boolean isDocumentReady = ((JavascriptExecutor) driver).executeScript("return document.readyState")
                            .toString().equals("complete");
                    logger.debug("Document ready state check returned.", isDocumentReady);
                    return isDocumentReady;
                } catch (Exception ignored) {
                    logger.error("Exception occurred while checking document ready state.", ignored);
                    return true;
                }
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    /**
     * Retrieves a WebDriverWait instance with the specified timeout.
     *
     * @param seconds the timeout in seconds.
     * @return the WebDriverWait instance.
     */
    public WebDriverWait getWait(int seconds) {
        logger.debug("Creating WebDriverWait with a timeout of  seconds.", seconds);
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
