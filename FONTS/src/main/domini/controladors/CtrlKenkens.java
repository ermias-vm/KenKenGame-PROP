package main.domini.controladors;

import main.domini.classes.SolucionadorKenken;
import main.domini.classes.Tauler;
import main.domini.excepcions.*;
import main.domini.classes.operacions.Operacio;
import main.domini.classes.operacions.*;
import main.domini.classes.Casella;
import main.domini.classes.Regio;
import java.util.ArrayList;

/**
 * Controlador de Kenkens que gestiona les operacions relacionades amb els jocs de Kenken.
 * S'encarrega de llegir, guardar, resoldre i validar taulers de Kenken.
 * @autor Ermias Valls Mayor
 */
public class CtrlKenkens {

    /**
     * Instància de SolucionadorKenken per a la resolució de taulers de Kenken.
     */
    private static SolucionadorKenken Solucionador;

    /**
     * Instància singleton de CtrlKenkens.
     */
    private static CtrlKenkens ctrlKenkens;

    /**
     * Constructor privat de la classe CtrlKenkens.
     * Inicialitza l'instància de CtrlDomini.
     */
    private CtrlKenkens() {
        Solucionador  = SolucionadorKenken.getInstance();
    }

    /**
     * Retorna la instància singleton de CtrlKenkens.
     *
     * @return Instància de CtrlKenkens.
     */
    public static CtrlKenkens getInstance() {
        if (ctrlKenkens == null) ctrlKenkens = new CtrlKenkens();
        return ctrlKenkens;
    }

    /**
     * Retorna l'operació corresponent al número d'operació proporcionat.
     *
     * @param operacio  Número d'operació.
     * @return Operació corresponent.
     */
    private Operacio getOperacio(int operacio) {
        switch (operacio) {
            case 1:
                return new Suma();
            case 2:
                return new Resta();
            case 3:
                return new Multiplicacio();
            case 4:
                return new Divisio();
            case 5:
                return new Modul();
            case 6:
                return new Exponenciacio();
            default:
                return null;
        }
    }

    /**
     * Llegeix un tauler de la base de dades amb l'identificador proporcionat.
     *
     * @param id  Identificador del tauler.
     * @return Tauler llegit.
     */
    public Tauler llegirTauler(String id) throws ExcepcioTaulerNoExisteix {
        String contingutTauler = CtrlDomini.getInstance().llegirTaulerPersistencia(id);
        return stringToTauler(contingutTauler, id);

    }

