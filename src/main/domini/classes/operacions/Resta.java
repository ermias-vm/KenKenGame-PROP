package main.domini.classes.operacions;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
import java.util.Collections;

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

    public int[] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        try {
            if (midaRegio != 2) {
                throw new ExcepcioMoltsValors(2, "EQ");
            }
            else {
                if (valors.length == 1) {
                    ArrayList<Integer> finsDosResultats = new ArrayList<>();
                    int resta = valors[0] - Resultat;
                    if (resta > 0 && resta <= midaTauler) {
                        finsDosResultats.add(resta);
                    }
                    int suma = valors[0] + Resultat;
                    if (suma > 0 && suma <= midaTauler) {
                        finsDosResultats.add(suma);
                    }
                    int[] solucionsToInt = finsDosResultats.stream().mapToInt(i -> i).toArray();
                    return solucionsToInt;
                }
                ArrayList<Integer> solucions = new ArrayList<>();
                boolean[] jaPosat = new boolean[midaTauler];
                for (int i = 1; i <= midaTauler; i++) {
                    int resta = Resultat + i;
                    if (resta > 0 && resta <= midaTauler) {
                        if (!jaPosat[i-1]) {
                            solucions.add(i);
                            jaPosat[i-1] = true;
                        }
                        if (!jaPosat[resta-1]) {
                            solucions.add(resta);
                            jaPosat[resta-1] = true;
                        }
                    }
                }
                Collections.sort(solucions);
                int[] solucionsToInt = solucions.stream().mapToInt(i -> i).toArray();
                return solucionsToInt;
            }
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_ARRAY;
        }
    }
}
