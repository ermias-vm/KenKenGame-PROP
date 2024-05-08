package main.domini.excepcions;

public class ExcepcioUsuariJaExisteix extends Exception {
    private String missatge;

    public ExcepcioUsuariJaExisteix(String nomUsuari) {
        super("El Usuari " + nomUsuari + " ja existeix");
        this.missatge = "El Usuari " + nomUsuari + " ja existeix";
    }

    @Override
    public String toString() {
        return "Excepcio Usuari: " + missatge;
    }
}