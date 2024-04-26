package main.domini.excepcions;

public class CasellaNoExisteixException extends Exception {
    private String missatge;

    /**
     * Crea una nova instància de CasellaNoExisteixException amb un missatge predeterminat.
     */
    public CasellaNoExisteixException(int x, int y) {
        this("La casella en la posició (" + x + ", " + y + ") no existeix.");
    }

    /**
     * Crea una nova instància de CasellaNoExisteixException amb un missatge específic.
     * @param s El missatge de l'excepció.
     */
    public CasellaNoExisteixException(String s) {
        super(s);
        this.missatge = s;
    }

    /**
     * Retorna una representació en cadena de l'excepció.
     * @return Una cadena que representa l'excepció.
     */
    @Override
    public String toString() {
        return "Excepcio Casella: " + missatge;
    }
}