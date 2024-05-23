package main.domini.classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa el ranking de les partides acabades i avaluables
 * Una partida és avaluable si s'ha solucionat correctament sense guardar-la ni utilitzar cap pista.
 * Un ranking és una llista de les partides acabades i avaluables ordenades per temps.
 */
public class Ranking {
    /**
     * Llista de les partides acabades, utilitzant el format {@link PartidaAcabada}
     */
    private ArrayList<PartidaAcabada> rankingPartides;
    /**
     * Constructora de la classe.
     */
    public Ranking() {
        rankingPartides = new ArrayList<>();
    }
    /**
     * Afegeix una partida acabada al ranking. El ranking es manté ordenat per temps.
     * @param partidaAcabada Partida acabada a afegir.
     * @return Cert si s'ha afegit la partida, fals altrament.
     */
    public boolean afegirPartida(String partidaAcabada) {
        String[] parts = partidaAcabada.split("\n");
        PartidaAcabada partida = new PartidaAcabada(parts[0], parts[1], parts[2], parts[3]);
        rankingPartides.add(partida);
        Collections.sort(rankingPartides);
        return true;
    }

    /**
     * Retorna les N partides amb millor temps a partir de l'índex donat.
     * @param index Índex a partir del qual es volen obtenir les partides.
     * @return Llista de les N partides amb millor temps a partir de l'índex donat.
     */
    public ArrayList<String> getN(int index, int n) {
        ArrayList<String> ranking = new ArrayList<>();
        for (int i = index; i < index+n && i < rankingPartides.size(); ++i) {
            if (rankingPartides.get(i) != null) {
                ranking.add(rankingPartides.get(i).getPartidaAcabada());
            }
        }
        return ranking;
    }
    /**
     * Retorna el ranking complet de les partides.
     * @return Llista de les partides del ranking.
     */
    public ArrayList<String> getRanking() {
        ArrayList<String> ranking = new ArrayList<>();
        for (PartidaAcabada partida : rankingPartides) {
            ranking.add(partida.getPartidaAcabada());
        }
        return ranking;
    }
    /**
     * Retorna les partides d'un usuari donat.
     * @param identificadorUsuari Identificador de l'usuari.
     * @return Llista de les partides de l'usuari donat.
     */
    public ArrayList<String> getUsuari(String identificadorUsuari) {
        ArrayList<String> ranking = new ArrayList<>();
        for (PartidaAcabada partida : rankingPartides) {
            if (partida.getPartidaAcabada().split(" ")[1].equals(identificadorUsuari)) {
                ranking.add(partida.getPartidaAcabada());
            }
        }
        return ranking;
    }
    /**
     * Retorna les partides d'un tauler donat.
     * @param identificadorTauler Identificador del tauler.
     * @return Llista de les partides del tauler donat.
     */
    public ArrayList<String> getTauler(String identificadorTauler) {
        ArrayList<String> ranking = new ArrayList<>();
        for (PartidaAcabada partida : rankingPartides) {
            if (partida.getPartidaAcabada().split(" ")[2].equals(identificadorTauler)) {
                ranking.add(partida.getPartidaAcabada());
            }
        }
        return ranking;
    }
}

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
