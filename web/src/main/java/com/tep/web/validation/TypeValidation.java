package com.tep.web.validation;

import com.tep.utilities.PropUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * TypeValidation class to handle validation of locator types and options.
 */
public class TypeValidation {

    private static TypeValidation instance = null;
    private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);

    /**
     * Private constructor to prevent instantiation.
     */
    private TypeValidation() {
    }

    /**
     * Returns the singleton instance of TypeValidation.
     *
     * @return the singleton instance of TypeValidation.
     */
    public static TypeValidation getInstance() {
        if (instance == null) {
            instance = new TypeValidation();
        }
        return instance;
    }

    /**
     * Validates if the provided locator type is valid.
     *
     * @param type the locator type to validate.
     * @return true if the locator type is valid, false otherwise.
     */
    public boolean validLocatorType(String type) {
        return Arrays.asList("id", "class", "css", "name", "xpath", "linktext", "partiallinktext", "tagname").contains(type);
    }

    /**
     * Throws an exception if the provided locator type is invalid.
     *
     * @param type the locator type to validate.
     * @throws Exception if the locator type is invalid.
     */
    public void validateLocator(String type) throws Exception {
        if (!validLocatorType(type)) {
            logger.error("Invalid locator type provided: {}", type);
            throw new Exception("Invalid locator type - " + type);
        }
    }

    /**
     * Validates if the provided option by type is valid.
     *
     * @param optionBy the option by type to validate.
     * @return true if the option by type is valid, false otherwise.
     */
    public boolean validOptionBy(String optionBy) {
        return Arrays.asList("text", "value", "index").contains(optionBy);
    }

    /**
     * Throws an exception if the provided option by type is invalid.
     *
     * @param optionBy the option by type to validate.
     * @throws Exception if the option by type is invalid.
     */
    public void validateOptionBy(String optionBy) throws Exception {
        if (!validOptionBy(optionBy)) {
            logger.error("Invalid option by criteria provided: {}", optionBy);
            throw new Exception("Invalid option by - " + optionBy);
        }
    }
}
