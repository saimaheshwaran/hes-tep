package com.tep.web.config;

/**
 * The Enums class contains the enumeration definitions used across the framework.
 * Currently, it includes the BrowserType enum that defines supported browser types.
 */
public class WebEnums {

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

}
