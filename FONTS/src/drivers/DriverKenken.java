package drivers;

import main.domini.controladors.CtrlDomini;

import java.util.Scanner;

/**
 * Driver per a la classe Kenken.
 * Aquesta classe permet interactuar amb la classe Kenken a través de la línia de comandes.
 * @author Ermias Valls Mayor
 */
public class DriverKenken {
    /**
     * Controlador de domini que es farà servir per interactuar amb la classe Kenken.
     */
    private static CtrlDomini CD;

    /**
     * Constructor del driver.
     * @param CD Controlador de domini que es farà servir per interactuar amb la classe Kenken.
     */
    public DriverKenken(CtrlDomini CD) {
        this.CD = CD;
    }

    /**
     * Mètode principal del driver.
     * Aquest mètode permet a l'usuari interactuar amb la classe Kenken a través de la línia de comandes.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int idTauler = 0;
        String grau = "";

        do {
            System.out.println("Introdueixi un el Tauler a solucionar  [1-10]:");
            System.out.println("Introdueixi 0 per sortir.");
            idTauler = scanner.nextInt();

            if (idTauler == 0) {
                break;
            }

            if (idTauler < 1 || idTauler > 10) {
                System.out.println("Número invàlid. Si us plau, introdueixi un número entre 1 i 10.");
                continue;
            }

            scanner.nextLine(); // consume the newline left-over
            System.out.println("Introdueixi el grau:");
            grau = scanner.nextLine();

            try {
                CD.importarKenken(idTauler, grau);
            } catch (Exception e) {
                System.out.println("S'ha produït un error: " + e.getMessage());
            }
        } while (idTauler != 0);

        scanner.close();
    }
}