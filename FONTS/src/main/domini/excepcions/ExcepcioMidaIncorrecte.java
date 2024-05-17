package main.domini.excepcions;

public class ExcepcioMidaIncorrecte extends Exception {
    private String missatge;

    public ExcepcioMidaIncorrecte() {
        this("La mida es incorrecta");
    }

    public ExcepcioMidaIncorrecte(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Regio: " + missatge;
    }
}
