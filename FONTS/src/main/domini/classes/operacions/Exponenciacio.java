package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioMoltsValors;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe Exponenciacio implementa la interfície Operacio i defineix l'operació d'exponenciació.
 *
 * @author Ermias Valls Mayor
 */
public class Exponenciacio implements Operacio {

    /**
     * Realitza l'operació d'exponenciació en dos enters.
     *
     * @param a El primer enter.
     * @param b El segon enter.
     * @return El resultat de a elevat a b.
     */
    @Override
    public int opera2(int a, int b) {
        return (int) Math.pow(a, b);
    }

    /**
     * Realitza l'operació d'exponenciació en un array d'enters.
     *
     * @param valors Un array d'enters.
     * @return El resultat de l'operació d'exponenciació en els enters de l'array.
     */
    @Override
    public int operaN(int[] valors) {
        try {
            if (valors.length != 2) {
                throw new ExcepcioMoltsValors(2, "EQ");
            } else return (int) Math.pow(valors[0], valors[1]);
        } catch (ExcepcioMoltsValors e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Calcula els possibles valors que podrien resultar en el resultat donat quan s'aplica l'operació d'exponenciació a ells.
     *
     * @param Resultat El resultat de l'operació d'exponenciació.
     * @param midaTauler La mida del tauler.
     * @param midaRegio La mida de la regió.
     * @param valors Un array de valors inicials.
     * @return Un conjunt de possibles valors.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) {
        Set<Integer> solucions = new HashSet<>();
        if (Resultat == 1) {
            for (int i = 1; i <= midaTauler; i++) {
                solucions.add(i);
            }
        } else if (valors.length == 1) {
            double result = Math.log(Resultat) / Math.log(valors[0]);
            solucions.add((int) result);
        } else {
            int i = 2;
            while (i <= midaTauler) {
                int j = 0;
                int res = 0;
                while (j <= midaTauler && res <= Resultat) {
                    res = (int) Math.pow(i, j);
                    if (Resultat == res) {
                        solucions.add(i);
                        solucions.add(j);
                    }
                    j++;
                }
                i++;
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
        return 6;
    }

    /**
     * Retorna si el valor pot ser resultat de l'operació de l'exponenciació.
     * @param resultat El resultat de l'operació.
     * @return True si el resultat és major que 1, false altrament.
     */
    @Override
    public boolean valorPotSerResultat(int resultat) {
        return resultat >= 1;
    }

    /**
     * Retorna si l'operació és commutativa.
     *
     * @return False.
     */
    @Override
    public boolean esCommutativa() {
        return false;
    }

    @Override
    public String getOperacioText() {
        return "^";
    }
}