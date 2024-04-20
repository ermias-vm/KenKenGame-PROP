package main.domini.excepcions;
/**
 * Excepció que es llença quan s'intenta accedir a una partida que ha estat tancada.
 */
public class ExcepcioPartidaTancada extends Exception {
    /**
     * Es crea una excepció amb el missatge predeterminat "Error: partida tancada".
     */
    public ExcepcioPartidaTancada() {
        this("Error: partida tancada");
    }
    /**
     * Es crea una excepció amb el missatge que es passa per paràmetre.
     * @param s Missatge de l'excepció.
     */
    public ExcepcioPartidaTancada(String s) {
        super(s);
    }
}
