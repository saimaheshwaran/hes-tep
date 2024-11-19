package org.sm.api;

import io.restassured.RestAssured;
import jdk.jfr.Enabled;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriverApiProxyTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.reset();
        DriverApi.getInstance().resetRequest(); // Clean up before each test, should be added in NGTPAPIHooks
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
        DriverApi.getInstance().resetRequest();  // Clean up after each test, should be added in NGTPAPIHooks
    }

    @Test(enabled = false)
    void test_request_with_proxy() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().setProxyUrl("http://localhost:9000");
        DriverApi.getInstance().setProxyUsername("user");
        DriverApi.getInstance().setProxyPassword("pass");
        DriverApi.getInstance().executeRequest("POST");
        DriverApi.getInstance().getResponse().then().statusCode(200);
    }
}
