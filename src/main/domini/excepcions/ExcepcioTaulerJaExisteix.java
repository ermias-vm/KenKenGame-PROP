package main.domini.excepcions;

public class ExcepcioTaulerJaExisteix extends Exception {
    private String missatge;

    public ExcepcioTaulerJaExisteix() {
        this("El Tauler ja existeix");
    }

    public ExcepcioTaulerJaExisteix(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Tauler: " + missatge;
    }
}