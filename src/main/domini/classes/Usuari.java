package main.domain.classes;

//import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
//import java.util.TreeMap;


public class Usuari {
    private String nom;
    private String contrasenya;
    private List<Tauler> taulers;

    public Usuari(String nom, String contrasenya) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.taulers = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public List<Tauler> getTaulers() {
        return taulers;
    }

    public void afegirTauler(Tauler tauler) {
        taulers.add(tauler);
    }

    public void esborrarTauler(Tauler tauler) {
        taulers.remove(tauler);
    }
}
