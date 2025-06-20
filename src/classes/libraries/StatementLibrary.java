// Package all statements in custom library
package classes.libraries;

// Import Java CLasses

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
        String KEY_INPUT = "input";
        String KEY_OUTPUT = "output";

    // Keyword lists
    String[] KEYS_STATEMENT_TYPE = {KEY_START, KEY_STOP, KEY_PUBLIC, KEY_PRIVATE, KEY_PROTECTED, KEY_STATIC, KEY_RETURN, KEY_WHILE, KEY_ENDWHILE, KEY_FOR, KEY_ENDFOR, KEY_IF, KEY_ELSE, KEY_ELSE_IF, KEY_ENDIF, KEY_DECLARATIONS, KEY_TYPE_STRING, KEY_TYPE_NUM, KEY_TYPE_BOOLEAN, KEY_INPUT, KEY_OUTPUT};
    String[] KEYS_STATEMENT_DECLARATION_START = {KEY_PUBLIC, KEY_PRIVATE, KEY_PROTECTED};
}