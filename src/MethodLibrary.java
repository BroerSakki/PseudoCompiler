// Package basic methods file in custom library
package classes.libraries;

// Import Java Classes
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ArrayList;

public interface MethodLibrary {
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
}