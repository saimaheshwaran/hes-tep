import com.tep.pages.eymerchandise.HomePage;
import com.tep.pages.eymerchandise.ProductPage;
import com.tep.pages.eymerchandise.SearchPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TestCases {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @Test
    public void testPo() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1440,768", "--disable-gpu");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://ey.corpmerchandise.com");

        HomePage homePage = new HomePage(driver, wait);
        SearchPage searchPage = new SearchPage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);

        homePage.getSearchBox().sendKeys("Pen");
        System.out.println("AccessibleName: " + homePage.getSearchBox().getAccessibleName());
        System.out.println("TagName: " + homePage.getSearchBox().getTagName());
        System.out.println("Location: " + homePage.getSearchBox().getLocation());

        homePage.getSearchBox().submit();
        searchPage.getProductPen().click();
        productPage.getQuantity().sendKeys("3");
        productPage.getAddToCart().click();


        driver.close();
        driver.quit();


    }
}
