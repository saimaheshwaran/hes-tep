package com.tep.web.browser;

import com.tep.web.base.Element;
import com.tep.web.base.Waits;
import com.tep.web.config.PageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import com.tep.web.config.Constants;

import java.util.Map;

public class WindowManipulation {

    private WebDriver driver;
    private Waits waits;
    private Element element;
    private PageObjects objects;

    public WindowManipulation(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    public WindowManipulation(WebDriver driver, PageObjects objects) {
        this.driver = driver;
        this.objects = objects;
        this.waits = new Waits(driver);
        this.element = new Element(driver);
    }

    public Keys getKey()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            return Keys.CONTROL;
        else if (os.contains("nux") || os.contains("nix"))
            return Keys.CONTROL;
        else if (os.contains("mac"))
            return Keys.COMMAND;
        else
            return null;
    }

    public void zoomInOut(String inOut)
    {
        WebElement Sel= driver.findElement(By.tagName("html"));
        if(inOut.equals("ADD"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.ADD));
        else if(inOut.equals("SUBTRACT"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
        else if(inOut.equals("reset"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
    }

    public void zoomInOutTillElementDisplay(String objName, String inOut) {
        zoomInOutTillElementDisplay(objects.get(objName), inOut);
    }

    public void zoomInOutTillElementDisplay(Map.Entry<String, String> locatorPair,String inOut)
    {
        Actions action = new Actions(driver);
        waits.waitForPresenceOfElementsLocated(locatorPair, Constants.DEFAULT_WAIT_TIME_SEC);
        while(true)
        {
            if (element.get(locatorPair).isDisplayed())
                break;
            else
                action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
        }
    }

    public void resizeBrowser(int width, int height)
    {
        driver.manage().window().setSize(new Dimension(width,height));
    }

    public void maximizeBrowser()
    {
        driver.manage().window().maximize();
    }

    public void zoomInAndOut(String value){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '"+value+"%'");
    }

}
