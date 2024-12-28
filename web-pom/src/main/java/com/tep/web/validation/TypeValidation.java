package com.tep.web.validation;

import java.util.Arrays;

/**
 * The TypeValidation class is a singleton that provides methods to validate locator types and option types.
 * It ensures that the specified locator and option types are valid before performing any related actions.
 */
public class TypeValidation {

    /** The single instance of TypeValidation used for validating locator and option types. */
    private static TypeValidation instance = null;

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private TypeValidation() {
    }

    /**
     * Returns the singleton instance of the TypeValidation class.
     *
     * @return The singleton instance of TypeValidation.
     */
    public static TypeValidation getInstance() {
        if (instance == null) {
            instance = new TypeValidation();
        }
        return instance;
    }

    /**
     * Validates whether the given locator type is valid.
     *
     * @param type The locator type to validate (e.g., "id", "class", "xpath").
     * @return true if the locator type is valid, false otherwise.
     */
    public boolean validLocatorType(String type) {
        return Arrays.asList("id", "class", "css", "name", "xpath", "linktext", "partiallinktext", "tagname").contains(type);
    }

    /**
     * Validates the given locator type and throws an exception if the type is invalid.
     *
     * @param type The locator type to validate (e.g., "id", "class", "xpath").
     * @throws Exception if the locator type is invalid.
     */
    public void validateLocator(String type) throws Exception {
        if (!validLocatorType(type)) {
            throw new Exception("Invalid locator type - " + type);
        }
    }

    /**
     * Validates whether the given option type is valid for a dropdown (e.g., "text", "value", "index").
     *
     * @param optionBy The option type to validate.
     * @return true if the option type is valid, false otherwise.
     */
    public boolean validOptionBy(String optionBy) {
        return Arrays.asList("text", "value", "index").contains(optionBy);
    }

    /**
     * Validates the given option type and throws an exception if the type is invalid.
     *
     * @param optionBy The option type to validate (e.g., "text", "value", "index").
     * @throws Exception if the option type is invalid.
     */
    public void validateOptionBy(String optionBy) throws Exception {
        if (!validOptionBy(optionBy)) {
            throw new Exception("Invalid option by - " + optionBy);
        }
    }

}
