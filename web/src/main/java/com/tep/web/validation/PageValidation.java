package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import com.tep.web.base.Waits;
import com.tep.web.config.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PageValidation class to handle validation of web pages.
 */
public class PageValidation {

    private Waits waits;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(PageValidation.class);

    /**
     * Constructor to initialize the PageValidation with a WebDriver instance.
     *
     * @param driver the WebDriver instance to interact with.
     */
    public PageValidation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return the title of the current page.
     */
    public String getPageTitle() {
        waits.waitForJSandJQueryToLoad();
        return driver.getTitle();
    }

    /**
     * Checks if the title of the current page matches the expected title.
     *
     * @param title    the expected title of the page.
     * @param testCase true if the title should match, false otherwise.
     */
    public void checkPageTitle(String title, boolean testCase) {
        waits.waitForJSandJQueryToLoad();
        String pageTitle = getPageTitle();
        Boolean titleMatched = title.equals(pageTitle);

        if (!titleMatched && testCase) {
            logger.error("Page Title Not Matched: Expected '{}' but found '{}'", title, pageTitle);
            Assertion.equalsTrue(false, "Page Title Not Matched: Expected Page Title is '" + title + "' but Displayed Page Title is '" + pageTitle + "'.");
        } else if (titleMatched && !testCase) {
            Assertion.equalsFalse(true, "Page Title Matched: Expected Page Title is '" + title + "' and Displayed Page Title is '" + pageTitle + "'.");
        }
    }

    /**
     * Checks if the partial title is contained in the title of the current page.
     *
     * @param partialTitle the partial title to check.
     * @param testCase     true if the partial title should be contained, false otherwise.
     */
    public void checkPartialPageTitle(String partialTitle, boolean testCase) {
        Boolean titleMatched = waits.waitForTitleContains(partialTitle, Constants.DEFAULT_WAIT_TIME_SEC);
        String pageTitle = getPageTitle();

        if (!titleMatched && testCase) {
            logger.error("Partial Page Title Not Present: Expected partial title '" + partialTitle + "' in actual title '" + pageTitle + "'");
            Assertion.equalsTrue(false, "Expected: Actual Page Title \"" + pageTitle + "\" should contain partial page title \"" + partialTitle + "\". But Partial Page Title Not Present.");
        } else if (titleMatched && !testCase) {
            logger.error("Partial Page Title is Present when it should not be: Expected partial title '" + partialTitle + "' not to be in actual title '" + pageTitle + "'");
            Assertion.equalsFalse(true, "Expected: Actual Page Title \"" + pageTitle + "\" should not contain partial page title \"" + partialTitle + "\". But Partial Page Title is Present.");
        }
    }

    /**
     * Checks if the page is fully loaded within the specified timeout.
     *
     * @param timeOutInSeconds the timeout in seconds.
     */
    public void isPageLoaded(int timeOutInSeconds) {
        boolean isPageLoaded = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = "return document.readyState";

        if (js.executeScript(jsCommand).toString().equals("complete")) {
            logger.info("Page loaded immediately.");
            return;
        }

        for (int i = 0; i < timeOutInSeconds; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warn("Thread interrupted during sleep.", e);
                e.printStackTrace();
            }
            if (js.executeScript(jsCommand).toString().equals("complete")) {
                isPageLoaded = true;
                break;
            }
        }

        Assertion.equalsTrue(isPageLoaded, "Page '" + driver.getTitle() + "' is not Loaded.");
    }

    /**
     * Waits for the page to load using JavaScript within the specified timeout.
     *
     * @param timeOutInSeconds the timeout in seconds.
     * @param shouldBeLoaded   true if the page should be loaded, false otherwise.
     */
    public void javaScriptWaitForPageToLoad(int timeOutInSeconds, boolean shouldBeLoaded) {
        boolean isPageLoaded = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = "return document.readyState";

        if (js.executeScript(jsCommand).toString().equals("complete")) {
            logger.info("Page loaded immediately.");
            return;
        }

        for (int i = 0; i < timeOutInSeconds; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info("Thread interrupted during sleep.", e);
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
