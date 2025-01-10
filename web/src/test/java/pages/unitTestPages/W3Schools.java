package pages.unitTestPages;

import lombok.Data;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

@Data
public class W3Schools extends BasePage {


    @CacheLookup
    @FindBy(id = "iframeResult")
    private WebElement frame;

    @FindBy(id = "vehicle1")
    private WebElement vehicle1;

    @FindBy(xpath = "//span[@class='slider round']")
    private WebElement toggle;

    @FindBy(xpath="//button[text()='Click me']")
    private WebElement clickButton;

    @FindBy(xpath="//p[@ondblclick='myFunction()']")
    private WebElement doubleClickButton;

    @FindBy(id = "cars")
    private WebElement dropdown;
    public W3Schools(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
