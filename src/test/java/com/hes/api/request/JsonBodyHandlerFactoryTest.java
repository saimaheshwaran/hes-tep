package com.hes.api.request;

import com.hes.utilities.StringToType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JsonBodyHandlerFactoryTest {
    private final BodyHandler handler = BodyHandlerFactory.forJson();
    private final String initialJson = "{\"name\":\"John\",\"age\":25,\"address\":{\"city\":\"New York\"},\"newPerson\":null,\"hobbies\":null}";

    @Test
    public void testUpdateJson() {

        // Update name
        String updatedJson = handler.update(initialJson, "$.name", "Doe");
        assertEquals("{\"name\":\"Doe\",\"age\":25,\"address\":{\"city\":\"New York\"},\"newPerson\":null,\"hobbies\":null}", updatedJson);

        // Update age
        updatedJson = handler.update(initialJson, "$.age", 30);
        assertEquals("{\"name\":\"John\",\"age\":30,\"address\":{\"city\":\"New York\"},\"newPerson\":null,\"hobbies\":null}", updatedJson);

        // Update city inside address
        updatedJson = handler.update(initialJson, "$.address.city", "Los Angeles");
        assertEquals("{\"name\":\"John\",\"age\":25,\"address\":{\"city\":\"Los Angeles\"},\"newPerson\":null,\"hobbies\":null}", updatedJson);
    }

    @Test
    public void test_update_json_object() {

        // Test adding a new JSON object
        Object newPersonJson = StringToType.parseValue("{\"name\":\"Jane\",\"age\":28}:json");

        String updatedJsonWithNewObject = handler.update(initialJson, "$.newPerson", newPersonJson);
        assertTrue(updatedJsonWithNewObject.contains("\"newPerson\":{\"name\":\"Jane\",\"age\":28}"));

        // Test adding a new JSON array
        Object hobbiesJsonArray = StringToType.parseValue("[\"Reading\",\"Hiking\"]:json");
        String updatedJsonWithNewArray = handler.update(initialJson, "$.hobbies", hobbiesJsonArray);
        assertTrue(updatedJsonWithNewArray.contains("\"hobbies\":[\"Reading\",\"Hiking\"]"));
    }

    @Test
    public void testReadJson() {
        BodyHandler handler = BodyHandlerFactory.forJson();

        // Read name
        String name = handler.read(initialJson, "$.name");
        assertEquals("John", name);

        // Read age
        int age = handler.read(initialJson, "$.age", Integer.class);
        assertEquals(25, age);

        // Read city inside address
        String city = handler.read(initialJson, "$.address.city");
        assertEquals("New York", city);
    }

    @Test
    public void testDeleteJson() {
        BodyHandler handler = BodyHandlerFactory.forJson();

        // Delete name
        String updatedJson = handler.delete(initialJson, "$.name");
        assertEquals("{\"age\":25,\"address\":{\"city\":\"New York\"},\"newPerson\":null,\"hobbies\":null}", updatedJson);

        // Delete age
        updatedJson = handler.delete(initialJson, "$.age");
        assertEquals("{\"name\":\"John\",\"address\":{\"city\":\"New York\"},\"newPerson\":null,\"hobbies\":null}", updatedJson);

        // Delete city inside address
        updatedJson = handler.delete(initialJson, "$.address.city");
        assertEquals("{\"name\":\"John\",\"age\":25,\"address\":{},\"newPerson\":null,\"hobbies\":null}", updatedJson);
    }

    @Test
    public void test_pojo_update() {
        // Convert POJO to JSON
        Person person = new Person("Bob", 30);
        String personJson = BodyHandlerFactory.forJson().toString(person);
        assertEquals(personJson, "{\"name\":\"Bob\",\"age\":30}");
        String updatedPerson = BodyHandlerFactory.forJson().update(personJson, "$.name", 2);
        assertEquals(updatedPerson, "{\"name\":2,\"age\":30}");
    }

    @Test
    public void testCreateObjectFromScratch() {
        String initialJson = "{}";
        BodyHandler handler = BodyHandlerFactory.forContent(initialJson);
        Object node = StringToType.parseValue("{}:json");

        String json = handler.update(initialJson, "$.organization", "Ernst & Young US LLP");
        json = handler.update(json, "$.employees[]", node);
        System.out.println("json = " + json);
        json = handler.update(json, "$.employees[0].details.name", "Andrei Goriunov");
        json = handler.update(json, "$.employees[0].jobTitle", "Automation Engineer");
        json = handler.update(json, "$.employees[+]", node);
        json = handler.update(json, "$.employees[1].details.name", "Raktim Saha");
        System.out.println("json = " + json);
    }
}

class Person {
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}