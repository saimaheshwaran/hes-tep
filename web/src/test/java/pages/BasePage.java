package pages;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
public abstract class BasePage {

    private static WebDriver driver;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
}
