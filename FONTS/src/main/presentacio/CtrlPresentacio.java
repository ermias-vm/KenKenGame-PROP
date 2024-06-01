package main.presentacio;

import main.domini.classes.Tauler;
import main.domini.classes.Usuari;
import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlKenkens;
import main.domini.controladors.ControladorRanking;
import main.domini.controladors.ControladorPartida;
import main.domini.excepcions.*;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;
import main.presentacio.CrearKenkenManual.CrearKenkenManual;
import main.presentacio.Partida.ControladorPresentacioPartida;
import main.presentacio.Ranking.ControladorPresentacioRanking;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.io.IOException;


/**
 * La classe CtrlPresentacio és el controlador de presentació principal de l'aplicació.
 * Aquesta classe és responsable de gestionar la interacció entre l'usuari i l'aplicació,
 * incloent la visualització de les vistes i la comunicació amb el controlador de domini.
 *
 * @autor Ermias Valls Mayor
 */
public class CtrlPresentacio {

    public static final int NOMBREPARTIDESLLISTA = 10;
    /**
     * Mida mínima del tauler de les partides.
     */
    public static final int MIDAMIN = 3;
    /**
     * Mida màxima del tauler de les partides.
     */
    public static final int MIDAMAX = 9;

    /**
     * Instància única de la classe CtrlPresentacio, utilitzada per implementar el patró Singleton.
     */
    private static CtrlPresentacio CPresentacio;

    /**
     * Instància única de la classe CtrlDomini, utilitzada per accedir a les funcionalitats del domini.
     */
    private static CtrlDomini CDomini;

    /**
     * Controlador de la presentació del ranking, utilitzat per gestionar la vista del ranking.
     */
    private ControladorPresentacioRanking controladorPresentacioRanking_;

    /**
     * Controlador de la presentació de la partida, utilitzat per gestionar la vista de la partida.
     */
    private ControladorPresentacioPartida controladorPresentacioPartida_;

    /**
     * El marc principal de l'aplicació. Totes les vistes es mostraran en aquest marc.
     */
    private JFrame mainFrame = new JFrame();

    /**
     * Vista de la funcionalitat d'iniciar sessió.
     */
    private IniciarSessio iniSessio;

    /**
     * Vista de la funcionalitat de registrar-se.
     */
    private Registrarse registrar;

    /**
     * Vista del menú principal.
     */
    private MenuPrincipal menuPrincipal;

    /**
     * Vista de la funcionalitat de generar un KenKen.
     */
    private CrearKenKenParametres generarKenken;

    /**
     * Vista de la funcionalitat de crear un KenKen manualment.
     */
    private CrearKenkenManual crearKenken;

    /**
     * Vista de la configuració de l'usuari.
     */
    private ConfigUsuari configUsuari;

    /**
     * Constructor privat de la classe CtrlPresentacio.
     * Inicialitza les instàncies de CtrlDomini, ControladorPresentacioRanking i ControladorPresentacioPartida.
     * També estableix aquesta instància com a controlador de presentació per a ControladorPresentacioRanking i ControladorPresentacioPartida.
     */
    private CtrlPresentacio() {
        CDomini= CtrlDomini.getInstance();
        controladorPresentacioRanking_ = ControladorPresentacioRanking.getInstance();
        controladorPresentacioRanking_.setControladorPresentacio(this);
        controladorPresentacioPartida_ = ControladorPresentacioPartida.getInstance();
        controladorPresentacioPartida_.setControladorPresentacio(this);
    }

    /**
     * Retorna la instància singleton de CtrlPresentacio.
     * Si la instància no s'ha creat encara, la crea.
     * @return Instància de CtrlPresentacio.
     */
    public static CtrlPresentacio getInstance() {
        if (CPresentacio == null) CPresentacio= new CtrlPresentacio();
        return CPresentacio;
    }

            /// FUNCIONS DE LA INTERFICIE GRAFICA ///

