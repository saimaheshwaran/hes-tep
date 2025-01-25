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
     * Executes a given method with a specified number of retries and an exponential backoff strategy.
     * <p>
     * This method will attempt to invoke the supplied method up to {@code maxRetries} times. If the execution
     * fails due to an exception, the method will wait for an exponentially increasing duration before retrying.
     * The wait time is capped at {@code maxBackoffMillis}.
     * </p>
     * <p>
     * Usage: {@code T result = executeWithRetry(() -> yourMethodToExecute(), 3, 10000);}
     * </p>
     *
     * @param <T>              the type of the result returned by the {@code supplier}
     * @param supplier         a {@link Supplier} that provides the method to be executed
     * @param maxRetries       the maximum number of retries before giving up
     * @param maxBackoffMillis the maximum time (in milliseconds) to wait between retries
     * @return the result of the successful method execution, or {@code null} if all retries fail (though the method is
     * designed to either return a result or throw an exception, so reaching a {@code null} return should be exceptional)
     * @throws RuntimeException if the supplied method fails on the last retry
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
