package drivers;

import main.domini.controladors.CtrlUsuari;

import java.util.Scanner;

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
                controladorUsuari.iniciarSessio(nomUsuari, contrasenya);
                sessioIniciada = true;
                System.out.println("Sessió iniciada amb èxit!");
                mostrarMenuUsuari();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Si us plau, torna-ho a provar.");
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
                controladorUsuari.registrarUsuari(nomUsuari, contrasenya);
                usuariRegistrat = true;
                System.out.println("Usuari registrat amb èxit!");
                mostrarMenuUsuari();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Si us plau, torna-ho a provar.");
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