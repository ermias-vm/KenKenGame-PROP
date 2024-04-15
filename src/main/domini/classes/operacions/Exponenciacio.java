package main.domini.classes.operacions;

import main.domini.classes.ErrorConstantsOperacions;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;

public class Exponenciacio implements Operacio {
    public int opera2(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            else return (int) Math.pow(valors[0], valors[1]);
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
            return ErrorConstantsOperacions.ERROR_INT;
        }
    }

    public int[][]calculaPossiblesValors(int Resultat,int midaTauler, int midaRegio, int[] valors){
        // Implementacio pendent
        return null;
    }
}