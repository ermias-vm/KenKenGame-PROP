package main.presentacio.Ranking;

import javax.swing.*;
import java.util.ArrayList;

public class BuscadorUsuari extends JPanel {
    ArrayList<ObservadorBuscador> observadors_ = new ArrayList<>();
    public BuscadorUsuari() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel labelUsuari = new JLabel("Usuari: ");
        JTextField textBuscar = new JTextField();
        JButton botoBuscar = new JButton("Buscar");
        botoBuscar.addActionListener(e -> {
            String nom = textBuscar.getText();
            for (ObservadorBuscador o : observadors_) {
                o.busquedaUsuari(nom);
            }
        });
        this.add(labelUsuari);
        this.add(textBuscar);
        this.add(botoBuscar);
    }
    public void addObservador(ObservadorBuscador observador) {
        observadors_.add(observador);
    }

}
