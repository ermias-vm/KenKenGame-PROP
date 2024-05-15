package main.presentacio.Partida;

import main.presentacio.ObservadorBoto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_ERROR;

public class MenuPartida extends JPanel {
    private JLabel missatge_;
    private ComponentTimer timer_;
    private JButton undoBoto_;
    private JButton redoBoto_;
    private  JButton pistaBoto_;
    private boolean pistaAvisat_ = false;
    private JButton acabaBoto_;
    private JButton guardaBoto_;
    private boolean guardatAvisat_ = false;
    private JButton tancaIguardaBoto_;
    private JButton sortirBoto_;
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    public MenuPartida() {

        this.setLayout(new BorderLayout());

        JPanel panellSuperior = new JPanel();
        panellSuperior.setLayout(new BoxLayout(panellSuperior, BoxLayout.X_AXIS));

        JPanel panellCentral = new JPanel();
        panellCentral.setLayout(new BoxLayout(panellCentral, BoxLayout.Y_AXIS));

        JPanel panellInferior = new JPanel();
        panellInferior.setLayout(new BoxLayout(panellInferior, BoxLayout.X_AXIS));

        JLabel missatge = new JLabel();
        missatge_ = missatge;
        panellInferior.add(missatge);

        panellInferior.add(Box.createHorizontalGlue());

        timer_ = new ComponentTimer(0);
        panellSuperior.add(timer_);

        ImageIcon undo = new ImageIcon("/undo.png");
        undoBoto_ = new JButton(undo);
        undoBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarUndo();
        });
        panellSuperior.add(undoBoto_);

        ImageIcon redo = new ImageIcon("/redo.png");
        redoBoto_ = new JButton(redo);
        redoBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarRedo();
        });
        panellSuperior.add(redoBoto_);

        pistaBoto_ = new JButton("Pista");
        pistaBoto_.addActionListener(e -> {
            if (!pistaAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si se't dona una pista no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarPista();
                pistaAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarPista();
        });
        panellCentral.add(pistaBoto_);
        panellCentral.add(Box.createVerticalGlue());
        acabaBoto_ = new JButton("Acabar");
        acabaBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarAcabar();
        });
        panellCentral.add(acabaBoto_);

        guardaBoto_ = new JButton("Guardar");
        guardaBoto_.addActionListener(e -> {
            if (!guardatAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si guardes la partida no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarGuardar();
                guardatAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarGuardar();
        });
        panellCentral.add(guardaBoto_);

        tancaIguardaBoto_ = new JButton("Tancar i Guardar");
        tancaIguardaBoto_.addActionListener(e -> {
            if (!guardatAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si guardes la partida no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarGuardar();
                guardatAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarTancaIguarda();
        });
        panellCentral.add(tancaIguardaBoto_);

        sortirBoto_ = new JButton("Sortir");
        sortirBoto_.addActionListener(e -> {
            int decisio = JOptionPane.showOptionDialog(null, "Si surts sense guardar es perdrà tot el progrès no guardat", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
            if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarSortir();
        });
        panellInferior.add(sortirBoto_);
        this.add(panellSuperior, BorderLayout.NORTH);
        this.add(panellCentral, BorderLayout.CENTER);
        this.add(panellInferior, BorderLayout.SOUTH);
    }
    public void addObservadorBoto(ObservadorBoto observer) {
        observadorsBoto_.add(observer);
    }

    public void mostrarMissatge(String missatge, boolean bo) {
        if (bo) {
            missatge_.setForeground(Color.GREEN);
        } else {
            missatge_.setForeground(Color.decode(COLOR_ERROR));
        }
        missatge_.setText(missatge);
        int delay = 1500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                missatge_.setText("");
            }
        };
        new Timer(delay, taskPerformer).start();
    }
};
