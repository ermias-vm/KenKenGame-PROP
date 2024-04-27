package main.domini.classes;

import main.domini.excepcions.ExcepcioCasellaNoExisteix;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa un tauler del joc.
 * @author David Giribet
 */
public class Tauler {
    private int idTauler; // Identificador del tauler
    private int grau; // Grau del tauler
    private Casella[][] caselles; // Llista de caselles del tauler

    /**
     * Constructor de la classe Tauler.
     *
     * @param idTauler Identificador del tauler.
     * @param grau     Grau del tauler.
     */
    public Tauler(int idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.caselles = new Casella[grau][grau];
    }

    /**
     * Constructor de la classe Tauler amb llista de caselles.
     *
     * @param idTauler  Identificador del tauler.
     * @param grau      Grau del tauler.
     * @param caselles  Llista de caselles del tauler.
     */
    public Tauler(int idTauler, int grau, Casella [][] caselles) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.caselles = caselles;
    }

    /**
     * Obté l'identificador del tauler.
     *
     * @return Identificador del tauler.
     */
    public int getIdTauler() {
        return idTauler;
    }

    /**
     * Obté el grau del tauler.
     *
     * @return Grau del tauler.
     */
    public int getGrau() {
        return grau;
    }


    public Casella getCasella(int x, int y) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > grau || y < 1 || y > grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        return caselles[x-1][y-1];
    }

    /**
     * Obté la llista de caselles del tauler.
     *
     * @return Llista de caselles del tauler.
     */
    public Casella [][] getCaselles() {
        return caselles;
    }

    /**
     * Afegeix una casella al tauler.
     *
     * @param casella Casella a afegir.
     */

    public void afegirCasella(int x, int y, Casella casella) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > grau || y < 1 || y > grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        caselles[x-1][y-1] = casella;
    }


    public void borrarCasella(int x, int y) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > grau || y < 1 || y > grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        caselles[x-1][y-1] = null;
    }

    /**
     * Obté el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return Valor de la casella a la posició (x, y).
     */
    public int getValor(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).getValor();
    }

    /**
     * Estableix el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x   Coordenada x de la casella.
     * @param y   Coordenada y de la casella.
     * @param num Nou valor per la casella.
     */
    public void setValor(int x, int y, int num) throws ExcepcioCasellaNoExisteix {
        getCasella(x, y).setValor(num);
    }

    /**
     * Borra el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     */
    public void borrarValor(int x, int y) throws ExcepcioCasellaNoExisteix {
        getCasella(x, y).borrarValor();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler és modificable.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella és modificable, false en cas contrari.
     */
    public boolean esModificable(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).esModificable();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler està buida.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella està buida, false en cas contrari.
     */
    public boolean esBuida(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).esBuida();
    }
}
