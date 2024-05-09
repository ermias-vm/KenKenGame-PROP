package main.domini.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreadorKenkenParam {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demanar el grau del tauler
        System.out.println("De quin grau vols el tauler?");
        int grau = scanner.nextInt();

        // Demanar el nombre d'operacions
        System.out.println("Quantes operacions vols?");
        int numOperacions = scanner.nextInt();

        // Demanar les operacions
        System.out.println("Digue'm aquestes " + numOperacions + " operacions (nombres del 1 al 6):");
        int[] operacions = new int[numOperacions];
        for (int i = 0; i < numOperacions; i++) {
            operacions[i] = scanner.nextInt();
        }

        scanner.close();

        // Generar el tauler
        //char[][] tauler = generarTauler(grau);
    }

    // Mètode per generar un tauler buit
    /*
    private static char[][] generarTauler(int grau) {
        
    }
    */

    // Mètode per desar el tauler en un fitxer de text
    /*
    private static void desarTaulerEnFitxer(char[][] tauler, String nomFitxer) {
        
    }
    */
}