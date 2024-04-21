package main.domini.excepcions;

/**
 * Excepció llançada quan es vol fer una operació sobre una partida que ja ha acabat.
 */
public class ExcepcioPartidaAcabada extends Exception {
    /**
     * Es crea una excepció amb el missatge per defecte.
     */
    public ExcepcioPartidaAcabada() {
        this("Error: partida acabada");
    }
    /**
     * Es crea una excepció amb el missatge introduït.
     * @param s missatge de l'excepció
     */
    public ExcepcioPartidaAcabada(String s) {
        super(s);
    }
}
