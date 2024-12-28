package com.tep.web.browser;

import lombok.AllArgsConstructor;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;
import java.util.Objects;

@AllArgsConstructor
public class TabEvent {

    private SeleniumDriver seleniumDriver;

    public void createNew(String url) {
        String script = "window.open('about:blank','_blank');";
        ((JavascriptExecutor) seleniumDriver.getBrowser()).executeScript(script);
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            seleniumDriver.getBrowser().switchTo().window(winHandle);
        }
        seleniumDriver.getBrowser().get(url);
    }

    public void switchTo(String tabTitle) {
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            seleniumDriver.getBrowser().switchTo().window(winHandle);
            if (Objects.requireNonNull(seleniumDriver.getBrowser().getTitle()).equalsIgnoreCase(tabTitle)) {
                break;
            }
        }
    }

    public void switchToFirstTab() {
        Set<String> handles = seleniumDriver.getBrowser().getWindowHandles();
        for (String handle : handles) {
            seleniumDriver.getBrowser().switchTo().window(handle);
            break;
        }
    }

}
