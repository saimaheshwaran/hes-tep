package com.tep.api.unitTests.request;

import com.tep.api.request.ApiRequest;
import com.tep.api.request.ApiRequestFactory;
import com.tep.api.request.ApiRequestJsonImpl;
import com.tep.api.request.ApiRequestXmlImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiRequestFactoryTest {

    @Test
    public void testForJson() {
        ApiRequest request = ApiRequestFactory.forJson();
        assertTrue(request instanceof ApiRequestJsonImpl);
    }

    @Test
    public void testForXml() {
        ApiRequest request = ApiRequestFactory.forXml();
        assertTrue(request instanceof ApiRequestXmlImpl);
    }

    @Test
    public void testForContentWithJson() {
        ApiRequest request = ApiRequestFactory.forContent("{\"key\":\"value\"}");
        assertTrue(request instanceof ApiRequestJsonImpl);
    }

    @Test
    public void testForContentWithXml() {
        String xmlContent = "<root><element>value</element></root>";
        ApiRequest request = ApiRequestFactory.forContent(xmlContent);
        assertTrue(request instanceof ApiRequestXmlImpl);
    }

    @Test
    public void testForContentWithUnknown() {
        String unknownContent = "unknown_content";
        assertThrows(IllegalArgumentException.class, () -> ApiRequestFactory.forContent(unknownContent));
    }

    @Test
    public void testForContentWithEmptyOrNull() {
        String emptyContent = "";
        assertThrows(IllegalArgumentException.class, () -> ApiRequestFactory.forContent(emptyContent));

        assertThrows(IllegalArgumentException.class, () -> ApiRequestFactory.forContent(null));
    }

}
