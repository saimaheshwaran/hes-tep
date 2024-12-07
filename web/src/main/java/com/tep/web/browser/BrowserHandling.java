package com.tep.web.browser;

import com.tep.web.config.PageObjects;
import org.openqa.selenium.WebDriver;

public class BrowserHandling {

    private WebDriver driver;
    private PageObjects objects;

    public BrowserHandling(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
    }

    public BrowserHandling(WebDriver driver) {
        this.driver = driver;
    }

    public void toUrl(String url) {
        driver.navigate().to(url);
    }

    public void goTo(String objName) {
        driver.navigate().to(objects.get(objName, "value"));
    }

    public void back() {
        driver.navigate().back();
    }

    public void forward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

}
