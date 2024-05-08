package main.domini.excepcions;

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