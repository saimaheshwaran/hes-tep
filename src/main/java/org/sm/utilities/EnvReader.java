package org.sm.utilities;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for reading environment variables from a .env file.
 * <p>
 * This class uses the Dotenv library to load environment variables from a specified file
 * or directory and provides a method to retrieve them as a key-value map.
 * </p>
 */
public final class EnvReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvReader.class);

    // Private constructor to prevent instantiation
    private EnvReader() {
        throw new UnsupportedOperationException("EnvReader is a utility class and cannot be instantiated.");
    }

    /**
     * Reads environment variables from a specified .env file or directory.
     *
     * @param directoryPath the directory path containing the .env file
     * @return a {@code Map<String, String>} containing the environment variables as key-value pairs
     * @throws RuntimeException if an error occurs while reading the environment variables
     */
    public static Map<String, String> getEnv(String directoryPath) {
        if (directoryPath == null || directoryPath.isBlank()) {
            throw new IllegalArgumentException("Directory path must not be null or empty.");
        }

        LOGGER.info("Attempting to read environment variables from directory: {}", directoryPath);

        try {
            Dotenv dotenv = Dotenv.configure()
                    .directory(directoryPath)
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();

            return dotenv.entries()
                    .stream()
                    .collect(Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));
        } catch (Exception e) {
            LOGGER.error("Failed to read environment variables from directory: {}", directoryPath, e);
            throw new RuntimeException("Error reading environment variables from directory: " + directoryPath, e);
        }
    }
}