import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import org.junit.Test;

public class TestCases {

    @Test
    public void seleniumEYTest() {
        WebAppDriver webAppDriver = new WebAppDriver(Enums.BrowserType.CHROME);
        webAppDriver.browserHandling.goTo("EY.page");
        webAppDriver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        webAppDriver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        webAppDriver.pageValidation.checkPartialPageTitle("Search", true);
        webAppDriver.seleniumClick.click("EY.pen");
        webAppDriver.pageValidation.checkPartialPageTitle("Pen", true);
        webAppDriver.seleniumSendKeys.clearInputs("EY.quantity");
        webAppDriver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        webAppDriver.javaScriptClick.click("EY.addToCart");
        webAppDriver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        webAppDriver.waits.sleep(2);
        webAppDriver.close();
    }
}
