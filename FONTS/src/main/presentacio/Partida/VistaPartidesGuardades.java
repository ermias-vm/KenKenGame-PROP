package main.presentacio.Partida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * {@code VistaPartidesGuardades} és la vista de les partides guardades d'un usuari,
 * per a seleccionar-ne una i jugar-la.
 * En una pàgina s'ensenya la mida, data i temps de les (int) NOMBREPARTIDES partides guardades de l'usuari,
 * i es pot navegar entre pàgines.
 */
public class VistaPartidesGuardades  extends ComponentLlistaPartides {
    /**
     * Crea una vista de les partides guardades amb les partides guardades de l'usuari.
     * @param partidesGuardades Array de partides guardades de l'usuari.
     */
    public VistaPartidesGuardades(String[] partidesGuardades, int NOMBREPARTIDES) {
        super(partidesGuardades, NOMBREPARTIDES, new String[]{"ID: Tauler-Mida", "Data", "Temps"});
        this.setBorder(new EmptyBorder(15, 20, 15, 20));

    }
    /**
     * Genera la mida, data i temps d'una partida guardada.
     * @param partidaGuardada Partida guardada a analitzar.
     * @return Array de Strings amb la mida, data i temps de la partida guardada.
     */
    @Override
    protected String[] generaText(String partidaGuardada){
        String[] parts = partidaGuardada.split("\n");
        String identificador = parts[0];
        String[] identificadorDividit = identificador.split(":");
        StringBuilder data = new StringBuilder();
        for (int j = 1; j < identificadorDividit.length; ++j) {
            data.append(identificadorDividit[j]);
        }
        String dataString = data.toString();
        String[] dataDividida = dataString.split("T");
        String dia = dataDividida[0];
        String[] diaDividit = dia.split("-");
        String hora = dataDividida[1];
        char[] horaDividida = hora.toCharArray();
        String dataFormat = String.valueOf(new StringBuilder( diaDividit[2]+"-"+diaDividit[1]+"-"+diaDividit[0]+ " " + horaDividida[0] + horaDividida[1] + ":" + horaDividida[2] + horaDividida[3]));
        String temps = parts[2] + " s";;
        String identificadorTauler = parts[1];
        return new String[]{identificadorTauler, dataFormat, temps};
    }
}
