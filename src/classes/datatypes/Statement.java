// Package Statement class to datatypes
package classes.datatypes;

import classes.libraries.MethodLibrary;
import classes.libraries.StatementLibrary;
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
        private int codePos;
    //================================================================

    // Constructor
    //================================================================
        public Statement(String[] text, int textPos) {
            keywords = new ArrayList<>();
            mutateBase(text);
            setTextPos(textPos);
            setCodePos(-1);
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
        private void setCodePos(int codePos) {
            this.codePos = codePos;
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
        public String[] getText() {
            return text;
        }
        public List<String> getKeywords() {
            return keywords;
        }
        public String getKeyword(int index) {
            return keywords.get(index);
        }
        public int getDepth() {
            return depth;
        }
        public int getTextPos() {
            return textPos;
        }
        public int getCodePos() {
            return codePos;
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
