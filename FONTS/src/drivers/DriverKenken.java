package drivers;

import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlKenkens;

import java.util.Scanner;

/**
 * Driver per a la classe Kenken.
 * Aquesta classe permet interactuar amb la classe Kenken a través de la línia de comandes.
 * @author Ermias Valls Mayor
 */
public class DriverKenken {

    /**
     * Mètode principal del driver.
     * Aquest mètode permet a l'usuari interactuar amb la classe Kenken a través de la línia de comandes.
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcio;
        String idTauler;

        do {
            System.out.println("MENÚ:");
            System.out.println("1. Resoldre");
            System.out.println("2. Mostrar Tauler");
            System.out.println("0. Sortir");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:

                    idTauler= obtenirIdentificadorTauler(scanner);
                    long startTime = System.currentTimeMillis();

                    CtrlKenkens.getInstance().resoldreKenken(idTauler);
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println("Temps en resoldre: " + duration + " ms." + "\n");
                    break;
                case 2:
                    idTauler = obtenirIdentificadorTauler(scanner);
                    CtrlKenkens.getInstance().pintarTauler(idTauler);
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static String obtenirIdentificadorTauler(Scanner scanner) {
        System.out.println("Introdueix l'identificador del tauler amb el format : id-grau");
        return scanner.next();
    }

}