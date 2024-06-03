package main.domini.excepcions;

/**
 * Excepciò que es llença quan un usuari no existeix en la base de dades.
 */
public class ExcepcioUsuariNoExisteix extends Exception {
    private String missatge;

    public ExcepcioUsuariNoExisteix(String nomUsuari) {
        super("El Usuari " + nomUsuari + " NO existeix");
        this.missatge = "El Usuari " + nomUsuari + " NO existeix";
    }

    @Override
    public String toString() {
        return "Excepcio Usuari: " + missatge;
    }
}