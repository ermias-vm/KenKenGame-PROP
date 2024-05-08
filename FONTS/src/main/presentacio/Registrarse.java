package main.presentacio;


import main.domini.excepcions.ExcepcioUsuariJaExisteix;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrarse {

    private JPanel panelComplet;
    private JPanel panelIniciar;
    private JLabel labelLogo;
    private JTextField textFieldUsuari;
    private JLabel labelUsuari;
    private JLabel labelContrasenya;
    private JPasswordField passwordFieldContr;
    private JButton buttonResistrar;
    private JButton buttonIniciarSessio;
    private JLabel lableComfContra;
    private JPasswordField passwordFieldConfContr;

    //private CtrlPresentacio CPre = CtrlPresentacio.getInstance();
    private CtrlUsuariUI CUui = CtrlUsuariUI.getInstance();

    public Registrarse() {
        createUIComponents();
        buttonResistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuari = textFieldUsuari.getText();
                String contrasenya = new String(passwordFieldContr.getPassword());
                String confContrasenya = new String(passwordFieldConfContr.getPassword());
                if (usuari.isEmpty() || contrasenya.isEmpty() || confContrasenya.isEmpty()) {
                    System.out.println("Usuari i/o contrasenya no valida");
                }else if(!contrasenya.equals(confContrasenya)){
                    System.out.println("Contrasenya no coincideix");
                }
                else {
                    try {
                        CUui.registrarUsuari(usuari, contrasenya);
                        System.out.println("Usuari registrat correctament: es mostrara menu principal....");
                    } catch (ExcepcioUsuariJaExisteix ex) {
                        System.out.println("Usuari ja existeix");
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
    }

    private void createUIComponents() {

    }
    public JPanel getDefaultPanel() {
        return panelComplet;
    }



}