package com.hes.api;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.NoRouteToHostException;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class DriverApiOperationsTest {
    final HashMap<String, String> cookies = new HashMap<>() {{
        put("CookieKey", "CookieValue");
    }};
    final HashMap<String, String> headers = new HashMap<>() {{
        put("Header1", "Value1");
    }};
    final HashMap<String, String> pathParams = new HashMap<>() {{
        put("endpoint", "post");
    }};
    final HashMap<String, String> queryParams = new HashMap<>() {{
        put("Param1", "Value1");
    }};
    final String body = "{\"myJson\":{\"arr\":[1,2,3,4,5]}}";
    DriverApiOperations api;

    @BeforeMethod
    void setup() {
        RestAssured.reset();
        api = new DriverApiOperations();
        api.resetRequest();
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    void test_basic_request_with_all_variables() {
        api.setBaseUri("https://httpbin.org");
        api.setBasePath("{endpoint}");
        api.setCookies(cookies);
        api.setHeaders(headers);
        api.setPathParams(pathParams);
        api.setQueryParams(queryParams);
        api.setBody(body);
        api.executeRequest("POST");
        // Validations
        api.getResponse().then().body("url", equalTo("https://httpbin.org/post?Param1=Value1"));
        api.getResponse().then().body("headers.Header1", equalTo("Value1"));
        api.getResponse().then().body("headers.Cookie", equalTo("CookieKey=CookieValue"));
        api.getResponse().then().body("args.Param1", equalTo("Value1"));
    }

    @Test
    public void test_not_set_baseUri() {
        try {
            api.executeRequest("GET");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue(e.getCause() instanceof IllegalStateException, "Expected cause to be IllegalStateException");
        }
    }

    @Test
    public void test_buildRequestSpec_and_execute_only_host() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 3)
                .setParam("http.connection.timeout", 2));

        api.setBaseUri("https://httpbin.org");
        api.setBasePath("{endpoint}");
        api.setCookies(cookies);
        api.setHeaders(headers);
        api.setPathParams(pathParams);
        api.setQueryParams(queryParams);
        api.setBody(body);
        api.setProxyHost("myproxy.com");
        api.setProxyScheme("http");

        try {
            api.executeRequest("POST");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue((e.getCause() instanceof ConnectTimeoutException) || (e.getCause() instanceof NoRouteToHostException),
                    "Expected cause to be ConnectTimeoutException or NoRouteToHostException");
        }
    }

    @Test
    public void test_buildRequestSpec_and_execute_only_host_and_port() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 3)
                .setParam("http.connection.timeout", 2));

        api.setBaseUri("https://httpbin.org");
        api.setBasePath("{endpoint}");
        api.setCookies(cookies);
        api.setHeaders(headers);
        api.setPathParams(pathParams);
        api.setQueryParams(queryParams);
        api.setBody(body);
        api.setProxyHost("myproxy.com");
        api.setProxyScheme("http");
        api.setProxyPort(8080);

        try {
            api.executeRequest("POST");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue((e.getCause() instanceof ConnectTimeoutException) || (e.getCause() instanceof NoRouteToHostException),
                    "Expected cause to be ConnectTimeoutException or NoRouteToHostException");
        }
    }

    @Test
    public void test_buildRequestSpec_and_execute_all_proxy_details() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 3)
                .setParam("http.connection.timeout", 2));

        api.setBaseUri("https://httpbin.org");
        api.setBasePath("{endpoint}");
        api.setCookies(cookies);
        api.setHeaders(headers);
        api.setPathParams(pathParams);
        api.setQueryParams(queryParams);
        api.setBody(body);
        api.setProxyHost("myproxy.com");
        api.setProxyScheme("http");
        api.setProxyPort(8080);
        api.setProxyUsername("user");
        api.setProxyPassword("pass");

        try {
            api.executeRequest("POST");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue((e.getCause() instanceof ConnectTimeoutException) || (e.getCause() instanceof NoRouteToHostException),
                    "Expected cause to be ConnectTimeoutException or NoRouteToHostException");
        }
    }

    @Test
    public void test_buildRequestSpec_and_execute_only_host_and_creds() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 3)
                .setParam("http.connection.timeout", 2));

        api.setBaseUri("https://httpbin.org");
        api.setBasePath("{endpoint}");
        api.setCookies(cookies);
        api.setHeaders(headers);
        api.setPathParams(pathParams);
        api.setQueryParams(queryParams);
        api.setBody(body);
        api.setProxyHost("myproxy.com");
        api.setProxyScheme("http");
        api.setProxyUsername("user");
        api.setProxyPassword("pass");

        try {
            api.executeRequest("POST");
            Assert.fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Expected exception was thrown, test passes
            Assert.assertTrue((e.getCause() instanceof ConnectTimeoutException) || (e.getCause() instanceof NoRouteToHostException),
                    "Expected cause to be ConnectTimeoutException or NoRouteToHostException");
        }
    }
}
