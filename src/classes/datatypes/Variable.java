package classes.datatypes;

import classes.libraries.MethodLibrary;
import classes.libraries.StatementLibrary;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable implements MethodLibrary, StatementLibrary {
    // Global variables
    //================================================================
        private String accessSpecifier;
        private boolean isStatic;
        private String datatype;
        private String identifier;
        private String value;
    //================================================================

    // Constructors
    //================================================================
        public Variable(Statement statement) {
            readStatement(statement);
        }
    //================================================================

    // Setters
    //================================================================
        private void setAccessSpecifier(String accessSpecifier) {
            this.accessSpecifier = accessSpecifier;
        }

        private void setIsStatic(boolean isStatic) {
            this.isStatic = isStatic;
        }

        private void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        private void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        private void setValue(String value) {
            if (identifier == null || datatype == null) {
                setToNull();
            } else {
                this.value = value;
            }
        }
    //================================================================

    // Getters
    //================================================================
        public String getAccessSpecifier() {
            return accessSpecifier;
        }
        public boolean getIsStatic() {
            return isStatic;
        }
        public String getDatatype() {
            return datatype;
        }
        public String getIdentifier() {
            return identifier;
        }
        public String getValue() {
            return value;
        }
    //================================================================

    // Work methods
    //================================================================
        private void readStatement(Statement statement) {
            //Local variables
            String testString = String.join(" ", statement.getText());
            String regexDeclare = "^(0_TAB\\s*)+\\s*((public|private|protected)\\s+(static\\s+)?)?(string|num|boolean)\\s+\\w+(\\[\\w+\\])*\\s*;?\\s*(0_ENTER)\\s*$";
            String regexInitialize = "^(0_TAB\\s*)+\\s*((public|private|protected)\\s+(static\\s+)?)?(string|num|boolean)\\s+\\w+(\\[\\w+\\])*\\s*=\\s*(\\w+|\"([^\"]*)\")\\s*(0_ENTER)\\s*$";
            Pattern patternDeclare = Pattern.compile(regexDeclare);
            Pattern patternInitialize = Pattern.compile(regexInitialize);
            Matcher matcherDeclare = patternDeclare.matcher(testString);
            Matcher matcherInitialize = patternInitialize.matcher(testString);
            boolean isDeclaration = true;
            boolean initialize = false;
            int datatypeIndex;

            //Check whether the variable is declared and or initialized
            if (matcherDeclare.matches()) {
                this.value = null;
            } else if (matcherInitialize.matches()) {
                initialize = true;
            } else {
                setToNull();
                isDeclaration = false;
            }

            //Declare the variable if the line is a declaration
            if (isDeclaration) {
                if (containsElement(KEYS_STATEMENT_DECLARATION_START, statement.getText()[statement.getDepth()])) {
                    setAccessSpecifier(statement.getText()[statement.getDepth()]);
                    if (statement.getText()[statement.getDepth()+1].equals(KEY_STATIC)) {
                        setIsStatic(true);
                        datatypeIndex = statement.getDepth()+2;
                    } else {
                        setIsStatic(false);
                        datatypeIndex = statement.getDepth()+1;
                    }
                } else if (statement.getText()[statement.getDepth()].equals(KEY_STATIC)) {
                    setAccessSpecifier(KEY_PROTECTED);
                    setIsStatic(true);
                    datatypeIndex = statement.getDepth()+1;
                } else {
                    setAccessSpecifier(KEY_PROTECTED);
                    setIsStatic(false);
                    datatypeIndex = statement.getDepth();
                }

                //Set datatype and identifier values
                setDatatype(statement.getText()[datatypeIndex]);
                setIdentifier(statement.getText()[datatypeIndex+1]);

            // Set the value if the statement is being initialized
            if (initialize) {
                setValue(statement.getText()[statement.getText().length-2]);
            }
            }
        }

        public void setToNull() {
            setAccessSpecifier(null);
            setIsStatic(false);
            setDatatype(null);
            setIdentifier(null);
            this.value = null;
        }
    //================================================================
}
