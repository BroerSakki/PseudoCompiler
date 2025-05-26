// Package basic methods file in custom library
package classes.libraries;

// Import Java Classes
import java.lang.reflect.Method;
import java.util.Arrays;

// Import ConstLibrary


public interface MethodLibrary extends ConstLibrary{
	// Output data
	//================================================================
		static String[] returnMethodIndex(Class<?> clazz) {
			Method[] methods = clazz.getDeclaredMethods();
			String[] allMethods = new String[methods.length];
			
			for (int i = 0; i < methods.length; i++) {
				allMethods[i] = methods[i].getName();
			}
			
			String[] methodNames = Arrays.stream(allMethods).distinct().toArray(String[]::new);
			Arrays.sort(methodNames);
			
			return methodNames;
		}
		static void displayMethodIndex(Class<?> clazz) {
			String[] methodIndex = returnMethodIndex(clazz);
			String className = clazz.getName();
			int lastSeparator = className.lastIndexOf(".");
			System.out.println("Method index of the " + className.substring(lastSeparator+1, className.length()) +  " class:");
			for (int i = 0; i < methodIndex.length; i++) {
				System.out.println("\t- " + methodIndex[i] + "()");
			}
		}
	//================================================================
	
	// General Methods
	//================================================================
	default <T> int countOccurrences(Iterable<T> collection, T value) {
        int count = 0;

        for (T item : collection) {
            if ((value == null && item == null) || (value != null && value.equals(item))) {
                count++;
            }
        }

        return count;
    }
	default boolean containsElement(String[] arr, String match) {
		// Declarations
		boolean isEqual = false;

		// Check for match
		for (String element : arr) {
			if (element.equals(match)) {
				isEqual = true;
			}
		}

		return isEqual;
	}
	default boolean containsElement(char[] arr, char match) {
		// Declarations
		boolean isEqual = false;

		// Check for match
		for (char element : arr) {
			if (element == match) {
				isEqual = true;
			}
		}

		return isEqual;
	}
	default boolean containsElement(int[] arr, int match) {
		// Declarations
		boolean isEqual = false;

		// Check for match
		for (int element : arr) {
			if (element == match) {
				isEqual = true;
			}
		}

		return isEqual;
	}
	default boolean containsElement(double[] arr, double match) {
		// Declarations
		boolean isEqual = false;

		// Check for match
		for (double element : arr) {
			if (element == match) {
				isEqual = true;
			}
		}

		return isEqual;
	}
}