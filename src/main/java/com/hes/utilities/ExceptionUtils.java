package com.hes.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * The {@code ExceptionUtils} class provides utility methods for logging errors and throwing exceptions.
 * This class cannot be instantiated.
 */
public class ExceptionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ExceptionUtils() {
        // Prevent instantiation
    }

    /**
     * Logs an error and throws an {@code IllegalArgumentException} if the provided object is null.
     *
     * @param objectToCheck The object to check for nullity.
     * @throws IllegalArgumentException if the object is null.
     */
    public static void logErrorAndThrowIfNull(Object objectToCheck) {
        logErrorAndThrowIfNull("Unnamed object", objectToCheck);
    }

    /**
     * Logs an error and throws an {@code IllegalArgumentException} if the provided object is null.
     *
     * @param objName       The name of the object to be used in the error message.
     * @param objectToCheck The object to check for nullity.
     * @throws IllegalArgumentException if the object is null.
     */
    public static void logErrorAndThrowIfNull(String objName, Object objectToCheck) {
        if (Objects.isNull(objectToCheck)) {
            LOGGER.error("{} cannot be null.", objName);
            throw new IllegalArgumentException(objName + " is null.");
        }
    }

    /**
     * Logs an error and throws an {@code IllegalArgumentException} if the provided object is null.
     *
     * @param logger        The logger to use for logging the error.
     * @param objName       The name of the object to be used in the error message.
     * @param objectToCheck The object to check for nullity.
     * @throws IllegalArgumentException if the object is null.
     */
    public static void logErrorAndThrowIfNull(Logger logger, String objName, Object objectToCheck) {
        if (Objects.isNull(objectToCheck)) {
            logger.error("{} cannot be null.", objName);
            throw new IllegalArgumentException(objName + " is null.");
        }
    }

    /**
     * Logs an error and throws a runtime exception provided by the exception supplier.
     *
     * @param logger            The logger to use for logging the error.
     * @param message           The error message to log.
     * @param exceptionSupplier The supplier that provides the runtime exception to be thrown.
     * @throws RuntimeException the runtime exception provided by the supplier.
     */
    public static void logErrorAndThrow(Logger logger, String message, Supplier<RuntimeException> exceptionSupplier) {
        RuntimeException exception = exceptionSupplier.get();
        logErrorAndThrow(logger, message, exception);
    }

    /**
     * Logs an error and throws a runtime exception provided by the exception supplier.
     *
     * @param exceptionSupplier The supplier that provides the runtime exception to be thrown.
     * @throws RuntimeException the runtime exception provided by the supplier.
     */
    public static void logErrorAndThrow(Supplier<RuntimeException> exceptionSupplier) {
        logErrorAndThrow(LOGGER, "", exceptionSupplier);
    }

    /**
     * Logs an error and throws a runtime exception.
     *
     * @param logger   The logger to use for logging the error.
     * @param message  The error message to log.
     * @param exception The exception to be thrown.
     * @throws RuntimeException the runtime exception.
     */
    public static void logErrorAndThrow(Logger logger, String message, Exception exception) {
        logger.error("{}: {}", message, exception.getMessage(), exception);
        throw new RuntimeException(message, exception);
    }
}
