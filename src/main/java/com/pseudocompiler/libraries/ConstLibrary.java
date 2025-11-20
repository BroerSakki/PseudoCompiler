// Package global constants file in custom library
package com.pseudocompiler.libraries;

public interface ConstLibrary {
	//Logos
	String LOGO_IMAGE = """
                             _____                    _             _____                      _ _           \r
                            |  __ \\                  | |           / ____|                    (_) |          \r
                            | |__) |__  ___ _   _  __| | ___   ___| |     ___  _ __ ___  _ __  _| | ___ _ __ \r
                            |  ___/ __|/ _ \\ | | |/ _` |/ _ \\ / __| |    / _ \\| '_ ` _ \\| '_ \\| | |/ _ \\ '__|\r
                            | |   \\__ \\  __/ |_| | (_| | (_) | (__| |___| (_) | | | | | | |_) | | |  __/ |   \r
                            |_|   |___/\\___|\\__,_|\\__,_|\\___/ \\___|\\_____\\___/|_| |_| |_| .__/|_|_|\\___|_|   \r
                                                                                        | |                  \r
                                                                                        |_|                  
                            """;

	String SAKKI_IMAGE = """
                               _____       _    _    _ \r
                              / ____|     | |  | |  (_)\r
                             | (___   __ _| | _| | ___ \r
                              \\___ \\ / _` | |/ / |/ / |\r
                              ____) | (_| |   <|   <| |\r
                             |_____/ \\__,_|_|\\_\\_|\\_\\_|
                             """;

	String RASSIE_IMAGE = """
                               _____               _      \r
                              |  __ \\             (_)     \r
                              | |__) |__ _ ___ ___ _  ___ \r
                              |  _  // _` / __/ __| |/ _ \\\r
                              | | \\ \\ (_| \\__ \\__ \\ |  __/\r
                              |_|  \\_\\__,_|___/___/_|\\___|
                              """;
									
	String JEAN_IMAGE = """
                                  _ ______          _   _    \r
                                 | |  ____|   /\\   | \\ | |\r
                                 | | |__     /  \\  |  \\| |\r
                             _   | |  __|   / /\\ \\ | . ` |\r
                            | |__| | |____ / ____ \\| |\\  |\r
                             \\____/|______/_/    \\_\\_| \\_|
                            """;
	
	// Prompts
	String PROMPT_FILE_NAME = "Name of file to convert >> ";
	
	// Formatting
	String FORMAT_DEFAULT_LINE = "----------------------------------------";
	
	// Profile use
	String USER = "user";
	String DEV = "dev";

	// Directory locations
	String DIR_USER_READ_TXT = "src/main/resources/user/read/txt/";
	String DIR_USER_WRITE_TXT = "src/main/resources/user/write/txt/";
	
	//Tokens
		//Formatting
		String TOKEN_FORMAT_TAB = "0_TAB ";
		String TOKEN_FORMAT_ENTER = " 0_ENTER";

		//Grouping operators
		char[] TOKEN_OPERATORS_QUOTES = {'\'', '\"'};
		char[] TOKEN_OPERATORS_BRACKETS = {'(', ')', '[', ']'};
		char[] TOKEN_OPERATORS_GROUPING = {'\'', '\"', '(', ')', '[', ']'};
		char[] TOKEN_OPERATORS_SPLITTERS = {' ', ','};

		//Operators
		String[] TOKEN_OPERATORS_CONDITIONS = {"=", "<", ">", "<=", ">=", "<>", "&&", "\\|\\|", " AND ", " OR "};

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