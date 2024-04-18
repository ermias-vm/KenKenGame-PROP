package main.domini.excepcions;

public class ExcepcioPartidaMalament extends Exception {
    private String missatge;

    public ExcepcioPartidaMalament() {
        this("Error: partida malament");
    }

    public ExcepcioPartidaMalament(String s) {
        super(s);
        this.missatge = s;
    }
}
