package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaulerConstrutor extends JPanel {
    private static TaulerConstrutor taulerConstrutor;
    private int mida;
    private int numCasellesAssignades = 0;
    private CasellaConstructora[][] caselles;
    private ArrayList<String> dadesRegions = new ArrayList<>();
    private ArrayList<int[]> posCaselles = new ArrayList<>();

    private  TaulerConstrutor(int mida) {
        super(new GridLayout(mida,mida));
        this.mida = mida;
        inicialitzarCaselles(mida);

    }

    public static TaulerConstrutor getInstance() {
        return taulerConstrutor;
    }

    public static void newInstance(int mida) {
            taulerConstrutor = new TaulerConstrutor(mida);
    }

    private void inicialitzarCaselles(int mida) {
        caselles = new CasellaConstructora[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                CasellaConstructora casella = new CasellaConstructora(i,j);
                caselles[i][j] = casella;
                this.add(casella);
            }
        }
    }

    public int getMida() {
        return mida;
    }

    public int getNumCasellesAssignades() {
        return numCasellesAssignades;
    }

    public String getContigutTauler() {
        StringBuilder contingutTauler = new StringBuilder();
        contingutTauler.append(caselles.length).append(" ").append(dadesRegions.size()).append("\n");
        for (String s : dadesRegions) {
            contingutTauler.append(s);
            contingutTauler.append("\n");
        }
        return contingutTauler.toString();
    }

    public void afegirPosCasella (int x, int y) {
        posCaselles.add(new int[]{x, y});
    }

    public void eliminarPosCasella (int x, int y) {
        for (int i = 0; i < posCaselles.size(); i++) {
            if (posCaselles.get(i)[0] == x && posCaselles.get(i)[1] == y) {
                posCaselles.remove(i);
                break;
            }
        }
    }

    public void assignarCasellesRegio(String operacio, String resultat) {
        StringBuilder infoRegio = new StringBuilder();
        infoRegio.append(traduirOperacio(operacio)).append(" ");
        infoRegio.append(resultat).append(" ");
        infoRegio.append(posCaselles.size());

        for (int[] pos : posCaselles) {
            CasellaConstructora casella = caselles[pos[0]][pos[1]];
            casella.marcaComRegio(dadesRegions.size());
            infoRegio.append(" ").append(pos[0]+1).append(" ").append(pos[1]+1);
        }

        dadesRegions.add(infoRegio.toString().trim());
        imprimirDadesRegio();
        numCasellesAssignades += posCaselles.size();
        posCaselles.clear();

    }

    private String traduirOperacio(String operacio) {
        switch (operacio) {
            case "SUMA":
                if(posCaselles.size() == 1) return "0";
                return "1";
            case "RESTA":
                return "2";
            case "MULT":
                return "3";
            case "DIV":
                return "4";
            case "EXP":
                return "5";
            case "MOD":
                return "6";
            default:
                return "-1";
        }
    }

    public void imprimirDadesRegio() {
        System.out.println("Dades de les regions:");
        for (String infoRegio : dadesRegions) {
            System.out.println(infoRegio);
        }
    }

}
