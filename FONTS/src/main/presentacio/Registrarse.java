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

    public Registrarse() {
        System.out.println("Entrant a la pantalla de registrar-se");
        createUIComponents();
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        buttonIniciarSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de registrar-se");
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
        labelLogo = new JLabel(Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800));
        labelSeparador = new JLabel(Utils.carregarImatge("resources/imatges/separador_amb_or.png", 200, 20));
    }
    public JPanel getDefaultPanel() {
        return panelComplet;
    }



}