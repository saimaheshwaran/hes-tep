package com.tep.web.browser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class TabHandling {

    private WebDriver driver;

    public TabHandling(WebDriver driver) {
        this.driver = driver;
    }

    public void createNew(String url){
        String a = "window.open('about:blank','_blank');";
        ((JavascriptExecutor)driver).executeScript(a);
        for(String winHandle : driver.getWindowHandles())
            driver.switchTo().window(winHandle);
        driver.get(url);
    }

    public void switchTo(String tabTitle){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
            if(driver.getTitle().equalsIgnoreCase(tabTitle)){
                break;
            }
        }
    }

    public  void  switchToFirstTab(){
        Set<String> handles = driver.getWindowHandles();
        for(String handle :handles) {
            driver.switchTo().window(handle);
            break;
        }
    }

}
