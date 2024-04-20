package main.domini.excepcions;
/**
 * Excepció que es llença quan el valor introduït en una casella no és vàlid ja que no es troba dins del rang de valors possibles per aquell tauler.
 */
public class ExcepcioValorInvalid extends Exception {
    /**
     * Creació de l'excepció amb un missatge per defecte.
     */
    public ExcepcioValorInvalid() {
        this("El valor introduït es invàlid");
    }
    /**
     * Creació de l'excepció amb un missatge.
     * @param s Missatge d'error.
     */
    public ExcepcioValorInvalid(String s) {
        super(s);
    }
}
