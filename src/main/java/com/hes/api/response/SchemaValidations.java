package com.hes.api.response;

import io.restassured.module.jsv.JsonSchemaValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hes.api.DriverApi;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static com.hes.utilities.ExceptionUtils.logErrorAndThrow;
import static com.hes.utilities.ExceptionUtils.logErrorAndThrowIfNull;

/**
 * The {@code SchemaValidations} class provides methods for validating JSON schemas in REST API responses.
 */
public class SchemaValidations {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaValidations.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SchemaValidations() {
        // Prevent instantiation
    }

    /**
     * Validates the JSON schema of the REST API response against a specified schema file.
     * <p>
     * This method loads the JSON schema from the classpath and validates the response body against it.
     * If the response does not match the schema, an {@link AssertionError} is thrown.
     * <p>
     * In case of an invalid schema path or unsupported schema validation, appropriate exceptions are logged and thrown.
     *
     * @param filePath The relative path to the JSON schema file in the classpath.
     * @throws AssertionError if the response does not match the schema.
     * @throws IllegalArgumentException if the schema path is invalid.
     * @throws JsonSchemaValidationException if schema validation is unsupported.
     */
    public static void validateJsonSchema(String filePath) {
        logErrorAndThrowIfNull("Schema file path", filePath);
        String pathToSchema = filePath; //RestConfigConstants.APPLICATION_CLASSPATH + filePath;
        LOGGER.info("Loading schema at: {}", pathToSchema);
        try {
            DriverApi.getInstance().getResponse().then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(pathToSchema));
        } catch (AssertionError ae) {
            LOGGER.error("Response does not match the schema at '{}'. {}", pathToSchema, ae.getMessage());
            throw new AssertionError(ae);
        } catch (IllegalArgumentException ie) {
            logErrorAndThrow(LOGGER, String.format("The path '%s' does not exist on class path", pathToSchema), ie);
        } catch (JsonSchemaValidationException jsve) {
            logErrorAndThrow(LOGGER, "DriverAPI Unsupported Schema Validation", jsve);
        }
    }
}

