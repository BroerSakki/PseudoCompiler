# Makefile for compiling Java program


# Source Directory
SRC_DIR := src


# Output Directories
CLASS_DIRS = classes/libraries classes/tools core


# Source files
SOURCES_LIBRARIES = src/classes/libraries/ConstLibrary.java src/classes/libraries/ColorLibrary.java src/classes/libraries/MethodLibrary.java
SOURCES_DATATYPES = src/classes/datatypes/Num.java
SOURCES_TOOLS = src/classes/tools/Directories.java src/classes/tools/ColoringTools.java src/classes/tools/StartupTools.java src/classes/tools/Grouper.java src/classes/tools/Formatter.java src/classes/tools/Dissector.java
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
build: print_new libraries datatypes tools main


# Build all required .class files
build-clear: print_new libraries datatypes tools main clear

# Rebuild program
new: clear clean run_clear print_new build

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
	@timeout /t 1 >nul
	

clear:
	@cls

run_clear:
	@cls

# Print echo statement
print_new:
	@echo "Building new files..."


# Name and explain necessary commands
help:
	@echo "Run 'make':			Creates then runs all .class files"
	@echo "Run 'make list_classes':	Print all .class files"
	@echo "Run 'make clean':		Deletes all .class files"
	@echo "Run 'make run':		Run the compiled program"
	@echo "Run 'make <directory name>':	Compile all files from <directory name>"
	@echo "Run 'make  build':		Compile all java code"
	@echo "Run 'make build-clear':	Compile all java code then clear the terminal"
	@echo "Run 'make clear':		Clear the terminal"
	
	
run:
	@echo "Executing Main Program"
	java -cp src Main