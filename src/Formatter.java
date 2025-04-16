// Package Formatter to tools folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Import Custom Libraries
import classes.libraries.*;

public class Formatter extends MethodLibrary {
	// Declarations
	final public static Class<?> currentClass = Formatter.class;
	private static int spaceTop = 0;
	private static int spaceBottom = 0;
	
	// Reset Variables
	public void resetDefaults() {
		spaceTop = 0;
		spaceBottom = 0;
		methodIndex.clear();
	}
	
	// Spacing Modifiers
	//================================================================
		public static void modSpaceTop(int size) {
			spaceTop = size;
		}
		public static void modSpaceBottom(int size) {
			spaceBottom = size;
		}
		public static void modSpace(int top, int bottom) {
			spaceTop = top;
			spaceBottom = bottom;
		}
	//================================================================
	
	// Spacing Methods
	public static void space(int lines) {
		for (int i = 1; i <= lines; i++) {
			System.out.println();
		}
	}
		
	// Overloaded makeLine Method
	//================================================================
		public static void makeLine() {
			space(spaceTop);
			System.out.println(ConstLibrary.FORMAT_DEFAULT_LINE);
			space(spaceBottom);
		}
		public static void makeLine(char lineChar) {
			space(spaceTop);
			for (int i = 1; i <= 40; i++) {
				System.out.print(lineChar);
			}
			System.out.println();
			space(spaceBottom);
		}
		public static void makeLine(char lineChar, int len) {
			space(spaceTop);
			for (int i = 1; i <= len; i++) {
				System.out.print(lineChar);
			}
			System.out.println();
			space(spaceBottom);
		}
	//================================================================
	
	// Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			displayMethodIndex(currentClass);
		}
	//================================================================
	
	// Constructor
	//================================================================
		public Formatter() {
			List<String> list = Arrays.asList(returnMethodIndex(currentClass));
			methodIndex = new ArrayList<>(list);
		}
	//================================================================
	
	public static void main(String[] args) {
		Formatter format = new Formatter();
	}
}