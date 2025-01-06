package tests.eymerchandise;
import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;

public class Testcasesweb {
    @AfterMethod
    public void tearDown() {
    }

    @org.testng.annotations.Test
    public void testverifypage() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(Enums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        driver.pageValidation.checkPageTitle("Search", true);
        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();
    }

    @org.testng.annotations.Test
    public void testProduct() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(Enums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());

        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Bag");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductBag());
        driver.pageValidation.checkPartialPageTitle("Bag", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);

        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();
    }

    @org.testng.annotations.Test
    public void testnewcategory() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(Enums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumClick.click(productPage.getNewcat());
        driver.pageValidation.checkPartialPageTitle("NEW", true);
        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }

    @org.testng.annotations.Test
    public void testsubmenu() {

        WebAppDriver driver = new WebAppDriver();

        driver.openBrowser(Enums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumClick.click(productPage.getApparel());
        driver.seleniumClick.click(productPage.getHeadwear());
        driver.pageValidation.checkPartialPageTitle("Headwear", true);
        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }

}
