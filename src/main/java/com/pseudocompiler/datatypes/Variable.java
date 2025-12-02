package com.pseudocompiler.datatypes;

import com.pseudocompiler.datatypes.Statement;
import com.pseudocompiler.libraries.StatementLibrary;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Variable {

    // Attributes
    //====================================================================================================
        private String datatype;
        private String identifier;
        private String value;
        private int size;
    //====================================================================================================

    // Constructors
    //====================================================================================================
        public Variable(Statement statement) {
            readDeclaration(statement);
        }
    //====================================================================================================

    // Setters
    //====================================================================================================
        public void setValue(String value) {
            this.value = value;
        }
    //====================================================================================================

    // Getters
    //====================================================================================================
        public String getDatatype() {
            return datatype;
        }
        public String getIdentifier() {
            return identifier;
        }
        public String getValue() {
            return value;
        }
        public int getSize() {
            return size;
        }
    //====================================================================================================

    // Work Methods
    //====================================================================================================
        private void readDeclaration(Statement statement) {
            //Local Variables
            Matcher matcher;
            String[] attributes;

            //Get Relevant Matcher
            matcher = StatementLibrary.getMatcher(StatementLibrary.REGEX_VARIABLE_DECLARE_ASSIGN, statement.toString());

            //Analyze Declaration
            attributes = analyzeDeclaration(matcher);
        }

        private String[] analyzeDeclaration(Matcher matcher) {
            //Local Variables
            ArrayList<String> attributes = new ArrayList<>();

            //Check Statement Validity
            if (matcher.matches()) {
                for (int i = 1; i < matcher.groupCount(); i++) {
                    System.out.printf("Group %d: %s", i, matcher.group(i));
                }
            }

            return null;
        }
    //====================================================================================================

    // To String Methods
    //====================================================================================================
        @Override
        public String toString() {
            return "{\n" +
                "\t\"datatype\": \"" + datatype + "\",\n" +
                "\t\"identifier\": \"" + identifier + "\",\n" +
                "\t\"value\": \"" + value + "\",\n" +
                "\t\"size\": " + size + "\n" +
                "}";
        }
    //====================================================================================================


}