package pages.eymerchandise;

import lombok.Data;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class ProductPage extends BasePage {

    @FindBy(id = "Quantity")
    private WebElement quantity;

    @FindBy(xpath = "//button[contains(@id, 'button-addtocart')]")
    private WebElement addToCart;

    @FindBy(xpath = "//div[@id=\"messageBoxContainerId\"]")
    private WebElement alert;

    @FindBy(xpath= "//*[@id=\"customCategoryValidation\"]")
    private WebElement newcat;

    @FindBy(xpath="//*[@id='layout']/z-widget/header/div[3]/div[2]/div/z-widget[2]/nav/ul[1]/li[3]")
    private WebElement apparel;

    @FindBy(xpath= "//*[@id=\"customCategoryValidation\"and @href=\"/category/headwear\"]")
    private WebElement headwear;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
