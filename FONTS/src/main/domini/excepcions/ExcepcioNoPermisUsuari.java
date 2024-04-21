package main.domini.excepcions;

/**
 * Excepció llançada quan un usuari no té permís per fer una operació, per exemple en intentar modificar una partida que no és seva.
 * La seva causa ve denotada en el missatge de l'excepció, assignat en llançar-la.
 */
public class ExcepcioNoPermisUsuari extends Exception {
    /**
     * Es crea una excepció amb el missatge introduït.
     * @param s missatge de l'excepció
     */
    public ExcepcioNoPermisUsuari(String s) {
        super(s);
    }
}
