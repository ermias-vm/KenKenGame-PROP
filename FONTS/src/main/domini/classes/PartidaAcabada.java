package main.domini.classes;


/**
 * Classe que representa una partida acabada i avaluable.
 */
class PartidaAcabada implements Comparable<PartidaAcabada> {
    /**
     * Identificador de la partida.
     */
    private String identificadorPartida_;
    /**
     * Identificador de l'usuari.
     */
    private String identificadorUsuari_;
    /**
     * Identificador del tauler.
     */
    private String identificadorTauler_;
    /**
     * Temps de resolució de la partida.
     */
    private String temps_;

    /**
     * Constructora de la classe.
     * @param identificadorPartida_ Identificador de la partida.
     * @param identificadorUsuari_ Identificador de l'usuari.
     * @param identificadorTauler_ Identificador del tauler.
     * @param temps_ Temps de resolució de la partida.
     */
    public PartidaAcabada(String identificadorPartida_, String identificadorUsuari_, String identificadorTauler_, String temps_) {
        this.identificadorPartida_ = identificadorPartida_;
        this.identificadorUsuari_ = identificadorUsuari_;
        this.identificadorTauler_ = identificadorTauler_;
        this.temps_ = temps_;
    }

    /**
     * Retorna la representació de la partida acabada.
     * @return El identificador de la partida, l'usuari, el tauler i el temps de resolució.
     */
    public String getPartidaAcabada(){
        return identificadorPartida_ + " " + identificadorUsuari_ + " " + identificadorTauler_ + " " + temps_;
    }

    /**
     * Compara dues partides acabades pel temps de resolució.
     * @param o La partida acabada amb la qual es compara.
     * @return Un valor negatiu si aquesta partida té un temps menor, un valor positiu si aquesta partida té un temps major, 0 si tenen el mateix temps.
     */
    public int compareTo(PartidaAcabada o) {
        return Float.compare(Float.parseFloat(this.temps_), Float.parseFloat(o.temps_));
    }
}

