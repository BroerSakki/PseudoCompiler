// Package Directories class to tools folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

// Import Custom Libraries
import classes.libraries.*;

public class Directories implements ConstLibrary, MethodLibrary {
	// Declarations
	//================================================================
		final public static Class<?> currentClass = Directories.class;
		public static ArrayList<String> methodIndex = new ArrayList<String>();
	//================================================================
	
	// Utility funtions
	//================================================================
		private static ArrayList<String> splitPath(String filePath) {
			// Declarations
			String[] arrPath = filePath.split("\\\\");
			ArrayList<String> path = new ArrayList<String>(Arrays.asList(arrPath));
			
			return path;
		}
		private static boolean testPathParent(String targetParent, String filePath) {
			// Declarations
			boolean isCorrectParent = targetParent.equals(getFirstParent(filePath));
			
			return isCorrectParent;
		}
		private static String customPath(String targetPath, String fileName) {
			return targetPath + getFileName(fileName);
		}
		private static String userTextFilePath() {
			// Declarations
			ArrayList<String> path = splitPath(currentDirectory());
			int rootDir = path.indexOf("PseudoCompiler");
			int dirDepth = (path.size() - 1) - rootDir;
			String targetPath = "";
			
			// Navigate to root folder
			for (int i = 0; i < dirDepth; i++) {
				targetPath = targetPath + "../";
			}
			
			targetPath = targetPath + DIR_USER_FILES_TXT;
			
			return targetPath;
		}
		private static String joinPath(String parents, String filePath) {
			// Declarations
			ArrayList<String> path = splitPath(filePath);
			String pathString = "";
			
			for (int i = 0; i < path.size() - 1; i++) {
				pathString = pathString + path.get(i) + "/";
			}
			
			pathString = pathString + getFileName(filePath);
			
			return pathString;
		}
		public static String currentDirectory() {
			return System.getProperty("user.dir");
		}
	//================================================================
	
	// Getters
	//================================================================
		public static String getFileName(String filePath) {
			// Declarations
			ArrayList<String> path = splitPath(filePath);
			
			return path.get(path.size()-1);
		}
		public static String getFirstParent(String filePath) {
			return splitPath(filePath).get(0);
		}
		public static String getFilePath(String filePath) {
			String fileName = getFileName(filePath);
			
			return customPath(userTextFilePath(), fileName);
		}
	//================================================================

	// Print Current Directory
	//================================================================
		public static void printCurrentDirectory() {
			ArrayList<String> path = splitPath(currentDirectory());
			for (int i = 0; i < path.size(); i++) {
				System.out.println(path.get(i));
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
}