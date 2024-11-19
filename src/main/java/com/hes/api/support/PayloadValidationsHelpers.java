package com.hes.api.support;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.hes.utilities.ExceptionUtils.logErrorAndThrowIfNull;

public class PayloadValidationsHelpers {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadValidationsHelpers.class);
    private static final double DELTA = 0.0001;

    private PayloadValidationsHelpers() {
    }

    /**
     * Compares the numeric value at the specified path in the response with the expected value using the provided matcher.
     * This method handles both floating-point and non-floating-point numbers.
     *
     * @param path          The path in the response where the numeric value is located.
     * @param response      The response from which to extract the numeric value.
     * @param expectedValue The expected numeric value for comparison.
     * @param matcher       The Hamcrest matcher to use for the comparison.
     * @throws AssertionError if no value is found at the specified path.
     */
    public static void compareValues(String path, Response response, Number expectedValue, Matcher<? super Number> matcher) {
        logErrorAndThrowIfNull(LOGGER, "Path", path);
        logErrorAndThrowIfNull(LOGGER, "Response", response);
        logErrorAndThrowIfNull(LOGGER, "Expected Value", expectedValue);
        logErrorAndThrowIfNull(LOGGER, "Matcher", matcher);

        Number actualValue = response.path(path);
        if (actualValue == null) {
            LOGGER.error("No value found at path: " + path);
            throw new RuntimeException("No value found at path: " + path);
        }

        // Check if the expected value is a floating-point number
        if (expectedValue instanceof Double || expectedValue instanceof Float) {
            if (matcher instanceof IsNot) {
                // "not equal" comparison for floating-point numbers
                assertThat("Value at " + path, actualValue.doubleValue(), not(closeTo(expectedValue.doubleValue(), DELTA)));
            } else {
                // "equal" and other comparisons for floating-point numbers
                assertThat("Value at " + path, actualValue.doubleValue(), closeTo(expectedValue.doubleValue(), DELTA));
            }
        } else {
            // For non-floating point numbers, use the matcher directly with the Number type
            assertThat("Value at " + path, actualValue, matcher);
        }
    }

    /**
     * Handles comparison of a numeric value with items in a mixed-type list at the specified path in the response.
     * This method checks if the list contains (or does not contain) the expected numeric value.
     *
     * @param path           The path in the response where the list is located.
     * @param response       The response from which to extract the list.
     * @param expectedValue  The expected numeric value to compare against the list items.
     * @param comparisonType The type of comparison to perform ("have item" or "not have item").
     * @throws AssertionError if the list at the specified path is null.
     */
    public static void handleMixedList(String path, Response response, Number expectedValue, String comparisonType) {
        logErrorAndThrowIfNull(LOGGER, "Path", path);
        logErrorAndThrowIfNull(LOGGER, "Response", response);
        logErrorAndThrowIfNull(LOGGER, "Expected Value", expectedValue);
        logErrorAndThrowIfNull(LOGGER, "Comparison Type", comparisonType);

        List<?> actualList = response.path(path);
        if (actualList == null) {
            throw new AssertionError("List at path '" + path + "' is null.");
        }

        boolean hasItem = actualList.stream().anyMatch(item -> {
            if (item instanceof Number num) {
                return isExpectedNumber(expectedValue, num);
            }
            return false;
        });

        if (comparisonType.equals("have item")) {
            assertThat("List at " + path + " does not have the expected item", hasItem, is(true));
        } else if (comparisonType.equals("not have item")) {
            assertThat("List at " + path + " should not have the item", hasItem, is(false));
        }
    }

    /**
     * Determines if the actual number is considered equal to the expected number.
     * For floating-point numbers, this comparison allows a small delta to account for precision issues.
     *
     * @param expected The expected numeric value.
     * @param actual   The actual numeric value.
     * @return true if the numbers are considered equal, false otherwise.
     */
    public static boolean isExpectedNumber(Number expected, Number actual) {
        logErrorAndThrowIfNull(LOGGER, "Expected Number", expected);
        logErrorAndThrowIfNull(LOGGER, "Actual Number", actual);

        if (expected instanceof Double || expected instanceof Float) {
            return Math.abs(expected.doubleValue() - actual.doubleValue()) <= DELTA;
        } else {
            return expected.longValue() == actual.longValue();
        }
    }
}
