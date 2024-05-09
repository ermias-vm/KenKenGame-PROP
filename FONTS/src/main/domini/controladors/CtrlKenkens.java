package main.domini.controladors;

import main.domini.classes.SolucionadorKenken;
import main.domini.classes.Tauler;
import main.domini.excepcions.ExcepcioCasellaNoExisteix;
import main.domini.interficies.Operacio;
import main.domini.classes.operacions.*;
import main.domini.classes.Casella;
import main.domini.classes.Regio;
import main.persistencia.ControladorPersistenciaTauler;

import java.util.ArrayList;


public class CtrlKenkens {
    private  ControladorPersistenciaTauler controladorPersistenciaTauler_;
    private SolucionadorKenken AS = new SolucionadorKenken();
    public CtrlKenkens(ControladorPersistenciaTauler controladorPersistenciaTauler) {
        controladorPersistenciaTauler_ = controladorPersistenciaTauler;
    }
    private Operacio getOperacio(int oper) {
        switch (oper) {
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
                //System.out.println("SENSE OPERACIO");
                return null;
        }
    }


    public Tauler llegirTauler(String id) {
        try {
            String content = controladorPersistenciaTauler_.llegirTauler(id);
            //System.out.println(content); //imprimir prova
            String[] lines = content.split("\n");
            String[] primeraLinea = lines[0].split(" ");
            int N = Integer.parseInt(primeraLinea[0]);
            int R = Integer.parseInt(primeraLinea[1]);


            Tauler T = new Tauler(id, N);

            //Llegir cada regio
            for (int i = 0; i < R; i++) {
                String[] regioInfo = lines[i+1].split(" ");
                //System.out.println(Arrays.toString(regioInfo));
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
                    casella.setPermutacions(elements);
                    j += 2;
                    if (j < regioInfo.length && regioInfo[j].startsWith("[")) {
                        String valorString = regioInfo[j].replaceAll("[\\[\\]]", "");
                        casella.setValor(Integer.parseInt(valorString));
                        casella.setInmodificable();
                        ++j;
                    }
                    caselles.add(casella);
                    T.afegirCasella(x, y, casella);
                }

                Operacio operacio = getOperacio(oper);
                //System.out.print(operacio.getNumOperacio());
                Regio rj = new Regio(caselles, operacio, result);
                T.afegirRegioJoc(rj);
            }
            return T;
        } catch (ExcepcioCasellaNoExisteix e) {
            throw new RuntimeException(e);
        }
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
    /*
    public void pintarTauler(String idTauler, String grau) throws Exception {
        T = CK.llegirTauler(idTauler);
        System.out.println("Contingut del Tauler " + idTauler + " de grau " + grau + ":");
        CK.mostrarTauler(T);
    }
    */
    public int[][] resoldreKenken(Tauler T, int[][] valorsPartida) throws Exception {
        for (int i = 0; i < valorsPartida.length; i++) {
            for (int j = 0; j < valorsPartida[i].length; j++) {
                int x = i + 1;
                int y = j + 1;
                int valor = valorsPartida[i][j];
                T.setValor(x, y, valor);
            }
        }
        AS.solucionarKenken(T);
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
    /*
    public void resoldreKenken(Tauler T) throws Exception {
        int grau = Integer.parseInt(idTauler.split("-")[1]);
        Tauler T = llegirTauler(idTauler);
        System.out.println("Contingut del Tauler " + idTauler + " de grau " + grau + ":");
        mostrarTauler(T);
        AS.solucionarKenken(T);
        if (T.teSolucio()) {
            System.out.println("Tauler resolt:");
            CK.mostrarTauler(T);
        }
        else {
            System.out.println("El tauler no té solució."+ "\n");
            CK.mostrarTauler(T);
        }
    }
    */
}