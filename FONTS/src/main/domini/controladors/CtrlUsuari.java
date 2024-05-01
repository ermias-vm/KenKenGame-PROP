package main.domini.controladors;

import main.domini.excepcions.*;

import main.domini.classes.Usuari;

import java.io.*;
//import java.util.Scanner;

/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 * @author David Giribet
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
        
        try (BufferedReader br = new BufferedReader(new FileReader("Data/BDUsuaris.txt"))) {
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
     * @param nomUsuari El nom a registrar.
     * @param contrasenya La contrasenya a registrar.
     */
    public boolean registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        if (existeixUsuari(nomUsuari)) {
            throw new ExcepcioUsuariJaExisteix("Ja existeix un usuari amb aquest nom.");
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data/BDUsuaris.txt", true))) {
            bw.write(nomUsuari + " " + contrasenya);
            bw.newLine();
        }
        
        return true;
    }

    /**
     * Obtenir Contrasenya.
     * @param nomUsuari El nom d'usuari.
     * @return La contrasenya de l'usuari.
     */
    public String obtenirContrasenya(String nomUsuari) {
        String contrasenya = null;
        try (BufferedReader br = new BufferedReader(new FileReader("Data/BDUsuaris.txt"))) {
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
     */
    public boolean iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioUsuariNoExisteix, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Data/BDUsuaris.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String nom = partes[0];
                String contrasenyaGuardada = partes[1];
                if (nom.equals(nomUsuari) && contrasenya.equals(contrasenyaGuardada)) {
                    Usuari usuari = new Usuari(nomUsuari, contrasenya);
                    setUsuariActual(usuari);
                    return true;
                }
            }
        }
        throw new ExcepcioUsuariNoExisteix("Usuari o contrasenya incorrectes.");
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
