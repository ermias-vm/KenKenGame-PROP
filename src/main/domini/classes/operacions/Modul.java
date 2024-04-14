package main.domini.classes.operacions;
import main.ErrorConstants;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;
import java.util.ArrayList;

public class Modul implements Operacio {

    public int opera2(int a, int b) {
        return a % b;
    }

    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            else return valors[0] % valors[1];
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_INT;
        }
    }
//Millorar retorn de la funcio per evitar bucles per copiar  a int [][]
    public int[][] calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) {
        try {
            if (midaRegio != 2) {
                throw new ExcepcioMoltsValors(2, "EQ");
            }
            else {
                if (valors.length == 1) { // Quan una de les dos caselles esta bloquejada
                    int valorBloquejat = valors[0];
                    ArrayList<Integer> possiblesValors = new ArrayList<>();

                    for (int i = 1; i <= midaTauler; i++) {
                        if (i != valorBloquejat && (i % valorBloquejat == Resultat || valorBloquejat % i == Resultat)) {
                            possiblesValors.add(i);
                        }
                    }

                    int nombreSolucions = possiblesValors.size();
                    int[][] solucions = new int[nombreSolucions][midaRegio];

                    for (int i = 0; i < nombreSolucions; i++) {
                        solucions[i][0] = possiblesValors.get(i);
                    }

                    return solucions;
                }
                else { // Quan no  hi ha cap casella bloquejada
                    ArrayList<int[]> solucions = new ArrayList<>();

                    for (int a = 1; a <= midaTauler; a++) {
                        for (int b = a; b <= midaTauler; b++) {
                            if (a != b && (a % b == Resultat || b % a == Resultat)) {
                                int[] solucioParcial = {a, b};
                                solucions.add(solucioParcial);
                            }
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
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstants.ERROR_MATRIX;
        }
    }

}