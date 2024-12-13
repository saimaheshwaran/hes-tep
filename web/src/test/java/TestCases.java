import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import org.junit.Test;

public class TestCases {

    WebAppDriver driver = new WebAppDriver();

    @Test
    public void seleniumEYChromeTest() {

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
    public void seleniumEYChromeTest1() {

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
}
