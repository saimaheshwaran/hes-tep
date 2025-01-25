package com.tep.api.config;

public class ApiEnums {

    public enum Http_Method {
        GET,
        POST
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private ApiEnums() {
        throw new UnsupportedOperationException("ApiConstants is a utility class and cannot be instantiated.");
    }
}
