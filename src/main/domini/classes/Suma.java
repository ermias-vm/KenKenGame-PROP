package main.domini.classes;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;
import java.util.ArrayList;

public class Suma implements Operacio{
    public int opera2(int a, int b){
        return a+b;
    }
    public int operaN(int[] valors){
        int suma = 0;
        for (int i = 0; i < valors.length; i++){
            suma += valors[i];
        }
        return suma;
    }
    public int[][] calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) {
        try {
            if (valors.length > midaRegio ){throw new ExcepcioMoltsValors(midaRegio, "MAX");}
            int sumatori = Resultat;
            int midaUtil = midaRegio;
            for (int i = 0; i < valors.length; i++) {
                sumatori -= valors[i];
                midaUtil--;
            }
            ArrayList<ArrayList<Integer>> solucions = new ArrayList<>();
            calculaPossiblesValorsBacktrack(midaTauler, midaUtil, sumatori, solucions, new ArrayList<>());
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
    private void calculaPossiblesValorsBacktrack(int midaTauler, int midaUtil, int sumatori, ArrayList<ArrayList<Integer>> solucions, ArrayList<Integer> solucioParcial) {
    //Cas base
        if (solucioParcial.size() == midaUtil-1) {
           if (sumatori > 0 && sumatori <= midaTauler){
               ArrayList<Integer> solucioCopia = new ArrayList<>(solucioParcial);
               solucioCopia.add(sumatori);
               solucions.add(solucioCopia);
        }
    }
        else for (int i = 1; i <= midaTauler; i++) {
            //Crec que seria correcte igualment amb i<sumatori
            if (i <= sumatori) {
                solucioParcial.add(i);
                calculaPossiblesValorsBacktrack(midaTauler, midaUtil, sumatori - i, solucions, solucioParcial);
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}