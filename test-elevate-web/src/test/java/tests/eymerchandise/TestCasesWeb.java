package tests.eymerchandise;

import com.tep.web.WebAppDriver;
import org.junit.jupiter.api.Test;
import com.tep.web.config.WebEnums;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;

public class TestCasesWeb {

    @Test
    public void testverifypage() {
        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        HomePage homePage = new HomePage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.pageValidation.checkPageTitle("Search", true);
        driver.closeBrowser();
    }

    @Test
    public void testProduct() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Bag");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductBag());
        driver.pageValidation.checkPartialPageTitle("Bag", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);
        driver.closeBrowser();
    }

    @Test
    public void testnewcategory() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        HomePage homePage = new HomePage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.seleniumClick.click(productPage.getNewcat());
        driver.pageValidation.checkPartialPageTitle("NEW", true);
        driver.closeBrowser();
    }

    @Test
    public void testsubmenu() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        HomePage homePage = new HomePage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.seleniumClick.click(productPage.getApparel());
        driver.seleniumClick.click(productPage.getHeadwear());
        driver.pageValidation.checkPartialPageTitle("Headwear", true);
        driver.closeBrowser();
    }

    @Test
    public void readdatafromexternalsource() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        driver.seleniumSendKeys.sendKeys("EY.searchBox", "Bag");
        driver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        driver.seleniumClick.click("EY.bag");
        driver.pageValidation.checkPartialPageTitle("Bag", true);
        driver.seleniumSendKeys.clearInputs("EY.quantity");
        driver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        driver.seleniumClick.click("EY.addToCart");
        driver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        driver.closeBrowser();
    }

    @Test
    public void testverifypage_chromecanary() {
        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME_CANARY);
        HomePage homePage = new HomePage(driver.getBrowser());
        driver.browserEvent.goToUrl(homePage.getUrl());
        driver.pageValidation.checkPageTitle("Search", true);
        driver.closeBrowser();
    }

}