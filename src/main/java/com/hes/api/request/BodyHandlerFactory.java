package com.hes.api.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code BodyHandlerFactory} class provides factory methods to obtain appropriate {@code BodyHandler} instances
 * based on the content type. It supports JSON and XML content types.
 */
public class BodyHandlerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(BodyHandlerFactory.class);

    private static final BodyHandler JSON_HANDLER = new BodyHandlerJSONImpl();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BodyHandlerFactory() {
        // Prevent instantiation
    }

    /**
     * Returns a {@code BodyHandler} instance for handling JSON content.
     *
     * @return a {@code BodyHandler} instance for JSON content.
     */
    public static BodyHandler forJson() {
        return JSON_HANDLER;
    }

    /**
     * Returns a {@code BodyHandler} instance based on the provided content.
     * Determines the content type and returns the appropriate handler.
     *
     * @param content The content to be handled.
     * @return a {@code BodyHandler} instance for the determined content type.
     * @throws IllegalArgumentException if the content type is unknown.
     */
    public static BodyHandler forContent(String content) {
        ContentType type = getContentType(content);

        return switch (type) {
            case JSON -> JSON_HANDLER;
            case XML -> null; // Placeholder for XML handler implementation
            case UNKNOWN -> {
                String errorMessage = String.format("Unknown content type for content '%.20s'. Unable to determine the handler.", content);
                LOGGER.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        };
    }

    /**
     * Determines the content type based on the provided content string.
     *
     * @param content The content to be analyzed.
     * @return the determined {@code ContentType}.
     */
    private static ContentType getContentType(String content) {
        if (content == null || content.trim().isEmpty()) {
            return ContentType.UNKNOWN;
        }

        String trimmedContent = content.trim();
        if (trimmedContent.startsWith("{") || trimmedContent.startsWith("[")) {
            return ContentType.JSON;
        } else if (trimmedContent.startsWith("<")) {
            return ContentType.XML;
        }

        return ContentType.UNKNOWN;
    }

    /**
     * Enum representing the supported content types.
     */
    private enum ContentType {
        JSON, XML, UNKNOWN
    }
}
