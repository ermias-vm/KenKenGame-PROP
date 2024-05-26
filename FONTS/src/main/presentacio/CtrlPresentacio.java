package main.presentacio;

import main.domini.classes.Usuari;
import main.domini.controladors.CtrlDomini;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;
import main.presentacio.CrearKenkenManual.CrearKenkenManual;

import javax.swing.*;
import java.io.IOException;

public class CtrlPresentacio {
    public static final int NOMBREPARTIDESLLISTA = 10;

    private static CtrlPresentacio CPresentacio;
    private static CtrlDomini CDomini;
    private JFrame mainFrame = new JFrame();

    private IniciarSessio iniSessio;
    private Registrarse registrar;
    private MenuPrincipal menuPrincipal;
    private CrearKenKenParametres generarKenken;
    private CrearKenkenManual crearKenken;
    private ConfigUsuari configUsuari;

    private CtrlPresentacio() {
        CDomini= CtrlDomini.getInstance();
    }

    public static CtrlPresentacio getInstance() {
        if (CPresentacio == null) CPresentacio= new CtrlPresentacio();
        return CPresentacio;
    }

            ///////// FUNCIONS DE INTERFICIE GRAFICA /////////

    public void run() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        showIniciarSessio();
        //showCrearKenKen();
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
        mainFrame.setContentPane(menuPrincipal.getDefaultPanel());
        mainFrame.setVisible(true);
    }
    public void showConfigUsuari() {
        configUsuari = new ConfigUsuari();
        mainFrame.setTitle("Configuracio Usuari");
        mainFrame.setContentPane(configUsuari.getDefaultPanel());
        mainFrame.setVisible(true);
    }
    public void showGenerarKenKen() {
        generarKenken = new CrearKenKenParametres();
        mainFrame.setTitle("Generador Kenken");
        mainFrame.setContentPane(generarKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }
    public void showCrearKenKen() {
        CrearKenkenManual.newInstance();
        crearKenken = CrearKenkenManual.getInstance();
        mainFrame.setTitle("Creador Kenken");
        mainFrame.setContentPane(crearKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void initJugar() {
        //C.iniciarPartida();
    }

            ///////// FUNCIONS QUE CRIDEN AL DOMINI /////////


            /// FUNCINOS USUARI ///

    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CDomini.iniciarSessio(nomUsuari, contrasenya);
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CDomini.registrarUsuari(nomUsuari, contrasenya);
    }

    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CDomini.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    public Usuari getUsuariActual() {
        return CDomini.getUsuariActual();
    }

    public void tancarSessio() {
        CDomini.tancarSessio();
    }



}


