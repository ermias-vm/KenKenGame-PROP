package main.domini.controladors;

import com.google.gson.stream.JsonReader;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistencia;

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

    //AFEGIR FUNCIONS DE CONTROLADORS DEL DOMINI
    // ControladorPartida
    public String[] getPartidesGuardadesUsuari() {
        return CPartida.getPartidesGuardadesUsuari();
    }
    public int[][] getValorsPartida() {
        return CPartida.getValorsPartida();
    }

    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return CPartida.haJugat(identificadorTauler, nomUsuari);
    }

    public int getMidaPartida() {
        return CPartida.getMidaPartida();
    }

    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioCarregaPartida, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida, ExcepcioPartidaEnCurs {
        return CPartida.carregarUltimaPartidaGuardada(nomUsuari);
    }

    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida {
        return CPartida.carregarPartidesGuardadesUsuari(nomUsuari);
    }

    public String iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida {
        return CPartida.iniciarPartidaGuardada(identificadorPartida, nomUsuari);
    }

    public String iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CPartida.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
    }

    public String iniciaPartidaAleatoria(int mida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        return CPartida.iniciaPartidaAleatoria(mida, nomUsuari);
    }

    public String introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcioPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        return CPartida.introduirValor(fila, columna, valor);
    }

    public String desferMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CPartida.desferMoviment();
    }
    public String referMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        return CPartida.referMoviment();
    }

    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
        return CPartida.donaPista();
    }

    public boolean guardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CPartida.guardarPartida(nomUsuari);
    }

    public boolean tancarIguardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        return CPartida.tancarIguardarPartida(nomUsuari);
    }

    public String[] acabarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada, ExcepcioCasellaNoExisteix, ExcepcioNoPermisUsuari {
        return CPartida.acabarPartida(nomUsuari);
    }

    public boolean tancaPartida() {
        return CPartida.tancaPartida();
    }

    public ArrayList<Boolean>[][] getAdjacentsPartida() {
        return CPartida.getAdjacentsPartida();
    }

    public boolean tancaControlador() {
        return CPartida.tancaControlador();
    }

    //ControladorRanking
    public boolean afegirPartida(String partidaAcabada) {
        return CRanking.afegirPartida(partidaAcabada);
    }

    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return CRanking.getRankingN(mida, index, n);
    }

    public ArrayList<String> getRankingMida(int mida) {
        return CRanking.getRankingMida(mida);
    }

    public ArrayList<String> getRankingUsuari(String identificadorUsuari) {
        return CRanking.getRankingUsuari(identificadorUsuari);
    }
    //Persistencia
    //Controlador de persistencia de Partides
    /**
     * Carrega l'última partida guardada per un usuari. L'usuari existeix.
     * La informació de la partida guardada es troba al fitxer "data/partides/PartidesGuardades.txt" i està ordenada per última guardada.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a llegir les dades de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a retornar les dades de la partida.
     * @param nomUsuari Nom de l'usuari que ha guardat la partida i la vol carregar.
     * @return Una cadena de text amb la informació de la partida guardada.
     */
    public String carregarUltimaPartidaGuardadaPersistencia(String nomUsuari){
        return CPersistencia.carregarUltimaPartidaGuardada(nomUsuari);
    }
    /**
     * Carrega totes les partides guardades per un usuari. L'usuari existeix.
     * La informació de les partides guardades es troba al fitxer "data/partides/PartidesGuardades.txt".
     * En utilitzar un HashSet per identificar les partides, es garanteix que no es repeteixen.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a llegir les dades de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a retornar les dades de la partida.<br>
     * @param nomUsuari Nom de l'usuari que ha guardat les partides i les vol carregar.
     * @return Una llista de cadenes de text amb la informació de les partides guardades.
     */
    public ArrayList<String> carregarPartidesGuardadesUsuariPersistencia(String nomUsuari) {
        return CPersistencia.carregarPartidesGuardadesUsuari(nomUsuari);
    }
    /**
     * Carrega totes les partides acabades per un usuari. L'usuari existeix.
     * Busca a tots els fitxers de partides acabades per trobar les partides de l'usuari.<br>
     * Utilitza el format descrit a {@link main.persistencia.ControladorPersistenciaPartida#arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param nomUsuari Nom de l'usuari del qual es volen carregar les partides.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregarPartidesAcabadesUsuariPersistencia(String nomUsuari) {
        return CPersistencia.carregarPartidesAcabadesUsuari(nomUsuari);
    }
    /**
     * Carrega totes les partides acabades d'una mida. La mida és vàlida.
     * La informació de les partides acabades es troba al fitxer "data/partides/PartidesAcabadesMida%d.txt" on %d és la mida.<br>
     * Utilitza el format descrit a {@link main.persistencia.ControladorPersistenciaPartida#arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param mida Mida de les partides que es volen carregar.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregaPartidesAcabadesMidaPersistencia(int mida) {
        return CPersistencia.carregaPartidesAcabadesMida(mida);
    }
    /**
     * Guarda una partida.
     * La partida es guarda a l'inici del fitxer de partides guardades assegurant ordre cronologic.
     * La informació de la partida es guarda al fitxer "data/partides/PartidesGuardades.txt".<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a guardar les dades a memòria.
     * @param partidaGuardada Una cadena de text amb la informació de la partida a guardar.
     * @return True si s'ha guardat la partida, false altrament.
     */
    public boolean guardarPartidaPersistencia(String partidaGuardada) {
        return CPersistencia.guardarPartida(partidaGuardada);
    }
    /**
     * Arxiva una partida acabada.
     * La partida arxivada es guarda al final del fitxer de partides acabades.
     * La informació de la partida es guarda al fitxer "data/partides/PartidesAcabadesGuardades.txt" si havia estat guardada.
     * O al fitxer "data/partides/PartidesAcabadesMida%d.txt" on %d és la mida de la partida.
     * S'elimina la partida arxivada del fitxer de partides guardades.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per a llegir la partida.
     * Però la guarda al fitxer com a (sense comptar les línies amb / ni |):<br>
     * //////FORMAT GUARDAT PARTIDA ACABADA\\\\\\<br>
     * Identificador de la partida<br>
     * Identificador de l'usuari<br>
     * Identificador del tauler<br>
     * Temps total de la partida<br>
     * Mida del tauler<br>
     *  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||<br>
     * Total: 5 línies.
     * El si és guardada o no ve implicit en el nom del fitxer.
     * @param partidaAcabada Una cadena de text amb la informació de la partida a arxivar.
     * @return True si s'ha arxivat la partida, false altrament.
     */
    public boolean arxivarPartidaPersistencia(String partidaAcabada) {
        return CPersistencia.arxivarPartida(partidaAcabada);
    }
    /**
     * Funció de cerca per a saber si un usuari ha jugat ja un tauler.
     * @param identificadorTauler Identificador del tauler
     * @param nomUsuari Nom de l'usuari
     * @return True si l'usuari ha jugat el tauler, false altrament.
     */
    public boolean haJugatPersistencia(String identificadorTauler, String nomUsuari) {
        return CPersistencia.haJugat(identificadorTauler, nomUsuari);
    }

    // Controlador de persistencia de taulers
    /**
     * Llegeix un tauler del disc.
     * @param identificadorTauler Identificador del tauler.
     * @return Dades del tauler.
     */
    public String llegirTaulerPersistencia(String identificadorTauler) {
        return CPersistencia.llegirTauler(identificadorTauler);
    }
    /**
     * Genera un identificador per al tauler a partir de les dades del tauler i el guarda al disc, comprova primer que no existeixi ja,
     * si existeix retorna l'identificador del tauler que existeix ja.
     * @param dadesTauler Dades del tauler.
     * @return Identificador del tauler.
     */
    public String generaIdentificadorIGuardaTaulerPersistencia(String dadesTauler) {
        return CPersistencia.generaIdentificadorIGuardaTauler(dadesTauler);
    }
    /**
     * Selecciona un tauler aleatori de la mida especificada.
     * @param mida Mida del tauler.
     * @return Nom del fitxer del tauler seleccionat o string buida si no hi ha.
     */
    public String seleccionaTaulerAleatoriPersistencia(int mida) {
        return CPersistencia.seleccionaTaulerAleatori(mida);
    }
    // Controlador de persistencia d'usuaris
    public JsonReader getUsuariPersistencia(String nomUsuari) throws FileNotFoundException {
        return CPersistencia.getUsuari(nomUsuari);
    }
    public boolean existeixUsuariPersistencia(String nomUsuari) {
        return CPersistencia.existeixUsuari(nomUsuari);
    }
    public void guardarUsuariPersistencia(String nomUsuari, String dadesUsuariJson) throws IOException {
        CPersistencia.guardarUsuari(nomUsuari, dadesUsuariJson);
    }
}