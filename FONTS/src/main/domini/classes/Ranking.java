package main.domini.classes;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    private ArrayList<PartidaAcabada> rankingPartides;
    public Ranking() {
        rankingPartides = new ArrayList<>();
    }
    public boolean afegirPartida(String partidaAcabada) {
        String[] parts = partidaAcabada.split("\n");
        PartidaAcabada partida = new PartidaAcabada(parts[0], parts[1], parts[2], parts[3]);
        rankingPartides.add(partida);
        Collections.sort(rankingPartides);
        return true;
    }
    public ArrayList<String> get10(int index) {
        ArrayList<String> ranking = new ArrayList<>();
        for (int i = index; i < index+10 && i < rankingPartides.size(); ++i) {
            ranking.add(rankingPartides.get(i).getPartidaAcabada());
        }
        return ranking;
    }

    public ArrayList<String> getUsuari(String identificadorUsuari) {
        ArrayList<String> ranking = new ArrayList<>();
        for (PartidaAcabada partida : rankingPartides) {
            if (partida.getPartidaAcabada().split(" ")[1].equals(identificadorUsuari)) {
                ranking.add(partida.getPartidaAcabada());
            }
        }
        return ranking;
    }
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
class PartidaAcabada implements Comparable<PartidaAcabada> {
    private String identificadorPartida_;
    private String identificadorUsuari_;
    private String identificadorTauler_;
    private String temps_;

    public PartidaAcabada(String identificadorPartida_, String identificadorUsuari_, String identificadorTauler_, String temps_) {
        this.identificadorPartida_ = identificadorPartida_;
        this.identificadorUsuari_ = identificadorUsuari_;
        this.identificadorTauler_ = identificadorTauler_;
        this.temps_ = temps_;
    }
    public String getPartidaAcabada(){
        return identificadorPartida_ + " " + identificadorUsuari_ + " " + identificadorTauler_ + " " + temps_;
    }
    public int compareTo(PartidaAcabada o) {
       return Float.compare(Float.parseFloat(this.temps_), Float.parseFloat(o.temps_));
    }
}
