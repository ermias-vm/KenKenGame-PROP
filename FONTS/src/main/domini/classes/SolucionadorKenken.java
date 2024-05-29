package main.domini.classes;

import main.domini.excepcions.*;
import main.persistencia.CtrlUsuariData;


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
     * Mètode recursiu per a la resolució del tauler de joc.
     * @param T Tauler de joc a resoldre
     * @param i Índex de la fila
     * @param j Índex de la columna
     * @throws Exception Si es produeix un error durant la resolució
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
                c.setValor(r.getResultat());
                c.setInmodificable();
            }
        }
    }

    /**
     * Mètode que soluciona un tauler de Kenken.
     * @param T Tauler de joc a solucionar.
     * @throws Exception Si es produeix un error durant la solució.
     */
    public void solucionarKenken(Tauler T) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable, ExcepcionPosicioIncorrecta {
        optimitzacioNoOperacio(T);
        backtracking(T, 1, 1);
    }
}
