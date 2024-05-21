package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CasellaConstructora extends JPanel {

    int posX;
    int posY;
    private boolean seleccionada = false;
    private boolean pertanyRegio = false;


    public CasellaConstructora(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        System.out.println("Soc a la casella " + posX + " " + posY);
        addMouseListener();
    }

    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasellaConstructora casella = (CasellaConstructora) e.getSource();
                if (SwingUtilities.isLeftMouseButton(e) && !casella.teRegioAssignada()) {
                    casella.setSeleccionada();
                }
                else if (SwingUtilities.isRightMouseButton(e) && !casella.teRegioAssignada() && casella.esSeleccionada()) {
                    casella.assignarRegio();
                }
            }
        });
    }

    public void setSeleccionada() {
        if (!this.teRegioAssignada()) {
            if (this.seleccionada) {
                this.seleccionada = false;
                this.setBackground(Color.WHITE);
            } else {
                this.seleccionada = true;
                this.setBackground(new Color(200, 200, 255));
            }
        }
    }

    public void assignarRegio() {
        if(this.esSeleccionada()) {
            this.pertanyRegio = true;
            this.setBackground(new Color(200, 255, 200));
        }
    }
    public void desassignarRegio() {
        if(this.esSeleccionada()) {
            this.pertanyRegio = false;
            this.setBackground(Color.WHITE);
        }
    }

    public boolean esSeleccionada() {
        return seleccionada;
    }

    public boolean teRegioAssignada() {
        return pertanyRegio;
    }
}
