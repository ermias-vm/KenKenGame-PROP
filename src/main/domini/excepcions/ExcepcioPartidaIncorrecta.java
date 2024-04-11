package main.domini.excepcions;

public class ExcepcioPartidaIncorrecta extends Exception {
    private String missatge;

    public ExcepcioPartidaIncorrecta() {
        this("Els valors introduits no satisfan el tauler");
    }

    public ExcepcioPartidaIncorrecta(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Partida: " + missatge;
    }
}