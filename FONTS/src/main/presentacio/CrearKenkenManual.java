package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class CrearKenkenManual {
    private JLabel grauLabel;
    private JPanel configPanel;
    private JLabel logoCreateLabel;
    private JPanel panelComplet;
    private JPanel panelEsq;
    private JPanel grauPanel;
    private JButton confirmarButton;
    private JButton sortirButton;
    private JButton aceptarButton;
    private JPanel TaulerPanel;
    private JComboBox grauComboBox;
    private ComponentTauler tauler;
    public CrearKenkenManual() {
        createUIComponents();
        System.out.println("Entrant a la pantalla de crear kenken");

        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de crear kenken");
                //FALTA  fer la comfirmacio de sortida
                //
                //
                //
                CtrlPresentacio.getInstance().showMenuPrincipal();
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt((String) Objects.requireNonNull(grauComboBox.getSelectedItem()));
                System.out.println("Creant tauler de mida " + size);
                createKenkenBoard(size);
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    private void createKenkenBoard(int size) {
        TaulerPanel = new JPanel();
        createUIComponents();
        //TaulerPanel.removeAll();

        ArrayList<Boolean>[][] mapaAdjacents = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapaAdjacents[i][j] = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    mapaAdjacents[i][j].add(false);
                }
            }
        }

        tauler = new ComponentTauler(size, mapaAdjacents);
        ObservadorDeCasella observador = new ObservadorDeCasella();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tauler.addObserver(observador, i, j);
            }
        }
        TaulerPanel.add(tauler);
        TaulerPanel.revalidate();
        TaulerPanel.repaint();
    }

    private void createUIComponents() {
        logoCreateLabel = new JLabel(Utils.carregarImatge("resources/imatges/logoKenkenCreador.png", 320, 320));
    }
}
