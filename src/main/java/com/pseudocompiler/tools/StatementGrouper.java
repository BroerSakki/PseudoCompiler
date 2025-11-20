package com.pseudocompiler.tools;

import com.pseudocompiler.datatypes.Statement;

public class StatementGrouper {

    // Global Variables
    //================================================================
        private Statement head;
        private Statement[] body;
        private boolean isIsolated;
    //================================================================

    // Constructors
    //================================================================
        public StatementGrouper(Statement head, Statement[] body) {
            setHead(head);
            setBody(body);
        }
    //================================================================

    // Setters
    //================================================================
        public void setHead(Statement head) {
            this.head = head;
        }
        public void setBody(Statement[] body) {
            this.body = body;
        }
        public void setIsolated(boolean isIsolated) {
            this.isIsolated = isIsolated;
        }
    //================================================================

    // Getters
    //================================================================
        public Statement getHead() {
            return head;
        }
        public Statement[] getBody() {
            return body;
        }
        public boolean getIsIsolated() {
            return isIsolated;
        }
    //================================================================
}
