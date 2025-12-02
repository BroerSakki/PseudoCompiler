package com.pseudocompiler.tools;

import com.pseudocompiler.datatypes.Statement;
import com.pseudocompiler.datatypes.Variable;
import com.pseudocompiler.libraries.StatementLibrary;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodBuilder {
	
	// Global variables
	//================================================================
    	private Statement head;
    	private Statement[] body;
    	private String returnValue;
        private Variable[] globalScope;
        private Variable[] localScope;
    //================================================================

    // Constructors
    //================================================================
    	/**
    	 * Creates a MethodBuilder instance with a head, body, and return value.
    	 * @param head the head of the method, typically a Statement object
    	 * @param body the body of the method, an array of Statement objects
    	 * @param returnValue the return value of the method, can be null if not applicable
    	 */
    	public MethodBuilder(Statement head, Statement[] body, String returnValue, Variable[] globalScope) {
    		setHead(head); // Validate the head based on the method head
    		setBody(body); // Validate the body based on the method head
    		setReturnValue(returnValue); // Validate the return value based on the method head
            setGlobalScope(globalScope);
            setLocalScope();
    	}

    	/**
		 * Creates a MethodBuilder instance of a main method.
		 */
    	public MethodBuilder() {
            this(buildHeadMain(), new Statement[] {}, null, new Variable[0]);
    	}

        /**
         * Creates a MethodBuilder instance of a 'main' method
         * @param body the body of the method, an array of Statement objects
         */
        public MethodBuilder(Statement[] body) {
            this(buildHeadMain(), body, null, new Variable[0]);
        }
    //================================================================
    	
    // Setters
    //================================================================
   		/**
    	 * Sets the head of the method.
    	 * @param head the new head Statement for the method
   		 */
   		private void setHead(Statement head) {
               this.head = head;
   		}
   		/**
   		 * Sets the body of the method.
   		 * @param body an array of Statement objects to set as the body of the method
   		 */
    	private void setBody(Statement[] body) {
                this.body = body;
    		}
    	/**
   		 * Sets the return value of the method.
   		 * @param returnValue the new return value as a String, can be null if not applicable
   		 */
   		private void setReturnValue(String returnValue) {
    		this.returnValue = returnValue;
    	}
        /**
         * Define the global scope of variables for the method
         * @param globalScope Variable array containing all global variables
        */
        private void setGlobalScope(Variable[] globalScope) {
               this.globalScope = globalScope;
        }
        private void setLocalScope() {
            //Local Variables
            ArrayList<Variable> variables = new ArrayList<>();
            int declareBlockIndex = -1;
            Matcher matcher;

            //Loop through body
            for (int i = 0; i < body.length; i++) {

                 matcher = StatementLibrary.getMatcher(StatementLibrary.REGEX_BLOCK_DECLARE, body[i].toString());

                if (matcher.matches()) {
                    declareBlockIndex = i;
                }

                if ((declareBlockIndex > -1) && (i > declareBlockIndex)) {
                    if (StatementLibrary.compareRegex(StatementLibrary.REGEX_VARIABLE_DECLARE_ASSIGN, body[i].toString())) {
                        //Local Variables
                        Variable variable = new Variable(body[i]);

                        variables.add(variable);
                    } else {
                        declareBlockIndex = -1;
                    }
                }
            }

            localScope = variables.toArray(new Variable[0]);
        }
    //================================================================
    	
    // Getters
    //================================================================
    	/**
    	 * Returns the head of the method.
    	 * @return the head Statement of the method
    	 */
		public Statement getHead() {
            return head;
		}
		/**
		 * Returns the body of the method.
		 * @return an array of Statement objects representing the body of the method
		 */
		public Statement[] getBody() {
            return body;
		}
		/**
		 * Returns the return value of the method.
		 * @return the return value as a String, can be null if not applicable
		 */
		public String getReturnValue() {
            return returnValue;
		}
        /**
         * Returns the global scope of variables
         * @return an array of Variables
         */
        public Variable[] getGlobalScope() {
            return globalScope;
        }
        /**
         * Returns the local scope of variables
         * @return an array of Variables
         */
        public Variable[] getLocalScope() {
            return localScope;
        }
    //================================================================
		
	// Work Methods
	//================================================================
		/**
		 * Build a Statement for the main method.
		 * @return a Statement object representing the main method head
		 */
		public static Statement buildHeadMain() {
			return new Statement("public static void main(String[] args)");
		}
		
		@Override
		public String toString() {
			return toJavaHead() + "\n{\n" + toJavaBody() + "}\n";
		}
	//================================================================

    // To Java Methods
    //================================================================
        public String toJavaHead() {
            return "public static " + head.toString().replace("0_TAB", "").replace("0_ENTER", "");
        }

        public String toJavaBody() {
            //Local Variables
            StringBuilder sb = new StringBuilder();

            for (Statement stmt : body) {
                String line = stmt.toString().replace("0_TAB", "").replace("0_ENTER", "");
                sb.append("\t".repeat(Math.max(0, stmt.getDepth()-1))).append("\t").append(line).append("\n");
            }
            if (returnValue != null) {
                sb.append("\t".repeat(Math.max(0, head.getDepth()))).append("\treturn ").append(returnValue).append(";\n");
            }

            return sb.toString();
        }
    //================================================================
}