package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField grauText;

    public CrearKenkenManual() {
        System.out.println("Entrant a la pantalla de crear kenken");
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de crear kenken");
                CtrlPresentacio.getInstance().showMenuPrincipal();
            }
        });
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    private void createUIComponents() {
        try {
            ImageIcon imageIcon = new ImageIcon("resources/imatges/logoKenkenCreador.png");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
            logoCreateLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Error al  carregar la imatge: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
