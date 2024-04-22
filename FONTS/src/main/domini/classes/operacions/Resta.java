package main.domini.classes.operacions;
import main.domini.excepcions.ExcepcioDivisio_0;
import main.domini.excepcions.ExcepcioMoltsValors;
import main.domini.excepcions.ExcepcioValorInvalid;
import main.domini.interficies.Operacio;
import java.util.HashSet;
import java.util.Set;
/**
 * La classe {@code Resta} és una implementació de la interfície {@code Operacio}
 * que realitza els càlculs mitjançant l'operacio de resta d'enters.
 * @autor Nil Beascoechea Vàzquez
 */
public class Resta implements Operacio {
    /**
     * Realitza la resta de dos enters independentment de l'ordre.
     *
     * @param a Primer enter
     * @param b Segon enter
     * @return El valor absolut de la resta d'ambdós enters
     */
    @Override
    public int opera2(int a, int b) {
        return Math.abs(a-b);
    }

    /**
     * Realitza la resta de dos enters independentment de l'ordre donats com a vector.
     *
     * @param valors Un vector d'enters a restar
     * @throws ExcepcioMoltsValors Si el vector té més de dos valors
     * @return El valor absolut de la resta de tots els enters del vector
     */
    @Override
    public int operaN(int[] valors) throws ExcepcioMoltsValors {
            if (valors.length != 2) { throw new ExcepcioMoltsValors(2, "EQ");}
            else return Math.abs(valors[0]-valors[1]);
    }
    /**
     * Calcula totes les possibles solucions de dos enters d'1 a la mida del tauler que restats donin el resultat introduït.
     *  Es poden passar valors inicials per a que el mètode els tingui en compte.
     *  Retorna els valors d'1 a midaTauler que apareixen en alguna de les solucions calculades.
     *
     * @param resultat El resultat de la resta que volem trobar.
     * @param midaTauler La mida del tauler on es calcula.
     * @param midaRegio La mida de la regió on es calcula.
     * @param valors Un vector d'enters que conté els valors inicials de la regió a partir dels quals es calcula si hi ha possible solució i quina.
     *               No pot ser igual a la mida de la regió. És a dir la regió no pot estar plena.
     * @throws ExcepcioMoltsValors Si el vector té igual o més de dos valors o la regió té més de dues caselles. És a dir només s'hauria d'entrar si la regió no està plena.
     * @throws ExcepcioDivisio_0 Si algun dels valors és 0.
     * @return Tots els possibles valors únics que poden ser solució.
     */
    @Override
    public Set<Integer> calculaPossiblesValors(int resultat, int midaTauler, int midaRegio, int[] valors) throws ExcepcioMoltsValors, ExcepcioValorInvalid {
        if (midaRegio != 2) {
            throw new ExcepcioMoltsValors(2, "EQ");
        }
        if (valors.length >= 2) {
            throw new ExcepcioMoltsValors(1, "MAX");
        }
        Set<Integer> solucions = new HashSet<>();
        if (valors.length == 1) {
            if (valors[0] < 1 || valors[0] > midaTauler || resultat == 0) {
                throw new ExcepcioValorInvalid();
            }
            int resta = valors[0] - resultat;
            if (resta > 0 && resta <= midaTauler) {
                solucions.add(resta);
            }
            int suma = valors[0] + resultat;
            if (suma > 0 && suma <= midaTauler) {
                solucions.add(suma);
            }
        }
        else {
            for (int i = 1; i <= midaTauler; i++) {
                int resta = resultat + i;
                if (resta > 0 && resta <= midaTauler) {
                    solucions.add(i);
                    solucions.add(resta);
                }
            }
        }
        return solucions;
    }

    @Override
    public int getNumOperacio() {
        return 2;
    }
}
