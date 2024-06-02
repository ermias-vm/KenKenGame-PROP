package main.presentacio.Partida;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static main.presentacio.Utils.COLOR_DE_FONS;

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
     *
     * @param mida              Mida del tauler
     * @param mapaAdjacents     Matriu de booleans que indica si les caselles de dalt, esquerra, baix i dreta són de la mateixa regió.
     * @param operacionsPartida
     */
    public ComponentTauler(int mida, ArrayList<Boolean>[][] mapaAdjacents, int[][] valorsTauler, ArrayList<String> operacionsPartida) {
        this.setLayout( new GridLayout(mida, mida));
        tauler = new ComponentCasella[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                tauler[i][j] = new ComponentCasella(mida, i, j, valorsTauler[i][j]);
                this.add(tauler[i][j]);
            }
        }
        for (String operacio: operacionsPartida) {
            String[] parts = operacio.split(" ");
            int i = Integer.parseInt(parts[0]);
            int j = Integer.parseInt(parts[1]);
            String operacioString;
            if (Objects.equals(parts[2], "null")) operacioString = parts[3];
            else operacioString = parts[3]  + parts[2];
            tauler[i][j].setOperacio(operacioString);
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
                Boolean topRegio = mapaAdjacents[i][j].get(0);
                Boolean leftRegio = mapaAdjacents[i][j].get(1);
                Boolean bottomRegio = mapaAdjacents[i][j].get(2);
                Boolean rightRegio = mapaAdjacents[i][j].get(3);
                Boolean borderTop = i == 0;
                Boolean borderLeft = j == 0;
                Boolean borderBottom = i == mapaAdjacents.length - 1;
                Boolean borderRight = j == mapaAdjacents[0].length - 1;
                int top = 0;
                if (!topRegio) top = 3;
                if (borderTop) top = 6;
                int left = 0;
                if (!leftRegio) left = 3;
                if (borderLeft) left = 6;
                int bottom = 0;
                if (!bottomRegio) bottom = 3;
                if (borderBottom) bottom = 6;
                int right = 0;
                if (!rightRegio) right = 3;
                if (borderRight) right = 6;
                Color colorRegio = new Color(11, 3, 138, 175);
                Border regions = new MatteBorder(top, left, bottom, right, colorRegio);
                Color colorCasella = new Color(78, 152, 193, 175);
                Border caselles = new MatteBorder(1, 1, 1, 1, colorCasella);
                Border border = BorderFactory.createCompoundBorder(regions,caselles);
                tauler[i][j].setBorder(border);
            }
        }
    }

    /**
     * Afegeix un observador a una casella del tauler.
     * @param observer Observador de la casella
     */
    public void addObserver(ObservadorCasella observer) {
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j].addObserver(observer);
            }
        }
    }

    /**
     * Actualitza els valors de les caselles del tauler.
     * @param tauler Matriu de valors del tauler
     */
    public void actualitzaValor(String valor, int fila, int columna) {
        this.tauler[fila][columna].setValor(valor);
        this.revalidate();
        this.repaint();
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

    public void setFocus(int fila, int columna) {
        tauler[fila][columna].requestFocus();
    }

    public void setAllCorrectes() {
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j].setIncorrecte(false);
            }
        }
    }
}
