# Makefile for Compiling Java Program

# Set Terminal Specific Commands
#======================================================================================================================================================
# Detect if running in WSL
IS_WSL := $(shell uname -r 2>/dev/null | grep -i -E 'microsoft|WSL')

# Default to Windows commands if OS is Windows_NT, unless WSL is detected
ifeq ($(OS),Windows_NT)
ifneq ($(IS_WSL),)
# WSL (Unix-like commands)
RM := rm -f
MKDIR := mkdir -p
CLEAR := clear
CLEAN := find . -type f -name "*.class" -delete
DELAY := sleep 1
else
# Windows (cmd.exe or PowerShell)
RM := del /Q
MKDIR := mkdir
CLEAR := cls
CLEAN := for /R %%d in (*.class) do del /Q "%%d"
DELAY := ping -n 2 127.0.0.1 >nul
endif
else
# Unix-like shell (bash, sh, etc.)
RM := rm -f
MKDIR := mkdir -p
CLEAR := clear
CLEAN := find . -type f -name "*.class" -delete
DELAY := sleep 1
endif
#======================================================================================================================================================

# Global Variables
#======================================================================================================================================================
#Directories
DIR_SRC := src
DIR_LIB := src/classes/libraries/
DIR_DATATYPES := src/classes/datatypes/
DIR_TOOLS := src/classes/tools/

#Source Files
SOURCES_LIBRARIES = $(DIR_LIB)ConstLibrary.java $(DIR_LIB)ColorLibrary.java $(DIR_LIB)MethodLibrary.java $(DIR_LIB)StatementLibrary.java
SOURCES_DATATYPES = $(DIR_DATATYPES)Num.java $(DIR_DATATYPES)Statement.java $(DIR_DATATYPES)Variable.java
SOURCES_TOOLS = $(DIR_TOOLS)Directories.java $(DIR_TOOLS)ColoringTools.java $(DIR_TOOLS)StartupTools.java $(DIR_TOOLS)Grouper.java $(DIR_TOOLS)Formatter.java $(DIR_TOOLS)Dissector.java
SOURCE_MAIN = src/Main.java

#Class Files
CLASS_MAIN = $(SOURCE_MAIN:$(DIR_SRC)/%.java=$(DIR_SRC)/%.class)
CLASSES_LIBRARIES = $(SOURCES_LIBRARIES:$(DIR_SRC)/%.java=$(DIR_SRC)/%.class)
CLASSES_DATATYPES = $(SOURCES_DATATYPES:$(DIR_SRC)/%.java=$(DIR_SRC)/%.class)
CLASSES_TOOLS = $(SOURCES_TOOLS:$(DIR_SRC)/%.java=$(DIR_SRC)/%.class)

#Compiler and Run Flags
JC := javac
JVM := java
JC_FLAGS := -d $(DIR_SRC)/ -cp $(DIR_SRC)/
#======================================================================================================================================================

# Message Variables
#======================================================================================================================================================
HELP_MAKE := "make:			Reset, recompile and run PseudoCompiler"
HELP_MAKE_CLEAN := "make clean:			Remove all .class files"
HELP_MAKE_RUN := "make run:			Run Main class of PseudoCompiler"
HELP_MAKE_BUILD := "make build:			Compile all java code"
HELP_MAKE_CLEAR := "make clear:			Clear the terminal"
#======================================================================================================================================================

# Suffixes
#======================================================================================================================================================
.SUFFIXES: .java
#======================================================================================================================================================

# Base Commands
#======================================================================================================================================================
#Default
all: new clear_all run
run:
	@java -cp src Main

#Updates
new: clear_new1 clean clear_new2 print_new build

#Build
build: print_new libraries datatypes tools main clear_build print_compile_success wait_build
build_clear: build clear_buildcl
#======================================================================================================================================================

# Formatting Commands
#======================================================================================================================================================
#Print
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
help:
	@echo $(HELP_MAKE)
	@echo $(HELP_MAKE_CLEAN)
	@echo $(HELP_MAKE_RUN)
	@echo $(HELP_MAKE_BUILD)
	@echo $(HELP_MAKE_CLEAR)

#Clear
clear_all:
	@$(CLEAR)
clear_update:
	@$(CLEAR)
clear_build:
	@$(CLEAR)
clear_buildcl:
	@$(CLEAR)
clear_new1:
	@$(CLEAR)
clear_new2:
	@$(CLEAR)
clear_clean1:
	@$(CLEAR)
clear_clean2:
	@$(CLEAR)
	
#Wait
wait_build:
	@$(DELAY)
wait_clean1:
	@$(DELAY)
wait_clean2:
	@$(DELAY)
wait_run:
	@$(DELAY)
	
#Clean
clean_execute:
	@$(CLEAN)
clean: clear_clean1 print_clean clean_execute wait_clean1 clear_clean2 print_clean_success wait_clean2
#======================================================================================================================================================

# Library Build Commands
#======================================================================================================================================================
main: $(CLASS_MAIN)
$(CLASS_MAIN): $(DIR_SRC)/%.class: $(DIR_SRC)/%.java
	$(JC) $(JC_FLAGS) $<
libraries: $(CLASSES_LIBRARIES)
$(CLASSES_LIBRARIES): $(DIR_SRC)/%.class: $(DIR_SRC)/%.java
	$(JC) $(JC_FLAGS) $<
datatypes: $(CLASSES_DATATYPES)
$(CLASSES_DATATYPES): $(DIR_SRC)/%.class: $(DIR_SRC)/%.java
	$(JC) $(JC_FLAGS) $<
tools: $(CLASSES_TOOLS)
$(CLASSES_TOOLS): $(DIR_SRC)/%.class: $(DIR_SRC)/%.java
	$(JC) $(JC_FLAGS) $<
#======================================================================================================================================================
