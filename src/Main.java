// Import custom tools
import classes.libraries.ConstLibrary;
import classes.libraries.ColorLibrary;
import classes.tools.*;

// Import custom datatypes
import classes.datatypes.Num;
import classes.datatypes.Statement;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		// Create MethodLibrary Object
		ColoringTools coloring = new ColoringTools();
		
		// Create Dissector Object
		Dissector program = new Dissector("PseudoProgram.txt");

		StartupTools.displayLogoRainbow();

		StartupTools.displayLogoGradient();

		StartupTools.displayCreditsRainbow();

		StartupTools.displayCreditsGradient();

		int[] rgb1 = {0, 59, 0};
		int[] rgb2 = {0, 255, 65};

		System.out.println();

		coloring.gradientText(ConstLibrary.JEAN_IMAGE, rgb1, rgb2, true, true);

		program.printInfo();
		program.printTextBodyFormatted();


		
		Formatter.modSpaceTop(1);
		Formatter.makeRainbowLine('-', 40);
		
		program.printMethodIndex();

		Grouper thingemeBob = new Grouper("string PROMPT_USER_NAME = \"Enter username >> \"");
		Statement thingy = new Statement(thingemeBob.getFinalText().toArray(new String[0]), 5);

		System.out.println();
		System.out.println(thingy.getKeywords().toString());
		System.out.println(Arrays.toString(thingy.getText()));
	}
}
