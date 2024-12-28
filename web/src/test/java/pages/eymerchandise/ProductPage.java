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

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
