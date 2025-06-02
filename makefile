# Makefile for compiling Java program


# Source Directory
SRC_DIR := src


# Output Directories
DIR_LIB = src/classes/libraries/
DIR_DATATYPES = src/classes/datatypes/
DIR_TOOLS = src/classes/tools/


# Source files
SOURCES_LIBRARIES = $(DIR_LIB)ConstLibrary.java $(DIR_LIB)ColorLibrary.java $(DIR_LIB)MethodLibrary.java $(DIR_LIB)StatementLibrary.java
SOURCES_DATATYPES = $(DIR_DATATYPES)Num.java $(DIR_DATATYPES)Statement.java
SOURCES_TOOLS = $(DIR_TOOLS)Directories.java $(DIR_TOOLS)ColoringTools.java $(DIR_TOOLS)StartupTools.java $(DIR_TOOLS)Grouper.java $(DIR_TOOLS)Formatter.java $(DIR_TOOLS)Dissector.java
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
all: new run_clear2 run


# Build all required .class files
build: print_new libraries datatypes tools main run_clear3 print_compile_success wait


# Build all required .class files
build-clear: build clear

# Rebuild program
new: clear clean run_clear1 print_new build

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

run_clear1:
	@cls
	
run_clear2:
	@cls

run_clear3:
	@cls

# Print echo statements
print_new:
	@echo "Building new files..."

print_compile_success:
	@echo "Compiled successfully"
	
# Wait 1 second
wait:
	@timeout /t 1 >nul

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
	@timeout /t 1 >nul
	java -cp src Main