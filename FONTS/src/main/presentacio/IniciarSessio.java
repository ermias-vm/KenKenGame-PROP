package main.presentacio;

import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * La classe IniciarSessio de la capa presentació, gestiona l'inici de sessió dels usuaris.
 * Es una vista que permet que iniciar sessió al usuari.
 * Conté diversos components d'interfície d'usuari, com ara camps de text per a l'usuari i la contrasenya.
 * @author Ermias Valls Mayor
 */
public class IniciarSessio {

    /**
     * Panell principal de la interfície d'usuari.
     */
    private JPanel panelComplet;

    /**
     * Panell per a l'inici de sessió.
     */
    private JPanel panelIniciar;

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
     * Camp de text per a la contrasenya.
     */
    private JPasswordField passwordFieldContr;

    /**
     * Botó per a iniciar sessió.
     */
    private JButton buttonIniSessio;

    /**
     * Botó per a crear un compte.
     */
    private JButton buttonCrearCompte;

    /**
     * Etiqueta per al separador.
     */
    private JLabel labelSeparador;

    /**
     * Etiqueta per a mostrar missatges d'error.
     */
    private JLabel errorLabel;

    /**
     * Constructor de la classe IniciarSessio.
     * Inicialitza els escoltadors dels botons de la interfície d'usuari.
     */
    public IniciarSessio() {
        System.out.println("Entrant a la pantalla d'iniciar sessio");
        setupButtonIniciarSessioListener();
        setupButtonCrearCompteListener();
        setupPasswordFieldContrListener();
    }
    /**
     * Configura l'escoltador d'esdeveniments per al botó d'iniciar sessió.
     * Quan l'usuari fa clic en aquest botó, es recullen les dades introduïdes en els camps de text de l'usuari i la contrasenya.
     * Si algun d'aquests camps està buit, es mostra un missatge d'error.
     * Si tots dos camps estan plens, es tracta d'iniciar sessió amb les dades introduïdes.
     * Si l'inici de sessió és exitós, es mostra el menú principal.
     * Si l'usuari no existeix o la contrasenya és incorrecta, es mostra un missatge d'error.
     */
    public void setupButtonIniciarSessioListener() {
        buttonIniSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuari = textFieldUsuari.getText();
                String contrasenya = new String(passwordFieldContr.getPassword());

                if (usuari.isEmpty() || contrasenya.isEmpty()) {
                    System.out.println("Usuari i/o contrasenya no valida");
                    errorLabel.setText("<html><div style='text-align: center;'>Usuari i/o contrasenya no valida." +
                            "<br>Si us plau comproveu les dades.</div></html>");
                } else {
                    try {
                        CtrlPresentacio.getInstance().iniciarSessio(usuari, contrasenya);
                        System.out.println("Sessio iniciada correctament");
                        System.out.println("Sortint de iniciar sessio");
                        CtrlPresentacio.getInstance().showMenuPrincipal();
                    } catch (ExcepcioUsuariNoExisteix ex) {
                        System.out.println("Usuari no existeix");
                        errorLabel.setText("<html><div style='text-align: center;'>Usuari no existeix." +
                                "<br>Si us plau comproveu el vostre usuari.</div></html>");
                    } catch (ExcepcioContrasenyaIncorrecta ex) {
                        System.out.println("Contrasenya  incorrecta");
                        errorLabel.setText("<html><div style='text-align: center;'>Contrasenya incorrecta." +
                                "<br>Si us plau comproveu la vostra contrasenya.</div></html>");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al botó de crear compte.
     * Quan l'usuari fa clic en aquest botó, es mostra la pantalla de registre.
     */
    private void setupButtonCrearCompteListener() {
        buttonCrearCompte.addActionListener(e -> {
            System.out.println("Sortint de iniciar sessio");
            CtrlPresentacio.getInstance().showRegistrarse();
        });
    }

    /**
     * Configura l'escoltador d'esdeveniments per al camp de text de la contrasenya.
     * Quan l'usuari prem la tecla Enter en aquest camp de text, es fa clic en el botó d'iniciar sessió.
     */
    private void setupPasswordFieldContrListener() {
        passwordFieldContr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonIniSessio.doClick();
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
        labelSeparador = new JLabel(Utils.carregarImatge("resources/imatges/separador_amb_or.png", 200, 20));

    }

}