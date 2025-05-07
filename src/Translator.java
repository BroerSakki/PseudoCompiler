// Package Translator to tools folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;

// Import Custom Tools
import classes.tools.Dissector;

public class Translator {
	// Declare finalPseudoText
	//================================================================
		public ArrayList<String> finalPseudoText = new ArrayList<String>();
	//================================================================
	
	// Constructor
	//================================================================
		/**
		 * Constructor for Translator class
		 */
		public Translator(ArrayList<String> separatedText) {
			finalPseudoText = separatedText;
		}
	//================================================================
}
