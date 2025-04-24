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

	//Changes and returns color of text string
	public static String recolorText(String text, int R, int G, int B)
	{
		//Set rgb colors using R, G, B values
		String ANSI_COLOR = "\033[38;2;" + R + ";" + G + ";" + B + "m";
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
		//Creates colored text
		String recoloredText = ANSI_COLOR + text + ANSI_RESET;
		
		//Returns colored text
		return recoloredText;
	}

    //Changes and returns color of text character
	public static String recolorChar(char text, int R, int G, int B)
	{
		//Set rgb colors using R, G, B values
		String ANSI_COLOR = "\033[38;2;" + R + ";" + G + ";" + B + "m";
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
		//Creates colored text
		String recoloredText = ANSI_COLOR + text + ANSI_RESET;
		
		//Returns colored text
		return recoloredText;
	}
	
    //Prints rainbow text based on length of text inputted
	public static void rainbowTextDynamic(String text)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
        //Initialize rgb colors
		int r = 255;
		int g = 0;
		int b = 0;
		
        //Determines amount of steps
		int steps = 1000 / text.length();
		
		//Create and print Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
			System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
			
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
	}

	public static void rainbowTextDynamic(String text, int startR, int startG, int startB)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
		
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";
		
        //Initialize rgb colors
		int r = startR;
		int g = startG;
		int b = startB;
		
        //Determines amount of steps
		int steps = 1000 / text.length();
		
		//Create and print Rainbow effect
		for (int i = 0; i < text.length(); i++)
		{
			ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
			System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
			
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
	}

	//Overoaded functions
    //================================================================
        //Changes text color with full spectrum of rainbow (For a very long rainbow effect)
	    public void rainbowText(String text)
	    {
	    	//Initialize ANSI_COLOR
	    	String ANSI_COLOR;
        
	    	//Resets cmd color to default
	    	String ANSI_RESET = "\033[0m";
        
            //Initialize rgb colors
	    	int r = 255;
	    	int g = 0;
	    	int b = 0;
        
	    	//Create Rainbow effect
	    	for (int i = 0; i < text.length(); i++)
	    	{
	    		ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
	    		System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
            
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
	    }
    
        //Changes text color with rainbow of given steps (Used for shorter rainbows)
	    public void rainbowText(String text, int steps)
	    {
	    	//Initialize ANSI_COLOR
	    	String ANSI_COLOR;
        
	    	//Resets cmd color to default
	    	String ANSI_RESET = "\033[0m";
        
            //Initialize rgb colors
	    	int r = 255;
	    	int g = 0;
	    	int b = 0;
        
	    	//Create Rainbow effect
	    	for (int i = 0; i < text.length(); i++)
	    	{
	    		ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
	    		System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
            
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
	    }
    
        //Changes text color with rainbow of given start rgb values (Used to specify where rainbow should start)
	    public static void rainbowText(String text, int startR, int startG, int startB)
	    {
	    	//Initialize ANSI_COLOR
	    	String ANSI_COLOR;
        
	    	//Resets cmd color to default
	    	String ANSI_RESET = "\033[0m";
        
            //Initialize rgb colors
	    	int r = startR;
	    	int g = startG;
	    	int b = startB;
        
	    	//Create Rainbow effect
	    	for (int i = 0; i < text.length(); i++)
	    	{
	    		ANSI_COLOR = "\033[38;2;" + r + ";" + g + ";" + b + "m";
	    		System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);
            
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
	    }
    //================================================================NaN
	
	public static void gradientText(String text, double firstR, double firstG, double firstB, double secondR, double secondG, double secondB)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
        
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		double value = 0;
		double valueDouble;

		double r;
		double g;
		double b;

		double r1;
		double g1;
		double b1;

		double r2;
		double g2;
		double b2;

		int ir = 0;
		int ig = 0;
		int ib = 0;

		r1 = firstR / 255;
		g1 = firstG / 255;
		b1 = firstB / 255;

		r2 = secondR / 255;
		g2 = secondG / 255;
		b2 = secondB / 255;

		for (int i = 0; i < text.length(); i++)
		{
			valueDouble = value / 100;

			r = r1 + (r2 - r1) * valueDouble;
			g = g1 + (g2 - g1) * valueDouble;
			b = b1 + (b2 - b1) * valueDouble;

			ir = (int)(r * 255);
			ig = (int)(g * 255);
			ib = (int)(b * 255);

			ANSI_COLOR = "\033[38;2;" + ir + ";" + ig + ";" + ib + "m";
	    	System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);

			value++;
		}
	}

	public static void dynamicGradientText(String text, double firstR, double firstG, double firstB, double secondR, double secondG, double secondB)
	{
		//Initialize ANSI_COLOR
		String ANSI_COLOR;
        
		//Resets cmd color to default
		String ANSI_RESET = "\033[0m";

		double value = 0;
		double valueDouble;

		double r;
		double g;
		double b;

		double r1;
		double g1;
		double b1;

		double r2;
		double g2;
		double b2;

		int ir = 0;
		int ig = 0;
		int ib = 0;

		boolean direction = true;

		int steps = 100 / (text.length() - 1);

		if (steps < 1)
		{
			steps = 1;
		}

		r1 = firstR / 255;
		g1 = firstG / 255;
		b1 = firstB / 255;

		r2 = secondR / 255;
		g2 = secondG / 255;
		b2 = secondB / 255;

		for (int i = 0; i < text.length(); i++)
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

			r = r1 + (r2 - r1) * valueDouble;
			g = g1 + (g2 - g1) * valueDouble;
			b = b1 + (b2 - b1) * valueDouble;

			ir = (int)(r * 255);
			ig = (int)(g * 255);
			ib = (int)(b * 255);

			ANSI_COLOR = "\033[38;2;" + ir + ";" + ig + ";" + ib + "m";
	    	System.out.print(ANSI_COLOR + text.charAt(i) + ANSI_RESET);

			if (direction)
			{
				value += steps;
			}
			else
			{
				value -= steps;
			}
		}
	}

    // Method Index output
	//================================================================
		public ArrayList<String> getMethodIndex() {
			return methodIndex;
		}
		public void printMethodIndex() {
			displayMethodIndex(currentClass);
		}
	//================================================================NaN
    
    // Constructor
	//================================================================
		public ColoringTools() {
			List<String> list = Arrays.asList(returnMethodIndex(currentClass));
			methodIndex = new ArrayList<>(list);
		}
	//================================================================

    public static void main(String[] args) {
		ColoringTools coloring = new ColoringTools();
	}
}