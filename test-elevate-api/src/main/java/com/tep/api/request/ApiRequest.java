package com.tep.api.request;

/**
 * Interface representing operations for manipulating and querying API request content.
 *
 * <p>This interface provides methods to read, update, delete, and convert content
 * in a structured format, such as JSON or XML, for use in API requests.
 */
public interface ApiRequest {

    /**
     * Converts an object to its string representation.
     *
     * @param obj the object to be converted
     * @return a string representation of the given object
     */
    String toString(Object obj);

    /**
     * Reads a value from the content at the specified path.
     *
     * @param content the source content (e.g., JSON string)
     * @param path the path to the value (e.g., JSONPath or XPath)
     * @return the value at the specified path as a string, or {@code null} if not found
     */
    String read(String content, String path);

    /**
     * Deletes a value from the content at the specified path.
     *
     * @param content the source content (e.g., JSON string)
     * @param path the path to the value to be deleted (e.g., JSONPath or XPath)
     * @return the modified content after the deletion
     */
    String delete(String content, String path);

    /**
     * Reads and casts a value from the content at the specified path to the specified type.
     *
     * @param <T> the type of the value to return
     * @param content the source content (e.g., JSON string)
     * @param path the path to the value (e.g., JSONPath or XPath)
     * @param type the class of the type to cast the value to
     * @return the value at the specified path, cast to the given type, or {@code null} if not found
     */
    <T> T read(String content, String path, Class<T> type);

    /**
     * Updates a value in the content at the specified path.
     *
     * @param content the source content (e.g., JSON string)
     * @param path the path to the value to be updated (e.g., JSONPath or XPath)
     * @param value the new value to be set
     * @return the modified content after the update
     */
    String update(String content, String path, Object value);
}
