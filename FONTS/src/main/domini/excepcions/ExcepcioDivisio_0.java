package main.domini.excepcions;

/**
 * ExcepcioDivisio_0 es una excepcio que salta quan es vol dividir per 0
 */
public class ExcepcioDivisio_0 extends Exception {
    private String missatge_;
    public ExcepcioDivisio_0() {
        this("Error: divisio per 0");
    }
    public ExcepcioDivisio_0(String s) {
        super(s);
        this.missatge_ = s;
    }
}
