package main.domini.excepcions;
/**
 * Excepció que es llença quan hi ha un error en la càrrega d'una partida.
 */
public class ExcepcioCarregaPartida extends Exception {
    public ExcepcioCarregaPartida(String s) {
        super(s);
    }
}
