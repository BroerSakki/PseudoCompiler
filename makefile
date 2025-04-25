# Makefile for compiling Java program


# Source Directory
SRC_DIR := src


# Output Directories
CLASS_DIRS = classes/libraries classes/tools core


# Source files
SOURCES_LIBRARIES = src/ConstLibrary.java src/ColorLibrary.java src/MethodLibrary.java
SOURCES_DATATYPES = src/Num.java
SOURCES_TOOLS = src/Directories.java src/ColoringTools.java src/StartupTools.java src/Formatter.java src/Dissector.java
SOURCE_MAIN = src/Main.java


# Class files (replace .java with .class)
CLASS_MAIN = $(SOURCE_MAIN:$(SRC_DIR)/%.java=$(SRC_DIR)/%.class)
CLASSES_LIBRARIES = $(SOURCES_LIBRARIES:$(SRC_DIR)/%.java=$(SRC_DIR)/%.class)
CLASSES_DATATYPES = $(SOURCES_DATATYPES:$(SRC_DIR)/%.java=$(SRC_DIR)/%.class)
CLASSES_TOOLS = $(SOURCES_TOOLS:$(SRC_DIR)/%.java=$(SRC_DIR)/%.class)


# Compiler and Compile Flags
JC := javac
JVM := java
JC_FLAGS := -d $(SRC_DIR)/ -cp $(SRC_DIR)/

# Suffixes
.SUFFIXES: .java

# Target that does not produce output files
.PHONY: all clean

# Default targets
all: libraries datatypes tools main clear run


# Build all required .class files
build: libraries datatypes tools main


# Build all required .class files
build-clear: libraries datatypes tools main clear


# Build Main Class
main: $(CLASS_MAIN)

$(CLASS_MAIN): $(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JC_FLAGS) $<
	

# Build Libraries Directory
libraries: $(CLASSES_LIBRARIES)

$(CLASSES_LIBRARIES): $(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JC_FLAGS) $<
	

# Build Datatypes Directory
datatypes: $(CLASSES_DATATYPES)

$(CLASSES_DATATYPES): $(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JC_FLAGS) $<
	

# Build Tools Directory
tools: $(CLASSES_TOOLS)

$(CLASSES_TOOLS): $(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JC_FLAGS) $<


# List all classes
list_classes:
	@echo "Listing all .class files:"
	dir /S *.class


# Clean up any output files
clean:
	@echo "Removing all .class files..."
	for /R %%d in (*.class) do del /Q "%%d"
	

clear:
		@cls


# Name and explain necessary commands
help:
	@echo "Run 'make' to create all .class files"
	@echo "Run 'make list_classes' to see all .class files"
	@echo "Run 'make clean' to delete all .class files"
	@echo "Run 'make run' to run the compiled program"
	@echo "Run 'make <directory name> to compile all files from a directory"
	
	
run:
	@echo "Executing Main Program"
	java -cp src core.Main