package main.presentacio.Partida;

import javax.swing.*;
import java.util.ArrayList;

public class VistaMenuJugarPartida extends JPanel {
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    VistaMenuJugarPartida() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton introduirTaulerManualment = new JButton("Introduir Tauler Manualment");
        introduirTaulerManualment.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.introduirTaulerManualment();
        });
        JButton introduirIdentificadorTauler = new JButton("Introduir Identificador Tauler");
        introduirIdentificadorTauler.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.introduirIdentificadorTauler();
        });
        JButton partidaAleatoria = new JButton("Partida Aleatoria");
        partidaAleatoria.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.partidaAleatoria();
        });
        JButton ultimaPartidaGuardada = new JButton("Ultima Partida Guardada");
        ultimaPartidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.ultimaPartidaGuardada();
        });
        JButton partidaGuardada = new JButton("Partida Guardada");
        partidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.partidaGuardada();
        });
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.tornar();
        });
        this.add(introduirTaulerManualment);
        this.add(introduirIdentificadorTauler);
        this.add(partidaAleatoria);
        this.add(ultimaPartidaGuardada);
        this.add(partidaGuardada);
        this.add(Box.createHorizontalGlue());
        this.add(tornar);
    }
    public void addObservadorBoto(ObservadorBoto ob) {
        observadorsBoto_.add(ob);
    }
}
