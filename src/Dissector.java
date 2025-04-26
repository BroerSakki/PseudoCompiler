// Package Dissector to tools folder
package classes.tools;

// Import Java Classes
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Import Tool Classes
import classes.libraries.ColorLibrary;
import classes.tools.Formatter;
import classes.tools.Directories;
import classes.tools.ColoringTools;

// Import Custom Libraries
import classes.libraries.*;

public class Dissector implements ConstLibrary, MethodLibrary {	
	//Cool tests wat werk
	//Gebruik printInfo om inligting oor die invoer file te wys
	//Gebruik die getters om inligting oor die file apart op te roep
	//Meeste setters word in die constructor gebruik, maar kan ook later aparte aspekte verander
	// Gebruik update om te verseker dat alle inliging oor die file nog op datum is

	final public static Class<?> currentClass = Dissector.class;
	public static ArrayList<String> methodIndex = new ArrayList<String>();

	// Create Objects
	//================================================================
		// Libraries
		private static Formatter format = new Formatter();
		private static ColoringTools coloring = new ColoringTools();
		// File handling
		private static File file;
		private static Path pathVariable;
		private static Scanner reader;
	//================================================================
	
	// Variables
	//================================================================
		private String filePath;
		private String absPath;
		private int lines;
	//================================================================
	
	// Create textBody Object
	ArrayList<String> textBody = new ArrayList<String>();
	ArrayList<ArrayList<String>> textBodyFormatted = new ArrayList<>();
	
	// Constructor
	//================================================================
		/**
		 * Constructor for Dissector class
		 */
		public Dissector(String fileName) {
			setPath(fileName);
			List<String> list = Arrays.asList(returnMethodIndex(currentClass));
			methodIndex = new ArrayList<>(list);
		}
	//================================================================
	
	// Print Methods
	//================================================================
		// Overloaded printInfo
		/**
		 * Prints information about file to screen
		 * (optional) @param hasFormat Boolean value to determine if output will be formatted
		 * @return Nothing, is a print function
		 */
		public void printInfo() {
			String printAbsPath = "File path-absolute: " + absPath;
			format.modSpace(2, 0);
			format.makeGradientLine('-', printAbsPath.length(), ColorLibrary.MATRIX_GREEN_START, ColorLibrary.MATRIX_GREEN_STOP);
			
			System.out.println(coloring.recolorText("File path: ", ColorLibrary.MATRIX_GREEN_STOP)  + filePath);
			System.out.println(coloring.recolorText("File path-absolute: ", ColorLibrary.MATRIX_GREEN_STOP)  + absPath);
			System.out.println(coloring.recolorText("Lines in file: ", ColorLibrary.MATRIX_GREEN_STOP)  + lines);
			
			format.modSpace(0, 2);
			format.makeGradientLine('-', printAbsPath.length(), ColorLibrary.MATRIX_GREEN_STOP, ColorLibrary.MATRIX_GREEN_START);
			format.modSpace(0, 0);
		}
		public void printInfo(boolean hasFormat) {
			if (hasFormat) {
				printInfo();
			} else {
				System.out.println("File path: " + filePath);
				System.out.println("File path-absolute: " + absPath);
				System.out.println("Lines in file: " + lines);
			}
		}
		
		// Overloaded printTextBody
		/**
		 * Prints the body of the file being dissected to the screen
		 * (optional) @param hasIndex Boolean value to determine if output has an index
		 * @return Nothing, is a print function
		 */
		public void printTextBody() {
			for (int i = 0; i < textBody.size(); i++) {
				System.out.println((i+1) + ":\t" + textBody.get(i));
			}
		}
		public void printTextBody(boolean hasIndex) {
			if (hasIndex) {
				printTextBody();
			} else {
				for (int i = 0; i < textBody.size(); i++) {
					System.out.println(textBody.get(i));
				}
			}
		}
		
		// Overlaoded printTextBodyFormatted
		/**
		 * Prints the body of text as formatted closer to tokens
		 * @return Nothing, is a print function
		 */
		public void printTextBodyFormatted() {
			for (ArrayList<String> line : textBodyFormatted) {
				for (String word : line) {
					System.out.print(word + "|");
				}
				System.out.println("\n" + line.size());
			}
		}
	//================================================================

