package tests.eymerchandise;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.Test;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.SearchPage;
import pages.eymerchandise.ProductPage;

public class TestCases {

    @Test
    public void testPo4() {

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

        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }

    @Test
    public void testPo5() {

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

        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }

}
