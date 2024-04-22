package test;

import main.stubs.TaulerStub;
import main.domini.excepcions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe {@code Partida} representa una partida de Kenken d'un usuari amb un cert tauler.
 * Ve determinada per un identificador únic creat a partir de l'usuari i el moment de creació de la partida.<br>
 * Conté la data d'inici de la instanciació actual, el tauler de la partida, la seva mida per a tenir accés rapid a ella,
 * l'identificador de l'usuari que l'ha creada, els valors de les caselles de la partida en un instant,
 * el temps que s'ha estat jugant, i si la partida està guardada, acabada o tancada.<br>
 * Format de la informació d'una partida acabada {@link #acabaPartida()}.<br>
 * Format de la informació d'una partida guardada: {@link #guardaPartida()}.
 * @author Nil Beascoechea Vàzquez
 */
public class Partida_per_a_test {
    /**
     * Identificador de la partida, creat a partir de l'usuari i el moment de creació de la partida.
     * El format és "identificadorUsuariPartida:yyyy-mm-ddThh:mm:ss".
     */
    private final String identificadorPartida_;
    /**
     * Data d'inici de la instanciació actual.
     */
    private final LocalDateTime iniciPartida_;
    /**
     * Grau del tauler de la partida, és a dir la N d'un tauler NxN.
     */
    private final int grauPartida_;
    /**
     * Identificador de l'usuari que ha creat la partida.
     */
    private final String identificadorUsuariPartida_;
    /**
     * Tauler de la partida.
     */
    private final TaulerStub taulerPartida_;
    /**
     * Valors de les caselles de la partida en un instant com a una matriu d'enters.
     */
    private int[][] valorsPartida_;
    /**
     * Temps que s'ha estat jugant.
     */
    private float tempsPartida_;
    /**
     * Indica si la partida està guardada. Es guardarà a l'arxiu de partides guardades.
     */
    private boolean guardadaPartida_ = false;
    /**
     * Indica si la partida està acabada. Només es pot acabar una partida si està correctament resolta.
     * No es poden fer més modificacions a la partida i es guardarà a l'arxiu de partides acabades.
     */
    private boolean acabadaPartida_ = false;
    /**
     * Indica si la partida està tancada. Només es pot tancar una partida si està acabada.
     * No es poden fer més modificacions a la partida.
     */
    private boolean tancadaPartida_ = false;

    /**
     * Crea una nova partida amb un identificador d'usuari i un tauler donats.
     * Utilitzada en crear una partida totalment nova.
     * @param identificadorUsuariPartida Identificador de l'usuari que ha creat la partida.
     * @param TaulerPartida Tauler de la partida.
     */
    public Partida_per_a_test(String identificadorUsuariPartida, TaulerStub TaulerPartida) {

        this.iniciPartida_ = LocalDateTime.now();
        int grauPartida = TaulerPartida.getGrau();
        this.identificadorUsuariPartida_ = identificadorUsuariPartida;
        this.taulerPartida_ = TaulerPartida;
        this.grauPartida_ = grauPartida;
        this.identificadorPartida_ = creaIdentificadorPartida() ;
        this.valorsPartida_ = new int[grauPartida][grauPartida]; //per defecte a 0
        this.tempsPartida_ = 0;
    }
    /**
     * Crea una nova partida amb un identificador d'usuari, un tauler, un temps i uns valors donats.
     * Utilitzat per carregar una partida guardada.
     * @param identificadorUsuariPartida identificador de l'usuari que ha creat la partida.
     * @param TaulerPartida Tauler de la partida.
     * @param tempsPartida Temps que s'ha estat jugant.
     * @param valorsPartida Valors de les caselles de la partida en un instant.
     */
    //Per a carregar una partida guardada, parametres donats pel controlador guardar i carregar i s'ocupa de comprovar que no estigui ja carregada
    public Partida_per_a_test(String identificadorPartida, String identificadorUsuariPartida, TaulerStub TaulerPartida, float tempsPartida, int[][] valorsPartida) throws ExcepcioCreacioPartida {

        this.iniciPartida_ = LocalDateTime.now();
        if (TaulerPartida.getGrau() != valorsPartida.length) {
            throw new ExcepcioCreacioPartida("El grau del tauler i la mida de la matriu de valors no coincideixen.");
        }
        if (!identificadorPartida.contains(identificadorUsuariPartida+":")) {
            throw new ExcepcioCreacioPartida("L'identificador de la partida no coincideix amb l'usuari.");
        }
        this.identificadorUsuariPartida_ = identificadorUsuariPartida;
        this.taulerPartida_ = TaulerPartida;
        this.grauPartida_ = TaulerPartida.getGrau();
        this.identificadorPartida_ = identificadorPartida ;
        this.valorsPartida_ = valorsPartida; //per defecte a 0
        this.tempsPartida_ = tempsPartida;
        this.guardadaPartida_ = true;
    }

