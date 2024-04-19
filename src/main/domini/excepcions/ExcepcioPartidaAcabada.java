package main.domini.excepcions;

public class ExcepcioPartidaAcabada extends Exception {
    public ExcepcioPartidaAcabada() {
        this("Error: partida acabada");
    }
    public ExcepcioPartidaAcabada(String s) {
        super(s);
    }
}
