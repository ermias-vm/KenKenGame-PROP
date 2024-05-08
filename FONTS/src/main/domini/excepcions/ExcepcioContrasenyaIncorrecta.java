package main.domini.excepcions;

public class ExcepcioContrasenyaIncorrecta extends Exception {
    private String missatge;

    public ExcepcioContrasenyaIncorrecta() {
        this("La contrasenya es incorrecta");
    }

    public ExcepcioContrasenyaIncorrecta(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Contrasenya: " + missatge;
    }
}