    /**
     * Converteix una cadena de text a un Tauler.
     *
     * @param contingutTauler  Contingut del tauler en format de text.
     * @param id  Identificador del tauler.
     * @return Classe Tauler.
     */
    public Tauler stringToTauler(String contingutTauler, String id) {
        try {
            String[] lines = contingutTauler.split("\n");
            String[] primeraLinea = lines[0].split(" ");
            int N = Integer.parseInt(primeraLinea[0]);
            int R = Integer.parseInt(primeraLinea[1]);

            Tauler T = new Tauler(id, N);
            //Llegir cada regio
            for (int i = 0; i < R; i++) {
                String[] regioInfo = lines[i+1].split(" ");
                int oper = Integer.parseInt(regioInfo[0]);
                int result = Integer.parseInt(regioInfo[1]);
                int elements = Integer.parseInt(regioInfo[2]);
                ArrayList<Casella> caselles = new ArrayList<>();
                int j = 3;
                //Llegir cada casella de la regio
                while(j < regioInfo.length) {
                    int x = Integer.parseInt(regioInfo[j]);
                    int y = Integer.parseInt(regioInfo[j+1]);
                    Casella casella = new Casella(x, y);
                    j += 2;
                    if (j < regioInfo.length && regioInfo[j].startsWith("[")) {
                        String valorString = regioInfo[j].replaceAll("[\\[\\]]", "");
                        casella.setValor(Integer.parseInt(valorString));
                        if (elements > 1) casella.setImmodificable();
                        ++j;
                    }
                    caselles.add(casella);
                    T.afegirCasella(x, y, casella);
                }

                Operacio operacio = getOperacio(oper);
                Regio rj = new Regio(caselles, operacio, result);
                T.afegirRegio(rj);
            }
            return T;
        } catch (ExcepcioCasellaNoExisteix e) {
            throw new RuntimeException(e);
        } catch (ExcepcioCasellaNoModificable e) {
            throw new RuntimeException(e);
        } catch (ExcepcioMidaIncorrecte e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converteix un tauler a una cadena de text.
     *
     * @param T  Tauler a convertir.
     * @return Tauler convertit en format de text.
     */
    public String taulerToString(Tauler T) {
        StringBuilder sb = new StringBuilder();
        sb.append(T.getGrau()).append(" ").append(T.getRegions().size()).append("\n");
        for (Regio r : T.getRegions()) {
            if (r.getOperacio() == null) sb.append("0");
            else sb.append(r.getOperacio().getNumOperacio());
            sb.append(" ").append(r.getResultat()).append(" ").append(r.getMida());
            for (Casella c : r.getCaselles()) {
                sb.append(" ").append(c.getPosX()).append(" ").append(c.getPosY());
                if (c.getValor() != 0) sb.append(" [").append(c.getValor()).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * Selecciona un tauler aleatori de la mida proporcionada.
     *
     * @param mida  Mida del tauler.
     * @return Identificador del tauler seleccionat.
     */
    public String seleccionaTaulerAleatori(int mida) {
        //Sha de fer desde Domini
        return CtrlDomini.getInstance().seleccionaTaulerAleatoriPersistencia(mida);
    }


    /**
     * Resol un tauler de Kenken amb els valors de partida proporcionats i retorna la solució.
     *
     * @param T  Tauler de Kenken a resoldre.
     * @param valorsPartida  Valors de partida.
     * @return Matriu de valors de la solució, o null si no hi ha solució.
     */
    public int[][] resoldreKenken(Tauler T, int[][] valorsPartida) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        for (int i = 0; i < valorsPartida.length; i++) {
            for (int j = 0; j < valorsPartida[i].length; j++) {
                int x = i + 1;
                int y = j + 1;
                int valor = valorsPartida[i][j];
                T.setValorIbloquejar(x, y, valor);
                //T.setValor(x, y, valor);
            }
        }
        T.setTrobat(false);
        Solucionador.solucionarKenken(T);
        if (T.teSolucio()) {
            int[][] valorsSolucio = new int[valorsPartida.length][valorsPartida[0].length];
            for (int i = 0; i < valorsPartida.length; i++) {
                for (int j = 0; j < valorsPartida[i].length; j++) {
                    int x = i + 1;
                    int y = j + 1;
                    valorsSolucio[i][j] = T.getValor(x, y);
                }
            }
            return valorsSolucio;
        }
        return null;
    }

    /**
     * Guarda un tauler a la base de dades.
     *
     * @param contigutTauler  Contingut del tauler en format de text.
     * @return Identificador del tauler guardat.
     */
    public String guardarTaulerBD(String contigutTauler) {
        String idTauler = CtrlDomini.getInstance().generaIdentificadorIGuardaTaulerPersistencia(contigutTauler);
        return idTauler;
    }

    /**
     * Comprova si un tauler és vàlid.
     *
     * @param contingutTauler  Contingut del tauler en format de text.
     * @return Cert si el tauler és vàlid, fals altrament.
     */
    public boolean esTaulerValid(String contingutTauler) {
        try {
            Tauler T = stringToTauler(contingutTauler, "temporal");

            long startTime = System.currentTimeMillis();
            Solucionador.solucionarKenken(T);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("\nTemps de resoldre: " + duration + " ms.");
            if (T.teSolucio()) {
                System.out.println("Tauler resolt:");
                mostrarTauler(T);
            }
            else {
                System.out.println("El tauler no té solució."+ "\n");
                mostrarTauler(T);
            }
            return T.teSolucio();
        } catch (Exception e) {
            return false;
        }
    }


    ///////////////////////////////  FUNCIONS DIRVERS  ///////////////////////////////////

    public void resoldreKenken(String idTauler, boolean guardarBD) throws Exception {
        Tauler T = llegirTauler(idTauler);


        long startTime = System.currentTimeMillis();
        Solucionador.solucionarKenken(T);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (T.teSolucio()) {
            System.out.println("Tauler " +idTauler + " resolt en " + duration + " ms:");
            mostrarTauler(T);
        }
        else {
            System.out.println("El tauler " +idTauler + " no té solució en "+ duration+ "\n");
            mostrarTauler(T);
        }
        if (guardarBD) {
            guardarTaulerBD(taulerToString(T));
        }
    }

    public void pintarTauler(String idTauler) throws Exception {
        Tauler T = llegirTauler(idTauler);
        System.out.println("Contingut del Tauler " + idTauler + ":");
        mostrarTauler(T);
    }

    public void mostrarTauler(Tauler T) throws Exception {
        int grau = T.getGrau();
        for (int i = 1; i <= grau; i++) {
            for (int j = 1; j <= grau; j++) {
                int valor = T.getCasella(i, j).getValor();
                System.out.print(valor + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}