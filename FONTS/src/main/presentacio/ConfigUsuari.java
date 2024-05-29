package main.presentacio;

import javax.swing.*;
import java.awt.event.*;

/**
 * La classe ConfigUsuari és una part de la interfície d'usuari que permet a l'usuari canviar la seva contrasenya.
 * Conté diversos components d'interfície d'usuari, com ara camps de text per a la contrasenya actual, la nova contrasenya
 * i la confirmació de la nova contrasenya, així com botons per a guardar els canvis, tancar la sessió i sortir de la pantalla de configuració.
 * @Autor Ermias Valls Mayor
 */
public class ConfigUsuari {

    /**
     * Panell principal de la interfície d'usuari.
     */
    private JPanel panelComplet;

    /**
     * Etiqueta per al logo.
     */
    private JLabel labelLogo;

    /**
     * Panell dret de la interfície d'usuari.
     */
    private JPanel panelDreta;

    /**
     * Etiqueta per al text "Canviar Contrasenya".
     */
    private JLabel canviarContrasenyaLabel;

    /**
     * Camp de text per a la nova contrasenya.
     */
    private JPasswordField passwordFieldContr;

    /**
     * Camp de text per a la confirmació de la nova contrasenya.
     */
    private JPasswordField passwordFieldConfContr;

    /**
     * Etiqueta per a mostrar missatges d'error.
     */
    private JLabel errorLabel;

    /**
     * Botó per a sortir de la pantalla de configuració.
     */
    private JButton sortirButton;

    /**
     * Camp de text per a la contrasenya actual.
     */
    private JPasswordField passwordFieldActual;

    /**
     * Botó per a guardar els canvis de contrasenya.
     */
    private JButton guardarButton;

    /**
     * Botó per a tancar la sessió de l'usuari.
     */
    private JButton tancarSessioButton;

    /**
     * Constructor de la classe ConfigUsuari.
     * Inicialitza els components de la interfície d'usuari, configura els camps de contrasenya per a mostrar
     * el text per defecte i configura els escoltadors d'esdeveniments per als camps de contrasenya i els botons.
     */
    public ConfigUsuari() {
        System.out.println("Entrant a la pantalla de configuracio d'usuari");
        createUIComponents();
        passwordFieldActual.setEchoChar((char) 0);
        passwordFieldContr.setEchoChar((char) 0);
        passwordFieldConfContr.setEchoChar((char) 0);
        setupPaswordFieldListeners();
        setupButtonListeners();
    }

