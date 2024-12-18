package com.tep.pages.eymerchandise;

import com.tep.pages.BasePage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class SearchPage extends BasePage {

    @CacheLookup
    @FindBy(xpath = "//ul//a[@id='linkProductName']//h4[contains(text(),'Pen')]")
    private WebElement productPen;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


}
