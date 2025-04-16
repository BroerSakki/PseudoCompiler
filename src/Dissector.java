// Package Dissector to tools folder
package classes.tools;

// Import Java Classes
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Import Formatter Class
import classes.tools.Formatter;

// Import Custom Libraries
import classes.libraries.*;

public class Dissector extends MethodLibrary {	
	// Create Objects
	//================================================================
		// Libraries
		private static ConstLibrary conLib = new ConstLibrary();
		private static Formatter format = new Formatter();
		// File handling
		private static File file;
		private static Scanner reader;
	//================================================================
	
	// Variables
	//================================================================
		final public static Class<?> currentClass = Dissector.class;
		private String filePath;
		private String absPath;
		private int lines;
	//================================================================
	
	// Create textBody Object
	ArrayList<String> textBody = new ArrayList<String>();
	ArrayList<ArrayList<String>> textBodyFormatted = new ArrayList<>();
	
	// Constructor
	//================================================================
		public Dissector(String path) {
			filePath = path;
			setTextBody();
			setTextBodyFormatted();
			lines = textBody.size();
			List<String> list = Arrays.asList(returnMethodIndex(currentClass));
			methodIndex = new ArrayList<>(list);
		}
	//================================================================
	
	// Pront Methods
	//================================================================
		// Overloaded printInfo
		public void printInfo() {
			String printAbsPath = "File path-absolute: " + absPath;
			format.modSpace(2, 0);
			format.makeLine('-', printAbsPath.length());
			
			System.out.println("File path: " + filePath);
			System.out.println("File path-absolute: " + absPath);
			System.out.println("Lines in file: " + lines);
			
			format.modSpace(0, 2);
			format.makeLine('-', printAbsPath.length());
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
		public void printTextBodyFormatted() {
			for (int i = 0; i < textBodyFormatted.size(); i++) {
				for (int k = 0; k < textBodyFormatted.get(i).size(); k++) {
					System.out.print(textBodyFormatted.get(i).get(k) + "|");
				}
				System.out.println();
			}
		}
	//================================================================

	// Getters
	//================================================================
	public String getInputPath() {
		return filePath;
	}
	public String getAbsPath() {
		return absPath;
	}
	public int getLen() {
		return lines;
	}
	public ArrayList<String> getTextBody() {
		return textBody;
	}
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
	public void setPath(String path) {
		filePath = path;
		update();
	}
	public void setReader() {
		reader.close();
		reader = new Scanner(filePath);
	}
	private void setTextBody() {
		textBody.clear();
		try {
				file = new File(filePath);
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
	private void setTextBodyFormatted() {
		ArrayList<String> textFormatStep = new ArrayList<String>();	
		for (int i = 0; i < textBody.size(); i++) {
			String line = textBody.get(i).replaceAll("\t", conLib.TAB);
			System.out.println(line);
			String[] words = line.split("\\s+");
			for (int j = 0; j < words.length; j++) {
				textFormatStep.add(words[j]);
			}
			textBodyFormatted.add(textFormatStep);
		}
	}
	//================================================================
	
	// Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			displayMethodIndex(currentClass);
		}
	//================================================================
	
	// Update data
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
}