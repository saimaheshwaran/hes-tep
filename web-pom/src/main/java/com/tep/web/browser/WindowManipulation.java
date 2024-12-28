package com.tep.web.browser;

import org.openqa.selenium.*;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.interactions.Actions;

public class WindowManipulation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public WindowManipulation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

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

    public void zoomInOutTillElementDisplay(String objName, String inOut) {
        zoomInOutTillElementDisplay(seleniumDriver.getElement(objName), inOut);
    }

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

    public void resizeBrowser(int width, int height) {
        seleniumDriver.getBrowser().manage().window().setSize(new Dimension(width, height));
    }

    public void maximizeBrowser() {
        seleniumDriver.getBrowser().manage().window().maximize();
    }

    public void zoomInAndOut(String value) {
        JavascriptExecutor executor = (JavascriptExecutor) seleniumDriver;
        executor.executeScript("document.body.style.zoom = '" + value + "%'");
    }
}
