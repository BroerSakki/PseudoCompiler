// Package global constants file in custom library
package classes.libraries;

// Import custom tools
import classes.libraries.ColorLibrary;
import classes.tools.ColoringTools;

public interface ConstLibrary {
	// Create MethodLibrary Object
	ColoringTools coloring = new ColoringTools();
	
	//Logos
	final public static String LOGO_IMAGE = " _____                    _             _____                      _ _           \r\n" + //
											"|  __ \\                  | |           / ____|                    (_) |          \r\n" + //
											"| |__) |__  ___ _   _  __| | ___   ___| |     ___  _ __ ___  _ __  _| | ___ _ __ \r\n" + //
											"|  ___/ __|/ _ \\ | | |/ _` |/ _ \\ / __| |    / _ \\| '_ ` _ \\| '_ \\| | |/ _ \\ '__|\r\n" + //
											"| |   \\__ \\  __/ |_| | (_| | (_) | (__| |___| (_) | | | | | | |_) | | |  __/ |   \r\n" + //
											"|_|   |___/\\___|\\__,_|\\__,_|\\___/ \\___|\\_____\\___/|_| |_| |_| .__/|_|_|\\___|_|   \r\n" + //
											"                                                            | |                  \r\n" + //
											"                                                            |_|                  \n";

	final public static String SAKKI_IMAGE = "  _____       _    _    _ \r\n" + //
											 " / ____|     | |  | |  (_)\r\n" + //
											 "| (___   __ _| | _| | ___ \r\n" + //
											 " \\___ \\ / _` | |/ / |/ / |\r\n" + //
											 " ____) | (_| |   <|   <| |\r\n" + //
											 "|_____/ \\__,_|_|\\_\\_|\\_\\_|\n";

	final public static String RASSIE_IMAGE = " _____               _      \r\n" + //
											  "|  __ \\             (_)     \r\n" + //
											  "| |__) |__ _ ___ ___ _  ___ \r\n" + //
											  "|  _  // _` / __/ __| |/ _ \\\r\n" + //
											  "| | \\ \\ (_| \\__ \\__ \\ |  __/\r\n" + //
											  "|_|  \\_\\__,_|___/___/_|\\___|\n";
									
	final public static String JEAN_IMAGE = "      _ ______          _   _    \r\n" + //
											"     | |  ____|   /\\   | \\ | |\r\n" + //
											"     | | |__     /  \\  |  \\| |\r\n" + //
											" _   | |  __|   / /\\ \\ | . ` |\r\n" + //
											"| |__| | |____ / ____ \\| |\\  |\r\n" + //
											" \\____/|______/_/    \\_\\_| \\_|\n";
	
	// Prompts
	public static final String PROMPT_FILE_NAME = "Name of file to convert >> ";
	
	// Formatting
	public static final String FORMAT_DEFAULT_LINE = "----------------------------------------";
	
	// Directory locations
	public static final String DIR_USER_FILES_TXT = "user/files/txt/";
	
	// Token keywords
	public static final String TAB = "TAB ";
	public static final String ENTER = " ENTER ";

	//Steps
		//Logo
		public static final int LOGO_RAINBOW_LEFT_BIG_STEPS = 18;
		public static final int LOGO_RAINBOW_RIGHT_BIG_STEPS = 19;

		public static final int LOGO_RAINBOW_RIGHT_SMALL_STEPS = 20;
		public static final int LOGO_RAINBOW_LEFT_SMALL_STEPS = 36;

		public static final int LOGO_GRADIENT_BIG_STEPS = 5;
		public static final int LOGO_GRADIENT_RIGHT_STEPS = 10;
		public static final int LOGO_GRADIENT_LEFT_STEPS = 15;

		//Credits
		public static final int CREDITS_RAINBOW_RIGHT_STEPS = 52;
		public static final int CREDITS_RAINBOW_LEFT_STEPS = 50;

		public static final int CREDITS_GRADIENT_LEFT_BIG_STEPS = 6;
		public static final int CREDITS_GRADIENT_RIGHT_BIG_STEPS = 8;

		public static final int CREDITS_GRADIENT_LEFT_SMALL_STEPS = 20;
		public static final int CREDITS_GRADIENT_RIGHT_SMALL_STEPS = 15;

