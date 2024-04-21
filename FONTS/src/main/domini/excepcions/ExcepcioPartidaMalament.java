package main.domini.excepcions;

/**
 * Excepció llançada quan una partida no és correcta.
 */
public class ExcepcioPartidaMalament extends Exception {
    /**
     * Es crea una excepció amb un missatge d'error per defecte.
     */
    public ExcepcioPartidaMalament() {
        this("Error: partida malament");
    }
    /**
     * Es crea una excepció amb un missatge d'error.
     * @param s Missatge d'error.
     */
    public ExcepcioPartidaMalament(String s) {
        super(s);
    }
}
