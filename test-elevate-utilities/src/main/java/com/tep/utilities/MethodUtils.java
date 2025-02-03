package com.tep.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * The {@code MethodUtils} class provides utility methods for executing methods with retry mechanisms.
 * This class cannot be instantiated.
 */
public class MethodUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodUtils.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MethodUtils() {
        // Prevent instantiation
    }

    /**
     * Executes a given supplier function with a specified number of retry attempts and exponential backoff.
     * If the supplier function throws an exception, the method will retry the execution until the maximum
     * number of retries is reached or the function completes successfully.
     *
     * @param <T>           The type of the result provided by the supplier.
     * @param supplier      The supplier function to be executed. Must not be null.
     * @param maxRetries    The maximum number of retry attempts. Must be a non-negative integer.
     * @param maxBackoffMillis The maximum backoff time in milliseconds to wait between retries.
     *                         Must be a non-negative long value.
     * @return The result of the supplier function if execution is successful.
     * @throws RuntimeException If all retry attempts are exhausted and the supplier function
     *                          still fails, the last exception is rethrown wrapped in a RuntimeException.
     */
    public static <T> T executeWithRetry(Supplier<T> supplier, int maxRetries, long maxBackoffMillis) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                LOGGER.warn("Execution failure detected. Retrying... (" + (i + 1) + "/" + maxRetries + ")");
                if (i == maxRetries - 1) {
                    LOGGER.error("All execution retry attempts exhausted.", e);
                    throw new RuntimeException(e);  // rethrow the exception on the last retry
                }
                // Exponential backoff with max backoff limit
                long backoffTime = (long) Math.min(Math.pow(2, i) * 1000, maxBackoffMillis);
                try {
                    Thread.sleep(backoffTime);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null; // should not reach here
    }
}
