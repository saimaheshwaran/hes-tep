package com.tep.api.request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class XmlApiRequestTest {
    private static final String SAMPLE_XML = "<person><name>John</name><age>25</age><address><city>New York</city></address></person>";
    private final ApiRequest xmlHandler = ApiRequestFactory.forXml();

    @Test
    public void testRead() {
        String name = xmlHandler.read(SAMPLE_XML, "/person/name/text()");
        assertEquals("John", name);

        String age = xmlHandler.read(SAMPLE_XML, "/person/age/text()");
        assertEquals("25", age);

        String city = xmlHandler.read(SAMPLE_XML, "/person/address/city/text()");
        assertEquals("New York", city);
    }

    @Test
    public void testUpdate() {
        String updatedXml = xmlHandler.update(SAMPLE_XML, "/person/name", "Jane");
        String updatedName = xmlHandler.read(updatedXml, "/person/name/text()");
        assertEquals("Jane", updatedName);

        updatedXml = xmlHandler.update(SAMPLE_XML, "/person/address/city", "Los Angeles");
        String updatedCity = xmlHandler.read(updatedXml, "/person/address/city/text()");
        assertEquals("Los Angeles", updatedCity);
    }

    @Test
    public void testDelete() {
        String afterDeletion = xmlHandler.delete(SAMPLE_XML, "/person/address");
        assertNull(xmlHandler.read(afterDeletion, "/person/address"));

        afterDeletion = xmlHandler.delete(SAMPLE_XML, "/person/name");
        assertNull(xmlHandler.read(afterDeletion, "/person/name"));
    }
}
