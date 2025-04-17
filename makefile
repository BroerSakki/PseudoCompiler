# Makefile for compiling Java program


# Source Directory
SRC_DIR := src


# Output Directories
CLASS_DIRS = classes/libraries classes/tools core


# Source files
SOURCES = src/ConstLibrary.java src/MethodLibrary.java src/Formatter.java src/Dissector.java src/Main.java
MAIN = src.core.Main


# Class files (replace .java with .class)
CLS := $(SOURCES:$(SRC_DIR)/%.java=$(SRC_DIR)/%.class)


# Compiler and Compile Flags
JC := javac
JVM := java
JC_FLAGS := -d $(SRC_DIR)/ -cp $(SRC_DIR)/

# Suffixes
.SUFFIXES: .java

# Target that does not produce output files
.PHONY: all clean

# Default targets
all: $(CLS)

$(CLS): $(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JC_FLAGS) $<

list_classes:
	@echo "Listing all .class files:"
	dir /S *.class

# Clean up any output files
clean:
	@echo "Removing all .class files..."
	for /R %%d in (*.class) do del /Q "%%d"
	
help:
	@echo "Run 'make' to create all .class files"
	@echo "Run 'make list_classes' to see all .class files"
	@echo "Run 'make clean' to delete all .class files"
	
run:
	@echo "Executing Main Program"
	java -cp src core.Main