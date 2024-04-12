package main.domini.classes.operacions;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.interficies.Operacio;
import java.util.ArrayList;

public class Multiplicacio implements Operacio {

    public int opera2(int a, int b) {
        return a*b;
    }

    public int operaN(int[] valors) {
        int multiplicacio = 1;
        for (int valor : valors) {
            multiplicacio = multiplicacio * valor;
        }
        return multiplicacio;
    }

    public int[][] calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors) {
        try {
            if (valors.length > midaRegio) {throw new ExcepcioMoltsValors(midaRegio, "MAX");}
            int multiplicacio = Resultat;
            int midaUtil = midaRegio;
            for (int valor : valors) {
                try {
                    if (multiplicacio % valor != 0) {
                        throw new ExcepcioNoDivisor(valor, multiplicacio);
                    }
                    multiplicacio = multiplicacio / valor;
                    midaUtil--;
                } catch (ExcepcioNoDivisor e) {
                    System.out.println(e.getMessage());
                    return ErrorConstants.ERROR_MATRIX;
                }
            }
            ArrayList<ArrayList<Integer>> solucions = new ArrayList<>();
            calculaPossiblesValorsBacktrack(midaTauler, midaUtil, multiplicacio, solucions, new ArrayList<>());
            int nombreSolucions = solucions.size();
            int[][] solucionsToInt = new int[nombreSolucions][midaUtil];
            for (int i = 0; i < nombreSolucions; i++) {
                solucionsToInt[i] = solucions.get(i).stream().mapToInt(j -> j).toArray();
            }
            return solucionsToInt;
        } catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_MATRIX;
        }
    }
    private void calculaPossiblesValorsBacktrack(int midaTauler, int midaUtil, int multiplicacio, ArrayList<ArrayList<Integer>> solucions, ArrayList<Integer> solucioParcial) {
        //Cas base
        if (solucioParcial.size() == midaUtil-1) {
            if (multiplicacio > 0 && multiplicacio <= midaTauler){
                ArrayList<Integer> solucioCopia = new ArrayList<>(solucioParcial);
                solucioCopia.add(multiplicacio);
                solucions.add(solucioCopia);
            }
        }
        else for (int i = 1; i <= midaTauler; i++) {
            if (multiplicacio%i == 0) {
                solucioParcial.add(i);
                calculaPossiblesValorsBacktrack(midaTauler, midaUtil, multiplicacio/i, solucions, solucioParcial);
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}
