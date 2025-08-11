package classes.datatypes;

import java.util.regex.Matcher;

import classes.libraries.StatementLibrary;

public class VariableStatement extends Statement {
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
		public VariableStatement(String text) {
			//Local variables
			super(text);
			
			group(this);
		}
		public static VariableStatement buildVariableStatement(Statement statement) {
			return new VariableStatement(statement.toString());
		}
	//================================================================
		
	// Setters
	//================================================================
		public void setAccessSpecifier(String accessSpecifier) {
			this.accessSpecifier = accessSpecifier;
		}
		public void setStatic(boolean isStatic) {
			this.isStatic = isStatic;
		}
		public void setDatatype(String datatype) {
			this.datatype = datatype;
		}
		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		public void setValue(String value) {
			this.value = value;
		}
	//================================================================
		
	// Getters
	//================================================================
		public String getAccessSpecifier() {
			return accessSpecifier;
		}
		public boolean isStatic() {
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
		
	// Work Methods
	//================================================================
		private void group(Statement statement) {
			//Local variables
			Matcher matcherDeclaration = getMatcher(Statement.REGEX_VARIABLE_DECLARATION);
			Matcher matcherAssignment = getMatcher(Statement.REGEX_VARIABLE_ASSIGNMENT);
			
			//Set Global variables
			if (matcherDeclaration.matches()) {
				setAccessSpecifier(matcherDeclaration.group(1));
				setStatic(matcherDeclaration.group(2) != null);
				setDatatype(matcherDeclaration.group(3));
				setIdentifier(matcherDeclaration.group(4));
				setValue(matcherDeclaration.group(6));
			} else if (matcherAssignment.matches()) {
				setIdentifier(matcherAssignment.group(1));
				setValue(matcherAssignment.group(3));
			} else {
				throw new IllegalArgumentException("Invalid Variable Statement: " + statement.toString());
			}
		}
	//================================================================
}
