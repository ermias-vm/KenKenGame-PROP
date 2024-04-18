package main.domini.classes;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import main.stubs.TaulerStub;

public class Partida {
    //for testing inicialitzat a 2022-01-01 00:00
    private final LocalDateTime iniciPartida_ = LocalDateTime.of(2022, 1, 1, 0, 0);
    private final String identificadorPartida_;
    private final int midaPartida_;
    private final String identificadorUsuariPartida_;
    private final TaulerStub taulerPartida_;
    private int[][] valorsPartida_;
    private int tempsPartida_;
    //una partida es acabada si es correcta
    private boolean guardadaPartida_ = false;
    private boolean acabadaPartida_ = false;
    private boolean tancadaPartida_ = false;


    public Partida(int midaPartida, String identificadorUsuariPartida, TaulerStub TaulerPartida) {

        //this.iniciPartida_ = LocalDateTime.now();
        //This is commented for testing

        this.identificadorUsuariPartida_ = identificadorUsuariPartida;
        this.taulerPartida_ = TaulerPartida;
        this.midaPartida_ = midaPartida;
        this.identificadorPartida_ = creaIdentificadorPartida() ;
        this.valorsPartida_ = new int[midaPartida][midaPartida]; //per defecte a 0
        this.tempsPartida_ = 0;
    }
    //Per a carregar una partida guardada, parametres donats pel controlador guardar i carregar i s'ocupa de comprovar que no estigui ja carregada
    public Partida(String identificadorPartida, String identificadorUsuariPartida, TaulerStub TaulerPartida, int tempsPartida, int midaPartida, int[][] valorsPartida) {

        //this.iniciPartida_ = LocalDateTime.now();
        //This is commented for testing

        this.identificadorUsuariPartida_ = identificadorUsuariPartida;
        this.taulerPartida_ = TaulerPartida;
        this.midaPartida_ = midaPartida;
        this.identificadorPartida_ = identificadorPartida ;
        this.valorsPartida_ = valorsPartida; //per defecte a 0
        this.tempsPartida_ = tempsPartida;
        this.guardadaPartida_ = true;
    }

    //Getters
    public LocalDateTime getiniciPartida() {
        return iniciPartida_;
    }
    public String getIdentificadorPartida() {
        return identificadorPartida_;
    }
    public int getMidaPartida() {
        return midaPartida_;
    }
    public String getUsuariPartida() {
        return identificadorUsuariPartida_;
    }
    public TaulerStub getTaulerPartida(){
        return taulerPartida_;
    }
    public String getIdentificadorTaulerPartida() {
        return taulerPartida_.getIdentificador();
    }
    public int[][] getValorsPartida() {
        return valorsPartida_;
    }
    public int getTempsPartida() {
        return tempsPartida_;
    }
    public boolean getGuardadaPartida() {
        return guardadaPartida_;
    }
    public boolean getAcabadaPartida() {
        return acabadaPartida_;
    }

    public int setValorPartida(int valor, int fila, int columna) {
        if (tancadaPartida_) {
            return ErrorConstantsPartida.ERROR_INT_PARTIDA_TANCADA;
        }
        if (acabadaPartida_) {
            return ErrorConstantsPartida.ERROR_INT_PARTIDA_ACABADA;
        }
        if (fila < 0 || fila >= midaPartida_ || columna < 0 || columna >= midaPartida_) {
            return ErrorConstantsPartida.ERROR_INT_FILA_COLUMNA;
        }
        if (1 <= valor && valor <= midaPartida_) {
            valorsPartida_[fila][columna] = valor;
            return 1;
        }
        else return ErrorConstantsPartida.ERROR_INT_VALOR_INVALID;
    }

    public String acabaPartida() {
        if (acabadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_ACABADA;
        }
        if (tancadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_TANCADA;
        }
        if (taulerPartida_.corretgeix(valorsPartida_)) {
            this.acabadaPartida_ = true;
            String textPartidaAcabada = this.identificadorPartida_ + '\n' + this.identificadorUsuariPartida_ + '\n' + this.getIdentificadorTaulerPartida() + '\n' + String.valueOf(this.calculaTemps()) + '\n' + this.midaPartida_ + '\n' + String.valueOf(this.guardadaPartida_);
            return textPartidaAcabada;
        }
        else return ErrorConstantsPartida.ERROR_STRING_PARTIDA_INCORRECTA;
    }
    public String tancaIGuardaPartida() {
        if (acabadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_ACABADA;
        }
        if (tancadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_TANCADA;
        }
        this.tancadaPartida_ = true;
        this.guardadaPartida_ = true;
        this.tempsPartida_ = this.calculaTemps();
        StringBuilder textPartidaGuardada = new StringBuilder();
        textPartidaGuardada.append(this.identificadorPartida_).append('\n')
                .append(this.getIdentificadorTaulerPartida()).append('\n')
                .append(String.valueOf(tempsPartida_)).append('\n')
                .append(this.midaPartida_).append('\n');
        for (int i = 0; i < this.midaPartida_; i++) {
            for (int j = 0; j < this.midaPartida_; j++) {
                if (j != this.midaPartida_ - 1) textPartidaGuardada.append(String.valueOf(this.valorsPartida_[i][j])).append(" ");
                else textPartidaGuardada.append(String.valueOf(this.valorsPartida_[i][j])).append("\n");
            }
        }
        return textPartidaGuardada.toString();
    }

    public String guardaPartida() {
        if (acabadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_ACABADA;
        }
        if (tancadaPartida_) {
            return ErrorConstantsPartida.ERROR_STRING_PARTIDA_TANCADA;
        }
        this.guardadaPartida_ = true;
        StringBuilder textPartidaGuardada = new StringBuilder();
        textPartidaGuardada.append(this.identificadorPartida_).append('\n')
                .append(this.getIdentificadorTaulerPartida()).append('\n')
                .append(String.valueOf(this.calculaTemps())).append('\n')
                .append(this.midaPartida_).append('\n');
        for (int i = 0; i < this.midaPartida_; i++) {
            for (int j = 0; j < this.midaPartida_; j++) {
                if (j != this.midaPartida_ - 1) textPartidaGuardada.append(String.valueOf(this.valorsPartida_[i][j])).append(" ");
                else textPartidaGuardada.append(String.valueOf(this.valorsPartida_[i][j])).append("\n");
            }
        }
        return textPartidaGuardada.toString();
    }
    public String generaPartidaText() {
        StringBuilder textPartida = new StringBuilder();
        for (int i = 0; i < this.midaPartida_; i++) {
            for (int j = 0; j < this.midaPartida_; j++) {
                if (j != this.midaPartida_ - 1) textPartida.append(String.valueOf(this.valorsPartida_[i][j])).append(" ");
                else textPartida.append(String.valueOf(this.valorsPartida_[i][j])).append("\n");
            }
        }
        return textPartida.toString();
    }
    private int calculaTemps() {
        //for testing purposes
        //LocalDateTime tempsActual = LocalDateTime.now();
        LocalDateTime tempsActual = LocalDateTime.of(2022, 1, 1, 0, 8);;

        Duration duracio = Duration.between(this.iniciPartida_, tempsActual);
        int tempsTotal = this.tempsPartida_ + (int) duracio.getSeconds();
        return tempsTotal;
    }
    private String creaIdentificadorPartida() {
        String inici = this.iniciPartida_.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return this.identificadorUsuariPartida_ +":"+ inici;
    }
}
