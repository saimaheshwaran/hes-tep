package com.tep.utilities;

import java.nio.file.FileSystems;

public final class Constants {

    // Base project directory retrieved from the system property
    public static String PROJECT_DIR = System.getProperty("user.dir");

    // Platform-specific file separator
    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    // Paths for source directories
    public static final String SRC_PATH = PROJECT_DIR + SEPARATOR + "src";
    public static final String TARGET_PATH = PROJECT_DIR + SEPARATOR + "target";
    public static final String MAIN_PATH = SRC_PATH + SEPARATOR + "main";
    public static final String TEST_PATH = SRC_PATH + SEPARATOR + "test";

    // Paths for resource directories
    public static final String MAIN_RESOURCES_PATH = MAIN_PATH + SEPARATOR + "resources";
    public static final String TEST_RESOURCES_PATH = TEST_PATH + SEPARATOR + "resources";

    // Paths for properties files
    public static final String TEP_PROPERTIES_PATH = MAIN_RESOURCES_PATH + SEPARATOR + "tep.properties";
    public static final PropUtils TEP_PROPERTIES = new PropUtils(TEP_PROPERTIES_PATH);

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
        throw new UnsupportedOperationException("ApiConstants is a utility class and cannot be instantiated.");
    }
}
