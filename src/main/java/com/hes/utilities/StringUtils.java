package com.hes.utilities;

/**
 * The {@code StringUtils} class provides utility methods for string operations such as obfuscation and indentation.
 * This class cannot be instantiated.
 */
public class StringUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private StringUtils() {
        // Prevent instantiation
    }

    /**
     * Obfuscates a string with "*" while keeping its length.
     *
     * @param secret The secret string to obfuscate.
     * @return A string where every character of the secret string is replaced with "*".
     *         If the secret string is {@code null}, returns "null".
     */
    public static String secret(String secret) {
        return secret == null ? "null" : "*".repeat(secret.length());
    }

    /**
     * Indents each line of the provided text with the specified indentation character repeated a given number of times.
     *
     * @param text       The text to be indented.
     * @param indentChar The character to be used for indentation. This character is repeated {@code amount} times to create the indentation.
     * @param amount     The number of times the indentation character should be repeated.
     * @return The indented text with each line prefixed by the specified indentation.
     */
    public static String indent(String text, String indentChar, int amount) {
        return text.replaceAll("(?m)^", indentChar.repeat(amount));
    }

    /**
     * Indents each line of the provided text with a default indentation of two spaces.
     *
     * @param text The text to be indented.
     * @return The indented text with each line prefixed by two spaces.
     */
    public static String indent(String text) {
        return StringUtils.indent(text, "  ", 1);
    }
}
