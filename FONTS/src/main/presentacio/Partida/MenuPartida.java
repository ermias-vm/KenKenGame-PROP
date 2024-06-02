package main.presentacio.Partida;


import main.presentacio.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_BE;
import static main.presentacio.Partida.ControladorPresentacioPartida.COLOR_ERROR;
import static main.presentacio.Utils.carregarImatge;
import static main.presentacio.Utils.crearBotoPersonalitzat;

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
    public MenuPartida(int tempsInicial){
        this.setLayout(new BorderLayout());
        JPanel panellGridBag = new JPanel();
        panellGridBag.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        timer_ = new ComponentTimer(tempsInicial);
        panellGridBag.add(timer_, gbc);

        ImageIcon undo = carregarImatge("resources/imatges/undo.png", 50, 50);
        undoBoto_ =crearBotoPersonalitzat(80, 80, "", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        undoBoto_.setIcon(undo);
        undoBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarUndo();
        });
        gbc.gridx = 1;
        gbc.insets = new Insets(20, 10, 0, 10);
        panellGridBag.add(undoBoto_, gbc);

        ImageIcon redo = carregarImatge("resources/imatges/redo.png", 50, 50);
        redoBoto_ =crearBotoPersonalitzat(80, 80, "", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        redoBoto_.setIcon(redo);
        redoBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarRedo();
        });
        gbc.gridx = 2;
        gbc.insets = new Insets(20, 10, 0, 20);
        panellGridBag.add(redoBoto_, gbc);

        pistaBoto_ = crearBotoPersonalitzat(100, 80, "Pista", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        pistaBoto_.setFont(new Font("Dialog", Font.PLAIN, 25));
        pistaBoto_.addActionListener(e -> {
            if (!pistaAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si se't dona una pista no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarPista();
                pistaAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarPista();
        });
        gbc.insets = new Insets(100, 40, 20, 40);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panellGridBag.add(pistaBoto_, gbc);

        acabaBoto_ = crearBotoPersonalitzat(100, 80, "Acabar", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        acabaBoto_.setFont(new Font("Dialog", Font.PLAIN, 25));
        acabaBoto_.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarAcabar();
        });
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 40, 20, 40);
        panellGridBag.add(acabaBoto_, gbc);

        guardaBoto_ = crearBotoPersonalitzat(100, 80, "Guardar", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        guardaBoto_.setFont(new Font("Dialog", Font.PLAIN, 25));
        guardaBoto_.addActionListener(e -> {
            if (!guardatAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si guardes la partida no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarGuardar();
                guardatAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarGuardar();
        });
        gbc.gridy = 3;
        panellGridBag.add(guardaBoto_, gbc);

        tancaIguardaBoto_ = crearBotoPersonalitzat(100, 80, "Tancar i Guardar", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        tancaIguardaBoto_.setFont(new Font("Dialog", Font.PLAIN, 25));
        tancaIguardaBoto_.addActionListener(e -> {
            if (!guardatAvisat_) {
                int decisio = JOptionPane.showOptionDialog(null, "Si guardes la partida no podràs participar als rankings", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
                if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarTancaIguarda();
                guardatAvisat_ = true;
            }
            else for (ObservadorBoto ob : observadorsBoto_) ob.notificarTancaIguarda();
        });
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 40, 40, 40);
        panellGridBag.add(tancaIguardaBoto_, gbc);
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridwidth = 2;
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        JLabel missatge = new JLabel();
        missatge.setHorizontalAlignment(SwingConstants.CENTER);
        missatge_ = missatge;
        panellGridBag.add(missatge, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        sortirBoto_ = crearBotoPersonalitzat(100, 40, "Sortir", Color.WHITE, Utils.COLOR_BOTO_BLAU, true);
        sortirBoto_.addActionListener(e -> {
            int decisio = JOptionPane.showOptionDialog(null, "Si surts sense guardar es perdrà tot el progrès no guardat", null, JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE, null, new Object[]{"Acceptar", "Cancel·lar"}, null);
            if (decisio == 0) for (ObservadorBoto ob : observadorsBoto_) ob.notificarSortir();
        });
        panellGridBag.add(sortirBoto_, gbc);
        this.add(panellGridBag, BorderLayout.CENTER);
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
            String color = COLOR_BE;
            String[] parts = color.split(",");
            missatge_.setForeground(new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        } else {
            String color = COLOR_ERROR;
            String[] parts = color.split(",");
            missatge_.setForeground(new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        missatge_.setText(missatge);
        int delay = 1500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                missatge_.setText("");
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
};
