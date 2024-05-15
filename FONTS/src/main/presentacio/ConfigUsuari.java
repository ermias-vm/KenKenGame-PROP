package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigUsuari {


    private JPanel panelComplet;
    private JLabel labelLogo;
    private JPanel panel2;
    private JLabel canviarContrasenyaLabel;
    private JPasswordField passwordFieldContr;
    private JPasswordField passwordFieldConfContr;
    private JLabel errorLabel;
    private JButton sortirButton;
    private JPasswordField passwordFieldActual;
    private JButton guardarButton;
    private JButton tancarSessioButton;


    public ConfigUsuari() {
        System.out.println("Entrant a la pantalla de configuracio d'usuari");
        createUIComponents();

        passwordFieldActual.setEchoChar((char) 0);
        passwordFieldContr.setEchoChar((char) 0);
        passwordFieldConfContr.setEchoChar((char) 0);

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

        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sortint de la configuracio de l'usuari");
                CtrlPresentacio.getInstance().showMenuPrincipal();

            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contraActual = new String(passwordFieldActual.getPassword());
                String contraNova = new String(passwordFieldContr.getPassword());
                String contraNovaConf = new String(passwordFieldConfContr.getPassword());
                if (!errorLabel.getText().isEmpty()) errorLabel.setText("");

                if (contraActual.isEmpty() || contraActual.equals("Contrasenya actual") || contraNova.isEmpty() || contraNova.equals("Nova contrasenya") || contraNovaConf.isEmpty() || contraNovaConf.equals("Comfirmar nova contrasenya")) {
                    System.out.println("Contrasenya actual i/o nova contrasenya no valida");
                    errorLabel.setText("<html>Contrasenya actual i/o <br>nova contrasenya no valida.<br>Si us plau comproveu les dades.</html>");
                } else if (!contraNova.equals(contraNovaConf)) {
                    System.out.println("Contrasenyes no coincideixen");
                    errorLabel.setText("<html>Contrasenyes no coincideixen.<br>Si us plau comproveu les dades.</html>");
                } else {
                    try {
                        MissatgePopUp missatgePopUp = CtrlPresentacio.getInstance().showPopUp("<html>Estas segur que vols canviar<br>la contrasenya actual?</html>");
                        if (!missatgePopUp.esCancelat()) {
                            CtrlUsuariUI.getInstance().canviarContrasenya(contraActual, contraNova);
                            System.out.println("Contrasenya canviada correctament");
                        }
                        else {
                            System.out.println("Canvi de contrasenya cancelat");
                        }
                    } catch (Exception ex) {
                        System.out.println("Contrasenya actual incorrecta");
                        errorLabel.setText("<html>Contrasenya actual incorrecta.<br>Si us plau comproveu la vostra contrasenya.</html>");
                    }
                }
            }
        });


        tancarSessioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tancant sessio");
                CtrlUsuariUI.getInstance().tancarSessio();
                CtrlPresentacio.getInstance().showIniciarSessio();

            }
        });
    }

    public JPanel getDefaultPanel() {
        return panelComplet;
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
}
