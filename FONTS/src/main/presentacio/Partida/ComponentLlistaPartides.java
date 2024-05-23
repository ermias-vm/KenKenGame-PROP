package main.presentacio.Partida;

/*
public class ComponentLlistaPartides extends JLayeredPane implements ActionListener {
    private final int NOMBREPARTIDES_ = 10;
    private String[] informacioPartides_;
    private int indexActual_ = 0;
    private int maxIndex_ = 0;
    private JButton[] botonsPartida_;
    private JLabel[] labelsPartida_;
    private JButton previousN_;
    private JButton nextN_;

    public ComponentLlistaPartides(String[] partidesGuardades) {
        informacioPartides_ = partidesGuardades;
        maxIndex_ = partidesGuardades.length / NOMBREPARTIDES_;
        if (partidesGuardades.length % NOMBREPARTIDES_ == 0) maxIndex_ -= 1;
    }

    public void setIndexActual(int indexActual) {
        indexActual_ = indexActual;
    }

    public void actualitzaVista() {
        removeAll();
        int n = Math.min(NOMBREPARTIDES_, informacioPartides_.length - indexActual_ * NOMBREPARTIDES_);
        botonsPartida_ = new JButton[n];
        labelsPartida_ = new JLabel[n];
        for (int i = 0; i < n; ++i) {
            botonsPartida_[i] = new JButton("Partida " + (indexActual_ * NOMBREPARTIDES_ + i + 1));
            botonsPartida_[i].setBounds(10, 10 + 30 * i, 100, 20);
            add(botonsPartida_[i], JLayeredPane.DEFAULT_LAYER);
            labelsPartida_[i] = new JLabel(informacioPartides_[indexActual_ * NOMBREPARTIDES_ + i]);
            labelsPartida_[i].setBounds(120, 10 + 30 * i, 200, 20);
            add(labelsPartida_[i], JLayeredPane.DEFAULT_LAYER);
        }
        if (indexActual_ > 0) {
            previousN_ = new JButton("Anterior");
            previousN_.setBounds(10, 10 + 30 * n, 100, 20);
        }
    }
}
*/