package main.domain.classes.src.main.domini.excepcions;

public class ExcepcioCasellaNoModificable extends Exception {
    private String missatge;

    public ExcepcioCasellaNoModificable() {
        this("La casella no es pot modificar");
    }

    public ExcepcioCasellaNoModificable(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Casella: " + missatge;
    }
}
