package main.domini.excepcions;
/**
 * Excepció ExcepcioInicialitzacioControladorTauler: es dona quan hi ha un error en la inicialització del controlador de tauler
 */
public class ExcepcioInicialitzacioControladorTauler extends Exception {
    public ExcepcioInicialitzacioControladorTauler(String s) {
        super(s);
    }
}
