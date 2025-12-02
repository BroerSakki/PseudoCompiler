// Package Statement class to datatypes
package com.pseudocompiler.datatypes;

import com.pseudocompiler.libraries.MethodLibrary;
import com.pseudocompiler.libraries.StatementLibrary;
import com.pseudocompiler.tools.Grouper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement implements MethodLibrary, StatementLibrary {
    // Global variables
    //================================================================
        private String[] text;
        private int depth;
    //================================================================

    // Constructor
    //================================================================
		public Statement(String[] text) {
            setText(text);
            setDepth();
        }
		public Statement(String text) {
            this((new Grouper(text).getFinalText().toArray(String[]::new)));
        }
    //================================================================

    // Setters
    //================================================================
        private void setText(String[] text) {
            this.text = text;
        }
        private void setDepth(int depth) {
            this.depth = depth;
        }
        private void setDepth() {
            setDepth(countLeadingTabs(this.toString()));
        }
    //================================================================

    // Getters
    //================================================================
        public String[] getText() {
            return text;
        }
        public int getDepth() {
            return depth;
        }
    //================================================================

    // Work methods
    //================================================================
        /**
         * Returns a Matcher object for the given regex pattern against the statement's text
         * @param regex The regex pattern to match against the statement's text
         * @return Matcher object that can be used to find matches in the statement's text
         */
        public Matcher getMatcher(String regex) {
            return StatementLibrary.getMatcher(regex, this.toString());
		}

        public static int countLeadingTabs(String line) {
            if (line == null || line.isEmpty()) {
                return 0;
            }

            // Regex: ^\t* matches zero or more tabs at the start of the line
            Pattern pattern = Pattern.compile("^(\\t*)");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                // group(1) is the capture of the leading tabs
                System.out.println(line + " contains " + matcher.group(1).length() + " tabs");
                return matcher.group(1).length();
            }

            return 0; // no leading tabs
        }
    //================================================================

    // To String Methods
    //================================================================
        @Override
        public String toString() {
            return String.join(" ", getText());
        }

        public String toString(String delimiter) {
            return String.join(delimiter, getText());
        }
    //================================================================
}
