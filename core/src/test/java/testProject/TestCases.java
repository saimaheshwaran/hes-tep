package testProject;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;

import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    ApiDriver apiDriver = new ApiDriver();
    WebAppDriver webAppDriver = new WebAppDriver();

    @Test
    public void combined_Test() {

        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);

        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);

        webAppDriver.open(Enums.BrowserType.CHROME);

        webAppDriver.browserHandling.goTo("EY.page");
        webAppDriver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        webAppDriver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        webAppDriver.pageValidation.checkPartialPageTitle("Search", true);
        webAppDriver.seleniumClick.click("EY.pen");
        webAppDriver.pageValidation.checkPartialPageTitle("Pen", true);
        webAppDriver.seleniumSendKeys.clearInputs("EY.quantity");
        webAppDriver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        webAppDriver.javaScriptClick.click("EY.addToCart");
        webAppDriver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        webAppDriver.waits.sleep(2);

        webAppDriver.close();

    }

    @Test
    @Epic("Api Testing")
    @Feature("Yaml Representation")
    @Story("Basics")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test to verify that the user can execute api from yaml representation")
    public void api_basic_request_from_yaml() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }

    @Test
    @Epic("Api Testing")
    @Feature("Yaml Representation")
    @Story("QueryParams")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test to verify that the user can do api query param execution from yaml representation")
    public void api_basic_request_from_yaml_with_query_params() {
        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }

    @Test
    @Epic("Web Testing")
    @Feature("Yaml Representation")
    @Story("Basics")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test to verify that the user can do basic execution from yaml representation")
    public void seleniumEYChromeTest() {

        webAppDriver.open(Enums.BrowserType.CHROME);

        webAppDriver.browserHandling.goTo("EY.page");
        webAppDriver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        webAppDriver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        webAppDriver.pageValidation.checkPartialPageTitle("Search", true);
        webAppDriver.seleniumClick.click("EY.pen");
        webAppDriver.pageValidation.checkPartialPageTitle("Pen", true);
        webAppDriver.seleniumSendKeys.clearInputs("EY.quantity");
        webAppDriver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        webAppDriver.javaScriptClick.click("EY.addToCart");
        webAppDriver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        webAppDriver.waits.sleep(2);

        webAppDriver.close();

    }

}
