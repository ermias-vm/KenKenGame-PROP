package main.presentacio.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_BE;
import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_ERROR;

/**
 * {code MenuPartida} és un panell que conté els botons i el rellotge de la partida.
 */
public class MenuPartida extends JPanel {
    /**
     * {code missatge_} és un missatge que es mostra a l'usuari en situacions concretes.
     */
    private JLabel missatge_;
    /**
     * {code timer_} és el rellotge de la partida. {@link ComponentTimer}
     */
    private ComponentTimer timer_;
    /**
     * {code undoBoto_} és el botó per desfer l'última jugada.
     */
    private JButton undoBoto_;
    /**
     * {code redoBoto_} és el botó per refer l'última jugada.
     */
    private JButton redoBoto_;
    /**
     * {code pistaBoto_} és el botó per demanar una pista.
     */
    private  JButton pistaBoto_;
    /**
     * {code pistaAvisat_} és un booleà que indica si s'ha avisat a l'usuari que si demana una pista no podrà participar en els rankings.
     */
    private boolean pistaAvisat_ = false;
    /**
     * {code acabaBoto_} és el botó per acabar la partida.
     */
    private JButton acabaBoto_;
    /**
     * {code guardaBoto_} és el botó per guardar la partida.
     */
    private JButton guardaBoto_;
    /**
     * {code guardatAvisat_} és un booleà que indica si s'ha avisat a l'usuari que si guarda la partida no podrà participar en els rankings.
     */
    private boolean guardatAvisat_ = false;
    /**
     * {code tancaIguardaBoto_} és el botó per tancar la partida i guardar-la.
     */
    private JButton tancaIguardaBoto_;
    /**
     * {code sortirBoto_} és el botó per sortir de la partida en qualsevol moment.
     */
    private JButton sortirBoto_;
    /**
     * {code observadorsBoto_} és una llista d'observadors que estan pendents dels esdeveniments dels botons.
     */
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();

    /**
     * Creadora de la classe. Crea cada component del panell i els afegeix al panell.
     */
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

    /**
     * Afegeix un observador a la llista d'observadors.
     * @param observer Observador a afegir.
     */
    public void addObservadorBoto(ObservadorBoto observer) {
        observadorsBoto_.add(observer);
    }

    /**
     * Mostra un missatge a l'usuari. Si el missatge és "dolent" es mostrarà en vermell, si és "bo" es mostrarà en verd.
     * Després d'1,5 segons el missatge desapareixerà.
     * @param missatge Missatge a mostrar.
     * @param bo Booleà que indica si el missatge és "dolent" o "bó".
     */
    public void mostrarMissatge(String missatge, boolean bo) {
        if (bo) {
            missatge_.setForeground(Color.decode(COLOR_BE));
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
