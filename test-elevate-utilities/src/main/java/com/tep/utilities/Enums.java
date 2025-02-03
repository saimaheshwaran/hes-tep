package com.tep.utilities;

/**
 * The Enums class contains the enumeration definitions used across the framework.
 * Currently, it includes the BrowserType enum that defines supported browser types.
 */
public class Enums {

    /**
     * Enum representing the supported browser types.
     */
    public enum BrowserType {
        /**
         * Chrome browser.
         */
        CHROME,

        /**
         * Firefox browser.
         */
        FIREFOX,

        /**
         * Safari browser.
         */
        SAFARI,

        /**
         * Edge browser.
         */
        EDGE,

        /**
         * Default browser type, used when no specific browser is selected.
         */
        DEFAULT
    }

    /**
     * Enum representing the types of manipulation operations that can be performed.
     */
    public enum Manipulation_Mode {
        /**
         * Set operation, used to create or overwrite data.
         */
        SET,

        /**
         * Update operation, used to modify existing data.
         */
        UPDATE,

        /**
         * Delete operation, used to remove data.
         */
        DELETE
    }

    /**
     * Enum representing the types of comparison operations that can be used in conditions or assertions.
     */
    public enum Comparison_Type {
        /**
         * Indicates that two values are equal.
         */
        EQUAL,

        /**
         * Indicates that two values are not equal.
         */
        NOT_EQUAL,

        /**
         * Indicates that one value is less than another.
         */
        LESS_THAN,

        /**
         * Indicates that one value is less than or equal to another.
         */
        LESS_THAN_OR_EQUAL,

        /**
         * Indicates that one value is greater than another.
         */
        GREATER_THAN,

        /**
         * Indicates that one value is greater than or equal to another.
         */
        GREATER_THAN_OR_EQUAL,

        /**
         * Indicates that one value contains another.
         */
        CONTAINS,

        /**
         * Indicates that one value does not contain another.
         */
        NOT_CONTAINS,

        /**
         * Indicates that a value starts with a specified prefix.
         */
        STARTS_WITH,

        /**
         * Indicates that a collection has a specified item.
         */
        HAS_ITEM,

        /**
         * Indicates that a collection does not have a specified item.
         */
        NOT_HAS_ITEM,

        /**
         * Indicates that a collection or string is empty.
         */
        IS_EMPTY,

        /**
         * Indicates that a collection or string is not empty.
         */
        IS_NOT_EMPTY,

        /**
         * Indicates that a value is null.
         */
        IS_NULL,

        /**
         * Indicates that a value is not null.
         */
        IS_NOT_NULL,

        /**
         * Indicates that a boolean value is true.
         */
        IS_TRUE,

        /**
         * Indicates that a boolean value is false.
         */
        IS_FALSE,

        /**
         * Indicates that a value is a number.
         */
        IS_NUMBER,
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private Enums() {
        throw new UnsupportedOperationException("Enums class cannot be instantiated.");
    }

}
