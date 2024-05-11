package main.presentacio;


import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registrarse {

    private JPanel panelComplet;
    private JPanel panel2;
    private JLabel labelLogo;
    private JTextField textFieldUsuari;
    private JLabel labelUsuari;
    private JLabel labelContrasenya;
    private JPasswordField passwordFieldContr;
    private JButton buttonRegistrar;
    private JButton buttonIniciarSessio;
    private JLabel lableComfContra;
    private JPasswordField passwordFieldConfContr;
    private JLabel labelSeparador;
    private JLabel errorLabel;

    //private CtrlPresentacio CPre = CtrlPresentacio.getInstance();
    private CtrlUsuariUI CUui = CtrlUsuariUI.getInstance();

    public Registrarse() {
        createUIComponents();
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuari = textFieldUsuari.getText();
                String contrasenya = new String(passwordFieldContr.getPassword());
                String confContrasenya = new String(passwordFieldConfContr.getPassword());
                if (usuari.isEmpty() || contrasenya.isEmpty() || confContrasenya.isEmpty()) {
                    System.out.println("Usuari i/o contrasenya no valida");
                    errorLabel.setText("<html>Usuari i/o contrasenya no valida.<br>Si us plau comproveu les dades.</html>");
                }else if(!contrasenya.equals(confContrasenya)){
                    System.out.println("Contrasenya no coincideix");
                    errorLabel.setText("<html>Contrasenyes no coincideixen.<br>Si us plau comproveu les dades.</html>");
                }
                else {
                    try {
                        CUui.registrarUsuari(usuari, contrasenya);
                        System.out.println("Usuari registrat correctament: es mostrara menu principal....");
                        CtrlPresentacio.getInstance().showMenuPrincipal();
                    } catch (ExcepcioUsuariJaExisteix ex) {
                        System.out.println("Usuari ja existeix");
                        errorLabel.setText("<html>Usuari ja existeix.<br>Si us plau comproveu el vostre usuari.</html>");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buttonIniciarSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.getInstance().showIniciarSessio();
            }
        });

        passwordFieldConfContr.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonRegistrar.doClick();
                }
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