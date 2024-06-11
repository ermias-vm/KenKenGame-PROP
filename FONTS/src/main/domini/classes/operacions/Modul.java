package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioMoltsValors;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe Modul implementa la interfície Operacio i defineix l'operació de mòdul.
 *
 * @author Ermias Valls Mayor
 */
public class Modul implements Operacio {

    /**
     * Realitza l'operació de mòdul en dos enters.
     *
     * @param a El primer enter.
     * @param b El segon enter.
     * @return El resultat de a mòdul b.
     */
    @Override
    public int opera2(int a, int b) {
        return a % b;
    }

    /**
     * Realitza l'operació de mòdul en un array d'enters.
     *
     * @param valors Un array d'enters.
     * @return El resultat de l'operació de mòdul en els enters de l'array.
     */
    @Override
    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            return valors[0] % valors[1];
        }
        catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Calcula els possibles valors que podrien resultar en el resultat donat quan s'aplica l'operació de mòdul a ells.
     *
     * @param Resultat El resultat de l'operació de mòdul.
     * @param midaTauler La mida del tauler.
     * @param midaRegio La mida de la regió.
     * @param valors Un array de valors inicials.
     * @return Un conjunt de possibles valors.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) {
        Set<Integer> solucions = new HashSet<>();
        if (valors.length == 1) {
            for (int i = 1; i <= midaTauler; i++) {
                if (i % valors[0] == Resultat) {
                    solucions.add(i);
                }
            }
        } else {
            for (int i = 1; i <= midaTauler; i++) {
                for (int j = i; j <= midaTauler; j++) {
                    if (i % j == Resultat) {
                        solucions.add(i);
                        solucions.add(j);
                    }
                }
            }
        }
        return solucions;
    }

    /**
     * Retorna el número de l'operació.
     *
     * @return El número de l'operació.
     */
    @Override
    public int getNumOperacio() {
        return 5;
    }
    /**
     * Per a un valor qualsevol diu si el valor seria possible com a resultat d'una operació de mòdul.
     * @param resultat El valor a comprovar.
     * @return True si el valor és positiu, false altrament.
     */
    @Override
    public boolean valorPotSerResultat(int resultat) {
        return resultat >= 0;
    }

    /**
     * Diu si l'operació és commutativa.
     * @return False
     */
    @Override
    public boolean esCommutativa() {
        return false;
    }

    @Override
    public String getOperacioText() {
        return "%";
    }
}