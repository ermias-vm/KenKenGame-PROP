package main.presentacio.Partida;

import main.presentacio.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * {@code VistaMenuJugarPartida} és la vista del menú de jugar partida.
 * És el menú que es mostra quan es vol jugar una partida, previ a la vista de {@link VistaPartida}.
 */
public class VistaMenuJugarPartida extends JPanel {
    /**
     * {@code ObservadorBoto} és una llista d'observadors que notificaran quan es premi un botó i quin mètode per tractar-ho.
     */
    private ArrayList<ObservadorBoto> observadorsBoto_ = new ArrayList<>();
    /**
     * {@code identificadorUsuari_} és l'identificador de l'usuari que ha iniciat sessió.
     */
    private String identificadorUsuari_;

    /**
     * Crea una nova VistaMenuJugarPartida.
     * Aquesta vista conté els següents botons:
     * Introduir Tauler Manualment: permet introduir un tauler manualment.
     * Introduir Identificador Tauler: permet introduir un identificador de tauler per jugar-lo.
     * Partida Aleatoria: permet jugar una partida amb un tauler aleatori de certa mida.
     * Última Partida Guardada: permet jugar l'última partida guardada.
     * Partida Guardada: permet jugar una partida guardada.
     * Tornar: permet tornar a la vista anterior. (Menú principal)
     */
    VistaMenuJugarPartida() {
        this.setLayout((new BorderLayout()));
        JPanel panellCentral = new JPanel();
        panellCentral.setLayout(new GridBagLayout());
        JPanel panellMenu = new JPanel();
        panellMenu.setLayout(new BorderLayout());
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());

        GridBagConstraints botoConstraints = new GridBagConstraints();
        botoConstraints.insets = new Insets(20, 0, 10, 0);
        botoConstraints.gridx = 1;
        botoConstraints.gridy = GridBagConstraints.RELATIVE; // this will set the gridy value to the next available row
        botoConstraints.weightx = 1;
        botoConstraints.weighty = 1;
        botoConstraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints panelBuitEsquerra = new GridBagConstraints();
        panelBuitEsquerra.gridx = 0;
        panelBuitEsquerra.gridy = GridBagConstraints.RELATIVE;
        panelBuitEsquerra.weightx = 0.17;
        panelBuitEsquerra.weighty = 1;
        panelBuitEsquerra.fill = GridBagConstraints.NONE;

        GridBagConstraints panelBuitDreta = new GridBagConstraints();
        panelBuitDreta.gridx = 2;
        panelBuitDreta.gridy = GridBagConstraints.RELATIVE;
        panelBuitDreta.weightx = 0.17;
        panelBuitDreta.weighty = 1;
        panelBuitDreta.fill = GridBagConstraints.NONE;
        JButton introduirTaulerManualment = new JButton("Introduir Tauler Manualment");
        introduirTaulerManualment.setFont(new Font("Arial", Font.PLAIN, 20));
        introduirTaulerManualment.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarIntroduirTaulerManualment();
        });
        JButton introduirIdentificadorTauler = new JButton("Introduir Identificador Tauler");
        introduirIdentificadorTauler.setFont(new Font("Arial", Font.PLAIN, 20));
        introduirIdentificadorTauler.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarIntroduirIdentificadorTauler();
        });
        JButton partidaAleatoria = new JButton("Partida Aleatoria");
        partidaAleatoria.setFont(new Font("Arial", Font.PLAIN, 20));
        partidaAleatoria.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarPartidaAleatoria();
        });
        JButton ultimaPartidaGuardada = new JButton("Ultima Partida Guardada");
        ultimaPartidaGuardada.setFont(new Font("Arial", Font.PLAIN, 20));
        ultimaPartidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarUltimaPartidaGuardada();
        });
        JButton partidaGuardada = new JButton("Partida Guardada");
        partidaGuardada.setFont(new Font("Arial", Font.PLAIN, 20));
        partidaGuardada.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.jugarPartidaGuardada();
        });
        JButton tornar = new JButton("Tornar");
        tornar.addActionListener(e -> {
            for (ObservadorBoto ob : observadorsBoto_) ob.notificarSortir();
        });

        menu.add(new JPanel(), panelBuitEsquerra);
        menu.add(new JPanel(), panelBuitDreta);

        menu.add(introduirTaulerManualment, botoConstraints);
        botoConstraints.insets = new Insets(10, 0, 10, 0);
        menu.add(introduirIdentificadorTauler, botoConstraints);

        menu.add(partidaAleatoria, botoConstraints);

        menu.add(ultimaPartidaGuardada, botoConstraints);


        menu.add(partidaGuardada, botoConstraints);
        panellMenu.add(menu, BorderLayout.CENTER);
        JPanel tornarPanel = new JPanel();
        tornarPanel.setLayout(new BorderLayout());
        tornarPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
        JPanel tornarPanelMargins = new JPanel();
        tornarPanelMargins.add(tornar, BorderLayout.CENTER);
        tornarPanelMargins.setBorder(new EmptyBorder(10, 0, 10, 10));
        tornarPanel.add(tornarPanelMargins, BorderLayout.EAST);
        panellMenu.add(tornarPanel, BorderLayout.SOUTH);
        ImageIcon imatgeFons = Utils.carregarImatge("resources/imatges/fonsKenken.png", 800, 800);
        JLabel fons = new JLabel(imatgeFons);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panellCentral.add(fons, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.34;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        panellCentral.add(panellMenu, gbc);
        this.add(panellCentral, BorderLayout.CENTER);

    }
    /**
     * Afegeix un observador de botó.
     * @param ob Observador de botó a afegir.
     */
    public void addObservadorBoto(ObservadorBoto ob) {
        observadorsBoto_.add(ob);
    }

    /**
     * Actualitza l'identificador de l'usuari.
     * @param identificadorUsuari Identificador de l'usuari.
     */
    public void setUsuari(String identificadorUsuari) {
        identificadorUsuari_ = identificadorUsuari;
    }
    /**
     * Retorna l'identificador de l'usuari.
     * @return Identificador de l'usuari.
     */
    public String getUsuari() {
        return identificadorUsuari_;
    }
}
