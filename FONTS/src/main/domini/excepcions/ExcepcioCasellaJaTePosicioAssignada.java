package main.domini.excepcions;
/**
 * Excepció que es llança quan s'intenta assignar una posició a una casella que ja té una posició assignada.
 *
 * @author Ermias Valls Mayor
 */
public class ExcepcioCasellaJaTePosicioAssignada extends Exception {
    private String missatge;

    /**
     * Crea una nova instància de ExcepcioCasellaJaTePosicioAssignada amb un missatge predeterminat.
     */
    public ExcepcioCasellaJaTePosicioAssignada() {
        this("La casella ja te una posicio assignada");
    }

    /**
     * Crea una nova instància de ExcepcioCasellaJaTePosicioAssignada amb un missatge específic.
     * @param s El missatge de l'excepció.
     */
    public ExcepcioCasellaJaTePosicioAssignada(String s) {
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
