// Package Directories class to tools folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// Import Custom Libraries
import classes.libraries.MethodLibrary;

public class Directories implements MethodLibrary {
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
		 * Separate parts of a path and identify the file name
		 * @param filePath String variable that includes the path (complete or incomplete) to a desired file
		 * @return String variable representing only the file name (includes possible file extension)
		 */
		private static String fileName(String filePath) {
			//Local variables
			ArrayList<String> path = splitPath(filePath);
			return path.getLast();
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

            return "../".repeat(Math.max(0, dirDepth)) +
                    dirPath;
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

            return new ArrayList<>(Arrays.asList(arrPath));
		}

		/**
		 * Separately build the directory to be used and find the name of the file before concatenating into 1 final path
		 * @param dirPath String variable representing the path starting from the "Pseudocompiler" directory
		 * @param fileNameOrPath String variable representing the file name (can also be a path)
		 * @return String variable representing the concatenated path
		 */
		private static String buildPath(String dirPath, String fileNameOrPath) {
			//Local variables
			String dir = targetDir(dirPath);
			
			return dir + fileName(fileNameOrPath);
		}
	//================================================================

	// Use methods
	//================================================================
		/**
		 * Builds and returns a custom path to the predefined read destination as documented in ConstLibrary
		 * @param fileNameOrPath String variable of the path including the filename (or only the filename)
		 * @return Path variable of the current working directory to the target read file
		 */
		public static Path readPath(String fileNameOrPath) {
			return Paths.get(buildPath(targetDir(DIR_USER_READ_TXT), fileNameOrPath));
		}
		/**
		 * Builds and returns a custom path to the predefined write destination as documented in ConstLibrary
		 * @param fileNameOrPath String variable of the path including the filename (or only the filename)
		 * @return Path variable of the current working directory to the target write file
		 */
		public static Path writePath(String fileNameOrPath) {
			return Paths.get(buildPath(targetDir(DIR_USER_WRITE_TXT), fileNameOrPath));
		}
	
		/**
		 * Find a read file from a String either written as a path or simply a file name
		 * @param fileNameOrPath String variable of either the file name or path
		 * @return File variable from read folder
		 */
		public static File readFile(String fileNameOrPath) {
			//Local variables
			File attemptFile = null;

			try {
				attemptFile = readPath(fileNameOrPath).toFile();
			} catch (Exception e) {
				System.out.println("Error: File not found");
			}

			return attemptFile;
		}
		/**
		 * Find a write file from a String either written as a path or simply a file name
		 * @param fileNameOrPath String variable of either the file name or path
		 * @return File variable from write folder
		 */
		public static File writeFile(String fileNameOrPath) {
			//Local variables
			File attemptFile = null;

			try {
				attemptFile = writePath(fileNameOrPath).toFile();
			} catch (Exception e) {
				System.out.println("Error: File not found");
			}

			return attemptFile;
		}
	//================================================================
}