package org.sm.api.response;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.DriverApi;
import org.sm.api.config.RestConfigConstants;
import org.sm.utilities.FileUtils;

import java.sql.Driver;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.sm.api.support.PayloadValidationsHelpers.compareValues;
import static org.sm.api.support.PayloadValidationsHelpers.handleMixedList;
import static org.sm.utilities.ExceptionUtils.logErrorAndThrowIfNull;

public class PayloadValidations {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadValidations.class);

    private PayloadValidations() {
    }

    /**
     * Validates the string value or list of string values of a specified JSON or XML field in the response body against an expected value or item.
     * The comparison type determines how the actual value should be compared to the expected value or whether an item is present in the list.
     *
     * @param path       the JSON or XML path to the field or list to be validated (e.g., "data.name" for JSON or "//data/name" for XML for a single value,
     *                   or "data.names[]" for JSON or "//data/names/name" for XML for an array/list of values).
     * @param comparison the type of comparison to perform ("equal", "not equal", "contain", "not contain", "have item", "not have item")
     * @param expected   the expected string value or an item expected to be in the list
     * @throws IllegalArgumentException if the comparison type is not one of the expected values
     */
    public static void validateResponseStringFieldByPath(String path, String comparison, String expected) {
        logErrorAndThrowIfNull(LOGGER, "Path", path);
        logErrorAndThrowIfNull(LOGGER, "Comparison", comparison);
        logErrorAndThrowIfNull(LOGGER, "Expected String", expected);
        comparison = comparison.trim().toLowerCase();

        // Retrieve the response
        ValidatableResponse response = DriverApi.getInstance().getResponse().then();
        switch (comparison) {
            case "equal" -> response.body(path, equalTo(expected));
            case "not equal" -> response.body(path, not(equalTo(expected)));
            case "contain" -> response.body(path, containsString(expected));
            case "not contain" -> response.body(path, not(containsString(expected)));
            case "have item" -> response.body(path, hasItem(expected));
            case "not have item" -> response.body(path, not(hasItem(expected)));
            default -> {
                LOGGER.error(String.format("Invalid comparison type '%s' in validateResponseStringFieldByPath.", comparison));
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates the boolean value or list of boolean values of a specified JSON or XML field in the response body against an expected value.
     * The comparison type determines how the actual value should be compared to the expected value, or whether an item is present in the list.
     *
     * @param path       the JSON or XML path to the field or list to be validated (e.g., "data.active" for JSON or "//data/active" for XML for a single value,
     *                   or "data.flags[]" for JSON or "//data/flags/flag" for XML for an array/list of values).
     * @param comparison the type of comparison to perform ("equal", "not equal", "have item", "not have item")
     * @param expected   the expected boolean value, or an item expected to be in the list if "have item" or "not have item" is used
     * @throws IllegalArgumentException if the comparison type is not one of the expected values or is not properly applicable to the data type
     */
    public static void validateResponseBooleanFieldByPath(String path, String comparison, boolean expected) {
        logErrorAndThrowIfNull(LOGGER, "Path", path);
        logErrorAndThrowIfNull(LOGGER, "Comparison", comparison);
        comparison = comparison.trim().toLowerCase();
        // Retrieve the response
        ValidatableResponse response = DriverApi.getInstance().getResponse().then();
        switch (comparison) {
            case "equal" -> response.body(path, equalTo(expected));
            case "not equal" -> response.body(path, not(equalTo(expected)));
            case "have item" -> response.body(path, hasItem(expected));
            case "not have item" -> response.body(path, not(hasItem(expected)));
            default -> {
                LOGGER.error(String.format("Invalid comparison type '%s' in validateResponseBooleanFieldByPath.", comparison));
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates the numeric value or list of numeric values of a specified JSON or XML field in the response body against an expected value.
     * The comparison type determines how the actual value should be compared to the expected value, or whether an item is present in the list.
     *
     * @param path       the JSON or XML path to the field or list to be validated (e.g., "data.count" for JSON or "//data/count" for XML for a single value,
     *                   or "data.counts[]" for JSON or "//data/counts/count" for XML for an array/list of values).
     * @param comparison the type of comparison to perform ("equal", "not equal", "have item", "not have item", "be greater than", "be less than")
     * @param expected   the expected integer value, or an item expected to be in the list if "have item" or "not have item" is used
     * @throws IllegalArgumentException if the comparison type is not one of the expected values or is not properly applicable to the data type
     */
    public static void validateResponseNumericFieldByPath(String path, String comparison, Number expected) {
        logErrorAndThrowIfNull(LOGGER, "Path", path);
        logErrorAndThrowIfNull(LOGGER, "Comparison", comparison);
        logErrorAndThrowIfNull(LOGGER, "Expected Number", expected);
        comparison = comparison.trim().toLowerCase();

        // Retrieve the response
        Response response = DriverApi.getInstance().getResponse();

        switch (comparison) {
            case "equal" -> compareValues(path, response, expected, equalTo(expected));
            case "not equal" -> compareValues(path, response, expected, not(equalTo(expected)));
            case "be greater than" -> {
                Double actualDoubleGT = ((Number) response.path(path)).doubleValue();
                assertThat("Value at " + path, actualDoubleGT, greaterThan(expected.doubleValue()));
            }
            case "be less than" -> {
                Double actualDoubleLT = ((Number) response.path(path)).doubleValue();
                assertThat("Value at " + path, actualDoubleLT, lessThan(expected.doubleValue()));
            }
            case "have item", "not have item" -> handleMixedList(path, response, expected, comparison);
            default -> {
                LOGGER.error(String.format("Invalid comparison type '%s' in validateResponseNumericFieldByPath.", comparison));
                throw new IllegalArgumentException("Invalid comparison type: " + comparison);
            }
        }
    }

    /**
     * Validates the text of a response based on a specified comparison type and expected value.
     * <p>
     * This method compares the actual text response from NGTPAPI with the provided expected string.
     * Supported comparison types include:
     * <ul>
     *     <li>"equal": Checks if the response text is equal to the expected value.</li>
     *     <li>"not equal": Checks if the response text is not equal to the expected value.</li>
     *     <li>"contain": Checks if the response text contains the expected value.</li>
     *     <li>"not contain": Checks if the response text does not contain the expected value.</li>
     * </ul>
     * An {@link IllegalArgumentException} is thrown for invalid comparison types.
     *
     * @param comparison The type of comparison to perform. Valid values are "equal", "not equal", "contain", and "not contain".
     * @param expected   The expected string value to be compared against the response text.
     * @throws IllegalArgumentException if an invalid comparison type is provided.
     */
    public static void validateResponseText(String comparison, String expected) {
        logErrorAndThrowIfNull(LOGGER, "Comparison", comparison);
        logErrorAndThrowIfNull(LOGGER, "Expected text", expected);
        comparison = comparison.trim().toLowerCase();

        // Retrieve the response
        ValidatableResponse response = DriverApi.getInstance().getResponse().then();

        switch (comparison) {
            case "equal" -> response.body(equalTo(expected));
            case "not equal" -> response.body(not(equalTo(expected)));
            case "contain" -> response.body(containsString(expected));
            case "not contain" -> response.body(not(containsString(expected)));
            default -> throw new IllegalArgumentException("Invalid comparison type: " + comparison);
        }
    }

    /**
     * Validates the JSON content of a REST API response against a specified JSON file.
     * This method reads the expected JSON structure from a file and compares it
     * with the actual JSON response from the REST API. The comparison ignores
     * the order of elements in JSON arrays and does not consider any extra fields
     * in the actual response.
     *
     * @param filePath The relative path to the JSON file containing the expected response.
     *                 This path is relative to the predefined application data path,
     *                 and is expected to be in the 'expected' subfolder.
     *                 For example, if the expected JSON file is located at
     *                 'APPLICATION_DATA_PATH/expected/myExpectedResponse.json',
     *                 the parameter should be 'myExpectedResponse.json'.
     */
    public static void validateJSONResponseAgainstExpectedJSONFile(String filePath) {
        logErrorAndThrowIfNull(LOGGER, "Expected file path in validateJSONResponseAgainstExpectedJSONFile", filePath);
        String expectedFilePath = filePath; //RestConfigConstants.APPLICATION_PATH + filePath;
        String expected = FileUtils.readFileToString(expectedFilePath);
        validateJSONResponseAgainstExpectedJSONString(expected);
    }

    /**
     * Validates the JSON response against an expected JSON string.
     * <p>
     * This method compares the actual JSON response from NGTPAPI with the provided expected JSON string.
     * It uses {@link JSONAssert#assertEquals(String, String, boolean)} for comparison,
     * which allows non-strict checking (ignoring extra fields in the actual JSON).
     * <p>
     * In case of a mismatch, it formats the error message for better readability,
     * logs the error, and throws an {@link AssertionError}.
     *
     * @param expected The expected JSON string to compare against the actual response.
     * @throws AssertionError if the actual JSON response does not match the expected JSON string.
     *                        The error message contains a detailed comparison result.
     */
    public static void validateJSONResponseAgainstExpectedJSONString(String expected) {
        logErrorAndThrowIfNull(LOGGER, "Expected String in validateJSONResponseAgainstExpectedJSONString", expected);

        String actual = DriverApi.getInstance().getResponse().body().asString();
        logErrorAndThrowIfNull(LOGGER, "Actual String in validateJSONResponseAgainstExpectedJSONString", actual);

        try {
            JSONAssert.assertEquals(expected, actual, false);
        } catch (AssertionError ae) {
            String message = "\n" + Arrays.stream(ae.getMessage().split(";"))
                    .map(String::trim)
                    .collect(Collectors.joining("\n\n"));
            LOGGER.error("Response JSON does not match expected JSON. {}", message);
            throw new AssertionError(message, ae);
        } catch (JSONException e) {
            LOGGER.error("JSON Exception. {}", e.getMessage());
        }
    }
}
