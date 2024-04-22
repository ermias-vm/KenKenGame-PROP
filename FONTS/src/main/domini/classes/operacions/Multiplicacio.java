package main.domini.classes.operacions;
import main.domini.excepcions.ExcepcioDivisio_0;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.excepcions.ExcepcioValorInvalid;
import main.domini.interficies.Operacio;
import java.util.*;
/**
 * La classe {@code Multiplicació} és una implementació de la interfície {@code Operacio}
 * que realitza els càlculs mitjançant l'operacio de multiplicació d'enters.
 * @autor Nil Beascoechea Vàzquez
 */
public class Multiplicacio implements Operacio {
    /**
     * Realitza la multiplicació de dos enters independentment de l'ordre.
     *
     * @param a Primer enter
     * @param b Segon enter
     * @return La multiplicació d'ambdós enters
     */
    @Override
    public int opera2(int a, int b) {
        return a*b;
    }
    /**
     * Realitza la multiplicació de n enters donats en un vector.
     *
     * @param valors Vector d'enters a multiplicar
     * @throws ExcepcioMoltsValors Si el vector és buit
     * @return La multiplicació de tots els enters del vector
     */
    @Override
    public int operaN(int[] valors) throws ExcepcioMoltsValors {
        if (valors.length == 0) {
            throw new ExcepcioMoltsValors(1, "MIN");
        }
        int multiplicacio = 1;
        for (int valor : valors) {
            multiplicacio *= valor;
        }
        return multiplicacio;
    }
    /**
     * Calcula totes les possibles solucions de dos enters d'1 a la mida del tauler que multiplicats donin el resultat introduït.
     *  Es poden passar valors inicials per a que el mètode els tingui en compte.
     *  Retorna els valors d'1 a midaTauler que apareixen en alguna de les solucions calculades.
     *
     * @param resultat El resultat de la multiplicació que volem trobar.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaRegio La mida de la regió on es calcula.
     * @param valors Un vector d'enters que conté els valors inicials de la regió a partir dels quals es calcula si hi ha possible solució i quina.
     *               No pot ser igual a la mida de la regió. És a dir la regió no pot estar plena.
     * @throws ExcepcioMoltsValors Si el vector té igual o més valors que caselles la regió.
     * @throws ExcepcioDivisio_0 Si algun dels valors és 0.
     * @throws ExcepcioNoDivisor Si algun dels valors no és divisor del resultat.
     * @throws ExcepcioValorInvalid Si algun dels valors és més gran que la mida del tauler o negatiu.
     * @return Tots els possibles valors únics que poden ser solució.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int resultat, int midaTauler, int midaRegio, int[] valors) throws ExcepcioNoDivisor, ExcepcioMoltsValors, ExcepcioValorInvalid {
        if (valors.length >= midaRegio) {throw new ExcepcioMoltsValors(midaRegio-1, "MAX");}
        int nombreRepeticions = (midaRegio + 1)/2;
        int[] vegadesRepetibles = new int[midaTauler];
        Arrays.fill(vegadesRepetibles, nombreRepeticions);
        int multiplicacio = resultat;
        int midaUtil = midaRegio;
        for (int valor : valors) {
            if (valor > midaTauler || valor < 1) {
                throw new ExcepcioValorInvalid();
            }
            if (multiplicacio % valor != 0) {
                throw new ExcepcioNoDivisor(valor, multiplicacio);
            }
            multiplicacio = multiplicacio / valor;
            --vegadesRepetibles[valor-1];
            midaUtil--;
        }
        Set<Integer> solucions = new HashSet<>();
        calculaPossiblesValorsBacktrack(vegadesRepetibles,1,midaTauler, midaUtil, multiplicacio, solucions, new ArrayList<>());
        return solucions;
    }

    @Override
    public int getNumOperacio() {
        return 3;
    }

    /**
     * Funcio ajuda per a calcular les possibles solucions de la multiplicació mitjançant backtracking.
     *
     * @param vegadesRepetibles Un vector de mida midaTauler que conté el nombre de vegades que es pot
     *                         repetir cada valor d'acord amb el màxim possible si la regió tingués forma d'escala.
     * @param min Valor minim entre 1 i midaTauler que es pot introduïr a la solució parcial.
     *           Per evitar soluciones repetides amb diferent ordre de números.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaUtil La mida de la solució a donar un cop s'ha eliminat els valors introduïts a la funció calculaPossiblesValors().
     * @param multiplicacio El resultat de la multiplicació que volem trobar després d'haver-la dividit per l'últim element afegit a la solució parcial.
     * @param solucions Un set que conté tots els valors únics trobats fins al moment que pertanyen a alguna solució.
     * @param solucioParcial Un vector que conté una possible solució.
     */
    private void calculaPossiblesValorsBacktrack(int[] vegadesRepetibles,int min, int midaTauler, int midaUtil, int multiplicacio, Set<Integer> solucions, ArrayList<Integer> solucioParcial) {
        //Cas base
        if (solucioParcial.size() == midaUtil && multiplicacio == 1) {
            solucions.addAll(solucioParcial);
        }
        else for (int i = min; i <= midaTauler; i++) {
            if (multiplicacio%i == 0 && vegadesRepetibles[i-1] > 0) {
                solucioParcial.add(i);
                --vegadesRepetibles[i-1];
                calculaPossiblesValorsBacktrack(vegadesRepetibles, i, midaTauler, midaUtil, multiplicacio/i, solucions, solucioParcial);
                ++vegadesRepetibles[i-1];
                solucioParcial.remove(solucioParcial.size() - 1);
            }
        }
    }
}

