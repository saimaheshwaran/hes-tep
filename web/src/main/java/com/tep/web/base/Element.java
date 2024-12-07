package com.tep.web.base;

import com.tep.web.config.Constants;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;

public class Element {

    private Waits waits;
    private WebDriver driver;
    private WebElement element;

    public Element (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement get(Map.Entry<String, String> locatorPair) {
        switch (locatorPair.getKey().toLowerCase()) {
            case "id" -> element = driver.findElement(By.id(locatorPair.getValue()));
            case "name" -> element = driver.findElement(By.name(locatorPair.getValue()));
            case "xpath" -> element = driver.findElement(By.xpath(locatorPair.getValue()));
            case "css" -> element = driver.findElement(By.cssSelector(locatorPair.getValue()));
            case "tagname" -> element = driver.findElement(By.tagName(locatorPair.getValue()));
            case "linktest" -> element = driver.findElement(By.linkText(locatorPair.getValue()));
            case "classname" -> element = driver.findElement(By.className(locatorPair.getValue()));
            case "partiallinktest" -> element = driver.findElement(By.partialLinkText(locatorPair.getValue()));
            default -> element = null;
        }
        scrollTo(element);
        return element;
    }

    public By getBy(Map.Entry<String, String> locatorPair) {
        By by;
        switch (locatorPair.getKey().toLowerCase()) {
            case "id" -> by = By.id(locatorPair.getValue());
            case "name" -> by = By.name(locatorPair.getValue());
            case "xpath" -> by = By.xpath(locatorPair.getValue());
            case "css" -> by = By.cssSelector(locatorPair.getValue());
            case "tagname" -> by = By.tagName(locatorPair.getValue());
            case "linktest" -> by = By.linkText(locatorPair.getValue());
            case "classname" -> by = By.className(locatorPair.getValue());
            case "partiallinktest" -> by = By.partialLinkText(locatorPair.getValue());
            default -> by = null;
        }
        return by;
    }

    public void scrollTo(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style','background:GreenYellow; border: 0px solid blue;')", element);
            Thread.sleep(100);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style','background:; border: 0px solid blue;')", element);
        } catch (Exception ignored) { }
    }

    public List<WebElement> getList(Map.Entry<String, String> locatorPair){
        return waits.waitForPresenceOfElementsLocated(locatorPair, Constants.IMPLICIT_WAIT_TIME_SEC);
    }

    public boolean isSelected(Map.Entry<String, String> locatorPair){
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isSelected();
        }catch(StaleElementReferenceException e){
            isSelected(locatorPair);
        }
        return elementSelected;
    }

    public boolean isEnabled(Map.Entry<String, String> locatorPair){
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isEnabled();
        }catch(StaleElementReferenceException e){
            isEnabled(locatorPair);
        }
        return elementSelected;
    }

    public boolean isDisplayed(Map.Entry<String, String> locatorPair){
        boolean elementSelected = false;
        try {
            WebElement element = get(locatorPair);
            elementSelected = element.isDisplayed();
        }catch(StaleElementReferenceException e){
            isDisplayed(locatorPair);
        }
        return elementSelected;
    }

}
