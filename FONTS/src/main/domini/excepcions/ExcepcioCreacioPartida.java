package main.domini.excepcions;

/**
 * Excepció que es llença quan hi ha un error en la creació d'una partida.
 */
public class ExcepcioCreacioPartida extends Exception {
    public ExcepcioCreacioPartida(String s) {
        super(s);
    }
}
