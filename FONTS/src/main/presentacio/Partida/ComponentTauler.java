package main.presentacio.Partida;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class ComponentTauler extends JPanel {
    private ComponentCasella[][] tauler;
    public ComponentTauler(int mida,ArrayList<Boolean>[][] mapaAdjacents) {
        super(new GridLayout(mida, mida));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border);
        tauler = new ComponentCasella[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                tauler[i][j] = new ComponentCasella(mida, i, j);
                this.add(tauler[i][j]);
            }
        }
        creaBordersRegions(mapaAdjacents);
    }

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
    public void addObserver(ObservadorCasella observer , int i, int j) {
        tauler[i][j].addObserver(observer);
    }
}
