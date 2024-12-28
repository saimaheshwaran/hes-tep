package com.tep.web.validation;

import com.tep.web.base.SeleniumWaits;
import com.tep.web.base.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Objects;

/**
 * The PageValidation class provides methods for validating page titles and ensuring a page is fully loaded.
 * It contains methods to check the title of the page and wait for the page to load.
 */
public class PageValidation {

    /** The instance of SeleniumWaits used for waiting on elements and JavaScript/jQuery to load. */
    private final SeleniumWaits seleniumWaits;

    /** The instance of SeleniumDriver used to interact with the browser. */
    private final SeleniumDriver seleniumDriver;

    /**
     * Constructor to initialize the PageValidation class with a SeleniumDriver instance.
     *
     * @param seleniumDriver The SeleniumDriver instance used to interact with the browser.
     */
    public PageValidation(SeleniumDriver seleniumDriver) {
        this.seleniumDriver = seleniumDriver;
        this.seleniumWaits = new SeleniumWaits(seleniumDriver);
    }

    /**
     * Retrieves the title of the current page after ensuring that the JavaScript and jQuery are fully loaded.
     *
     * @return The title of the current page.
     */
    public String getPageTitle() {
        seleniumWaits.untilJSandJQueryToLoad();
        return seleniumDriver.getBrowser().getTitle();
    }

    /**
     * Verifies whether the page title matches the expected title.
     *
     * @param title The expected page title.
     * @param testCase A boolean indicating whether the test case expects the title to match (true) or not (false).
     */
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

    /**
     * Verifies whether the page title contains the specified partial title.
     *
     * @param partialTitle The partial title expected to be contained in the page title.
     * @param testCase A boolean indicating whether the test case expects the title to contain the partial title (true) or not (false).
     */
    public void checkPartialPageTitle(String partialTitle, boolean testCase) {
        Boolean titleMatched = seleniumWaits.untilTitleContains(partialTitle);
        String pageTitle = getPageTitle();
        if (!titleMatched && testCase) {
            Assertion.equalsTrue(false, "Expected: Actual Page Title \"" + pageTitle + "\" should contain partial page title \"" + partialTitle + "\". But Partial Page Title Not Present.");
        } else if (titleMatched && !testCase) {
            Assertion.equalsFalse(true, "Expected: Actual Page Title \"" + pageTitle + "\" should not contain partial page title \"" + partialTitle + "\". But Partial Page Title is Present.");
        }
    }

    /**
     * Waits for the page to be fully loaded by checking the document's readyState.
     *
     * @param timeOutInSeconds The timeout in seconds to wait for the page to load.
     */
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

    /**
     * Uses JavaScript to wait for the page to be fully loaded by checking the document's readyState.
     *
     * @param timeOutInSeconds The timeout in seconds to wait for the page to load.
     * @param shouldBeLoaded A boolean indicating whether the page should be loaded (true) or not (false).
     */
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
