package drivers;

import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlUsuari;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import java.io.IOException;
import java.util.Scanner;

public class DriverUsuari {
    private static CtrlUsuari CU;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        CU =  CtrlUsuari.getInstance();
        int opcio;

        do {
            System.out.println("MENÚ:");
            System.out.println("1. Iniciar sessió");
            System.out.println("2. Registrarse");
            System.out.println("0. Sortir");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    iniciarSessio();
                    menuUsuari();
                    break;
                case 2:
                    registrarse();
                    menuUsuari();
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

    private static void iniciarSessio() throws IOException, ExcepcioContrasenyaIncorrecta, ExcepcioUsuariNoExisteix {
        System.out.println("Introdueix el nom d'usuari:");
        String nomUsuari = scanner.next();
        System.out.println("Introdueix la contrasenya:");
        String contrasenya = scanner.next();
        CU.iniciarSessio(nomUsuari, contrasenya);
    }

    private static void registrarse() throws ExcepcioUsuariJaExisteix, IOException {
        System.out.println("Introdueix el nom d'usuari:");
        String nomUsuari = scanner.next();
        System.out.println("Introdueix la contrasenya:");
        String contrasenya = scanner.next();
        CU.registrarse(nomUsuari, contrasenya);
    }

    private static void menuUsuari() throws ExcepcioContrasenyaIncorrecta, IOException {
        int opcio;
        do {
            System.out.println("MENÚ USUARI:");
            System.out.println("1. Configurar Usuari");
            System.out.println("2. Crear Kenken");
            System.out.println("3. Jugar");
            System.out.println("4. Ranking");
            System.out.println("5. Tancar Sessió");
            System.out.println("0. Tornar");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    canviarContrasenya();
                    break;
                case 5:
                    CU.tancarSessio();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void canviarContrasenya() throws ExcepcioContrasenyaIncorrecta, IOException {
        System.out.println("Introdueix la contrasenya actual:");
        String ctrActual = scanner.next();
        System.out.println("Introdueix la nova contrasenya:");
        String ctrNova = scanner.next();
        System.out.println("Confirma la nova contrasenya:");
        String ctrNovaConfirm = scanner.next();
        if (!ctrNova.equals(ctrNovaConfirm)) {
            System.out.println("La confirmació de la contrasenya no coincideix.");
            return;
        }
        CU.canviarContrasenya(ctrActual, ctrNova);
    }
}