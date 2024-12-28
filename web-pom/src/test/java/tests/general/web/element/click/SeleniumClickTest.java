package tests.general.web.element.click;

import com.tep.web.base.SeleniumDriver;
import com.tep.web.base.SeleniumWaits;
import com.tep.web.browser.BrowserEvent;
import com.tep.web.config.Enums;
import com.tep.web.element.click.SeleniumClick;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SeleniumClickTest {

    @Test
    public void singleClickTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumClick seleniumClick = new SeleniumClick(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumClick.click(seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")));
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

    @Test
    public void doubleClickTest() {
        SeleniumDriver seleniumDriver = new SeleniumDriver(Enums.BrowserType.CHROME);
        BrowserEvent browserEvent = new BrowserEvent(seleniumDriver);
        SeleniumClick seleniumClick = new SeleniumClick(seleniumDriver);
        SeleniumWaits seleniumWaits = new SeleniumWaits(seleniumDriver);
        browserEvent.goToUrl("https://ey.corpmerchandise.com");
        seleniumClick.doubleClick(seleniumDriver.getBrowser().findElement(By.xpath("//*[@id=\"impersonateheader\"]/div/z-widget/ul/li[1]/a")));
        seleniumWaits.sleep(2);
        seleniumDriver.closeBrowser();
    }

}
