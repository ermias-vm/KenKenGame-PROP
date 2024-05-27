package main.domini.controladors;
import main.domini.classes.Tauler;
import main.domini.classes.Usuari;

import com.google.gson.stream.JsonReader;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistencia;
import main.domini.excepcions.ExcepcioContrasenyaIncorrecta;
import main.domini.excepcions.ExcepcioUsuariJaExisteix;
import main.domini.excepcions.ExcepcioUsuariNoExisteix;

import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Controlador de domini que gestiona les operacions principals del joc Kenken.
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    private static CtrlDomini CDomini;
    private static CtrlUsuari CUsuari;
    private static ControladorPartida CPartida;
    private static CtrlKenkens CKenkens;
    private static ControladorRanking CRanking;
    private static ControladorPersistencia CPersistencia;

    private Usuari usuariActual;

    private CtrlDomini() {
        CPersistencia = ControladorPersistencia.getInstance();
        CUsuari = CtrlUsuari.getInstance();
        CPartida = ControladorPartida.getInstance();
        CKenkens = CtrlKenkens.getInstance();
        CRanking = ControladorRanking.getInstance();

    }


    public static CtrlDomini getInstance() {
        if (CDomini == null) CDomini = new CtrlDomini();
        return CDomini;
    }

    //////////////// FUNCIONS DE USUARI ////////////////

    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CUsuari.iniciarSessio(nomUsuari, contrasenya);
    }

    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CUsuari.registrarse(nomUsuari, contrasenya);
    }

    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CUsuari.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    public void setUsuariActual(Usuari usuari) {
        usuariActual = usuari;
    }

    public Usuari getUsuariActual() {
        return usuariActual;
    }
    public String getNomUsuariActual() {
        return usuariActual.getNomUsuari();
    }
    public void tancarSessio() {
        usuariActual = null;
    }

    // ControladorPartida
    /**
     * {@link ControladorPartida#getPartidesGuardadesUsuari()}
     */
    public String[] getPartidesGuardadesUsuari() {
        return CPartida.getPartidesGuardadesUsuari();
    }
    /**
     * {@link ControladorPartida#getValorsPartida()}
     */
    public int[][] getValorsPartida() {
        return CPartida.getValorsPartida();
    }
    /**
     * {@link ControladorPartida#haJugat(String, String)}
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return CPartida.haJugat(identificadorTauler, nomUsuari);
    }
    /**
     * {@link ControladorPartida#getMidaPartida()}
     */
    public int getMidaPartida() {
        return CPartida.getMidaPartida();
    }
    /**
     * {@link ControladorPartida#carregarUltimaPartidaGuardada(String)}
     */
    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioPartidaEnCurs {
        return CPartida.carregarUltimaPartidaGuardada(nomUsuari);
    }
    /**
     * {@link ControladorPartida#carregarPartidesGuardadesUsuari(String)}
     */
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida {
        return CPartida.carregarPartidesGuardadesUsuari(nomUsuari);
    }
    /**
     * {@link ControladorPartida#iniciarPartidaGuardada(String,String)}
     */
    public String iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        return CPartida.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }
    /**
     * {@link ControladorPartida#iniciaPartidaIdentificadorTauler(String, String)}
     */
    public String iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CPartida.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }
    /**
     * {@link ControladorPartida#iniciaPartidaAleatoria(int, String)}
     */
    public String iniciaPartidaAleatoria(int mida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler, ExcepcioNoPartidaAleatoria {
        return CPartida.iniciaPartidaAleatoria(mida, nomUsuari);
    }
    /**
     * {@link ControladorPartida#introduirValor(int, int, int)}
     */
    public String introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcioPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        return CPartida.introduirValor(fila, columna, valor);
    }
    /**
     * {@link ControladorPartida#desferMoviment()}
     */
    public String desferMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CPartida.desferMoviment();
    }
    /**
     * {@link ControladorPartida#referMoviment()}
     */
    public String referMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CPartida.referMoviment();
    }
    /**
     * {@link ControladorPartida#donaPista()}
     */
    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
        return CPartida.donaPista();
    }
    /**
     * {@link ControladorPartida#guardarPartida(String)}
     */
    public boolean guardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CPartida.guardarPartida(nomUsuari);
    }

    /**
     * {@link ControladorPartida#tancarIguardarPartida(String)}
     */
    public boolean tancarIguardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CPartida.tancarIguardarPartida(nomUsuari);
    }
    /**
     * {@link ControladorPartida#acabarPartida(String)}
     */
    public String[] acabarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada, ExcepcioCasellaNoExisteix, ExcepcioNoPermisUsuari {
        return CPartida.acabarPartida(nomUsuari);
    }

    /**
     * {@link ControladorPartida#tancaPartida()}
     */
    public boolean tancaPartida() {
        return CPartida.tancaPartida();
    }

    /**
     * {@link ControladorPartida#getAdjacentsPartida()}
     */
    public ArrayList<Boolean>[][] getAdjacentsPartida() {
        return CPartida.getAdjacentsPartida();
    }

    //ControladorRanking
    /**
     * {@link ControladorRanking#afegirPartida(String)}
     */
    public boolean afegirPartida(String partidaAcabada) {
        return CRanking.afegirPartida(partidaAcabada);
    }
    /**
     * {@link ControladorRanking#getRankingN(int, int, int)}
     */
    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return CRanking.getRankingN(mida, index, n);
    }
    /**
     * {@link ControladorRanking#getRankingMida(int)}
     */
    public ArrayList<String> getRankingMida(int mida) {
        return CRanking.getRankingMida(mida);
    }
    /**
     * {@link ControladorRanking#getRankingUsuari(String)}
     */
    public ArrayList<String> getRankingUsuari(String identificadorUsuari) {
        return CRanking.getRankingUsuari(identificadorUsuari);
    }
    //CtrlKenkens
    /**
     * {@link CtrlKenkens#llegirTauler(String)}
     */
    public Tauler llegirTauler(String id) throws ExcepcioTaulerNoExisteix {
        return CKenkens.llegirTauler(id);
    }
    /**
     * {@link CtrlKenkens#stringToTauler(String, String)}
     */
    public Tauler stringToTauler(String contingutTauler, String id) {
        return CKenkens.stringToTauler(contingutTauler, id);
    }
    /**
     * {@link CtrlKenkens#taulerToString(Tauler)}
     */
    public String taulerToString(Tauler T) {
        return CKenkens.taulerToString(T);
    }
    /**
     * {@link CtrlKenkens#seleccionaTaulerAleatori(int)}
     */
    public String seleccionaTaulerAleatori(int mida) {
        return CKenkens.seleccionaTaulerAleatori(mida);
    }
    /**
     * {@link CtrlKenkens#resoldreKenken(Tauler, int[][])}
     */
    public int[][] resoldreKenken(Tauler T, int[][] valorsPartida) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
        return CKenkens.resoldreKenken(T, valorsPartida);
    }
    /**
     * {@link CtrlKenkens#guardarTaulerBD(String)}
     */
    public String guardarTaulerBD(String contigutTauler) {
        return CKenkens.guardarTaulerBD(contigutTauler);
    }
    /**
     * {@link CtrlKenkens#esTaulerValid(String)}
     */
    public boolean esTaulerValid(String contingutTauler) {
        return CKenkens.esTaulerValid(contingutTauler);
    }
    /**
     * {@link CtrlKenkens#resoldreKenken(String, boolean)}
     */
    public void resoldreKenken(String idTauler, boolean guardarBD) throws Exception {
        CKenkens.resoldreKenken(idTauler, guardarBD);
    }
    /**
     * {@link CtrlKenkens#pintarTauler(String)}
     */
    public void pintarTauler(String idTauler) throws Exception {
        CKenkens.pintarTauler(idTauler);
    }
    /**
     * {@link CtrlKenkens#mostrarTauler(Tauler)}
     */
    public void mostrarTauler(Tauler T) throws Exception {
        CKenkens.mostrarTauler(T);
    }
    //Persistencia
    //Controlador de persistencia de Partides
    /**
     * {@link ControladorPersistencia#carregarUltimaPartidaGuardada(String)}
     */
    public String carregarUltimaPartidaGuardadaPersistencia(String nomUsuari){
        return CPersistencia.carregarUltimaPartidaGuardada(nomUsuari);
    }
    /**
     * {@link ControladorPersistencia#carregarPartidesGuardadesUsuari(String)}
     */
    public ArrayList<String> carregarPartidesGuardadesUsuariPersistencia(String nomUsuari) {
        return CPersistencia.carregarPartidesGuardadesUsuari(nomUsuari);
    }
    /**
     * {@link ControladorPersistencia#carregaPartidesAcabadesMida(int)}
     */
    public ArrayList<String> carregaPartidesAcabadesMidaPersistencia(int mida) {
        return CPersistencia.carregaPartidesAcabadesMida(mida);
    }
    /**
     * {@link ControladorPersistencia#guardarPartida(String)}
     */
    public boolean guardarPartidaPersistencia(String partidaGuardada) {
        return CPersistencia.guardarPartida(partidaGuardada);
    }
    /**
     * {@link ControladorPersistencia#arxivarPartida(String)}
     */
    public boolean arxivarPartidaPersistencia(String partidaAcabada) {
        return CPersistencia.arxivarPartida(partidaAcabada);
    }
    /**
     * {@link ControladorPersistencia#haJugat(String,String)}
     */
    public boolean haJugatPersistencia(String identificadorTauler, String nomUsuari) {
        return CPersistencia.haJugat(identificadorTauler, nomUsuari);
    }

    // Controlador de persistencia de taulers

    /**
     * {@link ControladorPersistencia#llegirTauler(String)}
     */
    public String llegirTaulerPersistencia(String identificadorTauler) throws ExcepcioTaulerNoExisteix {
        return CPersistencia.llegirTauler(identificadorTauler);
    }
    /**
     * {@link ControladorPersistencia#generaIdentificadorIGuardaTauler(String)}
     */
    public String generaIdentificadorIGuardaTaulerPersistencia(String dadesTauler) {
        return CPersistencia.generaIdentificadorIGuardaTauler(dadesTauler);
    }
    /**
     * {@link ControladorPersistencia#seleccionaTaulerAleatori(int)}
     */
    public String seleccionaTaulerAleatoriPersistencia(int mida) {
        return CPersistencia.seleccionaTaulerAleatori(mida);
    }
    // Controlador de persistencia d'usuaris
    /**
     * {@link ControladorPersistencia#getUsuari(String)}
     */
    public JsonReader getUsuariPersistencia(String nomUsuari) throws FileNotFoundException {
        return CPersistencia.getUsuari(nomUsuari);
    }
    /**
     * {@link ControladorPersistencia#existeixUsuari(String)}
     */
    public boolean existeixUsuariPersistencia(String nomUsuari) {
        return CPersistencia.existeixUsuari(nomUsuari);
    }
    /**
     * {@link ControladorPersistencia#guardarUsuari(String, String)}
     */
    public void guardarUsuariPersistencia(String nomUsuari, String dadesUsuariJson) throws IOException {
        CPersistencia.guardarUsuari(nomUsuari, dadesUsuariJson);
    }
}