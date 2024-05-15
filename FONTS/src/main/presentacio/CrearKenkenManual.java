package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                int size = (int) grauComboBox.getSelectedItem();
                createKenkenBoard(size);
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    private void createKenkenBoard(int size) {
        TaulerPanel.removeAll();
        ArrayList<Boolean>[][] mapaAdjacents = new ArrayList[size][size]; // You need to provide the adjacency map
        tauler = new ComponentTauler(size, mapaAdjacents);
        ObservadorDeCasella observador = new ObservadorDeCasella();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ComponentCasella cell = new ComponentCasella(size, i, j);
                cell.addObserver(observador);
                TaulerPanel.add(cell);
            }
        }
        TaulerPanel.revalidate();
        TaulerPanel.repaint();
    }

    private void createUIComponents() {
        try {
            ImageIcon imageIcon = new ImageIcon("resources/imatges/logoKenkenCreador.png");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(320, 320, java.awt.Image.SCALE_SMOOTH);
            logoCreateLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Error al  carregar la imatge: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
