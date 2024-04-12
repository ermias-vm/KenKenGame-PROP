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

    public int[][] calculaPossiblesValors(int Resultat, int mida, int[] valors) {
            // Implementacio pendent
        return null;
    }
}