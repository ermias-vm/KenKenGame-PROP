package main.domini.excepcions;

public class ExcepcioTaulerNoExisteix extends Exception {
    private String missatge;

    public ExcepcioTaulerNoExisteix() {
        this("El Tauler no existeix");
    }

    public ExcepcioTaulerNoExisteix(String s) {
        super(s);
        this.missatge = s;
    }

    @Override
    public String toString() {
        return "Excepcio Tauler: " + missatge;
    }
}