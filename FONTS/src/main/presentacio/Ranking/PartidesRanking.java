package main.presentacio.Ranking;

public class PartidesRanking extends main.presentacio.Partida.ComponentLlistaPartides{
    /**
     * Crea una llista de partides amb la informació donada i el nombre de partides a mostrar per pàgina.
     *
     * @param informacioPartides Array de Strings amb la informació de les partides.
     * @param NOMBREPARTIDES  Nombre de partides a mostrar per pàgina.
     */
    public PartidesRanking(String[] informacioPartides, int NOMBREPARTIDES) {
        super(informacioPartides, NOMBREPARTIDES, new String[]{"Usuari", "Data", "ID Tauler-Mida", "Temps"});
    }

    /**
     * A partir de la informació d'una partida acabada amb el format: identificadorPartida_ + " " + identificadorUsuari_ + " " + identificadorTauler_ + " " + temps_
     * genera l'usuari, data, identificador del tauler i temps de la partida.
     * @param partidaAcabada Informació de la partida.
     * @return Array de Strings amb l'usuari, data, identificador del tauler i temps de la partida.
     */
    @Override
    protected String[] generaText(String partidaAcabada) {
        String[] parts = partidaAcabada.split(" ");
        String identificador = parts[0];
        String[] identificadorDividit = identificador.split(":");
        StringBuilder data = new StringBuilder();
        for (int j = 1; j < identificadorDividit.length; ++j) {
            data.append(identificadorDividit[j]);
        }
        String dataString = data.toString();
        String[] dataDividida = dataString.split("T");
        String dataFormat = String.valueOf(new StringBuilder(dataDividida[0] + " " + dataDividida[1]));
        String identificadorUsuari = parts[1];
        String identificadorTauler = parts[2];
        String temps = parts[3];
        return new String[]{identificadorUsuari, dataFormat,identificadorTauler, temps};
    }
}
