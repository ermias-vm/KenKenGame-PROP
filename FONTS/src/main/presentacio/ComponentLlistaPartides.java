package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * {@code ComponentLlistaPartides} és la classe abstracta que representa una llista de NOMBREPARTIDES_ d'elements
 * on cada element és clickable i conte una informació concreta.
 */
public abstract class ComponentLlistaPartides extends JLayeredPane {
    /**
     * Nombre de partides que es mostren a la vegada.
     */
    private int NOMBREPARTIDES_;
    /**
     * Nombre d'informacions que es mostren per cada partida. Per exemple, si es vol mostrar la mida, data i temps de cada partida, INFOPERPARTIDA_ = 3.
     */
    private int INFOPERPARTIDA_;
    /**
     * Llista d'observadors de la llista de partides.
     */
    private ArrayList<ObservadorLlista> observadorsLLista_ = new ArrayList<>();
    /**
     * Conté la informació de cada partida.
     */
    private String[] informacioPartides_;
    /**
     * Índex de la pàgina actual.
     */
    private int indexActual_ = 0;
    /**
     * Índex màxim de la pàgina.
     */
    private int maxIndex_ = 0;
    /**
     * Botons de les partides.
     */
    private JButton[] botonsPartida_;
    /**
     * Labels de les partides.
     */
    private JLabel[] labelsPartida_;
    /**
     * Botó per anar a la pàgina anterior.
     */
    private JButton previousN_;
    /**
     * Botó per anar a la pàgina següent.
     */
    private JButton nextN_;

    /**
     * Crea una llista de partides amb la informació donada i el nombre de partides a mostrar per pàgina.
     * @param informacioPartides Array de Strings amb la informació de les partides.
     * @param NOMBREPARTIDES Nombre de partides a mostrar per pàgina.
     * @param capcalera Array de Strings amb la capçalera de la informació de les partides.
     */
    public ComponentLlistaPartides(String[] informacioPartides, int NOMBREPARTIDES, String[] capcalera) {
        NOMBREPARTIDES_ = NOMBREPARTIDES+1;
        informacioPartides_ = informacioPartides;
        INFOPERPARTIDA_ = capcalera.length;
        maxIndex_ = informacioPartides.length / NOMBREPARTIDES_;
        if (informacioPartides.length % NOMBREPARTIDES_ == 0) maxIndex_ -= 1;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel panelText = new JPanel();
        panelText.setLayout(new GridLayout(NOMBREPARTIDES_, INFOPERPARTIDA_));
        JPanel panelButons = new JPanel();
        panelButons.setLayout(new GridLayout(NOMBREPARTIDES_, 1));
        panelButons.setBorder(BorderFactory.createTitledBorder("Partides Guardades"));
        JButton[] botonsPartida = new JButton[NOMBREPARTIDES_];
        JLabel[] labelsPartida = new JLabel[(NOMBREPARTIDES_) *INFOPERPARTIDA_];
        for (int i = 0; i < INFOPERPARTIDA_; ++i) {
            labelsPartida[i] = new JLabel(capcalera[i]);
            panelText.add(labelsPartida[i]);
        }
        botonsPartida[0] = new JButton();
        botonsPartida[0].setVisible(false);
        for (int i = 1; i < NOMBREPARTIDES_; ++i) {
            botonsPartida[i] = new JButton();
            labelsPartida[i*3] = new JLabel();
            labelsPartida[i*3 + 1] = new JLabel();
            labelsPartida[i*3 + 2] = new JLabel();
            if (i < informacioPartides.length) {
                botonsPartida[i].setText("");
                String[] midadatatemps = generaText(informacioPartides[i]);
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
                for (int j = 0; j < NOMBREPARTIDES_; ++j) {
                    if (botonsPartida[j] == sourceButton) {
                        indexBoto = j;
                        break;
                    }
                }
                for (ObservadorLlista ob : observadorsLLista_) ob.clickatLlista(informacioPartides_[indexActual_ * NOMBREPARTIDES_ + indexBoto]);
            });
            panelButons.add(botonsPartida[i]);
            panelText.add(labelsPartida[i*3]);
            panelText.add(labelsPartida[i*3 + 1]);
            panelText.add(labelsPartida[i*3 + 2]);
        }
        layeredPane.add(panelButons, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panelText, JLayeredPane.PALETTE_LAYER);
        botonsPartida_ = botonsPartida;
        labelsPartida_ = labelsPartida;
        JButton previousN = new JButton((NOMBREPARTIDES_-1) + " anteriors");
        previousN.addActionListener(e -> {
            previousN();
        });
        previousN.setEnabled(false);
        previousN_ = previousN;
        JButton nextN = new JButton(NOMBREPARTIDES_ + " següents");
        nextN.addActionListener(e -> {
            nextN();
        });
        nextN_ = nextN;
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorLlista ob : observadorsLLista_) ob.tornarMenu();
        });
        this.add(layeredPane);
        this.add(previousN);
        this.add(nextN);
        this.add(Box.createHorizontalGlue());
        this.add(tornar);
    }

    /**
     * Tira enrere si pot i ensenya les NOMBREPARTIDES_ anteriors.
     */
    private void previousN() {
        indexActual_ -= 1;
        if (indexActual_ == 0) previousN_.setEnabled(false);
        if (indexActual_ < maxIndex_) nextN_.setEnabled(true);
        for (int i = 1; i < NOMBREPARTIDES_; ++i) {
            if (indexActual_ * NOMBREPARTIDES_ + i < informacioPartides_.length){
                String [] midadatatemps = generaText(informacioPartides_[indexActual_ * NOMBREPARTIDES_ + i]);
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
    /**
     * Tira endavant si pot i ensenya les NOMBREPARTIDES_ següents.
     */
    private void nextN() {
        indexActual_ += 1;
        if (indexActual_ == maxIndex_) nextN_.setEnabled(false);
        if (indexActual_ > 0) previousN_.setEnabled(true);
        for (int i = 1; i < NOMBREPARTIDES_; ++i) {
            if (indexActual_ * NOMBREPARTIDES_ + i < informacioPartides_.length){
                String[] midadatatemps = generaText(informacioPartides_[indexActual_ * NOMBREPARTIDES_ + i]);
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
    /**
     * Afegeix un observador de la llista de partides.
     * @param ob Observador de la llista de partides.
     */
    public void addObservadorLlista(ObservadorLlista ob) {
            observadorsLLista_.add(ob);
    }

    /**
     * Genera el text per posar a cada label en funció de la informació de la partida.
     * @param s Informació de la partida.
     * @return Array de Strings amb què va a cada label ordenat.
     */
    protected abstract String[] generaText(String s);

    /**
     * Posa l'índex actual de la pàgina.
     * @param indexActual Índex actual de la pàgina.
     */
    public void setIndexActual(int indexActual) {
        indexActual_ = indexActual;
    }
}