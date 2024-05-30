package main.presentacio.Partida;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * {@code VistaPartida} és la vista de la partida. Està formada per un {@code ComponentTauler} i un {@code MenuPartida}.
 */
public class VistaPartida extends JPanel{
    private String identificadorUsuari_;
    private ComponentTauler componentTauler_;
    private MenuPartida menuPartida_;
    VistaPartida(int mida, ArrayList<Boolean>[][] mapaAdjacents, ArrayList<String> operacionsPartida, String identificadorUsuari, int[][] tauler){
        identificadorUsuari_ = identificadorUsuari;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ComponentTauler componentTauler = new ComponentTauler(mida, mapaAdjacents, tauler, operacionsPartida);
        MenuPartida menuPartida = new MenuPartida();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.66;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(componentTauler, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.34;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(menuPartida, gbc);
        componentTauler_ = componentTauler;
        menuPartida_ = menuPartida;
    }

    /**
     * Mostra un missatge en el menú de la partida.
     * @param missatge Missatge a mostrar.
     * @param correctesa Indica si el missatge és correcte o incorrecte.
     */
    public void mostrarMissatgeMenu(String missatge, boolean correctesa) {
        menuPartida_.mostrarMissatge(missatge, correctesa);
    }

    /**
     * Afegeix un observador al menú de la partida.
     * @param observadorBoto Observador a afegir.
     */
    public void addObserverMenuPartida(ObservadorBoto observadorBoto){
        menuPartida_.addObservadorBoto(observadorBoto);
    }

    /**
     * Afegeix un observador a una casella del tauler.
     * @param observadorCasella Observador a afegir.

     */
    public void addObserverCasella(ObservadorCasella observadorCasella){
        componentTauler_.addObserver(observadorCasella);
    }

    /**
     * Actualitza els valors del tauler.
     * @param tauler Tauler amb els valors a actualitzar.
     */
    public void actualitzaValor(String valor, int fila, int columna){
        componentTauler_.actualitzaValor(valor, fila, columna);
    }

    /**
     * Actualitza les posicions incorrectes del tauler.
     * @param posicionsIncorrectes Posicions incorrectes a actualitzar.
     */
    public void actualitzaPosicionsIncorrectes(ArrayList<int[]> posicionsIncorrectes) {
        componentTauler_.actualitzaPosicionsIncorrectes(posicionsIncorrectes);
    }

    /**
     * Retorna l'identificador de l'usuari que ha iniciat la partida.
     * @return Identificador de l'usuari.
     */
    public String getIdentificadorUsuari() {
        return identificadorUsuari_;
    }

    public void setFocus(int fila, int columna) {
        componentTauler_.setFocus(fila, columna);
    }
}
