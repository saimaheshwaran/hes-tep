package com.tep.utilities;

import io.cucumber.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tep.utilities.ExceptionUtils.logErrorAndThrowIfNull;

/**
 * The {@code MapUtils} class provides utility methods for operations on maps, such as converting, updating, and merging maps.
 * This class cannot be instantiated.
 */
public class MapUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapUtils.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MapUtils() {
        // Prevent instantiation
    }

    /**
     * Converts the given Map's keys and values to strings using {@code String.valueOf()}.
     *
     * @param inputMap any Map
     * @return input map with String keys and values or an empty map if inputMap is null.
     */
    public static Map<String, String> convertToMapStrStr(Map<?, ?> inputMap) {
        if (inputMap == null) {
            LOGGER.warn("Input Map is null. Returning empty Map<String, String>.");
            return new HashMap<>();
        }
        Map<String, String> mapStrStr = new HashMap<>();
        try {
            inputMap.forEach((key, value) -> mapStrStr.put(String.valueOf(key), String.valueOf(value)));
        } catch (Exception e) {
            LOGGER.error("Error converting Map to Map<String, String> in convertToMapStrStr method.", e);
            throw new RuntimeException("Error converting Map: ", e);
        }
        return mapStrStr;
    }

    /**
     * Converts a Cucumber DataTable into a HashMap.
     * <p>
     * This method processes a DataTable with either one or two columns.
     * For each row in the DataTable:
     * <ul>
     *     <li>If the row has two columns, it treats the first column as the key and the second column as the value.</li>
     *     <li>If the row has only one column, it treats the column as the key and assigns a null value.</li>
     * </ul>
     * <p>
     * An IllegalArgumentException is thrown if any row in the DataTable is found to be empty.
     *
     * @param dataTable The DataTable to be converted, typically obtained from a Cucumber step definition.
     * @return A HashMap where each row of the DataTable is represented as a key-value pair. Empty HashMap if data table is null.
     * @throws IllegalArgumentException if any row in the DataTable is empty.
     */
    public static Map<String, Object> dataTableToMap(DataTable dataTable) {
        if (dataTable == null) {
            LOGGER.warn("Data table is null. Returning empty Map<String, Object>.");
            return new HashMap<>();
        }
        List<List<String>> rows = dataTable.asLists(String.class);
        LOGGER.debug("Rows of data table: " + rows);
        Map<String, Object> map = new HashMap<>();

        for (List<String> row : rows) {
            if (row.size() >= 2) {
                // Two columns: key and value
                map.put(row.get(0), row.get(1));
            } else if (row.size() == 1) {
                // Only one column: key, so set value to null
                map.put(row.get(0), null);
            } else {
                LOGGER.error("Row in the DataTable is empty");
                throw new IllegalArgumentException("Row in the DataTable is empty");
            }
        }

        return map;
    }

    /**
     * Updates the provided map based on the specified mode.
     *
     * <ul>
     *   <li><b>set:</b> Directly sets the provided map, replacing any existing data.</li>
     *   <li><b>update:</b> Merges the provided map with any existing data in the ThreadLocal map.</li>
     *   <li><b>delete:</b> Removes the keys in the provided map from the ThreadLocal map.</li>
     * </ul>
     *
     * @param mode        The update mode, either "set", "update", or "delete".
     * @param inputMap    The Map containing new data.
     * @param mapToUpdate The Map to update.
     * @return Updated map.
     * @throws IllegalArgumentException If an unsupported mode is provided.
     */
    public static Map<String, String> updateMap(String mode, Map<String, String> inputMap, Map<String, String> mapToUpdate) {
        logErrorAndThrowIfNull(LOGGER, "Mode", mode);
        if (mapToUpdate == null) {
            LOGGER.debug("Map to update is null. Initializing new HashMap.");
            mapToUpdate = new HashMap<>();
        }
        Map<String, String> updatedMap = new HashMap<>(mapToUpdate);
        if (inputMap == null) {
            LOGGER.debug("Input Map is null. No changes made to Map to update.");
            return updatedMap;
        }

        switch (mode.trim().toLowerCase()) {
            case "set":
                updatedMap.clear();
                updatedMap.putAll(inputMap);
                break;
            case "update":
                updatedMap.putAll(inputMap);
                break;
            case "delete":
                inputMap.keySet().forEach(updatedMap::remove);
                break;
            default:
                LOGGER.error("Unsupported mode argument for updateMap method: " + mode);
                throw new IllegalArgumentException("Unsupported mode: " + mode);
        }
        return updatedMap;
    }
}
