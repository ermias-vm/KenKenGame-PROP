package drivers;

import main.persistencia.ControladorPersistenciaTauler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class DriverAfegirTauler {
    public static void main(String[] args) {
        DriverAfegirTauler driverAfegirTauler = new DriverAfegirTauler();
        ControladorPersistenciaTauler controladorPersistenciaTauler = ControladorPersistenciaTauler.getInstance();
        while (true){
            System.out.println("Enter a block of text (type 'END' to finish):");
            Scanner scanner = new Scanner(System.in);
            StringBuilder blockOfText = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ("END".equals(line)) {
                    break;
                }
                blockOfText.append(line).append("\n");
            }
            System.out.println("You entered:\n" + blockOfText.toString());
            System.out.println("Identificador: "+controladorPersistenciaTauler.generaIdentificadorIGuardaTauler(blockOfText.toString()));
        }
    }
}
