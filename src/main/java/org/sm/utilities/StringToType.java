package org.sm.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility provides capability to convert string representation of a value to a datatype,
 * specified as suffix.
 *
 */
public class StringToType {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private StringToType() {

    }

    /**
     * Parse the string value into a data type according to suffix.
     *
     * @param typedValue suffix like :string, :bool, :int, :float, :long, :null, :json to indicate data type.
     * @return value of supplied suffix type or string if no suffix.
     */
    public static Object parseValue(String typedValue) {
        if (typedValue.endsWith(":string") || typedValue.endsWith(":str")) {
            return typedValue.substring(0, typedValue.lastIndexOf(":"));
        } else if (typedValue.endsWith(":bool") || typedValue.endsWith(":boolean")) {
            return Boolean.parseBoolean(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":int") || typedValue.endsWith(":integer")) {
            return Integer.parseInt(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":float") || typedValue.endsWith(":double") || typedValue.endsWith(":decimal")) {
            return Double.parseDouble(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":long")) {
            return Long.parseLong(typedValue.substring(0, typedValue.lastIndexOf(":")));
        } else if (typedValue.endsWith(":null")) {
            return null;
        } else if (typedValue.endsWith(":json")) {
            String json = typedValue.substring(0, typedValue.lastIndexOf(":json"));
            try {
                return OBJECT_MAPPER.readTree(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Default to string if no type indicator is provided
            return typedValue;
        }
    }
}
