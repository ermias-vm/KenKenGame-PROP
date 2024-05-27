package main.domini.classes;
import main.domini.excepcions.ExcepcioCasellaNoModificable;
import main.domini.excepcions.ExcepcioCasellaJaTePosicioAssignada;
/**
 * La classe Casella representa una cel·la en un joc de KenKen.
 * Conté el valor de la cel·la, la seva posició (x, y) i si és modificable o no.
 *
 * @author Ermias Valls Mayor
 */
public class Casella {
    /**
     * Valor de la casella. Aquest valor pot ser qualsevol enter entre 0 i el grau del tauler.
     * Un valor de 0 indica que la casella està buida.
     */
    private int valor;

    /**
     * Coordenada x de la casella en el tauler. Aquest valor és un enter entre 1 i el grau del tauler.
     */
    private int posX;

    /**
     * Coordenada y de la casella en el tauler. Aquest valor és un enter entre 1 i el grau del tauler.
     */
    private int posY;

    /**
     * Indica si la casella és modificable o no. Si és true, el valor de la casella pot ser canviat.
     * Si és false, el valor de la casella no pot ser canviat.
     */
    private boolean modificable;

    public Casella(){
        this(-1,-1);
    }

    /**
     * Constructor amb posició. Inicialitza la cel·la amb la posició donada i estableix el seu valor a 0 i modificable a true.
     * @param x La posició x de la cel·la.
     * @param y La posició y de la cel·la.
     */
    public Casella(int x, int y) {
        this.valor = 0;
        this.posX = x;
        this.posY = y;
        this.modificable = true;
    }

    /**
     * Estableix el valor de la cel·la.
     * @param num El valor a establir.
     */
    public void setValor(int num) throws ExcepcioCasellaNoModificable {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = num;
    }

    /**
     * Esborra el valor de la cel·la (l'estableix a 0).
     */
    public void borrarValor() throws ExcepcioCasellaNoModificable {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = 0;
    }

    /**
     * Retorna el valor de la cel·la.
     * @return El valor de la cel·la.
     */
    public int getValor(){
        return valor;
    }

    /**
     * Retorna la posició x de la cel·la.
     * @return La posició x de la cel·la.
     */
    public int  getPosX() {
        return this.posX;
    }

    /**
     * Retorna la posició y de la cel·la.
     * @return La posició y de la cel·la.
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Estableix la posició de la cel·la.
     * @param x La posició x a establir.
     * @param y La posició y a establir.
     */
    public void setPosXY(int x, int y) {
        try {
            if (this.posX != -1 && this.posY != -1) throw new ExcepcioCasellaJaTePosicioAssignada();
            this.posX = x;
            this.posY = y;
        }
        catch (ExcepcioCasellaJaTePosicioAssignada e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Estableix la cel·la com a no modificable.
     */
    public void  setInmodificable () {
        this.modificable = false;
    }

    /**
     * Retorna si la cel·la és modificable o no.
     * @return true si la cel·la és modificable, false en cas contrari.
     */
    public boolean esModificable() {
        return this.modificable;
    }

    /**
     * Retorna si la cel·la està buida o no.
     * @return true si la cel·la està buida (el seu valor és 0), false en cas contrari.
     */
    public boolean esBuida() {
        return this.valor == 0;
    }

}
