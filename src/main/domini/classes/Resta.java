package main.domini.classes;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
public class Resta implements Operacio {

    public int opera2(int a, int b) {
        return Math.abs(a-b);
    }

    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            else return Math.abs(valors[0]-valors[1]);
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_INT;
        }
    }

    public int[][] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        try {
            if (midaRegio != 2) {
                throw new ExcepcioMoltsValors(2, "EQ");
            }
            else {
                if (valors.length == 1) {
                    int[][] unicResultat = new int[1][1];
                    unicResultat[0][0] = Math.abs(valors[0]-Resultat);
                    return unicResultat;
                }
                ArrayList<int[]> solucions = new ArrayList<>();
                for (int i = 1; i <= midaTauler; i++) {
                    int[] solucioParcial = new int[2];
                    int resta = Resultat + i;
                    if (resta > 0 && resta <= midaTauler) {
                        solucioParcial[0] = i;
                        solucioParcial[1] = resta;
                        solucions.add(solucioParcial);
                    }
                }
                int nombreSolucions = solucions.size();
                int[][] solucionsToInt = new int[nombreSolucions][midaRegio];
                for (int i = 0; i < nombreSolucions; i++) {
                    solucionsToInt[i] = solucions.get(i);
                }
                return solucionsToInt;
            }
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_MATRIX;
        }
    }
}
