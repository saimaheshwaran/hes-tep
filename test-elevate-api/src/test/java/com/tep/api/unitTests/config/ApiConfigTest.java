package com.tep.api.unitTests.config;

import com.tep.api.config.ApiConfig;
import com.tep.api.config.ApiKeys;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class ApiConfigTest {


    @Test
    public void test_getInvalidApiConfiguration() {
        Object result = ApiConfig.get("Invalid_Configuration", ApiKeys.BASE_URI);
        Assert.assertNull(result, "Configuration value should be null for invalid API name and path");
    }

    @Test
    void test_getValidPath() {
        String expected = "https://www.dev.example.com";
        String actual = ApiConfig.get("api_name_1", ApiKeys.BASE_URI);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void test_from_FallbackEnv_ApiName() {
        String expected = "fallbackEnv_test_field_value";
        String actual = ApiConfig.get("api_name_1", "fallbackEnv_test_field");
        Assert.assertEquals(expected, actual);
    }

    @Test
    void test_dotenv_replacement() {
        HashMap<String, String> expected = new HashMap<>() {{
            put("Authentication", "Bearer dev_bearer_token");
            put("Cookie2", "Value2");
        }};
        HashMap<String, String> actual = ApiConfig.get("api_name_1", ApiKeys.COOKIES);
        Assert.assertEquals(expected, actual);
    }
}

