// Package global constants file in custom library
package classes.libraries;

public interface ConstLibrary {
	//Logos
	String LOGO_IMAGE = " _____                    _             _____                      _ _           \r\n" + //
											"|  __ \\                  | |           / ____|                    (_) |          \r\n" + //
											"| |__) |__  ___ _   _  __| | ___   ___| |     ___  _ __ ___  _ __  _| | ___ _ __ \r\n" + //
											"|  ___/ __|/ _ \\ | | |/ _` |/ _ \\ / __| |    / _ \\| '_ ` _ \\| '_ \\| | |/ _ \\ '__|\r\n" + //
											"| |   \\__ \\  __/ |_| | (_| | (_) | (__| |___| (_) | | | | | | |_) | | |  __/ |   \r\n" + //
											"|_|   |___/\\___|\\__,_|\\__,_|\\___/ \\___|\\_____\\___/|_| |_| |_| .__/|_|_|\\___|_|   \r\n" + //
											"                                                            | |                  \r\n" + //
											"                                                            |_|                  \n";

	String SAKKI_IMAGE = "  _____       _    _    _ \r\n" + //
											 " / ____|     | |  | |  (_)\r\n" + //
											 "| (___   __ _| | _| | ___ \r\n" + //
											 " \\___ \\ / _` | |/ / |/ / |\r\n" + //
											 " ____) | (_| |   <|   <| |\r\n" + //
											 "|_____/ \\__,_|_|\\_\\_|\\_\\_|\n";

	String RASSIE_IMAGE = " _____               _      \r\n" + //
											  "|  __ \\             (_)     \r\n" + //
											  "| |__) |__ _ ___ ___ _  ___ \r\n" + //
											  "|  _  // _` / __/ __| |/ _ \\\r\n" + //
											  "| | \\ \\ (_| \\__ \\__ \\ |  __/\r\n" + //
											  "|_|  \\_\\__,_|___/___/_|\\___|\n";
									
	String JEAN_IMAGE = "      _ ______          _   _    \r\n" + //
											"     | |  ____|   /\\   | \\ | |\r\n" + //
											"     | | |__     /  \\  |  \\| |\r\n" + //
											" _   | |  __|   / /\\ \\ | . ` |\r\n" + //
											"| |__| | |____ / ____ \\| |\\  |\r\n" + //
											" \\____/|______/_/    \\_\\_| \\_|\n";
	
	// Prompts
	String PROMPT_FILE_NAME = "Name of file to convert >> ";
	
	// Formatting
	String FORMAT_DEFAULT_LINE = "----------------------------------------";
	
	// Profile use
	String USER = "user";
	String DEV = "dev";

	// Directory locations
	String DIR_USER_READ_TXT = "user/read/txt/";
	String DIR_USER_WRITE_TXT = "user/write/txt";
	
	//Tokens
		//Formatting
		String[] TOKEN_FORMAT_TAB = {" 0_TAB ", "\t"};
		String[] TOKEN_FORMAT_ENTER = {" 0_ENTER ", "\n"};

		//Grouping operators
		char[] TOKEN_OPERATORS_QUOTES = {'\'', '\"'};
		char[] TOKEN_OPERATORS_BRACKETS = {'(', ')', '[', ']'};
		char[] TOKEN_OPERATORS_GROUPING = {'\'', '\"', '(', ')', '[', ']'};
		char[] TOKEN_OPERATORS_SPLITTERS = {' ', ','};

		//Operators
		char TOKEN_OPERATOR_COMMA = ',';
		char[] TOKEN_OPERATORS_SINGLE = {'*', '/', '%', '+', '-', '<', '>', '='};
		char[][] TOKEN_OPERATORS_MULTI = {{'<', '='}, {'>', '='}, {'=', '='}, {'!', '='}};
		char[][] TOKEN_OPERATORS_WORDS = {{'N', 'O', 'T'}, {'A', 'N', 'D'}, {'O', 'R'}};

	//Steps
		//Logo
		int LOGO_RAINBOW_LEFT_BIG_STEPS = 18;
		int LOGO_RAINBOW_RIGHT_BIG_STEPS = 19;

		int LOGO_RAINBOW_RIGHT_SMALL_STEPS = 20;
		int LOGO_RAINBOW_LEFT_SMALL_STEPS = 36;

		int LOGO_GRADIENT_BIG_STEPS = 5;
		int LOGO_GRADIENT_RIGHT_STEPS = 10;
		int LOGO_GRADIENT_LEFT_STEPS = 15;

		//Credits
		int CREDITS_RAINBOW_RIGHT_STEPS = 52;
		int CREDITS_RAINBOW_LEFT_STEPS = 50;

		int CREDITS_GRADIENT_LEFT_BIG_STEPS = 6;
		int CREDITS_GRADIENT_RIGHT_BIG_STEPS = 8;

		int CREDITS_GRADIENT_LEFT_SMALL_STEPS = 20;
		int CREDITS_GRADIENT_RIGHT_SMALL_STEPS = 15;
}