// Package Main to core folder
package core;

// Import custom tools
import classes.libraries.ConstLibrary;
import classes.tools.Formatter;
import classes.tools.ColoringTools;
import classes.tools.Dissector;
import classes.tools.Directories;

// Import custom datatypes
import classes.datatypes.Num;

public class Main {
	public static void main(String[] args) {
		// Declarations
		Num num1;
		Num num2 = new Num("num2", 35f);
		num1 = new Num("num1");
		System.out.println(num1.getDeclaration() + "\n" + num2.getDeclaration());
		
		System.out.println("\n\n\n\n\n\n");
		
		// Create MethodLibrary Object
		Formatter format = new Formatter();

		// Create MethodLibrary Object
		ColoringTools coloring = new ColoringTools();
		
		// Create Dissector Object
		Dissector program = new Dissector("PseudoProgram.txt");

		coloring.rainbowText(ConstLibrary.LOGO_IMAGE, 50);

		coloring.rainbowText(ConstLibrary.SAKKI_IMAGE, 50);

		coloring.rainbowText(ConstLibrary.RASSIE_IMAGE, 50);

		program.printInfo();
		program.printTextBody();
		
		format.modSpaceTop(1);
		format.makeLine('-', 40, 0, 143, 17);
		
		program.printMethodIndex();
	}
}
