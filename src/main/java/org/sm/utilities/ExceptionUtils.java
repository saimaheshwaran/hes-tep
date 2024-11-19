package org.sm.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Supplier;

public class ExceptionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);

    private ExceptionUtils() {
    }

    /**
     * Throws an IllegalArgumentException if the provided object is null.
     * <p>
     * This method is a convenience overload of {@link #logErrorAndThrowIfNull(String, Object)}
     * for cases where the object name is not necessary in the exception message.
     *
     * @param objectToCheck The object to check for nullity.
     * @throws IllegalArgumentException if the object is null.
     */
    public static void logErrorAndThrowIfNull(Object objectToCheck) {
        logErrorAndThrowIfNull("Unnamed object", objectToCheck);
    }

    public static void logErrorAndThrowIfNull(String objName, Object objectToCheck) {
        if (Objects.isNull(objectToCheck)) {
            LOGGER.error("{} cannot be null.", objName);
            throw new IllegalArgumentException(objName + " is null.");
        }
    }

    /**
     * Throws an IllegalArgumentException if the provided object is null,
     * including a custom object name in the exception message.
     * <p>
     * This method is useful for validating method arguments and ensuring
     * they are not null, with a clear exception message identifying the null argument.
     *
     * @param objName       The name of the object, used in the exception message.
     * @param objectToCheck The object to check for nullity.
     * @throws IllegalArgumentException if the object is null,
     *                                  with a message indicating the provided object name.
     * @author Anrei Goriunov (Andrei.Goriunov@ey.com)
     */
    public static void logErrorAndThrowIfNull(Logger logger, String objName, Object objectToCheck) {
        if (Objects.isNull(objectToCheck)) {
            logger.error("{} cannot be null.", objName);
            throw new IllegalArgumentException(objName + " is null.");
        }
    }

    /**
     * Logs an error message and throws a runtime exception.
     * The runtime exception is provided by a {@link Supplier} which allows for flexible exception creation.
     * The message from the supplied exception is logged before the exception is thrown.
     *
     * @param logger            the {@link Logger} used to log the error message.
     * @param exceptionSupplier a {@link Supplier} that provides the {@link RuntimeException} to be thrown.
     *                          This supplier should instantiate the exception with the desired error message.
     * @throws RuntimeException the exception created and supplied by the {@code exceptionSupplier}.
     *                          This is re-thrown after logging its message.
     */
    public static void logErrorAndThrow(Logger logger, String message, Supplier<RuntimeException> exceptionSupplier) {
        RuntimeException exception = exceptionSupplier.get();
        logErrorAndThrow(logger, message, exception);
    }

    public static void logErrorAndThrow(Supplier<RuntimeException> exceptionSupplier) {
        logErrorAndThrow(LOGGER, "", exceptionSupplier);
    }

    /**
     * Logs an error message and throws the provided runtime exception.
     * The message from the exception is logged before the exception is thrown.
     *
     * @param logger    the {@link Logger} used to log the error message.
     * @param message   the additional message to log along with the exception's message.
     * @param exception the {@link RuntimeException} to be thrown.
     * @throws RuntimeException the exception provided as the {@code exception} parameter.
     *                          This is re-thrown after logging its message.
     * @author Anrei Goriunov (Andrei.Goriunov@ey.com)
     */
    public static void logErrorAndThrow(Logger logger, String message, Exception exception) {
        logger.error("{}: {}", message, exception.getMessage(), exception);
        throw new RuntimeException(message, exception);
    }
}
