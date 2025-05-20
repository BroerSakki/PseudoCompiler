// Package basic methods file in custom library
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Import Custom Libraries
import classes.libraries.MethodLibrary;
import classes.libraries.ConstLibrary;

public class ColoringTools implements MethodLibrary
{
	//Cool tests wat werk
	//Gebruik recolorText om text enige rgb kleur te gee
	//Gebruik Dynamic vir single line strings wat die hele rainbow moet bevat
	//Gebruik 'n step van 50 as \n gebruik word vir matching rainbow effect
	//Gebruik rainbow met start rgb om die rainbow se begin kleur te specify
	
    final public static Class<?> currentClass = ColoringTools.class;
	public static ArrayList<String> methodIndex = new ArrayList<String>();

	/**
	 * Changes and returns color of text string
	 * @param text The text to recolor
	 * @param rgb The rgb color to recolor to
	 * @return Recolored text when print is false else prints recolored text and returns empty string
	 */
	public String recolorText(String text, int[] rgb, boolean print)
	{
		//Set rgb colors using R, G, B values
		String ANSI_COLOR = "\033[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
		//Creates colored text
		String recoloredText = ANSI_COLOR + text + ANSI_RESET;
		
		if (print) {
			System.out.print(recoloredText);
			return "";
		} else {
			//Returns colored text
			return recoloredText;
		}
	}

	/**
	 * Changes and returns color of text character
	 * @param character The character to recolor
	 * @param rgb The rgb color to recolor to
	 * @return Recolored character when print is false else prints recolored character and returns empty string
	 */
	public String recolorChar(char character, int[] rgb, boolean print)
	{
		//Set rgb colors using R, G, B values
		String ANSI_COLOR = "\033[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
		//Creates colored text
		String recoloredCharacter = ANSI_COLOR + character + ANSI_RESET;
		
		if (print) {
			System.out.print(recoloredCharacter);
			return "";
		} else {
			//Returns colored text
			return recoloredCharacter;
		}
	}
	
