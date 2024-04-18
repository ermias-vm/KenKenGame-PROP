package main.domini.excepcions;

public class ExcepcioPartidaTancada extends Exception {
    private String missatge;

    public ExcepcioPartidaTancada() {
        this("Error: partida tancada");
    }

    public ExcepcioPartidaTancada(String s) {
        super(s);
        this.missatge = s;
    }
}
