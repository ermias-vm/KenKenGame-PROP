package main.presentacio;

import main.domini.controladors.CtrlDomini;

import javax.swing.*;

public class CtrlPresentacio {
    private static CtrlPresentacio CPre;
    private static CtrlDomini CD;
    private JFrame mainFrame = new JFrame();

    private IniciarSessio iniSessio;
    private Registrarse registrar;
    private MenuPrincipal menuPrincipal;

    public CtrlPresentacio() {
        CD = CtrlDomini.getInstance();
    }
    public static CtrlPresentacio getInstance() {
        if (CPre == null) CPre = new CtrlPresentacio();
        return CPre;
    }

    public void run() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        showIniciarSessio();
        //showRegistrarse();
    }

    public void showIniciarSessio() {
        iniSessio = new IniciarSessio();
        mainFrame.setTitle("Iniciar Sessio");
        mainFrame.setContentPane(iniSessio.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void showRegistrarse() {
        registrar = new Registrarse();
        mainFrame.setTitle("Registrar-se");
        mainFrame.setContentPane(registrar.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void showMenuPrincipal() {
        menuPrincipal = new MenuPrincipal();
        mainFrame.setTitle("Menu Principal");
        //mainFrame.setContentPane(menuPrincipal.getDefaultPanel());
        mainFrame.setVisible(true);
    }





}