	//Display Logo
	/**
	 * Displays default rainbow Logo
	 */
	public static void displayLogoRainbow()
	{
		coloring.rainbowText(LOGO_IMAGE, LOGO_RAINBOW_LEFT_SMALL_STEPS, false);
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
				coloring.rainbowText(LOGO_IMAGE, LOGO_RAINBOW_LEFT_BIG_STEPS, false);
			}
			else
			{
				coloring.rainbowText(LOGO_IMAGE, LOGO_RAINBOW_LEFT_SMALL_STEPS, false);
			}
		}
		else
		{
			if (big)
			{
				coloring.rainbowText(LOGO_IMAGE, LOGO_RAINBOW_RIGHT_BIG_STEPS, false);
			}
			else
			{
				coloring.rainbowText(LOGO_IMAGE, LOGO_RAINBOW_RIGHT_SMALL_STEPS, false);
			}
		}
	}

	/**
	 * Displays default gradient logo
	 */
	public static void displayLogoGradient()
	{
		coloring.gradientText(LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, LOGO_GRADIENT_BIG_STEPS, false);
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
			coloring.gradientText(LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, LOGO_GRADIENT_BIG_STEPS, false);
		}
		else if (left)
		{
			coloring.gradientText(LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, LOGO_GRADIENT_LEFT_STEPS, false);
		}
		else
		{
			coloring.gradientText(LOGO_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, LOGO_GRADIENT_RIGHT_STEPS, false);
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
			coloring.gradientText(LOGO_IMAGE, rgb1, rgb2, LOGO_GRADIENT_BIG_STEPS, false);
		}
		else if (left)
		{
			coloring.gradientText(LOGO_IMAGE, rgb1, rgb2, LOGO_GRADIENT_LEFT_STEPS, false);
		}
		else
		{
			coloring.gradientText(LOGO_IMAGE, rgb1, rgb2, LOGO_GRADIENT_RIGHT_STEPS, false);
		}
	}

	//Display Credits
	/**
	 * Displays default rainbow credits
	 */
	public static void displayCreditsRainbow()
	{
		coloring.rainbowText(SAKKI_IMAGE, CREDITS_RAINBOW_LEFT_STEPS, false);
		coloring.rainbowText(RASSIE_IMAGE, CREDITS_RAINBOW_LEFT_STEPS, false);
	}

	/**
	 * Displays default rainbow credits with given direction
	 * @param left Rainbow goes to the left if true else to the right
	 */
	public static void displayCreditsRainbow(boolean left)
	{
		if (left)
		{
			coloring.rainbowText(SAKKI_IMAGE, CREDITS_RAINBOW_LEFT_STEPS, false);
			coloring.rainbowText(RASSIE_IMAGE, CREDITS_RAINBOW_LEFT_STEPS, false);
		}
		else
		{
			coloring.rainbowText(SAKKI_IMAGE, CREDITS_RAINBOW_RIGHT_STEPS, false);
			coloring.rainbowText(RASSIE_IMAGE, CREDITS_RAINBOW_RIGHT_STEPS, false);
		}
	}

	/**
	 * Displays default gradient credits
	 */
	public static void displayCreditsGradient()
	{
		coloring.gradientText(SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
		coloring.gradientText(RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
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
				coloring.gradientText(SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_LEFT_BIG_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_LEFT_BIG_STEPS, false);
			}
			else
			{
				coloring.gradientText(SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_LEFT_SMALL_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_LEFT_SMALL_STEPS, false);
			}
		}
		else
		{
			if (big)
			{
				coloring.gradientText(SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
			}
			else
			{
				coloring.gradientText(SAKKI_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, ColorLibrary.RED, ColorLibrary.AQAU, CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false);
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
				coloring.gradientText(SAKKI_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_LEFT_BIG_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_LEFT_BIG_STEPS, false);
			}
			else
			{
				coloring.gradientText(SAKKI_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_LEFT_SMALL_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_LEFT_SMALL_STEPS, false);
			}
		}
		else
		{
			if (big)
			{
				coloring.gradientText(SAKKI_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_RIGHT_BIG_STEPS, false);
			}
			else
			{
				coloring.gradientText(SAKKI_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false);
				coloring.gradientText(RASSIE_IMAGE, rgb1, rgb2, CREDITS_GRADIENT_RIGHT_SMALL_STEPS, false);
			}
		}
		
	}
}