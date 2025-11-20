// Package Formatter to tools folder
package com.pseudocompiler.tools;

// Import Java Classes
/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
*/

// Import Custom Libraries
import com.pseudocompiler.libraries.MethodLibrary;

public class Formatter implements MethodLibrary {
	// Declarations
	//================================================================
		private static int spaceTop = 0;
		private static int spaceBottom = 0;
		private static final ColoringTools coloring = new ColoringTools();
	//================================================================
	
	// Reset Variables
	//================================================================
		/**
		 * Resets spacing variables to defaults
		 */
		public static void resetDefaults() {
			spaceTop = 0;
			spaceBottom = 0;
		}
	//================================================================
	
	// Spacing Modifiers
	//================================================================
		/**
		 * Sets top spacing variables to given value
		 * @param size Top spacing
		 */
		public static void modSpaceTop(int size) {
			spaceTop = size;
		}
		/**
		 * Sets bottom spacing variables to given value
		 * @param size Bottom spacing
		 */
		public static void modSpaceBottom(int size) {
			spaceBottom = size;
		}
		/**
		 * Sets top and bottom spacing variables to given values
		 * @param top Top spacing
		 * @param bottom Bottom spacing
		 */
		public static void modSpace(int top, int bottom) {
			spaceTop = top;
			spaceBottom = bottom;
		}
	//================================================================
	
	// Spacing Methods
	//================================================================
		/**
		 * Creates newline spaces
		 * @param lines Amount of lines
		 */
		public static void space(int lines) {
			for (int i = 1; i <= lines; i++) {
				System.out.println();
			}
		}
	//================================================================
	
	// Overloaded makeLine Method
	//================================================================
		/**
		 * Prints a default line
		 */
		public static void makeLine() {
			space(spaceTop);
			System.out.println(FORMAT_DEFAULT_LINE);
			space(spaceBottom);
		}
		/**
		 * Prints a line of given character
		 * @param lineChar Character to use for the line
		 */
		public static void makeLine(char lineChar) {
			space(spaceTop);
			for (int i = 1; i <= 40; i++) {
				System.out.print(lineChar);
			}
			System.out.println();
			space(spaceBottom);
		}
		/**
		 * Prints a line of given character and length
		 * @param lineChar Character to use for the line
		 * @param len Length of line
		 */
		public static void makeLine(char lineChar, int len) {
			space(spaceTop);
			for (int i = 1; i <= len; i++) {
				System.out.print(lineChar);
			}
			System.out.println();
			space(spaceBottom);
		}
		/**
		 * Prints a colored line of given character, length and color
		 * @param lineChar Character to use for the line
		 * @param len Length of line
		 * @param rgb Color of line
		 */
		public static void makeLine(char lineChar, int len, int[] rgb) {
			space(spaceTop);
			for (int i = 1; i <= len; i++) {
				coloring.recolorChar(lineChar, rgb, true);
			}
			System.out.println();
			space(spaceBottom);
		}
		/**
		 * Prints a rainbow line of given character and length
		 * @param lineChar Character to use for the line
		 * @param len Length of line
		 */
		public static void makeRainbowLine(char lineChar, int len) {
			space(spaceTop);
			StringBuilder line = new StringBuilder();
			for (int i = 1; i <= len; i++) {
				line.append(lineChar);
			}
			coloring.dynamicRainbowText(line.toString(), false, true);
			System.out.println();
			space(spaceBottom);
		}
		/**
		 * Prints a line of given character, length and start and end colors
		 * @param lineChar Character to use for the line
		 * @param len Length of line
		 * @param rgb1 First rgb color
		 * @param rgb2 Second rgb color
		 */
		public static void makeGradientLine(char lineChar, int len, int[] rgb1, int[] rgb2) {
			space(spaceTop);
            coloring.dynamicGradientText(String.valueOf(lineChar).repeat(Math.max(0, len)), rgb1, rgb2, false, true);
			System.out.println();
			space(spaceBottom);
		}
	//================================================================
}