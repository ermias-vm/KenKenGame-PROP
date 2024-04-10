import java.util.ArrayList;
import java.util.List;

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
    public int[][] calculaPossiblesValors(int Resultat, int mida, int[] valors) {
        boolean buit = true;
        int sumatori = Resultat;
        int midaUtil = mida;
        for (int i = 0; i < valors.length; i++) {
            sumatori -= valors[i];
            midaUtil--;
        }
        ArrayList<ArrayList<Integer>> solucions = new ArrayList<>();
        calculaPossiblesValorsBacktrack(midaUtil, sumatori, solucions, new ArrayList<>());

    }
    private void calculaPossiblesValorsBacktrack(int midaUtil, int sumatori, ArrayList<ArrayList<Integer>> solucions, ArrayList<Integer> solucioParcial) {

    }
}