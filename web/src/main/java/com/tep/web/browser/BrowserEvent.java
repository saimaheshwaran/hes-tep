package com.tep.web.browser;

import lombok.AllArgsConstructor;
import com.tep.web.base.SeleniumDriver;

/**
 * The BrowserEvent class encapsulates browser navigation actions such as going to a URL,
 * navigating back, forward, and refreshing the page.
 * It interacts with a SeleniumDriver instance to perform these actions on the browser.
 */
@AllArgsConstructor
public class BrowserEvent {

    /**
     * The SeleniumDriver instance used to interact with the browser.
     */
    private SeleniumDriver seleniumDriver;

    /**
     * Navigates to the specified URL in the browser.
     * @param url The URL to navigate to.
     */
    public void goToUrl(String url) {
        seleniumDriver.getBrowser().navigate().to(url);
    }

    /**
     * Navigates to the URL specified by a page object value.
     * This method retrieves the URL value from the page object map using the specified object name.
     * @param objName The name of the page object whose value holds the URL to navigate to.
     */
    public void goToUrlByPOValue(String objName) {
        goToUrl(seleniumDriver.getPageObjects().get(objName, "value"));
    }

    /**
     * Navigates back in the browser history.
     * Equivalent to clicking the browser's back button.
     */
    public void back() {
        seleniumDriver.getBrowser().navigate().back();
    }

    /**
     * Navigates forward in the browser history.
     * Equivalent to clicking the browser's forward button.
     */
    public void forward() {
        seleniumDriver.getBrowser().navigate().forward();
    }

    /**
     * Refreshes the current page in the browser.
     * Equivalent to clicking the browser's refresh button or pressing F5.
     */
    public void refresh() {
        seleniumDriver.getBrowser().navigate().refresh();
    }

}
