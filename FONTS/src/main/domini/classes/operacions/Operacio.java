package main.domini.classes.operacions;

import main.domini.excepcions.ExcepcioDivisio_0;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioNoDivisor;
import main.domini.excepcions.ExcepcioValorInvalid;

import java.util.Set;

/**
 * La interfície {@code Operacio} defineix els diferents càlculs que es poden fer amb una operació determinada.
 * @author Nil Beascoechea Vàzquez
 */
public interface Operacio {
    /**
     * Realitza una operació de dos enters.
     *
     * @param a primer enter
     * @param b segon enter
     * @return el resultat de l'operació
     */
    public int opera2(int a, int b) throws ExcepcioDivisio_0, ExcepcioNoDivisor, ExcepcioValorInvalid;
    /**
     * Realitza una operació de n enters donats en un vector.
     *
     * @param valors vector d'enters
     * @throws ExcepcioMoltsValors Si l'array conté més de dos enters i l'operació no és commutativa.
     * @throws ExcepcioDivisio_0 Si algun dels valors és 0
     * @throws ExcepcioNoDivisor Si cap dels dos valors és divisor de l'altre
     * @return el resultat de l'operació
     */
    public int operaN(int[] valors) throws ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioNoDivisor;
    /**
     * Calcula totes les possibles solucions de dos enters d'1 a la mida del tauler que donin el resultat introduït.
     *  Es poden passar valors inicials per a que el mètode els tingui en compte.
     *  Retorna els valors d'1 a midaTauler que apareixen en alguna de les solucions calculades.
     *
     * @param Resultat el resultat de l'operació que volem trobar.
     * @param midaTauler la mida del tauler on es calcula.
     * @param midaRegio la mida de la regió on es calcula.
     * @param valors un vector d'enters que conté els valors inicials de la regió a partir dels quals es calcula si hi ha possible solució i quina.
     *               No pot ser igual a la mida de la regió. És a dir la regió no pot estar plena.
     * @throws Exception en funció de l'operació, veure Javadoc de les diferents implementacions.
     * @return tots els possibles valors únics que poden ser solució.
     */
    public Set<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) throws ExcepcioMoltsValors, ExcepcioValorInvalid, ExcepcioNoDivisor;
    /**
     * Obté el número de l'operació.
     * @return el número de l'operació
     */
    public int getNumOperacio();

    /**
     * Retorna si el valor pot ser resultat de l'operació.
     * @param resultat el valor a comprovar
     * @return true si el valor pot ser resultat de l'operació, false altrament.
     */
    public boolean valorPotSerResultat(int resultat);
    /**
     * Retorna si l'operació és commutativa.
     * @return true si l'operació és commutativa, false altrament.
     */
    public boolean esCommutativa();

    /**
     * Retorna el text de l'operació. Per exemple d'una suma "+"
     * @return el text de l'operació
     */
    String getOperacioText();
}
