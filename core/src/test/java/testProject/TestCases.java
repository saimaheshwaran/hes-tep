package testProject;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;

import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    ApiDriver apiDriver = new ApiDriver();
    WebAppDriver webAppDriver;

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

        webAppDriver = new WebAppDriver(Enums.BrowserType.CHROME);
        System.setProperty("default_assertion", "soft");

        webAppDriver.browserHandling.goTo("EY.page");
        webAppDriver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        webAppDriver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        webAppDriver.pageValidation.checkPartialPageTitle("Search", true);
        webAppDriver.seleniumClick.click("EY.pen");
        webAppDriver.pageValidation.checkPartialPageTitle("Pen", true);
        webAppDriver.seleniumSendKeys.clearInputs("EY.quantity");
        webAppDriver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        webAppDriver.javaScriptClick.click("EY.addToCart");
        webAppDriver.waits.sleep(2);

        webAppDriver.close();

    }

    @Test
    public void api_basic_request_from_yaml() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }

    @Test
    public void api_basic_request_from_yaml_with_query_params() {
        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }

    @Test
    public void seleniumEYChromeTest() {

        webAppDriver = new WebAppDriver(Enums.BrowserType.CHROME);
        System.setProperty("default_assertion", "soft");

        webAppDriver.browserHandling.goTo("EY.page");
        webAppDriver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        webAppDriver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        webAppDriver.pageValidation.checkPartialPageTitle("Search", true);
        webAppDriver.seleniumClick.click("EY.pen");
        webAppDriver.pageValidation.checkPartialPageTitle("Pen", true);
        webAppDriver.seleniumSendKeys.clearInputs("EY.quantity");
        webAppDriver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        webAppDriver.javaScriptClick.click("EY.addToCart");
        webAppDriver.waits.sleep(2);

        webAppDriver.close();

    }

}
