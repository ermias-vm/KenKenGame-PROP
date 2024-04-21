package main.domini.excepcions;

/**
 * Excepció que es llença quan es vol carregar una partida i ja hi ha una partida en curs que no s'ha acabat o tancat.
 */
public class ExcepcioPartidaEnCurs extends Exception {
    /**
     * Es crea una excepció amb el missatge introduït.
     * @param s missatge de l'excepció
     */
    public ExcepcioPartidaEnCurs(String s) {
        super(s);
    }
}
