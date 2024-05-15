package main.presentacio.Partida;

import main.presentacio.ObservadorBoto;

import javax.swing.*;
import java.util.ArrayList;

public class VistaMenuJugarPartida extends JPanel {
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    private String identificadorUsuari_;
    VistaMenuJugarPartida() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton introduirTaulerManualment = new JButton("Introduir Tauler Manualment");
        introduirTaulerManualment.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarIntroduirTaulerManualment();
        });
        JButton introduirIdentificadorTauler = new JButton("Introduir Identificador Tauler");
        introduirIdentificadorTauler.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarIntroduirIdentificadorTauler();
        });
        JButton partidaAleatoria = new JButton("Partida Aleatoria");
        partidaAleatoria.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarPartidaAleatoria();
        });
        JButton ultimaPartidaGuardada = new JButton("Ultima Partida Guardada");
        ultimaPartidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarUltimaPartidaGuardada();
        });
        JButton partidaGuardada = new JButton("Partida Guardada");
        partidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarPartidaGuardada();
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

    public String getUsuari() {
        return identificadorUsuari_;
    }
}
