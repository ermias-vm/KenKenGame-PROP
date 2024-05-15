package main.domini.controladors;

import main.domini.classes.Ranking;
import main.persistencia.ControladorPersistenciaPartida;

import java.util.ArrayList;

public class ControladorRanking {
    private final int midaMin_ = 3;
    private final int midaMax_ = 9;
    private Ranking[] rankingPerMida_;
    private ControladorPersistenciaPartida controladorPersistenciaPartida_;
    private static ControladorRanking controladorRanking_;
    private ControladorRanking(){
        rankingPerMida_ = new Ranking[midaMax_ - midaMin_ + 1];
        for (int i = 0; i < midaMax_ - midaMin_ + 1; ++i) {
            rankingPerMida_[i] = new Ranking();
        }
        controladorPersistenciaPartida_ = ControladorPersistenciaPartida.getInstance();
        inicialitzarRanking();
    }
    public static ControladorRanking getInstance() {
        if (controladorRanking_ == null) {
            controladorRanking_ = new ControladorRanking();
        }
        return controladorRanking_;
    }
    public boolean afegirPartida(String partidaAcabada) {
        String[] parts = partidaAcabada.split("\n");
        int mida = Integer.parseInt(parts[4]);
        return rankingPerMida_[mida - midaMin_].afegirPartida(partidaAcabada);
    }

    public ArrayList<String> getRanking10(int mida, int index) {
        return rankingPerMida_[mida - midaMin_].get10(index);
    }
    public ArrayList<String> getRankingUsuari(String identificadorUsuari){
        ArrayList<String> rankingUsuari = new ArrayList<>();
        for (int i = 0; i < rankingPerMida_.length; ++i) {
            rankingUsuari.addAll(rankingPerMida_[i].getUsuari(identificadorUsuari));
        }
        return rankingUsuari;
    }
    private void inicialitzarRanking() {
        for (int i = 0; i < rankingPerMida_.length; ++i) {
            ArrayList<String> partidesAcabades = controladorPersistenciaPartida_.carregaPartidesAcabadesMida(i + midaMin_);
            for (String partidaAcabada : partidesAcabades) {
                rankingPerMida_[i].afegirPartida(partidaAcabada);
            }
        }
    }

}
