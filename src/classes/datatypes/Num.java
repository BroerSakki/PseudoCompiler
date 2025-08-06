// Package Num class to datatypes folder
package classes.datatypes;

// Import Java Classes
import classes.libraries.MethodLibrary;


public class Num implements MethodLibrary {
	// Global variables
	//================================================================
	private String value = null;
	private String[] values = null;
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
	public void setValue(String value) {
		if (!isArray) { // Only if this is a single-value Num
			this.value = value;
			setIsArray(false);
		}
	}
	public void setValue(int index, String value) {
		if (isArray && index >= 0 && index < this.values.length) {
			this.values[index] = value;
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds for values array.");
		}
	}
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
	public String getValue() {
		return value;
	}
	public String[] getValues() {
		return values;
	}
	public boolean getIsArray() {
		return isArray;
	}
	//================================================================

	// Methods
	//================================================================
	@Override
	public String toString() {
		return MethodLibrary.toJson(this, true);
	}
	public int toInt() {
		if (isArray) {
			throw new UnsupportedOperationException("Cannot convert an array Num to int.");
		}
		// Parse as double, then cast to int
		return (int) Double.parseDouble(value);
	}
	public int[] toIntArray() {
		if (!isArray) {
			throw new UnsupportedOperationException("Cannot convert a single-value Num to int array.");
		}
		return java.util.Arrays.stream(values)
			.mapToInt(s -> (int) Double.parseDouble(s))
			.toArray();
	}
	public double toDouble() {
		if (isArray) {
			throw new UnsupportedOperationException("Cannot convert an array Num to double.");
		}
		return Double.parseDouble(value);
	}
	public double[] toDoubleArray() {
		if (!isArray) {
			throw new UnsupportedOperationException("Cannot convert a single-value Num to double array.");
		}
		return java.util.Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
	}
	//================================================================
}