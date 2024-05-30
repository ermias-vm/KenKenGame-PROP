package main.presentacio.Partida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ComponentTimer és la classe que representa un temporitzador en la interfície gràfica.
 */
public class ComponentTimer extends JPanel {
    /**
     * {@code seconds_} és el temps en segons del temporitzador.
     */
    private int seconds_ = 0;
    /**
     * {@code timerLabel} és el JLabel que conté el temps del temporitzador.
     */
    private JLabel timerLabel;

    /**
     * Creadora de la classe ComponentTimer.
     * @param seconds Segons inicials del temporitzador.
     */
    public ComponentTimer(int seconds) {
        this.seconds_ = seconds;
        String tempsInicial = String.format("%02d:%02d", seconds / 60, seconds % 60);
        timerLabel = new JLabel();
        timerLabel.setText(tempsInicial);
        timerLabel.setFont(timerLabel.getFont().deriveFont(20.0f));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(timerLabel);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds_++;
                int minutes = seconds_ / 60;
                int remainingSeconds = seconds_ % 60;
                timerLabel.setText(String.format("%02d:%02d", minutes, remainingSeconds));
            }
        });
        timer.start();
    }
}
