// Package Main to core folder
package core;

// Import custom tools
import classes.libraries.ConstLibrary;
import classes.libraries.ColorLibrary;
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

		ConstLibrary.displayLogoRainbow();

		ConstLibrary.displayLogoGradient();

		ConstLibrary.displayCreditsRainbow();

		ConstLibrary.displayCreditsGradient();

		int[] rgb1 = {0, 59, 0};
		int[] rgb2 = {0, 255, 65};

		System.out.println();

		coloring.gradientText(ConstLibrary.JEAN_IMAGE, rgb1, rgb2, 20, false);

		program.printInfo();
		program.printTextBody();
		
		format.modSpaceTop(1);
		format.makeRainbowLine('-', 40);
		
		program.printMethodIndex();
	}
}
