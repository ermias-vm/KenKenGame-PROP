package main.domini.excepcions;

public class ExcepcioUsuariNoExisteix extends Exception {
    private String missatge;

    public ExcepcioUsuariNoExisteix() {
        this("El Usuari NO existeix");
    }

    public ExcepcioUsuariNoExisteix(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Usuari: " + missatge;
    }
}
