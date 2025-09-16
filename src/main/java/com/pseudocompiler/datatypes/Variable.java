package com.pseudocompiler.datatypes;

import com.pseudocompiler.libraries.MethodLibrary;
import com.pseudocompiler.libraries.StatementLibrary;
import java.util.regex.Matcher;

public class Variable implements MethodLibrary, StatementLibrary {
    // Global variables
    //================================================================
        private String accessSpecifier;
        private boolean isStatic;
        private String datatype;
        private String identifier;
        private String value;
        private int size;
    //================================================================

    // Constructors
    //================================================================
        public Variable(Statement statement) {
            setToNull();
            readDeclaration(statement);
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

        private void setSize(int size) {
            this.size = size;
        }
    //================================================================

    // Getters
    //================================================================
        /**
         * Get the access specifier of the variable
         * @return
         */
        public String getAccessSpecifier() {
            return accessSpecifier;
        }
        /**
		 * Get if the variable is static
		 * @return boolean indicating if the variable is static
		 */
        public boolean getIsStatic() {
            return isStatic;
        }
        /**
		 * Get the datatype of the variable
		 * @return String representing the datatype of the variable
		 */
        public String getDatatype() {
            return datatype;
        }
        /**
         * Get the identifier of the variable
         * @return String representing the identifier of the variable
         */
        public String getIdentifier() {
            return identifier;
        }
        /**
		 * Get the value of the variable
		 * @return String representing the value of the variable
		 */
        public String getValue() {
            return value;
        }
        /**
		 * Get the size of the variable (for arrays)
		 * @return int representing the size of the variable
		 */
        public int getSize() {
            return  size;
        }
    //================================================================

    // Work methods
    //================================================================
        private void readDeclaration(Statement statement) {
            //Local variables
			Matcher matcher = statement.getMatcher(REGEX_VARIABLE_DECLARATION, statement.toString());
			
			//Declare Variable

            //Check if the statement is a valid variable declaration
            //If it is, set the variable's properties
			if (matcher.matches()) {
				setAccessSpecifier(matcher.group(1));
				if (matcher.group(2).equals("static")) {
					setIsStatic(true);
				} else {
					setIsStatic(false);
				}
				setDatatype(matcher.group(3));
				setIdentifier(matcher.group(4));
				setValue(matcher.group(6));
			} else {
				setToNull();
			}
        }
		
		public void readAssignment(Statement statement) {
			//Local variables
			Matcher matcher = statement.getMatcher(REGEX_VARIABLE_ASSIGNMENT, statement.toString());
			
			//Assign value
			if (matcher.matches()) {
				setValue(matcher.group(3));
			}
		}

        public final void setToNull() {
            setAccessSpecifier(null);
            setIsStatic(false);
            setDatatype(null);
            setIdentifier(null);
            setSize(-1);
            this.value = null;
        }
    //================================================================

    //Static toJava methods
    //================================================================
        //Convert the variable to a Java declaration string
        /**
         * Convert a Variable object to a Java declaration string
         * @param variable Variable object to be converted
         * @return String representing the Java declaration of the variable
         */
        public static String toJavaDeclaration(Variable variable) {
            StringBuilder sb = new StringBuilder();
            if (variable.getAccessSpecifier() != null) {
                sb.append(variable.getAccessSpecifier()).append(" ");
            }
            if (variable.getIsStatic()) {
                sb.append("static ");
            }
            sb.append(variable.getDatatype()).append(" ").append(variable.getIdentifier());
            if (variable.getSize() > 0) {
                sb.append("[").append(variable.getSize()).append("]");
            }
            if (variable.getValue() != null) {
                sb.append(" = ").append(variable.getValue());
            }
            sb.append(";");

            return sb.toString();
        }
        
        /**
         * Convert a Statement object to a Java declaration string
         * @param statement Statement object to be converted
         * @return String representing the Java declaration of the variable
         */
        public static String toJavaDeclaration(Statement statement) {
            Variable variable = new Variable(statement);
            return toJavaDeclaration(variable);
        }

        //Convert the variable to a Java assignment string
        /**
		 * Convert a Variable object to a Java assignment string
		 * @param variable Variable object to be converted
		 * @return String representing the Java assignment of the variable
		 */
        public static String toJavaAssignment(Variable variable) {
            StringBuilder sb = new StringBuilder();
            sb.append(variable.getIdentifier());

            sb.append(" = ").append(variable.getValue()).append(";");

            return sb.toString();
        }
        
        /**
		 * Convert a Variable object to a Java assignment string with an index
		 * @param variable Variable object to be converted
		 * @param index Index of the variable in an array
		 * @param value Value to be assigned to the variable at the specified index
		 * @return String representing the Java assignment of the variable at the specified index
		 */
        public static String toJavaAssignment(Variable variable, int index, String value) {
            StringBuilder sb = new StringBuilder();

            sb.append(variable.getIdentifier());
            sb.append("[").append(index).append("]");
            sb.append(" = ").append(value).append(";");

            return sb.toString();
        }
        
        /**
		 * Convert a Variable object to a Java assignment string with a value
		 * @param variable Variable object to be converted
		 * @param value Value to be assigned to the variable
		 * @return String representing the Java assignment of the variable with the specified value
		 */
        public static String toJavaAssignment(Variable variable, String value) {
            variable.setValue(value);
            return toJavaAssignment(variable);
        }
        
        /**
		 * Convert a Statement object to a Java assignment string
		 * @param statement Statement object to be converted
		 * @return String representing the Java assignment of the variable
		 */
        public static String toJavaAssignment(Statement statement) {
            Variable variable = new Variable(statement);
            return toJavaAssignment(variable);
        }
    //================================================================
}
