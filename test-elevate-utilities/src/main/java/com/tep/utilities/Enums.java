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

    public enum Manipulation_Mode {
        SET,
        UPDATE,
        DELETE
    }

    public enum Comparison_Type {
        EQUAL,
        NOT_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        CONTAINS,
        NOT_CONTAINS,
        STARTS_WITH,
        HAS_ITEM,
        NOT_HAS_ITEM,
        IS_EMPTY,
        IS_NOT_EMPTY,
        IS_NULL,
        IS_NOT_NULL,
        IS_TRUE,
        IS_FALSE,
        IS_NUMBER,
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private Enums() {
        throw new UnsupportedOperationException("Enums class cannot be instantiated.");
    }

}
