// Package Statement class to datatypes
package classes.datatypes;

import classes.libraries.MethodLibrary;
import classes.libraries.StatementLibrary;
import classes.tools.Grouper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statement implements MethodLibrary, StatementLibrary {
    // Global variables
    //================================================================
        private String[] text;
        private final List<String> keywords;
        private int depth;
        private int textPos;
    //================================================================

    // Constructor
    //================================================================
        public Statement(String[] text, int textPos) {
            keywords = new ArrayList<>();
            mutateBase(text);
            setTextPos(textPos);
        }
		public Statement(String[] text) {
            keywords = new ArrayList<>();
            mutateBase(text);
			setTextPos(-1);
        }
		public Statement(String text) {
			//Local variables
			Grouper group = new Grouper(text);
			String[] words = group.getFinalText().toArray(String[]::new);
            keywords = new ArrayList<>();
            mutateBase(words);
            setTextPos(-1);
        }
    //================================================================

    // Setters
    //================================================================
        private void setText(String[] text) {
            this.text = text;
        }
        private void setDepth() {
            this.depth = Collections.frequency(List.of(text), "0_TAB");
        }
        private void setTextPos(int textPos) {
            this.textPos = textPos;
        }
    //================================================================

    // Mutators
    //================================================================
        private void addKeyword(String keyword) {
            keywords.add(keyword);
        }
        private  void mutateBase(String[] text) {
            setText(text);
            findKeywords();
            setDepth();
        }
    //================================================================

    // Getters
    //================================================================
        /**
		 * Returns the final text of the statement
		 * @return String[] representing the text of the statement
		 */
        public String[] getText() {
            return text;
        }
        /**
         * Returns the keywords found in the statement
         * @return List<String> containing the keywords
         */
         public List<String> getKeywords() {
            return keywords;
        }
        /**
         * Returns the amount of tabulations in the statement, which is used to determine the depth of the statement
         * @return int representing the depth of the statement
         */
         public int getDepth() {
            return depth;
        }
        /**
		 * Returns the position of the statement in the text, if available
		 * @return int representing the position of the statement in the text, or -1 if not set
		 */
        public int getTextPos() {
            return textPos;
        }
        /**
         * Returns the text of the statement as a single String
		 * @return String representing the text of the statement
         */
        @Override
        public String toString() {
            return String.join(" ", getText());
        }
        /**
		 * Returns the text of the statement as a single String with a specified delimiter
		 * @param delimiter String to be used as a delimiter between words
		 * @return String representing the text of the statement with the specified delimiter
		 */
        public String toString(String delimiter) {
            return String.join(delimiter, getText());
        }
    //================================================================

    // Work methods
    //================================================================
        private void findKeywords() {
            for (String match : text) {
                if (containsElement(KEYS_STATEMENT_TYPE, match)) {
                    addKeyword(match);
                }
            }
        }
    //================================================================
}
