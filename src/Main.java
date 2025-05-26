// Import custom tools
import classes.libraries.ConstLibrary;
import classes.libraries.ColorLibrary;
import classes.tools.StartupTools;
import classes.tools.Formatter;
import classes.tools.ColoringTools;
import classes.tools.Dissector;
import classes.tools.Directories;

// Import custom datatypes
import classes.datatypes.Num;

public class Main {
	public static void main(String[] args) {
		// Create MethodLibrary Object
		Formatter format = new Formatter();

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
	}
}
