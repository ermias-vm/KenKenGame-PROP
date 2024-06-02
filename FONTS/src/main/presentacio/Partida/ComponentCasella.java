package main.presentacio.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_ERROR;

/**
 * ComponentCasella és la classe que representa una casella del taulell del kenken en la interfície gràfica.
 * Conté un valor, una operació i un botó per a poder introduir un valor a la casella, així com les seves coordenades.
 */
public class ComponentCasella extends JPanel implements  KeyListener{

    /**
     * {@code valor_} és el JLabel que conté el valor de la casella en text.
     */
    private JLabel valor_;
    /**
     * {@code operacio_} és el JLabel que conté l'operació de la casella en text.
     */
    private JLabel operacio_;
    /**
     * {@code botoPremut_} és un booleà que indica si s'ha premut el botó de la casella.
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
    private Color backgroundColor_;

    /**
     * Creadora de la classe ComponentCasella.
     * Com estarà en una gridLayout les mides canviaran segons la mida de la finestra.
     * @param mida Mida del kenken
     * @param fila Fila de la casella
     * @param columna Columna de la casella
     */
    public ComponentCasella(int mida, int fila, int columna, int valorCasella) {
        this.setLayout(new BorderLayout());
        mida_ = mida;
        fila_ = fila;
        columna_ = columna;
        observers_ = new ArrayList<>();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Casella clicada: " + fila_ + " " + columna_);
                botoPremut_ = true;
                for (ObservadorCasella observer : observers_){
                    observer.notificarRequestFocus(fila_, columna_);
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel casella = new JPanel();
        casella.setLayout(new GridBagLayout());
        JPanel operacio = new JPanel();
        operacio.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel operacioText = new JLabel();
        operacio_ = operacioText;
        operacio.add(operacioText, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel panellBuit = new JPanel();
        operacio.add(panellBuit, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        casella.add(operacio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel valor = new JLabel();
        valor.setHorizontalAlignment(JLabel.CENTER);
        valor.setFont(new Font("Arial", Font.PLAIN, 300/mida));
        backgroundColor_ = casella.getBackground();
        valor.setForeground(backgroundColor_);
        valor_ = valor;
        casella.add(valor_, gbc);
        this.add(casella, BorderLayout.CENTER);
        setValor(String.valueOf(valorCasella));
        this.addKeyListener(this);
    }

    /**
     * Posa la casella com a incorrecte o correcte en funció del paràmetre incorrecte.
     * Pinta el botó de la casella amb un color vermell si és incorrecte, i sense color si és correcte.
     * @param incorrecte true si la casella és incorrecte, false si és correcte.
     */
    public void setIncorrecte(boolean incorrecte) {
        if (valor_.getText() != "0"){
            incorrecte_ = incorrecte;
            pintaBoto();
        }
    }

    /**
     * Pinta el botó de la casella en funció de si és incorrecte o no.
     */
    private void pintaBoto() {
        if (!incorrecte_) {
            if (valor_.getText() == "0") valor_.setForeground(backgroundColor_);
            else valor_.setForeground(Color.BLACK);
        } else{
            String colorError = COLOR_ERROR;
            String[] parts = colorError.split(",");
            valor_.setForeground(new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        this.repaint();
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
                valor_.setForeground(Color.BLACK);
            } else {
                valor_.setText("0");
                valor_.setForeground(backgroundColor_);
            }
        } catch (NumberFormatException e) {
            valor_.setText("0");
            valor_.setForeground(backgroundColor_);
        }
    }

    /**
     * Posa l'operació de la casella a operacio.
     * @param operacio Operació a posar a la casella. Format: "simbolOperacio resultat".
     */
    public void setOperacio(String operacio){
        operacio_.setText(operacio);
    }

    /**
     * Retorna la dimensió default de la casella.
     * @return Dimensió default de la casella.
     */
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
            System.out.println("Tecla premuda: " + c);
            if (permesa(c)){
                setValor(c);
                for (ObservadorCasella observer : observers_) {
                    observer.notificarCanviValor(c, fila_, columna_);
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
}
