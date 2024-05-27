package main.presentacio.Ranking;

import static main.domini.controladors.ControladorRanking.MIDAMIN;
import static main.domini.controladors.ControladorRanking.MIDAMAX;

import javax.swing.*;
import java.util.ArrayList;

/**
 * ComponentSelectorMida és un JPanel que conté un selector de mida de tauler.
 * Això permet seleccionar la mida dels taulers que es volen veure al ranking. Es selecciona amb un JSpinner.
 */
public class ComponentSelectorMida extends JPanel {
    /**
     * Llista d'observadors del selector de mida.
     */
    private ArrayList<ObservadorSelectorMida> observadors_ = new ArrayList<>();

    /**
     * Constructora per defecte.
     */
    public ComponentSelectorMida() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel labelMida = new JLabel("Ranking dels taulers de mida: ");
        int midaMinima = MIDAMIN;
        int midaMaxima = MIDAMAX;
        ArrayList<String> opcionsArrayList = new ArrayList<>();
        for (int i = midaMinima; i <= midaMaxima; ++i) {
            opcionsArrayList.add(Integer.toString(i));
        }
        String[] opcions = opcionsArrayList.toArray(new String[0]);
        SpinnerModel model = new SpinnerListModel(opcions);
        JSpinner selectorMida = new JSpinner(model);
        selectorMida.addChangeListener(e -> {
            String mida = (String) selectorMida.getValue();
            for (ObservadorSelectorMida o : observadors_) {
                o.midaSeleccionada(mida);
            }
        });
        this.add(labelMida);
        this.add(selectorMida);
    }
    /**
     * Afegeix un observador al selector de mida.
     * @param observador Observador que es vol afegir.
     */
    public void addObservador(ObservadorSelectorMida observador) {
        observadors_.add(observador);
    }
}