    //Getters
    /**
     * Retorna la data d'inici de la instanciació actual.
     * @return Data d'inici de la instanciació actual.
     */
    public LocalDateTime getiniciPartida() {
        return iniciPartida_;
    }
    /**
     * Retorna l'identificador de la partida.
     * @return Identificador de la partida.
     */
    public String getIdentificadorPartida() {
        return identificadorPartida_;
    }
    /**
     * Retorna el grau del tauler de la partida.
     * @return Grau del tauler de la partida.
     */
    public int getGrauPartida() {
        return grauPartida_;
    }
    /**
     * Retorna l'identificador de l'usuari que ha creat la partida.
     * @return Identificador de l'usuari que ha creat la partida.
     */
    public String getUsuariPartida() {
        return identificadorUsuariPartida_;
    }
    /**
     * Retorna el tauler de la partida.
     * @return Tauler de la partida.
     */
    public TaulerStub getTaulerPartida(){
        return taulerPartida_;
    }
    /**
     * Retorna l'identificador del tauler de la partida.
     * @return Identificador del tauler de la partida.
     */
    public String getIdentificadorTaulerPartida() {
        return String.valueOf(taulerPartida_.getIdentificador());
    }
    /**
     * Retorna el valor d'una casella de la partida.
     * @param fila Fila de la casella.
     * @param columna Columna de la casella.
     * @return Valor de la casella de la partida.
     */
    public int getValorPartida(int fila, int columna) {
        return valorsPartida_[fila][columna];
    }
    /**
     * Retorna els valors de les caselles de la partida en un instant.
     * @return Valors de les caselles de la partida en un instant.
     */
    public int[][] getValorsPartida() {
        return valorsPartida_;
    }
    /**
     * Retorna el temps que s'ha estat jugant.
     * Per no haver d'anar actualitzant el temps periòdicament, es calcula cada vegada que es necessita,
     *és a dir, quan es vol guardar, acabar o tancar la partida.
     * @return Temps que s'ha estat jugant.
     */
    public float getTempsPartida() {
        return tempsPartida_;
    }
    /**
     * Retorna si la partida està guardada.
     * @return true si la partida està guardada, false altrament
     */
    public boolean getGuardadaPartida() {
        return guardadaPartida_;
    }
    /**
     * Retorna si la partida està acabada.
     * @return true si la partida està acabada, false altrament
     */
    public boolean getAcabadaPartida() {
        return acabadaPartida_;
    }
    /**
     * Retorna si la partida està tancada.
     * @return true si la partida està tancada, false altrament
     */
    public boolean getTancadaPartida() {
        return tancadaPartida_;
    }

