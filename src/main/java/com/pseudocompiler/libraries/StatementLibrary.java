// Package all statements in custom library
package com.pseudocompiler.libraries;

// Import Java CLasses
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface StatementLibrary {

    // Keywords
    //====================================================================================================
        String KEY_START = "start";
        String KEY_STOP = "stop";
        String KEY_RETURN = "return";
        String KEY_TRUE = "true";
        String KEY_FALSE = "false";
        String KEY_DECLARATIONS = "Declarations";
        String KEY_TYPE_NUM = "num";
        String KEY_TYPE_STRING = "string";
        String KEY_TYPE_BOOLEAN = "boolean";
        String KEY_TYPE_VOID =  "void";
        String KEY_INPUT = "input";
        String KEY_OUTPUT = "output";

        String KEY_WHILE = "while";
        String KEY_END_WHILE = "endwhile";
        String KEY_FOR = "for";
        String KEY_END_FOR = "endfor";

        String KEY_IF = "if";
        String KEY_ELSE = "else";
        String KEY_ELSE_IF = "else if";
        String KEY_ENDIF = "endif";
    //====================================================================================================

    // Keyword lists
    //====================================================================================================
        String[] KEYS_STATEMENT_TYPE = {KEY_START, KEY_STOP, KEY_RETURN, KEY_WHILE, KEY_END_WHILE, KEY_FOR, KEY_END_FOR, KEY_IF, KEY_ELSE, KEY_ELSE_IF, KEY_ENDIF, KEY_DECLARATIONS, KEY_TYPE_STRING, KEY_TYPE_NUM, KEY_TYPE_BOOLEAN, KEY_INPUT, KEY_OUTPUT};
        String[] KEYS_STATEMENT_DATATYPES = {KEY_TYPE_BOOLEAN, KEY_TYPE_NUM, KEY_TYPE_STRING};
        String[] KEYS_STATEMENT_TYPES_FUNCTIONS = {KEY_TYPE_BOOLEAN, KEY_TYPE_NUM, KEY_TYPE_STRING, KEY_TYPE_VOID};
        String[] KEYS_STATEMENT_IO = {KEY_INPUT, KEY_OUTPUT};
    //====================================================================================================

    // Regex collections
    //====================================================================================================
        // Common Rules
        //------------------------------------------------------------------------------------------------
        String REGEX_RULES_TAB = "(?:0_TAB\\s+)";
        String REGEX_RULES_ENTER = "(?:0_ENTER)";
        String REGEX_RULES_VARIABLE_USE = "(?:(\\w+)(?:\\[([^]]*)])*)";
        String REGEX_RULES_QUOTE = "(\"[^\"]*\")";
        String REGEX_RULES_VARIABLE_LIST = "(?:(" + REGEX_RULES_VARIABLE_USE + ")(?:(?:\\s*[,;]?\\s*)(" + REGEX_RULES_VARIABLE_USE + "))*)";
        String REGEX_RULES_ASSIGN = REGEX_RULES_VARIABLE_USE + "(?:\\s*=\\s*)(" + REGEX_RULES_VARIABLE_USE + "|" + REGEX_RULES_QUOTE + ")";
        String REGEX_RULES_DECLARATION = keysToRegexOr(KEYS_STATEMENT_DATATYPES) + "\\s+" + REGEX_RULES_VARIABLE_USE;
        String REGEX_RULES_DECLARATIONS = "(" + REGEX_RULES_DECLARATION + "\\s*)(s*[,;]?\\s*" + REGEX_RULES_DECLARATION + ")?";
        //------------------------------------------------------------------------------------------------

        // Statements
        //------------------------------------------------------------------------------------------------
        String REGEX_MAIN_DECLARATION = "(?:" + KEY_START + ")\\s+" + REGEX_RULES_ENTER;
        String REGEX_MAIN_STOP = "(?:" + KEY_STOP + ")\\s+" + REGEX_RULES_ENTER;

        String REGEX_METHOD_DECLARE = "(\\w+)\\(\\s*(\\w+\\s+\\w+(?:\\[[^]]*])*\\s*[,;]?\\s*)*\\)\\s*" + REGEX_RULES_ENTER;
        String REGEX_METHOD_CALL = "(\\w+)(?:\\(([^)]*)\\))";
        String REGEX_METHOD_RETURN = "\\s*" + KEY_RETURN + "\\b\\s*(.*?)\\s*";

        String REGEX_VARIABLE_DECLARATION = REGEX_RULES_TAB + "+" + REGEX_RULES_DECLARATION + REGEX_RULES_ENTER;
        String REGEX_VARIABLE_ASSIGNMENT = REGEX_RULES_TAB + "+" + REGEX_RULES_ASSIGN + REGEX_RULES_ENTER;
        String REGEX_VARIABLE_DECLARE_ASSIGN = "(?:\t)(?:\t)+(?:(?:" + REGEX_RULES_DECLARATION + ")(?:(?:\\s*=\\s*)(" + REGEX_RULES_VARIABLE_USE + "|" + REGEX_RULES_QUOTE + "|" + REGEX_RULES_VARIABLE_LIST + "))?" + REGEX_RULES_ENTER + ")";

        String REGEX_BLOCK_DECLARE = "(?:\\t(?:" + KEY_DECLARATIONS + ")\\n)";
        //------------------------------------------------------------------------------------------------
    //====================================================================================================

    // Regex comparisons
    //====================================================================================================
        /**
        * Use regex to test a string
        * @param regex Regular expression to test against
        * @param testString String to be tested
         * @return True if the String matches, false if the String does not
        */
        static boolean compareRegex(String regex, String testString) {
            return compareDefaultRegex("^" + regex + "$", testString);
        }
        static boolean compareDefaultRegex(String regex, String testString) {
            //Local variables
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(testString);

            return matcher.matches();
        }

        /**
	     * Get a Matcher object for a given regex and test string
	    * @param regex The regex pattern to compile
	    * @param testString The string to match against the regex
	    * @return Matcher object that can be used to find matches in the test string
	    */
        static Matcher getMatcher(String regex, String testString) {
            return getDefaultMatcher("^" + regex + "$", testString);
        }
        static Matcher getDefaultMatcher(String regex, String testString) {
            //Local Variables
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(testString);
        }
    //====================================================================================================

    // Work Methods
    //====================================================================================================
        default String getReturn(String testString) {
            //Local variables
            Pattern pattern = Pattern.compile(REGEX_METHOD_RETURN);
            Matcher matcher = pattern.matcher(testString);

            if (matcher.matches()) {
                if (!matcher.group(1).isEmpty()) {
                    return matcher.group(1);
                }
            }

            return null;
        }

        static String keysToRegexOr(String[] keys) {
            return String.join("|", keys);
        }
    //====================================================================================================

}