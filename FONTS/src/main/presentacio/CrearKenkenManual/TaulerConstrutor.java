package main.presentacio.CrearKenkenManual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaulerConstrutor extends JPanel {
    private static TaulerConstrutor taulerConstrutor;
    private int mida;
    private int numCasellesAssignades;
    private CasellaConstructora[][] caselles;
    private ArrayList<String> dadesRegions = new ArrayList<>();
    private ArrayList<int[]> posCaselles = new ArrayList<>();

    private  TaulerConstrutor(int mida) {
        super(new GridLayout(mida,mida));
        this.mida = mida;
        this.numCasellesAssignades = 0;
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

    public int getNumCasellesSelecionades() {
        return posCaselles.size();
    }

    public boolean esModificat() {
        return (numCasellesAssignades > 0 || getNumCasellesSelecionades() > 0);
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
        infoRegio.append(traduirOperacioAnum(operacio)).append(" ");
        infoRegio.append(resultat).append(" ");
        infoRegio.append(posCaselles.size());

        ordenarPosicionsCaselles();

        int[] primeraPos = posCaselles.get(0);

        for (int[] pos : posCaselles) {
            CasellaConstructora casella = caselles[pos[0]][pos[1]];
            casella.marcaComRegio(dadesRegions.size());
            infoRegio.append(" ").append(pos[0]+1).append(" ").append(pos[1]+1);
        }

        CasellaConstructora primeraCasella = caselles[primeraPos[0]][primeraPos[1]];
        primeraCasella.addInfoRegio(resultat ,traduirOperacioAsimbol(operacio), mida);

        dadesRegions.add(infoRegio.toString().trim());
        imprimirDadesRegio();
        numCasellesAssignades += posCaselles.size();
        posCaselles.clear();
    }

    public void eliminarRegioAssignada(int index) {
        imprimirDadesRegio();
        String infoRegio = dadesRegions.get(index);
        String[] parts = infoRegio.split(" ");

        //Actualitzar index de les regions i desmarca les caselles de la regio  eliminada
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                if (caselles[i][j].getIndexRegio() == index) {
                    caselles[i][j].desmarcaComRegio();
                }
                else if (caselles[i][j].getIndexRegio() > index) {
                    caselles[i][j].decrementaIndexRegio();
                }
            }
        }
        //Eliminar label de la informacio de la regio (resultat,operacio) que te la  primer casella
        int x = Integer.parseInt(parts[3]) - 1;
        int y = Integer.parseInt(parts[4]) - 1;
        caselles[x][y].eliminarInfoRegio();

        int numCaselles = Integer.parseInt(parts[2]);
        numCasellesAssignades -= numCaselles;
        dadesRegions.remove(index);
        imprimirDadesRegio();
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

    void ordenarPosicionsCaselles() {
        posCaselles.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
    }

    public String validarEntrada(String operacio, String resultat) {

        if (resultat.isEmpty()) {
            return "<html><div style='text-align: center;'>El camp resultat es buit.<br>Si et plau, porporcioni un resultat valid.</div></html>";
        }
        else if (this.getNumCasellesSelecionades() == 1 && !operacio.equals("SUMA")) {
            return "<html><div style='text-align: center;'>Per regions d'una casella, l'unica operacio possible es SUMA." +
                    "<br>Si et plau, selecioni mes caselles o canvia la operacio a SUMA</div></html>";
        }
        else if ((operacio.equals("EXP") || operacio.equals("MOD") || operacio.equals("RESTA") || operacio.equals("DIV")) && this.getNumCasellesSelecionades()!= 2) {
            return "<html><div style='text-align: center;'>Per las operaciones \"RESTA\",\"DIV\", \"EXP\" i \"MOD\" has de selecionar " +
                    "exactament 2 caselles.<br>Si et plau, selecioni nomes dos caselles o canvia la operacio a SUMA o MULT</div></html>";
        }

        //return validarValorMinMaxOperacio(operacio, Integer.parseInt(resultat));
        return null;
    }

     String validarValorMinMaxOperacio(String operacio, int resultat) {
        System.out.println("Opercio a validar: " + operacio + " Resultat: " + resultat);
        switch (operacio) {
            case "SUMA":
                return validarSuma(resultat);
            case "RESTA":
                break;
            case "MULT":

                break;
            case "DIV":

                break;
            case "EXP":

                break;
            case "MOD":

                break;
        }
        return null;
    }

private String validarSuma(int resultat) {
        int N = getNumCasellesSelecionades();
        int min = N *(N+1)/2; //modificar
        int max = 0;

        for (int i = mida; i > mida - N; i--) { //modifiacar
            max += i;
        }
        System.out.println("Min: " + min + " Max: " + max);
        if (resultat < min || resultat > max) {
            System.out.println("Suma invalida");
            return "<html><div style='text-align: center;'>El resultat de la suma de " + N +
                    " caselles ha de ser <br> un valor entre " + min + " i " + max + "</div></html>";
        }
        System.out.println("Suma validada");
        return null;
    }


    private String traduirOperacioAnum(String operacio) {
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
                return null;
        }
    }
    private String traduirOperacioAsimbol(String operacio) {
        switch (operacio) {
            case "SUMA":
                if(posCaselles.size() == 1) return "";
                return "+";
            case "RESTA":
                return "-";
            case "MULT":
                return "x";
            case "DIV":
                return "÷";
            case "EXP":
                return "^";
            case "MOD":
                return "%";
            default:
                return null;
        }
    }

    public void resetTauler() {
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                caselles[i][j].configInicial();
            }
        }
        numCasellesAssignades = 0;
        dadesRegions.clear();
        posCaselles.clear();
    }

    public void imprimirDadesRegio() {
        System.out.println("Dades de les regions:");
        for (String infoRegio : dadesRegions) {
            System.out.println(infoRegio);
        }
        System.out.println(" ");
    }


}
