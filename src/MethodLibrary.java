// Package basic methods file in custom library
package classes.libraries;

import java.lang.reflect.Array;
// Import Java Classes
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// Import ConstLibrary
import classes.libraries.ConstLibrary;

public interface MethodLibrary extends ConstLibrary{
	// Output data
	//================================================================
		default String[] returnMethodIndex(Class<?> clazz) {
			Method[] methods = clazz.getDeclaredMethods();
			String[] allMethods = new String[methods.length];
			
			for (int i = 0; i < methods.length; i++) {
				allMethods[i] = methods[i].getName();
			}
			
			String[] methodNames = Arrays.stream(allMethods).distinct().toArray(String[]::new);
			Arrays.sort(methodNames);
			
			return methodNames;
		}
		default void displayMethodIndex(Class<?> clazz) {
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

	// default List<String> splitKeepingQuotes(String input) {
	// 	String reduced = input.replaceAll("\\s+", " ");
    //     List<String> result = new ArrayList<>();
    //     StringBuilder quoteMaker = new StringBuilder();
	// 	StringBuilder bracketMaker = new StringBuilder();
    //     boolean inQuotes = false;
	// 	int[] bracketDepth = {0, 0};
    //     char quoteChar = '\0'; // To track which quote character is being used
	// 	char bracketChar = '\0';

    //     for (char c : reduced.toCharArray()) {
    //         if (containsElement(TOKEN_OPERATORS_GROUPING, c)) {
	// 			if (!inQuotes) {
	// 			// Toggle the inBrackets flag
	// 				switch (c) {
	// 					case '\'':
	// 						inQuotes = true;
	// 						quoteChar = c;
	// 						break;
	// 					case '\"':
	// 						inQuotes = true;
	// 						quoteChar = c;
	// 						break;
	// 					case '(':
	// 						bracketDepth[0]++;
	// 						bracketChar = ')';
	// 						break;
	// 					case '[':
	// 						bracketDepth[1]++;
	// 						bracketChar = ']';
	// 						break;
	// 					case ')':
	// 						bracketDepth[0]--;
	// 						break;
	// 					case ']':
	// 						bracketDepth[1]--;
	// 						break;
	// 					case ',' :
	// 						result.add(String.valueOf(c));
	// 						break;
	// 				}
	// 			} else if (quoteChar == c) {
	// 				inQuotes = false;
	// 			}

	// 			quoteMaker.append(c);


	// 		} else if (c == ' ' && !inQuotes && bracketDepth[0] == 0 && bracketDepth[1] == 0) {
    //             // Finalize the current substring
    //             result.add(quoteMaker.toString().trim());
    //             quoteMaker.setLength(0); // Clear the StringBuilder for the next substring
	// 		} else {
	// 			quoteMaker.append(c);
	// 		}
    //     }

    //     // Add the last substring if there's any content left
    //     if (quoteMaker.length() > 0) {
    //         result.add(current.toString().trim());
    //     }

    //     return result;
    // }
	//================================================================
}