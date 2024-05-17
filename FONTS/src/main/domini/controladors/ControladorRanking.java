package main.domini.controladors;

import main.domini.classes.Ranking;
import main.persistencia.ControladorPersistenciaPartida;

import java.util.ArrayList;

/**
 * {@code ControladorRanking} és el controlador de domini encarregat de gestionar el ranking de les partides acabades.
 */
public class ControladorRanking {
    /**
     * Mida mínima del tauler de les partides.
     */
    private final int MIDAMIN_ = 3;
    /**
     * Mida màxima del tauler de les partides.
     */
    private final int MIDAMAX_ = 9;
    /**
     * Rankings de les partides acabades per mida.
     * Cada posició i representa el ranking de les partides acabades de mida i + MIDAMIN_.
     */
    private Ranking[] rankingPerMida_;
    /**
     * Instància del controlador de persistència de les partides.
     */
    private ControladorPersistenciaPartida controladorPersistenciaPartida_;
    /**
     * Instància del controlador de ranking.
     */
    private static ControladorRanking controladorRanking_;
    /**
     * Constructora de la classe.
     */
    private ControladorRanking(){
        rankingPerMida_ = new Ranking[MIDAMAX_ - MIDAMIN_ + 1];
        for (int i = 0; i < MIDAMAX_ - MIDAMIN_ + 1; ++i) {
            rankingPerMida_[i] = new Ranking();
        }
        controladorPersistenciaPartida_ = ControladorPersistenciaPartida.getInstance();
        inicialitzarRanking();
    }
    /**
     * Retorna la instància del controlador de ranking.
     * @return Instància del controlador de ranking.
     */
    public static ControladorRanking getInstance() {
        if (controladorRanking_ == null) {
            controladorRanking_ = new ControladorRanking();
        }
        return controladorRanking_;
    }
    /**
     * Afegeix una partida acabada al ranking de la mida corresponent.
     * Utilitza el format de {@link main.domini.classes.Partida#acabaPartida()} per a les dades de la partidaAcabada.
     * @param partidaAcabada Partida acabada a afegir.
     * @return Cert si s'ha afegit la partida, fals altrament.
     */
    public boolean afegirPartida(String partidaAcabada) {
        String[] parts = partidaAcabada.split("\n");
        int mida = Integer.parseInt(parts[4]);
        return rankingPerMida_[mida - MIDAMIN_].afegirPartida(partidaAcabada);
    }

    /**
     * Retorna les N partides amb millor temps a partir de l'índex donat de la mida corresponent.
     * @param mida Mida del tauler de les partides.
     * @param index Índex a partir del qual es volen obtenir les partides.
     * @param n Nombre de partides a obtenir.
     * @return Llista de les N partides amb millor temps a partir de l'índex donat.
     */
    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return rankingPerMida_[mida - MIDAMIN_].getN(index,n);
    }
    /**
     * Retorna les partides d'un usuari donat de la mida corresponent.
     * @param identificadorUsuari Identificador de l'usuari.
     * @return Llista de les partides de l'usuari donat.
     */
    public ArrayList<String> getRankingUsuari(String identificadorUsuari){
        ArrayList<String> rankingUsuari = new ArrayList<>();
        for (int i = 0; i < rankingPerMida_.length; ++i) {
            rankingUsuari.addAll(rankingPerMida_[i].getUsuari(identificadorUsuari));
        }
        return rankingUsuari;
    }

    /**
     * Inicialitza el ranking de les partides acabades.
     */
    private void inicialitzarRanking() {
        for (int i = 0; i < rankingPerMida_.length; ++i) {
            ArrayList<String> partidesAcabades = controladorPersistenciaPartida_.carregaPartidesAcabadesMida(i + MIDAMIN_);
            for (String partidaAcabada : partidesAcabades) {
                rankingPerMida_[i].afegirPartida(partidaAcabada);
            }
        }
    }

}
