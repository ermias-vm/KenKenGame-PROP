package main.presentacio.Ranking;

import main.presentacio.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

/**
 * Aquesta classe és un panell que conté un camp de text per a introduir un nom d'usuari i un botó per a buscar-lo.
 * Si es deixa en blanc el camp de text, es buscarà tots els usuaris.
 */
public class BuscadorUsuari extends JPanel {
    /**
     * Llista d'observadors del botó de buscar.
     */
    ArrayList<ObservadorBuscador> observadors_ = new ArrayList<>();

    /**
     * Constructora per defecte.
     */
    public BuscadorUsuari() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel labelUsuari = new JLabel("Usuari: ");
        JTextField textBuscar = new JTextField("en blanc -> tots els usuaris");
        textBuscar.setMinimumSize(new Dimension(180, 25));
        textBuscar.setMaximumSize(new Dimension(180, 25));
        textBuscar.setPreferredSize(new Dimension(180, 25));

        textBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textBuscar.getText().equals("en blanc -> tots els usuaris")) {
                    textBuscar.setText("");
                }
            }

        });

        JButton botoBuscar = Utils.crearBotoPersonalitzat(100,25,"Buscar", Color.WHITE, Utils.COLOR_BOTO_BLAU, false);
        botoBuscar.addActionListener(e -> {
            String nom = textBuscar.getText();
            for (ObservadorBuscador o : observadors_) {
                o.cercaUsuari(nom);
            }
        });

        this.add(labelUsuari);
        this.add(textBuscar);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(botoBuscar);
    }

    /**
     * Afegeix un observador al botó de buscar.
     * @param observador Observador que es vol afegir.
     */
    public void addObservador(ObservadorBuscador observador) {
        observadors_.add(observador);
    }

}
