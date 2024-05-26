package main.presentacio;

import main.domini.controladors.CtrlDomini;
import main.domini.excepcions.*;
import main.presentacio.CrearKenkenManual.CrearKenkenManual;

import javax.swing.*;
import java.util.ArrayList;

public class CtrlPresentacio {
    public static final int NOMBREPARTIDESLLISTA = 10;

    private static CtrlPresentacio CPresentacio;
    private static CtrlDomini CDomini;
    private static CtrlUsuariUI CUsuariUI;
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

    public void run() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        //showIniciarSessio();
        showCrearKenKen();
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
    public String[] getPartidesGuardadesUsuari() {
        return CDomini.getPartidesGuardadesUsuari();
    }
    public int[][] getValorsPartida() {
        return CDomini.getValorsPartida();
    }

    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return CDomini.haJugat(identificadorTauler, nomUsuari);
    }

    public int getMidaPartida() {
        return CDomini.getMidaPartida();
    }

    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioPartidaEnCurs {
        return CDomini.carregarUltimaPartidaGuardada(nomUsuari);
    }

    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida {
        return CDomini.carregarPartidesGuardadesUsuari(nomUsuari);
    }

    public String iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        return CDomini.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }

    public String iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CDomini.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }

    public String iniciaPartidaAleatoria(int mida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CDomini.iniciaPartidaAleatoria(mida, nomUsuari);
    }

    public String introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcioPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        return CDomini.introduirValor(fila, columna, valor);
    }

    public String desferMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CDomini.desferMoviment();
    }
    public String referMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CDomini.referMoviment();
    }

    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
        return CDomini.donaPista();
    }

    public boolean guardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CDomini.guardarPartida(nomUsuari);
    }

    public boolean tancarIguardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CDomini.tancarIguardarPartida(nomUsuari);
    }

    public String[] acabarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada, ExcepcioCasellaNoExisteix, ExcepcioNoPermisUsuari {
        return CDomini.acabarPartida(nomUsuari);
    }

    public boolean tancaPartida() {
        return CDomini.tancaPartida();
    }

    public ArrayList<Boolean>[][] getAdjacentsPartida() {
        return CDomini.getAdjacentsPartida();
    }

    public boolean tancaControlador() {
        return CDomini.tancaControlador();
    }

    //ControladorRanking
    public boolean afegirPartida(String partidaAcabada) {
        return CDomini.afegirPartida(partidaAcabada);
    }

    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return CDomini.getRankingN(mida, index, n);
    }

    public ArrayList<String> getRankingMida(int mida) {
        return CDomini.getRankingMida(mida);
    }

    public ArrayList<String> getRankingUsuari(String identificadorUsuari) {
        return CDomini.getRankingUsuari(identificadorUsuari);
    }
}


