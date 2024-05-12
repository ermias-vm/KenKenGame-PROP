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
        createUIComponents();

        configuracioUsuariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Entrant configuracio usuari");
                CtrlPresentacio.getInstance().showConfigUsuari();

            }
        });
    }

    private void createUIComponents() {

        try {
            ImageIcon imageIcon = new ImageIcon("resources/imatges/logoKenken.jpeg");
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