    /**
     * Inicia l'aplicació configurant el marc principal i mostrant la vista d'inici de sessió.
     * Configura el comportament del botó de tancament del marc principal per mostrar un missatge de confirmació abans de tancar l'aplicació.
     */
    public void run() {
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        mainFrame,
                        "Segur que vols sortir del joc KenKen?",
                        "Confirmació",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm == 0) {
                    System.exit(0);
                }
            }
        });

        showIniciarSessio();
       // showCrearKenKen();
    }

    /**
     * Mostra la vista d'iniciar sessió.
     * Crea una nova instància de la vista IniciarSessio, la configura com a contingut del marc principal i la fa visible.
     */
    public void showIniciarSessio() {
        iniSessio = new IniciarSessio();
        mainFrame.setTitle("Iniciar Sessio");
        mainFrame.setContentPane(iniSessio.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de registrar-se.
     * Crea una nova instància de la vista Registrarse, la configura com a contingut del marc principal i la fa visible.
     */
    public void showRegistrarse() {
        registrar = new Registrarse();
        mainFrame.setTitle("Registrar-se");
        mainFrame.setContentPane(registrar.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra el menú principal.
     * Crea una nova instància de la vista MenuPrincipal, la configura com a contingut del marc principal i la fa visible.
     */
    public void showMenuPrincipal() {
        menuPrincipal = new MenuPrincipal();
        mainFrame.setTitle("Menu Principal");
        mainFrame.setContentPane(menuPrincipal.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de configuració de l'usuari.
     * Crea una nova instància de la vista ConfigUsuari, la configura com a contingut del marc principal i la fa visible.
     */
    public void showConfigUsuari() {
        configUsuari = new ConfigUsuari();
        mainFrame.setTitle("Configuracio Usuari");
        mainFrame.setContentPane(configUsuari.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de generar un KenKen.
     * Crea una nova instància de la vista CrearKenKenParametres, la configura com a contingut del marc principal i la fa visible.
     */
    public void showGenerarKenKen() {
        generarKenken = new CrearKenKenParametres(false);
        mainFrame.setTitle("Generador Kenken");
        mainFrame.setContentPane(generarKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }
    /**
     * Mostra la vista de generar un KenKen i jugar després.
     * Crea una nova instància de la vista CrearKenKenParametres, la configura com a contingut del marc principal i la fa visible.
     */
    public void showGenerarKenKenJugarDespres() {
        generarKenken = new CrearKenKenParametres(true);
        mainFrame.setTitle("Generador Kenken");
        mainFrame.setContentPane(generarKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de crear un KenKen.
     * Crea una nova instància de la vista CrearKenkenManual, la configura com a contingut del marc principal i la fa visible.
     */
    public void showCrearKenKen() {
        CrearKenkenManual.newInstance();
        crearKenken = CrearKenkenManual.getInstance();
        mainFrame.setTitle("Creador Kenken");
        mainFrame.setContentPane(crearKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de generar un KenKen i jugar després.
     * Crea una nova instància de la vista CrearKenKenParametres, la configura com a contingut del marc principal i la fa visible.
     */
    public void showCrearKenKenJugarDespres() {
        CrearKenkenManual.newInstance(true);
        crearKenken = CrearKenkenManual.getInstance();
        mainFrame.setTitle("Creador Kenken");
        mainFrame.setContentPane(crearKenken.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Inicia la vista de jugar.
     * Inicialitza el menú de jugar partida amb el nom d'usuari actual, configura la vista com a contingut del marc principal i la fa visible.
     */
    public void initJugar() {
        controladorPresentacioPartida_.inicialitzaMenuJugarPartida(CDomini.getNomUsuariActual());
        mainFrame.setTitle("Jugar Partida");
        mainFrame.setContentPane(controladorPresentacioPartida_.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Mostra la vista de ranking.
     * Configura la vista de ranking com a contingut del marc principal i la fa visible.
     */
    public void showRanking() {
        mainFrame.setTitle("Ranking");
        mainFrame.setContentPane(controladorPresentacioRanking_.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * Inicia una partida amb un tauler identificat per l'identificador proporcionat.
     * Inicialitza el menú de jugar partida amb el nom d'usuari actual, inicia una partida amb el tauler identificat, configura la vista de partida com a contingut del marc principal i la fa visible.
     *
     * @param identificadorTauler Identificador del tauler amb el qual iniciar la partida.
     */
    public void jugarIdentificadorTauler(String identificadorTauler) {
        controladorPresentacioPartida_.inicialitzaMenuJugarPartida(CDomini.getNomUsuariActual());
        controladorPresentacioPartida_.jugarIdentificadorTauler(identificadorTauler);
        mainFrame.setTitle("Jugar Partida");
        mainFrame.setContentPane(controladorPresentacioPartida_.getDefaultPanel());
        mainFrame.setVisible(true);
    }

            /// FUNCINOS USUARI ///
    /**
     * {@link CtrlDomini#iniciarSessio(String, String)}
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CDomini.iniciarSessio(nomUsuari, contrasenya);
    }

    /**
     * {@link CtrlDomini#registrarUsuari(String, String)}
     */
    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CDomini.registrarUsuari(nomUsuari, contrasenya);
    }

    /**
     * {@link CtrlDomini#canviarContrasenya(String,String)}
     */
    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CDomini.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    /**
     * {@link CtrlDomini#getUsuariActual()}
     */
    public Usuari getUsuariActual() {
        return CDomini.getUsuariActual();
    }

    /**
     * {@link CtrlDomini#tancarSessio()}
     */
    public void tancarSessio() {
        CDomini.tancarSessio();
    }


            /// FUNCIONS PARTIDA ///
    /**
     * {@link ControladorPartida#getPartidesGuardadesUsuari()}
     */
    public String[] getPartidesGuardadesUsuari() {
        return CDomini.getPartidesGuardadesUsuari();
    }

    /**
     * {@link ControladorPartida#getValorsPartida()}
     */
    public int[][] getValorsPartida() {
        return CDomini.getValorsPartida();
    }

    /**
     * {@link ControladorPartida#haJugat(String, String)}
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return CDomini.haJugat(identificadorTauler, nomUsuari);
    }

    /**
     * {@link ControladorPartida#getMidaPartida()}
     */
    public int getMidaPartida() {
        return CDomini.getMidaPartida();
    }

    /**
     * {@link ControladorPartida#carregarUltimaPartidaGuardada(String)}
     */
    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioPersistenciaPartida {
        return CDomini.carregarUltimaPartidaGuardada(nomUsuari);
    }

    /**
     * {@link ControladorPartida#carregarPartidesGuardadesUsuari(String)}
     */
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida {
        return CDomini.carregarPartidesGuardadesUsuari(nomUsuari);
    }

    /**
     * {@link ControladorPartida#iniciarPartidaGuardada(String,String)}
     */
    public String iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        return CDomini.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }

    /**
     * {@link ControladorPartida#iniciaPartidaIdentificadorTauler(String, String)}
     */
    public String iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CDomini.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }

    /**
     * {@link ControladorPartida#iniciaPartidaAleatoria(int, String)}
     */
    public String iniciaPartidaAleatoria(int mida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler, ExcepcioNoPartidaAleatoria {
        return CDomini.iniciaPartidaAleatoria(mida, nomUsuari);
    }

    /**
     * {@link ControladorPartida#introduirValor(int, int, int)}
     */
    public String introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcioPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        return CDomini.introduirValor(fila, columna, valor);
    }

    /**
     * {@link ControladorPartida#desferMoviment()}
     */
    public String desferMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CDomini.desferMoviment();
    }

    /**
     * {@link ControladorPartida#referMoviment()}
     */
    public String referMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CDomini.referMoviment();
    }

    /**
     * {@link ControladorPartida#donaPista()}
     */
    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        return CDomini.donaPista();
    }

    /**
     * {@link ControladorPartida#guardarPartida(String)}
     */
    public boolean guardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CDomini.guardarPartida(nomUsuari);
    }

    /**
     * {@link ControladorPartida#tancarIguardarPartida(String)}
     */
    public boolean tancarIguardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CDomini.tancarIguardarPartida(nomUsuari);
    }

    /**
     * {@link ControladorPartida#acabarPartida(String)}
     */
    public String[] acabarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada, ExcepcioCasellaNoExisteix, ExcepcioNoPermisUsuari {
        return CDomini.acabarPartida(nomUsuari);
    }

    /**
     * {@link ControladorPartida#tancaPartida()}
     */
    public boolean tancaPartida() {
        return CDomini.tancaPartida();
    }

    /**
     * {@link ControladorPartida#getAdjacentsPartida()}
     */
    public ArrayList<Boolean>[][] getAdjacentsPartida() {
        return CDomini.getAdjacentsPartida();
    }
    public ArrayList<String> getOperacionsPartida(){
        return CDomini.getOperacionsPartida();
    }
    public float getTempsPartida(){ return CDomini.getTempsPartida(); }

            /// FUNCIONS RANKING ///
    /**
     * {@link ControladorRanking#afegirPartida(String)}
     */
    public boolean afegirPartida(String partidaAcabada) {
        return CDomini.afegirPartida(partidaAcabada);
    }

    /**
     * {@link ControladorRanking#getRankingN(int, int, int)}
     */
    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return CDomini.getRankingN(mida, index, n);
    }

    /**
     * {@link ControladorRanking#getRankingMida(int)}
     */
    public ArrayList<String> getRankingMida(int mida) {
        return CDomini.getRankingMida(mida);
    }

    /**
     * {@link ControladorRanking#getRankingUsuari(String)}
     */
    public ArrayList<String> getRankingUsuari(String identificadorUsuari) {
        return CDomini.getRankingUsuari(identificadorUsuari);
    }




    /**
     * {@link CtrlKenkens#llegirTauler(String)}
     */
    public Tauler llegirTauler(String id) throws ExcepcioTaulerNoExisteix {
        return CDomini.llegirTauler(id);
    }

    /**
     * {@link CtrlKenkens#stringToTauler(String, String)}
     */
    public Tauler stringToTauler(String contingutTauler, String id) {
        return CDomini.stringToTauler(contingutTauler, id);
    }

    /**
     * {@link CtrlKenkens#taulerToString(Tauler)}
     */
    public String taulerToString(Tauler T) {
        return CDomini.taulerToString(T);
    }

    /**
     * {@link CtrlKenkens#seleccionaTaulerAleatori(int)}
     */
    public String seleccionaTaulerAleatori(int mida) {
        return CDomini.seleccionaTaulerAleatori(mida);
    }

    /**
     * {@link CtrlKenkens#resoldreKenken(Tauler, int[][])}
     */
    public int[][] resoldreKenken(Tauler T, int[][] valorsPartida) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        return CDomini.resoldreKenken(T, valorsPartida);
    }

    /**
     * {@link CtrlKenkens#guardarTaulerBD(String)}
     */
    public String guardarTaulerBD(String contigutTauler) {
        return CDomini.guardarTaulerBD(contigutTauler);
    }

    /**
     * {@link CtrlKenkens#esTaulerValid(String)}
     */
    public boolean esTaulerValid(String contingutTauler) {
        return CDomini.esTaulerValid(contingutTauler);
    }

    /**
     * {@link CtrlKenkens#resoldreKenken(String, boolean)}
     */
    public void resoldreKenken(String idTauler, boolean guardarBD) throws Exception {
        CDomini.resoldreKenken(idTauler, guardarBD);
    }

    /**
     * {@link CtrlKenkens#pintarTauler(String)}
     */
    public void pintarTauler(String idTauler) throws Exception {
        CDomini.pintarTauler(idTauler);
    }

    /**
     * {@link CtrlKenkens#mostrarTauler(Tauler)}
     */
    public void mostrarTauler(Tauler T) throws Exception {
        CDomini.mostrarTauler(T);
    }
}


