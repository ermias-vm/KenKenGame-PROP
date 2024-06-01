package main.domini.classes;

import main.domini.excepcions.*;

/**
 * Classe que representa un solucionador de Kenken.
 * Utilitza un algoritme de backtracking per a la resolució del tauler de joc.
 *
 *   @author Ermias Valls Mayor
 */
public class SolucionadorKenken {
    /**
     * Instància singleton de SolucionadorKenken.
     */
    private static SolucionadorKenken Solucionador;

    /**
     * Constructor privat de la classe SolucionadorKenken.
     * Evita que es pugui instanciar la classe directament.
     */
    private SolucionadorKenken() {
    }

    /**
     * Retorna la instància singleton de SolucionadorKenken.
     * Si encara no s'ha creat, la crea.
     *
     * @return Instància de SolucionadorKenken.
     */
    public static SolucionadorKenken getInstance() {
        if (Solucionador == null) Solucionador = new SolucionadorKenken();
        return Solucionador;
    }

    /**
     * Mètode recursiu per a la resolució del tauler de joc utilitzant l'algoritme de backtracking.
     *
     * @param T Tauler de joc a resoldre. Aquest és un objecte de la classe Tauler que representa el tauler de KenKen que volem resoldre.
     * @param i Índex de la fila on comença la resolució. Aquest paràmetre indica la fila del tauler on comença l'algoritme de backtracking.
     * @param j Índex de la columna on comença la resolució. Aquest paràmetre indica la columna del tauler on comença l'algoritme de backtracking.
     *
     * @throws ExcepcioCasellaNoExisteix Si la casella que es vol accedir no existeix en el tauler.
     * @throws ExcepcioNoDivisor Si el valor de la casella no és un divisor del resultat de la regió.
     * @throws ExcepcioValorInvalid Si el valor assignat a la casella és invàlid (per exemple, si és negatiu o més gran que el grau del tauler).
     * @throws ExcepcioMoltsValors Si s'assignen massa valors a una regió.
     * @throws ExcepcioDivisio_0 Si es produeix una divisió per zero.
     * @throws ExcepcioCasellaNoModificable Si es tracta de modificar una casella que no és modificable.
     * @throws ExcepcionPosicioIncorrecta Si la posició de la casella és incorrecta.
     * Aquest mètode utilitza l'algoritme de backtracking per resoldre el tauler de KenKen.
     * Comença a la casella indicada pels índexs de fila i columna (i, j) i intenta assignar valors a les caselles de manera recursiva.
     * Si troba una solució, marca el tauler com a resolt. Si no pot assignar un valor vàlid a una casella,
     * fa un pas enrere (backtrack) i prova amb el següent valor possible.
     * Si la casella no és modificable, simplement passa a la següent casella.
     * L'algoritme continua fins que ha resolt el tauler o ha provat tots els valors possibles sense trobar una solució.
     */
    private void backtracking(Tauler T, int i, int j) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        //Cas base , te solucio
        if (i == T.getGrau()+1) {
            T.setTrobat(true);
        }
        // Crida Recursiva

        else if (!T.esModificable(i, j)) {
            // Si la casella no es modificable, pasa a la seguent
            if (j == T.getGrau()) {
                backtracking(T, i + 1, 1);
            } else {
                backtracking(T, i, j + 1);
            }

        } else {
            for (int valor = 1; valor <= T.getGrau() && !T.teSolucio(); ++valor) {
                if (T.esFilaValida(i, valor) && T.esColumValida(j, valor)) {
                    T.setValor(i, j, valor);
                    Regio r = T.getRegio(i, j);
                    if ((r.esCompleta() && r.esValida(null)) || !r.esCompleta()) {
                        if (j ==  T.getGrau()) {
                            backtracking(T, i + 1, 1);
                        } else {
                            backtracking(T, i, j + 1);
                        }
                    }
                }
            }
            if (!T.teSolucio()) {
                T.borrarValor(i, j);
            }
        }
    }

    /**
     * Optimització que estableix el valor de les caselles que pertanyen a una regió amb una única operació.
     * @param T Tauler de joc a optimitzar.
     * @throws ExcepcioCasellaNoModificable Si la casella no es pot modificar.
     */
    private void optimitzacioNoOperacio(Tauler T) throws ExcepcioCasellaNoModificable {
        for (Regio r : T.getRegions()) {
            if (r.getMida() == 1) {
                Casella c = r.getCaselles().get(0);
                if (c.getValor() == 0){
                    c.setValor(r.getResultat());
                    c.setImmodificable();
                }
            }
        }
    }

    /**
     * Mètode que soluciona un tauler de Kenken Util.
     * @param T Tauler de joc a solucionar.
     */
    public void solucionarKenken(Tauler T) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        optimitzacioNoOperacio(T);
        backtracking(T, 1, 1);
        T.restaurarModificabilitat();
    }
}
