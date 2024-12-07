package com.tep.web.browser;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import org.openqa.selenium.WebDriver;
import com.tep.web.config.Constants;

import java.util.Map;

public class WindowHandling {

    private WebDriver driver;
    private Waits waits;
    private Element element;
    String old_win;
    String lastWinHandle;

    public WindowHandling(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    public void handleAlert(String decision)
    {
        if(decision.equals("accept")) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }

    public String alerttext()
    {
        return   driver.switchTo().alert().getText();
    }

    public void switchToNewWindow()
    {
        old_win = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles())
            lastWinHandle = winHandle;
        driver.switchTo().window(lastWinHandle);
    }

    public void switchToOldWindow()
    {
        driver.switchTo().window(old_win);
    }

    public void switchToNewTab()
    {
        old_win = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles())
            lastWinHandle = winHandle;
        driver.switchTo().window(lastWinHandle);
    }

    public void switchToOldTab()
    {
        driver.switchTo().window(old_win);
    }

    public void switchToWindowByTitle(String windowTitle) throws Exception
    {
        old_win = driver.getWindowHandle();
        boolean winFound = false;
        for(String winHandle : driver.getWindowHandles())
        {
            String str = driver.switchTo().window(winHandle).getTitle();
            if (str.equals(windowTitle))
            {
                winFound = true;
                break;
            }
        }
        if (!winFound)
            throw new Exception("Window having title "+windowTitle+" not found");
    }

    public void closeNewWindow()
    {
        driver.close();
    }

    public void switchFrame(Map.Entry<String, String> locatorPair)
    {
        if(locatorPair.getKey().equalsIgnoreCase("index")) {
            driver.switchTo().frame(locatorPair.getValue());
        }
        else
        {
            waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
            driver.switchTo().frame(element.get(locatorPair));
        }
    }

    public void switchToDefaultContent()
    {
        driver.switchTo().defaultContent();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}
