// Package all statements in custom library
package com.pseudocompiler.libraries;

// Import Java CLasses
import java.util.ArrayList;

public class ExecutionContext {
	
	// Global constants
	//==================================================
		private static final String[] DEFAULT_DATATYPES = {"num", "string", "boolean", "void"};
	//==================================================
	
	// Global variables
	//==================================================
		private ArrayList<String> userDatatypes;
		private ArrayList<String> userMethods;
	//==================================================
		
	// Constructors
	//==================================================
		
	//==================================================
		
	// Setters
	//==================================================
		public void setUserDatatypes(ArrayList<String> userDatatypes) {
			this.userDatatypes = userDatatypes;
		}
		public void setUserMethods(ArrayList<String> userMethods) {
			this.userMethods = userMethods;
		}
		public void addUserDatatype(String datatype) {
			if (!userDatatypes.contains(datatype)) {
				userDatatypes.add(datatype);
			}
		}
		public void addUserMethod(String method) {
			if (!userMethods.contains(method)) {
				userMethods.add(method);
			}
		}
	//==================================================
		
	// Getters
	//==================================================
		public ArrayList<String> getUserDatatypes() {
			return userDatatypes;
		}
		public ArrayList<String> getUserMethods() {
			return userMethods;
		}
		public String[] getAllDatatypes() {
			ArrayList<String> allDatatypes = new ArrayList<>(userDatatypes);
			for (String datatype : DEFAULT_DATATYPES) {
				if (!allDatatypes.contains(datatype)) {
					allDatatypes.add(datatype);
				}
			}
			return allDatatypes.toArray(String[]::new);
		}
	//==================================================
}