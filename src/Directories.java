// Package Directories class to tools folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.lang.reflect.Array;

// Import Custom Libraries
import classes.libraries.*;

public class Directories implements ConstLibrary, MethodLibrary {
	//================================================================
		public static String[] methodIndex() {
			return MethodLibrary.returnMethodIndex(Directories.class);
		}
	//================================================================

	// Path details
	//================================================================
		/**
		 * @return Current working directory
		 */
		public static String currentDir() {
			return System.getProperty("user.dir");
		}
	
		/**
		 * Seperate parts of a path and identify the file name
		 * @param filePath String variable that includes the path (complete or incomplete) to a desired file
		 * @return String variable representing only the file name (includes possible file extension)
		 */
		private static String fileName(String filePath) {
			//Local variables
			ArrayList<String> path = splitPath(filePath);
			return path.get(path.size()-1);
		}

		/**
		 * Create a String that represents the path from the working directory, up to the "Pseudocompiler" directory, then down to the desired position
		 * @param dirPath String variable representing path starting from "PseudoCompiler"
		 * @return String variable representing the final path
		 */
		private static String targetDir(String dirPath) {
			//Local variables
			ArrayList<String> path = splitPath(currentDir());
			int rootDir = path.indexOf("PseudoCompiler");
			int dirDepth = (path.size()-1) - rootDir;
			StringBuilder targetPath = new StringBuilder();

			for (int i = 0; i < dirDepth; i++) {
				targetPath.append("../");
			}
			targetPath.append(dirPath);

			return targetPath.toString();
		}
	//================================================================

	// Work methods
	//================================================================
		/**
		 * Splits a String by the '\' character
		 * @param filePath String variable representing the path to a file
		 * @return ArrayList<String> Object where each element is the name of a directory, and the final element being the file name
		 */
		private static ArrayList<String> splitPath(String filePath) {
			//Local variables
			String[] arrPath = filePath.split("\\\\");
			ArrayList<String> path = new ArrayList<String>(Arrays.asList(arrPath));

			return path;
		}

		/**
		 * Seperately build the directory to be used and find the name of the file before concatinating into 1 final path
		 * @param dirPath String variable representing the path starting from the "Pseudocompiler" directory
		 * @param fileName String variable representing the file name (can also be a path)
		 * @return String variable representing the concatinated path
		 */
		private static String buildPath(String dirPath, String fileName) {
			//Local variables
			String dir = targetDir(dirPath);
			
			return dir + fileName(fileName);
		}
	//================================================================

	// Use methods
	//================================================================
		/**
		 * Builds and returns a custom path to one of the predefined read destinations as documented in ConstLibrary
		 * @param target String variable (User or Dev) that stipulates the location of the target directory
		 * @param filePath String variable of the path including the filename (or only the filename)
		 * @return String variable representing a path from the current woring directory to the relevant read file
		 */
		public static String toReadFile(String target, String filePath) {
			//Local variables
			String lowercaseTarget = target.toLowerCase();
			String dirPath = "";

			//Determine path to directory
			switch (lowercaseTarget) {
				case "user":
					dirPath = DIR_USER_READ_TXT;
					break;
				case "dev":
					dirPath = DIR_DEV_READ_TXT;
					break;
			}

			return buildPath(targetDir(dirPath), filePath);
		}

		/**
		 * Builds and returns a custom path to one of the predefined write destinations as documented in ConstLibrary
		 * @param target String variable (User or Dev) that stipulates the location of the target directory
		 * @param filePath String variable of the path including the filename (or only the filename)
		 * @return String variable representing a path from the current woring directory to the relevant write file
		 */
		public static String toWriteFile(String target, String filePath) {
			//Local variables
			String lowercaseTarget = target.toLowerCase();
			String dirPath = "";

			//Determine path to directory
			switch (lowercaseTarget) {
				case "user":
					dirPath = DIR_USER_WRITE_TXT;
					break;
				case "dev":
					dirPath = DIR_DEV_WRITE_TXT;
					break;
			}

			return buildPath(targetDir(dirPath), filePath);
		}
	//================================================================
}