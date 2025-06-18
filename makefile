# Makefile for compiling Java program

SHELL := cmd.exe

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
all: new clear_all run

update: clear_update build_clear run

# Build all required .class files
build: print_new libraries datatypes tools main clear_build print_compile_success wait_build


# Build all required .class files
build_clear: build clear_buildcl

# Rebuild program
new: clear_new1 clean clear_new2 print_new build

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
clean: clear_clean1 print_clean run_clean wait_clean1 clear_clean2 print_clean_success wait_clean2
	
run_clean:
	@for /R %%d in (*.class) do del /Q "%%d"



# Clear screen
clear_all:
	@cmd /c cls

clear_update:
	@cmd /c cls

clear_build:
	@cmd /c cls
	
clear_buildcl:
	@cmd /c cls

clear_new1:
	@cmd /c cls

clear_new2:
	@cmd /c cls

clear_clean1:
	@cmd /c cls

clear_clean2:
	@cmd /c cls



# Print echo statements
print_execute:
	@echo "Executing Main Program"

print_clean:
	@echo "Removing all .class files..."

print_clean_success:
	@echo "Class files removed successfully"

print_new:
	@echo "Building new files..."

print_compile_success:
	@echo "Compiled successfully"



# Wait 1 second
wait_build:
	@ping -n 2 127.0.0.1 >nul

wait_clean1:
	@ping -n 2 127.0.0.1 >nul

wait_clean2:
	@ping -n 2 127.0.0.1 >nul

wait_run:
	@ping -n 2 127.0.0.1 >nul



# Name and explain necessary commands
help:
	@echo "Run 'make':			Creates then runs all .class files"
	@echo "Run 'make list_classes':	Print all .class files"
	@echo "Run 'make clean':		Deletes all .class files"
	@echo "Run 'make run':		Run the compiled program"
	@echo "Run 'make <directory name>':	Compile all files from <directory name>"
	@echo "Run 'make  build':		Compile all java code"
	@echo "Run 'make build_clear':	Compile all java code then clear the terminal"
	@echo "Run 'make clear':		Clear the terminal"
	

	
run: print_execute wait_run
	@java -cp src Main