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

    public int[][] calculaPossiblesValors(int Resultat, int mida, int[] valors) {
        try {
            if (mida != 2) {
                throw new ExcepcioMoltsValors(2, "EQ");
            }
            else {
                ArrayList<int[]> solucions = new ArrayList<>();
                for (int i = 1; i <= mida; i++) {
                    int[] solucioParcial = new int[2];
                    int resta = Resultat + i;
                    if (resta > 0 && resta <= mida) {
                        solucioParcial[0] = i;
                        solucioParcial[1] = resta;
                        solucions.add(solucioParcial);
                    }
                }
                int nombreSolucions = solucions.size();
                int[][] solucionsToInt = new int[nombreSolucions][mida];
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
