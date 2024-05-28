package main.presentacio.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * {@code ComponentLlistaPartides} és la classe abstracta que representa una llista de NOMBREPARTIDES_ d'elements
 * on cada element és clickable i conte una informació concreta.
 */
public abstract class ComponentLlistaPartides extends JPanel {
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
    private int maxIndex_ ;
    /**
     * Botons de les partides.
     */
    private JPanel[] botonsPartida_;
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        NOMBREPARTIDES_ = NOMBREPARTIDES+1;
        informacioPartides_ = informacioPartides;
        INFOPERPARTIDA_ = capcalera.length;
        maxIndex_ = informacioPartides.length / NOMBREPARTIDES_;
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel panelText = new JPanel();
        panelText.setLayout(new GridLayout(NOMBREPARTIDES_, INFOPERPARTIDA_));
        JPanel panelBotons = new JPanel();
        panelBotons.setLayout(new GridLayout(NOMBREPARTIDES_, 1));
        panelText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JPanel[] botonsPartida = new JPanel[NOMBREPARTIDES_];
        JLabel[] labelsPartida = new JLabel[(NOMBREPARTIDES_) *INFOPERPARTIDA_];
        for (int i = 0; i < INFOPERPARTIDA_; ++i) {
            labelsPartida[i] = new JLabel(capcalera[i], SwingConstants.CENTER);
            labelsPartida[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
            panelText.add(labelsPartida[i]);
        }
        botonsPartida[0] = new JPanel();
        botonsPartida[0].setOpaque(false);
        panelBotons.add(botonsPartida[0]);
        for (int i = 1; i < NOMBREPARTIDES_; ++i) {
            botonsPartida[i] = new JPanel();
            for (int j = 0; j < INFOPERPARTIDA_; ++j) {
                labelsPartida[i*INFOPERPARTIDA_ + j] = new JLabel("", SwingConstants.CENTER);
                labelsPartida[i*INFOPERPARTIDA_ + j].setOpaque(false);
            }
            if (i <= informacioPartides.length) {
                String[] informacio = generaText(informacioPartides[i-1]);
                for (int j = 0; j < INFOPERPARTIDA_; ++j) {
                    labelsPartida[i*INFOPERPARTIDA_ + j].setText(informacio[j]);
                    labelsPartida[i*INFOPERPARTIDA_ + j].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
                }
            }
            botonsPartida[i].setOpaque(false);
            botonsPartida[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Object sourceButton = e.getSource();
                    int indexBoto = -1;
                    for (int j = 0; j < NOMBREPARTIDES_; ++j) {
                        if (botonsPartida[j] == sourceButton) {
                            indexBoto = j;
                            break;
                        }
                    }
                    int indexInformacio = indexActual_ * (NOMBREPARTIDES_ - 1) + indexBoto - 1;
                    if (indexBoto != 0 && indexBoto != -1 && indexInformacio < informacioPartides_.length) {
                        for (ObservadorLlista ob : observadorsLLista_)
                            ob.clickatLlista(informacioPartides_[indexInformacio]);
                    }
                }
            });
            panelBotons.add(botonsPartida[i]);
            for (int j = 0; j < INFOPERPARTIDA_; ++j) {
                panelText.add(labelsPartida[i*INFOPERPARTIDA_ + j]);
            }
        }
        botonsPartida_ = botonsPartida;
        labelsPartida_ = labelsPartida;
        JButton previousN = new JButton((NOMBREPARTIDES_-1) + " anteriors");
        previousN.addActionListener(e -> {
            previousN();
        });
        previousN.setEnabled(false);
        previousN_ = previousN;
        JButton nextN = new JButton((NOMBREPARTIDES_ -1)+ " següents");
        nextN.addActionListener(e -> {
            nextN();
        });
        if (maxIndex_ == 0) nextN.setEnabled(false);
        nextN_ = nextN;
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorLlista ob : observadorsLLista_) ob.tornarMenu();
        });
        layeredPane.add(panelBotons, gbc, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(panelText, gbc, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.add(previousN);
        bottom.add(nextN);
        bottom.add(Box.createHorizontalGlue());
        bottom.add(tornar);
        this.add(bottom);
    }

    /**
     * Tira enrere si pot i ensenya les NOMBREPARTIDES_ anteriors.
     */
    private void previousN() {
        indexActual_ -= 1;
        if (indexActual_ == 0) previousN_.setEnabled(false);
        if (indexActual_ < maxIndex_) nextN_.setEnabled(true);
        for (int i = 1; i < NOMBREPARTIDES_; ++i) {
            int indexInfo = indexActual_ * (NOMBREPARTIDES_-1) + i;
            if (indexInfo < informacioPartides_.length){
                String [] informacio = generaText(informacioPartides_[indexInfo-1]);
                for (int j = 0; j < INFOPERPARTIDA_; ++j) {
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setText(informacio[j]);
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
                }
            }
            else{
                for (int j = 0; j < INFOPERPARTIDA_; ++j){
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setText("");
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
                }
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
            int indexInfo = indexActual_ * (NOMBREPARTIDES_-1) + i;
            if (indexInfo <= informacioPartides_.length){
                String[] informacio = generaText(informacioPartides_[indexInfo-1]);
                for (int j = 0; j < INFOPERPARTIDA_; ++j) {
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setText(informacio[j]);
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
                }
            }
            else{
                for (int j = 0; j < INFOPERPARTIDA_; ++j){
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setText("");
                    labelsPartida_[i*INFOPERPARTIDA_ + j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
                }
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