package main.domini.classes.operacions;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
import java.util.Collections;

public class Divisio implements Operacio {

    public int opera2(int a, int b) {
        try {
            if (divisible(a,b)) {return a/b;}
            else{throw new ExcepcioNoDivisor(b,a);}
        } catch (ExcepcioNoDivisor e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_INT;
        }
    }
    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            if (divisible(valors[0],valors[1])) {return valors[0]/valors[1];}
            else if (divisible(valors[1],valors[0])) {return valors[1]/valors[0];}
            else {
                if (valors[0] > valors[1]) {throw new ExcepcioNoDivisor(valors[1], valors[0]);}
                else throw new ExcepcioNoDivisor(valors[0], valors[1]);
            }
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_INT;
        }
        catch (ExcepcioNoDivisor e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_INT;
        }
    }

    public int[] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        try {
            if (midaRegio != 2) {throw new ExcepcioMoltsValors(2, "EQ");}
            else if (valors.length > midaRegio) {throw new ExcepcioMoltsValors(midaRegio, "MAX");}
             else {
                if (valors.length == 1) {
                    if (!divisible(valors[0], Resultat)) {
                        int res = valors[0] * Resultat;
                        if (res > 0 && res <= midaTauler) {
                            int[] unicResultat = new int[1];
                            unicResultat[0] = res;
                            return unicResultat;
                        }
                        return new int[0];
                    } else {
                        ArrayList<Integer> finsDosResultats = new ArrayList<>();
                        int resMult = valors[0] * Resultat;
                        if (resMult > 0 && resMult <= midaTauler) {
                            finsDosResultats.add(resMult);
                        }
                        int resDiv = valors[0] / Resultat;
                        if (resDiv > 0 && resDiv <= midaTauler) {
                            finsDosResultats.add(resDiv);
                        }
                        int[] solucionsToInt = finsDosResultats.stream().mapToInt(i -> i).toArray();
                        return solucionsToInt;
                    }
                }
                ArrayList<Integer> solucions = new ArrayList<>();
                boolean[] jaPosat = new boolean[midaTauler];
                for (int i = 1; i <= midaTauler; i++) {
                    int solucio = i * Resultat;
                    if (solucio > 0 && solucio <= midaTauler) {
                        if (!jaPosat[i - 1]) {
                            solucions.add(i);
                            jaPosat[i - 1] = true;
                        }
                        solucions.add(solucio);
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
    private boolean divisible(int a, int b) {
        if (a % b == 0) return true;
        return false;
    }
}
