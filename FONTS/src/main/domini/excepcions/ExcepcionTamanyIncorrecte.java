package main.domini.excepcions;

public class ExcepcionTamanyIncorrecte extends Exception {
    private String missatge;

    public ExcepcionTamanyIncorrecte() {
        this("El tamany es incorrecta");
    }

    public ExcepcionTamanyIncorrecte(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Regio: " + missatge;
    }
}
