// Package all statements in custom library
package classes.libraries;

// Import Java CLasses

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
    String REGEX_MAIN_DECLARATION = "^(?:start)\\s+(?:0_ENTER)$";
    String REGEX_MAIN_STOP = "^(?:stop)\\s+(?:0_ENTER)$";
  
    String REGEX_FUNCTION_DECLARATION = "^(?:(public|private|protected)\\s+)?(static\\s+)?(\\w+)(?:\\((\\s*(?:(?:boolean|num|string)\\s+)(?:\\w+\\s*)(?:(?:[,:]\\s*)(?:(?:boolean|num|string)\\s+)(?:\\w+\\s*))*)?\\)\\s+)(?:0_ENTER)";
    String REGEX_FUNCTION_RETURN = "^(?:return\\s+)(\\w+\\s+)?(?:0_ENTER)$";
  
    String REGEX_VARIABLE_DECLARATION = "^(?:0_TAB\\s+)+(?:(public|private|protected)\\s+)?(?:(static)\\s+)?(?:(boolean|num|string)\\s+)(?:(\\w+)(?:\\[(?:(\\w+))\\])?\\s*)(?:(?:=\\s*)((?:(?:\\w+\\s*)|(?:\"(?:[^\"]*)\"))(?:" + "(?:,\\s*(?:(?:\\w+)|(?:\"(?:[^\"]*)\"))\\s*)*" + ")))?(?:\\s+)(?:0_ENTER)$";
    String REGEX_VARIABLE_ASSIGNMENT = "^(?:0_TAB\\s+)+(\\w+)(?:\\[(\\s*\\w+\\s*)\\])?(?:\\s*)(?:=\\s*)(((?:\\w+)|(?:(?:\"[?:^\"]*)\"))\\s+)(?:0_ENTER)$";
    
    String REGEX_IO = "^(?:0_TAB\\s+)+(?:(input|output)\\s+)(?:(?:\\w+|(?:\"[^\"]*)\"\\s*)(?:([,;]\\s*)(?:\\w+|(?:\"[^\"]*)\")\\s*)*\\s+)(?:0_ENTER)$";
    String REGEX_IO_INPUT = "^(?:0_TAB\\s+)+(?:input\\s+)(\\w+\\s+)(?:0_ENTER)$";
    String REGEX_IO_OUTPUT = "^(?:0_TAB\\s+)+(?:output\\s+)((?:\\w+|(?:\"[^\"]*)\"\\s*)(?:([,;]\\s*)(?:\\w+|(?:\"[^\"]*)\")\\s*)*\\s+)(?:0_ENTER)$";
    
    String REGEX_LOOP_WHILE = "^(0_TAB\\s+)*(while\\s+)(((NOT\\s+)?(\\w+|(\"([^\"])\"))(\\s*(" + String.join("|", ConstLibrary.TOKEN_OPERATORS_CONDITIONS) + "))\\s*(NOT\\s+)?(\\w+|(\"([^\"]*)\")))*\\s+)(is\\s+(true|false)\\s+)(do\\s+)(0_ENTER)$";
    String REGEX_LOOP_FOR = "^(?:0_TAB\\s+)*(?:for\\s+)(\\w+\\s*)(?:=\\s*)(\\d+\\s+)(?:to\\s+)(\\w+\\s+)((?:step\\s+)(\\d+\\s+))?(?:do\\s+)?(?:0_ENTER)$";

    // Regex comparisons
    /**
     * Use regex to test a string
     * @param regex
     * @param testString
     * @return True if the String matches, false if the String does not
     */
    public static boolean compareRegex(String regex, String testString) {
        //Local variables
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);
        
        return  matcher.matches();
    }

    public static Matcher getMatcher(String regex, String testString) {
        //Local variables
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(testString);
    }
}