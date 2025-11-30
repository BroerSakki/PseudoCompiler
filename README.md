# PseudoCompiler

Transpiler for pseudocode

# Format

The format of pseudocode that can be transpiled, is akin to that found in the Cengage textbook: Programming Logic and Design, by Joyce Farrel. Some things are excluded, such as classes, and file handling. It is, in fact, a simple version of pseudocode.

# Add to ~/.bashrc
Fast build:
alias mci='./mvnw clean install -DskipTests'

Run
alias mr='./mvnw exec:java'
OR
alias mr='java -jar target/*-SNAPSHOT.jar'
