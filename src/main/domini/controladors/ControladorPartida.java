package main.domini.controladors;
import main.domini.classes.Partida;
import main.domini.classes.Tauler;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistenciaPartida;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Controlador de la capa de domini que representa una partida i,
 * per tant, efectua les operacions convenients per a mantenir un correcte flux del joc.
 * Quan hi ha un joc en curs el seu atribut partida no és null, i quan no n'hi ha cap és null.<br>
 * El format d'encapsulament de dades de les partides està descrit a:<br>
 * {@link Partida#guardaPartida()} per a dades d'una partida guardada<br>
 * {@link Partida#acabaPartida()} per a dades d'una partida acabada<br>
 * {@link Partida#generaPartidaText()} per a l'estat d'una partida iniciada.<br>
 * Tot i que a memòria les partides acabades no es guarden amb exactament aquest format.
 * @see ControladorPersistenciaPartida
 * @author Nil Beascoechea Vàzquez
 */
public class ControladorPartida {
    /**
     * La partida en curs. Null si no hi ha cap partida en curs.
     * @see Partida
     */
    private Partida partida_;
    /**
     * Un mapa que relaciona tots els identificadors de les partides d'un usuari amb el seu contingut.
     * La seva funció és el ràpid accés a les partides guardades de l'usuari per si decideix carregar-ne una.
     */
    private HashMap<String, String> partidesGuardadesUsuari_;
    /**
     * Controlador de persistència de les partides. S'encarrega de guardar i carregar les partides.
     * @see ControladorPersistenciaPartida
     */
    private ControladorPersistenciaPartida controladorPersistenciaPartida_;
    /**
     * Controlador de domini dels taulers de Kenken.
     * @see CtrlTauler
     */
    private CtrlTauler controladorTauler_;
    /**
     * Constructora per defecte.
     */
    public ControladorPartida() {
        partida_ = null;
        partidesGuardadesUsuari_ = new HashMap<>();
    }
    /**
     * Setter del controlador de taulers.
     * @param controladorTauler Controlador de taulers de Kenken.
     * @return true.
     */
    public boolean setControladorTauler(CtrlTauler controladorTauler) {
        controladorTauler_ = controladorTauler;
        return true;
    }
    /**
     * Setter del controlador de persistència de les partides.
     * @param controladorPersistenciaPartida Controlador de persistència de les partides.
     * @return true.
     */
    public boolean setControladorPersistenciaPartida(ControladorPersistenciaPartida controladorPersistenciaPartida) {
        controladorPersistenciaPartida_ = controladorPersistenciaPartida;
        return true;
    }

    /**
     * Getter dels identificadors de les partides d'un usuari si s'han carregat a memòria i en té.
     * @return Retorna un vector de String amb els identificadors de les partides de l'usuari. Si no en té cap retorna un vector buit.
     */
    public String[] getPartidesGuardadesUsuari() {
        return partidesGuardadesUsuari_.keySet().toArray(new String[0]);
    }

    /**
     * Indica si hi ha cap existència en memòria d'una partida guardada amb l'identificador del tauler donat per aquell usuari.
     * @param identificadorTauler Identificador del tauler.
     * @param nomUsuari Nom de l'usuari.
     * @return Retorna true si l'usuari ha jugat aquest tauler, false si no.
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return controladorPersistenciaPartida_.haJugat(identificadorTauler, nomUsuari);
    }
    /**
     * Carrega l'última partida guardada de l'usuari. Comença la partida amb les dades guardades.
     * Primer crea la partida amb la funció {@link #stringToPartida(String, String)}.
     * Després genera el text de la partida i el tauler amb les funcions {@link Partida#generaPartidaText()} i {@link Tauler#generaTaulerText()}.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioInicialitzacioPersistenciaPartida Si no s'ha inicialitzat el controlador de persistència de les partides.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida guardada de l'usuari.
     * @throws ExcepcioPartidaEnCurs Si ja s'està jugant una partida.
     * @throws ExcepcioNoPermisUsuari Si el nom d'usuari no coincideix amb el de la partida.
     */
    public String[] carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari {
        if (controladorPersistenciaPartida_ == null) throw new ExcepcioInicialitzacioPersistenciaPartida("No s'ha inicialitzat el controlador de persistència de les partides");
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        String partida = controladorPersistenciaPartida_.carregarUltimaPartidaGuardada(nomUsuari);
        if (partida.isEmpty()) throw new ExcepcioCarregaPartida("No hi ha cap partida guardada de l'usuari " + nomUsuari);
        partida_ = stringToPartida(partida, nomUsuari);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    /**
     * Carrega totes les partides guardades de l'usuari per a un ràpid accés.
     * Primer carrega les dades de totes les partides guardades de l'usuari.
     * Després les guarda al mapa {@link #partidesGuardadesUsuari_} amb la clau de la partida com a clau i la partida com a valor.
     * @param nomUsuari Nom de l'usuari que vol carregar les partides.
     * @return Retorna un vector de String amb els identificadors de les partides de l'usuari.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida guardada de l'usuari.
     */
    public ArrayList<String> carregarPartidesGuardadesUsuari(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida {
        if (controladorPersistenciaPartida_ == null) throw new ExcepcioInicialitzacioPersistenciaPartida("No s'ha inicialitzat el controlador de persistència de les partides");
        ArrayList<String>partides = controladorPersistenciaPartida_.carregarPartidesGuardadesUsuari(nomUsuari);
        ArrayList<String> identificadorsPartidesUsuari = new ArrayList<>();
        for (String partida : partides) {
           String[] linies = partida.split("\n");
           String clau = linies[0];
           partidesGuardadesUsuari_.put(clau, partida);
           identificadorsPartidesUsuari.add(clau);
        }
        if (partidesGuardadesUsuari_.isEmpty()) throw new ExcepcioCarregaPartida("No hi ha cap partida guardada per aquest usuari");
        return identificadorsPartidesUsuari;
    }
    /**
     * Carrega una partida guardada de l'usuari segons un identificador. Comença la partida amb les dades guardades.
     * Primer carrega la partida amb la funció {@link #stringToPartida(String, String)}.
     * Després genera el text de la partida i el tauler amb les funcions {@link Partida#generaPartidaText()} i {@link Tauler#generaTaulerText()}.
     * @param identificadorPartida Identificador de la partida guardada.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioCarregaPartida Si no s'ha pogut carregar la partida amb l'identificador donat.
     * @throws ExcepcioPartidaEnCurs Si ja s'està jugant una partida.
     */
    public String[] iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs {
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        String partida = partidesGuardadesUsuari_.get(identificadorPartida);
        if (partida == null){
            throw new ExcepcioCarregaPartida("No s'ha trobat la partida amb identificador " + identificadorPartida);
        }
        partida_ = stringToPartida(partida, nomUsuari);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }

    /**
     * Comença una partida amb el tauler identificat per identificadorTauler.
     * @param identificadorTauler Identificador del tauler amb el qual es vol començar la partida.
     * @param nomUsuari Nom de l'usuari que vol començar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioCarregaTauler si no s'ha pogut carregar el tauler amb identificador identificadorTauler.
     * @throws ExcepcioPartidaEnCurs si ja s'està jugant una partida.
     */
    public String[] iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        if (controladorTauler_ == null) throw new ExcepcioInicialitzacioControladorTauler("No s'ha inicialitzat el controlador de taulers");
        Tauler tauler = controladorTauler_.carregarKenken(identificadorTauler);
        if (tauler == null) throw new ExcepcioCarregaTauler("No s'ha pogut carregar el tauler amb identificador " + identificadorTauler);
        partida_ = new Partida(nomUsuari, tauler);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    /**
     * Comença una partida amb les dades del tauler introduit.
     * @param dadesTauler Dades del tauler amb el qual es vol començar la partida.
     * @param nomUsuari Nom de l'usuari que vol començar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioCarregaTauler si no s'ha pogut crear el tauler amb les dades donades.
     * @throws ExcepcioPartidaEnCurs si ja s'està jugant una partida.
     */
    public String[] iniciaPartidaDadesTauler(String dadesTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler {
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        if (controladorTauler_ == null) throw new ExcepcioInicialitzacioControladorTauler("No s'ha inicialitzat el controlador de taulers");
        Tauler tauler = controladorTauler_.creaKenken(dadesTauler);
        if (tauler == null) throw new ExcepcioCarregaTauler("No s'ha pogut crear el tauler amb les dades donades");
        partida_ = new Partida(nomUsuari, tauler);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    /**
     * Canvia l'estat de la partida, és a dir un dels seus valors.
     * @param fila Fila de la casella a canviar.
     * @param columna Columna de la casella a canviar.
     * @param valor Valor a posar a la casella.
     * @return Retorna l'estat de la partida després de canviar la casella.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcionPosicioIncorrecta Si la posició de la casella no és correcta.
     * @throws ExcepcioValorInvalid Si el valor no és vàlid per a la casella.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     */
    public String[] introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcionPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        partida_.setValorPartida(fila, columna, valor);
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }

    /** Funció que canvia l'estat de la partida afegint un valor que portaria a una solució,
     * o indica si el que hi ha fins ara no pot portar a una solució.
     * @return Retorna l'estat de la partida després de canviar la casella.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
      */
    public String[] donaPista() throws ExcepcioCarregaPartida {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        Int solucioTotal = controladorTauler_.resolKenken(partida_.getTaulerPartida(), partida_.getValorsPartida());
        boolean posat = false;
        while (!posat){
            int fila = (int) (Math.random() * partida_.getTaulerPartida().getGrau());
            int columna = (int) (Math.random() * partida_.getTaulerPartida().getGrau());
            if (partida_.getValorsPartida()[fila][columna] == 0){
                partida_.setValorPartida(fila, columna, solucioTotal[fila][columna]);
                posat = true;
            }
        }
        partida_.setGuardadaPartida();
        String partidaText = partida_.generaPartidaText();
        String taulerText = partida_.getTaulerPartida().generaTaulerText();
        return new String[]{partidaText, taulerText};
    }
    /**
     * Guarda la partida en curs del controlador. L'afegeix també al mapa de partides guardades de l'usuari.
     * @param nomUsuari Nom de l'usuari que vol guardar la partida.
     * @return true si s'ha guardat la partida. false si no s'ha guardat la partida.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada o el nom d'usuari no coincideix amb la partida a guardar.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     */
    public boolean guardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        if (!partida_.getUsuariPartida().equals(nomUsuari)) throw new ExcepcioNoPermisUsuari("El nom d'usuari no coincideix amb el de la partida");
        String identificadorPartida = partida_.getIdentificadorPartida();
        String dadesPartida = partida_.guardaPartida();
        partidesGuardadesUsuari_.put(identificadorPartida, dadesPartida);
        return controladorPersistenciaPartida_.guardarPartida(dadesPartida);
    }
    /**
     * Tanca i guarda la partida en curs del controlador. L'afegeix també al mapa de partides guardades de l'usuari.
     * @return true si s'ha tancat i guardat la partida. false si no s'ha guardat la partida, i per tant no es tanca.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     */
    public boolean tancarIguardarPartida() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        String identificadorPartida = partida_.getIdentificadorPartida();
        String dadesPartida = partida_.tancaIGuardaPartida();
        partidesGuardadesUsuari_.put(identificadorPartida, dadesPartida);
        boolean guardada = controladorPersistenciaPartida_.guardarPartida(dadesPartida);
        if (guardada) tancaPartida();
        return guardada;
    }
    /**
     * Acaba la partida en curs del controlador. Una partida acabada és aquella que està ben resolta.
     * @return vector de 3 strings. La primera indica si s'ha guardat la partida a memòria. La segona si havia estat guardada prèviament. La tercera el temps que ha durat la partida.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaMalament Si la partida no s'ha pogut acabar per que és incorrecta.
     * @throws ExcepcioPartidaAcabada Si la partida ja està acabada.
     */
    public String[] acabarPartida() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        boolean haviaEstatGuardada = partida_.getGuardadaPartida();
        String dataPartida = partida_.acabaPartida();
        String temps = dataPartida.split("\n")[3];
        boolean guardadaCorrectament = controladorPersistenciaPartida_.arxivarPartida(dataPartida);
        if (guardadaCorrectament) tancaPartida();
        return new String[]{String.valueOf(guardadaCorrectament), String.valueOf(haviaEstatGuardada), temps};
    }
    /**
     * Tanca la partida en curs del controlador.
     * @return true
     */
    public boolean tancaPartida(){
        partida_ = null;
        return true;
    }
    /**
     * Funció privada que transforma una string de dades d'una partida en una instància de {@code Partida}.
     * @param partida Dades de la partida seguint el format {@link Partida#guardaPartida()}.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna una instància de {@code Partida} amb les dades de la string.
     * @throws ExcepcioCarregaPartida Si el nom d'usuari no coincideix amb el de la partida.
     */
    private Partida stringToPartida(String partida, String nomUsuari) throws ExcepcioNoPermisUsuari, ExcepcioCarregaPartida {
        String[] divisio = partida.split("\n");
        String identificadorPartida = divisio[0];
        String[] parts = identificadorPartida.split(":");
        String identificadorUsuariPartida = parts[0];
        if (!identificadorUsuariPartida.equals(nomUsuari)) throw new ExcepcioNoPermisUsuari("El nom d'usuari no coincideix amb el de la partida");
        String identificadorTaulerPartida = divisio[1];
        int tempsPartida = Integer.parseInt(divisio[2]);
        int midaPartida = Integer.parseInt(divisio[3]);
        int [][] valorsPartida = new int[midaPartida][midaPartida];
        for (int i = 0; i < midaPartida; i++) {
            String[] valors = divisio[4 + i].split(" ");
            for (int j = 0; j < midaPartida; j++) {
                valorsPartida[i][j] = Integer.parseInt(valors[j]);
            }
        }
        Tauler tauler = controladorTauler_.carregarKenken(identificadorTaulerPartida);
        if (tauler.getGrau() != midaPartida) throw new ExcepcioCarregaPartida("La mida de la partida guardada no coincideix amb la mda del seu tauler" );
        return new Partida(identificadorPartida, identificadorUsuariPartida, tauler, tempsPartida, valorsPartida);
    }

}
