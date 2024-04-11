package main.domini.classes;
import main.domini.excepcions.*;
public class Casella {

    private int valor;
    private int posX;
    private int posY;
    private boolean modificable;


    public Casella(){
        this(-1,-1);
    }

    public Casella(int x, int y) {
        this.valor = 0;
        this.posX = x;
        this.posY = y;
        this.modificable = true;
    }

    public void setValor(int num) {
        try {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = num;
        } catch (ExcepcioCasellaNoModificable e) {
            System.out.println(e.getMessage());
        }
    }
    public void borrarValor() {
        try {
            if (!modificable) throw new ExcepcioCasellaNoModificable();
            this.valor = 0;
        } catch (ExcepcioCasellaNoModificable e) {
            System.out.println(e.getMessage());
        }
    }

    public int getValor(){
        return valor;
    }

    public int  getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

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

    public void  setInmodificable () {
        this.modificable = false;
    }

    public boolean esModificable() {
        return this.modificable;
    }
    public boolean esBuida() {
        return this.valor == 0;
    }

}
