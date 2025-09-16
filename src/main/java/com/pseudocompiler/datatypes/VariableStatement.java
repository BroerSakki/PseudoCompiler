package com.pseudocompiler.datatypes;

import java.util.regex.Matcher;

final public class VariableStatement extends Statement {
	// Global variables
	//================================================================
		private String accessSpecifier;
		private boolean isStatic;
		private String datatype;
		private String identifier;
		private String value;
		private int type; // 0 = declaration, 1 = assignment
	//================================================================
		
	// Constructors
	//================================================================
		/**
		 * Creates a VariableStatement instance from a Statement object.
		 * It groups the statement into its components (access specifier, static modifier, datatype, identifier, and value).
		 * @param statement the Statement object to be grouped
		 */
		public VariableStatement(String text) {
			//Local variables
			super(text);
			
			group(this);
		}
		/**
		 * Creates a VariableStatement instance from a Statement object.
		 * It groups the statement into its components (access specifier, static modifier, datatype, identifier, and value).
		 * @param statement the Statement object to be grouped
		 */
		public VariableStatement(Statement statement) {
			//Local variables
			super(statement.toString());
			
			group(statement);
		}
	//================================================================
		
	// Setters
	//================================================================
		private void setAccessSpecifier(String accessSpecifier) {
			this.accessSpecifier = accessSpecifier;
		}
		private void setStatic(boolean isStatic) {
			this.isStatic = isStatic;
		}
		private void setDatatype(String datatype) {
			this.datatype = datatype;
		}
		private void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		private void setValue(String value) {
			this.value = value;
		}
	//================================================================
		
	// Getters
	//================================================================
		/**
		 * Returns the access specifier of the variable (public, private, protected).
		 * @return the access specifier as a String
		 */
		public String getAccessSpecifier() {
			return accessSpecifier;
		}
		/**
		 * Returns whether the variable is static.
		 * @return true if the variable is static, false otherwise
		 */
		public boolean isStatic() {
			return isStatic;
		}
		/**
		 * Returns the datatype of the variable (boolean, num, string).
		 * @return the datatype as a String
		 */
		public String getDatatype() {
			return datatype;
		}
		/**
		 * Returns the identifier of the variable.
		 * @return the identifier as a String
		 */
		public String getIdentifier() {
			return identifier;
		}
		/**
		 * Returns the value of the variable.
		 * If the variable is an array, it returns a comma-separated string of values.
		 * @return the value as a String
		 */
		public String getValue() {
			return value;
		}
		/**
		 * Returns the type of the variable statement.
		 * 0 = declaration, 1 = assignment, -1 = invalid
		 * @return the type as an int
		 */
		public int getType() {
			return type;
		}
	//================================================================
		
	// Work Methods
	//================================================================
		/**
		 * Groups the variable statement into its components.
		 * It identifies whether the statement is a declaration or an assignment,
		 * and extracts the access specifier, static modifier, datatype, identifier, and value.
		 * @param statement the Statement object to be grouped
		 */
		private void group(Statement statement) {
			//Local variables
			Matcher matcherDeclaration = getMatcher(Statement.REGEX_VARIABLE_DECLARATION);
			Matcher matcherAssignment = getMatcher(Statement.REGEX_VARIABLE_ASSIGNMENT);
			
			//Set Global variables
			if (matcherDeclaration.matches()) {
				type = 0; // Declaration
				setAccessSpecifier(matcherDeclaration.group(1));
				setStatic(matcherDeclaration.group(2) != null);
				setDatatype(matcherDeclaration.group(3));
				setIdentifier(matcherDeclaration.group(4));
				setValue(matcherDeclaration.group(6));
			} else if (matcherAssignment.matches()) {
				type = 1; //Assignment
				setIdentifier(matcherAssignment.group(1));
				setValue(matcherAssignment.group(3));
			} else {
				type = -1; // Invalid
				throw new IllegalArgumentException("Invalid Variable Statement: " + statement.toString());
			}
		}
	//================================================================
		
	// To Java Methods
	//================================================================
		/**
		 * Converts the variable statement to its Java representation.
		 * @return the Java representation of the variable statement as a String
		 */
		public String toJava() {
			//Local variables
			StringBuilder java = new StringBuilder();
			
			//Build Java representation
			if (type == 0) { // Declaration
				if (accessSpecifier != null) {
					java.append(accessSpecifier).append(" ");
				}
				if (isStatic) {
					java.append("static ").append(" ");
				}
				java.append(datatype).append(" ");
				java.append(identifier);
				if (value != null) {
					java.append(" = ").append(value);
				}
				java.append(";");
			} else if (type == 1) { // Assignment
				java.append(identifier).append(" = ").append(value).append(";");
			} else {
				throw new IllegalStateException("Invalid Variable Statement type: " + type);
			}
			
			return java.toString();
		}
}
