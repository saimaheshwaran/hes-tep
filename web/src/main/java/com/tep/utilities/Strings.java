package com.tep.utilities;

import com.tep.web.base.Waits;
import com.tep.web.validation.Assertion;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Strings utility class for various string operations and key identification.
 */
public class Strings {
    private static final Logger logger = LoggerFactory.getLogger(Strings.class);

    /**
     * Identifies the Selenium Keys constant based on the provided key string.
     *
     * @param key the key string to identify.
     * @return the corresponding Selenium Keys constant.
     */
    public static Keys identifyKey(String key) {
        return switch (key.toUpperCase()) {
            case "NULL" -> Keys.NULL;
            case "CANCEL" -> Keys.CANCEL;
            case "HELP" -> Keys.HELP;
            case "BACK_SPACE" -> Keys.BACK_SPACE;
            case "TAB" -> Keys.TAB;
            case "CLEAR" -> Keys.CLEAR;
            case "RETURN" -> Keys.RETURN;
            case "ENTER" -> Keys.ENTER;
            case "SHIFT" -> Keys.SHIFT;
            case "LEFT_SHIFT" -> Keys.LEFT_SHIFT;
            case "CONTROL" -> Keys.CONTROL;
            case "LEFT_CONTROL" -> Keys.LEFT_CONTROL;
            case "ALT" -> Keys.ALT;
            case "LEFT_ALT" -> Keys.LEFT_ALT;
            case "PAUSE" -> Keys.PAUSE;
            case "ESCAPE" -> Keys.ESCAPE;
            case "SPACE" -> Keys.SPACE;
            case "PAGE_UP" -> Keys.PAGE_UP;
            case "PAGE_DOWN" -> Keys.PAGE_DOWN;
            case "END" -> Keys.END;
            case "HOME" -> Keys.HOME;
            case "LEFT" -> Keys.LEFT;
            case "ARROW_LEFT" -> Keys.ARROW_LEFT;
            case "UP" -> Keys.UP;
            case "ARROW_UP" -> Keys.ARROW_UP;
            case "RIGHT" -> Keys.RIGHT;
            case "ARROW_RIGHT" -> Keys.ARROW_RIGHT;
            case "DOWN" -> Keys.DOWN;
            case "ARROW_DOWN" -> Keys.ARROW_DOWN;
            case "INSERT" -> Keys.INSERT;
            case "DELETE" -> Keys.DELETE;
            case "SEMICOLON" -> Keys.SEMICOLON;
            case "EQUALS" -> Keys.EQUALS;
            case "NUMPAD0" -> Keys.NUMPAD0;
            case "NUMPAD1" -> Keys.NUMPAD1;
            case "NUMPAD2" -> Keys.NUMPAD2;
            case "NUMPAD3" -> Keys.NUMPAD3;
            case "NUMPAD4" -> Keys.NUMPAD4;
            case "NUMPAD5" -> Keys.NUMPAD5;
            case "NUMPAD6" -> Keys.NUMPAD6;
            case "NUMPAD7" -> Keys.NUMPAD7;
            case "NUMPAD8" -> Keys.NUMPAD8;
            case "NUMPAD9" -> Keys.NUMPAD9;
            case "MULTIPLY" -> Keys.MULTIPLY;
            case "ADD" -> Keys.ADD;
            case "SEPARATOR" -> Keys.SEPARATOR;
            case "SUBTRACT" -> Keys.SUBTRACT;
            case "DECIMAL" -> Keys.DECIMAL;
            case "DIVIDE" -> Keys.DIVIDE;
            case "F1" -> Keys.F1;
            case "F2" -> Keys.F2;
            case "F3" -> Keys.F3;
            case "F4" -> Keys.F4;
            case "F5" -> Keys.F5;
            case "F6" -> Keys.F6;
            case "F7" -> Keys.F7;
            case "F8" -> Keys.F8;
            case "F9" -> Keys.F9;
            case "F10" -> Keys.F10;
            case "F11" -> Keys.F11;
            case "F12" -> Keys.F12;
            default -> Keys.NULL;
        };
    }

    /**
     * Removes UTF characters from the provided string.
     *
     * @param data the string from which UTF characters need to be removed.
     * @return a StringBuffer with UTF characters removed.
     */
    public static StringBuffer removeUTFCharacters(String data) {
        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {
            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
        }
        m.appendTail(buf);
        logger.info("UTF characters removed. Result: {}", buf);
        return buf;
    }

    /**
     * Fetches a substring from the provided string based on the regex and group.
     *
     * @param string the source string.
     * @param regex  the regex pattern to match.
     * @param group  the regex group to fetch.
     * @return the fetched substring.
     */
    public static String stringFetch(String string, String regex, int group) {
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            result = (matcher.group(group));
            logger.info("Substring fetched: '{}'", result);
        }
        return result;
    }

    /**
     * Searches for a substring in a list of strings.
     *
     * @param list         the list of strings to search in.
     * @param searchString the substring to search for.
     * @return the matched string.
     */
    public static String subStringSearchInList(ArrayList<String> list, String searchString) {
        String regex = String.format("^@(%s)(.*)\\d+", searchString);
        String match = null;
        for (String string : list) {
            if (string.matches(regex)) {
                match = string;
                logger.info("Match found: '{}'", match);
                break;
            }
        }
        return match;
    }

    /**
     * Encodes the provided string using Base64 encoding.
     *
     * @param string the string to encode.
     * @return the Base64 encoded string.
     */
    public static String encode(String string) {
        return Base64.getEncoder().encodeToString(String.format("%s", string).getBytes());
    }

    /**
     * Decodes the provided Base64 encoded string.
     *
     * @param bytes the Base64 encoded string to decode.
     * @return the decoded string.
     */
    public static String decode(String bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }

    /**
     * Compares two strings and asserts if they are equal.
     *
     * @param str1 the source string.
     * @param str2 the target string.
     */
    public void compareStrings(String str1, String str2) {
        Assertion.equals(str1, str2, "Success");
    }
}
