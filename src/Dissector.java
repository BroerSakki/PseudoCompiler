// Package Dissector to tools folder
package classes.tools;

// Import Java Classes
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.StringBuilder;

// Import Custom Libraries
import classes.libraries.ColorLibrary;
import classes.libraries.ConstLibrary;
import classes.libraries.MethodLibrary;

// Import Tool Classes
import classes.tools.Directories;

public class Dissector implements ConstLibrary, MethodLibrary {	
	//Cool tests wat werk
	//Gebruik printInfo om inligting oor die invoer file te wys
	//Gebruik die getters om inligting oor die file apart op te roep
	//Meeste setters word in die constructor gebruik, maar kan ook later aparte aspekte verander
	//Gebruik update om te verseker dat alle inliging oor die file nog op datum is

	final public static Class<?> currentClass = Dissector.class;
	public static ArrayList<String> methodIndex = new ArrayList<String>();

	// Create Objects
	//================================================================
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
		private int numberOfFunctions;
		private int[] mainIndexes = new int[2];
		private int[][] functionIndexes;
	//================================================================
	
	// Create textBody Object
	//================================================================
		ArrayList<String> textBody = new ArrayList<String>();
		ArrayList<String> textBodyFormatted = new ArrayList<String>();
	//================================================================
	
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
			Formatter.modSpace(2, 0);
			Formatter.makeGradientLine('-', printAbsPath.length(), ColorLibrary.MATRIX_GREEN_START, ColorLibrary.MATRIX_GREEN_STOP);
			
			info();
			
			Formatter.modSpace(0, 2);
			Formatter.makeGradientLine('-', printAbsPath.length(), ColorLibrary.MATRIX_GREEN_STOP, ColorLibrary.MATRIX_GREEN_START);
			Formatter.modSpace(0, 0);
		}
		public void printInfo(boolean hasFormat) {
			if (hasFormat) {
				printInfo();
			} else {
				info();
				System.out.println();
			}
		}
		private void info() {
			System.out.println("File path: " + filePath);
			System.out.println("File path-absolute: " + absPath);
			System.out.println("Lines in file: " + lines);
			System.out.println("Number of functions: " + numberOfFunctions);
		}
		
		// Overloaded printTextBody
		/**
		 * Prints the body of the file being dissected to the screen
		 * @param hasIndex Boolean value to determine if output has an index
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
			for (String method : textBodyFormatted) {
				System.out.println(method + "\n===============================================");
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
			//Declarations
			String textFormatStep = "";

			//Seperate text, then functions
			for (int i = mainIndexes[0]; i <= mainIndexes[1]; i++) {
				String line = textBody.get(i);
				line = line.replaceAll(TOKEN_FORMAT_TAB[1], TOKEN_FORMAT_TAB[0]);
			
				line = line.concat(TOKEN_FORMAT_ENTER[0]);
				List<String> words = splitKeepingQuotes(line);
				if (i == mainIndexes[1]) {
					textFormatStep = textFormatStep.concat("|");
				}
				textFormatStep = textFormatStep.concat(String.join("|", words));
			}

			textBodyFormatted.add(textFormatStep);

			for (int i = 0; i < numberOfFunctions; i++) {
				textFormatStep = "";

				for (int j = functionIndexes[i][0]; j <= functionIndexes[i][1]; j++) {
					String line = textBody.get(j);
					line = line.replaceAll(TOKEN_FORMAT_TAB[1], TOKEN_FORMAT_TAB[0]);
				
					line = line.concat(TOKEN_FORMAT_ENTER[0]);
					List<String> words = splitKeepingQuotes(line);
					if (j == functionIndexes[i][1]) {
						textFormatStep = textFormatStep.concat("|");
					}
					textFormatStep = textFormatStep.concat(String.join("|", words));
				}

				textBodyFormatted.add(textFormatStep);
			}
		}

		private void setFunctionNumbers() {
			numberOfFunctions = countOccurrences(textBody, KEY_RETURN);
			functionIndexes = new int[numberOfFunctions][2];
			mainIndexes[0] = textBody.indexOf(KEY_START);
			mainIndexes[1] = textBody.indexOf(KEY_STOP);

			int count = 0;

			for (int i = 0; i < textBody.size(); i++) {
				if (i < mainIndexes[0] || i > mainIndexes[1]) {
					if (textBody.get(i).equals(KEY_RETURN)) {
						functionIndexes[count][1] = i;
						count++;
					} else if (!textBody.get(i).contains(TOKEN_FORMAT_TAB[1])) {
						functionIndexes[count][0] = i;
					}
				}
			}

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
		setFunctionNumbers();
		setTextBodyFormatted();
		absPath = file.getAbsolutePath();
		lines = textBody.size();
	}

	public static List<String> splitKeepingQuotes(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
		int[] bracketDepth = {0, 0};
        char quoteChar = '\0'; // To track which quote character is being used
		char bracketChar = '\0';

        for (char c : input.toCharArray()) {
            if (c == QUOTE_SINGLE || c == QUOTE_DOUBLE) {
                // Toggle the inQuotes flag
                if (!inQuotes) {
                    inQuotes = true;
                    quoteChar = c; // Set the quote character
                } else if (c == quoteChar) {
                    inQuotes = false; // Close the quote
				}

                current.append(c); // Add the quote character to the current substring

            } else if ((c == '(' || c == '[')) {
				// Toggle the inBrackets flag
				switch (c) {
					case '(':
						bracketDepth[0]++;
						bracketChar = ')';
						break;
					case '[':
						bracketDepth[1]++;
						bracketChar = ']';
						break;
				}

				current.append(c);

			} else if (c == bracketChar) {
				switch (c) {
					case ')':
						bracketDepth[0]--;;
						break;
					case ']':
						bracketDepth[1]--;
						break;
				}

				current.append(c);

			} else if (c == ' ' && !inQuotes && bracketDepth[0] == 0 && bracketDepth[1] == 0) {
                // Finalize the current substring
                result.add(current.toString().trim());
                current.setLength(0); // Clear the StringBuilder for the next substring
			} else {
				current.append(c);
			}
        }

        // Add the last substring if there's any content left
        if (current.length() > 0) {
            result.add(current.toString().trim());
        }

        return result;
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