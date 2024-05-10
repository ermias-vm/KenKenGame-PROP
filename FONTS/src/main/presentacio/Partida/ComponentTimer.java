package main.presentacio.Partida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentTimer extends JPanel {
    private int seconds_ = 0;
    private JLabel timerLabel;

    public ComponentTimer(int seconds) {
        this.seconds_ = seconds;
        timerLabel = new JLabel();
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
