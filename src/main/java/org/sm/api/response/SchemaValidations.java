package org.sm.api.response;

import io.restassured.module.jsv.JsonSchemaValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sm.api.DriverApi;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.sm.utilities.ExceptionUtils.logErrorAndThrow;
import static org.sm.utilities.ExceptionUtils.logErrorAndThrowIfNull;

public class SchemaValidations {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaValidations.class);

    private SchemaValidations() {}

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
