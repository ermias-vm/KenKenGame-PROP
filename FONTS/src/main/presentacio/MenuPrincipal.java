package main.presentacio;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe MenuPrincipal representa la pantalla principal de l'aplicació.
 * Conté els difrents botons per a accedir a les diferents funcionalitats de l'aplicació.
 *
 * @Autor Ermias Valls Mayor
 */
public class MenuPrincipal {

    /**
     * Panell principal de la interfície d'usuari.
     */
    private JPanel panelComplet;

    /**
     * Etiqueta per al logo de l'aplicació.
     */
    private JLabel labelLogo;

    /**
     * Segon panell de la interfície d'usuari.
     */
    private JPanel panel2;

    /**
     * Botó per accedir a la configuració de l'usuari.
     */
    private JButton configuracioUsuariButton;

    /**
     * Botó per a la creació manual d'un Kenken.
     */
    private JButton crearKenkenManualButton;

    /**
     * Botó per començar a jugar a un Kenken.
     */
    private JButton jugarButton;

    /**
     * Botó per veure el ranking de Kenkens.
     */
    private JButton rankingButton;

    /**
     * Botó per a la creació aleatòria d'un Kenken.
     */
    private JButton crearKenkenAleatoriButton;

    /**
     * Constructor de la classe MenuPrincipal.
     * Inicialitza els escoltadors dels botons de la interfície d'usuari.
     */
    public MenuPrincipal() {
        System.out.println("Entrant a la pantalla menu principal");
        setupButtonsListeners();
    }

    /**
     * Configura els escoltadors d'esdeveniments per als botons de la interfície d'usuari.
     * Cada botó té un escoltador que respon a l'esdeveniment d'acció (clic del ratolí).
     * Les accions dels botons són per mostrar les vistes de : configuració d'usuari, crear un KenKen manualment,
     * jugar a un KenKen, veure el ranking de KenKens i crear un KenKen aleatori.
     */
    private void setupButtonsListeners() {
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

        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().initJugar();

            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showRanking();

            }
        });
    }

    /**
     * Mètode que retorna el panell principal de la interfície d'usuari.
     * @return Panell principal de la interfície d'usuari.
     */
    public JPanel getDefaultPanel() {
        return panelComplet;
    }

    /**
     * Aquest mètode crea els components personalitzats de la interfície d'usuari.
     * Inclou la càrrega d'imatges per a l'etiqueta del logo.
     *
     */
    private void createUIComponents() {
        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));
    }

}
