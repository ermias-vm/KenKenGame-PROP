package main.domini.classes;

import java.util.ArrayList;

public class SolucionadorKenken {

    /**
     * Mètode recursiu per a la resolució del tauler de joc.
     * @param T Tauler de joc a resoldre
     * @param i Índex de la fila
     * @param j Índex de la columna
     * @throws Exception Si es produeix un error durant la resolució
     */
    private void backtracking(Tauler T, int i, int j) throws Exception {
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

    private void optimitzacioNoOperacio(Tauler T) {
        for (Regio r : T.getRegions()) {
            if (r.getTamany() == 1) {
                Casella c = r.getCaselles().get(0);
                c.setValor(r.getResultat());
                c.setInmodificable();
            }
        }
    }


    public void solucionarKenken(Tauler T) throws Exception {
        optimitzacioNoOperacio(T);
        backtracking(T, 1, 1);
    }

    public int[] getValorsRegioMatriu(int [][] valorsTauler, int [][] posicionsRegio) {
        int [] valorsRegio = new int[posicionsRegio.length];
        for (int i = 0; i < posicionsRegio.length; ++i) {
            valorsRegio[i] = valorsTauler[posicionsRegio[i][0]][posicionsRegio[i][1]];
        }
        return valorsRegio;
    }


    public ArrayList<Regio> getRegionsIncorrectes(int [][] valorsTauler , Tauler T) throws Exception {
        ArrayList<Regio> regionsIncorrectes = new ArrayList<>();
        for (Regio r : T.getRegions()) {
            int [][] posicionsRegio = r.getPosicionsCaselles();
            int [] valorsRegio  =  getValorsRegioMatriu(valorsTauler, posicionsRegio);
            if (!r.esValida(valorsRegio)) {
                regionsIncorrectes.add(r);
            }
        }
        return regionsIncorrectes;
    }
}
