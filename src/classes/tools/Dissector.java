// Package Dissector to tools folder
package classes.tools;

import classes.datatypes.Statement;
import classes.libraries.MethodLibrary;
import classes.libraries.StatementLibrary;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dissector implements MethodLibrary, StatementLibrary {
	// Global variables
	//================================================================
		private boolean fileExists;
		private File textFile;
		private Scanner reader;
		private String fileName;
		private int numLines;
		private int numFunctions;
		private String[] baseText;
		private Statement[] perStatement;
		private int[] startStopIndexPair;
		private int[][] functionReturnIndexPairs;
	//================================================================

	// Constructors
	//================================================================
		public Dissector(String fileName) {
			setFileName(fileName);
			setDefaults();
		}
		public Dissector(Path filePath) {
			setFileName(filePath);
			setDefaults();
		}
		public Dissector(File file) {
			setFileName(file);
			setDefaults();
		}
	//================================================================

	// Setters
	//================================================================
		private void  setDefaults() {
			if (fileName == null) {
				System.out.println("Error: File has not been assigned");
			} else {
				if (checkFileExists(fileName)) {
					setTextFile(Directories.readFile(fileName));
					readData(textFile);

					setIndexPairs();
				} else {
					System.out.println("Error: File does not exist");
				}
			}
		}

		private void setFileExists(boolean fileExists) {
			this.fileExists = fileExists;
		}

		private void setTextFile(File textFile) {
			this.textFile = textFile;
		}

		private void setReader(File file) {
			try {
				this.reader = new Scanner(file);
			} catch (FileNotFoundException e) {
				setFileExists(false);
			}
		}

		// Overloaded setFileName
		private void setFileName(String fileName) {
			this.fileName = fileName;
			buildFileName();
		}
		private void setFileName(Path filePath) {
			this.fileName = filePath.getFileName().toString();
		}
		private void setFileName(File file) {
			this.fileName = file.getName();
		}

		private void setNumLines(int numLines) {
			this.numLines = numLines;
		}

		private void setNumFunctions(int numFunctions) {
			this.numFunctions = numFunctions;
		}

		private void setBaseText(String[] baseText) {
			this.baseText = baseText;
		}

		private void setPerStatement(Statement[] perStatement) {
			this.perStatement = perStatement;
		}

		private void setIndexPairs() {
			if (fileExists) {
				//Local variables
				int totalFunctions = getNumLines();
				int functionNum = -1;
				int declarationIndex = -1;
				int returnIndex;
				int startIndex = -1;
				int stopIndex = -1;

				//Assign a size to the functionReturnIndexPairs base array
				this.functionReturnIndexPairs = new int[totalFunctions][2];

				//Go through the base text line by line
				for (int i = 0; i < baseText.length; i++) {

					//Check if it is the start index
					if (baseText[i].equals("start")) {
						startIndex = i;

					//Check if it is the stop index
					} else if (baseText[i].equals("stop")) {
						stopIndex = i;

					//Check if it is a declaration index
					} else if (countFunction(baseText[i])) {
						functionNum++;
						declarationIndex = i;

					//Check if it is a return index
					} else if (baseText[i].trim().startsWith("return")) {
						returnIndex = i;
						addFunctionReturnIndexPair(functionNum, declarationIndex, returnIndex);
					}
				}

				setStartStopIndexPair(startIndex, stopIndex);

			} else {
				System.out.println("Error: File does not exist");
			}
		}

		private void setStartStopIndexPair(int startIndex, int stopIndex) {
			//Check fileExists
			if (fileExists) {
				//Create startStopIndexPair array
				this.startStopIndexPair = new int[2];

				//Assign startIndex and stopIndex
				startStopIndexPair[0] = startIndex;
				startStopIndexPair[1] = stopIndex;
			} else {
				//Assign null to startStopIndexPairs
				startStopIndexPair = null;
				System.out.println("Error: File does not exist");
			}
		}
	//================================================================

	// Mutators
	//================================================================
		private void addFunctionReturnIndexPair(int functionNum, int declarationIndex, int returnIndex) {
			//Check fileExists
			if (fileExists) {
				//Add declarationIndex and returnIndex to selected position
				functionReturnIndexPairs[functionNum][0] = declarationIndex;
				functionReturnIndexPairs[functionNum][1] = returnIndex;
			} else {
				//Assign null to functionReturnIndexPairs
				functionReturnIndexPairs = null;
				System.out.println("Error: file does not exist");
			}
		}
	//================================================================

	// Getters
	//================================================================
		/**
		 * Returns whether the file exists or not.
		 * @return boolean indicating if the file exists.
		 */
		public boolean getFileExists() {
			return this.fileExists;
		}
		/**
		 * Returns the text file being dissected.
		 * @return File object representing the text file.
		 */
		public File getTextFile() {
			return this.textFile;
		}
		/**
		 * Returns the name of the file being dissected.
		 * @return String representing the file name.
		 */
		public String getFileName() {
			return this.fileName;
		}
		/**
		 * Returns the number of lines in the text file.
		 * @return int representing the number of lines.
		 */
		public int getNumLines() {
			return this.numLines;
		}
		/**
		 * Returns the number of functions found in the text file.
		 * @return int representing the number of functions.
		 */
		public int getNumFunctions() {
			return this.numFunctions;
		}
		/**
		 * Returns the base text of the file as an array of strings.
		 * @return String array containing the lines of text.
		 */
		public String[] getBaseText() {
			return this.baseText;
		}
		/**
		 * Returns the statements parsed from the text file.
		 * @return Statement array containing the parsed statements.
		 */
		public Statement[] getPerStatement() {
			return this.perStatement;
		}
		/**
		 * Returns the indeces of start and stop of the pseudocode.
		 * @return int array containing the start and stop indices.
		 */
		public int[] getStartStopIndexPair() {
			return this.startStopIndexPair;
		}
		/**
		 * Returns the pairs of function declaration and return indices.
		 * @return 2D int array where each row contains a declaration index and a return index.
		 */
		public int[][] getFunctionReturnIndexPairs() {
			return this.functionReturnIndexPairs;
		}
	//================================================================

	// Work methods
	//================================================================
		public boolean checkFileExists(String fileName) {
			//Local variables
			File testFile = Directories.readFile(fileName);

			//Check if file exists
			if (testFile.exists()) {
				setFileExists(true);
			} else {
				setFileExists(false);
			}

			//Return fileExists
			return fileExists;
		}

		private boolean countFunction(String currentLine) {
			//Local variables
			boolean isFunction = false;
			String trimmedLine = currentLine.trim();
			String regex = "^(public|private|protected)?\\s*(static)?\\s*(\\w+)\\(\\s*(\\w+\\s+\\w+\\s*[,;]?\\s*)*\\)\\s*$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(trimmedLine);

			//Basic checks
			if (trimmedLine.startsWith("//") || trimmedLine.startsWith("#") || trimmedLine.isEmpty()) {
				isFunction = false;

			//Match with regex pattern
			} else if (matcher.matches()) {
				isFunction = true;
			}

			//Return isFunction
			return isFunction;
		}

		private String[] asWords(String line) {
			//Local variables
			String formattedLine;
			Grouper lineGroup;
			List<String> words;

			//Set up formatted line
			formattedLine = line.replaceAll("\t", TOKEN_FORMAT_TAB);
			formattedLine = formattedLine.concat(TOKEN_FORMAT_ENTER);

			//Group formatted text based on punctuation
			lineGroup = new Grouper(formattedLine);

			//Split line into seperate words
			words = lineGroup.getFinalText();

			//Return as Array of String
			return words.toArray(String[]::new);
		}

		private void readData(File texFile) {
			//Local variables
			List<String> textBody = new ArrayList<>();
			List<Statement> statements = new ArrayList<>();
			Statement currentStatement;
			String currentLine;
			int totalLines = 0;
			int totalFunctions = 0;

			try {
				//Assign reader to textFile
				setReader(texFile);

				//Assess each line
				while (reader.hasNextLine()) {
					//Read line
					currentLine = reader.nextLine();

					//Check if function count increases 
					if (countFunction(currentLine)) {
						totalFunctions++;
					}

					//Save lines and statements
					currentStatement = new Statement(asWords(currentLine), totalLines);
					textBody.add(currentLine);
					statements.add(currentStatement);

					//Increase counted lines
					totalLines++;
				}

				//Set base variables equal local variabls
				setBaseText(textBody.toArray(String[]::new));
				setNumLines(totalLines);
				setNumFunctions(totalFunctions);
				setPerStatement(statements.toArray(Statement[]::new));
			} catch (Exception e) {
				System.out.println("Error: could not read data");
			}

			//Close the reader
			reader.close();
		}
		
		/**
		 * Builds the file name by ensuring it is not null or empty and appending ".txt" if necessary.
		 * Throws IllegalArgumentException if the file name is invalid.
		 */
		private void buildFileName() {
			if (fileName == null || fileName.isEmpty()) {
				throw new IllegalArgumentException("File name cannot be null or empty");
			} else if (!fileName.endsWith(".txt")) {
				fileName += ".txt"; // Ensure the file name ends with .txt
			}
		}
	//================================================================
		
	// Print Methods
	//================================================================
		/**
		 * Returns a string representation of the Dissector object.
		 * @return String containing details about the file and its contents.
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Dissector for file: ").append(fileName).append("\n");
			sb.append("File exists: ").append(fileExists).append("\n");
			sb.append("Number of lines: ").append(numLines).append("\n");
			sb.append("Number of functions: ").append(numFunctions).append("\n");
			sb.append("Base text:\n");
			for (String line : baseText) {
				sb.append(line).append("\n");
			}
			return sb.toString();
		}
		
		/**
		 * Prints the base text of the file.
		 */
		public void printBaseText() {
			if (fileExists) {
				for (String line : baseText) {
					System.out.println(line);
				}
			} else {
				System.out.println("Error: File does not exist");
			}
		}

		/**
		 * Prints the statements parsed from the file.
		 */
		public void printStatements() {
			if (fileExists) {
				for (Statement statement : perStatement) {
					System.out.println(statement);
				}
			} else {
				System.out.println("Error: File does not exist");
			}
		}
	//================================================================
}