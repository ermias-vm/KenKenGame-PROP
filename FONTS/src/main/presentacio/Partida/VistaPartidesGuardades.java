package main.presentacio.Partida;

import javax.swing.*;
import java.util.ArrayList;

public class VistaPartidesGuardades extends JPanel {
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    private JButton[] botonsPartida_;
    VistaPartidesGuardades(String[] partidesGuardades) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Partides Guardades"));
        JButton[] botonsPartida = new JButton[partidesGuardades.length];
        for (int i = 0; i < partidesGuardades.length; ++i) {
            botonsPartida[i] = new JButton(partidesGuardades[i]);
            botonsPartida[i].addActionListener(e -> {
                JButton sourceButton = (JButton) e.getSource();
                String buttonText = sourceButton.getText();
                for (ObservadorBoto ob : observadorsBoto_) ob.carregaPartida(buttonText);
            });
            panel.add(botonsPartida[i]);
        }
        botonsPartida_ = botonsPartida;
        JButton next10 = new JButton("10 següents");
        next10.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.next10();
        });
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.tornar();
        });
        this.add(panel);
        this.add(tornar);
        this.add(Box.createHorizontalGlue());
        this.add(next10);
    }
    public void addObservadorBoto(ObservadorBoto ob) {
        observadorsBoto_.add(ob);
    }
    public void deuSeguents(String[] partidesGuardades) {
        int ultimIndex = 0;
        for (int i = 0; i < partidesGuardades.length; ++i) {
            botonsPartida_[i].setText(partidesGuardades[i]);
            ultimIndex = i;
        }
        for (int i = ultimIndex + 1; i < partidesGuardades.length; ++i) {
            botonsPartida_[i].setText("");
            botonsPartida_[i].setVisible(false);
        }
    }
}
