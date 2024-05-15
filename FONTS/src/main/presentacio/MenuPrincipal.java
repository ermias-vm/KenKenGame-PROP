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
        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.jpeg", 800, 800));
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
    }
}
