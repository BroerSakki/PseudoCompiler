// Import custom libraries
import classes.libraries.Formatter;
import classes.pseudoID.Dissector;

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