    /**
     * Estableix un valor a una casella de la partida.
     * @param valor
     * @param fila
     * @param columna
     * @return true si s'ha pogut establir el valor, llença una excepció altrament.
     * @throws ExcepcionPosicioIncorrecta si la fila o la columna no són vàlides. És a dir si no estan entre 0 i el grau del tauler-1.
     * @throws ExcepcioValorInvalid si el valor no és vàlid. És a dir si no és un valor entre 1 i el grau del tauler.
     * @throws ExcepcioPartidaAcabada si la partida ja està acabada.
     * @throws ExcepcioPartidaTancada si la partida està tancada.
     */
    public boolean setValorPartida(int fila, int columna, int valor) throws ExcepcionPosicioIncorrecta, ExcepcioValorInvalid, ExcepcioPartidaAcabada, ExcepcioPartidaTancada {
        if (tancadaPartida_) {
            throw new ExcepcioPartidaTancada();
        }
        if (acabadaPartida_) {
            throw new ExcepcioPartidaAcabada();
        }
        if (fila < 0 || fila >= grauPartida_ || columna < 0 || columna >= grauPartida_) {
            throw new ExcepcionPosicioIncorrecta();
        }
        if (0 <= valor && valor <= grauPartida_) {
            valorsPartida_[fila][columna] = valor;
            return true;
        }
        else throw new ExcepcioValorInvalid();
    }
    public boolean setGuardadaPartida() {
        this.guardadaPartida_ = true;
        return true;
    }
    /**
     * Acaba la partida si està correctament resolta.
     * El format de la informació de la partida acabada és (sense les línies amb |||||):<br>
     * ||||||FORMAT DADES PARTIDA ACABADA||||||<br>
     * Identificador de la partida<br>
     * Identificador de l'usuari<br>
     * Identificador del tauler<br>
     * Temps total de la partida<br>
     * Grau del tauler<br>
     * Si ha estat guardada o no<br>
     * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||<br>
     * Per tant, cada partida acabada ocupa 6 línies.
     * No es guarda a memòria secundària amb aquest format es guarda
     * amb el format vist a {@link main.persistencia.ControladorPersistenciaPartida#arxivarPartida(String)}
     * @return Text amb la informació de la partida acabada per a emmagatzemar-la a partides acabades.
     * @throws ExcepcioPartidaAcabada si la partida ja està acabada.
     * @throws ExcepcioPartidaTancada si la partida està tancada.
     * @throws ExcepcioPartidaMalament si la partida no està correctament resolta.
     */
    public String acabaPartida() throws ExcepcioPartidaAcabada, ExcepcioPartidaTancada, ExcepcioPartidaMalament {
        if (acabadaPartida_) {
            throw new ExcepcioPartidaAcabada();
        }
        if (tancadaPartida_) {
            throw new ExcepcioPartidaTancada();
        }
        if (taulerPartida_.corretgeix(valorsPartida_)) {
            this.acabadaPartida_ = true;
            String textPartidaAcabada = this.identificadorPartida_ + '\n' + this.identificadorUsuariPartida_ + '\n' + this.getIdentificadorTaulerPartida() + '\n' + this.calculaTemps() + '\n' + this.grauPartida_ + '\n' + this.guardadaPartida_;
            return textPartidaAcabada;
        }
        else throw new ExcepcioPartidaMalament();
    }
    /**
     * Tanca i guarda la partida. Genera un text amb el format descrit a {@link #guardaPartida()}.
     * @return Text amb la informació de la partida per a emmagatzemar-la a partides guardades.
     * @throws ExcepcioPartidaAcabada si la partida ja està acabada.
     * @throws ExcepcioPartidaTancada si la partida ja està tancada.
     */
    public String tancaIGuardaPartida() throws ExcepcioPartidaAcabada, ExcepcioPartidaTancada {
        if (acabadaPartida_) {
            throw new ExcepcioPartidaAcabada();
        }
        if (tancadaPartida_) {
            throw new ExcepcioPartidaTancada();
        }
        this.tancadaPartida_ = true;
        this.guardadaPartida_ = true;
        this.tempsPartida_ = this.calculaTemps();
        return generaPartidaGuardadaText();
    }
    /**
     * Guarda la partida.
     * Per a encapsular una partida guardada. El format és (sense les línies amb |||||):<br>
     * ||||||FORMAT DADES PARTIDA GUARDADA||||||<br>
     * Identificador de la partida<br>
     * Identificador del tauler<br>
     * Temps total de la partida<br>
     * Grau del tauler<br>
     * Valors de les caselles de la partida separats per espais en files i per salts de línia en columnes.<br>
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||<br>
     * És a dir ocuparà 4 + Grau línies.
     * @return Text amb la informació de la partida per a emmagatzemar-la a partides guardades.
     * @throws ExcepcioPartidaAcabada si la partida ja està acabada.
     * @throws ExcepcioPartidaTancada si la partida ja està tancada.
     */
    public String guardaPartida() throws ExcepcioPartidaAcabada, ExcepcioPartidaTancada {
        if (acabadaPartida_) {
            throw new ExcepcioPartidaAcabada();
        }
        if (tancadaPartida_) {
            throw new ExcepcioPartidaTancada();
        }
        this.guardadaPartida_ = true;
        return generaPartidaGuardadaText();
    }
    /**
     * Genera un text amb la informació de l'estat dels valors de la partida. El format és (sense les línies amb |||||)<br>
     * ||||||FORMAT ESTAT PARTIDA||||||<br>
     * 'x'' 'x'' 'x'' ... 'x''\n'<br>
     * ...<br>
     * 'x'' 'x'' 'x'' ... 'x''\n'<br>
     * |||||||||||||||||||||||||||||||||||||||||||||||||||<br>
     * On hi haurà tantes x a cada fila com el grau de la partida i tantas files com el grau de la partida.
     * Per a comunicar-se amb la capa de presentació en el {@link main.domini.controladors.ControladorPartida}
     * @return Text amb la informació de la matriu de valors de la partida.
     */
    public String generaPartidaText() {
        StringBuilder textPartida = new StringBuilder();
        for (int i = 0; i < this.grauPartida_; i++) {
            for (int j = 0; j < this.grauPartida_; j++) {
                if (j != this.grauPartida_ - 1) textPartida.append(this.valorsPartida_[i][j]).append(" ");
                else textPartida.append(this.valorsPartida_[i][j]).append("\n");
            }
        }
        return textPartida.toString();
    }

