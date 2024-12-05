package com.tep.api.response;

import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tep.utilities.ExceptionUtils.logErrorAndThrow;
import static com.tep.utilities.ExceptionUtils.logErrorAndThrowIfNull;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Class for handling API response schema validation against a JSON schema.
 */
public class ApiSchema {

    private static final Logger logger = LoggerFactory.getLogger(ApiSchema.class);
    private Response response;

    /**
     * Constructor that initializes ApiSchema with a given RestAssured Response.
     *
     * @param response The API response from RestAssured to be validated.
     */
    public ApiSchema(Response response) {
        this.response = response;
    }

    /**
     * Validates the response body against the specified JSON schema file.
     *
     * @param filePath The file path to the expected JSON schema file.
     * @throws AssertionError if the response does not match the schema.
     * @throws IllegalArgumentException if the file path is incorrect or invalid.
     * @throws JsonSchemaValidationException if there is a schema validation error.
     */
    public void validateJsonSchema(String filePath) {
        // Ensure the response and file path are not null
        logErrorAndThrowIfNull("ApiSchema.response cannot be null. Please set value to ApiSchema.response", response);
        logErrorAndThrowIfNull("Schema file path", filePath);

        logger.info("Loading schema from file: {}", filePath);

        try {
            // Validate the response body against the schema in the specified file path
            response.then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(filePath));
        } catch (AssertionError ae) {
            // Log and throw error if schema validation fails
            logger.error("Response does not match the schema at '{}'. Error: {}", filePath, ae.getMessage());
            throw new AssertionError(ae);
        } catch (IllegalArgumentException ie) {
            // Handle invalid file path or schema issues
            logErrorAndThrow(logger, String.format("The schema file path '%s' is invalid or does not exist.", filePath), ie);
        } catch (JsonSchemaValidationException jsve) {
            // Handle JSON schema validation errors
            logErrorAndThrow(logger, "Unsupported Schema Validation in ApiDriver.", jsve);
        }
    }
}
