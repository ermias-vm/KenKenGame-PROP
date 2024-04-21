package main.domini.excepcions;
/**
 * Excepció que es llença quan un nombre no és divisible per un altre.
 */
public class ExcepcioNoDivisor extends Exception {
    /**
     * Constructora de la classe.
     * @param valor el valor que no és divisor
     * @param valor2 el valor pel qual no és divisor
     */
    public ExcepcioNoDivisor(int valor, int valor2) {
    this ("Error: El valor " + valor + " no es divisor de " + valor2); ;
    }
    /**
     * Constructora de la classe amb missatge.
     * @param s missatge de l'excepció
     */
    public ExcepcioNoDivisor(String s) {
        super(s);
    }
}
