package main.domini.excepcions;

public class ExcepcioCasellaNoExisteix extends Exception {
    private String missatge;


    public ExcepcioCasellaNoExisteix(int x, int y) {
        this("La casella en la posició (" + x + ", " + y + ") no existeix.");
    }

    public ExcepcioCasellaNoExisteix(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Casella: " + missatge;
    }
}