package com.tep.api.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of {@link ApiRequest} for handling XML content.
 * <p>
 * Provides functionalities for reading, updating, and deleting XML data using XPath-like expressions.
 */
public class ApiRequestXmlImpl implements ApiRequest {

    private static final Logger logger = LoggerFactory.getLogger(ApiRequestXmlImpl.class);
    private final ObjectMapper xmlMapper = new XmlMapper();

    /**
     * Converts an object to an XML string representation.
     *
     * @param obj the object to be converted
     * @return the XML string representation of the object
     * @throws RuntimeException if the conversion fails
     */
    @Override
    public String toString(Object obj) {
        try {
            return xmlMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Failed to convert object to XML", e);
            throw new RuntimeException("Failed to convert object to XML", e);
        }
    }

    /**
     * Reads a value from the XML content using an XPath-like expression.
     *
     * @param content the XML content
     * @param path    the XPath expression
     * @return the XML value at the specified XPath, or null if not found
     * @throws RuntimeException if the reading fails
     */
    @Override
    public String read(String content, String path) {
        try {
            Document document = DocumentHelper.parseText(content);
            Node node = document.selectSingleNode(path);
            return node != null ? node.asXML() : null;
        } catch (Exception e) {
            logger.error("Failed to read XML content", e);
            throw new RuntimeException("Failed to read XML content", e);
        }
    }

    /**
     * Deletes a value from the XML content at the specified XPath expression.
     *
     * @param content the XML content
     * @param path    the XPath expression
     * @return the updated XML content as a string
     * @throws RuntimeException if the deletion fails
     */
    @Override
    public String delete(String content, String path) {
        try {
            Document document = DocumentHelper.parseText(content);
            List<Node> nodes = document.selectNodes(path);
            for (Node node : nodes) {
                node.detach(); // Remove the node from the document
            }
            return document.asXML();
        } catch (Exception e) {
            logger.error("Failed to delete from XML content", e);
            throw new RuntimeException("Failed to delete from XML content", e);
        }
    }

    /**
     * Reads a value from the XML content and converts it to the specified type.
     *
     * @param <T>       the type of the value to return
     * @param content   the XML content
     * @param path      the XPath expression
     * @param type      the type to convert the value to
     * @return the value at the specified XPath converted to the given type, or null if not found
     * @throws RuntimeException if the reading or conversion fails
     */
    @Override
    public <T> T read(String content, String path, Class<T> type) {
        String xmlFragment = read(content, path);
        if (xmlFragment == null) {
            return null; // Return null if no value was found at the path
        }
        try {
            return xmlMapper.readValue(xmlFragment, type); // Convert the XML fragment to the target type
        } catch (Exception e) {
            logger.error("Failed to read XML content", e);
            throw new RuntimeException("Failed to read XML content", e);
        }
    }

    /**
     * Updates the value in the XML content at the specified XPath expression.
     *
     * @param content the XML content
     * @param path    the XPath expression
     * @param value   the new value to set at the specified path
     * @return the updated XML content as a string
     * @throws RuntimeException if the update fails
     */
    @Override
    public String update(String content, String path, Object value) {
        try {
            Document document = DocumentHelper.parseText(content);
            Node node = document.selectSingleNode(path);
            if (node != null) {
                node.setText(value.toString()); // Set the new value at the specified node
            }
            return document.asXML();
        } catch (Exception e) {
            logger.error("Failed to update XML content", e);
            throw new RuntimeException("Failed to update XML content", e);
        }
    }
}
