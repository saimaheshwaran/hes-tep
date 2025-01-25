package com.tep.web.base;

import org.openqa.selenium.*;
import lombok.AllArgsConstructor;
import com.tep.web.config.WebConstants;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * SeleniumWaits is a utility class that provides custom waiting mechanisms for WebDriver elements,
 * including waiting for elements to be displayed, clickable, and the page to load with JavaScript.
 */
@AllArgsConstructor
public class SeleniumWaits {

    /**
     * The SeleniumDriver instance used to interact with the WebDriver and the browser.
     */
    private final SeleniumDriver seleniumDriver;

    /**
     * Returns a WebDriverWait instance with the specified timeout in seconds.
     * @param seconds The timeout duration in seconds.
     * @return A WebDriverWait instance with the specified timeout.
     */
    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(seleniumDriver.getBrowser(), Duration.ofSeconds(seconds));
    }

    /**
     * Returns a FluentWait instance with the specified timeout in seconds.
     * FluentWait allows for polling every 500 milliseconds and ignoring ElementNotInteractableException.
     * @param seconds The timeout duration in seconds.
     * @return A FluentWait instance with the specified timeout.
     */
    private FluentWait<WebDriver> getFluentWait(int seconds) {
        return new FluentWait<>(seleniumDriver.getBrowser())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementNotInteractableException.class);
    }

    /**
     * Waits until both JavaScript and jQuery are fully loaded and active.
     * This method checks if the jQuery library is active and if the document's readyState is "complete".
     * @return true if both JavaScript and jQuery are fully loaded; false otherwise.
     */
    public boolean untilJSandJQueryToLoad() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                boolean isJQueryActive = ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                return isJQueryActive;
            } catch (Exception ignored) {
                return true;
            }
        };

        ExpectedCondition<Boolean> jsLoad = driver -> {
            try {
                boolean isDocumentReady = ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
                return isDocumentReady;
            } catch (Exception ignored) {
                return true;
            }
        };

        FluentWait<WebDriver> wait = getFluentWait(WebConstants.DEFAULT_WAIT_TIME_SEC);
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    /**
     * Waits until the page title contains the specified text.
     * The default timeout duration is specified in the Constants.DEFAULT_WAIT_TIME_SEC.
     * @param pageTitle The expected text to be contained in the page title.
     * @return true if the page title contains the specified text, false otherwise.
     */
    public boolean untilTitleContains(String pageTitle) {
        return untilTitleContains(pageTitle, WebConstants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Waits until the page title contains the specified text.
     * @param pageTitle The expected text to be contained in the page title.
     * @param duration The timeout duration in seconds.
     * @return true if the page title contains the specified text, false otherwise.
     */
    public boolean untilTitleContains(String pageTitle, int duration) {
        boolean isPageTitleFound;
        untilJSandJQueryToLoad();
        try {
            isPageTitleFound = getFluentWait(duration).until(ExpectedConditions.titleContains(pageTitle));
        } catch (TimeoutException e) {
            isPageTitleFound = false;
        }
        return isPageTitleFound;
    }

    /**
     * Waits until the specified WebElement is displayed.
     * The default timeout duration is specified in the Constants.DEFAULT_WAIT_TIME_SEC.
     * @param webElement The WebElement to wait for.
     */
    public void untilElementDisplayed(WebElement webElement) {
        untilElementDisplayed(webElement, WebConstants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Waits until the specified WebElement is displayed.
     * @param webElement The WebElement to wait for.
     * @param seconds The timeout duration in seconds.
     */
    public void untilElementDisplayed(WebElement webElement, int seconds) {
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.visibilityOf(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    /**
     * Waits until the specified WebElement is not displayed.
     * The default timeout duration is specified in the Constants.DEFAULT_WAIT_TIME_SEC.
     * @param webElement The WebElement to wait for.
     */
    public void untilElementNotDisplayed(WebElement webElement) {
        untilElementNotDisplayed(webElement, WebConstants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Waits until the specified WebElement is not displayed.
     * @param webElement The WebElement to wait for.
     * @param seconds The timeout duration in seconds.
     */
    public void untilElementNotDisplayed(WebElement webElement, int seconds) {
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.invisibilityOf(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    /**
     * Waits until the specified WebElement is clickable.
     * The default timeout duration is specified in the Constants.DEFAULT_WAIT_TIME_SEC.
     * @param webElement The WebElement to wait for.
     */
    public void untilElementClickable(WebElement webElement) {
        untilElementClickable(webElement, WebConstants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Waits until the specified WebElement is clickable.
     * @param webElement The WebElement to wait for.
     * @param seconds The timeout duration in seconds.
     */
    public void untilElementClickable(WebElement webElement, int seconds) {
        untilElementDisplayed(webElement, seconds);
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.elementToBeClickable(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    /**
     * Waits until the specified WebElement is not clickable.
     * The default timeout duration is specified in the Constants.DEFAULT_WAIT_TIME_SEC.
     * @param webElement The WebElement to wait for.
     */
    public void untilElementNotClickable(WebElement webElement) {
        untilElementNotClickable(webElement, WebConstants.DEFAULT_WAIT_TIME_SEC);
    }

    /**
     * Waits until the specified WebElement is not clickable.
     * @param webElement The WebElement to wait for.
     * @param seconds The timeout duration in seconds.
     */
    public void untilElementNotClickable(WebElement webElement, int seconds) {
        untilElementDisplayed(webElement, seconds);
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    /**
     * Pauses the execution for a specified number of seconds.
     * @param seconds The number of seconds to sleep.
     */
    public void sleep(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Scrolls the page to bring the specified WebElement into view.
     * The WebElement is highlighted with a green-yellow background to indicate it's been scrolled into view.
     * @param element The WebElement to scroll to.
     */
    private void scrollTo(WebElement element) {
        try {
            ((JavascriptExecutor) seleniumDriver.getBrowser()).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) seleniumDriver.getBrowser()).executeScript(
                    "arguments[0].setAttribute('style','background:GreenYellow; border: 0px solid blue;')", element);
            Thread.sleep(100);
            ((JavascriptExecutor) seleniumDriver.getBrowser()).executeScript(
                    "arguments[0].setAttribute('style','background:; border: 0px solid blue;')", element);
        } catch (Exception ignored) {
        }
    }
}
