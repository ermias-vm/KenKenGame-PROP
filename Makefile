DOMINI = $(shell find FONTS/src/main/domini -name "*.java")
PERSISTENCIA = $(shell find FONTS/src/main/persistencia -name "*.java")
PRESENTACIO = $(shell find FONTS/src/main/presentacio -name "*.java")
EXE = bin

ifeq ($(OS), Windows_NT)
CLPTH = "$(EXE);lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar;lib/gson-2.8.6.jar;lib/forms_rt.jar;resources"
else
CLPTH = "$(EXE):lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:lib/gson-2.8.6.jar:lib/forms_rt.jar:resources"
endif

default: class

class:
	javac -d $(EXE) -encoding UTF-8 -cp $(CLPTH) -sourcepath FONTS FONTS/src/Main.java $(DOMINI) $(PERSISTENCIA) $(PRESENTACIO)

run:
	java -cp $(CLPTH) Main

clean:
	rm -rf $(EXE)/*
