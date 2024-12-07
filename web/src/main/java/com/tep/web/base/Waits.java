package com.tep.web.base;

import com.tep.web.config.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Waits {

    private WebDriver driver;
    private Element element;

    public Waits(WebDriver driver) {
        this.driver = driver;
        this.element = new Element(driver);
    }

    public void sleep(int seconds)
    {
        try {
            int duration = seconds;
            Thread.sleep(duration * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    public List<WebElement> waitForPresenceOfElementsLocated(Map.Entry<String, String> locatorPair, int duration)
    {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        return getWait(duration).until(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getBy(locatorPair)));
    }

    public void waitForElementToDisplay(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        getWait(duration).until(ExpectedConditions.visibilityOfElementLocated(element.getBy(locatorPair)));
    }

    public void waitForElementToClick(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        getWait(duration).until(ExpectedConditions.elementToBeClickable(element.getBy(locatorPair)));
    }

    public void waitForElementNotToDisplay(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        getWait(duration).until(ExpectedConditions.invisibilityOfElementLocated(element.getBy(locatorPair)));
    }

    public void waitForElementNotToClick(Map.Entry<String, String> locatorPair, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        getWait(duration).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element.getBy(locatorPair))));
    }

    public void waitTillElementIsVisible(Map.Entry<String, String> locatorPair, WebElement element, int duration) {
        waitForAjaxCall(locatorPair);
        waitForJSandJQueryToLoad();
        getWait(duration).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForTitleContains(String pageTitle, int duration)
    {
        boolean isPageTitleFound;
        waitForJSandJQueryToLoad();
        WebDriverWait wait = (new WebDriverWait(driver, Duration.ofSeconds(duration)));
        try{
            isPageTitleFound = wait.until(ExpectedConditions.titleContains(pageTitle));
        }catch(TimeoutException e){
            isPageTitleFound = false;
        }
        return isPageTitleFound;
    }

    public void waitForAjaxCall(Map.Entry<String, String> locatorPair) {
        try {
            Thread.sleep(100);
            By by = element.getBy(locatorPair);
            if (driver.findElements(by).size() > 0) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME_SEC), Duration.ofMillis(30));
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
            }
        } catch (Exception ignored) {}
    }

    public boolean  waitForJSandJQueryToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME_SEC));
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception ignored) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").
                            toString().equals("complete");
                } catch (Exception ignored) {
                    return true;
                }
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

}
