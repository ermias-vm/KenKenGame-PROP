package main.presentacio.Ranking;

import main.presentacio.Partida.ObservadorLlista;

import static main.presentacio.CtrlPresentacio.NOMBREPARTIDESLLISTA;
import javax.swing.*;
import java.awt.*;

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
    /**
     * Crea una nova vista de rankings amb la informació de les partides.
     * @param informacioPartides Informació de les partides.
     */
    public VistaRankings(String[] informacioPartides) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.17;
        gbc.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbcLlista = new GridBagConstraints();
        gbcLlista.gridx = 0;
        gbcLlista.gridy = 0;
        gbcLlista.weightx = 1;
        gbcLlista.weighty = 0.66;
        gbcLlista.fill = GridBagConstraints.BOTH;
        ComponentSelectorMida componentSelectorMida = new ComponentSelectorMida();
        componentSelectorMida_ = componentSelectorMida;
        BuscadorUsuari buscadorUsuari = new BuscadorUsuari();
        buscadorUsuari_ = buscadorUsuari;
        PartidesRanking partidesRanking = new PartidesRanking(informacioPartides, NOMBREPARTIDESLLISTA);
        partidesRanking_ = partidesRanking;
        this.add(buscadorUsuari, gbcLlista);
        this.add(componentSelectorMida, gbcLlista);
        this.add(partidesRanking, gbcLlista);
    }

    /**
     * Afegeix un observador al buscador d'usuaris.
     * @param observador Observador a afegir.
     */
    public void addObservadorBuscador(ObservadorBuscador observador) {
        buscadorUsuari_.addObservador(observador);
    }
    /**
     * Afegeix un observador al selector de mida.
     * @param observador Observador a afegir.
     */
    public void addObservadorSelectorMida(ObservadorSelectorMida observador) {
        componentSelectorMida_.addObservador(observador);
    }

    /**
     * Afegeix un observador a la llista de partides.
     * @param observador Observador a afegir.
     */
    public void addObservadorLlista(ObservadorLlista observador) {
        partidesRanking_.addObservadorLlista(observador);
    }
    /**
     * Actualitza la llista de partides amb la informació donada.
     * @param informacioPartides Informació de les partides.
     */
    public void actualitzaPartides(String[] informacioPartides) {
        partidesRanking_ = new PartidesRanking(informacioPartides, NOMBREPARTIDESLLISTA);
    }



}
