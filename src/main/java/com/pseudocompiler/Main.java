// Main Package
package com.pseudocompiler;

import com.pseudocompiler.datatypes.Num;
import com.pseudocompiler.datatypes.Statement;
import com.pseudocompiler.tools.Dissector;
import com.pseudocompiler.tools.MethodBuilder;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
        System.out.println("\n=== PROGRAM START ===\n");

        Dissector file = new Dissector("PseudoProgram");

        for (MethodBuilder method : file.getMethods()) {
            System.out.println(method);
        }

        System.out.println("\n=== PROGRAM STOP ===\n");
	}
}
