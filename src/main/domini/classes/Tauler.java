package main.domini.classes;

//import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
//import java.util.TreeMap;


public class Tauler {
    private int idTauler;
    private int grau;
    private List<Peça> peces;
    private List<Casella> caselles;

    public Tauler(int idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.peces = new ArrayList<>();
        this.caselles = new ArrayList<>();
    }

    public int getIdTauler() {
        return idTauler;
    }

    public int getGrau() {
        return grau;
    }

    public List<Peça> getPeces() {
        return peces;
    }

    public List<Casella> getCaselles() {
        return caselles;
    }

    public void afegirPeça(Peça peça) {
        peces.add(peça);
    }

    public void esborrarPeça(Peça peça) {
        peces.remove(peça);
    }

    public void afegirCasella(Casella casella) {
        caselles.add(casella);
    }

    public void esborrarCasella(Casella casella) {
        caselles.remove(casella);
    }
}
