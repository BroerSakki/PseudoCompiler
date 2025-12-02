// Main Package
package com.pseudocompiler;

import com.pseudocompiler.datatypes.Num;
import com.pseudocompiler.datatypes.Statement;
import com.pseudocompiler.datatypes.Variable;
import com.pseudocompiler.libraries.StatementLibrary;
import com.pseudocompiler.tools.Dissector;
import com.pseudocompiler.tools.MethodBuilder;

import java.util.Arrays;
import java.util.regex.Matcher;

public class Main {
	public static void main(String[] args) {
        System.out.println("\n=== PROGRAM START ===\n");

        Dissector file = new Dissector("PseudoProgram");

        Variable thingy = new Variable(new Statement("string PROMPT_USER_NAME = \"Enter username >> \""));

        System.out.println(Arrays.toString(file.getPerStatement()));

        System.out.println("\n=== PROGRAM STOP ===\n");
	}
}
