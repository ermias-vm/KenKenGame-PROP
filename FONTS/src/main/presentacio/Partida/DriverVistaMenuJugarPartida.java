package main.presentacio.Partida;

import javax.swing.*;

public class DriverVistaMenuJugarPartida {
    public static void main(String[] args) {
        JFrame frame = new JFrame("VistaMenuJugarPartida");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        VistaMenuJugarPartida vistaMenuJugarPartida = new VistaMenuJugarPartida();
        vistaMenuJugarPartida.setVisible(true);
        frame.add(vistaMenuJugarPartida);
        frame.setVisible(true);

    }
}
