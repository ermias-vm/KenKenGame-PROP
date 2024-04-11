package main.domini.classes;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import main.domini.excepcions.*;


public class Partida {
    private final LocalDateTime iniciPartida_;
    private final String identificadorPartida_;
    private final int midaPartida_;
    private final String identificadorUsuariPartida_;
    private final Tauler taulerPartida_;
    private int[][] valorsPartida_;
    private final int tempsPartida_;
    //una partida es acabada si es correcta
    private boolean guardadaPartida_ = false;
    private boolean acabadaPartida_ = false;

    public Partida(int midaPartida, String identificadorUsuariPartida, Tauler TaulerPartida) {
        this.iniciPartida_ = LocalDateTime.now();
        this.identificadorUsuariPartida_ = identificadorUsuariPartida;
        this.taulerPartida_ = TaulerPartida;
        this.midaPartida_ = midaPartida;
        this.identificadorPartida_ = creaIdentificadorPartida(iniciPartida_, identificadorUsuariPartida_) ;
        this.valorsPartida_ = new int[midaPartida][midaPartida]; //per defecte a 0
        this.tempsPartida_ = 0;
    }
    //Per a carregar una partida guardada, parametres donats pel controlador guardar i carregar i s'ocupa de comprovar que no estigui ja carregada
    public Partida(String identificadorPartida, String identificadorUsuariPartida, Tauler TaulerPartida, int tempsPartida, int midaPartida, int[][] valorsPartida) {
        this.iniciPartida_ = LocalDateTime.now();
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
    public Tauler getTaulerPartida(){
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

    public void setValorPartida(int valor, int fila, int columna) {
        try {
            if (1 <= valor && valor <= midaPartida_) ;
            else throw new ExcepcioValorInvalid();
            valorsPartida_[fila][columna] = valor;
        }
        catch (ExcepcioValorInvalid e) {
            System.out.println(e.getMessage());
        }
    }

    public String acabaPartida() {
        try {
            if (taulerPartida_.corretgeix(valorsPartida_)) {
                this.acabadaPartida_ = true;
                String textPartidaAcabada = this.identificadorPartida_ + '\n' + this.identificadorUsuariPartida_ + '\n' + this.getIdentificadorTaulerPartida() + '\n' + String.valueOf(this.calculaTemps()) + '\n' + this.midaPartida_ + '\n' + String.valueOf(this.guardadaPartida_);
                return textPartidaAcabada;
            }
            else throw new ExcepcioPartidaIncorrecta;
        }
        catch (ExcepcioPartidaIncorrecta e) {
            System.out.println(e.getMessage());
        }

    }

    public String guardaPartida() {
        this.guardadaPartida_ = true;
        StringBuilder textPartidaGuardada = new StringBuilder();
        textPartidaGuardada.append(this.identificadorPartida_).append('\n')
                .append(this.identificadorUsuariPartida_).append('\n')
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
    private int calculaTemps() {
        LocalDateTime tempsActual = LocalDateTime.now();
        Duration duracio = Duration.between(this.iniciPartida_, tempsActual);
        int tempsTotal = this.tempsPartida_ + (int) duracio.getSeconds();
        return tempsTotal;
    }
    private String creaIdentificadorPartida(LocalDateTime iniciPartida, String usuariPartida) {
        String inici = iniciPartida.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return usuariPartida + inici;
    }
}
