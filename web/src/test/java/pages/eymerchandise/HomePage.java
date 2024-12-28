package pages.eymerchandise;

import lombok.Data;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

@Data
public class HomePage extends BasePage {

    @CacheLookup
    @FindBy(id = "searchTextBox")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
