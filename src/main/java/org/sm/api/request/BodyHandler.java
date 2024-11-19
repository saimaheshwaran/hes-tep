package org.sm.api.request;

public interface BodyHandler {

    /**
     * Converts a given object to its string representation, e.g., JSON or XML.
     *
     * @param obj The object to be converted.
     * @return The string representation of the object.
     */
    String toString(Object obj);

    /**
     * Reads content based on the provided path/expression and returns the string representation.
     *
     * @param content The source content, e.g., JSON or XML string.
     * @param path    The path or expression to extract data.
     * @return The string representation of the extracted content.
     */
    String read(String content, String path);

    /**
     * Reads content based on the provided path/expression and returns a typed value.
     *
     * @param <T>     The expected type of the value.
     * @param content The source content, e.g., JSON or XML string.
     * @param path    The path or expression to extract data.
     * @param type    The class type of the expected value.
     * @return The typed value corresponding to the provided path.
     */
    <T> T read(String content, String path, Class<T> type);

    /**
     * Updates the content based on the provided path/expression with the given value.
     *
     * @param content The source content, e.g., JSON or XML string.
     * @param path    The path or expression indicating where to update.
     * @param value   The new value.
     * @return The updated content.
     */
    String update(String content, String path, Object value);
    // TODO add ability to create new nodes.

    /**
     * Deletes a specific field or node from the content based on the given path/expression.
     *
     * @param content The source content, e.g., JSON or XML string.
     * @param path    The path or expression indicating what to delete.
     * @return The content after deletion.
     */
    String delete(String content, String path);
}