package main.domini.excepcions;

/**
 * Excepció que es llança quan es tracta de modificar una casella que no es pot modificar.
 *
 * @author Ermias Valls Mayor
 */
public class ExcepcioCasellaNoModificable extends Exception {
    private String missatge;

    /**
     * Crea una nova instància de ExcepcioCasellaNoModificable amb un missatge predeterminat.
     */
    public ExcepcioCasellaNoModificable() {
        this("La casella no es pot modificar");
    }

    /**
     * Crea una nova instància de ExcepcioCasellaNoModificable amb un missatge específic.
     * @param s El missatge de l'excepció.
     */
    public ExcepcioCasellaNoModificable(String s) {
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
