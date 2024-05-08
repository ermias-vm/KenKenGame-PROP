package main.domini.controladors;

import main.domini.classes.*;
import main.domini.excepcions.*;

import java.io.IOException;
//import main.persistencia.CtrlPersistencia;

/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private CtrlUsuari CU;
    private ControladorPartida CP;
    private CtrlKenkens CK;


    private Tauler T;
    private SolucionadorKenken AS;
    private CreadorKenkenManual AC;
    /**
     * Constructor del controlador de domini.
     */
    public CtrlDomini() {
        CP = new ControladorPartida();
        CK = new CtrlKenkens();
        CU = new CtrlUsuari();
        AS = new SolucionadorKenken();
        AC = new CreadorKenkenManual();

    }

    //Usuari
    public void iniciarSessio(String usuari, String contrasenya) throws IOException, ExcepcioContrasenyaIncorrecta, ExcepcioUsuariNoExisteix {
        CU.iniciarSessio(usuari, contrasenya);
    }

    public void registrarUsuari(String usuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CU.registrarse(usuari, contrasenya);
    }

    public void canviarContrasenya(String ctrActual, String ctrNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CU.canviarContrasenya(ctrActual, ctrNova);
    }

    public void tancarSessio() {
        CU.tancarSessio();
    }


    //Kenkens
    public void pintarTauler(int idTauler, String grau) throws Exception {
        T = CK.llegirTauler(idTauler, grau);
        System.out.println("Contingut del Tauler " + idTauler + " de grau " + grau + ":");
        CK.mostrarTauler(T);
    }

    public void resoldreKenken(int idTauler, String grau) throws Exception {
        T = CK.llegirTauler(idTauler, grau);
        System.out.println("Contingut del Tauler " + idTauler + " de grau " + grau + ":");
        CK.mostrarTauler(T);
        AS.solucionarKenken(T);
        if (T.teSolucio()) {
            System.out.println("Tauler resolt:");
            CK.mostrarTauler(T);
        }
        else {
            System.out.println("El tauler no té solució."+ "\n");
            CK.mostrarTauler(T);
        }
    }


    //Partida
    /**
     * Carrega l'última partida guardada d'un usuari.
     * @param nomUsuari Nom de l'usuari.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioInicialitzacioPersistenciaPartida Si es produeix un error durant la inicialització de la persistència de la partida.
     * @throws ExcepcioNoPermisUsuari Si l'usuari no té permís per carregar la partida.
     * @throws ExcepcioPartidaEnCurs Si ja hi ha una partida en curs.
     * @throws ExcepcioCreacioPartida Si es produeix un error durant la creació de la partida.
     */
    private void carregarUltimaPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioNoPermisUsuari, ExcepcioPartidaEnCurs, ExcepcioCreacioPartida {
        CP.carregarUltimaPartidaGuardada(nomUsuari);
    }

    /**
     * Inicia una partida guardada.
     * @param identificadorPartida Identificador de la partida a iniciar.
     * @param nomUsuari Nom de l'usuari.
     * @throws ExcepcioPartidaEnCurs Si ja hi ha una partida en curs.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioNoPermisUsuari Si l'usuari no té permís per iniciar la partida.
     * @throws ExcepcioCreacioPartida Si es produeix un error durant la creació de la partida.
     */
    private void iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        CP.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }

    /**
     * Inicia una nova partida amb un tauler específic.
     * @param identificadorTauler Identificador del tauler per a la nova partida.
     * @param nomUsuari Nom de l'usuari.
     * @throws ExcepcioCarregaTauler Si es produeix un error durant la càrrega del tauler.
     * @throws ExcepcioInicialitzacioControladorTauler Si es produeix un error durant la inicialització del controlador del tauler.
     * @throws ExcepcioPartidaEnCurs Si ja hi ha una partida en curs.
     */
    private void iniciarNovaPartidaTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler,
            ExcepcioInicialitzacioControladorTauler,
            ExcepcioPartidaEnCurs {
        CP.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }

    /**
     * Inicia una nova partida amb dades de tauler específiques.
     * @param dadesTauler Dades del tauler per a la nova partida.
     * @param nomUsuari Nom de l'usuari.
     * @throws ExcepcioInicialitzacioControladorTauler Si es produeix un error durant la inicialització del controlador del tauler.
     * @throws ExcepcioPartidaEnCurs Si ja hi ha una partida en curs.
     * @throws ExcepcioCarregaTauler Si es produeix un error durant la càrrega del tauler.
     */
    private void iniciarNovaPartidaDadesTauler(String dadesTauler, String nomUsuari) throws ExcepcioInicialitzacioControladorTauler,
            ExcepcioPartidaEnCurs, ExcepcioCarregaTauler {
        CP.iniciaPartidaDadesTauler(dadesTauler, nomUsuari);
    }

    /**
     * Introdueix un valor en una casella de la partida en curs.
     * @param fila Fila de la casella.
     * @param columna Columna de la casella.
     * @param valor Valor a introduir.
     * @throws ExcepcioValorInvalid Si el valor és invàlid.
     * @throws ExcepcioPartidaEnCurs Si no hi ha una partida en curs.
     * @throws ExcepcionPosicioIncorrecta Si la posició de la casella és incorrecta.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioPartidaAcabada Si la partida ja ha acabat.
     */
    private void introduirValorCasella(int fila, int columna, int valor) throws ExcepcioValorInvalid,
            ExcepcioPartidaEnCurs,
            ExcepcionPosicioIncorrecta, ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaAcabada {
        CP.introduirValor(fila, columna, valor);
    }

    /**
     * Dona una pista per a la partida en curs.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioValorInvalid Si el valor de la pista és invàlid.
     * @throws ExcepcioPartidaAcabada Si la partida ja ha acabat.
     * @throws ExcepcionPosicioIncorrecta Si la posició de la pista és incorrecta.
     */
    private void donarPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcionPosicioIncorrecta {
        CP.donaPista();
    }

    /**
     * Guarda la partida en curs.
     * @param nomUsuari Nom de l'usuari.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioNoPermisUsuari Si l'usuari no té permís per guardar la partida.
     * @throws ExcepcioPartidaAcabada Si la partida ja ha acabat.
     */
    private void guardarPartida(String nomUsuari) throws ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioPartidaAcabada {
        CP.guardarPartida(nomUsuari);
    }

    /**
     * Tanca i guarda la partida en curs.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioPartidaAcabada Si la partida ja ha acabat.
     */
    private void tancarIguardarPartida() throws ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaAcabada {
        CP.tancarIguardarPartida();
    }

    /**
     * Acaba la partida en curs.
     * @throws ExcepcioPartidaAcabada Si la partida ja ha acabat.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioCarregaPartida Si es produeix un error durant la càrrega de la partida.
     * @throws ExcepcioPartidaMalament Si la partida no s'ha acabat correctament.
     */
    private void acabarPartida() throws ExcepcioPartidaAcabada, ExcepcioPartidaTancada, ExcepcioCarregaPartida, ExcepcioPartidaMalament {
        CP.acabarPartida();
    }

    /**
     * Tanca la partida en curs.
     */
    private void tancaPartida() {
        CP.tancaPartida();
    }

}