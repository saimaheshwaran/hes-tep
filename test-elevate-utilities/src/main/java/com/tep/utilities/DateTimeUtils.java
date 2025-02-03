package com.tep.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    /**
     * Retrieves the current system date and time formatted as a string.
     * The date and time are formatted in the pattern "yyyy/MM/dd hh:mm:ss a", which corresponds to
     * year/month/day hours:minutes:seconds AM/PM.
     *
     * @return A string representing the current system date and time in the specified format.
     */
    public static String getCurrentDateTime() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a").format(LocalDateTime.now());
    }

    /**
     * Retrieves the current system date formatted as a string.
     * The date is formatted in the pattern "yyyy/MM/dd", which corresponds to
     * year/month/day.
     *
     * @return A string representing the current system date in the specified format.
     */
    public static String getCurrentDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    /**
     * Retrieves the current system time formatted as a string.
     * The time is formatted in the pattern "hh:mm:ss a", which corresponds to
     * hours:minutes:seconds AM/PM.
     *
     * @return A string representing the current system time in the specified format.
     */
    public static String getCurrentTime() {
        return DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());
    }

}
