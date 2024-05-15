package main.presentacio.Partida;

import main.presentacio.ObservadorBoto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaPartidesGuardades extends JPanel {
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    private String[] partidesGuardades_;
    private int indexActual = 0;
    private int maxIndex = 0;
    private int nombrePartides = 10;
    private JButton[] botonsPartida_;
    private JLabel[] labelsPartida_;
    private JButton previous10_;
    private JButton next10_;

    VistaPartidesGuardades(String[] partidesGuardades) {
        partidesGuardades_ = partidesGuardades;
        maxIndex = partidesGuardades.length / nombrePartides;
        if (partidesGuardades.length % nombrePartides == 0) maxIndex -= 1;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel panelText = new JPanel();
        panelText.setLayout(new GridLayout(nombrePartides, 3));
        JPanel panelButons = new JPanel();
        panelButons.setLayout(new GridLayout(nombrePartides, 1));
        panelButons.setBorder(BorderFactory.createTitledBorder("Partides Guardades"));
        JButton[] botonsPartida = new JButton[nombrePartides];
        JLabel[] labelsPartida = new JLabel[nombrePartides*3];
        for (int i = 0; i < nombrePartides; ++i) {
            botonsPartida[i] = new JButton();
            labelsPartida[i*3] = new JLabel();
            labelsPartida[i*3 + 1] = new JLabel();
            labelsPartida[i*3 + 2] = new JLabel();
            if (i < partidesGuardades.length) {
                botonsPartida[i].setText("");
                String[] midadatatemps = generaMidaDataTemps(partidesGuardades[i]);
                labelsPartida[i*3].setText(midadatatemps[0]);
                labelsPartida[i*3 + 1].setText(midadatatemps[1]);
                labelsPartida[i*3 + 2].setText(midadatatemps[2]+" s");
            }
            else botonsPartida[i].setVisible(false);
            botonsPartida[i].setOpaque(false);
            botonsPartida[i].setContentAreaFilled(false);
            botonsPartida[i].addActionListener(e -> {
                JButton sourceButton = (JButton) e.getSource();
                int indexBoto = -1;
                for (int j = 0; j < nombrePartides; ++j) {
                    if (botonsPartida[j] == sourceButton) {
                        indexBoto = j;
                        break;
                    }
                }
                for (ObservadorBoto ob : observadorsBoto_) ob.carregaPartida(partidesGuardades_[indexActual*nombrePartides + indexBoto]);
            });
            panelButons.add(botonsPartida[i]);
            panelText.add(labelsPartida[i*3]);
            panelText.add(labelsPartida[i*3 + 1]);
            panelText.add(labelsPartida[i*3 + 2]);
        }
        layeredPane.add(panelButons, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panelText, JLayeredPane.PALETTE_LAYER);
        botonsPartida_ = botonsPartida;
        JButton previous10 = new JButton("10 anteriors");
        previous10.addActionListener(e -> {
            previous10();
        });
        previous10.setEnabled(false);
        previous10_ = previous10;
        JButton next10 = new JButton("10 següents");
        next10.addActionListener(e -> {
            next10();
        });
        next10_ = next10;
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.tornarMenu();
        });
        this.add(layeredPane);
        this.add(tornar);
        this.add(Box.createHorizontalGlue());
        this.add(previous10);
        this.add(next10);
    }

    private void previous10() {
        indexActual -= 1;
        if (indexActual == 0) previous10_.setEnabled(false);
        if (indexActual < maxIndex) next10_.setEnabled(true);
        for (int i = 0; i < nombrePartides; ++i) {
            if (indexActual*nombrePartides + i < partidesGuardades_.length){
                String [] midadatatemps = generaMidaDataTemps(partidesGuardades_[indexActual*nombrePartides + i]);
                labelsPartida_[i*3].setText(midadatatemps[0]);
                labelsPartida_[i*3 + 1].setText(midadatatemps[1]);
                labelsPartida_[i*3 + 2].setText(midadatatemps[2]+" s");
            }
            else{
                botonsPartida_[i].setVisible(false);
                labelsPartida_[i*3].setText("");
                labelsPartida_[i*3 + 1].setText("");
                labelsPartida_[i*3 + 2].setText("");
            }
        }
    }

    private void next10() {
        indexActual += 1;
        if (indexActual == maxIndex) next10_.setEnabled(false);
        if (indexActual > 0) previous10_.setEnabled(true);
        for (int i = 0; i < nombrePartides; ++i) {
            if (indexActual * nombrePartides + i < partidesGuardades_.length){
                String[] midadatatemps = generaMidaDataTemps(partidesGuardades_[indexActual*nombrePartides + i]);
                labelsPartida_[i*3].setText(midadatatemps[0]);
                labelsPartida_[i*3 + 1].setText(midadatatemps[1]);
                labelsPartida_[i*3 + 2].setText(midadatatemps[2]+" s");
            }
            else{
                botonsPartida_[i].setVisible(false);
                labelsPartida_[i*3].setText("");
                labelsPartida_[i*3 + 1].setText("");
                labelsPartida_[i*3 + 2].setText("");
            }
        }
    }

    public void addObservadorBoto(ObservadorBoto ob) {
        observadorsBoto_.add(ob);
    }
    private String[] generaMidaDataTemps(String partidaGuardada){
        String[] parts = partidaGuardada.split("\n");
        String identificador = parts[0];
        String[] identificadorDividit = identificador.split(":");
        StringBuilder data = new StringBuilder();
        for (int j = 1; j < identificadorDividit.length; ++j) {
            data.append(identificadorDividit[j]);
        }
        String dataString = data.toString();
        String[] dataDividida = dataString.split("T");
        String dataFormat = String.valueOf(new StringBuilder(dataDividida[0] + " " + dataDividida[1]));
        String temps = parts[2];
        String mida = parts[3];
        return new String[]{mida, dataFormat, temps};
    }
}
