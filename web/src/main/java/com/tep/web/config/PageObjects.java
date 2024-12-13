package com.tep.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tep.web.validation.CheckBoxValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PageObjects class to manage page objects and their locators.
 */
public class PageObjects {

    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> objects = new LinkedHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CheckBoxValidation.class);

    /**
     * Constructor to initialize and load page objects from a file based on the configured type (yaml or json).
     */
    public PageObjects() {
        try {
            String extension = Constants.PAGE_OBJECT_TYPE;
            FileInputStream readFile = new FileInputStream(Constants.TEST_DATA_INPUT_PATH + "PageObjects." + extension);
            switch (extension) {
                case "yaml", "yml" -> {
                    Yaml yaml = new Yaml();
                    objects = (LinkedHashMap) yaml.loadAs(readFile, LinkedHashMap.class);
                    logger.info("PageObjects loaded from YAML file.");
                }
                case "json" -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objects = (LinkedHashMap) objectMapper.readValue(readFile, LinkedHashMap.class);
                    logger.info("PageObjects loaded from JSON file.");
                }
                default -> {
                    logger.warn("Unsupported file extension for PageObjects: " + extension);
                }
            }
            readFile.close();
        } catch (Exception ignored) {
            logger.error("Error loading PageObjects.",ignored);
        }

        for (String page : objects.keySet())
            for (String element : objects.get(page).keySet())
                if (!objects.get(page).get(element).containsKey("value"))
                    objects.get(page).get(element).put("value", "");

    }

    /**
     * Retrieves the locator for the specified object name.
     *
     * @param objName the name of the object to retrieve the locator for.
     * @return a Map.Entry containing the locator type and value.
     */
    public Map.Entry<String, String> get(String objName) {
        String[] splitter = objName.split("\\.", 2);
        String page = splitter[0], element = splitter[1];
        for (var locator : objects.get(page).get(element).keySet()) {
            if (!(locator.equalsIgnoreCase("value") || objects.get(page).get(element).get(locator).equalsIgnoreCase(""))) {
                switch (locator.toLowerCase()) {
                    case "id", "css", "name", "xpath", "tagname", "linktest", "classname", "partiallinktest" -> {
                        logger.info("Locator found for " + objName + ": " + locator);
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

    /**
     * Sets the locator value for the specified object name and locator type.
     *
     * @param objName      the name of the object.
     * @param locator      the type of the locator.
     * @param locatorValue the value of the locator.
     * @return the set locator value.
     */
    public String set(String objName, String locator, String locatorValue) {
        try {
            String[] splitter = objName.split("\\.", 2);
            String page = splitter[0], element = splitter[1];
            objects.get(page).get(element).put(locator, locatorValue);
            logger.info("Set operation successful for " + objName + " with locator: " + locator + " and locatorValue: " + locatorValue);
            return objects.get(page).get(element).get(locator);
        } catch (Exception e) {
            logger.info("Error in set method for objName: " + objName + ", locator: " + locator + ", locatorValue: " + locatorValue, e);
            return "";
        }
    }

    /**
     * Retrieves the locator value for the specified object name and locator type.
     *
     * @param objName the name of the object.
     * @param locator the type of the locator.
     * @return the locator value.
     */
    public String get(String objName, String locator) {
        try {
            String[] splitter = objName.split("\\.", 2);
            String page = splitter[0], element = splitter[1];
            logger.info("Retrieved value for " + objName + " with locator: " + locator + " is: " + objects.get(page).get(element).get(locator));
            return objects.get(page).get(element).get(locator);
        } catch (Exception e) {
            logger.info("Error in get method for objName: " + objName + ", locator: " + locator, e);
            return "";
        }
    }

    /**
     * Unloads the page objects to yaml and json files.
     */
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
            logger.info("PageObjects YAML file written successfully.");

            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(stringObj, objects);
            FileWriter jsonWriteFile = new FileWriter(Constants.TEST_DATA_OUTPUT_PATH + "PageObjects" + ".json");
            jsonWriteFile.write(stringObj.toString());
            jsonWriteFile.close();
            logger.info("PageObjects JSON file written successfully.");
        } catch (Exception ignored) {
            logger.error("Error occurred while unloading PageObjects",ignored);

        }
        objects.clear();
    }
}
