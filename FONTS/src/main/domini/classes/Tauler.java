package main.domini.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa un tauler del joc.
 * @author David Giribet
 */
public class Tauler {
    private int idTauler; // Identificador del tauler
    private int grau; // Grau del tauler
    private List<Casella> caselles; // Llista de caselles del tauler

    /**
     * Constructor de la classe Tauler.
     *
     * @param idTauler Identificador del tauler.
     * @param grau     Grau del tauler.
     */
    public Tauler(int idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.caselles = new ArrayList<>();
    }

    /**
     * Constructor de la classe Tauler amb llista de caselles.
     *
     * @param idTauler  Identificador del tauler.
     * @param grau      Grau del tauler.
     * @param caselles  Llista de caselles del tauler.
     */
    public Tauler(int idTauler, int grau, ArrayList<Casella> caselles) {
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

    /**
     * Obté la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return La casella a la posició (x, y) o null si no existeix.
     */
    public Casella getCasella(int x, int y) {
        for (Casella casella : this.caselles) {
            if (casella.getPosX() == x && casella.getPosY() == y) {
                return casella;
            }
        }
        return null;
    }

    /**
     * Obté la llista de caselles del tauler.
     *
     * @return Llista de caselles del tauler.
     */
    public List<Casella> getCaselles() {
        return caselles;
    }

    /**
     * Afegeix una casella al tauler.
     *
     * @param casella Casella a afegir.
     */
    public void afegirCasella(Casella casella) {
        caselles.add(casella);
    }

    /**
     * Elimina una casella del tauler.
     *
     * @param casella Casella a eliminar.
     */
    public void borrarCasella(Casella casella) {
        caselles.remove(casella);
    }

    /**
     * Obté el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return Valor de la casella a la posició (x, y).
     */
    public int getValor(int x, int y) {
        return getCasella(x, y).getValor();
    }

    /**
     * Estableix el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x   Coordenada x de la casella.
     * @param y   Coordenada y de la casella.
     * @param num Nou valor per la casella.
     */
    public void setValor(int x, int y, int num) {
        getCasella(x, y).setValor(num);
    }

    /**
     * Borra el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     */
    public void borrarValor(int x, int y) {
        getCasella(x, y).borrarValor();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler és modificable.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella és modificable, false en cas contrari.
     */
    public boolean esModificable(int x, int y) {
        return getCasella(x, y).esModificable();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler està buida.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella està buida, false en cas contrari.
     */
    public boolean esBuida(int x, int y) {
        return getCasella(x, y).esBuida();
    }
}
