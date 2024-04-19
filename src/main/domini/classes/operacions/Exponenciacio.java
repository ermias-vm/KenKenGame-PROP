package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.interficies.Operacio;
import java.util.Set;

public class Exponenciacio implements Operacio {

    @Override
    public int opera2(int a, int b) {
        return (int) Math.pow(a, b);
    }

    @Override
    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            else return (int) Math.pow(valors[0], valors[1]);
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public Set<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) {
        return null;
    }

    @Override
    public int getNumOperacio() {
        return 6;
    }

}