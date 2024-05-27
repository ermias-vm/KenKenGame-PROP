package main.persistencia;

import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controlador principal de persistència.
 * Controla la persistència de les dades de la capa de domini, així com la interacció amb aquesta.
 */
public class ControladorPersistencia {
    /**
     * Instància única del controlador de persistència.
     */
    private static ControladorPersistencia controladorPersistencia_;
    /**
     * Controlador de persistència de taulers.
     */
    private static ControladorPersistenciaTauler controladorPersistenciaTauler_;
    /**
     * Controlador de persistència d'usuaris.
     */
    private static CtrlUsuariData controladorPersistenciaUsuari_;
    /**
     * Controlador de persistència de partides.
     */
    private static ControladorPersistenciaPartida controladorPersistenciaPartida_;

    /**
     * Crea una nova instància del controlador de persistència. Inicialitza els controladors de persistència de taulers, usuaris i partides.
     */
    private ControladorPersistencia() {
        controladorPersistenciaTauler_ = ControladorPersistenciaTauler.getInstance();
        controladorPersistenciaUsuari_ = CtrlUsuariData.getInstance();
        controladorPersistenciaPartida_ = ControladorPersistenciaPartida.getInstance();
    }

    /**
     * Retorna la instància única del controlador de persistència.
     * @return Instància única del controlador de persistència.
     */
    public static ControladorPersistencia getInstance() {
        if (controladorPersistencia_ == null) controladorPersistencia_ = new ControladorPersistencia();
        return controladorPersistencia_;
    }
    //Controlador de persistencia de Partides
    /**
     * Carrega l'última partida guardada per un usuari. L'usuari existeix.
     * La informació de la partida guardada es troba al fitxer "data/partides/PartidesGuardades.txt" i està ordenada per última guardada.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a llegir les dades de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a retornar les dades de la partida.
     * @param nomUsuari Nom de l'usuari que ha guardat la partida i la vol carregar.
     * @return Una cadena de text amb la informació de la partida guardada.
     */
    public String carregarUltimaPartidaGuardada(String nomUsuari){
        return controladorPersistenciaPartida_.carregarUltimaPartidaGuardada(nomUsuari);
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
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) {
        return controladorPersistenciaPartida_.carregarPartidesGuardadesUsuari(nomUsuari);
    }
    /**
     * Carrega totes les partides acabades per un usuari. L'usuari existeix.
     * Busca a tots els fitxers de partides acabades per trobar les partides de l'usuari.<br>
     * Utilitza el format descrit a {@link main.persistencia.ControladorPersistenciaPartida#arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param nomUsuari Nom de l'usuari del qual es volen carregar les partides.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregarPartidesAcabadesUsuari(String nomUsuari) {
        return controladorPersistenciaPartida_.carregarPartidesAcabadesUsuari(nomUsuari);
    }
    /**
     * Carrega totes les partides acabades d'una mida. La mida és vàlida.
     * La informació de les partides acabades es troba al fitxer "data/partides/PartidesAcabadesMida%d.txt" on %d és la mida.<br>
     * Utilitza el format descrit a {@link main.persistencia.ControladorPersistenciaPartida#arxivarPartida(String)} per llegir la informació de la partida de memòria.<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#acabaPartida()} per retornar la informació.<br>
     * @param mida Mida de les partides que es volen carregar.
     * @return Una llista de cadenes de text amb la informació de les partides acabades.
     */
    public ArrayList<String> carregaPartidesAcabadesMida(int mida) {
        return controladorPersistenciaPartida_.carregaPartidesAcabadesMida(mida);
    }
    /**
     * Guarda una partida.
     * La partida es guarda a l'inici del fitxer de partides guardades assegurant ordre cronologic.
     * La informació de la partida es guarda al fitxer "data/partides/PartidesGuardades.txt".<br>
     * Utilitza el format descrit a {@link main.domini.classes.Partida#guardaPartida()} per a guardar les dades a memòria.
     * @param partidaGuardada Una cadena de text amb la informació de la partida a guardar.
     * @return True si s'ha guardat la partida, false altrament.
     */
    public boolean guardarPartida(String partidaGuardada) {
        return controladorPersistenciaPartida_.guardarPartida(partidaGuardada);
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
    public boolean arxivarPartida(String partidaAcabada) {
        return controladorPersistenciaPartida_.arxivarPartida(partidaAcabada);
    }
    /**
     * Funció de cerca per a saber si un usuari ha jugat ja un tauler.
     * @param identificadorTauler Identificador del tauler
     * @param nomUsuari Nom de l'usuari
     * @return True si l'usuari ha jugat el tauler, false altrament.
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return controladorPersistenciaPartida_.haJugat(identificadorTauler, nomUsuari);
    }

    // Controlador de persistencia de taulers
    /**
     * Llegeix un tauler del disc.
     * @param identificadorTauler Identificador del tauler.
     * @return Dades del tauler.
     */
    public String llegirTauler(String identificadorTauler) {
        return controladorPersistenciaTauler_.llegirTauler(identificadorTauler);
    }
    /**
     * Genera un identificador per al tauler a partir de les dades del tauler i el guarda al disc, comprova primer que no existeixi ja,
     * si existeix retorna l'identificador del tauler que existeix ja.
     * @param dadesTauler Dades del tauler.
     * @return Identificador del tauler.
     */
    public String generaIdentificadorIGuardaTauler(String dadesTauler) {
        return controladorPersistenciaTauler_.generaIdentificadorIGuardaTauler(dadesTauler);
    }
    /**
     * Selecciona un tauler aleatori de la mida especificada.
     * @param mida Mida del tauler.
     * @return Nom del fitxer del tauler seleccionat o string buida si no hi ha.
     */
    public String seleccionaTaulerAleatori(int mida) {
        return controladorPersistenciaTauler_.seleccionaTaulerAleatori(mida);
    }
    // Controlador de persistencia d'usuaris
    public JsonReader getUsuari(String nomUsuari) throws FileNotFoundException {
        return controladorPersistenciaUsuari_.getUsuari(nomUsuari);
    }
    public boolean existeixUsuari(String nomUsuari) {
        return controladorPersistenciaUsuari_.existeixUsuari(nomUsuari);
    }
    public void guardarUsuari(String nomUsuari, String dadesUsuariJson) throws IOException {
        controladorPersistenciaUsuari_.guardarUsuari(nomUsuari, dadesUsuariJson);
    }
}
