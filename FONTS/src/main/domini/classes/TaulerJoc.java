package main.domini.classes;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe TaulerJoc que estén la classe Tauler.
 * Representa un tauler de joc amb regions i un estat de si s'ha trobat una solució.
 *
 * @author Ermias Valls Mayor
 */
public class TaulerJoc extends Tauler {

    private boolean trobat;
    private List<RegioJoc> regionsJoc;

    /**
     * Constructor de TaulerJoc.
     * @param idTauler Identificador del tauler
     * @param grau Grau del tauler
     */
    public TaulerJoc(int idTauler, int grau) {
        super(idTauler, grau);
        this.regionsJoc = new ArrayList<>();
        this.trobat = false;
    }
    /**
     * Comprova si el tauler té una solució.
     * @return true si té solució, false en cas contrari
     */
    public boolean teSolucion() {
        return trobat;
    }

    /**
     * Afegeix una regió de joc a la llista de regionsJoc.
     * @param regioJoc La regió de joc a afegir
     */
    public void afegirRegioJoc(RegioJoc regioJoc) {
        this.regionsJoc.add(regioJoc);
    }

    /**
     * Esborra una regió de joc de la llista de regionsJoc.
     * @param regioJoc La regió de joc a esborrar
     */
    public void borrarRegioJoc(RegioJoc regioJoc) {
        this.regionsJoc.remove(regioJoc);
    }

    /**
     * Retorna la llista de regions de joc.
     * @return Llista de regions de joc
     */
    public List<RegioJoc> getRegionsJoc() {
        return this.regionsJoc;
    }

    /**
     * Retorna la regió de joc que conté la casella amb les coordenades x, y.
     * @param x Coordenada x de la casella
     * @param y Coordenada y de la casella
     * @return Regió de joc que conté la casella, null si no es troba
     */
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

    /**
     * Comprova si un número és vàlid per a una fila donada.
     * @param fila Fila a comprovar
     * @param num Número a comprovar
     * @return true si el número és vàlid, false en cas contrari
     */
    public boolean esFilaValida(int fila, int num) {
        for (int colum = 1; colum <= getGrau(); ++colum) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }

    /**
     * Comprova si un número és vàlid per a una columna donada.
     * @param colum Columna a comprovar
     * @param num Número a comprovar
     * @return true si el número és vàlid, false en cas contrari
     */
    public boolean esColumValida(int colum, int num) {
        for (int fila = 1; fila <= getGrau(); ++fila) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }

    /**
     * Mètode recursiu per a la resolució del tauler de joc.
     * @param TJ Tauler de joc a resoldre
     * @param i Índex de la fila
     * @param j Índex de la columna
     * @throws Exception Si es produeix un error durant la resolució
     */
    private void backtracking(TaulerJoc TJ, int i, int j) throws Exception {
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
                    RegioJoc r = TJ.getRegio(i, j);
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

    /**
     * Resol el tauler de joc Kenken.
     * @param T Tauler de joc a resoldre
     * @throws Exception Si es produeix un error durant la resolució
     */
    public void solucionarKenken(TaulerJoc T) throws Exception {
        backtracking(T, 0, 0);
    }
}