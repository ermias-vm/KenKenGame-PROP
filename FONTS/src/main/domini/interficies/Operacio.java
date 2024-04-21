package main.domini.interficies;
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
    public int opera2(int a, int b) throws Exception;
    /**
     * Realitza una operació de n enters donats en un vector.
     *
     * @param valors vector d'enters
     * @throws Exception en funció de l'operació, veure Javadoc de les diferents implementacions.
     * @return el resultat de l'operació
     */
    public int operaN(int[] valors) throws Exception;
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
    public Set<Integer> calculaPossiblesValors(int Resultat, int midaTauler, int midaRegio, int[] valors) throws Exception;
    public int getNumOperacio();
}
