package main.domini.interficies;

import java.util.ArrayList;

public interface Operacio {
    public int opera2(int a, int b);
    public int operaN(int[] valors);
    public ArrayList<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors);
}
