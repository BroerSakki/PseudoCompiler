// Package Num class to datatypes folder
package classes.datatypes;

// Import Java Classes
import java.util.ArrayList;

// Import custom libraries
import classes.libraries.MethodLibrary;

public class Num implements MethodLibrary {
	// Global constants
	//================================================================
		final public static Class<?> currentClass = Num.class;
	//================================================================
	
	// Global variables
	//================================================================
		public static ArrayList<String> methodIndex = new ArrayList<String>();
		private byte numByte;
		private short numShort;
		private int numInt;
		private long numLong;
		private float numFloat;
		private double numDouble;
		public String type = "";
		public String name = "";
		public boolean declaredAndUsed = false;
		private boolean hasValue = true;
	//================================================================
	
	// Overloaded constructor to detirmine number datatype
	//================================================================
		public Num(String varName, byte value) {
			type = "byte";
			name = varName;
			numByte = value;
		}
		public Num(String varName, short value) {
			type = "short";
			name = varName;
			numShort = value;
		}
		public Num(String varName, int value) {
			type = "int";
			name = varName;
			numInt = value;
		}
		public Num(String varName, long value) {
			type = "long";
			name = varName;
			numLong = value;
		}
		public Num(String varName, float value) {
			type = "float";
			name = varName;
			numFloat = value;
		}
		public Num(String varName, double value) {
			type = "double";
			name = varName;
			numDouble = value;
		}
		public Num(String varName) {
			name = varName;
			hasValue = false;
		}
	//================================================================
	
	// Set methods
	//================================================================
		// Overloaded set
		public static Num set(String varName, byte value) {
			return new Num(varName, value);
		}
		public static Num set(String varName, short value) {
			return new Num(varName, value);
		}
		public static Num set(String varName, int value) {
			return new Num(varName, value);
		}
		public static Num set(String varName, long value) {
			return new Num(varName, value);
		}
		public static Num set(String varName, float value) {
			return new Num(varName, value);
		}
		public static Num set(String varName, double value) {
			return new Num(varName, value);
		}
	//================================================================
	
	// Get methods
	//================================================================
		public String getDeclaration() {
			String declaration = "";
			if (hasValue) {
				switch(type) {
					case "byte":
						declaration = type + " " + name + " = " + numByte + ";";
						break;
					case "short":
						declaration = type + " " + name + " = " + numShort + ";";
						break;
					case "int":
						declaration = type + " " + name + " = " + numInt + ";";
						break;
					case "long":
						declaration = type + " " + name + " = " + numLong + ";";
						break;
					case "float":
						declaration = type + " " + name + " = " + numFloat + ";";
						break;
					case "double":
						declaration = type + " " + name + " = " + numDouble + ";";
						break;
					default:
						declaration = "Error: Num not declared";
				}
			} else if (!type.isEmpty()) {
				declaration = type + " " + name + ";";
			} else {
				declaration = "Error: Type not yet initialized";
			}
			
			return declaration;
		}
	//================================================================
	
	// Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			MethodLibrary.displayMethodIndex(currentClass);
		}
	//================================================================
}