package main.domini.excepcions;

public class ExcepcioValorInvalid extends Exception {
    private String missatge;

    public ExcepcioValorInvalid() {
        this("El valor introduit es invalid");
    }

    public ExcepcioValorInvalid(String s) {
        super(s);
        this.missatge = s;
    }
}
