package tests.general.web.base;

import com.tep.web.base.SeleniumDriver;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.config.Enums;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWaitsTest {

    @Test
    public void waitUntilElementDisplayedTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        WebDriver webDriver =  seleniumDriver.getBrowser();
        webDriver.navigate().to("https://ey.corpmerchandise.com");
        WebElement element = webDriver.findElement(By.id("searchTextBox"));
        seleniumWaits.untilElementDisplayed(element);
        element.sendKeys("Pen");
        seleniumWaits.sleep(3);
        seleniumDriver.closeBrowser();
    }

}
