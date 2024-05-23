package main.presentacio.Partida;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * {@code ComponentTauler} és un JPanel que conté un tauler de caselles {@link ComponentCasella}.
 */
public class ComponentTauler extends JPanel {
    /**
     * {@code tauler} és una matriu de {@link ComponentCasella} que formen el tauler.
     */
    private ComponentCasella[][] tauler;

    /**
     * Creadora de la classe ComponentTauler.
     * @param mida Mida del tauler
     * @param mapaAdjacents Matriu de booleans que indica si les caselles de dalt, esquerra, baix i dreta són de la mateixa regió.
     */
    public ComponentTauler(int mida,ArrayList<Boolean>[][] mapaAdjacents, int[][] valorsTauler) {
        super(new GridLayout(mida, mida));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border);
        tauler = new ComponentCasella[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                tauler[i][j] = new ComponentCasella(mida, i, j, valorsTauler[i][j]);
                this.add(tauler[i][j]);
            }
        }
        creaBordersRegions(mapaAdjacents);
    }

    /**
     * Crea els borders de les regions del tauler d'acord amb el mapa d'adjacencies.
     * @param mapaAdjacents Matriu de booleans que indica si les caselles de dalt, esquerra, baix i dreta són de la mateixa regió.
     */
    private void creaBordersRegions(ArrayList<Boolean>[][] mapaAdjacents) {
        for (int i = 0; i < mapaAdjacents.length; i++) {
            for (int j = 0; j < mapaAdjacents[0].length; j++) {
                Boolean top = mapaAdjacents[i][j].get(0);
                Boolean left = mapaAdjacents[i][j].get(1);
                Boolean bottom = mapaAdjacents[i][j].get(2);
                Boolean right = mapaAdjacents[i][j].get(3);
                Border border = new MatteBorder(top ? 0 : 5, left ? 0 : 5, bottom ? 0 : 5, right ? 0 : 5, Color.BLACK);
                tauler[i][j].setBorder(border);
            }
        }
    }

    /**
     * Afegeix un observador a una casella del tauler.
     * @param observer Observador de la casella
     * @param i Fila de la casella
     * @param j Columna de la casella
     */
    public void addObserver(ObservadorCasella observer , int i, int j) {
        tauler[i][j].addObserver(observer);
    }

    /**
     * Actualitza els valors de les caselles del tauler.
     * @param tauler Matriu de valors del tauler
     */
    public void actualitzaValors(int[][] tauler) {
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                this.tauler[i][j].setValor(String.valueOf(tauler[i][j]));
            }
        }
    }

    /**
     * Actualitza les posicions incorrectes del tauler.
     * @param posicionsIncorrectes Llista de posicions incorrectes
     */

    public void actualitzaPosicionsIncorrectes(ArrayList<int[]> posicionsIncorrectes) {
        for (int[] pos : posicionsIncorrectes) {
            tauler[pos[0]][pos[1]].setIncorrecte(true);
        }
    }
}
