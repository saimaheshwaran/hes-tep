package com.tep.api.config;

import com.tep.utilities.Constants;
import com.tep.utilities.PropUtils;

import static com.tep.utilities.Constants.*;

/**
 * A utility class that defines constant paths and configuration values for the API framework.
 *
 * <p>This class is final and cannot be instantiated. It provides a centralized location for all
 * constant values used in the project, ensuring consistent and maintainable path management.</p>
 */
public final class ApiConstants {

    /**
     * The default number of retries for API calls.
     */
    public static final int DEFAULT_MAX_RETRIES = 2;

    /**
     * The maximum backoff time in milliseconds.
     */
    public static final int DEFAULT_MAX_BACKOFF_MS = 5000;

    /**
     * Private constructor to prevent instantiation.
     *
     * <p>This constructor is private to ensure that the class cannot be instantiated. This is a utility class
     * and should only be used to access its static members.</p>
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class
     */
    private ApiConstants() {
        throw new UnsupportedOperationException("ApiConstants is a utility class and cannot be instantiated.");
    }

}