    /**
     * Configura els escoltadors d'esdeveniments per als camps de contrasenya.
     * Quan un camp de contrasenya guanya el focus, es canvia el caràcter d'eco a '•' i es buida el camp si conté el text per defecte.
     * Quan un camp de contrasenya perd el focus, es comprova si el camp està buit o conté el text per defecte, en aquest cas, es torna a posar el text per defecte i es canvia el caràcter d'eco a 0.
     * A més, si l'usuari prem la tecla Enter en el camp de confirmació de la nova contrasenya, es fa clic automàticament en el botó de guardar.
     */
    public void setupPaswordFieldListeners() {

        passwordFieldActual.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordFieldActual.setEchoChar('•');

                if (new String(passwordFieldActual.getPassword()).equals("Contrasenya actual")) {
                    passwordFieldActual.setText("");
                }

            }
        });

        passwordFieldActual.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String pswd = new String(passwordFieldActual.getPassword());
                if (pswd.isEmpty() || pswd.equals("Contrasenya actual")) {
                    passwordFieldActual.setText("Contrasenya actual");
                    passwordFieldActual.setEchoChar((char) 0);
                }
            }
        });

        passwordFieldContr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordFieldContr.setEchoChar('•');
                if (new String(passwordFieldContr.getPassword()).equals("Nova contrasenya")) {
                    passwordFieldContr.setText("");
                }
            }
        });

        passwordFieldContr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String pswd = new String(passwordFieldContr.getPassword());
                if (pswd.isEmpty() || pswd.equals("Nova contrasenya")) {
                    passwordFieldContr.setText("Nova contrasenya");
                    passwordFieldContr.setEchoChar((char) 0);
                }
            }
        });

        passwordFieldConfContr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordFieldConfContr.setEchoChar('•');
                if (new String(passwordFieldConfContr.getPassword()).equals("Comfirmar nova contrasenya")) {
                    passwordFieldConfContr.setText("");
                }
            }
        });

        passwordFieldConfContr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String pswd = new String(passwordFieldConfContr.getPassword());
                if (pswd.isEmpty() || pswd.equals("Comfirmar nova contrasenya")) {
                    passwordFieldConfContr.setText("Comfirmar nova contrasenya");
                    passwordFieldConfContr.setEchoChar((char) 0);
                }
            }
        });

        passwordFieldConfContr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guardarButton.doClick();
                }
            }
        });
    }

    /**
     * Configura els escoltadors d'esdeveniments per als botons.
     * Quan l'usuari fa clic en el botó de sortir, es mostra el menú principal.
     * Quan l'usuari fa clic en el botó de guardar, es comproven les contrasenyes introduïdes per l'usuari
     * i si són vàlides, es canvia la contrasenya de l'usuari.
     * Quan l'usuari fa clic en el botó de tancar sessió, es tanca la sessió i es mostra la pantalla d'inici de sessió.
     */
    private void setupButtonListeners() {

        sortirButton.addActionListener(e -> {
            System.out.println("Sortint de la configuracio de l'usuari");
            CtrlPresentacio.getInstance().showMenuPrincipal();

        });

        guardarButton.addActionListener(e -> {
            String contraActual = new String(passwordFieldActual.getPassword());
            String contraNova = new String(passwordFieldContr.getPassword());
            String contraNovaConf = new String(passwordFieldConfContr.getPassword());
            if (!errorLabel.getText().isEmpty()) errorLabel.setText("");

            if (contraActual.isEmpty() || contraActual.equals("Contrasenya actual") || contraNova.isEmpty() || contraNova.equals("Nova contrasenya") || contraNovaConf.isEmpty() || contraNovaConf.equals("Comfirmar nova contrasenya")) {
                System.out.println("Contrasenya actual i/o nova contrasenya no valida");
                errorLabel.setText("<html><div style='text-align: center;'>Contrasenya actual i/o <br>nova contrasenya no valida.<br>Si us plau comproveu les dades.</div></html>");
            } else if (!contraNova.equals(contraNovaConf)) {
                System.out.println("Contrasenyes no coincideixen");
                errorLabel.setText("<html><div style='text-align: center;'>Contrasenyes no coincideixen.<br>Si us plau comproveu les dades.</div></html>");
            } else {
                try {
                    int dialogResult = JOptionPane.showConfirmDialog(guardarButton, "<html><div style='text-align: center;'>Estas segur que vols canviar" +
                            "<br>la contrasenya actual?</div></html>", "Confirmació", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        CtrlPresentacio.getInstance().canviarContrasenya(contraActual, contraNova);
                        JOptionPane.showMessageDialog(guardarButton, "Contrasenya canviada correctament", "Informacó", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Contrasenya canviada correctament");
                    } else {
                        System.out.println("Canvi de contrasenya cancelat");
                    }
                } catch (Exception ex) {
                    System.out.println("Contrasenya actual incorrecta");
                    errorLabel.setText("<html><div style='text-align: center;'>Contrasenya actual incorrecta." +
                            "<br>Si us plau comproveu la vostra contrasenya.</div></html>");
                }
            }
        });

        tancarSessioButton.addActionListener(e -> {
            System.out.println("Tancant sessio");
            CtrlPresentacio.getInstance().tancarSessio();
            CtrlPresentacio.getInstance().showIniciarSessio();
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
     * Crea els components personalitzats de la interfície d'usuari.
     * Carrega la imatge del logo.
     */
    private void createUIComponents() {
        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));
    }
}
