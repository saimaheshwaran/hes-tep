package com.hes.api.request;

import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class BodyHandlerFactoryTest {

    @Test
    public void testForJson() {
        BodyHandler handler = BodyHandlerFactory.forJson();
        assertTrue(handler instanceof BodyHandlerJSONImpl);
    }

    @Test
    public void testForContentWithJson() {
        BodyHandler handler = BodyHandlerFactory.forContent("{\"key\":\"value\"}");
        assertTrue(handler instanceof BodyHandlerJSONImpl);
    }

    @Test
    public void testForContentWithUnknown() {
        String unknownContent = "unknown_content";
        assertThrows(IllegalArgumentException.class, () -> BodyHandlerFactory.forContent(unknownContent));
    }

    @Test
    public void testForContentWithEmptyOrNull() {
        String emptyContent = "";
        assertThrows(IllegalArgumentException.class, () -> BodyHandlerFactory.forContent(emptyContent));

        assertThrows(IllegalArgumentException.class, () -> BodyHandlerFactory.forContent(null));
    }


}