	/**
	 * Changes text color with full spectrum of rainbow, dynamicaly (Meaning the step count is found dynamically)
	 * @param text The text to recolor
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String dynamicRainbowText(String text, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");
		
        //Initialize rgb colors
		int r = 255;
		int g = 0;
		int b = 0;

		//Initialize boolean
		boolean colorChar;

		int characters;

		//Determines if spaces should be included
		if (ignoreSpaces)
		{
			characters = countCharacters(text, " ");
		}
		else
		{
			characters = text.length();
		}
		
        //Determines amount of steps
		int steps = 1000 / characters;
		
		//Create and print Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}

			if (colorChar)
			{
				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
				
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
				
				if (r >= 255 && g < 255 && b <= 0) {
					g+= steps;
				}
				if (g >= 255 && r > 0 && b <= 0) {
					r-= steps;
				}
				if (g >= 255 && b < 255 && r <= 0) {
					b+= steps;
				}
				if (b >= 255 && g > 0 && r <= 0) {
					g-= steps;
				}
				if (b >= 255 && r < 255 && g <= 0) {
					r+= steps;
				}
				if (r >= 255 && b > 0 && g <= 0) {
					b-= steps;
				}

				if(r > 255) {
					r = 255;
				}
				if(g > 255) {
					g = 255;
				}
				if(b > 255) {
					b = 255;
				}
				
				if(r < 0) {
					r = 0;
				}
				if(g < 0) {
					g = 0;
				}
				if(b < 0) {
					b = 0;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Changes text color with full spectrum of rainbow with a start rgb color, dynamicaly (Meaning the step count is found dynamically)
	 * @param text The text to recolor
	 * @param rgb The start rgb color to use in the rainbow
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String dynamicRainbowText(String text, int[] rgb, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");
		
        //Initialize rgb colors
		int r = rgb[0];
		int g = rgb[1];
		int b = rgb[2];

		//Initialize boolean
		boolean colorChar;

		int characters;

		//Determines if spaces should be included
		if (ignoreSpaces)
		{
			characters = countCharacters(text, " ");
		}
		else
		{
			characters = text.length();
		}
		
        //Determines amount of steps
		int steps = 1000 / characters;

		if (steps < 1)
		{
			steps = 1;
		}
		
		//Create and print Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}

			if (colorChar)
			{
				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
				
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
				
				if (r >= 255 && g < 255 && b <= 0) {
					g+= steps;
				}
				if (g >= 255 && r > 0 && b <= 0) {
					r-= steps;
				}
				if (g >= 255 && b < 255 && r <= 0) {
					b+= steps;
				}
				if (b >= 255 && g > 0 && r <= 0) {
					g-= steps;
				}
				if (b >= 255 && r < 255 && g <= 0) {
					r+= steps;
				}
				if (r >= 255 && b > 0 && g <= 0) {
					b-= steps;
				}

				if(r > 255) {
					r = 255;
				}
				if(g > 255) {
					g = 255;
				}
				if(b > 255) {
					b = 255;
				}

				if(r < 0) {
					r = 0;
				}
				if(g < 0) {
					g = 0;
				}
				if(b < 0) {
					b = 0;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Changes text color with full spectrum of rainbow (For a very long rainbow effect)
	 * @param text The text to recolor
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String rainbowText(String text, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
    
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");
    
        //Initialize rgb colors
		int r = 255;
		int g = 0;
		int b = 0;

		//Initialize boolean
		boolean colorChar;
    
		//Create Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}
			if (colorChar)
			{
				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
				
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
				
				if (r == 255 && g < 255 && b == 0) {
					g++;
				}
				if (g == 255 && r > 0 && b == 0) {
					r--;
				}
				if (g == 255 && b < 255 && r == 0) {
					b++;
				}
				if (b == 255 && g > 0 && r == 0) {
					g--;
				}
				if (b == 255 && r < 255 && g == 0) {
					r++;
				}
				if (r == 255 && b > 0 && g == 0) {
					b--;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Changes text color with rainbow of given steps (Used for shorter rainbows)
	 * @param text The text to recolor
	 * @param steps The rate at which the rainbow increases (1 for slow increase and larger for larger increase)
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String rainbowText(String text, int steps, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
    
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");
    
        //Initialize rgb colors
		int r = 255;
		int g = 0;
		int b = 0;

		//Initialize boolean
		boolean colorChar;
    
		//Create Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}
			if (colorChar)
			{
				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
				
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
				
				if (r >= 255 && g < 255 && b <= 0) {
					g+= steps;
				}
				if (g >= 255 && r > 0 && b <= 0) {
					r-= steps;
				}
				if (g >= 255 && b < 255 && r <= 0) {
					b+= steps;
				}
				if (b >= 255 && g > 0 && r <= 0) {
					g-= steps;
				}
				if (b >= 255 && r < 255 && g <= 0) {
					r+= steps;
				}
				if (r >= 255 && b > 0 && g <= 0) {
					b-= steps;
				}
			
				if(r > 255) {
					r = 255;
				}
				if(g > 255) {
					g = 255;
				}
				if(b > 255) {
					b = 255;
				}
			
				if(r < 0) {
					r = 0;
				}
				if(g < 0) {
					g = 0;
				}
				if(b < 0) {
					b = 0;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Changes text color with rainbow of given start rgb values (Used to specify where rainbow should start)
	 * @param text The text to recolor
	 * @param rgb The start rgb color to use in the rainbow
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String rainbowText(String text, int[] rgb, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
    
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");
    
        //Initialize rgb colors
		int r = rgb[0];
		int g = rgb[1];
		int b = rgb[2];

		//Initialize boolean
		boolean colorChar;
    
		//Create Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}
			if (colorChar)
			{
				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
				
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
				
				if (r == 255 && g < 255 && b == 0) {
					g++;
				}
				if (g == 255 && r > 0 && b == 0) {
					r--;
				}
				if (g == 255 && b < 255 && r == 0) {
					b++;
				}
				if (b == 255 && g > 0 && r == 0) {
					g--;
				}
				if (b == 255 && r < 255 && g == 0) {
					r++;
				}
				if (r == 255 && b > 0 && g == 0) {
					b--;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Recolors text according to a gradient of two specified colors, with default step of 1
	 * @param text The text to recolor
	 * @param rgb1 The first rgb color to use in the gradient
	 * @param rgb2 The second rgb color to use in the gradient
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @param print Whether to print or return text
	 * @return String when print is false else prints text and returns empty string
	 */
	public String gradientText(String text, int[] rgb1, int[] rgb2, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
        
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");

		//Initialize doubles
		double value = 0;
		double valueDouble;

		//Initialize rgb variables
		int r;
		int g;
		int b;

		//Initialize booleans
		boolean direction = true;
		boolean colorChar;

		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}

			if (colorChar)
			{
				if (value >= 100)
				{
					direction = false;
				}

				if (value <= 0)
				{
					direction = true;
				}

				valueDouble = value / 100;

				r = (int)(rgb1[0] + ((rgb2[0] - rgb1[0]) * valueDouble));
				g = (int)(rgb1[1] + ((rgb2[1] - rgb1[1]) * valueDouble));
				b = (int)(rgb1[2] + ((rgb2[2] - rgb1[2]) * valueDouble));

				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";

				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);

