package tests.eymerchandise;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;

public class TestCases2 {

    @org.testng.annotations.Test
    public void testPo1() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Pen");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductPen());
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);
        driver.closeBrowser();
    }

    @org.testng.annotations.Test
    public void testPo2() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click("EY.pen");
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);
        driver.closeBrowser();
    }

    @org.testng.annotations.Test
    public void testPo3() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Pen");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductPen());
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);
        driver.closeBrowser();
    }
}
