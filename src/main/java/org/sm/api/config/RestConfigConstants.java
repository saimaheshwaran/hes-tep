package org.sm.api.config;

public final class RestConfigConstants {

    public static final String API_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources/api";
    public static final String ENV_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/.env";
    public static final String ENVIRONMENT = "dev";
    public static final String FALLBACK_ENVIRONMENT = "default";
    public static final int DEFAULT_MAX_RETRIES = 2;
    public static final int DEFAULT_MAX_BACKOFF_MS = 5000;

}
