package main.domini.classes.operacions;
import main.domini.classes.ErrorConstantsOperacions;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Multiplicacio implements Operacio {

    public int opera2(int a, int b) {
        return a*b;
    }

    public int operaN(int[] valors) {
        int multiplicacio = 1;
        for (int valor : valors) {
            multiplicacio *= valor;
        }
        return multiplicacio;
    }

    public int[] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        if (valors.length > midaRegio) {return ErrorConstantsOperacions.ERROR_ARRAY_MIDA;}
        int nombreRepeticions = (midaRegio + 1)/2;
        int[] vegadesRepetibles = new int[midaTauler];
        Arrays.fill(vegadesRepetibles, nombreRepeticions);
        int multiplicacio = Resultat;
        int midaUtil = midaRegio;
        for (int valor : valors) {
            if (valor == 0) {
                return ErrorConstantsOperacions.ERROR_ARRAY_DIV_0;
            }
            if (multiplicacio % valor != 0) {
                return ErrorConstantsOperacions.ERROR_ARRAY_NO_DIVISOR;
            }
            multiplicacio = multiplicacio / valor;
            midaUtil--;
        }
        ArrayList<ArrayList<Integer>> solucions = new ArrayList<>();
        calculaPossiblesValorsBacktrack(vegadesRepetibles,1,midaTauler, midaUtil, multiplicacio, solucions, new ArrayList<>());
        int nombreSolucions = solucions.size();
        ArrayList<Integer> possiblesValorsUnics = new ArrayList<>();
        boolean[] jaPosat = new boolean[midaTauler];
        for (int i = 0; i < nombreSolucions; i++) {
            for (int j = 0; j < solucions.get(i).size(); j++) {
                if (!jaPosat[solucions.get(i).get(j)-1]) {
                    possiblesValorsUnics.add(solucions.get(i).get(j));
                    jaPosat[solucions.get(i).get(j)-1] = true;
                }
            }
        }
        Collections.sort(possiblesValorsUnics);
        int[] solucionsToInt = possiblesValorsUnics.stream().mapToInt(i -> i).toArray();
        return solucionsToInt;
    }
    private void calculaPossiblesValorsBacktrack(int[] vegadesRepetibles,int min, int midaTauler, int midaUtil, int multiplicacio, ArrayList<ArrayList<Integer>> solucions, ArrayList<Integer> solucioParcial) {
        //Cas base
        if (solucioParcial.size() == midaUtil && multiplicacio == 1) {
            ArrayList<Integer> solucioCopia = new ArrayList<>(solucioParcial);
            solucions.add(solucioCopia);
        }
        else for (int i = min; i <= midaTauler; i++) {
            if (multiplicacio%i == 0 && vegadesRepetibles[i-1] > 0) {
                solucioParcial.add(i);
                --vegadesRepetibles[i-1];
                calculaPossiblesValorsBacktrack(vegadesRepetibles, i, midaTauler, midaUtil, multiplicacio/i, solucions, solucioParcial);
                ++vegadesRepetibles[i-1];
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}

