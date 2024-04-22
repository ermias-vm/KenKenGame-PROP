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

    private int valor;
    private int posX;
    private int posY;
    private boolean modificable;

    /**
     * Constructor per defecte. Inicialitza la cel·la amb la posició (-1, -1).
     */
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
     * @throws ExcepcioCasellaNoModificable Si la cel·la no és modificable.
     */
    public void setValor(int num) {
        try {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = num;
        } catch (ExcepcioCasellaNoModificable e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Esborra el valor de la cel·la (l'estableix a 0).
     * @throws ExcepcioCasellaNoModificable Si la cel·la no és modificable.
     */
    public void borrarValor() {
        try {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = 0;
        } catch (ExcepcioCasellaNoModificable e) {
            System.out.println(e.getMessage());
        }
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
     * @throws ExcepcioCasellaJaTePosicioAssignada Si la cel·la ja té una posició assignada.
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
