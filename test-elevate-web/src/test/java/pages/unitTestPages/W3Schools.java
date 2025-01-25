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

    @FindBy(id = "html")
    private WebElement html;

    @FindBy(id = "fname")
    private WebElement firstName;

    @FindBy(xpath="//input[@value='Submit']")
    private WebElement submit;

    @FindBy(id = "menuButton")
    private WebElement menu;

    @FindBy(xpath="//h1[contains(text(),'The button disabled attribute')]/following::button")
    private WebElement disabledButton;

    @FindBy(xpath="//h1[contains(text(),'The button Element')]/following::button")
    private WebElement enabledButton;

    @FindBy(tagName ="body")
    private WebElement body;



    @FindBy(xpath="(//label)[1]")
    private WebElement label;

    @FindBy(xpath="//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "log-in")
    private WebElement login;

    @FindBy(xpath="//*[@id=\"transactionsTable\"]/tbody/tr[5]/td[4]/a")
    private WebElement tableData;




    @FindBy(xpath="//input[@name='submit']")
    private WebElement submitGURU99;




    @FindBy(xpath="//button[@id='win1']")
    private WebElement window1;

    @FindBy(xpath="//button[@id='win2']")
    private WebElement window2;

    @FindBy(xpath="//textarea[@title='Search']")
    private WebElement search;


    public W3Schools(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
