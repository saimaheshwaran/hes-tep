package org.sm.api.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class to handle request, providing appropriate handlers for different request types.
 *
 */
public class BodyHandlerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(BodyHandlerFactory.class);

    private static final BodyHandler JSON_HANDLER = new BodyHandlerJSONImpl();

    private BodyHandlerFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Provides the JSON handler for request manipulation.
     *
     * @return BodyHandler specific for JSON request.
     */
    public static BodyHandler forJson() {
        return JSON_HANDLER;
    }

    /**
     * Provides the appropriate request handler (JSON/XML) based on the request's type.
     *
     * @param content The string representation of the request.
     * @return BodyHandler specific for the detected request type.
     * @throws IllegalArgumentException If the request type is unknown or unsupported.
     */
    public static BodyHandler forContent(String content) {
        ContentType type = getContentType(content);

        return switch (type) {
            case JSON -> JSON_HANDLER;
            case XML -> null;
            case UNKNOWN -> {
                LOGGER.error(String.format("Unknown content type for content '%.20s'. Unable to determine the handler.", content));
                throw new IllegalArgumentException(String.format("Unknown content type for content '%.20s'. Unable to determine the handler.", content));
            }
        };
    }

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

    private enum ContentType {
        JSON, XML, UNKNOWN
    }
}
