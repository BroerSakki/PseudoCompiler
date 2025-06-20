// Package Grouper file in libraries folder
package classes.tools;

// Import Java Classes
import classes.libraries.MethodLibrary;
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

    // Auxillary method
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
         * <h4>Condition 1</h4>
         * Sets the inBrackets variable to false if both of the bracketDepth elements are equal to 0
         * <h4>Condition 2</h4>
         * Sets the inBrackets variable to true if either bracketDepth elements are greater than 0
         * <h4>Exception</h4>
         * Prints an error message and resets all bracketDepth elements to 0 if conditions 1 and 2 are both false
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
         * Increase or decrease the correct bracketDepth element based on opening or closing respective brackets
         * @param c Char variable to test and compare to cases
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
         * Determine the charicaristics of the chosen character and assign it accordingly
         * @param c Char to be analized and possibly added to current and or result
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
         * @param c Char to be analized
         * @return true if splitting character is not bound by quotes or brackets, and false otherwise
         */
        private boolean willSplitAtChar(char c) {
            //Local variables
            boolean check = false;

            if (c == ' ' && !inBrackets && !inQuotes) {
                check = true;
            }
            return check;
        }

        /**
         * Groups the data form the baseText variable per character and saves it to the finalText variable
         * @return finalText
         */
        private List<String> group() {
            if (finalText != null) {
                finalText.clear();
            }

            makeReady();

            for (char c : baseText.toCharArray()) {
                findCriteria(c);
            }

            if (current.length() > 0) {
                result.add(current.toString().trim());
            }

            finalText = result;

            return finalText;
        }
    //================================================================
}