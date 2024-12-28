package com.tep.web.browser;

import lombok.AllArgsConstructor;
import com.tep.web.base.SeleniumDriver;

@AllArgsConstructor
public class BrowserEvent {

    private SeleniumDriver seleniumDriver;

    public void goToUrl(String url) {
        seleniumDriver.getBrowser().navigate().to(url);
    }

    public void goToUrlByPOValue(String objName) {
       goToUrl(seleniumDriver.getPageObjects().get(objName, "value"));
    }

    public void back() {
        seleniumDriver.getBrowser().navigate().back();
    }

    public void forward() {
        seleniumDriver.getBrowser().navigate().forward();
    }

    public void refresh() {
        seleniumDriver.getBrowser().navigate().refresh();
    }

}
