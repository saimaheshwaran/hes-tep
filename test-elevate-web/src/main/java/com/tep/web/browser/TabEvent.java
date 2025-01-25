package com.tep.web.browser;

import lombok.AllArgsConstructor;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;
import java.util.Objects;

/**
 * The TabEvent class provides methods to interact with browser tabs,
 * such as creating a new tab, switching between tabs, and switching to the first tab.
 * It relies on Selenium WebDriver to manipulate browser window handles.
 */
@AllArgsConstructor
public class TabEvent {

    /**
     * The SeleniumDriver instance used to interact with the browser.
     */
    private SeleniumDriver seleniumDriver;

    /**
     * Creates a new browser tab and navigates to the specified URL.
     * This method uses JavaScript to open a new blank tab and then navigates to the given URL.
     *
     * @param url The URL to navigate to in the newly opened tab.
     */
    public void createNew(String url) {
        // JavaScript to open a new blank tab
        String script = "window.open('about:blank','_blank');";
        ((JavascriptExecutor) seleniumDriver.getBrowser()).executeScript(script);

        // Switch to the new tab
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            seleniumDriver.getBrowser().switchTo().window(winHandle);
        }

        // Navigate to the provided URL in the new tab
        seleniumDriver.getBrowser().get(url);
    }

    /**
     * Switches to a tab based on the tab's title.
     * The method iterates through all open windows/tabs and switches to the one that matches the provided title.
     *
     * @param tabTitle The title of the tab to switch to.
     */
    public void switchTo(String tabTitle) {
        // Iterate over all window handles and switch to the tab with the matching title
        for (String winHandle : seleniumDriver.getBrowser().getWindowHandles()) {
            seleniumDriver.getBrowser().switchTo().window(winHandle);
            if (Objects.requireNonNull(seleniumDriver.getBrowser().getTitle()).equalsIgnoreCase(tabTitle)) {
                break;
            }
        }
    }

    /**
     * Switches to the first tab in the browser.
     * This method switches to the first window/tab in the browser's window handle list.
     */
    public void switchToFirstTab() {
        Set<String> handles = seleniumDriver.getBrowser().getWindowHandles();
        for (String handle : handles) {
            seleniumDriver.getBrowser().switchTo().window(handle);
            break;
        }
    }

}
