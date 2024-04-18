package main.domini.classes;

import java.util.ArrayList;
import java.util.List;

public class Tauler {
    private int idTauler;
    private int grau;
    private List<Casella> caselles;

    public Tauler(int idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.caselles = new ArrayList<>();
    }

    public Tauler(int idTauler, int grau, ArrayList<Casella> caselles) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.caselles = caselles;
    }

    public int getIdTauler() {
        return idTauler;
    }

    public int getGrau() {
        return grau;
    }

    public Casella getCasella(int x, int y) {
        for (Casella casella : this.caselles) {
            if (casella.getPosX() == x && casella.getPosY() == y) {
                return casella;
            }
        }
        return null;
    }

    public List<Casella> getCaselles() {
        return caselles;
    }

    public void afegirCasella(Casella casella) {
        caselles.add(casella);
    }

    public void borrarCasella(Casella casella) {
        caselles.remove(casella);
    }

    public int getValor(int x, int y) {
        return getCasella(x, y).getValor();
    }

    public void setValor(int x, int y, int num) {
        getCasella(x, y).setValor(num);
    }

    public void borrarValor(int x, int y) {
        getCasella(x, y).borrarValor();
    }

    public boolean esModificable(int x, int y) {
        return getCasella(x, y).esModificable();
    }

    public boolean esBuida(int x, int y) {
        return getCasella(x, y).esBuida();
    }
}