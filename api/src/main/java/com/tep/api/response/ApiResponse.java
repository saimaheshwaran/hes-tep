package com.tep.api.response;

import com.tep.utilities.Enums;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.tep.utilities.ExceptionUtils.logErrorAndThrowIfNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Class for handling API responses, validating fields, and comparing values within the response.
 */
public class ApiResponse {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);
    private Response response;
    private final double delta = 0.0001;

    /**
     * Default constructor for ApiResponse.
     */
    public ApiResponse() {}

    /**
     * Constructor that initializes the ApiResponse with a given RestAssured Response.
     *
     * @param response The API response from RestAssured.
     */
    public ApiResponse(Response response) {
        this.response = response;
    }

    /**
     * Gets the current API response.
     *
     * @return The current API response.
     */
    public Response get() {
        return response;
    }

    /**
     * Sets the API response.
     *
     * @param response The API response to set.
     */
    public void set(Response response) {
        this.response = response;
    }

    /**
     * Returns the schema of the response.
     *
     * @return ApiSchema instance for the current response, or null if the response is null.
     */
    public ApiSchema schema() {
        return (response == null) ? null : new ApiSchema(response);
    }

    /**
     * Validates a string field in the response body based on a given path and comparison type.
     *
     * @param path       The JSON path to the field in the response.
     * @param comparison The type of comparison (e.g., equal, not equal, contain).
     * @param expected   The expected value to compare against.
     */
    public void validateStringFieldByPath(String path, Enums.Comparison_Type comparison, String expected) {
        logErrorAndThrowIfNull(logger, "Path", path);
        logErrorAndThrowIfNull(logger, "Comparison", comparison);
        logErrorAndThrowIfNull(logger, "Expected String", expected);
        //comparison = comparison.trim().toLowerCase();

        // Retrieve the response
        ValidatableResponse response = this.response.then();
        switch (comparison) {
            case Enums.Comparison_Type.EQUAL -> response.body(path, equalTo(expected));
            case Enums.Comparison_Type.NOT_EQUAL -> response.body(path, not(equalTo(expected)));
            case Enums.Comparison_Type.CONTAIN -> response.body(path, containsString(expected));
            case Enums.Comparison_Type.NOT_CONTAIN -> response.body(path, not(containsString(expected)));
            case Enums.Comparison_Type.HAS_ITEM -> response.body(path, hasItem(expected));
            case Enums.Comparison_Type.NOT_HAS_ITEM -> response.body(path, not(hasItem(expected)));
            default -> {
                logger.error(String.format("Invalid comparison type '%s' in validateResponseStringFieldByPath.", comparison));
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates a boolean field in the response body based on a given path and comparison type.
     *
     * @param path       The JSON path to the field in the response.
     * @param comparison The type of comparison (e.g., equal, not equal).
     * @param expected   The expected boolean value to compare against.
     */
    public void validateBooleanFieldByPath(String path, Enums.Comparison_Type comparison, boolean expected) {
        // Validate input parameters
        logErrorAndThrowIfNull(logger, "Path", path);
        logErrorAndThrowIfNull(logger, "Comparison", comparison);
        //comparison = comparison.trim().toLowerCase();

        // Retrieve the response
        ValidatableResponse response = this.response.then();
        switch (comparison) {
            case Enums.Comparison_Type.EQUAL -> response.body(path, equalTo(expected));
            case Enums.Comparison_Type.NOT_EQUAL -> response.body(path, not(equalTo(expected)));
            case Enums.Comparison_Type.HAS_ITEM -> response.body(path, hasItem(expected));
            case Enums.Comparison_Type.NOT_HAS_ITEM -> response.body(path, not(hasItem(expected)));
            default -> {
                logger.error("Invalid comparison type '{}' in validateBooleanFieldByPath.", comparison);
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates a numeric field in the response body based on a given path and comparison type.
     *
     * @param path       The JSON path to the field in the response.
     * @param comparison The type of comparison (e.g., equal, greater than, less than).
     * @param expected   The expected numeric value to compare against.
     */
    public void validateNumericFieldByPath(String path, Enums.Comparison_Type comparison, Number expected) {
        // Validate input parameters
        logErrorAndThrowIfNull(logger, "Path", path);
        logErrorAndThrowIfNull(logger, "Comparison", comparison);
        logErrorAndThrowIfNull(logger, "Expected Number", expected);
        //comparison = comparison.trim().toLowerCase();

        switch (comparison) {
            case Enums.Comparison_Type.EQUAL -> compareValues(path, response, expected, equalTo(expected));
            case Enums.Comparison_Type.NOT_EQUAL -> compareValues(path, response, expected, not(equalTo(expected)));
            case Enums.Comparison_Type.GREATER_THAN-> {
                Double actualDoubleGT = ((Number) response.path(path)).doubleValue();
                assertThat("Value at " + path, actualDoubleGT, greaterThan(expected.doubleValue()));
            }
            case Enums.Comparison_Type.LESS_THAN -> {
                Double actualDoubleLT = ((Number) response.path(path)).doubleValue();
                assertThat("Value at " + path, actualDoubleLT, lessThan(expected.doubleValue()));
            }
            case Enums.Comparison_Type.HAS_ITEM, Enums.Comparison_Type.NOT_HAS_ITEM -> handleMixedList(path, response, expected, comparison);
            default -> {
                logger.error(String.format("Invalid comparison type '%s' in validateResponseNumericFieldByPath.", comparison));
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates the response body as a text field against an expected value.
     *
     * @param comparison The type of comparison (e.g., equal, not equal, contain).
     * @param expected   The expected text to compare against.
     */
    public void validateText(Enums.Comparison_Type comparison, String expected) {
        // Validate input parameters
        logErrorAndThrowIfNull(logger, "Comparison", comparison);
        logErrorAndThrowIfNull(logger, "Expected text", expected);
        //comparison = comparison.trim().toLowerCase();

        switch (comparison) {
            case Enums.Comparison_Type.EQUAL:
                response.then().body(equalTo(expected));
                break;
            case Enums.Comparison_Type.NOT_EQUAL:
                response.then().body(not(equalTo(expected)));
                break;
            case Enums.Comparison_Type.CONTAIN:
                response.then().body(containsString(expected));
                break;
            case Enums.Comparison_Type.NOT_CONTAIN:
                response.then().body(not(containsString(expected)));
                break;
            default:
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
        }
    }

    /**
     * Compares the actual value in the response with the expected value for numeric fields.
     *
     * @param path          The JSON path to the numeric field.
     * @param response      The API response.
     * @param expectedValue The expected numeric value.
     * @param matcher       The matcher to use for comparison.
     */
    private void compareValues(String path, Response response, Number expectedValue, Matcher<? super Number> matcher) {
        logErrorAndThrowIfNull(logger, "Path", path);
        logErrorAndThrowIfNull(logger, "Response", response);
        logErrorAndThrowIfNull(logger, "Expected Value", expectedValue);
        logErrorAndThrowIfNull(logger, "Matcher", matcher);

        Number actualValue = response.path(path);
        if (actualValue == null) {
            logger.error("No value found at path: " + path);
            throw new RuntimeException("No value found at path: " + path);
        }

        if (expectedValue instanceof Double || expectedValue instanceof Float) {
            if (matcher instanceof IsNot) {
                assertThat("Value at " + path, actualValue.doubleValue(), not(closeTo(expectedValue.doubleValue(), delta)));
            } else {
                assertThat("Value at " + path, actualValue.doubleValue(), closeTo(expectedValue.doubleValue(), delta));
            }
        } else {
            assertThat("Value at " + path, actualValue, matcher);
        }
    }

    /**
     * Compares the value at the given path to see if it is greater than the expected value.
     *
     * @param path       The path to the field in the response.
     * @param expected   The expected value.
     */
    private void compareGreaterThan(String path, Number expected) {
        Double actualValue = response.path(path);
        assertThat("Value at " + path, actualValue, greaterThan(expected.doubleValue()));
    }

    /**
     * Compares the value at the given path to see if it is less than the expected value.
     *
     * @param path       The path to the field in the response.
     * @param expected   The expected value.
     */
    private void compareLessThan(String path, Number expected) {
        Double actualValue = response.path(path);
        assertThat("Value at " + path, actualValue, lessThan(expected.doubleValue()));
    }

    /**
     * Handles a case where the response field is a list and checks if the list contains the expected item.
     *
     * @param path         The JSON path to the list in the response.
     * @param response     The API response.
     * @param expectedValue The value to search for in the list.
     * @param comparisonType The comparison type (have item or not have item).
     */
    private void handleMixedList(String path, Response response, Number expectedValue, Enums.Comparison_Type comparisonType) {
        logErrorAndThrowIfNull(logger, "Path", path);
        logErrorAndThrowIfNull(logger, "Response", response);
        logErrorAndThrowIfNull(logger, "Expected Value", expectedValue);
        logErrorAndThrowIfNull(logger, "Comparison Type", comparisonType);

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

        if (comparisonType.equals(Enums.Comparison_Type.HAS_ITEM)) {
            assertThat("List at " + path + " does not have the expected item", hasItem, is(true));
        } else if (comparisonType.equals(Enums.Comparison_Type.NOT_HAS_ITEM)) {
            assertThat("List at " + path + " should not have the item", hasItem, is(false));
        }
    }

    /**
     * Checks if the expected number matches the actual number.
     *
     * @param expected The expected number.
     * @param actual   The actual number.
     * @return true if the numbers match, false otherwise.
     */
    private boolean isExpectedNumber(Number expected, Number actual) {
        logErrorAndThrowIfNull(logger, "Expected Number", expected);
        logErrorAndThrowIfNull(logger, "Actual Number", actual);

        if (expected instanceof Double || expected instanceof Float) {
            return Math.abs(expected.doubleValue() - actual.doubleValue()) <= delta;
        } else {
            return expected.longValue() == actual.longValue();
        }
    }

    /**
     * Validates the response body against a given JSON file.
     *
     * @param filePath The file path to the expected JSON file.
     */
    public void validateResponseJsonAgainstExpectedJsonFile(String filePath) {
        logErrorAndThrowIfNull(logger, "Expected file path in validateJSONResponseAgainstExpectedJSONFile", filePath);
        String expectedJson = null;
        try {
            expectedJson = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            logger.error("File '{}' was not found.", filePath);
            throw new RuntimeException(e);
        }
        validateResponseJsonAgainstExpectedJsonString(expectedJson);
    }

    /**
     * Validates the response body against a given JSON string.
     *
     * @param expectedJson The expected JSON string.
     */
    public void validateResponseJsonAgainstExpectedJsonString(String expectedJson) {
        logErrorAndThrowIfNull(logger, "Expected JSON in validateJSONResponseAgainstExpectedJSONString", expectedJson);

        String actualJson = response.body().asString();
        logErrorAndThrowIfNull(logger, "Actual JSON in validateJSONResponseAgainstExpectedJSONString", actualJson);

        try {
            JSONAssert.assertEquals(expectedJson, actualJson, false);
        } catch (AssertionError ae) {
            String message = "\n" + Arrays.stream(ae.getMessage().split(";"))
                    .map(String::trim)
                    .collect(Collectors.joining("\n\n"));
            logger.error("Response JSON does not match expected JSON. {}", message);
            throw new AssertionError(message, ae);
        } catch (JSONException e) {
            logger.error("JSON Exception. {}", e.getMessage());

        }
    }
}