				if (direction)
				{
					value ++;
				}
				else
				{
					value --;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Recolors text according to a gradient of two specified colors, with steps specified
	 * @param text The text to recolor
	 * @param rgb1 The first rgb color to use in the gradient
	 * @param rgb2 The second rgb color to use in the gradient
	 * @param steps The rate at which the gradient increases (1 for slow increase and larger for larger increase)
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @returns String when print is false else prints text and returns empty string
	 */
	public String gradientText(String text, int[] rgb1, int[] rgb2, int steps, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
        
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");

		//Initialize doubles
		double value = 0;
		double valueDouble;

		//Initialize rgb variables
		int r;
		int g;
		int b;

		//Initialize booleans
		boolean direction = true;
		boolean colorChar;

		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}

			if (colorChar)
			{
				if (value >= 100)
				{
					direction = false;
				}

				if (value <= 0)
				{
					direction = true;
				}

				valueDouble = value / 100;

				r = (int)(rgb1[0] + ((rgb2[0] - rgb1[0]) * valueDouble));
				g = (int)(rgb1[1] + ((rgb2[1] - rgb1[1]) * valueDouble));
				b = (int)(rgb1[2] + ((rgb2[2] - rgb1[2]) * valueDouble));

				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
	    		
				outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);

				if (direction)
				{
					value += steps;
				}
				else
				{
					value -= steps;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Recolors text according to a gradient of two specified colors, dynamically (Meaning the step count is found dynamically)
	 * @param text The text to recolor
	 * @param rgb1 The first rgb color to use in the gradient
	 * @param rgb2 The second rgb color to use in the gradient
	 * @param ignoreSpaces Whether or not to ignore spaces
	 * @return String when print is false else prints text and returns empty string
	 */
	public String dynamicGradientText(String text, int[] rgb1, int[] rgb2, boolean ignoreSpaces, boolean print)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
        
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		StringBuilder outputText = new StringBuilder("");

		//Initialize doubles
		double value = 0;
		double valueDouble;

		//Initialize rgb variables
		int r;
		int g;
		int b;

		//Initialize booleans
		boolean direction = true;
		boolean colorChar;

		int characters;

		//Determines if spaces should be included
		if (ignoreSpaces)
		{
			characters = countCharacters(text, " ");
		}
		else
		{
			characters = text.length();
		}

		double steps = 100.0 / (characters - 1);

		if (steps < 1)
		{
			steps = 1;
		}

		for (int i = 0; i < text.length(); i++)
		{
			//Determines if spaces should be included
			if (ignoreSpaces)
			{
				if (text.charAt(i) != ' ')
				{
					colorChar = true;
				}
				else
				{
					colorChar = false;
				}
			}
			else
			{
				colorChar = true;
			}

			if (colorChar)
			{
				if (value >= 100)
				{
					direction = false;
				}

				if (value <= 0)
				{
					direction = true;
				}

				valueDouble = value / 100;

				r = (int)(rgb1[0] + ((rgb2[0] - rgb1[0]) * valueDouble));
				g = (int)(rgb1[1] + ((rgb2[1] - rgb1[1]) * valueDouble));
				b = (int)(rgb1[2] + ((rgb2[2] - rgb1[2]) * valueDouble));

				ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
	    		outputText.append(ANSI_COLOR + text.charAt(i) + ANSI_RESET);

				if (direction)
				{
					value += steps;
				}
				else
				{
					value -= steps;
				}
			}
			else
			{
				outputText.append(text.charAt(i));
			}
		}

		if (print) {
			System.out.print(outputText);
			return "";
		} else {
			String outputStringText = outputText.toString();
			return outputStringText;
		}
	}

	/**
	 * Counts all characters in string excluding given character
	 * @param text  String to count characters of
	 * @param charToExclude  The character to exclude when counting
	 * @return Integer of the count of the characters
	 */
	private static int countCharacters(String text, String charToExclude)
	{
		int count = text.replaceAll(charToExclude, "").length();
		return count;
	}

    // Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			MethodLibrary.displayMethodIndex(currentClass);
		}
	//================================================================NaN
    
    // Constructor
	//================================================================
		/**
		 * Constructor for ColoringTools class
		 */
		public ColoringTools() {
			List<String> list = Arrays.asList(MethodLibrary.returnMethodIndex(currentClass));
			methodIndex = new ArrayList<>(list);
		}
	//================================================================
}