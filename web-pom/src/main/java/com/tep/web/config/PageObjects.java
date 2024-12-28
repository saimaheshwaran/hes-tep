package com.tep.web.config;

import org.yaml.snakeyaml.Yaml;
import com.tep.utilities.ExcelReader;
import com.tep.utilities.YamlReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.Map;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.util.LinkedHashMap;

public class PageObjects {

    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> objects = new LinkedHashMap<>();

    public PageObjects() {
        try {
            String extension = Constants.PAGE_OBJECT_TYPE;
            switch (extension) {
                case "yaml", "yml" -> {
                    YamlReader yamlReader = new YamlReader();
                    objects = (LinkedHashMap) yamlReader.getYamlDataFromFolder(Constants.TEST_DATA_INPUT_PATH);
                }
                case "json" -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    FileInputStream readFile = new FileInputStream(Constants.TEST_DATA_INPUT_PATH + "PageObjects.json");
                    objects = (LinkedHashMap) objectMapper.readValue(readFile, LinkedHashMap.class);
                    readFile.close();
                }
                case "xlsx", "excel", "xls" -> {
                    ExcelReader excelReader = new ExcelReader();
                    objects = excelReader.getPageObjects(Constants.TEST_DATA_INPUT_PATH + "PageObjects.xlsx");
                }
                default -> objects = null;
            }
        } catch (Exception ignored) {
        }

        assert objects != null;
        for (String page : objects.keySet())
            for (String element : objects.get(page).keySet())
                if (!objects.get(page).get(element).containsKey("value"))
                    objects.get(page).get(element).put("value", "");

    }

    public Map.Entry<String, String> get(String objName) {
        String[] splitter = objName.split("\\.", 2);
        String page = splitter[0], element = splitter[1];
        for (var locator : objects.get(page).get(element).keySet()) {
            if (!(locator.equalsIgnoreCase("value") || objects.get(page).get(element).get(locator).equalsIgnoreCase(""))) {
                switch (locator.toLowerCase()) {
                    case "id", "css", "name", "xpath", "tagname", "linktest", "classname", "partiallinktest" -> {
                        return new Map.Entry<String, String>() {
                            @Override
                            public String getKey() {
                                return locator;
                            }

                            @Override
                            public String getValue() {
                                return objects.get(page).get(element).get(locator);
                            }

                            @Override
                            public String setValue(String value) {
                                return null;
                            }
                        };
                    }
                }
            }
        }
        return new Map.Entry<String, String>() {
            @Override
            public String getKey() {
                return null;
            }

            @Override
            public String getValue() {
                return null;
            }

            @Override
            public String setValue(String value) {
                return null;
            }
        };
    }

    public String set(String objName, String locator, String locatorValue) {
        try {
            String[] splitter = objName.split("\\.", 2);
            String page = splitter[0], element = splitter[1];
            objects.get(page).get(element).put(locator, locatorValue);
            return objects.get(page).get(element).get(locator);
        } catch (Exception e) {
            return "";
        }
    }

    public String get(String objName, String locator) {
        try {
            String[] splitter = objName.split("\\.", 2);
            String page = splitter[0], element = splitter[1];
            return objects.get(page).get(element).get(locator);
        } catch (Exception e) {
            return "";
        }
    }

    public void unload() {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringObj = new StringWriter();
        Yaml yaml = new Yaml();
        try {
            File outputDirectory = new File(Constants.TEST_DATA_OUTPUT_PATH);
            if(!outputDirectory.exists()) outputDirectory.mkdirs();

            FileWriter yamlWriteFile = new FileWriter(Constants.TEST_DATA_OUTPUT_PATH + "PageObjects" + ".yaml");
            yaml.dump(objects, yamlWriteFile);
            yamlWriteFile.close();

            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(stringObj, objects);
            FileWriter jsonWriteFile = new FileWriter(Constants.TEST_DATA_OUTPUT_PATH + "PageObjects" + ".json");
            jsonWriteFile.write(stringObj.toString());
            jsonWriteFile.close();
        } catch (Exception ignored) {

        }
        //objects.clear();
    }
}
