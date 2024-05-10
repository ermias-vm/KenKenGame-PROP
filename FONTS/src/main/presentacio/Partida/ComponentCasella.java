package main.presentacio.Partida;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ComponentCasella extends JPanel implements ActionListener, KeyListener{
    private JLabel valor;
    private JLabel operacio_;
    private JButton boto_;
    private boolean botoPremut_;
    private int mida_;
    private int fila_;
    private int columna_;
    private List<ObservadorCasella> observers_;
    public ComponentCasella(int mida, int fila, int columna) {
        mida_ = mida;
        fila_ = fila;
        columna_ = columna;
        valor = new JLabel();
        operacio_ = new JLabel();
        boto_ = new JButton();
        observers_ = new ArrayList<>();
        valor.setBounds(25, 25, 50, 50);
        operacio_.setBounds(0, 0, 25, 25);
        boto_.setBounds(0, 0, 100, 100);
        boto_.setOpaque(false);
        boto_.setContentAreaFilled(false);
        boto_.setBorderPainted(false);
        boto_.addActionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    public void addObserver(ObservadorCasella observer) {
        observers_.add(observer);
    }
    public void setValor(String s){
        try {
            int value = Integer.parseInt(s);
            if (value >= 1 && value <= mida_) {
                valor.setText(s);
            } else {
                valor.setText("");
            }
        } catch (NumberFormatException e) {
            valor.setText("");
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
                    observer.canviValor(c , fila_, columna_);
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
}
