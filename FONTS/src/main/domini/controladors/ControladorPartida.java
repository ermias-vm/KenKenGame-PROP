package main.domini.controladors;
import main.domini.classes.Partida;
import main.domini.classes.Regio;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistenciaPartida;
import main.domini.classes.Tauler;

import java.util.*;

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
     * Pila que guarda els moviments fets a la partida per a poder desfer-los.
     * Un moviment es representa com un vector d'enters de 3 posicions: fila, columna i el valor anterior al moviment.
     */
    private final Stack<int[]> desferMoviments_ = new Stack<>();
    /**
     * Pila que guarda els moviments desfets a la partida per a poder refer-los.
     * Un moviment es representa com un vector d'enters de 3 posicions: fila, columna i el valor anterior al moviment a refer.
     */
    private final Stack<int[]> referMoviments_ = new Stack<>();
    /**
     * Instància del controlador de domini.
     */
    private CtrlDomini controladorDomini_;

    /**
     * Instància del controlador de partida.
     */
    private static ControladorPartida controladorPartida_;
    /**
     * Constructora per defecte.
     */
    private ControladorPartida() {
        partidesGuardadesUsuari_ = new HashMap<>();
        partida_ = null;
    }

    /**
     * Getter de la instància del controlador de partida.
     * @return Retorna la instància del controlador de partida.
     */
    public static ControladorPartida getInstance() {
        if (controladorPartida_ == null) controladorPartida_ = new ControladorPartida();
        return new ControladorPartida();
    }

    /**
     * Setter del controlador de domini.
     * @param controladorDomini Controlador de domini.
     */
    public void setControladorDomini(CtrlDomini controladorDomini) {
        this.controladorDomini_ = controladorDomini;
    }

    /**
     * Getter dels identificadors de les partides d'un usuari si s'han carregat a memòria i en té.
     * @return Retorna un vector de String amb els identificadors de les partides de l'usuari. Si no en té cap retorna un vector buit.
     */
    public String[] getPartidesGuardadesUsuari() {
        return partidesGuardadesUsuari_.keySet().toArray(new String[0]);
    }

    /**
     * Getter de l'estat de la partida en curs.
     * @return L'estat dels valors de la partida com a matriu d'enters.
     */
    public int[][] getValorsPartida(){
        return partida_.getValorsPartida();
    }
    /**
     * Indica si hi ha cap existència en memòria d'una partida guardada amb l'identificador del tauler donat per aquell usuari.
     * @param identificadorTauler Identificador del tauler.
     * @param nomUsuari Nom de l'usuari.
     * @return Retorna true si l'usuari ha jugat aquest tauler, false si no.
     */
    public boolean haJugat(String identificadorTauler, String nomUsuari) {
        return controladorDomini_.haJugatPersistencia(identificadorTauler, nomUsuari);
    }
    public int getMidaPartida(){
        return partida_.getTaulerPartida().getGrau();
    }
    /**
     * Carrega l'última partida guardada de l'usuari. Comença la partida amb les dades guardades.
     * Primer crea la partida amb la funció {@link #stringToPartida(String, String)}.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioInicialitzacioPersistenciaPartida Si no s'ha inicialitzat el controlador de persistència de les partides.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida guardada de l'usuari.
     * @throws ExcepcioPartidaEnCurs Si ja s'està jugant una partida.
     * @throws ExcepcioNoPermisUsuari Si el nom d'usuari no coincideix amb el de la partida.
     */
    public String carregarUltimaPartidaGuardada(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioInicialitzacioPersistenciaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida{
        if (controladorDomini_ == null) throw new ExcepcioInicialitzacioPersistenciaPartida("No s'ha inicialitzat el controlador de persistència de les partides");
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        String partida = controladorDomini_.carregarUltimaPartidaGuardadaPersistencia(nomUsuari);
        if (partida.isEmpty()) throw new ExcepcioCarregaPartida("No hi ha cap partida guardada de l'usuari " + nomUsuari);
        partida_ = stringToPartida(partida, nomUsuari);
        String partidaText = partida_.generaPartidaText();
        return partidaText;
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
        if (controladorDomini_ == null) throw new ExcepcioInicialitzacioPersistenciaPartida("No s'ha inicialitzat el controlador de persistència de les partides");
        ArrayList<String>partides = controladorDomini_.carregarPartidesGuardadesUsuariPersistencia(nomUsuari);
        ArrayList<String> identificadorsPartidesUsuari = new ArrayList<>();
        for (String partida : partides) {
            String[] linies = partida.split("\n");
            String clau = linies[0];
            partidesGuardadesUsuari_.put(clau, partida);
            identificadorsPartidesUsuari.add(clau);
        }
        if (partidesGuardadesUsuari_.isEmpty()) throw new ExcepcioCarregaPartida("No hi ha cap partida guardada per aquest usuari");
        return partides;
    }
    /**
     * Carrega una partida guardada de l'usuari segons un identificador. Comença la partida amb les dades guardades.
     * Primer carrega la partida amb la funció {@link #stringToPartida(String, String)}.
     * @param identificadorPartida Identificador de la partida guardada.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioCarregaPartida Si no s'ha pogut carregar la partida amb l'identificador donat.
     * @throws ExcepcioPartidaEnCurs Si ja s'està jugant una partida.
     */
    public String iniciarPartidaGuardada(String identificadorPartida, String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaEnCurs, ExcepcioNoPermisUsuari, ExcepcioCreacioPartida{
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        String partida = partidesGuardadesUsuari_.get(identificadorPartida);
        if (partida == null) {
            throw new ExcepcioCarregaPartida("No s'ha trobat la partida amb identificador " + identificadorPartida);
        }
        partida_ = stringToPartida(partida, nomUsuari);
        String partidaText = partida_.generaPartidaText();
        return partidaText;
    }

    /**
     * Comença una partida amb el tauler identificat per identificadorTauler.
     * @param identificadorTauler Identificador del tauler amb el qual es vol començar la partida.
     * @param nomUsuari Nom de l'usuari que vol començar la partida.
     * @return Retorna un vector de String amb l'estat de la partida a l'índex [0] i les dades del tauler a l'índex [1].
     * @throws ExcepcioCarregaTauler si no s'ha pogut carregar el tauler amb identificador identificadorTauler.
     * @throws ExcepcioPartidaEnCurs si ja s'està jugant una partida.
     * @throws ExcepcioInicialitzacioControladorTauler si no s'ha inicialitzat el controlador de taulers.
     */
    public String iniciaPartidaIdentificadorTauler(String identificadorTauler, String nomUsuari) throws ExcepcioCarregaTauler, ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler{
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        if (controladorDomini_ == null) throw new ExcepcioInicialitzacioControladorTauler("No s'ha inicialitzat el controlador de taulers");
        try {
            Tauler tauler = controladorDomini_.llegirTauler(identificadorTauler);
            partida_ = new Partida(nomUsuari, tauler);
            if (haJugat(identificadorTauler, nomUsuari)) partida_.setGuardadaPartida();
            String partidaText = partida_.generaPartidaText();
            return partidaText;
        } catch (ExcepcioTaulerNoExisteix e) {
            throw new ExcepcioCarregaTauler("No s'ha pogut carregar el tauler amb identificador " + identificadorTauler);
        }
    }

    /**
     * Comença una partida amb un tauler aleatori de mida mida.
     * Primer busca a memòria si hi ha taulers que l'usuari no hagi jugat.
     * Si no n'hi ha en crea un automàticament.
     * @param mida Mida del tauler amb el qual es vol començar la partida.
     * @param nomUsuari Nom de l'usuari que vol començar la partida.
     * @return Retorna un String amb l'estat de la partida.
     * @throws ExcepcioPartidaEnCurs si ja s'està jugant una partida.
     * @throws ExcepcioInicialitzacioControladorTauler si no s'ha inicialitzat el controlador de taulers.
     */
    public String iniciaPartidaAleatoria(int mida, String nomUsuari) throws ExcepcioPartidaEnCurs, ExcepcioInicialitzacioControladorTauler, ExcepcioNoPartidaAleatoria {
        if (partida_ != null) throw new ExcepcioPartidaEnCurs("S'està jugant una partida en aquest moment");
        if (controladorDomini_ == null) throw new ExcepcioInicialitzacioControladorTauler("No s'ha inicialitzat el controlador de taulers");
        String identificadorTauler;
        do{
            identificadorTauler = controladorDomini_.seleccionaTaulerAleatoriPersistencia(mida);
        }
        while (haJugat(identificadorTauler, nomUsuari) && identificadorTauler != null);
        if (identificadorTauler == null) throw new ExcepcioNoPartidaAleatoria();
        try {
            Tauler tauler = controladorDomini_.llegirTauler(identificadorTauler);
            partida_ = new Partida(nomUsuari, tauler);
            String partidaText = partida_.generaPartidaText();
            return partidaText;
        } catch (ExcepcioTaulerNoExisteix e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Canvia l'estat de la partida, és a dir un dels seus valors.
     * @param fila Fila de la casella a canviar.
     * @param columna Columna de la casella a canviar.
     * @param valor Valor a posar a la casella.
     * @return Retorna l'estat de la partida després de canviar la casella.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcioPosicioIncorrecta Si la posició de la casella no és correcta.
     * @throws ExcepcioValorInvalid Si el valor no és vàlid per a la casella.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     */
    public String introduirValor(int fila, int columna, int valor) throws ExcepcioCarregaPartida, ExcepcioPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaTancada, ExcepcioPartidaAcabada {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        int valor_anterior = partida_.getValorPartida(fila, columna);
        partida_.setValorPartida(fila, columna, valor);
        desferMoviments_.push(new int[]{fila, columna, valor_anterior});
        referMoviments_.clear();
        String partidaText = partida_.generaPartidaText();
        return partidaText;
    }
    /**
     * Desfà l'últim moviment fet a la partida.
     * @return Retorna l'estat de la partida després de desfer el moviment.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioValorInvalid Si el valor de la casella no és vàlid.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     * @throws ExcepcioPosicioIncorrecta Si la posició de la casella no és correcta.
     * @throws ExcepcioDoUndo Si no hi ha cap moviment a desfer.
     */
    public String desferMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        if (desferMoviments_.isEmpty()) throw new ExcepcioDoUndo("No hi ha cap moviment a desfer.");
        int[] moviment = desferMoviments_.pop();
        referMoviments_.push(new int[]{moviment[0], moviment[1], partida_.getValorPartida(moviment[0], moviment[1])});
        partida_.setValorPartida(moviment[0], moviment[1], moviment[2]);
        return  partida_.generaPartidaText();
    }

    /**
     * Refà un moviment desfet a la partida.
     * @return Retorna l'estat de la partida després de refer el moviment.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioValorInvalid Si el valor de la casella no és vàlid.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     * @throws ExcepcioPosicioIncorrecta Si la posició de la casella no és correcta.
     * @throws ExcepcioDoUndo Si no hi ha cap moviment a refer.
     */
    public String referMoviment() throws ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioDoUndo {
        if (referMoviments_.isEmpty()) throw new ExcepcioDoUndo("No hi ha cap moviment a refer.");
        int[] moviment = referMoviments_.pop();
        desferMoviments_.push(new int[]{moviment[0], moviment[1], partida_.getValorPartida(moviment[0], moviment[1])});
        partida_.setValorPartida(moviment[0], moviment[1], moviment[2]);
        return partida_.generaPartidaText();
    }
    /** Funció que canvia l'estat de la partida afegint un valor que portaria a una solució,
     * o indica si el que hi ha fins ara no pot portar a una solució.
     * @return Retorna l'estat de la partida després de canviar la casella.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioValorInvalid Si el valor de la casella no és vàlid.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     * @throws ExcepcioPosicioIncorrecta Si la posició de la casella no és correcta.
     * @throws ExcepcioCasellaNoExisteix Si la casella no existeix.
     * @throws ExcepcioCasellaNoModificable Si la casella no es pot modificar.
     * @throws ExcepcioNoDivisor Si no es pot dividir entre algun valor amb un altre.
     * @throws ExcepcioMoltsValors Si el nombre de valors no es correspon amb la regió.
     * @throws ExcepcioDivisio_0 Si s'intenta dividir entre 0.
     */
    public ArrayList<int[]> donaPista() throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPosicioIncorrecta, ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        ArrayList<int[]> valorsRepetits = comprovaRepetits(partida_.getValorsPartida());
        if (!valorsRepetits.isEmpty()){
            return valorsRepetits;
        }
        ArrayList<Regio> valorsIncorrectes = partida_.getTaulerPartida().getRegionsIncorrectes(partida_.getValorsPartida());
        Regio[] regionsIncorrectes = valorsIncorrectes.toArray(new Regio[0]);
        for (Regio r : regionsIncorrectes){
            int[][] posicions = r.getPosicionsCaselles();
            for ( int[] posicio : posicions){
                int fila = posicio[0]-1;
                int columna = posicio[1]-1;
                if (partida_.getValorsPartida()[fila][columna] == 0){
                    valorsIncorrectes.remove(r);
                    break;
                }
            }
        }
        if (!valorsIncorrectes.isEmpty()){
            int longitud = valorsIncorrectes.size();
            int index = (int) (Math.random() * longitud);
            Regio regioIncorrecta = valorsIncorrectes.get(index);
            int[][] posicions = regioIncorrecta.getPosicionsCaselles();
            ArrayList<int[]> valorsIncorrectesRegio = new ArrayList<>();
            for (int i = 0; i < posicions.length; ++i){
                int fila = posicions[i][0]-1;
                int columna = posicions[i][1]-1;
                valorsIncorrectesRegio.add(new int[]{fila, columna});
            }
            return valorsIncorrectesRegio;
        }
        int[][] solucioTotal = controladorDomini_.resoldreKenken(partida_.getTaulerPartida(), partida_.getValorsPartida());
        boolean acabada = true;
        for (int i = 0; i < partida_.getTaulerPartida().getGrau(); ++i){
            for (int j = 0; j < partida_.getTaulerPartida().getGrau(); ++j){
                if (partida_.getValorsPartida()[i][j] != solucioTotal[i][j]){
                    acabada = false;
                    break;
                }
            }
        }
        if (acabada){
            partida_.setGuardadaPartida();
            return new ArrayList<>();
        }
        if (solucioTotal == null) throw new ExcepcioPartidaAcabada("La partida no té solució");
        boolean posat = false;
        while (!posat){
            int fila = (int) (Math.random() * partida_.getTaulerPartida().getGrau());
            int columna = (int) (Math.random() * partida_.getTaulerPartida().getGrau());
            if (partida_.getValorsPartida()[fila][columna] == 0){
                introduirValor(fila, columna, solucioTotal[fila][columna]);
                posat = true;
            }
        }
        partida_.setGuardadaPartida();
        return new ArrayList<>();
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
        return controladorDomini_.guardarPartidaPersistencia(dadesPartida);
    }
    /**
     * Tanca i guarda la partida en curs del controlador. L'afegeix també al mapa de partides guardades de l'usuari.
     * @return true si s'ha tancat i guardat la partida. false si no s'ha guardat la partida, i per tant no es tanca.
     * @throws ExcepcioCarregaPartida Si no hi ha cap partida carregada.
     * @throws ExcepcioPartidaTancada Si la partida està tancada.
     * @throws ExcepcioPartidaAcabada Si la partida està acabada.
     */
    public boolean tancarIguardarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaAcabada, ExcepcioNoPermisUsuari {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        if (!partida_.getUsuariPartida().equals(nomUsuari)) throw new ExcepcioNoPermisUsuari("El nom d'usuari no coincideix amb el de la partida");
        desferMoviments_.clear();
        referMoviments_.clear();
        String identificadorPartida = partida_.getIdentificadorPartida();
        String dadesPartida = partida_.tancaIGuardaPartida();
        partidesGuardadesUsuari_.put(identificadorPartida, dadesPartida);
        boolean guardada = controladorDomini_.guardarPartidaPersistencia(dadesPartida);
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
    public String[] acabarPartida(String nomUsuari) throws ExcepcioCarregaPartida, ExcepcioPartidaTancada, ExcepcioPartidaMalament, ExcepcioPartidaAcabada, ExcepcioCasellaNoExisteix, ExcepcioNoPermisUsuari {
        if (partida_ == null) throw new ExcepcioCarregaPartida("No hi ha cap partida carregada");
        if (!partida_.getUsuariPartida().equals(nomUsuari)) throw new ExcepcioNoPermisUsuari("El nom d'usuari no coincideix amb el de la partida");
        String dataPartida = partida_.acabaPartida();
        boolean haviaEstatGuardada = partida_.getGuardadaPartida();
        referMoviments_.clear();
        desferMoviments_.clear();
        String temps = dataPartida.split("\n")[3];
        boolean guardadaCorrectament = controladorDomini_.arxivarPartidaPersistencia(dataPartida);
        if (guardadaCorrectament){
            tancaPartida();
            if (!haviaEstatGuardada) controladorDomini_.afegirPartida(dataPartida);
        }
        return new String[]{String.valueOf(guardadaCorrectament), String.valueOf(haviaEstatGuardada), temps};
    }
    /**
     * Tanca la partida en curs del controlador.
     * @return true
     */
    public boolean tancaPartida(){
        partida_ = null;
        referMoviments_.clear();
        desferMoviments_.clear();
        return true;
    }
    /**
     * Funció privada que transforma una string de dades d'una partida en una instància de {@code Partida}.
     * @param partida Dades de la partida seguint el format {@link Partida#guardaPartida()}.
     * @param nomUsuari Nom de l'usuari que vol carregar la partida.
     * @return Retorna una instància de {@code Partida} amb les dades de la string.
     * @throws ExcepcioCarregaPartida Si el nom d'usuari no coincideix amb el de la partida.
     */
    private Partida stringToPartida(String partida, String nomUsuari) throws ExcepcioNoPermisUsuari, ExcepcioCarregaPartida, ExcepcioCreacioPartida {
        String[] divisio = partida.split("\n");
        String identificadorPartida = divisio[0];
        String[] parts = identificadorPartida.split(":");
        String identificadorUsuariPartida = parts[0];
        if (!identificadorUsuariPartida.equals(nomUsuari)) throw new ExcepcioNoPermisUsuari("El nom d'usuari no coincideix amb el de la partida");
        String identificadorTaulerPartida = divisio[1];
        float tempsPartida = Float.parseFloat(divisio[2]);
        int midaPartida = Integer.parseInt(divisio[3]);
        int [][] valorsPartida = new int[midaPartida][midaPartida];
        for (int i = 0; i < midaPartida; i++) {
            String[] valors = divisio[4 + i].split(" ");
            for (int j = 0; j < midaPartida; j++) {
                valorsPartida[i][j] = Integer.parseInt(valors[j]);
            }
        }
        try {
            Tauler tauler = controladorDomini_.llegirTauler(identificadorTaulerPartida);
            if (tauler.getGrau() != midaPartida)
                throw new ExcepcioCarregaPartida("La mida de la partida guardada no coincideix amb la mda del seu tauler");
            return new Partida(identificadorPartida, identificadorUsuariPartida, tauler, tempsPartida, valorsPartida);
        }
        catch (ExcepcioTaulerNoExisteix e) {
            throw new ExcepcioCarregaPartida("No s'ha pogut carregar el tauler de la partida guardada");
        }
    }

    /**
     * Comprova si hi ha valors repetits a les files i columnes del tauler.
     * @param valors Matriu de valors del tauler.
     * @return Retorna un vector d'enters amb les posicions de les caselles amb valors repetits.
     */
    private ArrayList<int[]> comprovaRepetits(int[][] valors) {
        HashSet<int[]> valorsRepetits = new HashSet<>();
        int mida_ = valors.length;
        boolean[][] valorsUtilitzatsFila = new boolean[mida_][mida_];
        boolean[][] valorsUtilitzatsColumna = new boolean[mida_][mida_];
        for (int i = 0; i < mida_; i++) {
            for (int j = 0; j < mida_; j++) {
                if (valors[i][j] != 0) {
                    if (valorsUtilitzatsFila[i][valors[i][j] - 1]) {
                        valorsRepetits.add(new int[]{i, j});
                        for (int k = 0; k < mida_; k++) {
                            if (valors[i][k] == valors[i][j]) valorsRepetits.add(new int[]{i, k});
                        }
                    }
                    valorsUtilitzatsFila[i][valors[i][j] - 1] = true;
                    if (valorsUtilitzatsColumna[j][valors[i][j] - 1]) {
                        valorsRepetits.add(new int[]{i, j});
                        for (int k = 0; k < mida_; k++) {
                            if (valors[k][j] == valors[i][j]) valorsRepetits.add(new int[]{k, j});
                        }
                    }
                    valorsUtilitzatsColumna[j][valors[i][j] - 1] = true;
                    }
                }
            }
        ArrayList<int[]> valorsRepetitsLlista = new ArrayList<>(valorsRepetits);
        return valorsRepetitsLlista;
    }

    /**
     * Genera el mapa d'adjacencies del tauler de la partida.
     * @return Retorna el mapa d'adjacencies del tauler de la partida.
     */
    public ArrayList<Boolean>[][] getAdjacentsPartida() {
        return partida_.getTaulerPartida().getAdjacents();
    }

    public ArrayList<String> getOperacionsPartida() {
        return partida_.getTaulerPartida().getOperacions();
    }
    public float getTempsPartida(){
        return partida_.getTempsPartida();
    }
}
