// Package global constants file in custom library
package classes.libraries;

public interface ConstLibrary {
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
	
	//Tokens
		//Formatting
		public static final String[] TOKEN_FORMAT_TAB = {" TAB ", "\t"};
		public static final String[] TOKEN_FORMAT_ENTER = {" ENTER ", "\n"};
		
		//Keywords
		public static final String KEY_START = "start";
		public static final String KEY_STOP = "stop";
		public static final String KEY_RETURN = "return";
		public static final String KEY_TRUE = "true";
		public static final String KEY_FALSE = "false";
		public static final String KEY_WHILE = "while";
		public static final String KEY_ENDWHILE = "endwhile";
		public static final String KEY_FOR = "for";
		public static final String KEY_ENDFOR = "endfor";
		public static final String KEY_ELSE = "else";
		public static final String KEY_IF = "if";
		public static final String KEY_ENDIF = "endif";

		//Grouping operators
		public static final char[] TOKEN_OPERATORS_QUOTES = {'\'', '\"'};
		public static final char[] TOKEN_OPERATORS_BRACKETS = {'(', ')', '[', ']'};
		public static final char[] TOKEN_OPERATORS_GROUPING = {'\'', '\"', '(', ')', '[', ']'};
		public static final char[] TOKEN_OPERATORS_SPLITTERS = {' ', ','};

		//Operators
		public static final char TOKEN_OPERATOR_COMMA = ',';
		public static final char[] TOKEN_OPERATORS_SINGLE = {'*', '/', '%', '+', '-', '<', '>', '='};
		public static final char[][] TOKEN_OPERATORS_MULTI = {{'<', '='}, {'>', '='}, {'=', '='}, {'!', '='}};
		public static final char[][] TOKEN_OPERATORS_WORDS = {{'N', 'O', 'T'}, {'A', 'N', 'D'}, {'O', 'R'}};

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
}