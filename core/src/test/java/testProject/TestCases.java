package testProject;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;
import com.tep.web.WebAppDriver;
import com.tep.web.config.Enums;
import io.qameta.allure.Allure;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TestCases {

    WebAppDriver driver = new WebAppDriver();
    ApiDriver apiDriver = new ApiDriver();

    @Test
    public void seleniumEYChromeTest() {

        driver.open(Enums.BrowserType.CHROME);

        driver.browserHandling.goTo("EY.page");
        driver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        driver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click("EY.pen");
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs("EY.quantity");
        driver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        driver.javaScriptClick.click("EY.addToCart");
        driver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        driver.waits.sleep(2);

        driver.close();

    }

    @Test
    public void seleniumEYChromeTest1() {

        driver.open(Enums.BrowserType.CHROME);
        driver.browserHandling.goTo("EY.page");
        driver.seleniumSendKeys.sendKeys("EY.searchBox", "Pen");
        driver.actionSendKeys.enterKeys("EY.searchBox", "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click("EY.pen");
        driver.pageValidation.checkPartialPageTitle("Pen", true);
        driver.seleniumSendKeys.clearInputs("EY.quantity");
        driver.seleniumSendKeys.sendKeys("EY.quantity", "2");
        driver.javaScriptClick.click("EY.addToCart");
        driver.textValidation.isPartiallyMatching("EY.alert", "Added to cart", true);
        driver.waits.sleep(2);

        driver.close();
    }

    @Test
    public void api_basic_request_from_yaml() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }

    @Test
    public void api_basic_request_from_yaml_with_query_params() {
        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }

    /**
     * Test to setup parameters
     */
    @Test
    public void test_set_map_parameters() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }});
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }});
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }
    /**
     * Test to setup parameters with mode
     */
    @Test
    public void test_set_map_parameters_with_mode() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.SET);
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", "MyCookieValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.SET);

        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1; MyCookie=MyCookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));

        apiDriver.getResponse().then().body("headers.Cookie", containsString("cookie1=cookieValue1"));

    }

    /**
     * Test to setup parameters with mode and delete
     */
    @Test
    public void test_set_map_parameters_with_mode_for_delete() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.SET);
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", "MyCookieValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", null);
        }}, com.tep.utilities.Enums.Manipulation_Mode.DELETE);
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.SET);

        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));
    }
    /**
     * Test to check as string method
     */
    @Test
    public void test_asString_method() {
        String expected = "{\n" +
                "\t\"apiName\": null,\n" +
                "\t\"baseUri\": \"https://catfact.ninja\",\n" +
                "\t\"basePath\": null,\n" +
                "\t\"endpoint\": \"fact\",\n" +
                "\t\"pathParams\": {},\n" +
                "\t\"headers\": {},\n" +
                "\t\"cookies\": {},\n" +
                "\t\"queryParams\": {},\n" +
                "\t\"formParams\": {},\n" +
                "\t\"proxyHost\": null,\n" +
                "\t\"proxyPort\": null,\n" +
                "\t\"proxyScheme\": null,\n" +
                "\t\"proxyUsername\": null,\n" +
                "\t\"proxyPassword\": null,\n" +
                "\t\"body\": null,\n" +
                "\t\"response\": null\n" +
                "}";
        apiDriver.setBaseUri("https://catfact.ninja");
        apiDriver.setEndPoint("fact");
        Assert.assertEquals(expected, apiDriver.toString());
    }

    /**
     * Test to set endpoint and verify statuscode
     */
    @Test
    public void test_endpoint_placeholders() {
        apiDriver.setBaseUri("https://catfact.ninja");
        apiDriver.setEndPoint("{endpoint}");
        apiDriver.setPathParams(new HashMap<>() {{
            put("endpoint", "fact");
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        Assert.assertEquals(200, apiDriver.getResponse().statusCode());
    }

    /**
     * Test to verify string before and after setting configuration
     */
    @Test
    public void test_set_request_details_from_rest_config() {
        String asStringBefore = apiDriver.toString();
        apiDriver.setConfigFromYaml("api_name_1");
        String asStringAfter = apiDriver.toString();
        Assert.assertNotEquals(asStringBefore, asStringAfter);
    }

    /**
     * Test headers and authorization from request
     */
    @Test
    public void test_retrieve_header_and_authorization () {
        apiDriver.setConfigFromYaml("base_param_post");
        System.out.println(apiDriver);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        Assert.assertEquals("Bearer dev_bearer_token", apiDriver.getHeaders().get("Authorization"));
    }

    /**
     * Test to verify proxy user name
     */
    @Test
    public void test_proxy_from_config_user() {
        apiDriver.setConfigFromYaml("api_proxy_with_creds");
        System.out.println("apidriver.Response = " + apiDriver.getProxyUsername());
        Assert.assertEquals("user",apiDriver.getProxyUsername());

    }
    /**
     * Test to setup form parameters
     */
    @Test
    public void test_rest_api_with_form_params_using_config() {
        apiDriver.setConfigFromYaml("form_param_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(200);
    }
    /**
     * Test to setup form parameters with mode
     */
    @Test
    public void test_rest_api_with_form_params_using_config_and_step() {
        apiDriver.setConfigFromYaml("form_param_post");

        apiDriver.setFormParams(new HashMap<>() {{
            put("formKey1", "formValue1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.SET);

        apiDriver.setFormParams(new HashMap<>() {{
            put("key2", "formValue2");
        }}, com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.setFormParams(new HashMap<>() {{
            put("key1", "value1");
        }}, com.tep.utilities.Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(200);
    }

    /**
     * Test to update request body
     */
    public void test_update_requestbody() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }});
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }});
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }});
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"my_integer","333","Integer", com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"nest.c[0]","12","Integer", com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"nest.c[1]","111","Integer", com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"nest.c[2]","","", com.tep.utilities.Enums.Manipulation_Mode.DELETE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"my_unset_variable","","", com.tep.utilities.Enums.Manipulation_Mode.DELETE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"nest.pankaj","Tester","String", com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"my_string_array[2]","Shakeer","String", com.tep.utilities.Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(),"my_boolean_array[2]","","", com.tep.utilities.Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.Response().validateStringFieldByPath("args.param1", com.tep.utilities.Enums.Comparison_Type.EQUAL,"paramValue1");
        apiDriver.Response().validateStringFieldByPath("headers.Cookie", com.tep.utilities.Enums.Comparison_Type.EQUAL,"cookie1=cookieValue1");
        apiDriver.Response().validateStringFieldByPath("headers.Header1", com.tep.utilities.Enums.Comparison_Type.EQUAL,"headerValue1");
    }

}
