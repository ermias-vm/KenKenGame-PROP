import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Partida {
    private final LocalDateTime iniciPartida_;
    private final String identificadorPartida_;
    private final int midaPartida_;
    private final String usuariPartida_;
    private final String identificadorTaulerPartida_;
    private int[][] valorsPartida_;
    private final int tempsPartida_;
    //una partida es acabada si es correcta
    private boolean guardadaPartida_ = false;
    private boolean acabadaPartida_ = false;

    public Partida(int midaPartida, String usuariPartida, String identificadorTaulerPartida) {
        this.iniciPartida_ = LocalDateTime.now();
        this.usuariPartida_ = usuariPartida;
        this.identificadorTaulerPartida_ = identificadorTaulerPartida;
        this.midaPartida_ = midaPartida;
        this.identificadorPartida_ = creaIdentificadorPartida(iniciPartida_, usuariPartida_) ;
        this.valorsPartida_ = new int[midaPartida][midaPartida]; //per defecte a 0
        this.tempsPartida_ = 0;
    }
    //Per a carregar una partida guardada, parametres donats pel controlador guardar i carregar i s'ocupa de comprovar que no estigui ja carregada
    public Partida(String identificadorPartida, String usuariPartida, String identificadorTaulerPartida, int tempsPartida, int midaPartida, int[][] valorsPartida) {
        this.iniciPartida_ = LocalDateTime.now();
        this.usuariPartida_ = usuariPartida;
        this.identificadorTaulerPartida_ = identificadorTaulerPartida;
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
        return usuariPartida_;
    }
    public String getIdentificadorTaulerPartida() {
        return identificadorTaulerPartida_;
    }
    public int[][] getValorsPartida() {
        return valorsPartida_;
    }
    public int getTempsPartida() {
        return tempsPartida_;
    }
    public boolean getAcabadaPartida() {
        return acabadaPartida_;
    }
    public void setValorPartida(int valor, int fila, int columna) {
        valorsPartida_[fila][columna] = valor;
    }
    public String generaTextPartidaAcabada() {
        String textPartidaAcabada = this.identificadorPartida_ + '\n' + this.usuariPartida_ + '\n' + this.identificadorTaulerPartida_ + '\n' + String.valueOf(this.calculaTemps()) + '\n' + this.midaPartida_ + '\n' + String.valueOf(this.guardadaPartida_);
        return textPartidaAcabada;
    }
    public String generaTextPartidaGuardada() {
        this.guardadaPartida_ = true;
        StringBuilder textPartidaGuardada = new StringBuilder();
        textPartidaGuardada.append(this.identificadorPartida_).append('\n')
                .append(this.usuariPartida_).append('\n')
                .append(this.identificadorTaulerPartida_).append('\n')
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
