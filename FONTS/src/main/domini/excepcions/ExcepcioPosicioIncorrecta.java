package main.domini.excepcions;

public class ExcepcioPosicioIncorrecta extends Exception {
    private String missatge;

    public ExcepcioPosicioIncorrecta() {
        this("La posició es incorrecta");
    }

    public ExcepcioPosicioIncorrecta(String s) {
        super(s);
        this.missatge = s;
    }
}
