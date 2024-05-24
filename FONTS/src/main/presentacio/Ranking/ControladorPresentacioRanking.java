package main.presentacio.Ranking;

import main.domini.controladors.ControladorRanking;
import main.presentacio.CtrlPresentacio;
import main.presentacio.Partida.ObservadorLlista;

import javax.swing.*;

import static main.domini.controladors.ControladorRanking.MIDAMIN;

public class ControladorPresentacioRanking implements ObservadorBuscador, ObservadorSelectorMida, ObservadorLlista {
    private int ultimaMida_;
    private VistaRankings vistaRankings_;
    private ControladorRanking controladorRanking_;
    private static ControladorPresentacioRanking instance_;
    private JPanel mainPanel_;
    private ControladorPresentacioRanking() {
        mainPanel_ = new JPanel();
        controladorRanking_ = ControladorRanking.getInstance();
    }
    public static ControladorPresentacioRanking getInstance() {
        if (instance_ == null) instance_ = new ControladorPresentacioRanking();
        return instance_;
    }
    public void inicialitzaVistaRankings(){
        ultimaMida_ = MIDAMIN;
        String[] informacioPartides = controladorRanking_.getRankingMida(MIDAMIN).toArray(new String[0]);
        vistaRankings_ = new VistaRankings(informacioPartides);
        vistaRankings_.addObservadorBuscador(this);
        vistaRankings_.addObservadorSelectorMida(this);
        vistaRankings_.addObservadorLlista(this);
        mainPanel_.add(vistaRankings_);
        mainPanel_.add(vistaRankings_);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }
    @Override
    public void clickatLlista(String s) {

    }

    @Override
    public void tornarMenu() {
        CtrlPresentacio.getInstance().showMenuPrincipal();
    }

    @Override
    public void midaSeleccionada(String mida) {
        ultimaMida_ = Integer.parseInt(mida);
        String[] informacioPartides = controladorRanking_.getRankingMida(Integer.parseInt(mida)).toArray(new String[0]);
        vistaRankings_.actualitzaPartides(informacioPartides);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    @Override
    public void busquedaUsuari(String nom) {
        if (nom.equals("")) {
            String[] informacioPartides = controladorRanking_.getRankingMida(ultimaMida_).toArray(new String[0]);
            vistaRankings_.actualitzaPartides(informacioPartides);
        } else {
            String[] informacioPartides = controladorRanking_.getRankingUsuari(nom).toArray(new String[0]);
            if (informacioPartides.length == 0) {
                JOptionPane.showMessageDialog(null, "No s'ha trobat cap partida amb aquest usuari", "Error", JOptionPane.ERROR_MESSAGE);
            }
            vistaRankings_.actualitzaPartides(informacioPartides);
        }

    }
}
