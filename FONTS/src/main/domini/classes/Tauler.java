package main.domini.classes;

import main.domini.excepcions.*;

import java.util.ArrayList;
import java.util.HashSet;


public class Tauler {
    private String idTauler;
    private int grau;
    private boolean trobat;
    
    private Casella[][] caselles;
    private ArrayList<Regio> regions;

    public Tauler(String idTauler, int grau) {
        this.idTauler = idTauler;
        this.grau = grau;
        this.trobat = false;
        
        this.caselles = new Casella[grau][grau];
        this.regions = new ArrayList<>();
    }
    
    public Tauler(String idTauler, int grau, Casella [][] caselles, ArrayList<Regio> regions) {
        this.idTauler = idTauler;
        this.grau = grau;

        this.caselles = caselles;
        this.regions = regions;
    }


    /**
     * Obté l'identificador del tauler.
     *
     * @return Identificador del tauler.
     */
    public String getIdTauler() {
        return idTauler;
    }

    /**
     * Obté el grau del tauler.
     *
     * @return Grau del tauler.
     */
    public int getGrau() {
        return this.grau;
    }

    /**
     * Comprova si el tauler té una solució.
     * @return true si té solució, false en cas contrari
     */
    public boolean teSolucio() {
        return trobat;
    }

    public void setTrobat(boolean trobat) {
        this.trobat = trobat;
    }

