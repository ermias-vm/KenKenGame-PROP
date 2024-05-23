package main.presentacio.Ranking;

import main.presentacio.ObservadorLlista;

import static main.presentacio.CtrlPresentacio.NOMBREPARTIDESLLISTA;
import javax.swing.*;
import java.awt.*;

public class VistaRankings extends JPanel {
    private BuscadorUsuari buscadorUsuari_;
    private ComponentSelectorMida componentSelectorMida_;
    private PartidesRanking partidesRanking_;
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
    public void addObservadorBuscador(ObservadorBuscador observador) {
        buscadorUsuari_.addObservador(observador);
    }
    public void addObservadorSelectorMida(ObservadorSelectorMida observador) {
        componentSelectorMida_.addObservador(observador);
    }
    public void addObservadorLlista(ObservadorLlista observador) {
        partidesRanking_.addObservadorLlista(observador);
    }
    public void actualitzaPartides(String[] informacioPartides) {
        partidesRanking_ = new PartidesRanking(informacioPartides, NOMBREPARTIDESLLISTA);
    }



}
