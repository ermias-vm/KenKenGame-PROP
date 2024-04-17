package main.domini.classes;

import java.util.List;
import java.util.ArrayList;

public class TaulerJoc extends Tauler {

    private boolean trobat;
    private List<RegioJoc> regionsJoc;

    public TaulerJoc(int idTauler, int grau) {
        super(idTauler, grau);
        this.regionsJoc = new ArrayList<>();
    }

    //// x, y, num estan a rang  [1,grau]  (Implementar codis error)
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
    ////

    public void afegirRegioJoc(RegioJoc regioJoc) {
        this.regionsJoc.add(regioJoc);
    }

    public void borrarRegioJoc(RegioJoc regioJoc) {
        this.regionsJoc.remove(regioJoc);
    }

    public List<RegioJoc> getRegionsJoc() {
        return this.regionsJoc;
    }

    public RegioJoc getRegio(int x, int y) {
        for (RegioJoc r : regionsJoc) {
            for (int j = 0; j < r.getTamany(); ++j) {
                if (r.getCasella(j).getPosX() == x && r.getCasella(j).getPosY() == y) {
                    return r;
                }
            }
        }
        return null;
    }


    // Funcions principals bactracking
    public boolean esFilaValida(int fila, int num) {
        for (int colum = 0; colum < getGrau(); ++colum) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }

    public boolean esColumValida(int colum, int num) {
        for (int fila = 0; fila < getGrau(); ++fila) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }
    private void backtracking(TaulerJoc TJ, int i, int j) {
        //Cas base , te solucio
        if (i == TJ.getGrau()) {
            trobat = true;
        }
        // Crida Recursiva
        else if (!TJ.esModificable(i, j)) {
            // Si la casella no es modificable, pasa a la seguent
            if (j + 1 == TJ.getGrau()) {
                backtracking(TJ, i + 1, 0);
            } else {
                backtracking(TJ, i, j + 1);
            }
        } else {
            for (int valor = 1; valor <= TJ.getGrau() && !trobat; ++valor) {
                if (TJ.esFilaValida(i, valor) && TJ.esColumValida(j, valor)) {
                    TJ.setValor(i, j, valor);
                    Regio r = TJ.getRegio(i, j);
                    if ((!r.esCompleta() || r.esCompleta() && r.esValida())) {
                        if (j + 1 == TJ.getGrau()) {
                            backtracking(TJ, i + 1, 0);
                        } else {
                            backtracking(TJ, i, j + 1);
                        }
                    }
                }
            }
            if (!trobat) {
                TJ.borrarValor(i, j);
            }
        }
    }

    public void solucionarKenken(TaulerJoc T) {
        trobat = false;
        backtracking(T, 0, 0);
        //
    }
}