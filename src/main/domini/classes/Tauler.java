package main.domini.classes;

import java.util.ArrayList;
import java.util.List;

public class Tauler {
    private int idTauler;
    private int grau;
    private List<Regio> regions;
    private List<Casella> caselles;

    public Tauler(int idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.regions = new ArrayList<>();
        this.caselles = new ArrayList<>();
    }

    public int getIdTauler() {
        return idTauler;
    }

    public int getGrau() {
        return grau;
    }

    public List<Regio> getRegions() {
        return regions;
    }

    public List<Casella> getCaselles() {
        return caselles;
    }

    public void afegirRegio(Regio regio) {
        regions.add(regio);
    }

    public void esborrarRegio(Regio regio) {
        regions.remove(regio);
    }

    public void afegirCasella(Casella casella) {
        caselles.add(casella);
    }

    public void esborrarCasella(Casella casella) {
        caselles.remove(casella);
    }
}