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
						System.out.println("Function num: " + functionNum + " Index: " + i);

					//Check if it is the stop index
					} else if (baseText[i].equals("stop")) {
						stopIndex = i;
						System.out.println("Function num: " + functionNum + " Index: " + i);

					//Check if it is a declaration index
					} else if (countFunction(baseText[i])) {
						functionNum++;
						declarationIndex = i;
						System.out.println("Declaration num: " + functionNum + " Index: " + i);

					//Check if it is a return index
					} else if (baseText[i].trim().startsWith("return")) {
						returnIndex = i;
						addFunctionReturnIndexPair(functionNum, declarationIndex, returnIndex);
						System.out.println("Return num: " + functionNum + " Index " + i);
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
		public boolean getFileExists() {
			return this.fileExists;
		}
		public File getTextFile() {
			return this.textFile;
		}
		public String getFileName() {
			return this.fileName;
		}
		public int getNumLines() {
			return this.numLines;
		}
		public int getNumFunctions() {
			return this.numFunctions;
		}
		public String[] getBaseText() {
			return this.baseText;
		}
		public Statement[] getPerStatement() {
			return this.perStatement;
		}
		public int[] getStartStopIndexPair() {
			return this.startStopIndexPair;
		}
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
			formattedLine = line.replaceAll(TOKEN_FORMAT_TAB[1], TOKEN_FORMAT_TAB[0]);
			formattedLine = formattedLine.concat(TOKEN_FORMAT_ENTER[0]);

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
	//================================================================
}