package main.domini.excepcions;

/**
 * Excepció llançada quan no hi ha cap partida aleatòria disponible.
 */
public class ExcepcioNoPartidaAleatoria extends Exception {
    public ExcepcioNoPartidaAleatoria() {
        super("No hi ha cap partida aleatòria disponible.");
    }
}
