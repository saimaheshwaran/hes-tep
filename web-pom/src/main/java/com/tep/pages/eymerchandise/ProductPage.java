package com.tep.pages.eymerchandise;

import com.tep.pages.BasePage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class ProductPage extends BasePage {

    @FindBy(id = "Quantity")
    private WebElement quantity;

    @FindBy(xpath = "//button[contains(@id, 'button-addtocart')]")
    private WebElement addToCart;

    @FindBy(xpath = "//div[@id=\"messageBoxContainerId\"]")
    private WebElement alert;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
}
