package com.hes.api.config;

/**
 * The {@code RestConfigConstants} class holds constant values used for REST configuration.
 * These constants include file paths, environment settings, and default retry configurations.
 */
public final class RestConfigConstants {

    /**
     * The path to the API folder containing configuration files.
     */
    public static final String API_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources/api";

    /**
     * The path to the environment file containing environment variables.
     */
    public static final String ENV_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/.env";

    /**
     * The name of the primary environment.
     */
    public static final String ENVIRONMENT = "dev";

    /**
     * The name of the fallback environment.
     */
    public static final String FALLBACK_ENVIRONMENT = "default";

    /**
     * The default maximum number of retries for API requests.
     */
    public static final int DEFAULT_MAX_RETRIES = 2;

    /**
     * The default maximum backoff time in milliseconds for retries.
     */
    public static final int DEFAULT_MAX_BACKOFF_MS = 5000;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private RestConfigConstants() {
        // Prevent instantiation
    }
}
