package main.domini.excepcions;

/**
 * Excepció llençada quan s'intenta efectuar una operació de càrrega/guardat i el controlador de persistència no s'ha inicialitzat correctament.
 */
public class ExcepcioInicialitzacioPersistenciaPartida extends Exception {
    /**
     * Es crea una excepció amb el missatge introduït.
     * @param s missatge de l'excepció
     */
    public ExcepcioInicialitzacioPersistenciaPartida(String s) {
        super(s);
    }
}
