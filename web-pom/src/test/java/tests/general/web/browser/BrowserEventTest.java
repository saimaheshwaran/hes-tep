package tests.general.web.browser;

import com.tep.web.base.SeleniumDriver;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.browser.BrowserEvent;
import com.tep.web.config.Enums;
import com.tep.web.element.click.SeleniumClick;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserEventTest {

    @Test
    public void goToTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

    @Test
    public void navigationTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")).click();
        seleniumWaits.sleep(2); browserEvent.back();
        seleniumWaits.sleep(2); browserEvent.forward();
        seleniumWaits.sleep(2); browserEvent.refresh();
        seleniumDriver.closeBrowser();
    }

}
