package com.tep.api.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating instances of {@link ApiRequest} based on content type.
 * <p>
 * Provides handlers for JSON and XML content manipulation and identifies the appropriate handler
 * for given content.
 */
public final class ApiRequestFactory {

    private static final Logger logger = LoggerFactory.getLogger(ApiRequestFactory.class);

    // Singleton instances of JSON and XML handlers
    private static final ApiRequest JSON_HANDLER = new ApiRequestJsonImpl();
    private static final ApiRequest XML_HANDLER = new ApiRequestXmlImpl();

    // Private constructor to prevent instantiation
    private ApiRequestFactory() {
        throw new UnsupportedOperationException("ApiRequestFactory is a utility class and cannot be instantiated.");
    }

    /**
     * Returns the {@link ApiRequest} instance for handling JSON content.
     *
     * @return the JSON handler instance
     */
    public static ApiRequest forJson() {
        return JSON_HANDLER;
    }

    /**
     * Returns the {@link ApiRequest} instance for handling XML content.
     *
     * @return the XML handler instance
     */
    public static ApiRequest forXml() {
        return XML_HANDLER;
    }

    /**
     * Determines the appropriate {@link ApiRequest} instance based on the content type.
     *
     * @param content the input content to evaluate (e.g., JSON or XML)
     * @return the corresponding {@link ApiRequest} instance
     * @throws IllegalArgumentException if the content type is unknown
     */
    public static ApiRequest forContent(String content) {
        ContentType type = determineContentType(content);
        return switch (type) {
            case JSON -> JSON_HANDLER;
            case XML -> XML_HANDLER;
            case UNKNOWN -> {
                String errorMessage = String.format(
                        "Unknown content type for content: '%.20s'. Unable to determine the handler.",
                        content == null ? "null" : content.trim()
                );
                logger.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        };
    }

    /**
     * Determines the type of the content (JSON, XML, or UNKNOWN).
     *
     * @param content the input content to evaluate
     * @return the {@link ContentType} corresponding to the content
     */
    private static ContentType determineContentType(String content) {
        if (content == null || content.trim().isEmpty()) {
            return ContentType.UNKNOWN;
        }
        String trimmedContent = content.trim();
        if (trimmedContent.startsWith("{") || trimmedContent.startsWith("[")) {
            return ContentType.JSON;
        } else if (trimmedContent.startsWith("<")) {
            return ContentType.XML;
        } else {
            return ContentType.UNKNOWN;
        }
    }

    /**
     * Enum representing the possible content types for determining handlers.
     */
    private enum ContentType {
        JSON, XML, UNKNOWN
    }
}