    public void afegirCasella(int x, int y, Casella casella) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > grau || y < 1 || y > grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        caselles[x-1][y-1] = casella;
    }


    public void borrarCasella(int x, int y) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > grau || y < 1 || y > grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        caselles[x-1][y-1] = null;
    }


    public Casella getCasella(int x, int y) throws ExcepcioCasellaNoExisteix {
        if (x < 1 || x > this.grau || y < 1 || y > this.grau) {
            throw new ExcepcioCasellaNoExisteix(x, y);
        }
        return this.caselles[x-1][y-1];
    }

    /**
     * Obté la llista de caselles del tauler.
     *
     * @return Llista de caselles del tauler.
     */
    public Casella [][] getCaselles() {
        return this.caselles;
    }

    
    /**
     * Obté el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return Valor de la casella a la posició (x, y).
     */
    public int getValor(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).getValor();
    }

    /**
     * Estableix el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x   Coordenada x de la casella.
     * @param y   Coordenada y de la casella.
     * @param num Nou valor per la casella.
     */
    public void setValor(int x, int y, int num) throws ExcepcioCasellaNoExisteix {
        getCasella(x, y).setValor(num);
    }

    /**
     * Borra el valor de la casella a la posició (x, y) del tauler.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     */
    public void borrarValor(int x, int y) throws ExcepcioCasellaNoExisteix {
        getCasella(x, y).borrarValor();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler és modificable.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella és modificable, false en cas contrari.
     */
    public boolean esModificable(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).esModificable();
    }

    /**
     * Verifica si la casella a la posició (x, y) del tauler està buida.
     *
     * @param x Coordenada x de la casella.
     * @param y Coordenada y de la casella.
     * @return true si la casella està buida, false en cas contrari.
     */
    public boolean esBuida(int x, int y) throws ExcepcioCasellaNoExisteix {
        return getCasella(x, y).esBuida();
    }
    


    
    /**
     * Afegeix una regió de joc a la llista de regions.
     * @param regioJoc La regió de joc a afegir
     */
    public void afegirRegioJoc(Regio regioJoc) {
        this.regions.add(regioJoc);
    }

    /**
     * Esborra una regió de joc de la llista de regions.
     * @param regioJoc La regió de joc a esborrar
     */
    public void borrarRegioJoc(Regio regioJoc) {
        this.regions.remove(regioJoc);
    }

    /**
     * Retorna la llista de regions de joc.
     * @return Llista de regions de joc
     */
    public ArrayList<Regio> getRegions() {
        return this.regions;
    }

    /**
     * Retorna la regió de joc que conté la casella amb les coordenades x, y.
     * @param x Coordenada x de la casella
     * @param y Coordenada y de la casella
     * @return Regió de joc que conté la casella, null si no es troba
     */
    public Regio getRegio(int x, int y) {
        for (Regio r : regions) {
            for (int j = 0; j < r.getTamany(); ++j) {
                if (r.getCasella(j).getPosX() == x && r.getCasella(j).getPosY() == y) {
                    return r;
                }
            }
        }
        return null;
    }

    /**
     * Comprova si un número és vàlid per a una fila donada.
     * @param fila Fila a comprovar
     * @param num Número a comprovar
     * @return true si el número és vàlid, false en cas contrari
     */
    public boolean esFilaValida(int fila, int num) throws ExcepcioCasellaNoExisteix {
        for (int colum = 1; colum <= getGrau(); ++colum) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }

    /**
     * Comprova si un número és vàlid per a una columna donada.
     * @param colum Columna a comprovar
     * @param num Número a comprovar
     * @return true si el número és vàlid, false en cas contrari
     */
    public boolean esColumValida(int colum, int num) throws ExcepcioCasellaNoExisteix {
        for (int fila = 1; fila <= getGrau(); ++fila) {
            if (getCasella(fila, colum).getValor() == num) return false;
        }
        return true;
    }

    /**
     * Comprova si els valors donats són vàlids per a cada fila i columna del tauler.
     *
     * @param valors Matriu bidimensional d'enters que representa els valors a comprovar.
     * @return true si tots els valors són vàlids per a les seves respectives files i columnes, false en cas contrari.
     *
     * Un valor és vàlid per a una fila si no apareix en cap altra casella de la mateixa fila.
     * Un valor és vàlid per a una columna si no apareix en cap altra casella de la mateixa columna.
     *
     * Si un valor és 0, es considera que no té valor i per tant es considera vàlid.
     */
    public boolean corretgeix(int[][] valors) throws ExcepcioCasellaNoExisteix {
        for (int i = 1; i <= getGrau(); ++i) {
            for (int j = 1; j <= getGrau(); ++j) {
                if (valors[i-1][j-1] != 0) {
                    if (!esFilaValida(i, valors[i-1][j-1]) || !esColumValida(j, valors[i-1][j-1])) {
                        return false;
                    }
                }
            }
            ArrayList<Regio> regionsIncorrectes = getRegionsIncorrectes(valors);
            if (!regionsIncorrectes.isEmpty()) return false;
        }
        return true;
    }
    public int[] getValorsRegioMatriu(int [][] valorsTauler, int [][] posicionsRegio) {
        int [] valorsRegio = new int[posicionsRegio.length];
        for (int i = 0; i < posicionsRegio.length; ++i) {
            valorsRegio[i] = valorsTauler[posicionsRegio[i][0]][posicionsRegio[i][1]];
        }
        return valorsRegio;
    }

    public ArrayList<Regio> getRegionsIncorrectes(int [][] valorsTauler){
        ArrayList<Regio> regionsIncorrectes = new ArrayList<>();
        for (Regio r : getRegions()) {
            int [][] posicionsRegio = r.getPosicionsCaselles();
            int [] valorsRegio  =  getValorsRegioMatriu(valorsTauler, posicionsRegio);
            try {
                if (!r.esValida(valorsRegio)) {
                    regionsIncorrectes.add(r);
                }
            } catch (ExcepcioNoDivisor | ExcepcioMoltsValors | ExcepcioDivisio_0 | ExcepcioValorInvalid e) {
                regionsIncorrectes.add(r);
            }
        }
        return regionsIncorrectes;
    }

    /**
     * Per a cada posicio del tauler retorna un vector de Booleans amb 4 valors, top left bottom i right que és true si la casella de la posicio que indica pertany a la regió.
     * @return Matriu de Booleans de mida grau x grau on cada posicio indica si les caselles del voltant pertanyen a la regió.
     */
    public ArrayList<Boolean>[][] getAdjacents() {
        ArrayList<Boolean>[][] adjacents = new ArrayList[grau][grau];
        for (int i = 0; i < grau; i++) {
            for (int j = 0; j < grau; j++) {
                adjacents[i][j] = new ArrayList<>(4);
            }
        }
        for (Regio r: regions) {
            int[][] posicionsCaselles = r.getPosicionsCaselles();
            HashSet<Integer[]> casellesRegio = new HashSet<>();
            for (int i = 0; i < posicionsCaselles.length; i++){
                int x = posicionsCaselles[i][0];
                int y = posicionsCaselles[i][1];
                casellesRegio.add(new Integer[]{x, y});
            }
            for (int i = 0; i < posicionsCaselles.length; i++){
                int x = posicionsCaselles[i][0];
                int y = posicionsCaselles[i][1];
                Integer[] top = new Integer[]{x, y-1};
                if (!casellesRegio.contains(top)) adjacents[x-1][y-1].set(0, false);
                else adjacents[x-1][y-1].set(0, true);
                Integer[] left = new Integer[]{x-1, y};
                if (!casellesRegio.contains(left)) adjacents[x-1][y-1].set(1, false);
                else adjacents[x-1][y-1].set(1, true);
                Integer[] bottom = new Integer[]{x, y+1};
                if (!casellesRegio.contains(bottom)) adjacents[x-1][y-1].set(2, false);
                else adjacents[x-1][y-1].set(2, true);
                Integer[] right = new Integer[]{x+1, y};
                if (!casellesRegio.contains(right)) adjacents[x-1][y-1].set(3, false);
                else adjacents[x-1][y-1].set(3, true);
            }
        }
        return adjacents;
    }
}
