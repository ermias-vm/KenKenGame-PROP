package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class CasellaConstructora extends JPanel {

    int posX;
    int posY;
    private boolean seleccionada;
    private boolean pertanyRegio;
    private int indexRegio;

    public CasellaConstructora(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.seleccionada = false;
        this.pertanyRegio = false;
        this.indexRegio = -1;

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        addMouseListener();
    }

    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasellaConstructora casella = (CasellaConstructora) e.getSource();
                //Si es fa click amb el boto esquerre del ratoli
                if (SwingUtilities.isLeftMouseButton(e) && !casella.teRegioAssignada()) {
                    casella.setSeleccionada();
                }
                //Si es fa click amb el boto dret del ratoli
                else if (SwingUtilities.isRightMouseButton(e)) {

                    if (!casella.teRegioAssignada() && casella.esSeleccionada()) {
                        CrearKenkenManual.getInstance().processarEntradaUsuari();
                    }

                    else if (casella.teRegioAssignada()) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar la regio?", "Confirmar eliminacio", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            TaulerConstrutor.getInstance().eliminarRegioAssignada(casella.getIndexRegio());
                        }
                    }
                }
            }
        });
    }

    public void setSeleccionada() {
            if (this.seleccionada) {
                this.seleccionada = false;
                this.setBackground(new Color(255, 255, 255));
                TaulerConstrutor.getInstance().eliminarPosCasella(posX, posY);

            } else {
                this.seleccionada = true;
                this.setBackground(new Color(145, 252, 246));
                TaulerConstrutor.getInstance().afegirPosCasella(posX, posY);
            }
    }

    public void marcaComRegio(int index) {
        this.pertanyRegio = true;
        this.indexRegio = index;
        System.out.println("Casella " + posX + " " + posY + " pertany a la regio " + index);
        this.setBackground(new Color(104, 253, 104));
    }

    public void desmarcaComRegio() {
        this.pertanyRegio = false;
        this.seleccionada = false;
        this.indexRegio = -1;
        this.setBackground(new Color(255, 255, 255));
    }

    public boolean esSeleccionada() {
        return seleccionada;
    }

    public boolean teRegioAssignada() {
        return pertanyRegio;
    }

    public void decrementaIndexRegio() {
        this.indexRegio--;
    }

    public int getIndexRegio() {
        return this.indexRegio;
    }
}
