package com.tep.web.validation;

import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;

public class PageValidation {

    private Waits waits;
    private WebDriver driver;

    public PageValidation(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }

    public String getPageTitle() {
        waits.waitForJSandJQueryToLoad();
        return driver.getTitle();
    }

    public void checkPageTitle(String title, boolean testCase)  {
        waits.waitForJSandJQueryToLoad();
        String pageTitle = getPageTitle();
        Boolean titleMatched = title.equals(pageTitle);

        if ((!titleMatched) && testCase)
            Assertion.equalsTrue(false,"Page Title Not Matched: Expected Page Title is '"+title+"' but Displayed Page Title is '"+pageTitle+"'\n");
        else if (titleMatched && (!testCase))
            Assertion.equalsFalse(true,"Page Title Matched: Expected Page Title is '"+title+"' and Displayed Page Title is '"+pageTitle+"'\n");
    }

    public void checkPartialPageTitle(String partialTitle, boolean testCase)  {
        Boolean titleMatched = waits.waitForTitleContains(partialTitle, Constants.DEFAULT_WAIT_TIME_SEC);
        String pageTitle = getPageTitle();

        if ((!titleMatched) && testCase)
            Assertion.equalsTrue(false, "Expected: Actual Page Title \"" + pageTitle + "\" should contain partial page title \"" +  partialTitle+ "\". But Partial Page Title Not Present\n");
        else if (titleMatched && (!testCase))
            Assertion.equalsFalse(true, "Expected: Actual Page Title \"" + pageTitle + "\" should not contain partial page title \"" +  partialTitle+ "\". But Partial Page Title is Present\n");
    }


    public void isPageLoaded(int timeOutInSeconds) {
        boolean isPageLoaded = false;
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = "return document.readyState";
        if (js.executeScript(jsCommand).toString().equals("complete")) {
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

        Assertion.equalsTrue(isPageLoaded,"Page '"+driver.getTitle()+"' is not Loaded");
    }

    public void javaScriptWaitForPageToLoad(int timeOutInSeconds, boolean shouldBeLoaded) {
        boolean isPageLoaded = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = "return document.readyState";
        if (js.executeScript(jsCommand).toString().equals("complete")) {
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
        Assertion.equalsTrue(isPageLoaded,"Page is Not Loaded");
    }

}
