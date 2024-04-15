package main.domini.classes.operacions;
import main.domini.classes.ErrorConstantsOperacions;
import main.domini.interficies.Operacio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    public int[] calculaPossiblesValors( int Resultat, int midaTauler, int midaRegio, int[] valors) {
        if (valors.length > midaRegio ){return ErrorConstantsOperacions.ERROR_ARRAY_MIDA;}
        int nombreRepeticions = (midaRegio + 1)/2;
        int[] vegadesRepetibles = new int[midaTauler];
        Arrays.fill(vegadesRepetibles, nombreRepeticions);
        int sumatori = Resultat;
        int midaUtil = midaRegio;
        for (int i = 0; i < valors.length; i++) {
            sumatori -= valors[i];
            --vegadesRepetibles[valors[i]-1];
            midaUtil--;
        }
        ArrayList<ArrayList<Integer>> solucions = new ArrayList<>();
        calculaPossiblesValorsBacktrack(vegadesRepetibles,1,midaTauler, midaUtil, sumatori, solucions, new ArrayList<>());
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
    private void calculaPossiblesValorsBacktrack(int[] vegadesRepetibles,int min, int midaTauler, int midaUtil, int sumatori, ArrayList<ArrayList<Integer>> solucions, ArrayList<Integer> solucioParcial) {
        //Cas base
        if (solucioParcial.size() == midaUtil && sumatori == 0) {
            ArrayList<Integer> solucioCopia = new ArrayList<>(solucioParcial);
            solucions.add(solucioCopia);
        }
        else for (int i = min; i <= midaTauler; i++) {
            //Crec que seria correcte igualment amb i<sumatori
            if (vegadesRepetibles[i-1] > 0 ) {
                solucioParcial.add(i);
                --vegadesRepetibles[i-1];
                calculaPossiblesValorsBacktrack(vegadesRepetibles,i, midaTauler, midaUtil, sumatori - i, solucions, solucioParcial);
                ++vegadesRepetibles[i-1];
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}