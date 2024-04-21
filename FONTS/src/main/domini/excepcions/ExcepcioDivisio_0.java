package main.domini.excepcions;

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
