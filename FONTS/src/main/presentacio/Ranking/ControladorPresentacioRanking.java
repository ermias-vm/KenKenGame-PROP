package main.presentacio.Ranking;

import main.presentacio.CtrlPresentacio;
import main.presentacio.Partida.ObservadorLlista;

import javax.swing.*;
import java.awt.*;

import static main.presentacio.CtrlPresentacio.MIDAMAX;
import static main.presentacio.CtrlPresentacio.MIDAMIN;
/**
 * Controlador de la vista de rankings. S'encarrega de gestionar les interaccions de l'usuari amb la vista de rankings,
 * així com de gestionar les crides a la capa de domini per obtenir la informació necessària.
 */
public class ControladorPresentacioRanking implements ObservadorBuscador, ObservadorSelectorMida, ObservadorLlista {
    /**
     * Última mida de tauler seleccionada
     */
    private int ultimaMida_;
    /**
     * Vista de rankings
     */
    private VistaRankings vistaRankings_;
    /**
     * Controlador principal de presentació
     */
    private CtrlPresentacio controladorPresentacio_;
    /**
     * Instància única de la classe (patró singleton)
     */
    private static ControladorPresentacioRanking instance_;
     /**
     * Panell principal i únic de la vista.
     */
    private JPanel mainPanel_;
    /**
     * Constructora per defecte. Inicialitza la vista de rankings.
     */
    private ControladorPresentacioRanking() {
        mainPanel_ = new JPanel();

    }
    /**
     * Retorna l'única instància de la classe.
     * @return Instància de la classe
     */
    public static ControladorPresentacioRanking getInstance() {
        if (instance_ == null) instance_ = new ControladorPresentacioRanking();
        return instance_;
    }
    public void setControladorPresentacio(CtrlPresentacio controladorPresentacio) {
        controladorPresentacio_ = controladorPresentacio;
        inicialitzaVistaRankings();
    }
    /**
     * Inicialitza la vista de rankings.
     */
    public void inicialitzaVistaRankings(){
        ultimaMida_ = MIDAMIN;
        mainPanel_.setLayout(new BorderLayout());
        String[] informacioPartides = controladorPresentacio_.getRankingMida(MIDAMIN).toArray(new String[0]);
        vistaRankings_ = new VistaRankings(informacioPartides);
        vistaRankings_.addObservadorBuscador(this);
        vistaRankings_.addObservadorSelectorMida(this);
        vistaRankings_.addObservadorLlista(this);
        mainPanel_.add(vistaRankings_, BorderLayout.CENTER);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    /**
     * Mètode que s'executa quan es clica sobre un element de la llista de rankings.
     * @param s Informació de l'element clicat.
     */
    @Override
    public void clickatLlista(String s) {
        String[] parts = s.split(" ");
        String identificadorTauler = parts[2];
        String[] opcions = {"Sí", "No"};
        int decisio = JOptionPane.showOptionDialog(null, "Vols carregar la partida amb identificador de tauler " + identificadorTauler + "?","Jugar Tauler", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcions, opcions[1]);
        if (decisio == JOptionPane.YES_OPTION) {
            controladorPresentacio_.jugarIdentificadorTauler(identificadorTauler);
        }
    }

    /**
     * Mètode que s'executa quan es vol tornar al menú principal. És a dir, s'ha clickat el botó de tornar.
     */
    @Override
    public void tornarMenu() {
        CtrlPresentacio.getInstance().showMenuPrincipal();
    }

    /**
     * Mètode que s'executa quan es selecciona una mida de tauler amb el JSpinner de la vista.
     * @param mida Mida seleccionada
     */
    @Override
    public void midaSeleccionada(String mida) {
        ultimaMida_ = Integer.parseInt(mida);
        String[] informacioPartides = controladorPresentacio_.getRankingMida(Integer.parseInt(mida)).toArray(new String[0]);
        vistaRankings_.actualitzaPartides(informacioPartides);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }

    public void actualitzaRanking() {
        String[] informacioPartides = controladorPresentacio_.getRankingMida(ultimaMida_).toArray(new String[0]);
        vistaRankings_.actualitzaPartides(informacioPartides);
        mainPanel_.revalidate();
        mainPanel_.repaint();
    }
    /**
     * Mètode que s'executa quan es fa una cerca d'usuari a la vista.
     * @param nom Nom de l'usuari a cercar
     */
    @Override
    public void cercaUsuari(String nom) {
        if (nom.equals("")) {
            String[] informacioPartides = controladorPresentacio_.getRankingMida(ultimaMida_).toArray(new String[0]);
            vistaRankings_.actualitzaPartides(informacioPartides);
        } else {
            String[] informacioPartides = controladorPresentacio_.getRankingUsuari(nom).toArray(new String[0]);
            if (informacioPartides.length == 0) {
                JOptionPane.showMessageDialog(null, "No s'ha trobat cap partida amb aquest usuari", "Error", JOptionPane.ERROR_MESSAGE);
            }
            vistaRankings_.actualitzaPartides(informacioPartides);
        }
    }
    /**
     * Retorna el panell principal
     * @return Panell principal
     */
    public JPanel getDefaultPanel() {
        return mainPanel_;
    }
}
