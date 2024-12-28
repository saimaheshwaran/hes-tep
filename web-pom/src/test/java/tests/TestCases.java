package tests;

import com.tep.utilities.ExcelReader;
import com.tep.web.WebAppDriver;
import com.tep.web.config.Constants;
import com.tep.web.config.Enums;
import org.junit.Test;
import pages.eymerchandise.HomePage;
import pages.eymerchandise.ProductPage;
import pages.eymerchandise.SearchPage;

public class TestCases {

    WebAppDriver driver = new WebAppDriver();

    @Test
    public void seleniumYamlTest() {

        driver.open(Enums.BrowserType.CHROME);
        driver.browserHandling.goTo("EY.page");

        driver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        driver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click("EY.pen");
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs("EY.quantity");
        driver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        driver.javaScriptClick.click("EY.addToCart");
        driver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        driver.waits.sleep(2);

        driver.close();

    }

    @Test
    public void seleniumPomTest() {

        driver.open(Enums.BrowserType.CHROME);
        driver.browserHandling.goTo("EY.page");
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

        driver.waits.sleep(2);
        driver.close();

    }

    @Test
    public void excelReadTest() {
    }
}
