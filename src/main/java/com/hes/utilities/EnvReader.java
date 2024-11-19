package com.hes.utilities;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code EnvReader} class provides utility methods for reading environment variables from a specified directory.
 * This class cannot be instantiated.
 */
public final class EnvReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvReader.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EnvReader() {
        throw new UnsupportedOperationException("EnvReader is a utility class and cannot be instantiated.");
    }

    /**
     * Reads environment variables from the specified directory and returns them as a map.
     *
     * @param directoryPath The path to the directory containing the environment variables file.
     * @return A map containing the environment variables as key-value pairs.
     * @throws IllegalArgumentException if the directory path is null or empty.
     * @throws RuntimeException if there is an error reading the environment variables.
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