	// Getters
	//================================================================
		// Get filePath
		/**
		 * Returns a path from the current directory to the target directory
		 * @return The filePath variable
		 */
		public String getInputPath() {
			return filePath;
		}
		
		
		// Get absPath
		/**
		 * Returns the absolute path of the target file
		 * @return The absPath variable
		 */
		public String getAbsPath() {
			return absPath;
		}
		
		// Get lines
		/**
		 * Returns the number of lines in the file
		 * @return The lines variable
		 */
		public int getLen() {
			return lines;
		}
		
		// Get textBody
		/**
		 * Returns the body of the text as an ArrayList of type String
		 * @return The textBody variable
		 */
		public ArrayList<String> getTextBody() {
			return textBody;
		}
		
		
		// Get line of text
		/**
		 * Returns a single line of text from the file
		 * @param lineNum Integer that represents the position of the line top return
		 * @return The text at the indicated line as a String
		 */
		public String getLine(int lineNum) {
			try {
				return textBody.get(lineNum-1);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error: Index out of bounds");
				return "";
			}
		}
	//================================================================

	// Setters
	//================================================================
		// Set filePath
		/**
		 * Sets the filePath String variable, as well as the pathVariable for the target file
		 * @param fileName String variable to be converted to path 
		 * @return Nothing, is a set function
		 */
		public void setPath(String fileName) {
			// Declarations
			boolean hasType, isTxt;
			String type, filePathStep;
			
			// Verify file name and type
			hasType = (fileName.lastIndexOf(".") != 0);
			if (!hasType) {
				filePathStep = fileName + ".txt";
			}
			
			isTxt = (fileName.lastIndexOf(".txt") != 0);
			type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			
			if (isTxt) {
				filePathStep = fileName;
			} else {
				filePathStep = fileName.replaceAll(fileName.substring(fileName.lastIndexOf("."), fileName.length()), ".txt");
			}
			
			filePath = Directories.getFilePath(filePathStep);
			pathVariable = Paths.get(filePath);
			
			update();
		}
		
		// Set reader
		/**
		 * Sets and links the reader Scanner object to the target file
		 * @return Nothing, is a set function
		 */
		public void setReader() {
			reader.close();
			reader = new Scanner(filePath);
		}
		
		// Set textBody
		/**
		 * Extracts, then sets the contents of the target file to the textBody ArrayList
		 * @return Nothing, is a set function
		 */
		private void setTextBody() {
			textBody.clear();
			try {
					file = pathVariable.toFile();
					reader = new Scanner(file);
					absPath = file.getAbsolutePath();
					
					// Extract textfile data to ArrayList
					while (reader.hasNextLine()) {
						String line = reader.nextLine();
						textBody.add(line);
					}
					
				} catch (FileNotFoundException e) {
					System.out.println("Error: File not found");
					e.printStackTrace();
				}
		}
		
		// Set textBodyFormatted
		/**
		 * Convert textbody into tokens
		 * @return Nothing, is a set function
		 */
		private void setTextBodyFormatted() {
			ArrayList<String> textFormatStep = new ArrayList<String>();	
			for (String line : textBody) {
				line = line.replaceAll("\t", TOKEN_FORMAT_TAB[0]);
				System.out.println(line);
				line = line.concat(TOKEN_FORMAT_ENTER[0]);
				String[] words = line.split("\\s+");
				for (String word : words) {
					textFormatStep.add(word);
				}
			}
			textBodyFormatted.add(textFormatStep);
		}
	//================================================================
	
	// Update data
	/**
	 * Runs methods to set al default information of the program
	 * @return Nothing, merely calls other functions
	 */
	public void update() {
		// Reset file data
		setTextBody();
		setTextBodyFormatted();
		absPath = file.getAbsolutePath();
		lines = textBody.size();
	}

	// Close Scanners
	public void closeReader() {
		reader.close();
	}
	
	// Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			displayMethodIndex(currentClass);
		}
	//================================================================
}