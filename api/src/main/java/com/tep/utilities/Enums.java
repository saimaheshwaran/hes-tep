package com.tep.utilities;

public class Enums {

    public enum Comparison_Type {
        EQUAL,
        NOT_EQUAL,
        CONTAIN,
        NOT_CONTAIN,
        HAS_ITEM,
        NOT_HAS_ITEM,
        GREATER_THAN,
        LESS_THAN;
    }
    public enum Manipulation_Mode {
        SET,
        UPDATE,
        DELETE
    }
    /**
     * Private constructor to prevent instantiation.
     */
    private Enums() {
        throw new UnsupportedOperationException("ApiConstants is a utility class and cannot be instantiated.");
    }
}
