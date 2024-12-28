package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Objects;

public class PageValidation {

    private final SeleniumWaits seleniumWaits;
    private final SeleniumDriver seleniumDriver;

    public PageValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    public String getPageTitle() {
        seleniumWaits.untilJSandJQueryToLoad();
        return seleniumDriver.getBrowser().getTitle();
    }

    public void checkPageTitle(String title, boolean testCase) {
        seleniumWaits.untilJSandJQueryToLoad();
        String pageTitle = getPageTitle();
        Boolean titleMatched = title.equals(pageTitle);
        if (!titleMatched && testCase) {
            Assertion.equalsTrue(false, "Page Title Not Matched: Expected Page Title is '" + title + "' but Displayed Page Title is '" + pageTitle + "'.");
        } else if (titleMatched && !testCase) {
            Assertion.equalsFalse(true, "Page Title Matched: Expected Page Title is '" + title + "' and Displayed Page Title is '" + pageTitle + "'.");
        }
    }

    public void checkPartialPageTitle(String partialTitle, boolean testCase) {
        Boolean titleMatched = seleniumWaits.untilTitleContains(partialTitle);
        String pageTitle = getPageTitle();
        if (!titleMatched && testCase) {
            Assertion.equalsTrue(false, "Expected: Actual Page Title \"" + pageTitle + "\" should contain partial page title \"" + partialTitle + "\". But Partial Page Title Not Present.");
        } else if (titleMatched && !testCase) {
            Assertion.equalsFalse(true, "Expected: Actual Page Title \"" + pageTitle + "\" should not contain partial page title \"" + partialTitle + "\". But Partial Page Title is Present.");
        }
    }

    public void isPageLoaded(int timeOutInSeconds) {
        boolean isPageLoaded = false;
        JavascriptExecutor js = (JavascriptExecutor) seleniumDriver.getBrowser();
        String jsCommand = "return document.readyState";
        if (Objects.requireNonNull(js.executeScript(jsCommand)).toString().equals("complete")) {
            return;
        }
        for (int i = 0; i < timeOutInSeconds; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (js.executeScript(jsCommand).toString().equals("complete")) {
                isPageLoaded = true;
                break;
            }
        }
        Assertion.equalsTrue(isPageLoaded, "Page '" + seleniumDriver.getBrowser().getTitle() + "' is not Loaded.");
    }

    public void javaScriptWaitForPageToLoad(int timeOutInSeconds, boolean shouldBeLoaded) {
        boolean isPageLoaded = false;
        JavascriptExecutor js = (JavascriptExecutor) seleniumDriver;
        String jsCommand = "return document.readyState";
        if (Objects.requireNonNull(js.executeScript(jsCommand)).toString().equals("complete")) {
            return;
        }
        for (int i = 0; i < timeOutInSeconds; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (js.executeScript(jsCommand).toString().equals("complete")) {
                isPageLoaded = true;
                break;
            }
        }
        Assertion.equalsTrue(isPageLoaded, "Page is Not Loaded.");
    }
}
