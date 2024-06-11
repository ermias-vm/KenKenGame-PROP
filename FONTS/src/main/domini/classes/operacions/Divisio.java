package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioDivisio_0;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.excepcions.ExcepcioValorInvalid;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe {@code Divisio} és una implementació de la interfície {@code Operacio}
 * que realitza els càlculs mitjançant l'operacio de divisió d'enters.
 * @autor Nil Beascoechea Vàzquez
 */
public class Divisio implements Operacio {
    /**
     * Realitza la divisió de dos enters independentment de l'ordre.
     *
     * @param a Primer enter
     * @param b Segon enter
     * @throws ExcepcioDivisio_0 Si algun dels dos enters és 0
     * @throws ExcepcioNoDivisor Si cap dels dos enters és divisor de l'altre
     * @return La divisió de l'enter més gran amb el més petit
     */
    @Override
    public int opera2(int a, int b) throws ExcepcioDivisio_0, ExcepcioNoDivisor {
        //if (a == 0 || b == 0) {throw new ExcepcioDivisio_0();}
        if (divisible(a,b)) {return a/b;}
        else if (divisible(b,a)) {return b/a;}
        else return -1;
        //else{throw new ExcepcioNoDivisor(a,b);}
    }
    /**
     * Realitza la divisió de dos enters independentment de l'ordre donats com a vector.
     *
     * @param valors Un vector d'enters a dividir
     * @throws ExcepcioMoltsValors Si el vector té més de dos valors
     * @throws ExcepcioDivisio_0 Si algun dels valors és 0
     * @throws ExcepcioNoDivisor Si cap dels dos valors és divisor de l'altre
     * @return La divisió de l'enter més gran del vector amb el més petit
     */
    @Override
    public int operaN(int[] valors) throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor {
        if (valors.length != 2) {throw new ExcepcioMoltsValors(2, "EQ");}
        return opera2(valors[0],valors[1]);
    }
    /**
     * Calcula totes les possibles solucions de dos enters d'1 a la mida del tauler que dividits donin el resultat introduït.
     * Es poden passar valors inicials per a que el mètode els tingui en compte.
     * Retorna els valors d'1 a midaTauler que apareixen en alguna de les solucions calculades.
     *
     * @param resultat El resultat de la divisió que volem trobar.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaRegio La mida de la regió on es calcula.
     * @param valors Un vector d'enters que conté els valors inicials de la regió a partir dels quals es calcula si hi ha possible solució i quina.
     *               No pot ser igual a la mida de la regió. És a dir la regió no pot estar plena. Només poden estar entre 1 i midaTauler.
     * @throws ExcepcioMoltsValors Si el vector té igual o més de dos valors o la regió té més de dues caselles. És a dir només s'hauria d'entrar si la regió no està plena.
     * @return Tots els possibles valors únics que poden ser solució.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int resultat, int midaTauler, int midaRegio, int[] valors) throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        if (midaRegio != 2) {throw new ExcepcioMoltsValors(2, "EQ");}
        if (valors.length > 2) {throw new ExcepcioMoltsValors(2, "MAX");}
        else {
            Set<Integer> solucions = new HashSet<>();
            if (!valorPotSerResultat(resultat)) return solucions;
            if (valors.length == 2) return solucions;
            if (valors.length == 1) {
                if (valors[0] > midaTauler || valors[0] < 1) {
                    throw new ExcepcioValorInvalid();
                }
                if (!divisible(valors[0], resultat)) {
                    int res = valors[0] * resultat;
                    if (res > 0 && res <= midaTauler) {
                        solucions.add (res);
                    }
                } else {
                    int resMult = valors[0] * resultat;
                    if (resMult > 0 && resMult <= midaTauler) {
                        solucions.add(resMult);
                    }
                    int resDiv = valors[0] / resultat;
                    if (resDiv > 0 && resDiv <= midaTauler) {
                        solucions.add(resDiv);
                    }
                }
            }
            else {
                for (int i = 1; i <= midaTauler; i++) {
                    int solucio = i * resultat;
                    if (solucio > 0 && solucio <= midaTauler ) {
                        solucions.add(i);
                        solucions.add(solucio);
                    }
                }
            }
            return solucions;
        }
    }
    /**
     * Retorna el número de l'operació.
     *
     * @return 4 (Ja que divisió és l'operació 4)
     */
    @Override
    public int getNumOperacio() {
        return 4;
    }

    /**
     * Per a un resultat qualsevol, diu si es podria aconseguir en el context del joc
     * @param resultat Resultat que volem saber si és vàlid.
     * @return False si el resultat és < 2, ja que 1 no pot ser resultat perquè implicaria la repetició d'un valor,
     * 0 ni cap nombre negatiu ho pot ser perquè no són valors vàlids.
     */
    @Override
    public boolean valorPotSerResultat(int resultat) {
        return resultat >= 2;
    }

    /**
     * Diu si l'operació és commutativa.
     *
     * @return False.
     */
    @Override
    public boolean esCommutativa() {
        return false;
    }

    @Override
    public String getOperacioText() {
        return "/";
    }

    /**
     * Diu si un enter és divisible per l'altre.
     *
     * @param a Primer enter
     * @param b Segon enter
     * @return True si a és divisible per b, false altrament
     */
    private boolean divisible(int a, int b) {
        if (a % b == 0) return true;
        return false;
    }
}
