package main.presentacio.Partida;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaPartida extends JPanel{
    private ComponentTauler componentTauler_;
    private MenuPartida menuPartida_;
    VistaPartida(int mida, ArrayList<Boolean>[][] mapaAdjacents){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        ComponentTauler componentTauler = new ComponentTauler(mida, mapaAdjacents);
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
    private void addObserverMenuPartida(ObservadorBoto observadorBoto){
        menuPartida_.addObservadorBoto(observadorBoto);
    }
    public void addObserverCasella(ObservadorCasella observadorCasella, int i, int j){
        componentTauler_.addObserver(observadorCasella, i, j);
    }
}
