package main.presentacio;

import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IniciarSessio {

    private JPanel panelComplet;
    private JPanel panelIniciar;
    private JLabel labelLogo;
    private JTextField textFieldUsuari;
    private JLabel labelUsuari;
    private JLabel labelContrasenya;
    private JPasswordField passwordFieldContr;
    private JButton buttonIniSessio;
    private JButton buttonCrearCompte;
    private JLabel labelSerparado;
    private JLabel errorLabel;

    //private CtrlPresentacio CPre = CtrlPresentacio.getInstance();
    private CtrlUsuariUI CUui = CtrlUsuariUI.getInstance();

    public IniciarSessio() {
        createUIComponents();
        buttonIniSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuari = textFieldUsuari.getText();
                String contrasenya = new String(passwordFieldContr.getPassword());

                if (usuari.isEmpty() || contrasenya.isEmpty()) {
                    System.out.println("Usuari i/o contrasenya no valida");
                    errorLabel.setText("<html>Usuari i/o contrasenya no valida.<br>Si us plau comproveu les dades.</html>");
                } else {
                    try {
                        CUui.iniciarSessio(usuari, contrasenya);
                        System.out.println("Sessio iniciada correctament: es mostrara menu principal....");
                        CtrlPresentacio.getInstance().showMenuPrincipal();

                    } catch (ExcepcioUsuariNoExisteix ex) {
                        System.out.println("Usuari no existeix");
                        errorLabel.setText("<html>Usuari no existeix.<br>Si us plau comproveu el vostre usuari.</html>");
                    } catch (ExcepcioContrasenyaIncorrecta ex) {
                        System.out.println("Contrasenya  incorrecta");
                        errorLabel.setText("<html>Contrasenya incorrecta.<br>Si us plau comproveu la vostra contrsenya.</html>");
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        passwordFieldContr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonIniSessio.doClick();
                }
            }
        });

        buttonCrearCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showRegistrarse();
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