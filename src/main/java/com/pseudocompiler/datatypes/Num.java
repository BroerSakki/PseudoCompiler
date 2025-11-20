// Package Num class to datatypes folder
package com.pseudocompiler.datatypes;

// Import MethodLibrary interface
import com.pseudocompiler.libraries.MethodLibrary;


public class Num implements MethodLibrary {
	// Global variables
	//================================================================
	private String value;
	private String[] values;
	private boolean isArray; // Indicates if this is an array of values
	//================================================================


	// Constructors
	//================================================================
	public Num(String value) {
		this.value = value;
		this.values = null;
		setIsArray(false);
	}
	public Num(String[] values) {
		this.values = values;
		this.value = null;
		setIsArray(true);
	}
	//================================================================


	// Setters
	//================================================================
	// Only allow updating the current mode (single or array), not switching between them
	/**
	 * Sets the value for a single-value Num.
	 * If this is an array Num, it will not change the values.
	 * @param value The new value to set.
	 */
	public void setValue(String value) {
		if (!isArray) { // Only if this is a single-value Num
			this.value = value;
			setIsArray(false);
		}
	}
	/**
	 * Sets the value at a specific index for an array-value Num.
	 * If this is a single-value Num, it will throw an exception.
	 * @param index The index to set the value at.
	 * @param value The new value to set at the specified index.
	 */
	public void setValue(int index, String value) {
		if (isArray && index >= 0 && index < this.values.length) {
			this.values[index] = value;
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds for values array.");
		}
	}
	/**
	 * Sets the values for an array-value Num.
	 * If this is a single-value Num, it will not change the value.
	 * @param values The new array of values to set.
	 */
	public void setValues(String[] values) {
		if (isArray) { // Only if this is an array-value Num
			this.values = values;
			setIsArray(true);
		}
	}
	private void setIsArray(boolean isArray) {
		this.isArray = isArray;
	}
	//================================================================


	// Getters
	//================================================================
	/**
	 * Gets the value of this Num.
	 * If this is an array Num, it will return null.
	 * @return The single value or null if this is an array Num.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * Gets the values of this Num.
	 * If this is a single-value Num, it will return null.
	 * @return The array of values or null if this is a single-value Num.
	 */
	public String[] getValues() {
		return values;
	}
	/**
	 * Checks if this Num is an array of values.
	 * @return true if this Num is an array, false otherwise.
	 */
	public boolean getIsArray() {
		return isArray;
	}
	//================================================================

	// Methods
	//================================================================
	/**
	 * Returns a string representation of this Num.
	 * If this is a single-value Num, it returns the value.
	 * If this is an array Num, it returns the values as a comma-separated string.
	 * @return A string representation of this Num.
	 */
	@Override
	public String toString() {
		return MethodLibrary.toJson(this, true);
	}
	/**
	 * If this is a single-value Num, it returns the value.
	 * If this is an array Num, it returns the values as a JSON array.
	 * @return the integer value of this Num
	 */
	public int toInt() {
		if (isArray) {
			throw new UnsupportedOperationException("Cannot convert an array Num to int.");
		}
		// Parse as double, then cast to int
		return (int) Double.parseDouble(value);
	}
	/**
	 * Converts this Num to an int array.
	 * If this is an array Num, it returns the values as an int array.
	 * @return an int array representation of this Num
	 */
	public int[] toIntArray() {
		if (!isArray) {
			throw new UnsupportedOperationException("Cannot convert a single-value Num to int array.");
		}
		return java.util.Arrays.stream(values)
			.mapToInt(s -> (int) Double.parseDouble(s))
			.toArray();
	}
	/**
	 * Converts this Num to a double.
	 * If this is an array Num, it throws an exception.
	 * @return the double value of this Num
	 */
	public double toDouble() {
		if (isArray) {
			throw new UnsupportedOperationException("Cannot convert an array Num to double.");
		}
		return Double.parseDouble(value);
	}
	/**
	 * Converts this Num to a double array.
	 * If this is a single-value Num, it throws an exception.
	 * @return a double array representation of this Num
	 */
	public double[] toDoubleArray() {
		if (!isArray) {
			throw new UnsupportedOperationException("Cannot convert a single-value Num to double array.");
		}
		return java.util.Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
	}
	//================================================================
}