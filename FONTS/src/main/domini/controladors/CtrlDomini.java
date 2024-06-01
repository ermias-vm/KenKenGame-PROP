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
import java.util.ArrayList;


/**
 * La classe CtrlDomini és el controlador principal del domini de l'aplicació Kenken.
 * Aquesta classe és responsable de gestionar les operacions principals del joc, incloent la gestió d'usuaris,
 * partides, rankings i Kenkens.
 *
 * @author Ermias Valls Mayor
 */
public class CtrlDomini {

    /**
     * Instància única de la classe CtrlDomini, utilitzada per implementar el patró Singleton.
     */
    private static CtrlDomini CDomini;

    /**
     * Instància única de la classe CtrlUsuari, utilitzada per accedir a les funcionalitats relacionades amb els usuaris.
     */
    private static CtrlUsuari CUsuari;

    /**
     * Instància única de la classe ControladorPartida, utilitzada per gestionar les partides del joc.
     */
    private static ControladorPartida CPartida;

    /**
     * Instància única de la classe CtrlKenkens, utilitzada per gestionar els Kenkens del joc.
     */
    private static CtrlKenkens CKenkens;

    /**
     * Instància única de la classe ControladorRanking, utilitzada per gestionar el ranking del joc.
     */
    private static ControladorRanking CRanking;

    /**
     * Instància única de la classe ControladorPersistencia, utilitzada per gestionar la persistència de dades del joc.
     */
    private static ControladorPersistencia CPersistencia;

    /**
     * Usuari actual de la sessió.
     */
    private Usuari usuariActual;

    /**
     * Constructor privat de la classe CtrlDomini.
     * Inicialitza les instàncies de ControladorPersistencia, CtrlUsuari, ControladorPartida, CtrlKenkens, ControladorRanking.
     */
    private CtrlDomini() {
        CPersistencia = ControladorPersistencia.getInstance();
        CUsuari = CtrlUsuari.getInstance();
        CPartida = ControladorPartida.getInstance();
        CPartida.setControladorDomini(this);
        CKenkens = CtrlKenkens.getInstance();
        CRanking = ControladorRanking.getInstance();
        CRanking.setControladorDomini(this);

    }

    /**
     * Retorna la instància singleton de CtrlDomini.
     *
     * @return Instància de CtrlDomini.
     */
    public static CtrlDomini getInstance() {
        if (CDomini == null) CDomini = new CtrlDomini();
        return CDomini;
    }

            /// FUNCINOS USUARI ///

    /**
     * {@link CtrlUsuari#iniciarSessio(String, String)}
     */
    public void iniciarSessio(String nomUsuari, String contrasenya) throws ExcepcioContrasenyaIncorrecta, IOException, ExcepcioUsuariNoExisteix {
        CUsuari.iniciarSessio(nomUsuari, contrasenya);
    }

    /**
     * {@link CtrlUsuari#registrarUsuari(String, String)}
     */
    public void registrarUsuari(String nomUsuari, String contrasenya) throws ExcepcioUsuariJaExisteix, IOException {
        CUsuari.registrarUsuari(nomUsuari, contrasenya);
    }

    /**
     * {@link CtrlUsuari#canviarContrasenya(String, String)}
     */
    public void canviarContrasenya(String contrasenyaActual, String contrasenyaNova) throws ExcepcioContrasenyaIncorrecta, IOException {
        CUsuari.canviarContrasenya(contrasenyaActual, contrasenyaNova);
    }

    /**
     * Estableix l'usuari actual de la sessió.
     * @param usuari L'objecte Usuari que es vol establir com a usuari actual.
     */
    public void setUsuariActual(Usuari usuari) {
        usuariActual = usuari;
    }

    /**
     * Obté l'usuari actual de la sessió.
     * @return L'objecte Usuari que representa l'usuari actual de la sessió.
     */
    public Usuari getUsuariActual() {
        return usuariActual;
    }

    /**
     * Obté el nom de l'usuari actual de la sessió.
     * @return Una cadena de text que representa el nom de l'usuari actual.
     */
    public String getNomUsuariActual() {
        return usuariActual.getNomUsuari();
    }

    /**
     * Tanca la sessió actual, establint l'usuari actual a null.
     */
    public void tancarSessio() {
        usuariActual = null;
    }


            /// FUNCIONS PARTIDA ///

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
    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioInicialitzacioPersistenciaPartida, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioPartidaEnCurs {
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
    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
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
    public ArrayList<String> getOperacionsPartida() {
        return CPartida.getOperacionsPartida();
    }
    public float getTempsPartida() {
        return CPartida.getTempsPartida();
    }

            /// FUNCIONS RANKING ///

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
    public ArrayList<String> getRankingUsuari(String identificadorUsuari, int ultimaMida_) {
        return CRanking.getRankingUsuari(identificadorUsuari, ultimaMida_);
    }


        /// FUNCIONS KENKENS ///

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
    public int[][] resoldreKenken(Tauler T, int[][] valorsPartida) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
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