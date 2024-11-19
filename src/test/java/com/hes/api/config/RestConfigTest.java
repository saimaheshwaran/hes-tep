package com.hes.api.config;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestConfigTest {

    @Test
    void test_from_env_apiName() {
        String expected = "https://www.dev.example.com";
        RestConfig restConfig = RestConfig.getInstance();
        String actual = restConfig.get("api_name_1", RestConfigKeys.BASE_URI);
        assertEquals(actual, expected);
    }

    @Test
    void test_from_FallbackEnv_ApiName() {
        String expected = "fallbackEnv_test_field_value";
        RestConfig restConfig = RestConfig.getInstance();
        String actual = restConfig.get("api_name_1", "fallbackEnv_test_field");
        assertEquals(actual, expected);
    }

    @Test
    void test_dotenv_replacement() {
        HashMap<String, String> expected = new HashMap<>() {{
            put("Authentication", "Bearer dev_bearer_token");
            put("Cookie2", "Value2");
        }};
        RestConfig restConfig = RestConfig.getInstance();
        HashMap<String, String> actual = restConfig.get("api_name_1", RestConfigKeys.COOKIES);
        assertEquals(actual, expected);
    }

    @Test
    void test_dotenv_get() {
        RestConfig restConfig = RestConfig.getInstance();
        Map<String, String> dotenv = restConfig.getEnv();
        assertTrue(dotenv.containsKey("UAT_BEARER_TOKEN"));
        assertTrue(dotenv.containsKey("UAT_API_PASSWORD"));
        assertTrue(dotenv.containsKey("DEV_BEARER_TOKEN"));
        assertTrue(dotenv.containsKey("DEV_API_PASSWORD"));
        assertTrue(dotenv.containsKey("DEV_PROXY_USERNAME"));
        assertTrue(dotenv.containsKey("DEV_PROXY_PASSWORD"));
    }

}
