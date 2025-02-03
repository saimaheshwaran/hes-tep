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
     * Converts a map with keys and values of any types to a map with keys and values of type String.
     * The conversion is done by calling the `String.valueOf` method on both keys and values of the input map.
     *
     * @param inputMap The input map with keys and values of any type. If null, an empty Map<String, String> is returned.
     * @return A new Map<String, String> with the keys and values converted to Strings.
     *         If the input map is null, an empty Map<String, String> is returned.
     * @throws RuntimeException If an error occurs during the conversion process. The original exception is included as the cause.
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
     * Converts a DataTable object into a Map by mapping the first column as keys and the second column as values.
     * If a row contains only one column, the value is set to null. If a row is empty, an exception is thrown.
     *
     * @param dataTable The DataTable to convert. Must not be null. Each row should contain at least one column.
     *                  The first column is treated as the key, and the second column (if present) is treated as the value.
     * @return A Map<String, Object> representing the rows of the DataTable. If the DataTable is null, an empty Map is returned.
     * @throws IllegalArgumentException If a row in the DataTable is empty.
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
     * Updates a map based on the specified manipulation mode and input data.
     * The method can set, update, or delete entries in the map to update.
     *
     * @param mode       the manipulation mode that determines how the map should be updated.
     *                   The mode should be one of the following:
     *                   - SET: Clears the map to update and sets it to the input map.
     *                   - UPDATE: Adds all entries from the input map to the map to update.
     *                            Existing keys will have their values overwritten.
     *                   - DELETE: Removes all entries in the map to update that have keys
     *                             present in the input map.
     * @param inputMap   the map containing the data to be used for updating the map to update.
     *                   If null, no changes will be made to the map to update.
     * @param mapToUpdate the map to be updated. If null, a new HashMap will be initialized.
     * @return a new {@link Map} instance containing the updated entries.
     * @throws NullPointerException if the mode is null.
     * @throws IllegalArgumentException if the mode is not supported.
     */
    public static Map<String, String> updateMap(Enums.Manipulation_Mode mode, Map<String, String> inputMap, Map<String, String> mapToUpdate) {
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

        switch (mode) {
            case SET:
                updatedMap.clear();
                updatedMap.putAll(inputMap);
                break;
            case UPDATE:
                updatedMap.putAll(inputMap);
                break;
            case DELETE:
                inputMap.keySet().forEach(updatedMap::remove);
                break;
            default:
                LOGGER.error("Unsupported mode argument for updateMap method: " + mode);
                throw new IllegalArgumentException("Unsupported mode: " + mode);
        }
        return updatedMap;
    }
}
