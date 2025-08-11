package classes.tools;

import classes.datatypes.Statement;

public class MethodBuilder {
	
	// Global variables
	//================================================================
    	private Statement head;
    	private Statement[] body;
    	private String returnValue;
    //================================================================

    // Constructors
    //================================================================
    	/**
    	 * Creates a MethodBuilder instance with a head, body, and return value.
    	 * @param head the head of the method, typically a Statement object
    	 * @param body the body of the method, an array of Statement objects
    	 * @param returnValue the return value of the method, can be null if not applicable
    	 */
    	public MethodBuilder(Statement head, Statement[] body, String returnValue) {
    		setHead(head); // Validate the head based on the method head
    		setBody(body); // Validate the body based on the method head
    		setReturnValue(returnValue); // Validate the return value based on the method head
    	}
    	/**
		 * Creates a MethodBuilder instance with a head and a return value, with an empty body.
		 * @param head the head of the method, typically a Statement object
		 * @param returnValue the return value of the method, can be null if not applicable
		 */
    	public MethodBuilder(Statement head, String returnValue) {
    		setHead(head); // Validate the head based on the method head
    		setBody(new Statement[] {}); // Initialize with an empty body
    		setReturnValue(returnValue); // Validate the return value based on the method head
    	}
    	/**
		 * Creates a MethodBuilder instance with a head and an empty body, with no return value.
		 * @param head the head of the method, typically a Statement object
		 */
    	public MethodBuilder(Statement head) {
    		this(buildHeadMain(), new Statement[] {}, null);
    	}
    //================================================================
    	
    // Setters
    //================================================================
   		/**
    	 * Sets the head of the method.
    	 * @param head the new head Statement for the method
   		 */
   		public void setHead(Statement head) {
   			this.head = head;
   		}
   		/**
   		 * Sets the body of the method.
   		 * @param body an array of Statement objects to set as the body of the method
   		 */
    	public void setBody(Statement[] body) {
    		this.body = body;
    		}
    	/**
   		 * Sets the return value of the method.
   		 * @param returnValue the new return value as a String, can be null if not applicable
   		 */
   		public void setReturnValue(String returnValue) {
    		this.returnValue = returnValue;
    		checkReturnValue(); // Ensure the return value is valid based on the method head
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
	//================================================================
		
	// Work Methods
	//================================================================
		/**
		 * Build a Statement for the main method.
		 * @return a Statement object representing the main method head
		 */
		private static Statement buildHeadMain() {
			return new Statement("public static void main(String[] args)");
		}
		
		/**
		 * Validates the return value based on the method head.
		 * If the method is void, the return value should be null.
		 */
		private void checkReturnValue() {
			if (head.getKeywords().contains("void") && returnValue != null) {
				this.returnValue = null; // If the method is void, returnValue should be null
			} else if (returnValue == null) {
				if (head.getKeywords().contains("string")) {
					this.returnValue = "null";
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\t").append(head.toString()).append("\n\t{\n");
			for (Statement stmt : body) {
				sb.append(stmt.getDepthTabs()).append("\t").append(stmt.toString()).append("\n");
			}
			if (returnValue != null) {
				sb.append("\t\treturn ").append(returnValue).append(";\n");
			}
			sb.append("\t}");
			return sb.toString();
		}
	//================================================================
}