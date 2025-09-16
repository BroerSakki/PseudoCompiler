// Package global constants file in custom library
package com.pseudocompiler.tools;

// Import custom tools
import com.pseudocompiler.libraries.ConstLibrary;
import com.pseudocompiler.libraries.ColorLibrary;

public interface StartupTools {
    // Create MethodLibrary Object
	ColoringTools coloring = new ColoringTools();
    
    //Display Logo
	/**
	 * Displays default rainbow Logo
	 */
	public static void displayLogoRainbow()
	{
		coloring.rainbowText(ConstLibrary.LOGO_IMAGE, ConstLibrary.LOGO_RAINBOW_LEFT_SMALL_STEPS, false, true);
	}

	/**
	 * Displays rainbow logo with given direction and size
	 * @param left Rainbow goes to the left if true else to the right
	 * @param big Rainbow is big when true else small
	 */
	public static void displayLogoRainbow(boolean left, boolean big)
	{
		if (left)
		{
			if (big)
			{
				coloring.rainbowText(ConstLibrary.LOGO_IMAGE, ConstLibrary.LOGO_RAINBOW_LEFT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.rainbowText(ConstLibrary.LOGO_IMAGE, ConstLibrary.LOGO_RAINBOW_LEFT_SMALL_STEPS, false, true);
			}
		}
		else
		{
			if (big)
			{
				coloring.rainbowText(ConstLibrary.LOGO_IMAGE, ConstLibrary.LOGO_RAINBOW_RIGHT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.rainbowText(ConstLibrary.LOGO_IMAGE, ConstLibrary.LOGO_RAINBOW_RIGHT_SMALL_STEPS, false, true);
			}
		}
	}

	/**
	 * Displays default gradient logo
	 */
	public static void displayLogoGradient()
	{
		coloring.gradientText(ConstLibrary.LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.LOGO_GRADIENT_BIG_STEPS, false, true);
	}

	/**
	 * Displays default gradient color logo with given direction and size
	 * @param big Gradient is big when true else small
	 * @param left Gradient goes to the left if true else to the right
	 */
	public static void displayLogoGradient(boolean big, boolean left)
	{
		if (big)
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.LOGO_GRADIENT_BIG_STEPS, false, true);
		}
		else if (left)
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.LOGO_GRADIENT_LEFT_STEPS, false, true);
		}
		else
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.LOGO_GRADIENT_RIGHT_STEPS, false, true);
		}
	}

	/**
	 * Displays gradient logo with given start and end colors and given direction and size
	 * @param rgb1 First rgb color
	 * @param rgb2 Second rgb color
	 * @param big Gradient is big when true else small
	 * @param left Gradient goes to the left if true else to the right
	 */
	public static void displayLogoGradient(int[] rgb1, int[] rgb2, boolean big, boolean left)
	{
		if (big)
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, rgb1, rgb2, ConstLibrary.LOGO_GRADIENT_BIG_STEPS, false, true);
		}
		else if (left)
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, rgb1, rgb2, ConstLibrary.LOGO_GRADIENT_LEFT_STEPS, false, true);
		}
		else
		{
			coloring.gradientText(ConstLibrary.LOGO_IMAGE, rgb1, rgb2, ConstLibrary.LOGO_GRADIENT_RIGHT_STEPS, false, true);
		}
	}

	//Display Credits
	/**
	 * Displays default rainbow credits
	 */
	public static void displayCreditsRainbow()
	{
		coloring.rainbowText(ConstLibrary.SAKKI_IMAGE, ConstLibrary.CREDITS_RAINBOW_LEFT_STEPS, false, true);
		coloring.rainbowText(ConstLibrary.RASSIE_IMAGE, ConstLibrary.CREDITS_RAINBOW_LEFT_STEPS, false, true);
	}

	/**
	 * Displays default rainbow credits with given direction
	 * @param left Rainbow goes to the left if true else to the right
	 */
	public static void displayCreditsRainbow(boolean left)
	{
		if (left)
		{
			coloring.rainbowText(ConstLibrary.SAKKI_IMAGE, ConstLibrary.CREDITS_RAINBOW_LEFT_STEPS, false, true);
			coloring.rainbowText(ConstLibrary.RASSIE_IMAGE, ConstLibrary.CREDITS_RAINBOW_LEFT_STEPS, false, true);
		}
		else
		{
			coloring.rainbowText(ConstLibrary.SAKKI_IMAGE, ConstLibrary.CREDITS_RAINBOW_RIGHT_STEPS, false, true);
			coloring.rainbowText(ConstLibrary.RASSIE_IMAGE, ConstLibrary.CREDITS_RAINBOW_RIGHT_STEPS, false, true);
		}
	}

	/**
	 * Displays default gradient credits
	 */
	public static void displayCreditsGradient()
	{
		coloring.gradientText(ConstLibrary.SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.PURPLE, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
		coloring.gradientText(ConstLibrary.RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.PURPLE, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
	}

	/**
	 * Displays default gradient credits with given direction and size
	 * @param left Gradient goes to the left if true else to the right
	 * @param big Gradient is big when true else small
	 */
	public static void displayCreditsGradient(boolean left, boolean big)
	{
		if (left)
		{
			if (big)
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_LEFT_BIG_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_LEFT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_LEFT_SMALL_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_LEFT_SMALL_STEPS, false, true);
			}
		}
		else
		{
			if (big)
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, ConstLibrary.CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false, true);
			}
		}
	}

	/**
	 * Displays gradient credits with given start and end colors and given direction and size
	 * @param rgb1 First rgb color
	 * @param rgb2 Second rgb color
	 * @param left Gradient goes to the left if true else to the right
	 * @param big Gradient is big when true else small
	 */
	public static void displayCreditsGradient(int[] rgb1, int[] rgb2, boolean left, boolean big)
	{
		if (left)
		{
			if (big)
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_LEFT_BIG_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_LEFT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_LEFT_SMALL_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_LEFT_SMALL_STEPS, false, true);
			}
		}
		else
		{
			if (big)
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_RIGHT_BIG_STEPS, false, true);
			}
			else
			{
				coloring.gradientText(ConstLibrary.SAKKI_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false, true);
				coloring.gradientText(ConstLibrary.RASSIE_IMAGE, rgb1, rgb2, ConstLibrary.CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false, true);
			}
		}
		
	}
}
