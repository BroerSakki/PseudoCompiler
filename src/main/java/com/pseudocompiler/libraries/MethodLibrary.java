// Package basic methods file in custom library
package com.pseudocompiler.libraries;

// Import Java Classes
import java.lang.reflect.Method;
import java.util.Arrays;

// Import ConstLibrary


public interface MethodLibrary extends ConstLibrary{

	// ===============================
	// Static Utility Methods
	// ===============================
	/**
	 * Returns a sorted, unique list of method names for a class.
	 */
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

	/**
	 * Prints the method index for a class.
	 */
	static void displayMethodIndex(Class<?> clazz) {
		String[] methodIndex = returnMethodIndex(clazz);
		String className = clazz.getName();
		int lastSeparator = className.lastIndexOf(".");
		System.out.println("Method index of the " + className.substring(lastSeparator+1) +  " class:");
		for (String index : methodIndex) {
			System.out.println("\t- " + index + "()");
		}
	}


	/**
	 * Converts any object to a JSON-like string using reflection.
	 * Includes all fields, even if null.
	 */
	static String toJson(Object obj) {
		return toJson(obj, false);
	}

	/**
	 * Converts any object to a JSON-like string using reflection.
	 * If ignoreNulls is true, skips fields with null values.
	 */
	static String toJson(Object obj, boolean ignoreNulls) {
		if (obj == null) return "null";
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = obj.getClass();

		if (clazz.isArray()) {
			sb.append("[");
			int len = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				Object val = java.lang.reflect.Array.get(obj, i);
				sb.append(toJson(val, ignoreNulls));
				if (i < len - 1) sb.append(", ");
			}
			sb.append("]");
		} else if (obj instanceof String) {
			sb.append("\"").append(obj).append("\"");
		} else if (clazz.isPrimitive() || obj instanceof Number || obj instanceof Boolean) {
			sb.append(obj.toString());
		} else {
			sb.append("{");
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			boolean first = true;
			for (java.lang.reflect.Field field : fields) {
				field.setAccessible(true);
				Object fieldValue;
				try {
					fieldValue = field.get(obj);
				} catch (IllegalAccessException e) {
					fieldValue = "<error>";
				}
				if (ignoreNulls && fieldValue == null) {
					continue;
				}
				if (!first) sb.append(", ");
				sb.append("\"").append(field.getName()).append("\": ");
				if (fieldValue == "<error>") {
					sb.append("\"<error>\"");
				} else if (fieldValue != null && fieldValue.getClass().isArray()) {
					sb.append(toJson(fieldValue, ignoreNulls));
				} else {
					sb.append(toJson(fieldValue, ignoreNulls));
				}
				first = false;
			}
			sb.append("}");
		}
		return sb.toString();
	}

	// ===============================
	// General Default Methods
	// ===============================
	/**
	 * Counts the number of occurrences of a value in a collection.
	 */
	default <T> int countOccurrences(Iterable<T> collection, T value) {
		int count = 0;
		for (T item : collection) {
			if ((value == null && item == null) || (value != null && value.equals(item))) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Checks if a String array contains a match.
	 */
	default boolean containsElement(String[] arr, String match) {
		for (String element : arr) {
			if (element.equals(match)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a char array contains a match.
	 */
	default boolean containsElement(char[] arr, char match) {
		for (char element : arr) {
			if (element == match) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if an int array contains a match.
	 */
	default boolean containsElement(int[] arr, int match) {
		for (int element : arr) {
			if (element == match) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a double array contains a match.
	 */
	default boolean containsElement(double[] arr, double match) {
		for (double element : arr) {
			if (element == match) {
				return true;
			}
		}
		return false;
	}
}