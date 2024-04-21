package main.domini.excepcions;

public class ExcepcioUsuariJaExisteix extends Exception {
    private String missatge;

    public ExcepcioUsuariJaExisteix() {
        this("El Usuari JA existeix");
    }

    public ExcepcioUsuariJaExisteix(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Usuari: " + missatge;
    }
}
