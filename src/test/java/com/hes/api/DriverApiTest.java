package com.hes.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class DriverApiTest {

    Response response;

    @BeforeMethod
    public void setUp() {
        DriverApi.getInstance().resetRequest();
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
        DriverApi.getInstance().resetRequest();
    }

    @Test
    public void test_rest_api_basic_request_from_config() {
        DriverApi driverApi = DriverApi.getInstance();
        driverApi.setApiDetailsFromYaml("catfact_fact");
        response = driverApi.executeRequest("get");
        assertEquals(200, response.statusCode());
    }

    @Test
    public void test_endpoint_placeholders() {
        DriverApi driverApi = DriverApi.getInstance();
        RestAssured.useRelaxedHTTPSValidation();
        driverApi.setBaseUri("https://catfact.ninja");
        driverApi.setEndpoint("{endpoint}");
        driverApi.setPathParams(new HashMap<>() {{
            put("endpoint", "fact");
        }});
        response = driverApi.executeRequest("get");
        assertEquals(200, driverApi.getResponse().statusCode());
    }

    @Test
    public void test_asString_method() {
        DriverApi driverApi = DriverApi.getInstance();
        String expected = "{\n" +
                "\t\"apiName\":null,\n" +
                "\t\"baseUri\":\"https://catfact.ninja\",\n" +
                "\t\"basePath\":null,\n" +
                "\t\"endpoint\":\"fact\",\n" +
                "\t\"pathParams\":{},\n" +
                "\t\"headers\":{},\n" +
                "\t\"cookies\":{},\n" +
                "\t\"queryParams\":{},\n" +
                "\t\"formParams\":{},\n" +
                "\t\"proxyHost\":null,\n" +
                "\t\"proxyPort\":null,\n" +
                "\t\"proxyScheme\":null,\n" +
                "\t\"proxyUsername\":null,\n" +
                "\t\"proxyPassword\":null,\n" +
                "\t\"body\":null,\n" +
                "\t\"response\":null\n" +
                "}";
        driverApi.setBaseUri("https://catfact.ninja");
        driverApi.setEndpoint("fact");
        assertEquals(expected, driverApi.toString());
    }

    @Test
    public void test_without_setting_baseURI() {
        DriverApi driverApi = DriverApi.getInstance();
        driverApi.setEndpoint("fact");

        try {
            response = driverApi.executeRequest("get");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue(e.getCause() instanceof IllegalStateException, "Expected cause to be IllegalStateException");
        }
    }

    @Test
    public void test_with_empty_config() {
        //GlobalProperties.getConfigProperties().setProperty("rest.rest_config_file_path", "config\\empty.yaml");
        DriverApi driverApi = DriverApi.getInstance();
        driverApi.setBaseUri("https://catfact.ninja");
        driverApi.setEndpoint("fact");
        driverApi.executeRequest("get");
    }

    @Test
    public void test_set_request_details_from_rest_config() {
        DriverApi driverApi = DriverApi.getInstance();
        String asStringBefore = driverApi.toString();
        driverApi.setApiDetailsFromYaml("api_name_1");
        String asStringAfter = driverApi.toString();
        assertNotEquals(asStringBefore, asStringAfter);
    }

    @Test
    void test_set_map_parameters() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }});
        DriverApi.getInstance().setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }});
        DriverApi.getInstance().setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }});
        DriverApi.getInstance().executeRequest("post");
        DriverApi.getInstance().getResponse().then().body("args.param1", equalTo("paramValue1"));
        DriverApi.getInstance().getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        DriverApi.getInstance().getResponse().then().body("headers.Header1", equalTo("headerValue1"));
    }

    @Test
    void test_set_map_parameters_with_mode() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }}, "set");
        DriverApi.getInstance().setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }}, "update");
        DriverApi.getInstance().setCookies(new HashMap<>() {{
            put("MyCookie", null);
        }}, "delete");
        DriverApi.getInstance().setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }}, "set");

        DriverApi.getInstance().executeRequest("post");
        DriverApi.getInstance().getResponse().then().body("args.param1", equalTo("paramValue1"));
        DriverApi.getInstance().getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        DriverApi.getInstance().getResponse().then().body("headers.Header1", equalTo("headerValue1"));
    }

    @Test
    void test_set_proxy() {
        String proxyUrl = "https://myproxyurl.com:8080";
        DriverApi.getInstance().setProxyUrl(proxyUrl);
        assertEquals("myproxyurl.com", DriverApi.getInstance().getProxyHost());
        assertEquals("https", DriverApi.getInstance().getProxyScheme());
        assertEquals(8080, DriverApi.getInstance().getProxyPort());
    }

    @Test
    void test_set_proxy_no_port() {
        String proxyUrl = "https://myproxyurl.com";
        DriverApi.getInstance().setProxyUrl(proxyUrl);
        assertEquals("myproxyurl.com", DriverApi.getInstance().getProxyHost());
        assertEquals("https", DriverApi.getInstance().getProxyScheme());
        assertNull(DriverApi.getInstance().getProxyPort());
    }

    @Test
    void test_retrieve_null_response() {
        assertNull(DriverApi.getInstance().getResponse());
    }

    @Test
    void test_retrieve_set_proxy_from_config() {
        DriverApi.getInstance().setApiDetailsFromYaml("test_api");
        assertEquals("myproxy.com", DriverApi.getInstance().getProxyHost());
        assertEquals(8080, DriverApi.getInstance().getProxyPort());
        assertEquals("https", DriverApi.getInstance().getProxyScheme());
        assertEquals("Andrei", DriverApi.getInstance().getProxyUsername());
        assertEquals("SecretPassword", DriverApi.getInstance().getProxyPassword());
    }

    @Test
    void test_retrieve_basePath_from_config() {
        DriverApi.getInstance().setApiDetailsFromYaml("test_api");
        assertEquals("{method}", DriverApi.getInstance().getBasePath());
    }

    @Test
    void test_proxy_from_config_no_creds() {
        DriverApi.getInstance().setApiDetailsFromYaml("api_proxy_with_creds");
        System.out.println("NGTPAPI.rest() = " + DriverApi.getInstance());
    }

    @Test
    void test_rest_api_with_form_params_using_config() {
        DriverApi.getInstance().setApiDetailsFromYaml("form_param_post");
        DriverApi.getInstance().executeRequest("post");
        DriverApi.getInstance().getResponse().then().statusCode(200);
    }

    @Test
    void test_rest_api_with_form_params_using_config_and_step() {
        DriverApi.getInstance().setApiDetailsFromYaml("form_param_post");
        DriverApi.getInstance().setFormParams(new HashMap<>() {{
            put("formKey1", "formValue1");
        }}, "set");
        DriverApi.getInstance().setFormParams(new HashMap<>() {{
            put("key2", "formValue2");
        }}, "update");
        DriverApi.getInstance().setFormParams(new HashMap<>() {{
            put("key1", "value1");
        }}, "delete");
        DriverApi.getInstance().executeRequest("post");
        DriverApi.getInstance().getResponse().then().statusCode(200);
    }

    public static class DriverApiSyncTest {
    }
}
