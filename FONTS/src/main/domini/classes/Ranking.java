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

