package main.presentacio.Ranking;

import main.presentacio.Partida.ObservadorLlista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.presentacio.CtrlPresentacio.NOMBREPARTIDESLLISTA;

/**
 * {@code VistaRanking} és la vista dels rankings de les partides.
 * Està formada per un buscador d'usuaris, un selector de mida i una llista de partides.
 * El nombre de partides que es mostren a la llista ve determinat per la constant {@link main.presentacio.CtrlPresentacio#NOMBREPARTIDESLLISTA}.
 */
public class VistaRankings extends JPanel {
    /**
     * Buscador d'usuaris.
     */
    private BuscadorUsuari buscadorUsuari_;
    /**
     * Selector de mida.
     */
    private ComponentSelectorMida componentSelectorMida_;
    /**
     * Llista de partides.
     */
    private PartidesRanking partidesRanking_;
    private JPanel panellCentral_;
    private ArrayList<ObservadorLlista> observadorsLlista_ = new ArrayList<>();
    private ArrayList<ObservadorSelectorMida> observadorsSelectorMida_ = new ArrayList<>();
    private ArrayList<ObservadorBuscador> observadorsBuscador_ = new ArrayList<>();
    /**
     * Crea una nova vista de rankings amb la informació de les partides.
     * @param informacioPartides Informació de les partides.
     */
    public VistaRankings(String[] informacioPartides) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel panellCentral = new JPanel();
        panellCentral.setLayout(new BoxLayout(panellCentral, BoxLayout.Y_AXIS));
        panellCentral.setPreferredSize(new Dimension(1080, 800));
        panellCentral.setMaximumSize(new Dimension(1080, 800));
        panellCentral.setMinimumSize(new Dimension(1080, 800));
        ComponentSelectorMida componentSelectorMida = new ComponentSelectorMida();
        componentSelectorMida.setPreferredSize(new Dimension(210, 40));
        componentSelectorMida.setMaximumSize(new Dimension(210, 40));
        componentSelectorMida.setMinimumSize(new Dimension(210, 40));
        componentSelectorMida_ = componentSelectorMida;

        BuscadorUsuari buscadorUsuari = new BuscadorUsuari();
        buscadorUsuari.setPreferredSize(new Dimension(360, 40));
        buscadorUsuari.setMaximumSize(new Dimension(360, 40));
        buscadorUsuari.setMinimumSize(new Dimension(360, 40));
        buscadorUsuari_ = buscadorUsuari;

        PartidesRanking partidesRanking = new PartidesRanking(informacioPartides, NOMBREPARTIDESLLISTA);
        partidesRanking.setPreferredSize(new Dimension(1080, 640));
        partidesRanking.setMaximumSize(new Dimension(1080, 640));
        partidesRanking.setMinimumSize(new Dimension(1080, 640));
        partidesRanking_ = partidesRanking;


        JPanel panellBuscador = new JPanel();
        panellBuscador.setLayout(new BoxLayout(panellBuscador, BoxLayout.X_AXIS));
        panellBuscador.add(buscadorUsuari);
        panellBuscador.add(Box.createHorizontalGlue());
        JPanel panellSelector = new JPanel();
        panellSelector.setLayout(new BoxLayout(panellSelector, BoxLayout.X_AXIS));
        panellSelector.add(componentSelectorMida);
        panellSelector.add(Box.createHorizontalGlue());
        panellCentral.add(Box.createVerticalGlue());
        panellCentral.add(panellBuscador);
        panellCentral.add(Box.createVerticalGlue());
        panellCentral.add(panellSelector);
        panellCentral.add(Box.createVerticalGlue());
        panellCentral.add(partidesRanking);
        panellCentral.add(Box.createVerticalGlue());


        panellCentral_ = panellCentral;

        Dimension dim = new Dimension(60, 800);
        JPanel panellEsquerra = new JPanel();
        panellEsquerra.setPreferredSize(dim);
        panellEsquerra.setMaximumSize(dim);
        panellEsquerra.setMinimumSize(dim);
        JPanel panellDreta = new JPanel();
        panellDreta.setPreferredSize(dim);
        panellDreta.setMaximumSize(dim);
        panellDreta.setMinimumSize(dim);
        this.add(panellEsquerra);
        this.add(panellCentral);
        this.add(panellDreta);
    }

    /**
     * Afegeix un observador al buscador d'usuaris.
     * @param observador Observador a afegir.
     */
    public void addObservadorBuscador(ObservadorBuscador observador) {
        buscadorUsuari_.addObservador(observador);
        observadorsBuscador_.add(observador);

    }
    /**
     * Afegeix un observador al selector de mida.
     * @param observador Observador a afegir.
     */
    public void addObservadorSelectorMida(ObservadorSelectorMida observador) {
        componentSelectorMida_.addObservador(observador);
        observadorsSelectorMida_.add(observador);
    }

    /**
     * Afegeix un observador a la llista de partides.
     * @param observador Observador a afegir.
     */
    public void addObservadorLlista(ObservadorLlista observador) {
        partidesRanking_.addObservadorLlista(observador);
        observadorsLlista_.add(observador);
    }
    /**
     * Actualitza la llista de partides amb la informació donada.
     * @param informacioPartides Informació de les partides.
     */
    public void actualitzaPartides(String[] informacioPartides) {
        panellCentral_.removeAll();
        JPanel panellBuscador = new JPanel();
        panellBuscador.setLayout(new BoxLayout(panellBuscador, BoxLayout.X_AXIS));
        panellBuscador.add(buscadorUsuari_);
        panellBuscador.add(Box.createHorizontalGlue());
        JPanel panellSelector = new JPanel();
        panellSelector.setLayout(new BoxLayout(panellSelector, BoxLayout.X_AXIS));
        panellSelector.add(componentSelectorMida_);
        panellSelector.add(Box.createHorizontalGlue());
        partidesRanking_ = new PartidesRanking(informacioPartides, NOMBREPARTIDESLLISTA);
        partidesRanking_.setPreferredSize(new Dimension(1080, 640));
        partidesRanking_.setMaximumSize(new Dimension(1080, 640));
        partidesRanking_.setMinimumSize(new Dimension(1080, 640));
        panellCentral_.add(Box.createVerticalGlue());
        panellCentral_.add(panellBuscador);
        panellCentral_.add(Box.createVerticalGlue());
        panellCentral_.add(panellSelector);
        panellCentral_.add(Box.createVerticalGlue());
        panellCentral_.add(partidesRanking_);
        panellCentral_.add(Box.createVerticalGlue());
        this.revalidate();
        this.repaint();
        for (ObservadorLlista observador : observadorsLlista_) {
            partidesRanking_.addObservadorLlista(observador);
        }
    }
}