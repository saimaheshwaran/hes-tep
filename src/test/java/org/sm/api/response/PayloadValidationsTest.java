package org.sm.api.response;

import org.sm.api.DriverApi;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PayloadValidationsTest {

    @BeforeMethod
    public void setUp() {
        DriverApi.getInstance().resetRequest(); // Clean up before each test, should be added in NGTPAPIHooks
    }

    @AfterMethod
    public void tearDown() {
        DriverApi.getInstance().clear(); // Clean up before each test, should be added in NGTPAPIHooks
    }

    @Test
    void test_single_field_validations_string() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string", "equal", "string");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string", "not equal", "integer");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string", "contain", "str");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string", "not contain", "int");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string_array", "have item", "str2");
        PayloadValidations.validateResponseStringFieldByPath("json.my_string_array", "not have item", "int1");
    }

    @Test
    void test_single_field_validations_boolean() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");
        PayloadValidations.validateResponseBooleanFieldByPath("json.my_boolean", "equal", true);
        PayloadValidations.validateResponseBooleanFieldByPath("json.my_boolean", "not equal", false);
        PayloadValidations.validateResponseBooleanFieldByPath("json.my_boolean_array", "have item", true);
        PayloadValidations.validateResponseBooleanFieldByPath("json.my_boolean_array", "not have item", false);
    }

    @Test
    void test_single_field_validations_numeric() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");

        // Integers
        PayloadValidations.validateResponseNumericFieldByPath("json.my_integer", "equal", 123);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_integer", "not equal", 321);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_number_array", "have item", 3);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_number_array", "not have item", 5);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_integer", "be greater than", 122);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_integer", "be less than", 124);

        // Doubles
        PayloadValidations.validateResponseNumericFieldByPath("json.my_double", "equal", 12.34);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_double", "not equal", 13.12);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_number_array", "have item", 4.4);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_number_array", "not have item", 11.1);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_double", "be greater than", 11.11);
        PayloadValidations.validateResponseNumericFieldByPath("json.my_double", "be less than", 12.35);
    }

    @Test
    void test_validation_against_json_string_positive() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");
        PayloadValidations.validateJSONResponseAgainstExpectedJSONFile("/Users/SaiMaheshwaran.Rajan/Library/CloudStorage/OneDrive-EY/Documents/IdeaProjects/Api/src/test/resources/expectedResponse/httpbin_post_positive.json");
    }

    @Test
    void test_validation_against_json_string_negative() {
        DriverApi.getInstance().resetRequest();
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");
        try {
            PayloadValidations.validateJSONResponseAgainstExpectedJSONFile("/Users/SaiMaheshwaran.Rajan/Library/CloudStorage/OneDrive-EY/Documents/IdeaProjects/Api/src/test/resources/expectedResponse/httpbin_post_negative.json");
            Assert.fail("Expected AssertionError to be thrown");
        } catch (AssertionError e) {
            // Expected exception was thrown, test passes
            Assert.assertEquals(e.getMessage().trim(), """
                json.my_double
                Expected: 12.35
                     got: 12.34


                json.my_integer
                Expected: 124
                     got: 123


                json.my_number_array[]: Expected 4 values but got 5


                json.nest.b
                Expected: false
                     got: true""");
        }
    }

    @Test
    void test_text_validation() {
        DriverApi.getInstance().setApiDetailsFromYaml("httpbin_post");
        DriverApi.getInstance().executeRequest("post");
        PayloadValidations.validateResponseText("contain", "\"url\": \"https://httpbin.org/post\"");
        PayloadValidations.validateResponseText("not contain", "abcdefg12345");
    }
}
