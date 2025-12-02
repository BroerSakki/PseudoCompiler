// Package Grouper file in libraries folder
package com.pseudocompiler.tools;

// Import Java Classes
import com.pseudocompiler.libraries.MethodLibrary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grouper implements MethodLibrary {
    // Global variables
    //================================================================
        private List<String> finalText  = new ArrayList<>();
        private final List<String> result = new ArrayList<>();
        private final StringBuilder current = new StringBuilder();
        private String baseText;
        private boolean inQuotes;
        private boolean inBrackets;
        private char quoteChar;
        private final int[] bracketDepth = {0, 0};
    //================================================================

    // Constructor
    //================================================================
        /**
         * Constructor for Grouper a Grouper Object
         * @param input String value to be formatted
         */
        public Grouper(String input) {
            setBaseText(input.replaceAll("\\s+", " "));
            setDefaults();
            group();
        }
    //================================================================

    // Auxiliary method
    //================================================================
        /**
         * Resets the current and result variables
         */
        private void makeReady() {
            current.setLength(0);
            result.clear();
        }
    //================================================================

    // Setters
    //================================================================
        /**
         * Set all relevant variables to default values
         */
        private void setDefaults() {
            finalText.clear();
            result.clear();
            current.setLength(0);
            setInBrackets(false);
            setInQuotes(false);
            quoteChar = '\0';
            Arrays.fill(bracketDepth, 0);
        }

        private void setBaseText(String baseText) {
            this.baseText = baseText;
        }
        private void setInBrackets(boolean inBrackets) {
            this.inBrackets = inBrackets;
        }
        private void setInQuotes(boolean inQuotes) {
            this.inQuotes = inQuotes;
        }
        private void setQuoteChar(char quoteChar) {
            this.quoteChar = quoteChar;
        }
    //================================================================

    // Getters
    //================================================================
        public String getBaseText() {
            return baseText;
        }
        public List<String> getFinalText() {
            return finalText;
        }
    //================================================================

    // Mutators
    //================================================================
        /**
		 * Mutate the inBrackets variable based on the current state of bracketDepth
		 */
        public void mutateInBrackets() {
            if (bracketDepth[0] == 0 && bracketDepth[1] == 0) {
                setInBrackets(false);
            } else if (bracketDepth[0] > 0 || bracketDepth[1] > 0) {
                setInBrackets(true);
            } else {
                System.out.println("Error: bracketDepth not valid. Resetting bracketDepth values...");
                bracketDepth[0] = 0;
                bracketDepth[1] = 0;
            }
        }

        /**
		 * Mutate the bracketDepth variable based on the current character
		 * @param c Character to be analyzed
		 */
        public void mutateBracketDepth(char c) {
            switch (c) {
                case '(' -> bracketDepth[0]++;
                case '[' -> bracketDepth[1]++;
                case ')' -> bracketDepth[0]--;
                case ']' -> bracketDepth[1]--;
            }
        }
    //================================================================

    // Work methods
    //================================================================
        /**
		 * Check if a character is in the given array of characters
		 * @param c Character to check
         */
        private void findCriteria(char c) {
            if (containsElement(TOKEN_OPERATORS_QUOTES, c)) {
                if (!inQuotes) {
                    setInQuotes(true);
                    setQuoteChar(c);
                } else if (quoteChar == c) {
                    setInQuotes(false);
                }

                current.append(c);
            } else if (!inQuotes) {
                mutateBracketDepth(c);
                current.append(c);

                if (willSplitAtChar(c)) {
                    result.add(current.toString().trim());
                    current.setLength(0);
                }

            } else {
                current.append(c);
            }
        }

        /**
         * Determine if  a character is of the type that splits, and is not in quotes or brackets
         * @param c Char to be analyzed
         * @return true if splitting character is not bound by quotes or brackets, and false otherwise
         */
        private boolean willSplitAtChar(char c) {
            return c == ' ' && !inBrackets && !inQuotes;
        }

        /**
         * Groups the data form the baseText variable per character and saves it to the finalText variable
         */
        private void group() {
            if (finalText != null) {
                finalText.clear();
            }

            makeReady();

            for (char c : baseText.toCharArray()) {
                findCriteria(c);
            }

            if (!current.isEmpty()) {
                result.add(current.toString().trim());
            }

            finalText = result;

        }
    //================================================================
}