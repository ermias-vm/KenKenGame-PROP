DOMINI = $(shell find FONTS/src/main/domini -name "*.java")
PERSISTENCIA = $(shell find FONTS/src/main/persistencia -name "*.java")
PRESENTACIO = $(shell find FONTS/src/main/presentacio -name "*.java")
DRIVERS = $(shell find FONTS/src/drivers -name "*.java")
TESTS = $(shell find FONTS/src/test -name "*.java")
EXE = bin

ifeq ($(OS), Windows_NT)
CLPTH = "$(EXE);lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar;lib/gson-2.8.6.jar;lib/forms_rt.jar;resources"
else
CLPTH = "$(EXE):lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:lib/gson-2.8.6.jar:lib/forms_rt.jar:resources"
endif

default: class

class:
	javac -d $(EXE) -encoding UTF-8 -cp $(CLPTH) -sourcepath FONTS FONTS/src/Main.java $(DOMINI) $(PERSISTENCIA) $(PRESENTACIO) $(DRIVERS) $(TESTS)

run:
	java -jar subgrup-prop41.5.jar



runDriverKenken:
	java -cp $(CLPTH) drivers.DriverKenken

runDriverUsuari:
	java -cp $(CLPTH) drivers.DriverUsuari



runTestCasella:
	java -cp $(CLPTH) org.junit.runner.JUnitCore test.CasellaTest

runTestRegio:
	java -cp $(CLPTH) org.junit.runner.JUnitCore test.RegioTest

runAllTests: runTestCasella runTestRegio


resetData:
	@RESP=$$(read -p "Estàs segur que vols eliminar els fitxers? (S/n) " RESP; echo $$RESP); \
	if [ "$$RESP" != "n" ]; then \
		for num in 3 4 5 6 7 8 9; do \
			find data/taulers/mida$$num -type f \( -name '[7-9][0-9]-*' -o -name '1[0-9][0-9]-*' \) -delete; \
		done; \
		find data/partides/ -type f -name '*.txt' -exec sh -c 'echo -n "" > "{}"' \; ; \
		echo "Fitxers eliminats correctament."; \
	else \
		echo "Operació cancel·lada."; \
	fi

clean:
	rm -rf $(EXE)/*
