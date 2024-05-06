package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioValorInvalid;
import main.domini.interficies.Operacio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe {@code Suma} és una implementació de la interfície {@code Operacio}
 * que realitza els càlculs mitjançant l'operacio de suma d'enters.
 * @autor Nil Beascoechea Vàzquez
 */
public class Suma implements Operacio{
    /**
     * Realitza la suma de dos enters.
     *
     * @param a Primer enter
     * @param b Segon enter
     * @return El valor de la suma dels dos.
     */
    @Override
    public int opera2(int a, int b){
        return a+b;
    }
    /**
     * Realitza la suma de n enters donats en un vector.
     *
     * @param valors Vector d'enters a sumar
     * @return La suma de tots els enters del vector
     * @throws ExcepcioMoltsValors Si el vector és buit.
     */
    @Override
    public int operaN(int[] valors) throws ExcepcioMoltsValors {
        if (valors.length == 0) {
            throw new ExcepcioMoltsValors(1, "MIN");
        }
        int suma = 0;
        for (int i = 0; i < valors.length; i++){
            suma += valors[i];
        }
        return suma;
    }
    /**
     * Calcula totes les possibles solucions de dos enters d'1 a la mida del tauler que sumats donin el resultat introduït.
     * Es poden passar valors inicials per a que el mètode els tingui en compte.
     * Retorna els valors d'1 a midaTauler que apareixen en alguna de les solucions calculades.
     *
     * @param resultat El resultat de la suma que volem trobar.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaRegio La mida de la regió on es calcula.
     * @param valors Un vector d'enters que conté els valors inicials de la regió a partir dels quals es calcula si hi ha possible solució i quina.
     * @throws ExcepcioMoltsValors Si el vector té més valors que caselles la regió.
     * @return Tots els possibles valors únics que poden ser solució.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int resultat, int midaTauler, int midaRegio, int[] valors) throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        if (valors.length > midaRegio ){throw new ExcepcioMoltsValors(midaRegio, "MAX");}
        int nombreRepeticions = (midaRegio + 1)/2;
        int[] vegadesRepetibles = new int[midaTauler];
        Arrays.fill(vegadesRepetibles, nombreRepeticions);
        int sumatori = resultat;
        int midaUtil = midaRegio;
        for (int i = 0; i < valors.length; i++) {
            if (valors[i] > midaTauler || valors[i] < 1) {
                throw new ExcepcioValorInvalid();
            }
            sumatori -= valors[i];
            --vegadesRepetibles[valors[i]-1];
            midaUtil--;
        }
        Set<Integer> solucions = new HashSet<>();
        calculaPossiblesValorsBacktrack(vegadesRepetibles,1,midaTauler, midaUtil, sumatori, solucions, new ArrayList<>());
        return solucions;
    }

    /**
     * Retorna el nombre d'operació que representa la suma.
     * @return 1
     */
    @Override
    public int getNumOperacio() {
        return 1;
    }

    /**
     * Retorna si el valor pot ser resultat de la suma.
     * @param resultat El valor a comprovar si pot ser resultat de la suma.
     * @return True si el valor és major o igual a 1 (ja que la suma pot ser també de només 1 element), false altrament.
     */
    @Override
    public boolean valorPotSerResultat(int resultat) {
        return resultat >= 1;
    }

    /**
     * Retorna si l'operació de suma és commutativa.
     * @return True.
     */
    @Override
    public boolean esCommutativa() {
        return true;
    }

    @Override
    public String getOperacioText() {
        return "+";
    }

    /**
     * Funció d'ajuda per a calcular les possibles solucions de la suma mitjançant backtracking.
     *
     * @param vegadesRepetibles Un vector de mida midaTauler que conté el nombre de vegades que es pot
     *                         repetir cada valor d'acord amb el màxim possible si la regió tingués forma d'escala.
     * @param min Valor mínim entre 1 i midaTauler que es pot introduir a la solució parcial.
     *           Per evitar solucions repetides amb diferent ordre de números.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaUtil La mida de la solució a donar un cop s'ha eliminat els valors introduïts a la funció calculaPossiblesValors().
     * @param sumatori El resultat de la suma que volem trobar després d'haver-li restat l'últim element afegit a la solució parcial.
     * @param solucions Un set que conté tots els valors únics trobats fins al moment que pertanyen a alguna solució.
     * @param solucioParcial Un vector que conté una possible solució.
     */
    private void calculaPossiblesValorsBacktrack(int[] vegadesRepetibles,int min, int midaTauler, int midaUtil, int sumatori, Set<Integer> solucions, ArrayList<Integer> solucioParcial) {
        //Cas base
        if (solucioParcial.size() == midaUtil && sumatori == 0) {
            solucions.addAll(solucioParcial);
        }
        else for (int i = min; i <= midaTauler; i++) {
            //Crec que seria correcte igualment amb i<sumatori
            if (vegadesRepetibles[i-1] > 0 && i <= sumatori) {
                solucioParcial.add(i);
                --vegadesRepetibles[i-1];
                calculaPossiblesValorsBacktrack(vegadesRepetibles,i, midaTauler, midaUtil, sumatori - i, solucions, solucioParcial);
                ++vegadesRepetibles[i-1];
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}