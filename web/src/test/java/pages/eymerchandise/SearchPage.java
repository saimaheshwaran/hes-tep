package pages.eymerchandise;

import lombok.Data;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

@Data
public class SearchPage extends BasePage {

    @CacheLookup
    @FindBy(xpath = "//ul//a[@id='linkProductName']//h4[contains(text(),'Pen')]")
    private WebElement productPen;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
