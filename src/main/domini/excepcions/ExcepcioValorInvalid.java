package main.domain.classes.src.main.domini.excepcions;

public class ExcepcioValorInvalid extends Exception {
    private String missatge;

    public ExcepcioValorInvalid() {
        this("El valor introduit es invalid");
    }

    public ExcepcioValorInvalid(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Partida: " + missatge;
    }
}