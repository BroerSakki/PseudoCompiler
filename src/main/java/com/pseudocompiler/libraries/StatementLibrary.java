// Package all statements in custom library
package com.pseudocompiler.libraries;

// Import Java CLasses
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface StatementLibrary {
    // Keywords
        String KEY_START = "start";
        String KEY_STOP = "stop";
        String KEY_PUBLIC = "public";
        String KEY_PRIVATE = "private";
        String KEY_PROTECTED = "protected";
        String KEY_STATIC = "static";
        String KEY_RETURN = "return";
        String KEY_TRUE = "true";
        String KEY_FALSE = "false";
        String KEY_WHILE = "while";
        String KEY_ENDWHILE = "endwhile";
        String KEY_FOR = "for";
        String KEY_ENDFOR = "endfor";
        String KEY_ELSE = "else";
        String KEY_IF = "if";
        String KEY_ELSE_IF = "else if";
        String KEY_ENDIF = "endif";
        String KEY_DECLARATIONS = "Declarations";
        String KEY_TYPE_NUM = "num";
        String KEY_TYPE_STRING = "string";
        String KEY_TYPE_BOOLEAN = "boolean";
        String KEY_TYPE_VOID =  "void";
        String KEY_INPUT = "input";
        String KEY_OUTPUT = "output";

    // Keyword lists
    String[] KEYS_STATEMENT_TYPE = {KEY_START, KEY_STOP, KEY_PUBLIC, KEY_PRIVATE, KEY_PROTECTED, KEY_STATIC, KEY_RETURN, KEY_WHILE, KEY_ENDWHILE, KEY_FOR, KEY_ENDFOR, KEY_IF, KEY_ELSE, KEY_ELSE_IF, KEY_ENDIF, KEY_DECLARATIONS, KEY_TYPE_STRING, KEY_TYPE_NUM, KEY_TYPE_BOOLEAN, KEY_INPUT, KEY_OUTPUT};
    String[] KEYS_STATEMENT_ACCESS_SPECIFIERS = {KEY_PUBLIC, KEY_PRIVATE, KEY_PROTECTED};
    String[] KEYS_STATEMENT_DATATYPES = {KEY_TYPE_BOOLEAN, KEY_TYPE_NUM, KEY_TYPE_STRING};
    String[] KEYS_STATEMENT_TYPES_FUNCTIONS = {KEY_TYPE_BOOLEAN, KEY_TYPE_NUM, KEY_TYPE_STRING, KEY_TYPE_VOID};
    String[] KEYS_STATEMENT_IO = {KEY_INPUT, KEY_OUTPUT};

    // Regex collections
    String REGEX_MAIN_DECLARATION = "^(?:0_TAB\\s+)(?:start)\\s+(?:0_ENTER)$";
    String REGEX_MAIN_STOP = "^(?:0_TAB\\s+)(?:stop)\\s+(?:0_ENTER)$";

    String REGEX_METHOD = "^(public|private|protected)?\\s*(static)?\\s*(\\w+)\\(\\s*(\\w+\\s+\\w+(?:\\[[^]]*])*\\s*[,;]?\\s*)*\\)\\s*$";
    String REGEX_METHOD_RETURN = "^\\s*return\\b\\s*(.*?)\\s*$";

    String REGEX_VARIABLE_DECLARATION = "^(?:0_TAB\\s+)*(?:(public|private|protected)\\s+)?(?:(static)\\s+)?(?:(boolean|num|string)\\s+)(?:(\\w+)(?:\\[(?:(\\w+))\\])?\\s*)(?:(?:=\\s*)((?:(?:\\w+\\s*)|(?:\"(?:[^\"]*)\"))(?:" + "(?:,\\s*(?:(?:\\w+)|(?:\"(?:[^\"]*)\"))\\s*)*" + ")))?(?:\\s+)(?:0_ENTER)$";
    String REGEX_VARIABLE_ASSIGNMENT = "^(?:0_TAB\\s+)*(\\w+)(?:\\[(\\s*\\w+\\s*)\\])?(?:\\s*)(?:=\\s*)(((?:\\w+)|(?:(?:\"[?:^\"]*)\"))\\s+)(?:0_ENTER)$";
    
    String REGEX_IO = "^(?:0_TAB\\s+)+(?:(input|output)\\s+)(?:(?:\\w+|(?:\"[^\"]*)\"\\s*)(?:([,;]\\s*)(?:\\w+|(?:\"[^\"]*)\")\\s*)*\\s+)(?:0_ENTER)$";
    String REGEX_IO_INPUT = "^(?:0_TAB\\s+)+(?:input\\s+)(\\w+\\s+)(?:0_ENTER)$";
    String REGEX_IO_OUTPUT = "^(?:0_TAB\\s+)+(?:output\\s+)((?:\\w+|(?:\"[^\"]*)\"\\s*)(?:([,;]\\s*)(?:\\w+|(?:\"[^\"]*)\")\\s*)*\\s+)(?:0_ENTER)$";
    
    // Regex comparisons
    /**
     * Use regex to test a string
     * @param regex Regular expression to test against
     * @param testString String to be tested
     * @return True if the String matches, false if the String does not
     */
    default boolean compareRegex(String regex, String testString) {
        //Local variables
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);
        
        return  matcher.matches();
    }

    /**
	 * Get a Matcher object for a given regex and test string
	 * @param regex The regex pattern to compile
	 * @param testString The string to match against the regex
	 * @return Matcher object that can be used to find matches in the test string
	 */
    default Matcher getMatcher(String regex, String testString) {
        //Local variables
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(testString);
    }

    default String getReturn(String testString) {
        //Local variables
        Pattern pattern = Pattern.compile(REGEX_METHOD_RETURN);
        Matcher matcher = pattern.matcher(testString);

        if (matcher.find()) {
            if (!Objects.equals(matcher.group(1), "")) {
                return matcher.group(1);
            }
        }

        return null;
    }
}