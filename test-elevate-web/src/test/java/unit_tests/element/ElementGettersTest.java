package unit_tests.element;

import com.tep.web.WebAppDriver;
import com.tep.web.config.WebEnums;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.unitTestPages.W3Schools;


public class ElementGettersTest {
    WebAppDriver driver;
    W3Schools w3schools;

    @BeforeEach
    public void initiateTest() {
        driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        w3schools = new W3Schools(driver.getBrowser());
        driver.browserEvent.goToUrl("https://demo.applitools.com/hackathon.html");
    }

    @Test
    public void getElementText() {
        String text = driver.getAttribute.text(w3schools.getLabel());
        System.out.println("Extracted Text from the Element is " + text);
    }


    @Test
    public void getElementAttribute() {
        String attributeValue = driver.getAttribute.fetch(w3schools.getLabel(), "value");
        System.out.println("Attribute value of the Element is " + attributeValue);
    }

    @Test
    public void getElementSelectedStatus() {

        boolean selectedStatus = driver.getElement.isSelected(w3schools.getCheckBox());
        System.out.println("After Selecting the Element");
        System.out.println("Is the Element Selected " + selectedStatus);
    }

    @Test
    public void getElementEnabledStatus() {
        boolean elementEnabled = driver.getElement.isEnabled(w3schools.getCheckBox());
        System.out.println("Is the Element Enabled " + elementEnabled);
    }

    @Test
    public void getElementPresenceStatus() {
        boolean elementPresent = driver.getElement.isDisplayed(w3schools.getCheckBox());
        System.out.println("Is the Element Present " + elementPresent);
    }

    @AfterEach
    public void tearDown() {
        driver.closeBrowser();
    }
}