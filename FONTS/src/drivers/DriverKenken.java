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
     * Mètode principal del driver.
     * Aquest mètode permet a l'usuari interactuar amb la classe Kenken a través de la línia de comandes.
     */
    public static void main(String[] args) throws Exception {
        CD = new CtrlDomini();
        Scanner scanner = new Scanner(System.in);
        int opcio, idTauler;
        String grau;

        do {
            System.out.println("MENÚ:");
            System.out.println("1. Crear/Validar");
            System.out.println("2. Resoldre");
            System.out.println("3. Generar Automàticament");
            System.out.println("4. Mostrar Tauler");
            System.out.println("0. Sortir");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    System.out.println("Pendent a implementar");
                    break;
                case 2:
                    grau = obtenirGrau(scanner);
                    idTauler = obtenirIdentificador(scanner);
                    CD.resoldreKenken(idTauler, grau);
                    break;
                case 3:
                    System.out.println("Pendent a implementar");
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    break;
                case 4:
                    grau = obtenirGrau(scanner);
                    idTauler = obtenirIdentificador(scanner);
                    CD.pintarTaulerJoc(idTauler, grau);
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static int obtenirIdentificador(Scanner scanner) {
        System.out.println("Introdueix l'identificador del tauler:");
        return scanner.nextInt();
    }

    private static String obtenirGrau(Scanner scanner) {
        System.out.println("Introdueix el grau del tauler:");
        return scanner.next();
    }
}