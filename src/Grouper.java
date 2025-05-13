// Package Grouper file in libraries folder
package classes.tools;

// Import Java Classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Import Custom Libraries
import classes.libraries.ConstLibrary;
import classes.libraries.MethodLibrary;

public class Grouper implements ConstLibrary, MethodLibrary {
    // Global variables
    //================================================================
        private String baseText;
        private List<String> finalText = new ArrayList<>();
        private StringBuilder current = new StringBuilder();
        private List<String> result = new ArrayList<>();
        private boolean inQuotes = false;
	    private int[] bracketDepth = {0, 0};
        private char quoteChar = '\0';
	    private char bracketChar = '\0';
    //================================================================

    // Constructor
    /**
     * Constructor for Grouper Class
     * @param input String that will be grouped acccording to the rules
     */
    //================================================================
        public Grouper(String input) {
            baseText = input.replaceAll("\\s+", " ");
            group();
        }
    //================================================================

    // Reset
    /**
     * Resets all variables
     */
    //================================================================
        public void reset() {
            baseText = "";
            finalText.clear();
            current.setLength(0);
            result.clear();
            inQuotes = false;
            Arrays.fill(bracketDepth, 0);
            quoteChar = '\0';
            bracketChar = '\0';
        }
        public void reset(boolean keepBaseText) {
            if (keepBaseText) {
                finalText.clear();
                current.setLength(0);
                result.clear();
                inQuotes = false;
                Arrays.fill(bracketDepth, 0);
                quoteChar = '\0';
                bracketChar = '\0';
            } else {
                reset();
            }
        }
    //================================================================

    // Group methods
    //================================================================
        //General grouping method
        public List<String> group() {
            //Reset finalText variable
            if (finalText != null) {
                finalText.clear();
            }

            //Reset current and result
            makeReady();

            for (char c : baseText.toCharArray()) {
                if (findCriteriaQuote(c)) {
                    if (findCriteriaBrackets(c)) {
                        current.append(c);
                    }
                }
            }

            // Add the last substring if there's any content left
            if (current.length() > 0) {
                result.add(current.toString().trim());
            }

            finalText = result;

            return finalText;
        }

        //Overloaded groupQuote Method
        public List<String> groupQuote() {
            return groupQuote(false);
        }
        public List<String> groupQuote(boolean runMakeReady) {
            if (runMakeReady) {
                //Reset current and result
                makeReady();
            }

            for (char c : baseText.toCharArray()) {
                if (findCriteriaQuote(c)) {
                    current.append(c);
                }
            }
            // Add the last substring if there's any content left
            if (current.length() > 0) {
                result.add(current.toString().trim());
            }

            finalText = result;

            return finalText;
        }
        public List<String> groupQuote(char quoteType) {
            return groupQuote(false, quoteType);
        }
        public List<String> groupQuote(boolean runMakeReady, char quoteType) {
            if (runMakeReady) {
                //Reset current and result
                makeReady();
            }

            for (char c : baseText.toCharArray()) {
                if (findCriteriaQuote(c, quoteType)) {
                    current.append(c);
                }
            }

            // Add the last substring if there's any content left
            if (current.length() > 0) {
                result.add(current.toString().trim());
            }

            finalText = result;

            return finalText;
        }

        //Overloaded groupBrackets Method
        public List<String> groupBrackets() {
            return groupBrackets(false);
        }
        public List<String> groupBrackets(boolean runMakeReady) {
            if (runMakeReady) {
                //Reset current and result
                makeReady();
            }

            for (char c : baseText.toCharArray()) {
                if (findCriteriaBrackets(c)) {
                    current.append(c);
                }
            }

            // Add the last substring if there's any content left
            if (current.length() > 0) {
                result.add(current.toString().trim());
            }

            finalText = result;

            return finalText;
        }
    //================================================================

    // Condition mutating methods
    //================================================================
        //General boolean swapper
        private boolean boolSwap(boolean b) {
            return !b;
        }

        //Overloaded quoteSwapper
        private void quoteSwapper() {
            boolSwap(inQuotes);
        }
        private char quoteSwapper(char c) {
            quoteSwapper();

            return c;
        }
    
        //Mutate Bracket Depth
        private boolean mutateBracketDepth(char c) {
            // All possible bracket inputs
            switch (c) {
                case '(':
                    bracketDepth[0]++;
                    bracketChar = ')';
                    break;
                case '[':
                    bracketDepth[1]++;
                    bracketChar = ']';
                    break;
                case ')':
                    bracketDepth[0]--;
                    break;
                case ']':
                    bracketDepth[1]--;
                    break;
            
                default:
                    System.out.println("Error mutating bracket depth: character is not a valid bracket type");
                    break;
            }

            return inBrackets();
        }
        private boolean inBrackets() {
            return (bracketDepth[0] > 0 || bracketDepth[1] > 0);            
        }
    //================================================================

    // Split conditions
    //================================================================
        private boolean isNonLiteralSplit(char c) {
            //Local Variables
            boolean check;
            check = ((c == ' ') && (!inQuotes) && (!inBrackets()));

            return check;
        }
    //================================================================

    // Getters
    //================================================================
        public String getBaseText() {
            return baseText;
        }
        public int[] getBracketDepth() {
            return bracketDepth;
        }
        public char getBracketChar() {
            return bracketChar;
        }
        public List<String> getFinalText() {
            return finalText;
        }
        public char getQuoteChar() {
            return quoteChar;
        }
        public List<String> getResult() {
            return result;
        }
    //================================================================

    // findCriteria Methods
    //================================================================
        //Add the quote as its own element
        private boolean findCriteriaQuote(char c) {
            //Local Variables
            boolean checkNextCategory = false;

            if (containsElement(TOKEN_OPERATORS_QUOTES, c)) {             
                    current.append(quoteSwapper(c));

                } else if (isNonLiteralSplit(c)) {
                    //Finalize current substring
                    result.add(current.toString().trim());
                    current.setLength(0);
                } else {
                    checkNextCategory = true;
                }

            return checkNextCategory;
        }
        private boolean findCriteriaQuote(char c, char match) {
            //Local Variables
            boolean checkNextCategory = false;

            if (c == match) {
                current.append(quoteSwapper(c));

                } else if (isNonLiteralSplit(c)) {
                    //Finalize current substring
                    result.add(current.toString().trim());
                    current.setLength(0);
                } else {
                    checkNextCategory = true;
                }

            return checkNextCategory;
        }
    
        private boolean findCriteriaBrackets(char c) {
            //Local Variables
            boolean checkNextCategory = false;

            if (!inQuotes) {
                if (mutateBracketDepth(c)) {
                    current.append(c);
                } else if (isNonLiteralSplit(c)) {
                    //Finalize current substring
                    result.add(current.toString().trim());
                    current.setLength(0);
                } else {
                    checkNextCategory = true;
                }
            }

            return checkNextCategory;
        }
    //================================================================

    // Auxillary methods
    //================================================================
        //Reset necessary variables
        private void makeReady() {
            current.setLength(0);
            result.clear();
        }   
    //================================================================
}