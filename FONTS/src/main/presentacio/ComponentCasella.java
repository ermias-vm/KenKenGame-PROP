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

public class ComponentCasella extends JPanel implements ActionListener, KeyListener{
    private JLabel valor_;
    private JLabel operacio_;
    private JButton boto_;
    private boolean botoPremut_;
    private boolean incorrecte_ = false;
    private int mida_;
    private int fila_;
    private int columna_;
    private List<ObservadorCasella> observers_;
    public ComponentCasella(int mida, int fila, int columna) {
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
        pintaBoto(boto_);
        boto_.addActionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.add(boto_);
        this.add(valor_);
        this.add(operacio_);
    }
    public void setIncorrecte(boolean incorrecte) {
        incorrecte_ = incorrecte;
        pintaBoto(boto_);
    }
    private void pintaBoto(JButton boto) {
        if (!incorrecte_){
            boto_.setOpaque(false);
            boto_.setContentAreaFilled(false);
            boto_.setBorderPainted(false);
        } else {
            boto_.setOpaque(true);
            boto.setBackground(Color.decode(COLOR_ERROR + "175"));
        }
    }

    public void addObserver(ObservadorCasella observer) {
        observers_.add(observer);
    }
    public void setValor(String s){
        if (!Objects.equals(valor_.getText(), s)) setIncorrecte(false);
        try {
            int value = Integer.parseInt(s);
            if (value >= 1 && value <= mida_) {
                valor_.setText(s);
            } else {
                valor_.setText("");
            }
        } catch (NumberFormatException e) {
            valor_.setText("");
        }
    }
    public void setOperacio_(String s){
        operacio_.setText(s);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // Set your preferred dimensions here
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        botoPremut_= true;
    }

    public void setIncorrecte() {
    }
}