    /**
     * Funció privada auxiliar per a generar un text amb la informació de la partida
     * Genera un text amb el format descrit a {@link #guardaPartida()}
     * @return Text amb la informació de la partida segons el format.
     */
    private String generaPartidaGuardadaText() {
        StringBuilder textPartidaGuardada = new StringBuilder();
        textPartidaGuardada.append(this.identificadorPartida_).append('\n')
                .append(this.getIdentificadorTaulerPartida()).append('\n')
                .append(this.calculaTemps()).append('\n')
                .append(this.grauPartida_).append('\n');
        for (int i = 0; i < this.grauPartida_; i++) {
            for (int j = 0; j < this.grauPartida_; j++) {
                if (j != this.grauPartida_ - 1) textPartidaGuardada.append(this.valorsPartida_[i][j]).append(" ");
                else textPartidaGuardada.append(this.valorsPartida_[i][j]).append("\n");
            }
        }
        return textPartidaGuardada.toString();
    }
    /**
     * Funció privada auxiliar per a calcular el temps total de la partida.
     * Ho calcula a partir de la data d'inici de la sessió de la partida actual i
     * el temps de la crida, sumant el temps acumulat.
     *
     * @return temps total de la partida.
     */
    private float calculaTemps() {
        LocalDateTime tempsActual = LocalDateTime.now();
        Duration duracio = Duration.between(this.iniciPartida_, tempsActual);
        float tempsTotal = this.tempsPartida_ + duracio.getSeconds() + duracio.getNano() / 1000000000.0f;
        return tempsTotal;
    }
    /**
     * Funció privada auxiliar per a crear l'identificador de la partida.
     * L'identificador de la partida és únic i es crea a partir de l'usuari i el moment de creació de la partida.
     * El format és "identificadorUsuariPartida:yyyy-mm-ddThh:mm:ss".
     * @return Identificador de la partida.
     */
    private String creaIdentificadorPartida() {
        String inici = this.iniciPartida_.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return this.identificadorUsuariPartida_ +":"+ inici;
    }
}
