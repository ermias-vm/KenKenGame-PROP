package main.domini.classes;

//import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
//import java.util.TreeMap;


/**
 * Classe que representa un usuari del sistema.
 * @author David Giribet
 */
public class Usuari {
    private String nom; // Nom de l'usuari
    private String contrasenya; // Contrasenya de l'usuari
    private List<Tauler> taulers; // Llista de taulers de l'usuari

    /**
     * Constructor de la classe Usuari.
     *
     * @param nom          Nom de l'usuari.
     * @param contrasenya  Contrasenya de l'usuari.
     */
    public Usuari(String nom, String contrasenya) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.taulers = new ArrayList<>();
    }

    /**
     * Obté el nom de l'usuari.
     *
     * @return Nom de l'usuari.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obté la contrasenya de l'usuari.
     *
     * @return Contrasenya de l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Obté la llista de taulers de l'usuari.
     *
     * @return Llista de taulers de l'usuari.
     */
    public List<Tauler> getTaulers() {
        return taulers;
    }

    /**
     * Afegeix un tauler a la llista de taulers de l'usuari.
     *
     * @param tauler Tauler a afegir.
     */
    public void afegirTauler(Tauler tauler) {
        taulers.add(tauler);
    }

    /**
     * Esborra un tauler de la llista de taulers de l'usuari.
     *
     * @param tauler Tauler a esborrar.
     */
    public void esborrarTauler(Tauler tauler) {
        taulers.remove(tauler);
    }
}
