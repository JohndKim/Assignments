package commands;

/**
 * Class containing some characters and regex
 */
public class IPParser {
    /**
     * Space
     */
    public static final String SPACE = " ";
    /**
     * 0-1 white space
     */
    public static final String ZERO_ONE_WHITESPACE = "[\\s]{0,1}";
    /**
     * (
     */
    public static final String OPEN_BRACKET = "[(]";
    /**
     * )
     */
    public static final String CLOSED_BRACKET = "[)]";
    /**
     * ()
     */
    public static final String BRACKETS = "[()]";
    /**
     * Empty string
     */
    public static final String EMPTY_STRING = "";
    /**
     * [
     */
    public static final String OPEN_SQUARE_BRACKET = "[";
    /**
     * ]
     */
    public static final String CLOSED_SQUARE_BRACKET = "]";
    /**
     * One or more times regex
     */
    public static final String ONE_OR_MORE_TIMES = "+"; // zero or more times
    /**
     * Period regex
     */
    public static final String PERIOD = "[.]"; // period
    /**
     * Digits regex (part of IP)
     */
    public static final String DIGITS = "[\\d]{1,3}"; // 1-3 digits
    /**
     * Regex point notation (for an IP)
     */
    public static final String REGEX_POINT_NOTATION = DIGITS + PERIOD + DIGITS + PERIOD + DIGITS + PERIOD + DIGITS;
    /**
     * For an ip with a possible space after it
     */
    public static final String REGEX_POINT_NOTATION_SPACE = REGEX_POINT_NOTATION + ZERO_ONE_WHITESPACE;
    /**
     * Regex bracket notation (multiple ips)
     */
    public static final String REGEX_BRACKET_NOTATION = OPEN_BRACKET + OPEN_SQUARE_BRACKET + REGEX_POINT_NOTATION_SPACE + CLOSED_SQUARE_BRACKET + ONE_OR_MORE_TIMES + CLOSED_BRACKET;

    private IPParser() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
