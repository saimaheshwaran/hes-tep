package com.hes.api.response;

import io.restassured.RestAssured;
import com.hes.api.DriverApi;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class SchemaValidationsTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.reset();
        DriverApi.getInstance().resetRequest(); // Clean up before each test, should be added in NGTPAPIHooks
    }

    @Test
    public void test_schema_validation_positive() {
        DriverApi driverApi = DriverApi.getInstance();
        driverApi.setApiDetailsFromYaml("catfact_fact");
        driverApi.executeRequest("get");
        assertEquals(200, driverApi.getResponse().statusCode());
        SchemaValidations.validateJsonSchema("data/inputdata/dev/catfact_fact/catfact_fact.json");
    }

    @Test
    public void test_schema_validation_negative() {
        DriverApi driverApi = DriverApi.getInstance();
        driverApi.setApiDetailsFromYaml("catfact_fact");
        driverApi.executeRequest("get");
        assertEquals(200, driverApi.getResponse().statusCode());
        assertThrows(AssertionError.class, () -> SchemaValidations.validateJsonSchema("data/inputdata/dev/catfact_fact/incorrect_catfact_fact.json"));
    }
}
