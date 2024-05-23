package main.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_ERROR;

/**
 * ComponentCasella és la classe que representa una casella del taulell del kenken en la interfície gràfica.
 * Conté un valor, una operació i un botó per a poder introduir un valor a la casella, així com les seves coordenades.
 */
public class ComponentCasella extends JPanel implements ActionListener, KeyListener{
    /**
     * {@code valor_} és el JLabel que conté el valor de la casella en text.
     */
    private JLabel valor_;
    /**
     * {@code operacio_} és el JLabel que conté l'operació de la casella en text.
     */
    private JLabel operacio_;
    /**
     * {@code boto_} és el JButton que permet introduir un valor a la casella.
     */
    private JButton boto_;
    /**
     * {@code botoPremut_} és un booleà que indica si s'ha premut el boto de la casella.
     */
    private boolean botoPremut_;
    /**
     * {@code incorrecte_} és un booleà que indica si la casella és incorrecte.
     */
    private boolean incorrecte_ = false;
    /**
     * {@code mida_} és la mida del kenken.
     */
    private int mida_;
    /**
     * {@code fila_} és la fila de la casella.
     */
    private int fila_;
    /**
     * {@code columna_} és la columna de la casella.
     */
    private int columna_;
    /**
     * {@code observers_} és la llista d'observadors de la casella.
     */
    private List<ObservadorCasella> observers_;

    /**
     * Creadora de la classe ComponentCasella.
     * Com estarà en una gridLayout les mides canviaran segons la mida de la finestra.
     * @param mida Mida del kenken
     * @param fila Fila de la casella
     * @param columna Columna de la casella
     */
    public ComponentCasella(int mida, int fila, int columna, int valor) {
       this.setLayout(null);
        mida_ = mida;
        fila_ = fila;
        columna_ = columna;
        valor_ = new JLabel();
        operacio_ = new JLabel();
        boto_ = new JButton();
        observers_ = new ArrayList<>();
        valor_.setBounds(25, 25, 50, 50);
        operacio_.setBounds(0, 0, 25, 25);
        boto_.setBounds(0, 0, 100, 100);
        pintaBoto();
        setValor(String.valueOf(valor));
        boto_.addActionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.add(boto_);
        this.add(valor_);
        this.add(operacio_);
    }

    /**
     * Posa la casella com a incorrecte o correcte en funció del paràmetre incorrecte.
     * Pinta el botó de la casella amb un color vermell si és incorrecte, i sense color si és correcte.
     * @param incorrecte true si la casella és incorrecte, false si és correcte.
     */
    public void setIncorrecte(boolean incorrecte) {
        incorrecte_ = incorrecte;
        pintaBoto();
    }

    /**
     * Pinta el botó de la casella en funció de si és incorrecte o no.
     */
    private void pintaBoto() {
        if (!incorrecte_){
            boto_.setOpaque(false);
            boto_.setContentAreaFilled(false);
            boto_.setBorderPainted(false);
        } else {
            boto_.setOpaque(true);
            boto_.setBackground(Color.decode(COLOR_ERROR + "175"));
        }
    }

    /**
     * Afegeix un observador a la casella.
     * @param observer Observador a afegir.
     */
    public void addObserver(ObservadorCasella observer) {
        observers_.add(observer);
    }

    /**
     * Posa el valor de la casella a valor.
     * @param valor Valor a posar a la casella.
     */
    public void setValor(String valor){
        if (!Objects.equals(valor_.getText(), valor)) setIncorrecte(false);
        try {
            int value = Integer.parseInt(valor);
            if (value >= 1 && value <= mida_) {
                valor_.setText(valor);
            } else {
                valor_.setText("");
            }
        } catch (NumberFormatException e) {
            valor_.setText("");
        }
    }

    /**
     * Posa l'operació de la casella a operacio.
     * @param operacio Operació a posar a la casella. Format: "simbolOperacio resultat".
     */
    public void setOperacio_(String operacio){
        operacio_.setText(operacio);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // Set your preferred dimensions here
    }

    /**
     * Quan s'ha premut el botó, es posa el botoPremut_ a true.
     * Aleshores, quan es premi una tecla, si el botoPremut_ és true, es posa el valor de la tecla a la casella, si és permès.
     * Es notifica als observadors que s'ha canviat el valor de la casella.
     * @param e event de teclat
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (botoPremut_){
            String c = String.valueOf(e.getKeyChar());
            if (permesa(c)){
                setValor(c);
                for (ObservadorCasella observer : observers_){
                    observer.notificarCanviValor(c , fila_, columna_);
                }
            }
            botoPremut_ = false;
        }
    }

    /**
     * Comprova que el caràcter c estigui permès en el context del joc.
     * @param c Caràcter a comprovar.
     * @return true si c és un número entre 0 i mida_, false altrament.
     */
    private boolean permesa(String c) {
        for (int i = 0; i <= mida_; ++i){
            if (c.equals(String.valueOf(i))) return true;
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
 //Quan es prem el boto, es posa el botoPremut_ a true.
    @Override
    public void actionPerformed(ActionEvent e) {
        botoPremut_= true;
    }

    public void setIncorrecte() {
    }
}
