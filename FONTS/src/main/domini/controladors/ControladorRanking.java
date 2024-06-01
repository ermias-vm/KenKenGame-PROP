package main.domini.controladors;

import main.domini.classes.Ranking;
import static main.presentacio.CtrlPresentacio.MIDAMAX;
import static main.presentacio.CtrlPresentacio.MIDAMIN;
import java.util.ArrayList;

/**
 * {@code ControladorRanking} és el controlador de domini encarregat de gestionar el ranking de les partides acabades.
 */
public class ControladorRanking {
    /**
     * Rankings de les partides acabades per mida.
     * Cada posició i representa el ranking de les partides acabades de mida i + MIDAMIN.
     */
    private Ranking[] rankingPerMida_;
    /**
     * Instància del controlador de domini.
     */
    private CtrlDomini controladorDomini_;
    /**
     * Instància del controlador de ranking.
     */
    private static ControladorRanking controladorRanking_;
    /**
     * Constructora de la classe.
     */
    private ControladorRanking(){
        rankingPerMida_ = new Ranking[MIDAMAX - MIDAMIN + 1];
        for (int i = 0; i < MIDAMAX - MIDAMIN + 1; ++i) {
            rankingPerMida_[i] = new Ranking();
        }
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
        return rankingPerMida_[mida - MIDAMIN].afegirPartida(partidaAcabada);
    }

    /**
     * Retorna les N partides amb millor temps a partir de l'índex donat de la mida corresponent.
     * @param mida Mida del tauler de les partides.
     * @param index Índex a partir del qual es volen obtenir les partides.
     * @param n Nombre de partides a obtenir.
     * @return Llista de les N partides amb millor temps a partir de l'índex donat.
     */
    public ArrayList<String> getRankingN(int mida, int index, int n) {
        return rankingPerMida_[mida - MIDAMIN].getN(index,n);
    }

    /**
     * Retorna el ranking complet de les partides de la mida corresponent.
     * @param mida Mida del tauler de les partides.
     * @return Llista de les partides del ranking.
     */
    public ArrayList<String> getRankingMida(int mida) {
        return rankingPerMida_[mida - MIDAMIN].getRanking();
    }
    /**
     * Retorna les partides d'un usuari donat de la mida corresponent.
     * @param identificadorUsuari Identificador de l'usuari.
     * @return Llista de les partides de l'usuari donat.
     */
    public ArrayList<String> getRankingUsuari(String identificadorUsuari, int mida){
        ArrayList<String> rankingUsuari = new ArrayList<>();
        rankingUsuari.addAll(rankingPerMida_[mida - MIDAMIN].getUsuari(identificadorUsuari));
        return rankingUsuari;
    }

    /**
     * Inicialitza el ranking de les partides acabades.
     */
    private void inicialitzarRanking() {
        for (int i = 0; i < rankingPerMida_.length; ++i) {
            ArrayList<String> partidesAcabades = controladorDomini_.carregaPartidesAcabadesMidaPersistencia(i + MIDAMIN);
            for (String partidaAcabada : partidesAcabades) {
                rankingPerMida_[i].afegirPartida(partidaAcabada);
            }
        }
    }

    /**
     * Estableix el controlador de domini.
     * @param ctrlDomini Controlador de domini.
     */
    public void setControladorDomini(CtrlDomini ctrlDomini) {
        controladorDomini_ = ctrlDomini;
        inicialitzarRanking();
    }
}
