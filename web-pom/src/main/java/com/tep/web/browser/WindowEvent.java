package com.tep.web.browser;

import com.tep.web.base.SeleniumWaits;
import org.openqa.selenium.WebElement;
import com.tep.web.base.SeleniumDriver;

public class WindowEvent {

    private final SeleniumWaits seleniumWaits;
    private String oldWindowHandle;
    private String lastWindowHandle;
    private final SeleniumDriver seleniumDriver;

    public WindowEvent(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public void handleAlert(String decision) {
        if ("accept".equalsIgnoreCase(decision)) {
            seleniumDriver.getBrowser().switchTo().alert().accept();
        } else {
            seleniumDriver.getBrowser().switchTo().alert().dismiss();
        }
    }

    public String getAlertText() {
        String alertText = seleniumDriver.getBrowser().switchTo().alert().getText();
        return alertText;
    }

    public void switchToNewWindow() {
        oldWindowHandle = seleniumDriver.getBrowser().getWindowHandle();
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        seleniumDriver.getBrowser().switchTo().window(lastWindowHandle);
    }

    public void switchToOldWindow() {
        seleniumDriver.getBrowser().switchTo().window(oldWindowHandle);
    }

    public void switchToNewTab() {
        oldWindowHandle = seleniumDriver.getBrowser().getWindowHandle();
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            lastWindowHandle = winHandle;
        }
        seleniumDriver.getBrowser().switchTo().window(lastWindowHandle);
    }

    public void switchToOldTab() {
        seleniumDriver.getBrowser().switchTo().window(oldWindowHandle);
    }

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

    public void closeNewWindow() {
        seleniumDriver.closeBrowser();
    }

    public void switchFrame(WebElement webElement) {
            seleniumWaits.untilElementDisplayed(webElement);
            seleniumDriver.getBrowser().switchTo().frame(webElement);
        }

    public void switchFrame(String objName) { switchFrame(seleniumDriver.getElement(objName)); }

    public void switchToDefaultContent() {
        seleniumDriver.getBrowser().switchTo().defaultContent();
    }

    public void refreshPage() {
        seleniumDriver.getBrowser().navigate().refresh();
    }

}
