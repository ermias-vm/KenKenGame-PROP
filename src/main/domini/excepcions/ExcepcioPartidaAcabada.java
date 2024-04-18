package main.domini.excepcions;

public class ExcepcioPartidaAcabada extends Exception {
    private String missatge;

    public ExcepcioPartidaAcabada() {
        this("Error: partida acabada");
    }

    public ExcepcioPartidaAcabada(String s) {
        super(s);
        this.missatge = s;
    }
}
