package com.tep.api.response;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;
import com.tep.utilities.Enums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiResponseTest {

    ApiDriver apiDriver = new ApiDriver();
    @Test
    void test_single_field_validations_string() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);


        apiDriver.Response().validateStringFieldByPath("json.my_string", Enums.Comparison_Type.EQUAL, "string");
        apiDriver.Response().validateStringFieldByPath("json.my_string", Enums.Comparison_Type.NOT_EQUAL, "integer");
        apiDriver.Response().validateStringFieldByPath("json.my_string", Enums.Comparison_Type.CONTAINS, "str");
        apiDriver.Response().validateStringFieldByPath("json.my_string", Enums.Comparison_Type.NOT_CONTAINS, "int");
        apiDriver.Response().validateStringFieldByPath("json.my_string_array", Enums.Comparison_Type.HAS_ITEM, "str2");
        apiDriver.Response().validateStringFieldByPath("json.my_string_array", Enums.Comparison_Type.NOT_HAS_ITEM, "int1");
    }

    @Test
    void test_single_field_validations_boolean() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.Response().validateBooleanFieldByPath("json.my_boolean", Enums.Comparison_Type.EQUAL, true);
        apiDriver.Response().validateBooleanFieldByPath("json.my_boolean", Enums.Comparison_Type.NOT_EQUAL, false);
        apiDriver.Response().validateBooleanFieldByPath("json.my_boolean_array", Enums.Comparison_Type.HAS_ITEM, true);
        apiDriver.Response().validateBooleanFieldByPath("json.my_boolean_array", Enums.Comparison_Type.NOT_HAS_ITEM, false);
    }

    @Test
    void test_single_field_validations_numeric() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        // Integers
        apiDriver.Response().validateNumericFieldByPath("json.my_integer", Enums.Comparison_Type.EQUAL, 123);
        apiDriver.Response().validateNumericFieldByPath("json.my_integer", Enums.Comparison_Type.NOT_EQUAL, 321);
        apiDriver.Response().validateNumericFieldByPath("json.my_number_array", Enums.Comparison_Type.HAS_ITEM, 3);
        apiDriver.Response().validateNumericFieldByPath("json.my_number_array", Enums.Comparison_Type.NOT_HAS_ITEM, 5);
        apiDriver.Response().validateNumericFieldByPath("json.my_integer", Enums.Comparison_Type.GREATER_THAN, 122);
        apiDriver.Response().validateNumericFieldByPath("json.my_integer", Enums.Comparison_Type.LESS_THAN, 124);

        // Doubles
        apiDriver.Response().validateNumericFieldByPath("json.my_double", Enums.Comparison_Type.EQUAL, 12.34);
        apiDriver.Response().validateNumericFieldByPath("json.my_double", Enums.Comparison_Type.NOT_EQUAL, 13.12);
        apiDriver.Response().validateNumericFieldByPath("json.my_number_array", Enums.Comparison_Type.HAS_ITEM, 4.4);
        apiDriver.Response().validateNumericFieldByPath("json.my_number_array",  Enums.Comparison_Type.NOT_HAS_ITEM, 11.1);
        apiDriver.Response().validateNumericFieldByPath("json.my_double", Enums.Comparison_Type.GREATER_THAN, 11.11);
        apiDriver.Response().validateNumericFieldByPath("json.my_double", Enums.Comparison_Type.LESS_THAN, 12.35);
    }

    @Test
    void test_validation_against_json_string_positive() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.Response().validateResponseJsonAgainstExpectedJsonFile("src/test/resources/testProject/misc/response/httpbin_post_positive.json");
    }

    @Test
    void test_validation_against_json_string_negative() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        AssertionError thrown = Assertions.assertThrows(
                AssertionError.class,
                () ->  apiDriver.Response().validateResponseJsonAgainstExpectedJsonFile("src/test/resources/testProject/misc/response/httpbin_post_negative.json"));

        Assertions.assertEquals("""
                json.my_double
                Expected: 12.35
                     got: 12.34

                json.my_integer
                Expected: 124
                     got: 123

                json.my_number_array[]: Expected 4 values but got 5

                json.nest.b
                Expected: false
                     got: true""", thrown.getMessage().trim());
    }

    @Test
    void test_text_validation() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.Response().validateText(Enums.Comparison_Type.CONTAINS, "\"url\": \"https://httpbin.org/post\"");
        apiDriver.Response().validateText(Enums.Comparison_Type.NOT_CONTAINS, "abcdefg12345");
    }
}