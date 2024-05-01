package drivers;

import main.domini.controladors.CtrlUsuari;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import java.util.Scanner;
import java.io.IOException;

public class DriverUsuari {
    private static CtrlUsuari controladorUsuari;
    private static Scanner scanner;

    public static void main(String[] args) {
        controladorUsuari = new CtrlUsuari();
        scanner = new Scanner(System.in);

        System.out.println("Benvingut/da al driver d'usuari de Kenken!");

        boolean sortir = false;
        while (!sortir) {
            System.out.println("Opcions:");
            System.out.println("1. Iniciar sessió");
            System.out.println("2. Registrar-se");
            System.out.println("3. Sortir");
            int opcio = scanner.nextInt();
            scanner.nextLine(); // Netegem el buffer del scanner

            switch (opcio) {
                case 1:
                    iniciarSessio();
                    break;
                case 2:
                    registrarUsuari();
                    break;
                case 3:
                    sortir = true;
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció invàlida. Si us plau, tria una opció vàlida.");
            }
        }

        // Tanquem el scanner en sortir
        scanner.close();
    }

    private static void iniciarSessio() {
        boolean sessioIniciada = false;
        while (!sessioIniciada) {
            System.out.println("Introdueix el teu nom d'usuari:");
            String nomUsuari = scanner.nextLine();
    
            System.out.println("Introdueix la teva contrasenya:");
            String contrasenya = scanner.nextLine();
    
            try {
                sessioIniciada = controladorUsuari.iniciarSessio(nomUsuari, contrasenya);
                if (sessioIniciada) {
                    System.out.println("Sessió iniciada amb èxit!");
                    mostrarMenuUsuari();
                } else {
                    System.out.println("Usuari o contrasenya incorrectes. Torna-ho a provar.");
                }
            } catch (ExcepcioUsuariNoExisteix e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error al intentar iniciar sessió. Si us plau, torna-ho a provar.");
            }
        }
    }
    
    private static void registrarUsuari() {
        boolean usuariRegistrat = false;
        while (!usuariRegistrat) {
            System.out.println("Introdueix el teu nom d'usuari:");
            String nomUsuari = scanner.nextLine();

            System.out.println("Introdueix la teva contrasenya:");
            String contrasenya = scanner.nextLine();

            try {
                usuariRegistrat = controladorUsuari.registrarUsuari(nomUsuari, contrasenya);
                if (usuariRegistrat) {
                    System.out.println("Usuari registrat amb èxit!");
                    mostrarMenuUsuari();
                } else {
                    System.out.println("Error al registrar l'usuari. Si us plau, intenta-ho de nou.");
                }
            } catch (ExcepcioUsuariJaExisteix e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error al intentar registrar l'usuari. Si us plau, torna-ho a provar.");
            }
        }
    }

    private static void mostrarMenuUsuari() {
        boolean sortir = false;
        while (!sortir) {
            System.out.println("Opcions:");
            System.out.println("1. Configurar perfil");
            System.out.println("2. Crear KenKen");
            System.out.println("3. Jugar");
            System.out.println("4. Mirar el ranking");
            System.out.println("5. Sortir");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Netegem el buffer del scanner

            switch (opcio) {
                case 1:
                    configurarPerfil();
                    break;
                case 2:
                    crearKenKen();
                    break;
                case 3:
                    jugar();
                    break;
                case 4:
                    mirarRanking();
                    break;
                case 5:
                    sortir = true;
                    System.out.println("Sessió tancada. Fins aviat!");
                    break;
                default:
                    System.out.println("Opció invàlida. Si us plau, tria una opció vàlida.");
            }
        }
    }

    private static void configurarPerfil() {
        // Lògica per configurar el perfil de l'usuari
    }

    private static void crearKenKen() {
        // Lògica per crear un nou KenKen
    }

    private static void jugar() {
        // Lògica per iniciar una partida de KenKen
    }

    private static void mirarRanking() {
        // Lògica per mostrar el ranking d'usuaris
    }
}