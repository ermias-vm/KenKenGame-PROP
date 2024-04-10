package main.domain.classes.src.main.domini.excepcions;

public class ExcepcioCasellaJaTePosicioAssignada extends Exception {
    private String missatge;

    public ExcepcioCasellaJaTePosicioAssignada() {
        this("La casella ja te una posicio assignada");
    }

    public ExcepcioCasellaJaTePosicioAssignada(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Casella: " + missatge;
    }
}
