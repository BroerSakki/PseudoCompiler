// Package Dissector to tools folder
package com.pseudocompiler.tools;

import com.pseudocompiler.datatypes.Statement;
import com.pseudocompiler.libraries.MethodLibrary;
import com.pseudocompiler.libraries.StatementLibrary;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println();
		}
		public Dissector(Path filePath) {
			setFileName(filePath);
			setDefaults();
            System.out.println();
		}
		public Dissector(File file) {
			setFileName(file);
			setDefaults();
            System.out.println();
		}
	//================================================================

	// Setters
	//================================================================
		private void  setDefaults() {
			if (fileName == null) {
				System.out.println("Error (setDefaults): File has not been assigned");
			} else {
				if (checkFileExists(fileName)) {
					setTextFile(Directories.readFile(fileName));
					readData(textFile);

					setIndexPairs();
				} else {
					System.out.println("Error (setDefaults): File does not exist");
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

		private void setBaseText(String[] baseText) {
            this.baseText = baseText;
		}

		private void setPerStatement(Statement[] perStatement) {
            this.perStatement = perStatement;
		}

        private void setNumFunctions() {
            //Local Variables
            int numFunctions = 0;

            for (String line : baseText) {
                if (StatementLibrary.compareRegex(REGEX_METHOD_RETURN, line)) {
                    numFunctions++;
                }
            }

            this.numFunctions = numFunctions;
        }

		private void setIndexPairs() {
			if (fileExists) {
				//Local variables
				int functionNum = 0;
				int declarationIndex = -1;
				int returnIndex;
				int startIndex = -1;
				int stopIndex = -1;

                System.out.println("Setting index pairs...");

				//Assign a size to the functionReturnIndexPairs base array
				this.functionReturnIndexPairs = new int[numFunctions][2];

				//Go through the base text line by line
				for (int i = 0; i < baseText.length; i++) {

					//Check if it is the start index
					if (baseText[i].equals("start")) {
						startIndex = i;

					//Check if it is the stop index
					} else if (baseText[i].equals("stop")) {
						stopIndex = i;

					//Check if it is a declaration index
					} else if (countFunction(perStatement[i])) {
						declarationIndex = i;

					//Check if it is a return index
					} else if (StatementLibrary.compareRegex(REGEX_METHOD_RETURN, baseText[i].trim())) {
						returnIndex = i;
						addFunctionReturnIndexPair(functionNum, declarationIndex, returnIndex);
                        functionNum++;
					}
				}

				setStartStopIndexPair(startIndex, stopIndex);

			} else {
				System.out.println("Error (setIndexPairs): File does not exist");
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
				System.out.println("Error (setStartStopIndexPair): File does not exist");
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
				System.out.println("Error (addFunctionReturnIndexPair): file does not exist");
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
		 * Returns the indexes of start and stop of the pseudocode.
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

        public MethodBuilder getMain() {
            //Local Variables
            ArrayList<Statement> lines = new ArrayList<>(Arrays.asList(perStatement).subList(startStopIndexPair[0]+1, startStopIndexPair[1]));

            return new MethodBuilder(lines.toArray(new Statement[0]));
        }

        public MethodBuilder[] getMethods() {
            //User Message
            System.out.println("Retrieving Subroutines...");

            //Local Variables
            ArrayList<MethodBuilder> methods = new ArrayList<>();
            MethodBuilder mainMethod = getMain();

            try {
                for (int[] indexPair : functionReturnIndexPairs) {
                    //Local Variables
                    ArrayList<Statement> lines = new ArrayList<>(Arrays.asList(perStatement).subList(indexPair[0] + 1, indexPair[1]));
                    String returnValue = getReturn(baseText[indexPair[1]]);
                    MethodBuilder methodBuilder = new MethodBuilder(perStatement[indexPair[0]], lines.toArray(new Statement[0]), returnValue, mainMethod.getLocalScope());
                    methods.add(methodBuilder);
                }

                //Give feedback
                System.out.printf("Note: A total of %d subroutines were found\n", methods.size());

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Note: No defined subroutines were found\n");
                return null;
            }

            System.out.println();

            return methods.toArray(new MethodBuilder[0]);
        }
	//================================================================

	// Work methods
	//================================================================
		public boolean checkFileExists(String fileName) {
			//Local variables
			File testFile = Directories.readPath(fileName).toFile();

			//Check if file exists
            setFileExists(testFile.exists());

			//Return fileExists
			return fileExists;
		}

		private boolean countFunction(Statement currentStatement) {
			return StatementLibrary.compareRegex(REGEX_METHOD_DECLARE, currentStatement.toString());
		}

		private String[] asWords(String line) {
			//Local variables
			Grouper lineGroup;
			List<String> words;

			//Group formatted text based on punctuation
			lineGroup = new Grouper(line);

			//Split line into separate words
			words = lineGroup.getFinalText();

			//Return as Array of String
			return words.toArray(String[]::new);
		}

		private void readData(File texFile) {
            //User Message
            System.out.println("Reading data...");

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
                    currentStatement = new Statement(asWords(currentLine));

					//Check if function count increases 
					if (countFunction(currentStatement)) {
						totalFunctions++;
					}

					//Save lines and statements
					textBody.add(currentLine);
					statements.add(currentStatement);

					//Increase counted lines
					totalLines++;
				}

				//Set base variables equal local variables
				setBaseText(textBody.toArray(String[]::new));
				setNumLines(totalLines);
				setNumFunctions();
				setPerStatement(statements.toArray(Statement[]::new));
			} catch (Exception e) {
				System.out.println("Error (readData): could not read data");
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
				throw new IllegalArgumentException("Error (buildFileName): File name cannot be null or empty");
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
				System.out.println("Error (printBaseText): File does not exist");
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
				System.out.println("Error (printStatements): File does not exist");
			}
		}
	//================================================================
}