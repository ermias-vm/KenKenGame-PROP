package main.domini.classes.operacions;
import main.domini.classes.ErrorConstantsOperacions;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
import java.util.Collections;

public class Divisio implements Operacio {

    public int opera2(int a, int b) {
        if (a == 0 || b == 0) {return ErrorConstantsOperacions.ERROR_INT_DIV_0;}
        if (divisible(a,b)) {return a/b;}
        else if (divisible(b,a)) {return b/a;}
        else{return ErrorConstantsOperacions.ERROR_INT_NO_DIV;}
    }
    public int operaN(int[] valors) {
        if (valors.length != 2) {return ErrorConstantsOperacions.ERROR_INT_MIDA;}
        return opera2(valors[0],valors[1]);
    }
    public int[] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        if (midaRegio != 2) {return ErrorConstantsOperacions.ERROR_ARRAY_MIDA;}
        if (valors.length > 2) {return ErrorConstantsOperacions.ERROR_ARRAY_MIDA;}
        else {
            if (valors.length == 1) {
                if (valors[0] == 0) {return ErrorConstantsOperacions.ERROR_ARRAY_DIV_0;}
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
            if (valors.length == 2) {
                if (valors[0] == 0 || valors[1] == 0)
                    return ErrorConstantsOperacions.ERROR_ARRAY_DIV_0;
                else return new int[0];
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
    private boolean divisible(int a, int b) {
        if (a % b == 0) return true;
        return false;
    }
}
