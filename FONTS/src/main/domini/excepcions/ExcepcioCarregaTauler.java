package main.domini.excepcions;

/**
 * Excepció que es llença quan hi ha un error en la càrrega d'un tauler.
 */
public class ExcepcioCarregaTauler extends Exception {
    public ExcepcioCarregaTauler(String s) {
        super(s);
    }
}
