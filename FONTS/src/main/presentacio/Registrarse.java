package main.presentacio;

import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe Registrarse de la capa presentació, gestiona el registre d'usuaris.
 * Es una vista que permet a l'usuari registrar-se al sistema.
 * @author Ermias Valls Mayor
 */
public class Registrarse {
    /**
     * Panell principal de la interfície d'usuari.
     */
    private JPanel panelComplet;
    /**
     * Panell secundari de la interfície d'usuari.
     */
    private JPanel panel2;
    /**
     * Etiqueta per al logo.
     */
    private JLabel labelLogo;
    /**
     * Camp de text per a l'usuari.
     */
    private JTextField textFieldUsuari;
    /**
     * Etiqueta per a l'usuari.
     */
    private JLabel labelUsuari;
    /**
     * Etiqueta per a la contrasenya.
     */
    private JLabel labelContrasenya;
    /**
     * Botó per a registrar-se.
     */
    private JButton buttonRegistrar;
    /**
     * Botó per a iniciar sessió.
     */
    private JButton buttonIniciarSessio;
    /**
     * Etiqueta per a la confirmació de la contrasenya.
     */
    private JLabel lableComfContra;
    /**
     * Camp de text per a la contrasenya.
     */
    private JPasswordField passwordFieldContr;
    /**
     * Camp de text per a la confirmació de la contrasenya.
     */
    private JPasswordField passwordFieldConfContr;

    /**
     * Etiqueta separadora, dels butons de iniciar sessio amb el buto de registrar-se.
     */
    private JLabel labelSeparador;

    /**
     * Etiqueta per a errors.
     */
    private JLabel errorLabel;
    private JPanel panelniciarSessio;
    private JPanel panelConfigRegistrar;

    /**
     * Constructor de la classe Registrarse.
     * Inicialitza els components de la interfície d'usuari i configura els listeners dels botons i del camp de text.
     */
    public Registrarse() {
        System.out.println("Entrant a la pantalla de registrar-se");
        setupButtonRegistrarListener();
        setupButtonIniciarSessioListener();
        setupPasswordFieldConfContrListener();
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó de registre. Quan l'usuari fa clic en aquest botó,
     * el mètode recupera el nom d'usuari i la contrasenya introduïts per l'usuari. Si algun d'aquests camps
     * està buit, es mostra un missatge d'error. Si la contrasenya i la confirmació de la contrasenya no
     * coincideixen, també es mostra un missatge d'error. Si tot està bé, es crida al mètode
     * `registrarUsuari` de la classe `CtrlPresentacio` per registrar l'usuari. Si l'usuari es registra
     * correctament, es mostra un missatge de confirmació i es redirigeix a l'usuari al menú principal.
     */
    private void setupButtonRegistrarListener() {
        buttonRegistrar.addActionListener(e -> {
            String usuari = textFieldUsuari.getText();
            String contrasenya = new String(passwordFieldContr.getPassword());
            String confContrasenya = new String(passwordFieldConfContr.getPassword());
            if (usuari.isEmpty() || contrasenya.isEmpty() || confContrasenya.isEmpty()) {
                System.out.println("Usuari i/o contrasenya no valida");
                errorLabel.setText("<html><div style='text-align: center;'>Usuari i/o contrasenya no valida." +
                        "<br>Si us plau comproveu les dades.</div></html>");
            } else if (!contrasenya.equals(confContrasenya)) {
                System.out.println("Contrasenya no coincideix");
                errorLabel.setText("<html><div style='text-align: center;'>Contrasenyes no coincideixen." +
                        "<br>Si us plau comproveu les dades.</div></html>");
            } else {
                try {
                    CtrlPresentacio.getInstance().registrarUsuari(usuari, contrasenya);
                    JOptionPane.showMessageDialog(buttonRegistrar, "Usuari registrat correctament", "Informació", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Usuari registrat correctament");
                    System.out.println("Sortint de registrar usuari ");
                    CtrlPresentacio.getInstance().showMenuPrincipal();

                } catch (ExcepcioUsuariJaExisteix ex) {
                    System.out.println("Usuari ja existeix");
                    errorLabel.setText("<html><div style='text-align: center;'>Usuari ja existeix." +
                            "<br>Si us plau comproveu el vostre usuari.</div></html>");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó d'inici de sessió. Quan l'usuari fa clic en aquest
     * botó, es mostrar la vista d'inici de sessió.
     */
    private void setupButtonIniciarSessioListener() {
        buttonIniciarSessio.addActionListener(e -> {
            System.out.println("Sortint de registrar-se");
            CtrlPresentacio.getInstance().showIniciarSessio();
        });
    }

    /**
     * Configura un escoltador d'esdeveniments per al camp de confirmació de la contrasenya. Quan l'usuari
     * prem la tecla Enter en aquest camp, es fa clic automàticament en el botó de registre, desencadenant
     * l'esdeveniment de registre.
     */
    private void setupPasswordFieldConfContrListener() {
        passwordFieldConfContr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonRegistrar.doClick();
                }
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
     * Inclou la càrrega d'imatges per a l'etiqueta del logo i l'etiqueta separadora.
     *
     */
    private void createUIComponents() {
        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));
        labelSeparador = new JLabel(Utils.carregarImatge("resources/imatges/separador_amb_or.png", 300, 30));

        buttonRegistrar = Utils.crearBotoPersonalitzat(310, 35,"Crear compte", Color.WHITE, Utils.COLOR_BOTO_VERD,true);
        buttonIniciarSessio = Utils.crearBotoPersonalitzat(310, 35,"Iniciar Sessió", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
    }

}