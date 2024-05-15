package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {


    private JPanel panelComplet;
    private JLabel labelLogo;
    private JPanel panel2;
    private JButton configuracioUsuariButton;
    private JButton crearKenkenManualButton;
    private JButton jugarButton;
    private JButton rankingButton;
    private JButton crearKenkenAleatoriButton;

    public MenuPrincipal() {
        System.out.println("Entrant a la pantalla menu principal");
        createUIComponents();

        configuracioUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showConfigUsuari();

            }
        });
        crearKenkenManualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showCrearKenKen();
            }
        });
        crearKenkenAleatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showGenerarKenKen();
            }
        });
    }

    private void createUIComponents() {

        try {
            ImageIcon imageIcon = new ImageIcon("resources/imatges/fonsKenken.jpeg");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH);
            labelLogo = new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Error al  carregar la imatge: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }
}
