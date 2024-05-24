package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CasellaConstructora extends JPanel {

    JLabel infoRegioLabel;
    int posX;
    int posY;
    private boolean seleccionada;
    private boolean pertanyRegio;
    private int indexRegio;

    public CasellaConstructora(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.setLayout(null);
        configInicial();
        addMouseListener();
    }

    public void configInicial() {
        this.seleccionada = false;
        this.pertanyRegio = false;
        this.indexRegio = -1;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
    }


    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CasellaConstructora casellaPremuda = (CasellaConstructora) e.getSource();
                //Si es fa click amb el boto esquerre del ratoli
                if (SwingUtilities.isLeftMouseButton(e) && !casellaPremuda.teRegioAssignada()) {
                    casellaPremuda.setSeleccionada();
                }
                //Si es fa click amb el boto dret del ratoli
                else if (SwingUtilities.isRightMouseButton(e)) {

                    if (!casellaPremuda.teRegioAssignada() && casellaPremuda.esSeleccionada()) {
                        CrearKenkenManual.getInstance().processarEntradaUsuari(casellaPremuda);
                    }

                    else if (casellaPremuda.teRegioAssignada()) {
                        int confirm = JOptionPane.showConfirmDialog(casellaPremuda, "Estas segur que vols eliminar la regio?", "Confirmar eliminacio", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            TaulerConstrutor.getInstance().eliminarRegioAssignada(casellaPremuda.getIndexRegio());
                        }
                    }
                }
            }
        });
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
        this.setBackground(new Color(104, 253, 104));
    }

    public void desmarcaComRegio() {
        this.pertanyRegio = false;
        this.seleccionada = false;
        this.indexRegio = -1;
        this.setBackground(new Color(255, 255, 255));
    }

    public void addInfoRegio(String operacio, String resultat, int midaTauler) {

        infoRegioLabel = new JLabel();
        int posIniX = 0;
        int posIniY = this.getWidth()/10;
        int ampladaLabel = (int)(this.getWidth());
        int alturaLabel = (int)(this.getHeight() * 0.3);
        infoRegioLabel.setBounds(posIniX, posIniY, ampladaLabel, alturaLabel);
        infoRegioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoRegioLabel.setVerticalAlignment(SwingConstants.CENTER);
        // Ajusta la mida de la font en funció de la mida del tauler
        int fontSizeResultat = (int) (75 / midaTauler);
        int fontSizeOperacio = (int)(fontSizeResultat * 1.5); // 150% más grande que fontSize
        String textInfoRegio = "<html><span style='font-size:" + fontSizeResultat + "px'>" + operacio +
                "</span><span style='font-size:" + fontSizeOperacio+ "px'>" + resultat + "</span></html>";

        infoRegioLabel.setText(textInfoRegio);
        this.add(infoRegioLabel);
    }

    public void eliminarInfoRegio() {
        this.remove(infoRegioLabel);
        }

}
