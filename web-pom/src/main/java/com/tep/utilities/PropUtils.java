package com.tep.utilities;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PropUtils {

    private final String filePath;
    private final Properties properties = new Properties();

    public PropUtils(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fileInputStream = new FileInputStream(this.filePath)) {
            properties.load(fileInputStream);
        } catch (IOException ignored) {
        }
    }

    public String get(String name) {
        return properties.getProperty(name);
    }

    public Properties getAllProperties() {
        return properties;
    }

    public Map<String, String> getAsMap() {
        Map<String, String> map = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }
        return map;
    }

    public void set(String name, String value) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            properties.setProperty(name, value);
            properties.store(fileOutputStream, null);
        } catch (IOException ignored) {
        }
    }
}

