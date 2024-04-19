package main.domini.excepcions;

public class ExcepcioValorInvalid extends Exception {
    public ExcepcioValorInvalid() {
        this("El valor introduit es invàlid");
    }

    public ExcepcioValorInvalid(String s) {
        super(s);
    }
}
