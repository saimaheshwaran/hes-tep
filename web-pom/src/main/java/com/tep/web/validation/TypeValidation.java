package com.tep.web.validation;

import java.util.Arrays;

public class TypeValidation {

    private static TypeValidation instance = null;

    private TypeValidation() {
    }

    public static TypeValidation getInstance() {
        if (instance == null) {
            instance = new TypeValidation();
        }
        return instance;
    }

    public boolean validLocatorType(String type) {
        return Arrays.asList("id", "class", "css", "name", "xpath", "linktext", "partiallinktext", "tagname").contains(type);
    }

    public void validateLocator(String type) throws Exception {
        if (!validLocatorType(type)) {
            throw new Exception("Invalid locator type - " + type);
        }
    }

    public boolean validOptionBy(String optionBy) {
        return Arrays.asList("text", "value", "index").contains(optionBy);
    }

    public void validateOptionBy(String optionBy) throws Exception {
        if (!validOptionBy(optionBy)) {
            throw new Exception("Invalid option by - " + optionBy);
        }
    }

}
