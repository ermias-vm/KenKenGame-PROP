package main.domini.classes;

import main.domini.excepcions.*;

import java.util.ArrayList;

public class SolucionadorKenken {

    /**
     * Mètode recursiu per a la resolució del tauler de joc.
     * @param T Tauler de joc a resoldre
     * @param i Índex de la fila
     * @param j Índex de la columna
     * @throws Exception Si es produeix un error durant la resolució
     */
    private void backtracking(Tauler T, int i, int j) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
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

    private void optimitzacioNoOperacio(Tauler T) throws ExcepcioCasellaNoModificable {
        for (Regio r : T.getRegions()) {
            if (r.getMida() == 1) {
                Casella c = r.getCaselles().get(0);
                c.setValor(r.getResultat());
                c.setInmodificable();
            }
        }
    }


    public void solucionarKenken(Tauler T) throws ExcepcioCasellaNoExisteix, ExcepcioNoDivisor, ExcepcioValorInvalid, ExcepcioMoltsValors, ExcepcioDivisio_0, ExcepcioCasellaNoModificable {
        optimitzacioNoOperacio(T);
        backtracking(T, 1, 1);
    }
}
