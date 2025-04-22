// Package Main to core folder
package core;

// Import custom tools
import classes.tools.Formatter;
import classes.tools.ColoringTools;
import classes.tools.Dissector;
import classes.tools.Directories;

public class Main {
	public static void main(String[] args) {
		// Create MethodLibrary Object
		Formatter format = new Formatter();

		// Create MethodLibrary Object
		ColoringTools coloring = new ColoringTools();
		
		// Create Dissector Object
		Dissector program = new Dissector("PseudoProgram.txt");

		//Cool Art
		coloring.rainbowText(" ____                     _        ____                      _ _           \r\n" + //
							"|  _ \\ ___  ___ _   _  __| | ___  / ___|___  _ __ ___  _ __ (_) | ___ _ __ \r\n" + //
							"| |_) / __|/ _ \\ | | |/ _` |/ _ \\| |   / _ \\| '_ ` _ \\| '_ \\| | |/ _ \\ '__|\r\n" + //
							"|  __/\\__ \\  __/ |_| | (_| | (_) | |__| (_) | | | | | | |_) | | |  __/ |   \r\n" + //
							"|_|   |___/\\___|\\__,_|\\__,_|\\___/ \\____\\___/|_| |_| |_| .__/|_|_|\\___|_|   \r\n" + //
							"                                                      |_|                  ", 100);

		program.printInfo();
		program.printTextBody();
		
		format.modSpaceTop(1);
		format.makeLine('-', 40, 0, 143, 17);
		
		program.printMethodIndex();
	}
}
