// Package Main to core folder
package core;

// Import custom tools
import classes.tools.Formatter;
import classes.tools.Dissector;
import classes.tools.Directories;

public class Main {
	public static void main(String[] args) {
		// Create MethodLibrary Object
		Formatter format = new Formatter();
		
		// Create Dissector Object
		Dissector program = new Dissector("PseudoProgram.txt");
		
		program.printInfo();
		program.printTextBody();
		
		format.modSpaceTop(1);
		format.makeLine();
		
		program.printMethodIndex();
	}
}
