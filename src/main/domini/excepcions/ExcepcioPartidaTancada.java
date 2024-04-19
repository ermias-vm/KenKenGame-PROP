package main.domini.excepcions;

public class ExcepcioPartidaTancada extends Exception {
    public ExcepcioPartidaTancada() {
        this("Error: partida tancada");
    }

    public ExcepcioPartidaTancada(String s) {
        super(s);
    }
}
