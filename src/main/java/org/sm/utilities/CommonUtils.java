package org.sm.utilities;

import java.util.List;
import java.util.Map;

public class CommonUtils {
    private CommonUtils() {
    }

    /**
     * Checks if a given string is not null and not empty.
     *
     * @param str the string to check
     * @return true if the string is not null and not empty, false otherwise
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Checks if a given map is not null and not empty.
     *
     * @param map the map to check
     * @return true if the map is not null and not empty, false otherwise
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Checks if any of the provided values is {@code null} or an empty string.
     * <p>
     * This method iterates through each element in the varargs parameter {@code values}.
     * If an element is {@code null}, or if it is a {@code String} and is empty (has a length of zero),
     * the method returns {@code true}. If all elements are non-null and non-empty strings,
     * the method returns {@code false}.
     * </p>
     *
     * @param values The array of {@code Object} instances to check, which may contain any type of object.
     * @return {@code true} if at least one element is {@code null} or an empty string, {@code false} otherwise.
     */
    public static boolean containsNullOrEmptyString(Object... values) {
        for (Object value : values) {
            if (value == null) {
                return true; // Found a null value
            }
            if (value instanceof String && ((String) value).isEmpty()) {
                return true; // Found an empty String
            }
        }
        return false; // No null or empty String values found
    }
}
