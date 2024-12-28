package com.tep.web.base;

import org.openqa.selenium.*;
import lombok.AllArgsConstructor;
import com.tep.web.config.Constants;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@AllArgsConstructor
public class SeleniumWaits {

    private final SeleniumDriver seleniumDriver;

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(seleniumDriver.getBrowser(), Duration.ofSeconds(seconds));
    }

    private FluentWait<WebDriver> getFluentWait(int seconds) {
        return new FluentWait<>(seleniumDriver.getBrowser())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementNotInteractableException.class);
    }

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

        FluentWait<WebDriver> wait = getFluentWait(Constants.DEFAULT_WAIT_TIME_SEC);
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean untilTitleContains(String pageTitle) { return untilTitleContains(pageTitle, Constants.DEFAULT_WAIT_TIME_SEC); }

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

    public void untilElementDisplayed(WebElement webElement) { untilElementDisplayed(webElement, Constants.DEFAULT_WAIT_TIME_SEC); }

    public void untilElementDisplayed(WebElement webElement, int seconds) {
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.visibilityOf(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    public void untilElementNotDisplayed(WebElement webElement) { untilElementNotDisplayed(webElement, Constants.DEFAULT_WAIT_TIME_SEC); }

    public void untilElementNotDisplayed(WebElement webElement, int seconds) {
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.invisibilityOf(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    public void untilElementClickable(WebElement webElement) { untilElementClickable(webElement, Constants.DEFAULT_WAIT_TIME_SEC); }

    public void untilElementClickable(WebElement webElement, int seconds) {
        untilElementDisplayed(webElement, seconds);
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.elementToBeClickable(webElement));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    public void untilElementNotClickable(WebElement webElement) { untilElementNotClickable(webElement, Constants.DEFAULT_WAIT_TIME_SEC); }

    public void untilElementNotClickable(WebElement webElement, int seconds) {
        untilElementDisplayed(webElement, seconds);
        untilJSandJQueryToLoad();
        try {
            getFluentWait(seconds).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
            scrollTo(webElement);
        } catch (Exception ignored) {}
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
