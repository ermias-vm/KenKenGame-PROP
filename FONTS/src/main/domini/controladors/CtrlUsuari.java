package main.domini.controladors;

import main.domini.excepcions.*;

import main.domini.classes.Usuari;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 */
public class CtrlUsuari {
    private Usuari usuariActual;

    /**
     * Constructor de la clase CtrlUsuari.
     */
    public CtrlUsuari() {
        this.usuariActual = null;
    }

    public Usuari getUariActual() {
        return usuariActual;
    }

    /**
     * Establir Usuari Loggejat.
     * @param usuari L'usuari en questio.
     */
    public void setUsuariActual(Usuari usuari) {
        this.usuariActual = usuari;
    }

    /**
     * Existeix l'usuari donat?
     * @param nomUsuari El nom a verificar.
     * @return True si existeix, False si no existeix.
     */
    public boolean existeixUsuari(String nomUsuari) {
        boolean existeix = false;
        String linea;
        
        try (BufferedReader br = new BufferedReader(new FileReader("BDUsuaris.txt"))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String nom = partes[0];
                if (nom.equals(nomUsuari)) {
                    existeix = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al llegir l'arxiu: " + e.getMessage());
        }
        
        return existeix;
    }

    /**
     * Registar usuari nou.
     * @param numUsuari El nom a registrar.
     */
    public void registrarUsuari(String nomUsuari, String contrasenya) {

        try {
            if (existeixUsuari(nomUsuari)) throw new ExcepcioUsuariJaExisteix();
        } catch (ExcepcioUsuariJaExisteix e) {
            System.out.println(e.getMessage());
        }

        try {
            FileWriter fw = new FileWriter("BDUsuaris.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(nomUsuari + " " + contrasenya);

            pw.close();
            bw.close();
            fw.close();

            System.out.println("Usuario registrado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al intentar registrar el usuario.");
            e.printStackTrace();
        }
    }

    /**
     * Obtenir Contrasenya.
     * @param nomUsuari El nom d'usuari.
     * @return La contrasenya de l'usuari.
     */
    public String obtenirContrasenya(String nomUsuari) {
        String contrasenya = null;
        try (BufferedReader br = new BufferedReader(new FileReader("BDUsuaris.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String nom = partes[0];
                if (nom.equals(nomUsuari)) {
                    contrasenya = partes[1];
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contrasenya;
    }
    

    /**
     * Iniciar sessio.
     * @param nomUsuari El nom d'usuari.
     * @param contrasenya La contrasenya de l'usuari.
     * @return .
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) {

        try {
            if (!existeixUsuari(nomUsuari)) throw new ExcepcioUsuariNoExisteix();
        } catch (ExcepcioUsuariNoExisteix e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("BDUsuaris.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts[0].equals(nomUsuari)) {
                    if (parts[1].equals(contrasenya)) {
                        System.out.println("Inicio de sesión exitoso.");
                        String c = obtenirContrasenya(nomUsuari);
                        Usuari usuari = new Usuari(nomUsuari,c);
                        setUsuariActual(usuari);
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Contraseña incorrecta.");
                        scanner.close();
                        return;
                    }
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error al intentar iniciar sesion.");
            e.printStackTrace();
        }
    }

    /**
     * Canviar Contrasenya.
     * @param usuari El nom d'usuari.
     * @param ctrActual La contrasenya actual.
     * @param ctrNova La contrasenya nova.
     */
    public void canviarContrasenya(String usuari, String ctrActual, String ctrNova) {
        if (existeixUsuari(usuari) && obtenirContrasenya(usuari).equals(ctrActual)) {

        } else {
            System.out.println("La contrasenya actual o l'usuari es incorrecte.");
        }
    }

    /**
     * Deixo aixo comentat perque tot i que no crec que ho necessitem, ja esta per si de cas.
     */
    /*
    public void tancarSessio() {
        usuariActual = null;
    }
    */

}
