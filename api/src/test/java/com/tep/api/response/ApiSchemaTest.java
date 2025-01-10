package com.tep.api.response;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiSchemaTest {

    ApiDriver apiDriver = new ApiDriver();

    @Test
    public void test_schema_validation_positive() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        Assertions.assertEquals(200, apiDriver.getResponse().statusCode());
        ApiSchema apiSchema = new ApiSchema(apiDriver.getResponse());
        apiSchema.validateJsonSchema("testProject/misc/schema/catfact_fact.json");
    }

    @Test
    public void test_schema_validation_negative() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        Assertions.assertEquals(200, apiDriver.getResponse().statusCode());
        ApiSchema apiSchema = new ApiSchema(apiDriver.getResponse());
        Assertions.assertThrows(AssertionError.class, () -> apiSchema.validateJsonSchema("testProject/misc/schema/incorrect_catfact_fact.json"));
    }
}

